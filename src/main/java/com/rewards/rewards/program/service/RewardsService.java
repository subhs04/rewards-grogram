package com.rewards.rewards.program.service;

import com.rewards.rewards.program.repository.UserTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardsService {

    @Autowired
    private UserTransactionsRepository userTransactionsRepository;

    public Double getMonthlyTotal(String custId, int month) {
        return userTransactionsRepository.getTotalAmountByMonth(custId, month);
    }

    public Double getOverallTotal(String custId) {
        return userTransactionsRepository.getTotalAmountByUser(custId);
    }
}
