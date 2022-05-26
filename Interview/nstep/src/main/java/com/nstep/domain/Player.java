package com.nstep.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Player {

    private String name;

    private String uuid;

//    private int currentScore;

    public Player(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID().toString();
//        this.currentScore = 0;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Player ")
                .append(this.name)
                .append(" - ")
                .append(this.uuid)
                .toString();
    }

}
