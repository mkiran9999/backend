package com.ravikiran.service;

import java.time.ZonedDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ravikiran.exception.DealNotFoundException;
import com.ravikiran.exception.ProductException;
import com.ravikiran.exception.ProductNotFoundException;
import com.ravikiran.modal.Deal;
import java.util.List;
import java.util.Optional;

import com.ravikiran.modal.Product;
import com.ravikiran.repository.DealsRepository;
import com.ravikiran.repository.ProductRepository;
import com.ravikiran.request.AddDealRequest;

@Service
public class DealsServiceImplementation implements DealsService {

    private ProductRepository productRepository;
    private DealsRepository dealsRepository;

    public DealsServiceImplementation(ProductRepository productRepository, DealsRepository dealsRepository) {
        this.productRepository = productRepository;
        this.dealsRepository = dealsRepository;
    }

    @Override
    public Deal addDeal(AddDealRequest req) {
        // Find the product by ID
        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + req.getProductId()));
        Deal deal = new Deal();
        deal.setBrand(product.getBrand());
        deal.setColor(product.getColor());
        deal.setDescription(product.getDescription());
        deal.setImageUrl(product.getImageUrl());
        deal.setQuantity(product.getQuantity());
        deal.setTitle(product.getTitle());
        deal.setCategory(product.getCategory());
        deal.setProductId(req.getProductId()); 
        deal.setDiscountedPrice(req.getDiscountedPrice());
        deal.setPrice(req.getPrice());
        deal.setDiscountPersent(req.getDiscountPercent());
        deal.setExpiryTime(req.getExpiryTime());

        return dealsRepository.save(deal);
    }
    
    @Override
    @Scheduled(fixedRate = 60000) // Run every minute (adjust as needed)
    public void checkAndHandleExpiredDeals() {
    	System.out.println(ZonedDateTime.now());
        List<Deal> expiredDeals = dealsRepository.findByExpiryTimeBefore(ZonedDateTime.now());
        for (Deal deal : expiredDeals) {
            dealsRepository.delete(deal);
        }
    }

	@Override
	public List<Deal> findAllDeals() {
		List<Deal> allItems = dealsRepository.findAll();
		return allItems;
	}

	@Override
	public Deal finddealById(Long id) throws DealNotFoundException {
		Optional<Deal> opt=dealsRepository.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new DealNotFoundException("product not found with id "+id);
	}
	
	@Override
    public boolean existsDealWithProductId(Long productId) {
        return dealsRepository.findByProductId(productId).isPresent();
    }
	

}
