package com.nareshit.serviceImpl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nareshit.entity.FilesEntity;
import com.nareshit.entity.UserRegister;
import com.nareshit.entity.mongo.UserRegisterMongo;
import com.nareshit.model.UserRequest;
import com.nareshit.model.UserRequestDto;
import com.nareshit.repository.FileRepo;
import com.nareshit.repository.UserRegisterRepo;
import com.nareshit.repository.mongo.UserRegisterMongoRepo;
import com.nareshit.service.UserRegisterService;

@Service
public class UserRegisterServiceimpl implements UserRegisterService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserRegisterServiceimpl.class);

	
	@Autowired
	private UserRegisterRepo userRegisterRepo;
	
	@Autowired
	private UserRegisterMongoRepo userRegisterMongoRepo;
	
	@Autowired FileRepo fileRepo;

	@Override
	public UserRegister insertUserRegister(UserRequestDto userRequestDto) {
		 logger.info("Registration serive layer calling or started");
		UserRegister user =new UserRegister();
		try {
		user.setFirstName(userRequestDto.getFirstName());
		user.setLastName(userRequestDto.getLastName());
		user.setEmail(userRequestDto.getEmail());
		user.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
		user.setContactId(userRequestDto.getContactId());
		 logger.info("Registration serive layer calling or ended");
		userRegisterRepo.save(user);
		
		UserRegisterMongo userMong = new UserRegisterMongo();
		userMong.setFirstName(userRequestDto.getFirstName());
		userMong.setLastName(userRequestDto.getLastName());
		userMong.setEmail(userRequestDto.getEmail());
		userMong.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
		userMong.setContactId(userRequestDto.getContactId());
		userRegisterMongoRepo.save(userMong);
		
		}catch (Exception e) {
            logger.error("New user creation process failed in Bookstore-DB . Exception:" +e.getMessage());    
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public UserRequest getUserRegisterDetails(Long id) {
		 Optional<UserRegister> byId = userRegisterRepo.findById(id);
		UserRegister userRegister = byId.get();
		return new UserRequest(userRegister.getFirstName(), userRegister.getLastName(),userRegister.getEmail());
	}

	@Override
	public UserRegister checkUserDetails(UserRequestDto userRequestDto) {
		
		UserRegister findbyEmail = userRegisterRepo.findByEmail(userRequestDto.getEmail());
		
		if(findbyEmail!=null) {
			
			String decode = new String(Base64.getDecoder().decode(findbyEmail.getPassword()));
			
			if(decode.equals(userRequestDto.getPassword())) {
				
				return findbyEmail;
			}
			
		}
		return findbyEmail;
		
//		return Optional.ofNullable(userRegisterRepo.findByEmail(userRequestDto.getEmail()))
//				       .filter(user -> new String(Base64.getDecoder().decode(user.getPassword()))
//				       .equals(userRequestDto.getPassword()))
//                       .orElse(null); 

		
	}
	@Override
	public UserRegister uploadMultiUserRegister(UserRequestDto userRequestDto, MultipartFile[] files) {
		UserRegister user =new UserRegister();
		try {
		user.setFirstName(userRequestDto.getFirstName());
		user.setLastName(userRequestDto.getLastName());
		user.setEmail(userRequestDto.getEmail());
		user.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
		user.setContactId(userRequestDto.getContactId());
		userRegisterRepo.save(user);
		if(files!=null && files.length>0) {
			for (MultipartFile multipartFile : files) {
				FilesEntity fss = new FilesEntity();
				fss.setFileName(multipartFile.getOriginalFilename());
				fss.setFileType(multipartFile.getContentType());
				fss.setData(multipartFile.getBytes());
				fileRepo.save(fss);
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	@Cacheable(value = "getAllUsers")
	public List<UserRegister> getAllUsersRegisterDetails() {
	 List<UserRegister> list = userRegisterRepo.findAll();
	 System.err.println("get the records from Database...............!");
	 return list;
	}

}
