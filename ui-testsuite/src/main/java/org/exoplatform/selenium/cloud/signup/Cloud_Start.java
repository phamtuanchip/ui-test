package org.exoplatform.selenium.cloud.signup;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.cloud.CloudBase;
import org.exoplatform.selenium.platform.ManageAccount;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author vuna
 * @date Nov. 12th 2013
 */
public class Cloud_Start extends CloudBase{
	ManageAccount magAccount;
	
	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		initCloudUrl(0);
		driver.get(baseCloudUrl);
		driver.manage().window().maximize();
		magAccount = new ManageAccount(driver);
		info("== Starting to login to eXo Cloud ==");
	}

	@AfterMethod
	public void afterMethods() {
		info("== Login to eXo Cloud: successful ==");
		driver.quit();
	}
	
	@Test
	public void test01_Login2Cloud(){	
		startExoCloud();
		switchToNewWindow();
		magAccount.signIn("fqa-exo", ELEMENT_PASSWORD);
		Utils.pause(3000);
		info("Get started in 3 steps");
		/*click(ELEMENT_BUTTON_NEXT_FIRST_PAGE);
		waitForAndGetElement(ELEMENT_CHECKBOX_GETTING_STARTED);
		click(ELEMENT_BUTTON_NEXT_SECOND_PAGE);
		waitForAndGetElement(ELEMENT_INPUT_EMAIL_ADDRESS);
		click(ELEMENT_BUTTON_FINISH_THIRD_PAGE);
		waitForAndGetElement(ELEMENT_HOME_PAGE);*/
	}
}