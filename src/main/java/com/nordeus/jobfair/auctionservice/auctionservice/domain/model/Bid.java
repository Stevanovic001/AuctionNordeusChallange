package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
@Getter
public class Bid {
    private AuctionId auctionId;
    private UserId userId;
    private final long time;
    public Bid(AuctionId auctionId, UserId userId) {
        this.auctionId = auctionId;
        this.userId = userId;
        this.time = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Bid{" +
                "auctionId=" + auctionId +
                ", userId=" + userId +
                '}';
    }
}
