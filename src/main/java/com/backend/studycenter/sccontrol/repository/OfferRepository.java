package com.backend.studycenter.sccontrol.repository;

import com.backend.studycenter.sccontrol.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Offer findOfferById(Long id);

}
