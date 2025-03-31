package com.rewards.rewards.program.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRewardsRequest {
    @NotNull
    String custId;
}
