package com.interest.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interest.entity.AccountHistory;
import com.interest.models.AccountHistoryId;

public interface AccountHistoryMapper extends JpaRepository<AccountHistory, AccountHistoryId>{

}
