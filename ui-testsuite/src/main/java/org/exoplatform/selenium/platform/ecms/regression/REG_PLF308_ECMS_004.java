package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestBase.actions;
import static org.exoplatform.selenium.TestBase.baseUrl;
import static org.exoplatform.selenium.TestBase.doubleClickOnElement;
import static org.exoplatform.selenium.TestBase.driver;
import static org.exoplatform.selenium.TestBase.initSeleniumTest;
import static org.exoplatform.selenium.TestBase.isElementNotPresent;
import static org.exoplatform.selenium.TestBase.pause;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.uploadFile;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToNodeByPath;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToSiteExplorer;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.loginEcms;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF308_ECMS_004 {
	
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
	   * - Login and go to content explorer, choose a drive
	   * - Upload an image
	   * - Open this image to preview
	   * EXPECTED RESULT:
	   * No action upload on action bar
	   */
	  @Test
	  public void checkUploadedImageOverwritten(){
	  	String fileName = "REG_PLF308_ECMS_004.png";
	  	By DRIVER_SITES_MANAGEMENT = By.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");
	  	String ACME_DOCUMENT_FOLDER = "acme/documents";
	  	By ELEMENT_UPLOADED = By.xpath("//div[@title='"+fileName+"']");
	  	
	  	info("Starting REG_PLF308_ECMS_004");
	  	
			info("Go to Site Explorer");
			goToSiteExplorer();
			
			info("Choose Management Sites");
			chooseDrive(DRIVER_SITES_MANAGEMENT);
			
			info("Go to acme document folder");
			goToNodeByPath(ACME_DOCUMENT_FOLDER);
			
			info("Upload image file");
			uploadFile(fileName,"TestData/"+fileName);
			pause(1000);
			
			info("Open the uploaded image file");
			doubleClickOnElement(ELEMENT_UPLOADED);
			
			info("No action upload on action bar");
			assert isElementNotPresent("Upload");
	  }
}
