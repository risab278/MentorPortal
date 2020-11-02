package com.eg;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptEg {
	public static void main(String[] args) {
			String password = "abcdef";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);

			System.out.println(hashedPassword);
	  }
}
