package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestBase.actions;
import static org.exoplatform.selenium.TestBase.baseUrl;
import static org.exoplatform.selenium.TestBase.driver;
import static org.exoplatform.selenium.TestBase.initSeleniumTest;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToNodeByPath;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToSiteExplorer;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.loginEcms;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.seleniumemulation.IsElementPresent;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF309_ECMS_009 {
	
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
	   * CASE: Check thumbnail view of PDF file
	   * Step 1:
	   * - Go to Content Administration → Manage drive- Add simple view for Site Management drive
	   * Step 2:
	   * - Go to Content Explorer
	   * - Upload new PFD file
	   * Step 3:
	   * - Change to simple view
	   */
	  @Test
		public void CheckThumbnailViewPDF_file() {
	  	String driveName = "Managed Sites";
	  	By locator = By.xpath("//div[@class='ActionContainer' and contains(text(),'" + driveName + "')]/ancestor::tr//img[@class='Edit16x16Icon']");
	  	By ELEMENT_APPLY_VIEW_TAB = By.xpath("//div[contains(text(),'Apply Views')]");
	  	By ELEMENT_SIMPLE_VIEW = By.xpath("//a[@class='DefaultView simple-view ViewIcon' and @title = 'Simple View']");
	  	By DRIVER_SITES_MANAGEMENT = By.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");
	  	String view = "Simple View";
	  	String ACME_DOCUMENT_FOLDER = "acme/documents";
	  	String fileName = "REG_PLF309_ECMS_009_PDF_file.pdf";
	  	
	  	info("Start REG_PLF309_ECMS_009");
	  	
	  	//Go to Manage Driver
	  	goToManageDriver();
	  	//Click to Edit button of Managed Sites
	  	click(locator);
	    //Choose applied view
	  	click(ELEMENT_APPLY_VIEW_TAB);
	  	selectCheckboxList(view);
	  	click(ELEMENT_SAVE_BUTTON);
	  	
	    //goto Site Explorer
			info("Go to Site Explorer");
			goToSiteExplorer();
			
			//Choose Management Sites
			chooseDrive(DRIVER_SITES_MANAGEMENT);
			
		  // Go to document of acme file
			info("Go to acme document folder");
			goToNodeByPath(ACME_DOCUMENT_FOLDER);
		  // Upload PDF file
			uploadFile(fileName,"TestData/"+fileName);
			
			// Change to simple View
			click(ELEMENT_SIMPLE_VIEW);
	  	
	    assert isTextNotPresent(fileName);
	  	
	  }

}
