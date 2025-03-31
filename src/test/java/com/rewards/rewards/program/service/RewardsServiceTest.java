package com.rewards.rewards.program.service;

import com.rewards.rewards.program.repository.UserTransactionsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RewardsServiceTest {
    @Mock
    private UserTransactionsRepository userTransactionsRepository;

    @InjectMocks
    private RewardsService rewardsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetMonthlyTotal() {
        String custId = "CUST001";
        int month = 3;
        when(userTransactionsRepository.getTotalAmountByMonth(custId, month)).thenReturn(100.0);

        Double result = rewardsService.getMonthlyTotal(custId, month);

        assertEquals(100.0, result);
        verify(userTransactionsRepository).getTotalAmountByMonth(custId, month);
    }

    @Test
    void testGetOverallTotal() {
        String custId = "CUST002";
        when(userTransactionsRepository.getTotalAmountByUser(custId)).thenReturn(250.0);

        Double result = rewardsService.getOverallTotal(custId);

        assertEquals(250.0, result);
        verify(userTransactionsRepository).getTotalAmountByUser(custId);
    }
}