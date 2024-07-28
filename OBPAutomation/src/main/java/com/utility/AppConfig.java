package com.utility;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.InputStream;
	import java.util.Properties;


	public class AppConfig {
		public static String ScreenShotFolder;
		public static String Path_TestData;
		public static String Logindetails_TestData;
		public static String Path_ExtentReport;
		public static String chromepath;
		public static String internetexppath;
		public static String firefoxpath;
		public static String jsonfiles;
		public static String Sheet;
		public static String AllRecordQuery;
		public static String TestYouModule;
		public static String TestCatagory;
		public static String ClearNotification;
		public static String HomePage;
		public static String Testngfilepath;
		public static String Appconfigfile;


		static {
			try {
				ScreenShotFolder = xmlDataReader().getProperty("ScreenShotFolder");
				Path_TestData = xmlDataReader().getProperty("Path_TestData");
				Logindetails_TestData = xmlDataReader().getProperty("Logindetails_TestData");
				Path_ExtentReport = xmlDataReader().getProperty("Path_ExtentReport");
				chromepath = xmlDataReader().getProperty("chromepath");
				internetexppath = xmlDataReader().getProperty("internetexppath");
				firefoxpath = xmlDataReader().getProperty("firefoxpath");
				jsonfiles = xmlDataReader().getProperty("jsonfiles");
				Sheet = xmlDataReader().getProperty("Sheet");
				AllRecordQuery = xmlDataReader().getProperty("AllRecordQuery");
				TestYouModule = xmlDataReader().getProperty("TestYouModule");
				ClearNotification= xmlDataReader().getProperty("ClearNotification");
				HomePage= xmlDataReader().getProperty("HomePage");
				Testngfilepath= xmlDataReader().getProperty("Testngfilepath");
				Appconfigfile = xmlDataReader().getProperty("Appconfigfile");


			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static String getScreenShotFolder() {
			return AppConfig.ScreenShotFolder;
		}

		public static void setScreenShotFolder(String screenShotFolder) {
			AppConfig.ScreenShotFolder = screenShotFolder;
		}
		
		public static String getClearnotification() {
			return AppConfig.ClearNotification;
		}

		public static void setClearnotification(String clearnotification) {
			AppConfig.ClearNotification = clearnotification;
		}
		
		
		public static String getHomePage() {
			return AppConfig.HomePage;
		}

		public static void setHomePage(String homepage) {
			AppConfig.HomePage = homepage;
		}


		public static String getPath_TestData() {
			return AppConfig.Path_TestData;
		}

		public static void setPath_TestData(String path_TestData) {
			AppConfig.Path_TestData = path_TestData;
		}

		public static String getPath_ExtentReport() {
			return AppConfig.Path_ExtentReport;
		}

		public static void setPath_ExtentReport(String path_ExcelReport) {
			AppConfig.Path_ExtentReport = path_ExcelReport;
		}

		public static String getLogindetails() {
			return AppConfig.Logindetails_TestData;
		}

		public static void setLogindetails(String Logindetails_TestData) {
			AppConfig.Logindetails_TestData = Logindetails_TestData;
		}

		public static String getChromepath() {
			return AppConfig.chromepath;
		}

		public static void setChromepath(String chromepath) {
			AppConfig.chromepath = chromepath;
		}

		public static String getInternetpath() {
			return AppConfig.internetexppath;
		}

		public static void setInternetpath(String internetpath) {
			AppConfig.internetexppath = internetpath;
		}

		public static String getfirefoxpath() {
			return AppConfig.firefoxpath;
		}

		public static void setfirefoxpath(String firefoxpath) {
			AppConfig.firefoxpath = firefoxpath;
		}

		public static String getreposityjson() {
			return AppConfig.jsonfiles;
		}

		public static void setreposityjson(String jsonrepo) {
			AppConfig.jsonfiles = jsonrepo;
		}

		public static String getsheet() {
			return AppConfig.Sheet;
		}

		public static void setsheet(String sheetdata) {
			AppConfig.Sheet = sheetdata;
		}

		public static String getAllquery() {
			return AppConfig.AllRecordQuery;
		}

		public static void setAllquery(String query) {
			AppConfig.AllRecordQuery = query;
		}

		public static String getModulesheet() {
			return AppConfig.TestYouModule;
		}

		public static void setModulesheet(String module) {
			AppConfig.TestYouModule = module;
		}
		
		
		public static String getTestngpath() {
			return AppConfig.Testngfilepath;
		}

		public static void setTesngpath(String testngpath) {
			AppConfig.Testngfilepath = testngpath;
		}
		
		public static String getAppconfigfile() {
			return AppConfig.Appconfigfile;
		}

		public static void setAppconfigfile(String appconfigfile) {
			AppConfig.Appconfigfile = appconfigfile;
		}

		public static Properties xmlDataReader() throws Exception {
			File file = new File("C:\\appconfigxml\\OBPAppconfig.xml");
			//File file = new File("\\OBPAutomation\\appconfigxml\\OBPAppconfig.xml");
			//File file = new File(AppConfig.Appconfigfile);
			InputStream inpStream = new FileInputStream(file);  
			Properties prop = new Properties();
			prop.loadFromXML(inpStream);
			return prop;
		}
	}
	

