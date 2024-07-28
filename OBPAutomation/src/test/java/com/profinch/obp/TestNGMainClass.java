package com.profinch.obp;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

import com.utility.AppConfig;

public class TestNGMainClass {

	public static void main(String[] args) {

		TestNG runner=new TestNG();
		List<String> suitefiles=new ArrayList<String>();
		suitefiles.add(AppConfig.Testngfilepath);
		runner.setTestSuites(suitefiles);
		runner.run();
		}
}

