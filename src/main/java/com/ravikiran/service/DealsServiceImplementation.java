package com.ravikiran.service;

import org.springframework.stereotype.Service;

import com.ravikiran.exception.ProductNotFoundException;
import com.ravikiran.modal.Deal;
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
        deal.setProduct(product);
        deal.setDiscountedPrice(req.getDiscountedPrice());
        deal.setPrice(req.getPrice());
        deal.setDiscountPercent(req.getDiscountPercent());
        deal.setExpiryTime(req.getExpiryTime());

        return dealsRepository.save(deal);
    }

	@Override
	public String removeDeal(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkAndHandleExpiredDeals() {
		// TODO Auto-generated method stub
		
	}

}
