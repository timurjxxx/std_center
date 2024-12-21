package com.backend.studycenter.sc_trainerize.repository.material;

import com.backend.studycenter.sc_trainerize.model.material.VideoServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoServerRepository extends JpaRepository<VideoServer, Long> {
}
