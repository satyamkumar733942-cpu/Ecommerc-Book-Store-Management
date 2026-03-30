package com.nareshit.service;

import com.nareshit.entity.CartModule;

public interface CartModuleService {

	public CartModule addToCart(Long custemerId, Long bookId, int quantity);

	public void deleteToCart(Long id);

}
