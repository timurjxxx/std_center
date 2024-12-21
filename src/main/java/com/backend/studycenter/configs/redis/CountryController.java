package com.backend.studycenter.configs.redis;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.model.util.util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private CountryService countryService;
//    @Autowired
//    private CountryAddService countryAddService;

    @Operation(summary = "Get all countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the countrys",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Country.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Country not found",
                    content = @Content)})
    @GetMapping(value = "/api/v1/countries")
    public ResponseEntity<ArrayList<Country>> getCountrys() {
        ArrayList<Country> countries = countryService.getCountries();
        if (countries.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(countries);
        }
    }

    @Operation(summary = "Get a country by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the country",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Country.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Country not found",
                    content = @Content)})
    
    @GetMapping(value = "/api/v1/countries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable(name = "id") Long countryId) throws EntityNotFoundException {
        Country country = countryService.getCountryById(countryId);
        if (country == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(country);
        }
    }

    @Operation(summary = "Adding a country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added the country",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Country.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Country not found",
                    content = @Content)})
    
    @PostMapping(value = "/api/v1/countrys", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        try {
            Country addedCountry = countryService.addCountry(country);

            return ResponseEntity.ok(addedCountry);
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Editing a country by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edited the country",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Country.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Country not found",
                    content = @Content)})
    
    @PutMapping(value = "/api/v1/countries/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> updateCountry(@RequestBody Country country, @PathVariable(name = "id") Long countryId) throws EntityNotFoundException {
        try {
            return ResponseEntity.ok(countryService.updateCountry(country, countryId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Deleting a country by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted a country",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Country.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Country not found",
                    content = @Content)})
    
    @DeleteMapping(value = "/api/v1/countrys/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable(name = "id") Long countryId) throws EntityNotFoundException {
        try {
            countryService.deleteCountry(countryId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
