package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestBase.actions;
import static org.exoplatform.selenium.TestBase.baseUrl;
import static org.exoplatform.selenium.TestBase.captureScreen;
import static org.exoplatform.selenium.TestBase.click;
import static org.exoplatform.selenium.TestBase.driver;
import static org.exoplatform.selenium.TestBase.initSeleniumTest;
import static org.exoplatform.selenium.TestBase.type;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToNodeByPath;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToSiteExplorer;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.loginEcms;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF309_ECMS_005 {
	
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
	  **
	   * REG_PLF309_ECMS_005: Add new webcontent with java script
	   * @Steps:
	   * - Go to Content Explorer
	   * - Open form to create new content
	   * - Choose: Free layout webcontent
	   * - Fill title and name
	   * - Select Advanced Search tab
	   * - Fill the javascript field with : alert("test");
	   * - Save and close
	   * @expected
	   * - the webContent is displayed and the javascript is executed
	   * 
	   */
		@Test
		public void testContentDisplayed_JSexecuted() {
		  By FREE_WEBCONTENT = By.linkText("Free layout webcontent");
		  String ACME_DOCUMENT_FOLDER = "acme/documents";
		  By ELEMENT_WEBCONTENT_TITLE_TEXTBOX = By.id("title");	
			By ELEMENT_WEBCONTENT_NAME_TEXTBOX = By.id("name");
			By ELEMENT_WEBCONTENT_JS_TEXTAREA = By.xpath("//textarea[contains(@id,'ContentJS')]");
			By ELEMENT_WEBCONTENT_ADVANCE_TAB = By.xpath("//div[@class='MiddleTab' and contains(text(),'Advanced')]");
			By DRIVER_SITES_MANAGEMENT = By.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");
			By ELEMENT_SAVE_CLOSE_BUTTON = By.linkText("Save & Close");
			String title_name ="REG_PLF309_ECMS_005";
			info("=======Start REG_PLF309_ECMS_005========");

			info("Go to Site Explorer");
			goToSiteExplorer();
			info("Choose  Sites Management");
			chooseDrive(DRIVER_SITES_MANAGEMENT);

			info("Go to acme document folder");
			goToNodeByPath(ACME_DOCUMENT_FOLDER);

			info ("Create free webcontent file");
			goToAddNewContent();
			click(FREE_WEBCONTENT);
			
			// Fill informations
			info ("Fill information in Free Layout Webcontent Form");
			type(ELEMENT_WEBCONTENT_TITLE_TEXTBOX,title_name,false);
			type(ELEMENT_WEBCONTENT_NAME_TEXTBOX, title_name, true);
			
			// Choose Advanced tab
			info ("Choose Advanced tab");
			click(ELEMENT_WEBCONTENT_ADVANCE_TAB);
			
			// Fill JS data
			info ("Fill JS data in Advanced tab");
			type(ELEMENT_WEBCONTENT_JS_TEXTAREA,"alert(&quot;test&quot;);",false);
			
			// Save & Close
			info("Click to Save & Close");
			click(ELEMENT_SAVE_CLOSE_BUTTON);
			
			// Verify the js is called with an alert
			info("Verify the js is called with an alert");
			captureScreen("REG_PLF309_ECMS_005");
			info("=======End REG_PLF309_ECMS_005========");
		}
}
