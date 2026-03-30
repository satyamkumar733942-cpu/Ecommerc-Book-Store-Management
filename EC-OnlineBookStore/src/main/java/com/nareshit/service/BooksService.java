package com.nareshit.service;

import java.util.List;

import com.nareshit.entity.BooksModule;

public interface BooksService {

	BooksModule custmerSaveBooks(BooksModule booksModule);

	List<BooksModule> custmergetAllBooks();

	BooksModule getByCustmerBookid(Long id);

}
