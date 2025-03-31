package com.rewards.rewards.program.controller;


import com.rewards.rewards.program.model.UserRewardsResponse;
import com.rewards.rewards.program.service.RewardsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RewardsControllerTest {

    @Mock
    private RewardsService rewardsService;

    @InjectMocks
    private RewardsController rewardsController;

    public RewardsControllerTest() {
        MockitoAnnotations.openMocks(this); // initialize mocks
    }

    @Test
    void testTotalRewardsEarned_validCustId_returnsResponse() {
        String custId = "CUST001";
        when(rewardsService.getOverallTotal(custId)).thenReturn(200.0);

        ResponseEntity<UserRewardsResponse> response = rewardsController.totalRewardsEarned(custId);

        assertEquals(200.0, response.getBody().getTotalRewardPointsEarned());
        assertEquals("CUST001", response.getBody().getCustId());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testRewardPointsMonthWise_validInputs_returnsResponse() {
        String custId = "CUST001";
        String month = "3";
        when(rewardsService.getMonthlyTotal(custId, Integer.valueOf(month))).thenReturn(75.0);

        ResponseEntity<UserRewardsResponse> response = rewardsController.rewardPointsMonthWise(custId, month);

        assertEquals(75.0, response.getBody().getTotalRewardPointsEarned());
        assertEquals("CUST001", response.getBody().getCustId());
        assertEquals(200, response.getStatusCodeValue());
    }
}
