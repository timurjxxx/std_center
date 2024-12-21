package com.backend.studycenter.sc_trainerize.repository.material;

import com.backend.studycenter.sc_trainerize.model.material.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
