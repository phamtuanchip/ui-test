package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestBase.actions;
import static org.exoplatform.selenium.TestBase.baseUrl;
import static org.exoplatform.selenium.TestBase.driver;
import static org.exoplatform.selenium.TestBase.initSeleniumTest;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.enableEditMode;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.loginEcms;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToOverView;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF309_ECMS_011 {
	
	/*-- Data for these test cases --*/
  final public String DATA_USER = "john";
  final public String DATA_PASS = "gtn";
  
  
	 @BeforeMethod
	  public void beforeMethods(){
	    initSeleniumTest();
	    driver.get(baseUrl);
	    actions = new Actions(driver);
	    info("Login ECMS with " + DATA_USER);
	    loginEcms(DATA_USER, DATA_PASS);
	  }

	  @AfterMethod
	  public void afterMethods(){
	    info("Logout ECMS");
	    driver.quit();
	    actions = null;
	  }
	  
	  /**
	   * Step 1:
	   * - Log in
	   * - Go to acme homepage
	   * - Change to Edit mode
	   * - Click Edit icon of Acme SignIn portlet
	   * Step 2:
	   * - Add some characters in content
	   * - Save and close
	   * - Click on Publish in action bar
	   * Step 3:
	   * - Click on Back icon
	   * EXPECTED:
	   * Step 1: Form to edit content appear
	   * Step 2: The content is published
	   * Step 3: The new content is shown.
	   */
	  
	  @Test
		public void check_SignInPortlet_after_Edit() {
	  	
	  	//Starting 
	  	info("Starting .....");
	  	//Go to Acme home page
	  	goToOverView();
	  	//Change to Edit mode
	  	enableEditMode(true);
	  	//edit content of SignIn Portlet
	  }

}
