package com.backend.studycenter.sc_trainerize.repository.topic;

import com.backend.studycenter.sc_trainerize.model.topic.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

}
