package com.nordeus.jobfair.auctionservice.auctionservice.domain;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Auction;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.service.AuctionNotifer;

import java.util.Collection;
import java.util.TimerTask;

public class AuctionRefresh extends TimerTask {
    private Collection<Auction> auctionList;
    private final AuctionNotifer auctionNotifer;

    public AuctionRefresh(Collection<Auction> auctionList, AuctionNotifer auctionNotifer) {
        this.auctionList = auctionList;
        this.auctionNotifer = auctionNotifer;
    }

    @Override
    public void run() {
        long currentT = System.currentTimeMillis();
        synchronized (auctionList) {
            for (Auction a: auctionList) {
                long timeE = currentT - a.getStartT();
                if(timeE > 59650){
                    if(a.getCurrentBid() == null || currentT - a.getCurrentBid().getTime() > 5000){
                        auctionNotifer.auctionFinished(a);
                        auctionList.remove(a);
                    }
                }
            }
        }
    }
}
