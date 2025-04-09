package com.rewards.rewards.program.controller;


import com.rewards.rewards.program.exception.CustomerNotFoundException;
import com.rewards.rewards.program.service.RewardsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;



@WebMvcTest(RewardsController.class)
class RewardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardsService rewardsService;

    @Test
    void totalRewardsEarnedSuccess() throws Exception {
        Mockito.when(rewardsService.getOverallTotal("CUST001")).thenReturn(150.0);

        mockMvc.perform(get("/api/v1/rewards/CUST001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.custId").value("CUST001"))
                .andExpect(jsonPath("$.totalRewardPointsEarned").value(150.0));
    }

    @Test
    void totalRewardsEarnedInvalidCustId() throws Exception {
        mockMvc.perform(get("/api/v1/rewards/123"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Validation failed")));
    }

    @Test
    void rewardPointsMonthWiseSuccess() throws Exception {
        Mockito.when(rewardsService.getMonthlyTotal("CUST001", 3)).thenReturn(75.0);

        mockMvc.perform(get("/api/v1/rewards/CUST001/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.custId").value("CUST001"))
                .andExpect(jsonPath("$.totalRewardPointsEarned").value(75.0));
    }

    @Test
    void rewardPointsMonthWiseInvalidMonth() throws Exception {
        mockMvc.perform(get("/api/v1/rewards/CUST001/13")) // invalid month
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Validation failed")));
    }

    @Test
    void totalRewardsEarnedCustomerNotFound() throws Exception {
        Mockito.when(rewardsService.getOverallTotal("uttttta"))
                .thenThrow(new CustomerNotFoundException("Customer not found: uttttta"));

        mockMvc.perform(get("/api/v1/rewards/uttttta"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Customer not found: uttttta")));
    }
}
