package com.ravikiran.service;

import com.ravikiran.modal.Deal;
import com.ravikiran.request.AddDealRequest;

public interface DealsService {

    Deal addDeal(AddDealRequest req);

    String removeDeal(Long productId);

    void checkAndHandleExpiredDeals();
}


