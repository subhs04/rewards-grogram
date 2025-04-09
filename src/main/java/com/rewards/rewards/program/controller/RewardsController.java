package com.rewards.rewards.program.controller;

import com.rewards.rewards.program.exception.CustomerNotFoundException;
import com.rewards.rewards.program.model.UserRewardsResponse;
import com.rewards.rewards.program.service.RewardsService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller that exposes APIs to fetch reward points
 * for customers based on transaction history.
 *
 * Handles:
 * - Total rewards for a customer
 * - Monthly rewards for a customer
 */
@RestController
@RequestMapping("/api/v1/rewards")
@Validated
@Slf4j
public class RewardsController {

    @Autowired
    RewardsService rewardsService;

    /**
     * Calculates rewards points earned by the customer for the whole period .
     * @param custId
     * @return
     */
    @GetMapping("/{custId}")
    public ResponseEntity<UserRewardsResponse> totalRewardsEarned(@PathVariable @NotBlank @Size(min = 5, max = 10) String custId) {
        log.info("Calculating total reward points for customer: {} ", custId);
        Double totalRewardPoints = rewardsService.getOverallTotal(custId);

        UserRewardsResponse response = UserRewardsResponse.builder()
                .custId(custId)
                .totalRewardPointsEarned(totalRewardPoints)
                .build();
        return ResponseEntity.ok(response);

    }

    /**
     * Calculates reward points for a particluar month for an user .
     * Expects month in between (1-12)
     * @param custId
     * @param month
     * @return
     */
    @GetMapping("/{custId}/{month}")
    public ResponseEntity<UserRewardsResponse> rewardPointsMonthWise(@PathVariable @NotBlank @Size(min = 5, max = 10) String custId,
                                                                     @Pattern(regexp = "^(1[0-2]|[1-9])$", message = "Invalid month. " +
                                                                             "Please provide a number from 1 to 12.") @PathVariable String month ){
        log.info("Calculating monthly reward points for customer: {} and month: {}", custId, month);
        Double totalRewardPoints = rewardsService.getMonthlyTotal(custId,Integer.valueOf(month));

        UserRewardsResponse response = UserRewardsResponse.builder()
                .custId(custId)
                .totalRewardPointsEarned(totalRewardPoints)
                .build();
        return ResponseEntity.ok(response);

    }
}
