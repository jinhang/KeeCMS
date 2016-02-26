package com.kee.cms.service;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class InitService {

	@PostConstruct
	public void init() {
	}


	public static void main(String[] args) {
		InitService init = new InitService();
		init.init();
	}
}
