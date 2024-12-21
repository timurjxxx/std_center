package com.backend.studycenter.scteach.repository;

import com.backend.studycenter.scteach.model.Proposal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {

}
