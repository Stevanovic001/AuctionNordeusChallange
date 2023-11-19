package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter

public class Auction {

    private final AuctionId auctionId;

    private String player;

    @Setter
    private Bid currentBid;

    private List<User> users;

    private long startT;

    private int price;

    public Auction() {
        this.auctionId = new AuctionId();
        this.player = player;
        this.users = new ArrayList<>();
        this.startT = System.currentTimeMillis();
        this.price = 1;
    }

    public void incrementPrice() {
        this.price++;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "auctionId=" + auctionId +
                ", currentBid=" + currentBid +
                ", price=" + price +
                '}';
    }
}
