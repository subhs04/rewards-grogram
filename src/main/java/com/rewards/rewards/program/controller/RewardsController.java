package com.rewards.rewards.program.controller;

import com.rewards.rewards.program.model.UserRewardsResponse;
import com.rewards.rewards.program.service.RewardsService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rewards")
@Validated
public class RewardsController {

    @Autowired
    RewardsService rewardsService;

    @GetMapping("/{custId}")
    public ResponseEntity<UserRewardsResponse> totalRewardsEarned(@PathVariable @NotBlank @Size(min = 5, max = 10) String custId){
        Double totalRewardPoints = rewardsService.getOverallTotal(custId);

        UserRewardsResponse response = UserRewardsResponse.builder()
                .custId(custId)
                .totalRewardPointsEarned(totalRewardPoints)
                .build();
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{custId}/{month}")
    public ResponseEntity<UserRewardsResponse> rewardPointsMonthWise(@PathVariable @NotBlank @Size(min = 5, max = 10) String custId,
                                                                     @Pattern(regexp = "^(1[0-2]|[1-9])$", message = "Invalid month. " +
                                                                             "Please provide a number from 1 to 12.") @PathVariable String month ){
        Double totalRewardPoints = rewardsService.getMonthlyTotal(custId,Integer.valueOf(month));

        UserRewardsResponse response = UserRewardsResponse.builder()
                .custId(custId)
                .totalRewardPointsEarned(totalRewardPoints)
                .build();
        return ResponseEntity.ok(response);

    }
}
