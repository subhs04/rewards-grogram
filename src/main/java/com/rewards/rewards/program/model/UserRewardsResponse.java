package com.rewards.rewards.program.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // null fields shouldn't be part of the response
public class UserRewardsResponse {
    String custId;
    Double rewardPointsEarnedForTheMonth;
    Double totalRewardPointsEarned;
    String month;


}
