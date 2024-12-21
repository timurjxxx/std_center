package com.backend.studycenter.sc_trainerize.repository.topic;

import com.backend.studycenter.sc_trainerize.model.topic.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Module, Long> {
}
