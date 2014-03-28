package com.test;

import java.util.List;

import com.dto.UserDTO;
import com.factory.DAOFactory;

public class TestUserDAOSearch {

	public static void main(String[] args) throws Exception {
		UserDTO userDTO=new UserDTO();
		userDTO.setLogin("chandrabhan.ips11@gmail.com");
		userDTO.setPassword("aa");
		List list=DAOFactory.getInstance().getUser().search(userDTO);
		System.out.println(list.size());

	}

}
