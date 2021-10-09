package com.interest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.interest.entity.AccountHistory;
import com.interest.mapper.AccountHistoryMapper;


@Service
public class AccountHistoryImpl {

	@Autowired
	private AccountHistoryMapper accountHistoryMapper;
	
	@Autowired
    private ApplicationEventPublisher publisher;

    
    @Transactional
    public void history(AccountHistory accountHistory) {
    	accountHistoryMapper.save(accountHistory);
        publisher.publishEvent(accountHistory);
    }
}
