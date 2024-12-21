package com.backend.studycenter.sc_trainerize.repository.material;

import com.backend.studycenter.sc_trainerize.model.material.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
