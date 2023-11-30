package com.ravikiran.service;

import java.util.List;

import com.ravikiran.modal.Deal;
import com.ravikiran.request.AddDealRequest;

public interface DealsService {

    Deal addDeal(AddDealRequest req);

    void checkAndHandleExpiredDeals();
    
    public List<Deal> findAllDeals();
    
    public Deal finddealById(Long id);
    
    boolean existsDealWithProductId(Long productId);
}


