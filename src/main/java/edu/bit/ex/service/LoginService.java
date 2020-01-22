package edu.bit.ex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.bit.ex.mapper.LoginMapper;
import edu.bit.ex.vo.UserVO;


@Service
public class LoginService {//비즈니스로직
	
	@Autowired
	LoginMapper loginMapper;
	
	public UserVO loginUser(String id, String pw) {
		return loginMapper.loginUser(id, pw);
	}
	
	
	
	
	
	
	
	
}
