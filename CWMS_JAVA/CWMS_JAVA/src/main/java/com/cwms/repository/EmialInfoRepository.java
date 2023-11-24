package com.cwms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwms.entities.EmailRequest;

public interface EmialInfoRepository extends JpaRepository<EmailRequest, String> {

}
