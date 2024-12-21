package com.backend.studycenter.sccontrol.repository;

import com.backend.studycenter.sccontrol.model.Webinar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface WebinarRepository extends JpaRepository<Webinar ,Long> {
}
