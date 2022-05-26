package com.nstep.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SnakeLadderParameter {

    private final int boardSquareNum;

    private final int snakeNum;

    private final int ladderNum;

}
