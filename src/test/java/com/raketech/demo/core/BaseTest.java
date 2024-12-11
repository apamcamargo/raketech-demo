package com.raketech.demo.core;

import com.raketech.demo.utils.YamlReader;

public class BaseTest extends DriverFactory{
	
	public SeleniumHelper web = new SeleniumHelper();
	public YamlReader yamlReader = new YamlReader();
	public RestAssuredHelper api = new RestAssuredHelper();

}
