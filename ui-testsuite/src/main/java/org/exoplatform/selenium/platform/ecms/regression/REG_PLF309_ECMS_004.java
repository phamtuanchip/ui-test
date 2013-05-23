package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestBase.actions;
import static org.exoplatform.selenium.TestBase.baseUrl;
import static org.exoplatform.selenium.TestBase.driver;
import static org.exoplatform.selenium.TestBase.initSeleniumTest;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.*;

import java.util.Date;


import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF309_ECMS_004 {
	
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
	   * CASE: Check default value of Calendar field 
	   * Step 1:
	   * - Go to Content Explorer
	   * - Select 1 drive
	   * - Click Add Content in action bar to add new content
	   * EXPECTED:Form to add new content is shown
	   * Step 2:
	   * - Choose template: Podcast
	   * EXPECTED:Current Date/Time is shown in Published field
	   * - Step 3:
	   * - Choose template: Sample node
	   * EXPECTED: 
	   * Current Date/Time is shown in Date/Date time field
	   */
	  @Test
		public void CheckDefaultValueCalendar_field() {
	  	String ACME_DOCUMENT_FOLDER = "acme/documents";
	  	By ELEMENT_PODCAST_LINK = By.linkText("Podcast");
	  	By ELEMENT_SAMPLENODE_LINK =By.linkText("Sample node");
	  	By ELEMENT_PUBLISH_DATE = By.xpath("//input[contains(@id,'publishedDate')]");
	  	By ELEMENT_DATETIME = By.xpath("//input[contains(@id,'datetime')]");
	  	By ELEMENT_CLOSE_BUTTON = By.linkText("Close");
	  
	  	info("Start REG_PLF309_ECMS_004");

			//goto Site Explorer
			info("Go to Site Explorer");
			goToSiteExplorer();
			
		  // Go to document of acme file
			info("Go to acme document folder");
			goToNodeByPath(ACME_DOCUMENT_FOLDER);
			
			// Go to add new Content
			goToAddNewContent();
			click(ELEMENT_PODCAST_LINK);
			
			// Verifie Current Date/Time is shown in Published field
			Date date = new Date();
			assert isElementPresent(ELEMENT_PUBLISH_DATE):date;
			// Close this session
			click(ELEMENT_CLOSE_BUTTON);
			
		  // Return to  add new Content
			goToAddNewContent();
			click(ELEMENT_SAMPLENODE_LINK);
			assert isElementPresent(ELEMENT_DATETIME):date;
			click(ELEMENT_CLOSE_BUTTON);
	  }

}
