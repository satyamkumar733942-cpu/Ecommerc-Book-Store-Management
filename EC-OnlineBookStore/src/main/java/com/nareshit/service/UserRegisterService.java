package com.nareshit.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nareshit.entity.UserRegister;
import com.nareshit.model.UserRequest;
import com.nareshit.model.UserRequestDto;

public interface UserRegisterService {

	public UserRegister  insertUserRegister(UserRequestDto userRequestDto);

	public UserRequest getUserRegisterDetails(Long  id);

	public UserRegister checkUserDetails(UserRequestDto userRequestDto);

	public UserRegister uploadMultiUserRegister(UserRequestDto userRequestDto, MultipartFile[] files);

	public List<UserRegister> getAllUsersRegisterDetails();

	
	

}
