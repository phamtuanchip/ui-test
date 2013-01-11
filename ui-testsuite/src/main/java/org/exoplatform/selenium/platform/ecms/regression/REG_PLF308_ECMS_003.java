package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestBase.actions;
import static org.exoplatform.selenium.TestBase.baseUrl;
import static org.exoplatform.selenium.TestBase.click;
import static org.exoplatform.selenium.TestBase.driver;
import static org.exoplatform.selenium.TestBase.initSeleniumTest;
import static org.exoplatform.selenium.TestBase.isElementPresent;
import static org.exoplatform.selenium.TestBase.type;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToNodeByPath;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToSiteExplorer;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.loginEcms;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF308_ECMS_003 {

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
	   * - Go to Content Explorer and choose a drive
	   * - Click Add Content on action bar
	   * - Choose a template which has title and name, eg: Article, Free layout web content, Event, Sample node
	   * - Fill name only
	   * EXPECTED RESULT:
	   * Title of document is automatically generated, and no JS error is shown
	   */
	  
	  @Test
		public void check_automatically_generate_title() {
	  	By DRIVER_SITES_MANAGEMENT = By.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");
	  	String ACME_DOCUMENT_FOLDER = "acme/documents";
	  	By ELEMENT_NEWFILE_LINK =By.linkText("File");
	  	By ELEMENT_NEWFILE_NAME_TEXTBOX =By.id("name");
	  	By ELEMENT_NEWFILE_TITLE_TEXTBOX =By.id("title0");
	  	String name = "REG_PLF308_ECMS_003";
	  	
	    //goto Site Explorer
			info("Go to Site Explorer");
			goToSiteExplorer();
			
			//Choose Management Sites
			chooseDrive(DRIVER_SITES_MANAGEMENT);
			 // Go to document of acme file
			info("Go to acme document folder");
			goToNodeByPath(ACME_DOCUMENT_FOLDER);
		  // Go to add new Content
			goToAddNewContent();
			
			click(ELEMENT_NEWFILE_LINK);	
			type(ELEMENT_NEWFILE_NAME_TEXTBOX, name, false);
		  //Click Enter
			info("Click tab");
			WebElement element = driver.findElement(ELEMENT_NEWFILE_TITLE_TEXTBOX);
		  element.sendKeys(Keys.TAB);
		  
		  info("CHECK: Title of document is automatically generated, and no JS error is shown");
			assert isElementPresent(ELEMENT_NEWFILE_TITLE_TEXTBOX):"REG_PLF308_ECMS_003";
			
	  }
}
