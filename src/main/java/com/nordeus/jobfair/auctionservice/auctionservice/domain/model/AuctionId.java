package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

import lombok.Getter;

import java.util.Random;
public class AuctionId {
    @Getter
    private int id;

    public AuctionId(){
        this.id = new Random().nextInt();
    }

    @Override
    public String toString() {
        return id + "";
    }
}
