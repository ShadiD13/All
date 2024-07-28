package com.utility;

import com.utility.AppConfig;

import filloreader.Xl_Reader;

public class Constants {
	
	static Xl_Reader xldata = new Xl_Reader(AppConfig.Path_TestData); 

	public static String browserName=xldata.getCellData("Login", "Browser", 2);

	}
