package com.test;

import java.net.InetAddress;

public class GetIp {
	
	public static void main(String[] args) throws Exception {
		InetAddress  address= InetAddress.getByName("www.sunrays.co.in");
		System.out.println(address);
	}

}
