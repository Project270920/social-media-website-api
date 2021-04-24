package com.learning.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.learning.api.entities.Address;

@EnableJpaRepositories
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
