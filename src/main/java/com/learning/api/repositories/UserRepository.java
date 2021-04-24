package com.learning.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.learning.api.entities.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer>
{
	public User findByUsernameAndEmailAndPhone(String username, String email, String phone);
	public User findByUsernameAndPassword(String username, String password);
	public Optional<User> findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.username= ?1 OR u.email= ?1 OR u.phone= ?1")
	public User findUserByUsernameOrEmailOrPhone(String user);
	
	@Query("SELECT u FROM User u WHERE u.username= ?1 OR u.email= ?1 OR u.phone= ?1 AND u.otp = ?2")
	public User findByUsernameOrEmailOrPhoneAndOtp(String obj, int otp);
	
	@Modifying
	@Query("UPDATE User u SET u.otp =:otp WHERE u.username =:username")
	public boolean updateOTP(@Param("username") String username,
							 @Param("otp") int otp);
}
