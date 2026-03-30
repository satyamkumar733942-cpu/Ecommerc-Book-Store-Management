package com.nareshit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nareshit.entity.BooksModule;
import com.nareshit.entity.CartModule;
import com.nareshit.entity.Customer;

public interface CartModuleRepo extends JpaRepository<CartModule, Long>{

	CartModule findByCustomerAndBooksModule(Customer customer, BooksModule booksModule);


}
