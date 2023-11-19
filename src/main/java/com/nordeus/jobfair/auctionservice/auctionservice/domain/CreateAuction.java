package com.nordeus.jobfair.auctionservice.auctionservice.domain;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Auction;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.service.AuctionNotifer;

import java.util.Collection;
import java.util.TimerTask;

public class CreateAuction extends TimerTask {

    private Collection<Auction> auctionList;
    private final AuctionNotifer auctionNotifer;

    public CreateAuction(Collection<Auction> auctionList, AuctionNotifer auctionNotifer) {
        this.auctionList = auctionList;
        this.auctionNotifer = auctionNotifer;
    }

    @Override
    public void run() {
        synchronized (auctionList) {
            for(int i = 0; i < 10; i++){
                auctionList.add(new Auction());
            }
        }
        auctionNotifer.activeAuctionsRefreshed(auctionList);
    }
}
