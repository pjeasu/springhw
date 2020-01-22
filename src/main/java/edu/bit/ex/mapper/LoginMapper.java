package edu.bit.ex.mapper;

import org.apache.ibatis.annotations.*;

import edu.bit.ex.vo.UserVO;

public interface LoginMapper { //¿µ¼Ó°èÃþ : persistence layer
	
	@Select("select * from users where username = #{username} and password = #{password}")
	public UserVO loginUser(@Param("username")String username, @Param("password")String password);
	
	
	
	
	
}
