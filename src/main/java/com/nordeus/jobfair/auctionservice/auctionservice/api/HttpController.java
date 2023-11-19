package com.nordeus.jobfair.auctionservice.auctionservice.api;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.AuctionService;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.AuctionServiceImpl;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Auction;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.AuctionId;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.User;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.UserId;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/auctions")
public class HttpController {
    @Autowired
    private AuctionService auctionService;

    @GetMapping("/active")
    public Collection<Auction> getAllActive() {
        return auctionService.getAllActive();
    }

    @PostMapping("/join/{auctionId}/{user}")
    public void joinAuc(@RequestParam("auctionId") AuctionId auctionId, @RequestParam("user") User user) {
        auctionService.join(auctionId, user);
    }
    @PostMapping("/bid/{auctionId}/{userId}")
    public void bidAuc(@RequestParam("auctionId") AuctionId auctionId, @RequestParam("user") UserId userId){
        auctionService.bid(auctionId, userId);
    }

    @GetMapping("/test")
    public void test(){
        Auction auction = getAllActive().iterator().next();
        User user = new User("Miladniko");
        joinAuc(auction.getAuctionId(), user);
        bidAuc(auction.getAuctionId(), user.getUserId());
    }

}
