package com.raketech.demo.core;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class Hooks {
	
	private static DriverFactory factory = new DriverFactory();
	
	@BeforeEach
	public void beforeEach() {
		try {
			factory.getDriver();
		} catch (Exception e) {
			throw new IllegalStateException("Driver was not created");
		}
	}
	
	@AfterEach
	public void afterEach() {
		factory.closeDriver();
	}
	
	@AfterAll
	public static void afterAll() {
		factory.killDriver();
	}
	

}
