package com.profinch.obp;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.utility.AppConfig;
import com.utility.CommonFile;
import com.utility.Utility;
import filloreader.FilloReader;

public class ProfinchSeleniumOBP extends Utility{
	
	@SuppressWarnings("unused")
	 WebDriver driver;

	public ProfinchSeleniumOBP(){
		driver=CommonFile.driver;
	}

	public static final Logger log = Logger.getLogger(ProfinchSeleniumOBP.class.getName());
	
	List<Map<String, String>> pram = new ArrayList<Map<String, String>>();    
	FilloReader fillo;
	Utility util=new Utility();
	CommonFile comm=new CommonFile();
	AppConfig app=new AppConfig();
	public String Refnumb;
	public String HostRefnumb;
	 String screenName;
	public String statusp="Pass";
	public String statusf="Fail";

	
	 
	@BeforeTest
	public void setup() throws Exception 
		{
			CommonFile.launch();
			Thread.sleep(5000);
			util.setUp();
		}

	@Test
	public void AutomationModule() throws Exception 
		{
		//VideoRecorder_utlity.startRecord("FuturaBankRecording");//Starting point of video recording
			//Scanner scn=new Scanner(System.in);
	
			log.info("This is testing");
			String jsonfiles=AppConfig.jsonfiles;
			String[] filepath=jsonfiles.split(",");
			for(int k=0;k<filepath.length;k++) {
			JSONParser jsonparse= new JSONParser();
			FileReader reader =new FileReader(filepath[k]);
			Object obj=jsonparse.parse(reader);
			JSONObject jsonobject=(JSONObject) obj;
			String Sheet=(String) jsonobject.get("Sheet");
			String Excelpath=(String) jsonobject.get("Excelpath");
			screenName=(String) jsonobject.get("Screenname");
			comm.setScreenDetails(screenName);
	
	
			String TestCaseID = null;
			String TestDescription=null;
		

					pram=new FilloReader().getTestDataInListMap(new File(Excelpath),Sheet, AppConfig.AllRecordQuery);
					
					for (Map<String, String> data : pram) {
						
						TestCaseID=data.get("TestCaseID");
						TestDescription=data.get("TestDescription");
						test=extent.createTest(Sheet+ "||" +TestCaseID+ "||" +TestDescription);
	
					
				try {
					String futuraobj=AppConfig.TestYouModule;
					
					String[] strArray= futuraobj.split(",");
					
					for(int i=0;i<strArray.length;i++)
					{
						JSONArray array=(JSONArray)jsonobject.get(strArray[i]);
						if(array!=null) {
						for(int j=0;j<array.size();j++) {
							JSONObject futurabank=(JSONObject)array.get(j);
					
							if((boolean)futurabank.get("action").equals("ScreenLaunch"))
						    {
								comm.launchScreen((String)futurabank.get("screenId"));
	       				    }
							
							
							if((boolean)futurabank.get("action").equals("SwitchEntity"))
	                        {
	                            if(data.get((String)futurabank.get("name")).equals("Yes")) {
	                            comm.switchtoEntity(data.get(futurabank.get("name")));
	                            }
	                         }
							
							if((boolean)futurabank.get("action").equals("Branch240"))
	                        {
	                            if(data.get((String)futurabank.get("name")).equals("Yes")) {
	                            comm.switchBranch240();
	                            }
	                         }
							
							if((boolean)futurabank.get("action").equals("HomeBranch"))
	                        {
	                            if(data.get((String)futurabank.get("name")).equals("Yes")) {
	                            comm.homeBranch();
	                            }
	                         }
														
							if((boolean)futurabank.get("action").equals("Mainscreen"))
						    {
								comm.Mainframes();
						    }
							
							if((boolean)futurabank.get("action").equals("branchFrame"))
						    {
								
								comm.branchFrame(); 
						    }
							
							if((boolean)futurabank.get("action").equals("switchframe1"))
						    {
								comm.switchFrame((String)futurabank.get("Locator"));
						    }
							
							 if((boolean)futurabank.get("action").equals("clickError"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									 Thread.sleep(2000); 
							        comm.clickbutton((String)futurabank.get("Locator"));
							        comm.errpopupclose();
								  }
							    }
							 
							 else if((boolean)futurabank.get("action").equals("switchframe"))

							 {
							 comm.swichtframe();
							 }
							 
							 else if((boolean)futurabank.get("action").equals("accountingEntries"))
							 {
								 if(data.get((String)futurabank.get("name")).equals("Yes")) {
									 comm.accountingEntries();
								 }
							 }
							 
							 else if((boolean)futurabank.get("action").equals("allMessages"))
							 {
								 if(data.get((String)futurabank.get("name")).equals("Yes")) {
									 comm.allMessages();
								 }
							 }
							 
							 else if((boolean)futurabank.get("action").equals("viewQueueLogs"))
							 {
							 comm.viewQueueLogs();
							 }
							 
							 else if((boolean)futurabank.get("action").equals("scroll"))
							 {
							        comm.mouseover((String)futurabank.get("Locator"));
							 }
							 
							 else if((boolean)futurabank.get("action").equals("scrollRight"))
							 {
							 comm.scrollRight((String)futurabank.get("Locator"));
							 }
							 
							 else if((boolean)futurabank.get("action").equals("scrollDown"))
							 {
							 comm.scrollDown((String)futurabank.get("Locator"));
							 }
							 
							 if((boolean)futurabank.get("action").equals("CloseRemark"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									 Thread.sleep(2000); 
							        comm.closeRemarkPopUp();
								  }
							    }
							 
							 else if((boolean)futurabank.get("action").equals("clickjs"))
								 
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  comm.clickbuttonjs((String)futurabank.get("Locator"));
								  }
							   }
							 
							 
							 else if((boolean)futurabank.get("action").equals("multiclick"))
								 
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  comm.mulitclick((String)futurabank.get("Locator"));
								  }
							   }
							 
							 else if((boolean)futurabank.get("action").equals("clear"))
								 
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  comm.clear((String)futurabank.get("Locator"));
								  }
							   }
							 
							 
							 else if((boolean)futurabank.get("action").equals("clickjswithError"))
								 
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(5000);
									  comm.clickbuttonjs((String)futurabank.get("Locator"));
									  comm.errpopupclose();
								  }
							   }
							 
							 else if((boolean)futurabank.get("action").equals("EnableOrDisable"))
								 
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  comm.enableordisable((String)futurabank.get("Locator"));
								  }
							   }
							 
							 else if((boolean)futurabank.get("action").equals("clickjs1"))
								 
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  comm.clickbuttonjs((String)futurabank.get("Locator"));
								  }
							   }
							 
							 
							 else if ((boolean) futurabank.get("action").equals("BranchSelect"))
							    {
								 Thread.sleep(2000);
							      comm.branchaccept(data.get(futurabank.get("name")),(String)futurabank.get("Locator"));				      
							    }
							 
							 else if((boolean)futurabank.get("action").equals("closeoverrideandinfo"))
								 
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  comm.closeOverrideandinfoPopUp();
								  }
							   }
							 
												 
							 else if((boolean)futurabank.get("action").equals("OverridePopUpClose"))
								 
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  comm.closeOverridePopUp();
								  }
							   }
							 
							 else if((boolean)futurabank.get("action").equals("Authorize"))
	                         {
	                           if(data.get((String)futurabank.get("name")).equals("Yes")) {
	                              Thread.sleep(2000);
	                             comm.Authorize();
	                           }
	                         }
							 
							 else if((boolean)futurabank.get("action").equals("doubleclick"))
								 
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  comm.clickbuttondoubleclick((String)futurabank.get("Locator"));
								  }
							   }
							 
							 else if ((boolean) futurabank.get("action").equals("wait"))
							    {
							      comm.waiting();				      
							    }	 
							 
							 else if((boolean)futurabank.get("action").equals("Exitscreen"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  comm.Exitscreen();
								  }
							   }
							 
							 
							 else if((boolean)futurabank.get("action").equals("Launch"))
								 
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  setup();
								  }
							   }
							 						 				 
							 else if ((boolean) futurabank.get("action").equals("sendkeys"))
							    {
								 Thread.sleep(2000);
							      comm.populateTextByXpath(data.get(futurabank.get("name")),(String)futurabank.get("Locator"));				      
							    }
							 
							 else if((boolean)futurabank.get("action").equals("LOVField"))
								 
							    {
								   comm.LOVFieldValue(data.get(futurabank.get("name")),(String)futurabank.get("Locator"),(String)futurabank.get("Locator1"),(String)futurabank.get("Locator2"),(String)futurabank.get("Locator3"));
							    }
							
							 else if ((boolean) futurabank.get("action").equals("dropdown"))
							    {
							      comm.selectDropdownByText(data.get(futurabank.get("name")),(String)futurabank.get("Locator"));				      
							    } 
							 
							 else if ((boolean) futurabank.get("action").equals("alertpopup"))
							    {
								 comm.checkUIFormatError();
							    } 
							 
							 
							 else if ((boolean) futurabank.get("action").equals("informationpopup"))
							    {
								 comm.closeInfoPopUp();
							    }
							 
							 
							 else if ((boolean) futurabank.get("action").equals("OpenChildScreen"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  comm.openChildScreenByTitle((String)futurabank.get("subScreenName"));
							    }
							   }
							 
							 else if ((boolean) futurabank.get("action").equals("OpenChildScreen_new"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  //after save record open the child tabs
									  comm.openChildScreenByTitle_new((String)futurabank.get("subScreenName"));
							    }
							   }
							 
							 else if ((boolean) futurabank.get("action").equals("Closingframes")) {
								 comm.closeframes();
							   }
							
							 
							 else if ((boolean) futurabank.get("action").equals("OpenChildScreen1"))
	                         {
	                           if(data.get((String)futurabank.get("name")).equals("Yes")) {
	
	                             comm.openChildScreenByTitleExit((String)futurabank.get("subScreenName"));
	                         }
	                        }
							 
							 else if ((boolean) futurabank.get("action").equals("CloseChildScreen"))
							    {
								 if(data.get((String)futurabank.get("name")).equals("Yes")) {
								 comm.closeChildScreen();
							    }
							   }
							 
							 else if ((boolean) futurabank.get("action").equals("CloseChildSubScreen"))
							    {
								 if(data.get((String)futurabank.get("name")).equals("Yes")) {
								 comm.closeChildSubscreen(screenName);
							    }
							   }
							 
							 else if ((boolean) futurabank.get("action").equals("selectflag"))
							    {
							      comm.selectFlag(data.get(futurabank.get("name")),(String)futurabank.get("Locator"));				      
							    }
							 
							 
							 else if ((boolean) futurabank.get("action").equals("radiobutton"))
							    {
							      comm.selectRadioBtn(data.get(futurabank.get("name")),(String)futurabank.get("Locator"));				      
							    }
							  
							  
							  else if((boolean)futurabank.get("action").equals("fileupload"))
							    {
								  comm.fileupload(data.get(futurabank.get("name")));						 
							    } 
								  
							  					
							  else if((boolean)futurabank.get("action").equals("tabout"))
							    {
							        comm.Tabout();
								}
							  
							  else if((boolean)futurabank.get("action").equals("fieldExitsOrNot"))
							    {
							    	if(data.get((String)futurabank.get("name")).equals("Yes")) {	  
								        comm.fieldExistsOrNot((String)futurabank.get("Locator"));
									 }
								 }
							  
						
							  else if((boolean)futurabank.get("action").equals("Negative"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  
							        comm.NegativeValidate((String)futurabank.get("Locator"),(String)futurabank.get("Locator1"));
								  }
							    }
							 
							  else if((boolean)futurabank.get("action").equals("closeoverrideandinfo123"))
		                            
	                          {
	                            if(data.get((String)futurabank.get("name")).equals("Yes")) {
	                                comm.closeOverridePopUp111();
	                            }
	                         }
							  
							  
							  else if((boolean)futurabank.get("action").equals("Positive"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									 
							        comm.PositiveValidate((String)futurabank.get("Locator"),(String)futurabank.get("Locator1"));							       								  
								  }
							   }
							  
							  
							  else if((boolean)futurabank.get("action").equals("signin"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {  
									  setup();
								  	}
							    }
							  
							  else  if((boolean)futurabank.get("action").equals("signout"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {									  
									  teardown();
								  }
							    }
							  
							  else if((boolean)futurabank.get("action").equals("gettext"))
							    {		
								  if(data.get("GetText").equals("Yes"))  {
								  
									    Refnumb = comm.gettextdata((String)futurabank.get("Locator"));
										Thread.sleep(5000);
									  	Fillo fillo=new Fillo();
										Connection connection=fillo.getConnection(Excelpath);
										String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name")+"="+"'"+Refnumb+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
										connection.executeUpdate(strQuery);
										connection.close();
								  }
								}
							 //Added By Sai
							  else if((boolean)futurabank.get("action").equals("PricingAmountValidate"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmount");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountValidate")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountValidate")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							  else if((boolean)futurabank.get("action").equals("ValidatePricingAmountFT_FX_COM"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountFT_FX_COM");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("ValidatePricingAmountFT_FX_COM")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("ValidatePricingAmountFT_FX_COM")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							   
							   
							  else if((boolean)futurabank.get("action").equals("ValidatePricingAmountFT_STD_CHG"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountFT_STD_CHG");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("ValidatePricingAmountFT_STD_CHG")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("ValidatePricingAmountFT_STD_CHG")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							  else if((boolean)futurabank.get("action").equals("ValidatePricingAmountFT_AA_OCM"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountFT_AA_OCM");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("ValidatePricingAmountFT_AA_OCM")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("ValidatePricingAmountFT_AA_OCM")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							  else if((boolean)futurabank.get("action").equals("PricingAmountSTD_CHG(PY)"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountSTD_CHG");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountSTD_CHG(PY)")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountSTD_CHG(PY)")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							  else if((boolean)futurabank.get("action").equals("PricingAmountDEBT(PY)"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountDEBT");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountDEBT(PY)")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountDEBT(PY)")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							  else if((boolean)futurabank.get("action").equals("PricingAmountDEBT(PYI)"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountDEBT");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountDEBT(PYI)")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountDEBT(PYI)")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							  else if((boolean)futurabank.get("action").equals("PricingAmountBEN_SHAR(PY)"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountBEN_SHAR");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountBEN_SHAR(PY)")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountBEN_SHAR(PY)")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							  else if((boolean)futurabank.get("action").equals("PricingAmountSTD_CHG(PZO)"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountSTD_CHG");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountSTD_CHG(PZO)")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountSTD_CHG(PZO)")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							  else if((boolean)futurabank.get("action").equals("PricingAmountSTD_CHG(PZI)"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountSTD_CHG");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountSTD_CHG(PZI)")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountSTD_CHG(PZI)")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							  else if((boolean)futurabank.get("action").equals("PricingAmountDD_OG_CHG"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountDD_OG_CHG");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountDD_OG_CHG")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountDD_OG_CHG")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							  else if((boolean)futurabank.get("action").equals("PricingAmountFT_FX_COM"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountFT_FX_COM");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountFT_FX_COM")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountFT_FX_COM")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							  else if((boolean)futurabank.get("action").equals("PricingAmountFT_SWF_CLS"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountFT_SWF_CLS");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountFT_SWF_CLS")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountFT_SWF_CLS")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }	 
							 
							  else if((boolean)futurabank.get("action").equals("PricingAmountFT_SDT_COM"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountFT_SDT_COM");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountFT_SDT_COM")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountFT_SDT_COM")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }	 
							 
							  else if((boolean)futurabank.get("action").equals("PricingAmountFT_BRN_O_C"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountFT_BRN_O_C");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountFT_BRN_O_C")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountFT_BRN_O_C")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							  else if((boolean)futurabank.get("action").equals("PricingAmountFT_STD_CHG"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountFT_STD_CHG");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountFT_STD_CHG")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountFT_STD_CHG")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							  else if((boolean)futurabank.get("action").equals("PricingAmountFT_BRN_T_C"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountFT_BRN_T_C");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountFT_BRN_T_C")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountFT_BRN_T_C")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }	
							 
							  else if((boolean)futurabank.get("action").equals("SHAPricingAmountFT_STD_CHG"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("SHAExpectedPricingAmountFT_STD_CHG");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("SHAPricingAmountFT_STD_CHG")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("SHAPricingAmountFT_STD_CHG")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							 
							  else if((boolean)futurabank.get("action").equals("SHAPricingAmountFT_BRN_T_C"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("SHAExpectedPricingAmountFT_BRN_T_C");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("SHAPricingAmountFT_BRN_T_C")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("SHAPricingAmountFT_BRN_T_C")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							  else if((boolean)futurabank.get("action").equals("BankPricingAmountFT_STD_CHG"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("BankExpectedPricingAmountFT_STD_CHG");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("BankPricingAmountFT_STD_CHG")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("BankPricingAmountFT_STD_CHG")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
						else if((boolean)futurabank.get("action").equals("PricingAmountFT_INCINT"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc =comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedPricingAmountFT_INCINT");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountFT_INCINT")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("PricingAmountFT_INCINT")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
						else if((boolean)futurabank.get("action").equals("PricingAmountFT_INC_CLS"))
					    {
						  if(data.get((String)futurabank.get("name")).equals("Yes")) {
							  Thread.sleep(3000);
							  String applc =comm.gettextdata((String)futurabank.get("Locator"));
							  System.out.println(applc);
							  String excellc= data.get("ExpectedPricingAmountFT_INC_CLS");
							 Fillo fillo=new Fillo();
							 Connection connection=fillo.getConnection(Excelpath);
							String strQuery="";
							 if(applc.equals(excellc))
							 {
								 if((boolean)futurabank.get("action").equals("PricingAmountFT_INC_CLS")) 
								 {
								  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
									connection.executeUpdate(strQuery);
									connection.close();
								 }
							 }
							 else
							 {
								 if((boolean)futurabank.get("action").equals("PricingAmountFT_INC_CLS")) {
								   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
									connection.executeUpdate(strQuery);
									connection.close();
								 }
							 }
						  }
					   }
							 
							  else if((boolean)futurabank.get("action").equals("BookingDateValidate"))
							    {
								  if(data.get((String)futurabank.get("name")).equals("Yes")) {
									  Thread.sleep(3000);
									  String applc=comm.gettextdata((String)futurabank.get("Locator"));
									  System.out.println(applc);
									  String excellc= data.get("ExpectedBookingDate");
									 Fillo fillo=new Fillo();
									 Connection connection=fillo.getConnection(Excelpath);
									String strQuery="";
										//connection.close();
									 if(applc.equals(excellc))
									 {
										 if((boolean)futurabank.get("action").equals("BookingDateValidate")) 
										 {
										  strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
									 else
									 {
										 if((boolean)futurabank.get("action").equals("BookingDateValidate")) {
										   strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name1")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
											connection.executeUpdate(strQuery);
											connection.close();
										 }
									 }
								  }
							   }
							 
							}
						}
					}
					
					pram=new FilloReader().getTestDataInListMap(new File(Excelpath),Sheet, AppConfig.AllRecordQuery);
					
					for (Map<String, String> data1 : pram) {
						
						//TestCaseID=data1.get("TestCaseID");
						//TestDescription=data1.get("TestDescription");
						//RTMID=data.get("RTMID");
						
						//test=extent.createTest(Sheet+ "||" +TestCaseID+ "||" +TestDescription);
						String futuraobj1=AppConfig.TestYouModule;
						
						String[] strArray1= futuraobj1.split(",");
						
						for(int i=0;i<strArray1.length;i++)
						{
							JSONArray array1=(JSONArray)jsonobject.get(strArray1[i]);
							if(array1!=null) {
							for(int j=0;j<array1.size();j++) {
						JSONObject futurabank=(JSONObject)array1.get(j);
						Fillo fillo=new Fillo();
						Connection connection=fillo.getConnection(Excelpath);
						//if(data1.get("PricingAmountFT_FX_COMStatus")!=null || data1.get("PricingAmountStatusFT_AA_OCM")!=null || data1.get("PricingAmountStatusFT_STD_CHG")!=null)
						//{
						//	System.out.println("Entered If Condition");
						if((boolean)futurabank.get("action").equals("ValidatePricingAmountFT_STD_CHG")) {
						
							if(data1.get("PricingAmountFT_FX_COMStatus").equals(statusp)
									||data1.get("PricingAmountStatusFT_AA_OCM").equals(statusp))
						    {		
								System.out.println("Entering into If Condition");
									String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
									connection.executeUpdate(strQuery);
									System.out.println("completed if Condition");
						    }
							else if ((data1.get("PricingAmountFT_FX_COMStatus").equals(statusp) && data1.get("PricingAmountStatusFT_STD_CHG").equals(statusp))){
								System.out.println("Entering into  else If Condition1");
								String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
								connection.executeUpdate(strQuery);
								System.out.println("completed else if Condition1");
							}
							
							else if ((data1.get("PricingAmountFT_FX_COMStatus").equals("") && data1.get("PricingAmountStatusFT_STD_CHG").equals("") && data1.get("PricingAmountStatusFT_AA_OCM").equals(""))){
								System.out.println("Entering into  else If Condition2");
								String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
								connection.executeUpdate(strQuery);
								System.out.println("completed else if Condition2");
							}
							
							else
							{
								System.out.println("Entering into else Condition");
								String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
								connection.executeUpdate(strQuery);
								System.out.println("completed else Condition");
							}
						}
						
						else if((boolean)futurabank.get("action").equals("PricingAmountSTD_CHG(PY)")) {
							
							if((data1.get("PricingAmountSTD_CHGStatus").equals(statusp) && data1.get("PricingAmountStatusDEBT").equals(statusp)))
						    {		
								System.out.println("Entering into If Condition");
									String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
									connection.executeUpdate(strQuery);
									System.out.println("completed if Condition");
						    }
							
							else if ((data1.get("PricingAmountSTD_CHGStatus").equals(statusp) && data1.get("PricingAmountStatusBEN_SHAR").equals(statusp))){
								System.out.println("Entering into  else If Condition2");
								String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
								connection.executeUpdate(strQuery);
								System.out.println("completed else if Condition2");
							}
							
							else
							{
								System.out.println("Entering into else Condition");
								String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
								connection.executeUpdate(strQuery);
								System.out.println("completed else Condition");
							}
						}
						
						else if((boolean)futurabank.get("action").equals("PricingAmountFT_FX_COM")) {
							
							if((data1.get("PricingAmountStatusFT_FX_COM").equals(statusp) && 
								data1.get("PricingAmountStatusFT_SWF_CLS").equals(statusp) && 
								data1.get("PricingAmountStatusFT_SDT_COM").equals(statusp) && 
								data1.get("PricingAmountStatusFT_BRN_O_C").equals(statusp) && 
								data1.get("PricingAmountStatusFT_STD_CHG").equals(statusp) && 
								data1.get("PricingAmountStatusFT_BRN_T_C").equals(statusp)) )
						    {		
								System.out.println("Entering into If Condition");
									String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
									connection.executeUpdate(strQuery);
									System.out.println("completed if Condition");
						    }
							
							else if((data1.get("PricingAmountStatusFT_FX_COM").equals(statusp) && 
									data1.get("PricingAmountStatusFT_SWF_CLS").equals(statusp) && 
									data1.get("PricingAmountStatusFT_SDT_COM").equals(statusp) && 
									data1.get("SHAPricingAmountStatusFT_STD_CHG").equals(statusp) && 
									data1.get("SHAPricingAmountStatusFT_BRN_T_C").equals(statusp)))
							    {		
									System.out.println("Entering into else If Condition");
										String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
										connection.executeUpdate(strQuery);
									System.out.println("completed else if Condition");
							    }
							else
							{
								System.out.println("Entering into else Condition");
								String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
								connection.executeUpdate(strQuery);
								System.out.println("completed else Condition");
							}
						}
						//BankPricingAmountFT_STD_CHG
						else if((boolean)futurabank.get("action").equals("BankPricingAmountFT_STD_CHG")) {
							if((data1.get("BankPricingAmountStatusFT_STD_CHG").equals(statusp)))
						    {		
								System.out.println("Entering into If Condition");
									String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
									connection.executeUpdate(strQuery);
									System.out.println("completed if Condition");
						    }
							else
							{
								System.out.println("Entering into else Condition");
								String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
								connection.executeUpdate(strQuery);
								System.out.println("completed else Condition");
							}
						}
						
					//	SHAPricingAmountFT_BRN_T_C
						
//						else if((boolean)futurabank.get("action").equals("SHAPricingAmountFT_BRN_T_C")) {
//									if((data1.get("PricingAmountStatusFT_FX_COM").equals(statusp) && 
//									data1.get("PricingAmountStatusFT_SWF_CLS").equals(statusp) && 
//									data1.get("PricingAmountStatusFT_SDT_COM").equals(statusp) && 
//									data1.get("SHAPricingAmountStatusFT_STD_CHG").equals(statusp) && 
//									data1.get("SHAPricingAmountStatusFT_BRN_T_C").equals(statusp)))
//							    {		
//									System.out.println("Entering into If Condition");
//										String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
//										connection.executeUpdate(strQuery);
//										System.out.println("completed if Condition");
//							    }
//							else
//							{
//								System.out.println("Entering into else Condition");
//								String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
//								connection.executeUpdate(strQuery);
//								System.out.println("completed else Condition");
//							}
//						}
						
						else if((boolean)futurabank.get("action").equals("PricingAmountDEBT(PYI)")) {
							
							if((data1.get("PricingAmountStatusDEBT").equals(statusp))||(data1.get("PricingAmountStatusBEN_SHAR").equals(statusp)))
						    {		
								System.out.println("Entering into If Condition");
									String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
									connection.executeUpdate(strQuery);
									System.out.println("completed if Condition");
						    }
							
//							else if ((data1.get("PricingAmountStatusBEN_SHAR").equals(statusp))){
//								System.out.println("Entering into  else If Condition2");
//								String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
//								connection.executeUpdate(strQuery);
//								System.out.println("completed else if Condition2");
//							}
							
							else
							{
								System.out.println("Entering into else Condition");
								String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
								connection.executeUpdate(strQuery);
								System.out.println("completed else Condition");
							}
						}
						
						if((boolean)futurabank.get("action").equals("PricingAmountSTD_CHG(PZO)")) {
							
							if (data1.get("PricingAmountSTD_CHGStatus").equals(statusp)){
								System.out.println("Entering into If Condition");
								String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
								connection.executeUpdate(strQuery);
								System.out.println("completed if Condition");
							}
							else if	((data1.get("PricingAmountDD_OG_CHGStatus").equals(statusp) && data1.get("PricingAmountSTD_CHGStatus").equals(statusp)))
						    {		
								System.out.println("Entering into else If Condition");
									String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
									connection.executeUpdate(strQuery);
									System.out.println("completed else if Condition");
						    }
							
							else
							{
								System.out.println("Entering into else Condition");
								String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
								connection.executeUpdate(strQuery);
								System.out.println("completed else Condition");
							}
						}
						
						else if((boolean)futurabank.get("action").equals("PricingAmountSTD_CHG(PZI)")) {
							if((data1.get("PricingAmountSTD_CHGStatus").equals(statusp)))
						    {		
								System.out.println("Entering into If Condition");
									String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
									connection.executeUpdate(strQuery);
									System.out.println("completed if Condition");
						    }
							else
							{
								System.out.println("Entering into else Condition");
								String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
								connection.executeUpdate(strQuery);
								System.out.println("completed else Condition");
							}
						}
						
						else if((boolean)futurabank.get("action").equals("PricingAmountFT_INC_CLS")) {
							if((data1.get("PricingAmountStatusFT_INC_CLS").equals(statusp)) || (data1.get("PricingAmountStatusFT_INCINT").equals(statusp)))
						    {		
								System.out.println("Entering into If Condition");
									String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusp+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
									connection.executeUpdate(strQuery);
									System.out.println("completed if Condition");
						    }
							
							else
							{
								System.out.println("Entering into else Condition");
								String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name2")+"="+"'"+statusf+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
								connection.executeUpdate(strQuery);
								System.out.println("completed else Condition");
							}
						}
						
						connection.close();
					}
				}
					}}
				
				passFailScreenshot(CommonFile.driver,"seleniumautomation");
				test.pass("Execution Pass");
				System.out.println("Try Test Case is Pass");
			    extent.flush();
	
				}
				
				
			
				catch(Exception e) {
					passFailScreenshot(CommonFile.driver,"seleniumautomation");
					test.fail("Execution failed");
					System.out.println("Catch Test Case is Fail");
				    extent.flush();
				    
				
				}
				continue;
	
			}
				pram=new FilloReader().getTestDataInListMap(new File(Excelpath),Sheet, AppConfig.AllRecordQuery);
										
					for (Map<String, String> data : pram) {
						
						TestCaseID=data.get("TestCaseID");
						
			if(data.get("AuthoriseRequired").equalsIgnoreCase("Yes")) {
							
						test=extent.createTest("Testing  bank Authorise flow" + Sheet+" "+TestCaseID);
						
					try {	
						
							JSONArray array1=(JSONArray)jsonobject.get("Authorise");
							if(array1!=null) {

							
							for(int m=0;m<array1.size();m++) {
								
								JSONObject futurabank=(JSONObject)array1.get(m);

					if((boolean)futurabank.get("action").equals("SecondUrAuthorise"))
					    {
						  if(data.get((String)futurabank.get("name")).equals("Yes")) {
							  Thread.sleep(3000);
							  comm.destroy();
							  Thread.sleep(3000);
							  comm.AuthoriseLogin((String)futurabank.get("Locator"),(String)futurabank.get("Locator1"));
						  }
					   }
					
					if((boolean)futurabank.get("action").equals("ScreenLaunch"))
				    {
						comm.launchScreen((String)futurabank.get("screenId"));
   				    }
					
				
					if((boolean)futurabank.get("action").equals("Mainscreen"))
				    {
						//comm.Mainframes1();//change for 9005 url
						comm.Mainframes(); //8008 url required
						
				    }
					
					
					
					 else if((boolean)futurabank.get("action").equals("clickjswithError"))
						 
					    {
						  if(data.get((String)futurabank.get("name")).equals("Yes")) {
							  Thread.sleep(3000);
							  comm.clickbuttonjs((String)futurabank.get("Locator"));
							  comm.errpopupclose();
						  }
					   }
					
					else if((boolean)futurabank.get("action").equals("clickjs"))
				    {
					  if(data.get((String)futurabank.get("name")).equals("Yes")) {
						  Thread.sleep(3000);
						  comm.clickbuttonjs((String)futurabank.get("Locator"));
					  }
				   }
					
					 else if ((boolean) futurabank.get("action").equals("sendkeys"))
					    {
						 Thread.sleep(2000);
					      comm.populateTextByXpath(data.get(futurabank.get("name")),(String)futurabank.get("Locator"));				      
					    }
					
					 else if ((boolean) futurabank.get("action").equals("sendkeys_new"))
					    {
						 Thread.sleep(2000);
					      comm.populateTextByXpath_new(data.get(futurabank.get("name")),(String)futurabank.get("Locator"));				      
					    }
					
					 else if((boolean)futurabank.get("action").equals("Authorize"))
                     {
                       if(data.get((String)futurabank.get("name")).equals("Yes")) {
                          Thread.sleep(2000);
                           comm.Authorize();
                       }
                     }
					
					 else if((boolean)futurabank.get("action").equals("Authorize1"))
                     {
                       if(data.get((String)futurabank.get("name")).equals("Yes")) {
                          Thread.sleep(4000);
                         if(data.get("Refnumber") != null)
                        	  	comm.Authorize1();

                       }
                     }
					
					 
					 else if((boolean)futurabank.get("action").equals("closeoverrideandinfo"))
						 
					    {
						  if(data.get((String)futurabank.get("name")).equals("Yes")) {
							  comm.closeOverrideandinfoPopUp();
						  }
					   }
					 
					
					 else if((boolean)futurabank.get("action").equals("Exitscreen"))
					    {
						  if(data.get((String)futurabank.get("name")).equals("Yes")) {
							  Thread.sleep(3000);
							  comm.Exitscreen();
						  }
					   }
					
					 else if ((boolean) futurabank.get("action").equals("wait"))
					    {
					      comm.waiting();				      
					    }
					 
					
					
					else if((boolean)futurabank.get("action").equals("Exitscreencancel"))
				    {
					  if(data.get((String)futurabank.get("name")).equals("Yes")) {
						  Thread.sleep(3000);
						  comm.clickBtnCancel();
					  }
				   }
						
						else if((boolean)futurabank.get("action").equals("signin"))
					    {
						  if(data.get((String)futurabank.get("name")).equals("Yes")) {  
							  setup();
					   }
					 } 
					  
					    else  if((boolean)futurabank.get("action").equals("signout"))
					    {
						  if(data.get((String)futurabank.get("name")).equals("Yes")) {							  
							  teardown();
						  }
					    }
												
						else if((boolean)futurabank.get("action").equals("gettext"))
							    {						    	
									HostRefnumb = comm.gettextdata((String)futurabank.get("Locator"));
										Thread.sleep(8000);
									  	Fillo fillo=new Fillo();
										Connection connection=fillo.getConnection(Excelpath);
										String strQuery="Update " +Sheet+ " Set "+(String)futurabank.get("name")+"="+"'"+HostRefnumb+"'"+" where TestCaseID="+"'"+TestCaseID+"'";
										connection.executeUpdate(strQuery);
										connection.close();
							    }
							}
						}
						
							passFailScreenshot(CommonFile.driver,"seleniumautomation");
							test.pass("Execution Pass");
							//comm.clickbuttonjs(AppConfig.HomePage);
						    extent.flush();

					}catch(Exception e) {
								
						passFailScreenshot(CommonFile.driver,"seleniumautomation");
						test.fail("Execution failed");
						//comm.clickbuttonjs(AppConfig.HomePage);
					    extent.flush();
					    
					}
					
					continue;
				}	
			}
		}		
	}

	@AfterTest
	public void teardown() throws Exception {
		//VideoRecorder_utlity.stopRecord();//End point of video recording
		comm.destroy();
		Thread.sleep(2000);
		CommonFile.driver.close();
		//CommonFile.driver.quit();
    	}
		
	}
