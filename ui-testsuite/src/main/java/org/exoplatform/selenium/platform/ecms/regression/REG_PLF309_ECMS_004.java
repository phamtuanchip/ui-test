package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestBase.actions;
import static org.exoplatform.selenium.TestBase.baseUrl;
import static org.exoplatform.selenium.TestBase.driver;
import static org.exoplatform.selenium.TestBase.initSeleniumTest;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sun.jna.platform.win32.Sspi.TimeStamp;

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
	  	By DRIVER_SITES_MANAGEMENT = By.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");
	  	By ELEMENT_CLOSE_BUTTON = By.linkText("Close");
	  
	  	info("=========Start REG_PLF309_ECMS_004==========");

			info("Go to Site Explorer");
			goToSiteExplorer();
			info("Choose  Sites Management");
			chooseDrive(DRIVER_SITES_MANAGEMENT);
			info("Go to acme document folder");
			goToNodeByPath(ACME_DOCUMENT_FOLDER);
			// Go to add new Content
			info("Add new content");
			goToAddNewContent();
			info("Choose podcast link");
			click(ELEMENT_PODCAST_LINK);
			info("Get the current date to compare with the published date field");
			SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy");
			info("========Verifie Current Date/Time is shown in Published field==========");
			String publisedDate = getValue(ELEMENT_PUBLISH_DATE).substring(0, 10);
			assert publisedDate.equalsIgnoreCase(simpleFormat.format(new Date()));
			info("Close this session");
			click(ELEMENT_CLOSE_BUTTON);
			info("Return to add new content");
			goToAddNewContent();
			info("Choose Sample node template");
			click(ELEMENT_SAMPLENODE_LINK);
			info("==========Check current date is show in datetime field==============");
		  String dateTimeTextField = getValue(ELEMENT_DATETIME).substring(0, 10);
			assert dateTimeTextField.equalsIgnoreCase(simpleFormat.format(new Date()));
			click(ELEMENT_CLOSE_BUTTON);
			info("=========End REG_PLF309_ECMS_004==========");
	  }
}
