package com.nordeus.jobfair.auctionservice.auctionservice.domain;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.*;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.service.AuctionNotifer;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.service.AuctionNotifierLogger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;

@Service

public class AuctionServiceImpl implements AuctionService {

    private final AuctionNotifer auctionNotifer;



    private Collection<Auction> auctionList;
    private Timer timer;

    public AuctionServiceImpl() {
        this.auctionNotifer = new AuctionNotifierLogger();
        this.auctionList = new CopyOnWriteArrayList<>();
        this.timer = new Timer();
        this.timer.schedule(new CreateAuction(auctionList, auctionNotifer), 0, 60000);
        this.timer.schedule(new AuctionRefresh(auctionList, auctionNotifer),2000, 2000);
    }

    @Override
    public Collection<Auction> getAllActive() {
        return auctionList;
    }

    @Override
    public Auction getAuction(AuctionId auctionId) {
        synchronized (auctionList){
            for (Auction a: auctionList) {
                if(a.getAuctionId() == auctionId)
                    return a;
        }

        }
        return null;
    }

    @Override
    public void join(AuctionId auctionId, User user) {
        synchronized (auctionList){
            for(Auction a : auctionList) {
                if(a.getAuctionId() == auctionId){
                    a.getUsers().add(user);
                }
            }
        }
    }

    @Override
    public void bid(AuctionId auctionId, UserId userId) {
        Bid bid = new Bid(auctionId, userId);
        synchronized (auctionList) {
            for (Auction a : auctionList) {
                if (a.getAuctionId() == auctionId) {
                    a.setCurrentBid(bid);
                    a.incrementPrice();
                }
            }
        }
        auctionNotifer.bidPlaced(bid);
    }
}
