package com.backend.studycenter.sccontrol.service;

import com.backend.studycenter.sccontrol.dto.OfferDTO;
import com.backend.studycenter.sccontrol.mapper.OfferMapper;
import com.backend.studycenter.sccontrol.model.Offer;
import com.backend.studycenter.sccontrol.repository.OfferRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OfferService {
    @Autowired
    OfferRepository offerRepository;

    public ArrayList<Offer> getAll() {
        return (ArrayList<Offer>) offerRepository.findAll();
    }

    public Offer getById(Long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found with given ID: " + id));
    }

    public Offer addOffer(OfferDTO offerDTO) {
        return offerRepository.save(OfferMapper.INSTANCE.toModel(offerDTO));
    }

    public Offer update(OfferDTO offerDTO, Long id) throws EntityNotFoundException {
        Offer existOffer = offerRepository.findOfferById(id);
        if (existOffer == null) {
            throw new EntityNotFoundException("Offer not found");
        }
        Offer updateOffer = OfferMapper.INSTANCE.toModel(offerDTO);
        updateOffer.setId(existOffer.getId());
        offerRepository.save(updateOffer);
        return updateOffer;
    }

    public void deleteById(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found data with given ID: " + id));
        offerRepository.deleteById(id);
    }

    public void clear() {

        offerRepository.deleteAll();
    }
}
