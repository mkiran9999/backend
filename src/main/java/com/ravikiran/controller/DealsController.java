package com.ravikiran.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravikiran.modal.Deal;
import com.ravikiran.request.AddDealRequest;
import com.ravikiran.service.DealsService;

@RestController
@RequestMapping("/api/admin/deals")
public class DealsController {

    private DealsService dealsService;

    public DealsController(DealsService dealsService) {
        this.dealsService = dealsService;
    }

    @PostMapping("/add")
    public ResponseEntity<Deal> addDealHandler(@RequestBody AddDealRequest req) {
        Deal addedDeal = dealsService.addDeal(req);
        return new ResponseEntity<>(addedDeal, HttpStatus.CREATED);
    }
}
