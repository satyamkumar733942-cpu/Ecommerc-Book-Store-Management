package com.nareshit.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nareshit.entity.BooksExcelFile;
import com.nareshit.repository.BooksExcelFileRepo;
import com.nareshit.service.BooksExcelUploadService;
import com.nareshit.utility.Helper;

@Service
public class BooksExcelUploadServiceImpl implements BooksExcelUploadService {

	@Autowired
	BooksExcelFileRepo booksExcelFileRepo;

	@Override
	public void uploadExcelintoDB(MultipartFile file) throws IOException {
		List<BooksExcelFile> excelFilesSaveDatabase = Helper.excelFilesInsertDatabase(file.getInputStream());
		booksExcelFileRepo.saveAll(excelFilesSaveDatabase);
	}
}
