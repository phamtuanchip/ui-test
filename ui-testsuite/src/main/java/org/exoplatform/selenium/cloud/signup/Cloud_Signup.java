package org.exoplatform.selenium.cloud.signup;


import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.cloud.CloudBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author vuna
 * @date Nov. 12th, 2013
 * <li>This class is to activate an Yopmail account for eXo Cloud test</li>
 * 
 */
public class Cloud_Signup extends CloudBase{
	
	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		//initCloudUrl(0);
		//driver.get(baseCloudUrl);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		info("== Starting to sign-up to eXo Cloud ==");
	}

	@AfterMethod
	public void afterMethods() {
		info("== Sign-up and login to eXo Cloud: successful ==");
		driver.quit();
	}
	
	@Test
	public void test00_signup(){	
		enterEmail2Signup(YOPMAIL_ADDRESS);
		initYopmailAccountTest(0);
	}
}