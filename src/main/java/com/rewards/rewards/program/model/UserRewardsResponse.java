package com.rewards.rewards.program.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRewardsResponse {
    String custId;
    Double rewardPointsEarnedForTheMonth;
    Double totalRewardPointsEarned;
    String month;

}
