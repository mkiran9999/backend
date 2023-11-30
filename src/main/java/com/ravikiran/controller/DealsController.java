package com.ravikiran.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravikiran.exception.ProductException;
import com.ravikiran.modal.Deal;
import com.ravikiran.modal.Product;
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
    public ResponseEntity<?> addDealHandler(@RequestBody AddDealRequest req) {
    	if (dealsService.existsDealWithProductId(req.getProductId())) {
            return new ResponseEntity<>("Deal with the same product ID already exists", HttpStatus.BAD_REQUEST);
        }
        Deal addedDeal = dealsService.addDeal(req);
        return new ResponseEntity<>(addedDeal, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Deal>> findAllProduct() {
    	List<Deal> allItems = dealsService.findAllDeals();
		
		return new ResponseEntity<List<Deal>>(allItems,HttpStatus.OK);
    }
    @GetMapping("/id/{dealId}")
	public ResponseEntity<Deal> findProductByIdHandler(@PathVariable Long dealId) throws ProductException{
		
		Deal deal=dealsService.finddealById(dealId);
		
		return new ResponseEntity<Deal>(deal,HttpStatus.ACCEPTED);
	}
}
