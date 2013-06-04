package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestBase.actions;
import static org.exoplatform.selenium.TestBase.baseUrl;
import static org.exoplatform.selenium.TestBase.captureScreen;
import static org.exoplatform.selenium.TestBase.click;
import static org.exoplatform.selenium.TestBase.driver;
import static org.exoplatform.selenium.TestBase.initSeleniumTest;
import static org.exoplatform.selenium.TestBase.mouseOver;
import static org.exoplatform.selenium.TestBase.pause;
import static org.exoplatform.selenium.TestBase.type;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.PageEditor.addContentDetailEmptyLayout;
import static org.exoplatform.selenium.platform.UserGroupManagement.selectGroup;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.createNewFile;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.enableEditMode;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToNodeByPath;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToOverView;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToPageCreationWinzard;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToSiteExplorer;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.loginEcms;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF308_ECMS_001 {
	
	/*-- Data for these test cases --*/
  final public String DATA_USER = "root";
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
	   * - Login as root
	   * - Go to Sites Explorer/Sites Management/acme/documents
	   * - Add new document
	   * - Go to acme home page add a new SCV page
	   * - Browse added document to be content of SCV, then save
	   * - Select SCV page
	   * - Change to edit mode
	   * - Click edit icon to edit the document (inside SCV)
	   * - Click Request Approval or Publish
	   * EXPECTED: 
	   * - New document is created successfully
	   * - SCV is created with added document successfully
	   * - Document is opened in edit mode
	   * - Document is sent to request approval or published, and can be view in Published mode in SCV and no exception
	   */
	  @Test
		public void check_FastPublishContent_Displayed() {
	  	By DRIVER_SITES_MANAGEMENT = By.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");
	  	String ACME_DOCUMENT_FOLDER = "acme/documents";
	  	String name_content_title="REG_PLF308_ECMS_001";
	  	By ELEMENT_CLOSE_BUTTON = By.linkText("Close");
	  	By ELEMENT_FAST_PUBLICH_ICON = By.xpath("//a[@class='FastPublishIcon' and @title = 'Publish']");
	  	By ELEMENT_PAGE_NAME = By.xpath("//input[@id='pageName']");
	  	By ELEMENT_NEXT_LINK = By.linkText("Next");
	  	By ELEMENT_SAVEPAGE_BUTTON = By.xpath("//a[@class='EdittedSaveButton' and @title = 'Finish']");
	  	By ELEMENT_FRAME_CONTAIN_PORTLET = By.xpath("//div[contains(@id,'UIPortlet')]");
	  	By ELEMENT_EDIT_PORTLET_ICON = By.xpath("//a[@title='Edit Portlet']");
	  	By ELEMENT_SELECT_CONTENT_PATH_LINK = By.xpath("//img[@class='AddIcon16x16 SelectFolderPathIcon']");
	  	String path = "General Drives/Sites Management/acme/documents";
	  	By ELEMENT_SELECT_CLV_PATH = By.xpath("//td/a[text()='" + name_content_title + "']");
	  	By ELEMENT_SAVE_BUTTON = By.linkText("Save");
	  	
			info("Go to Site Explorer");
			goToSiteExplorer();
			
			info("Choose Management Sites");
			chooseDrive(DRIVER_SITES_MANAGEMENT);
			 // Go to document of acme file
			info("Go to acme document folder: acme/documents");
			goToNodeByPath(ACME_DOCUMENT_FOLDER);
		  info("Go to add new Content");
			goToAddNewContent();
			info("Create new content with File template");
			createNewFile(name_content_title,name_content_title,name_content_title);
			info("Go to Acme home page");
	  	goToOverView();
	  	info("Create new page");
			goToPageCreationWinzard();
			type(ELEMENT_PAGE_NAME,"REG_PLF308_ECMS_001",false);
			click(ELEMENT_NEXT_LINK);
			pause(1000);
			click(ELEMENT_NEXT_LINK);
			pause(1000);
			info("Add Content List portlet into page");
			addContentDetailEmptyLayout();
			info("click to edit button on Content Detail Portlet");
			mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET, true);
			click(ELEMENT_EDIT_PORTLET_ICON);
			click(ELEMENT_SELECT_CONTENT_PATH_LINK);
			info("Select content path");
			selectGroup(path);
			info("Click to the created document");
			click(ELEMENT_SELECT_CLV_PATH);
			click(ELEMENT_SAVE_BUTTON);
			click(ELEMENT_CLOSE_BUTTON);
			
			pause(1000);
			click(ELEMENT_SAVEPAGE_BUTTON);
			
		  //Change to Edit mode
	  	enableEditMode(true);
	  	pause(3000);
	  	
	  	//Fast public content
	  	click(ELEMENT_FAST_PUBLICH_ICON);
	  	info("Document is sent to request approval or published, and can be view in Published mode in SCV and no exception");
	  	captureScreen("REG_PLF308_ECMS_001");
	  	
	  }
	  
}
