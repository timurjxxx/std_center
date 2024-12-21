package com.backend.studycenter.sccontrol.controller;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sccontrol.dto.OfferDTO;
import com.backend.studycenter.sccontrol.mapper.OfferMapper;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.sccontrol.model.Offer;
import com.backend.studycenter.sccontrol.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/offer/")
public class OfferController {
    private final Logger logger = LoggerFactory.getLogger(OfferController.class);
    @Autowired
    OfferService offerService;

    @PreAuthorize("hasAuthority('EDITOR')")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferDTO> save(@RequestBody OfferDTO offerDTO) {
        try {
            return ResponseEntity.ok(OfferMapper.INSTANCE.toDTO(offerService.addOffer(offerDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasAuthority('VIEWER')")
    @GetMapping("/getById/{id}")
    public ResponseEntity<OfferDTO> getById(@PathVariable(name = "id") Long id) {
        OfferDTO offerDTO = OfferMapper.INSTANCE.toDTO(offerService.getById(id));
        if (offerDTO == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(offerDTO);
        }
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @GetMapping("/getAll")
    public ResponseEntity<ArrayList<OfferDTO>> getAll() {
        ArrayList<Offer> offers = offerService.getAll();
        ArrayList<OfferDTO> offerDTOS = new ArrayList<>();
        for (Offer offer : offers) {
            offerDTOS.add(OfferMapper.INSTANCE.toDTO(offer));
        }
        if (offerDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(offerDTOS);
        }
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) throws EntityNotFoundException{
        try {
            offerService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
    @PreAuthorize("hasAuthority('EDITOR')")
    @PutMapping("/update/{id}")
    public ResponseEntity<OfferDTO> update(@RequestBody OfferDTO offerDTO, @PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(OfferMapper.INSTANCE.toDTO(offerService.update(offerDTO, id)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
