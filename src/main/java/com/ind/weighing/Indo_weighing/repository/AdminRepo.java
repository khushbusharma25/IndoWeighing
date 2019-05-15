package com.ind.weighing.Indo_weighing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.ind.weighing.Indo_weighing.domain.AdminDTO;


public interface AdminRepo extends MongoRepository<AdminDTO, Long> {

}
