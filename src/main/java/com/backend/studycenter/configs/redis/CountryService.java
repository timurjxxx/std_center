package com.backend.studycenter.configs.redis;

import static org.slf4j.LoggerFactory.getLogger;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

  private final Logger logger = getLogger(getClass().getName());

  @Autowired
  private CountryRepository countryRepository;

  @Cacheable(value = "countries", key = "'all'")
  public ArrayList<Country> getCountries() {
    logger.info("Countries are retrieved from DB");
    return (ArrayList<Country>) countryRepository.findAll();
  }

  @Cacheable(value = "countries", key = "#countryId")
  public Country getCountryById(Long countryId) throws EntityNotFoundException {
    logger.info("a country is retrieved from DB");
    Country country = countryRepository.findById(countryId)
        .orElseThrow(() -> new EntityNotFoundException("Not found country with id = " + countryId));
    return country;
  }

  @CacheEvict(value = "countries", allEntries = true)
  public Country addCountry(Country country) {
    logger.info("a country is added into DB");
    Country savedCountry = countryRepository.save(country);
    return savedCountry;
  }

  @CacheEvict(value = "countries", allEntries = true)
  public void deleteCountry(Long countryId) throws EntityNotFoundException {
    logger.info("a country is deleted from DB");

    Country country = countryRepository.findById(countryId)
        .orElseThrow(() -> new EntityNotFoundException("Not found country with id = " + countryId));
    countryRepository.delete(country);
  }

  public Country updateCountry(Country country, Long countryId) throws EntityNotFoundException {
    Optional<Country> countryOptional = countryRepository.findById(countryId);
    if (countryOptional.isEmpty()) {
      throw new EntityNotFoundException("Not found country with id = " + countryId);
    } else {
//      Country country = countryOptional.get();
      //TODO: update
      return countryRepository.save(country);
    }
  }

}
