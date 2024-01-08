package com.project.tabling.repository;

import com.project.tabling.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Partner findByUsername(String username);

}
