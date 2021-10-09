package com.interest.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.interest.entity.Account;

public interface AccountMapper extends JpaRepository<Account, Integer>{
	
	@Query("FROM Account WHERE identification = ?1")
	Account findAccount(Long identification);
}
