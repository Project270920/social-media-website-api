package com.learning.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.learning.api.entities.Profile;

@EnableJpaRepositories
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
