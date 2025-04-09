package com.rewards.rewards.program.service;

import com.rewards.rewards.program.exception.CustomerNotFoundException;
import com.rewards.rewards.program.exception.TransactionNotFoundException;
import com.rewards.rewards.program.repository.UserTransactionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardsService {
    private static final Logger logger = LoggerFactory.getLogger(RewardsService.class);

    @Autowired
    private UserTransactionsRepository userTransactionsRepository;

    /**
     * Returns monthly total points for customer and if
     * no transaction exits throws TransactionNotFoundException
     * @param custId
     * @param month
     * @return
     */
    public Double getMonthlyTotal(String custId, int month) {
        logger.info("Calculating rewards for customer {} in month {}", custId, month);
        Double monthlyTotal= userTransactionsRepository.getTotalAmountByMonth(custId, month);
        if(monthlyTotal ==null){
            throw new TransactionNotFoundException("No transactions exist for  customer "
                    + custId +" for given month");
        }
        return monthlyTotal;
    }

    /**
     * Returns overall total points earned by the customer and if
     *  no customer exits throws CustomerNotFoundException
     * @param custId
     * @return
     */
    public Double getOverallTotal(String custId)  {
        logger.info("Calculating overall reward points for customer {} ", custId);

        Double totalAmount= userTransactionsRepository.getTotalAmountByUser(custId);
        if(totalAmount ==null){
            throw new CustomerNotFoundException("Customer Id:  "+ custId +" not found");
        }
        return totalAmount;
    }
}
