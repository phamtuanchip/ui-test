package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestBase.actions;
import static org.exoplatform.selenium.TestBase.baseUrl;
import static org.exoplatform.selenium.TestBase.click;
import static org.exoplatform.selenium.TestBase.driver;
import static org.exoplatform.selenium.TestBase.initSeleniumTest;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.createNewFile;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.uploadFile;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToNodeByPath;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToSiteExplorer;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.loginEcms;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.selenesedriver.FindElement;
import org.openqa.selenium.internal.selenesedriver.FindElements;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF309_ECMS_006 {
	
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
	   * Step 1: Create new File content
	   * - Go to Content Explorer
	   * - Open form to create new content
	   * - Choose: File
	   * - Put values in required field
	   * - Save
	   * Step 2:
	   * - Select created file
	   * Step 3
	   * - Go to Content Explorer
	   * - Upload new file
	   * Step 4
	   * - Select uploaded file
	   * EXPECTED: 
	   * File is created successfully
	   * In action bar: there is no action: Add Folder, Add Content, Upload file, Import Node
	   */
	  
	  @Test
		public void Filter_actions_ntfile() {
	  	By DRIVER_SITES_MANAGEMENT = By.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");
	  	String ACME_DOCUMENT_FOLDER = "acme/documents";
	  	String name_content_title="REG_PLF309_ECMS_006";
	  	By ELEMENT_NEW_CONTENT_LINK = By.linkText("New Content");
	  	By ELEMENT_NEW_FOLDER_LINK = By.linkText("New Folder");
	  	By ELEMENT_UPLOAD_LINK = By.linkText("Upload");
	  	String fileName = "REG_PLF309_ECMS_006_PDF.pdf";
	  	By ELEMENT_UPLOADED = By.xpath("//div[contains(@title='fileName']");
	  	
	  	
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
			// Create new content with File template
			createNewFile(name_content_title,name_content_title,name_content_title);
			
			// Verify: In the action bar: there is no action: Add Folder, Add Content, Upload file, Import Node
			Assert.assertFalse(driver.findElement(ELEMENT_NEW_CONTENT_LINK).isDisplayed());
			Assert.assertFalse(driver.findElement(ELEMENT_NEW_FOLDER_LINK).isDisplayed());
			Assert.assertFalse(driver.findElement(ELEMENT_UPLOAD_LINK).isDisplayed());
			
		  // Go to document of acme file
			info("Go to acme document folder");
			goToNodeByPath(ACME_DOCUMENT_FOLDER);
			// Upload PDF file
			uploadFile(fileName,"TestData/"+fileName);
			// Open uploade file
			click(ELEMENT_UPLOADED);
		  // Verify: In the action bar: there is no action: Add Folder, Add Content, Upload file, Import Node
			Assert.assertFalse(driver.findElement(ELEMENT_NEW_CONTENT_LINK).isDisplayed());
			Assert.assertFalse(driver.findElement(ELEMENT_NEW_FOLDER_LINK).isDisplayed());
			Assert.assertFalse(driver.findElement(ELEMENT_UPLOAD_LINK).isDisplayed());
					
			
			
			
	  }

}
