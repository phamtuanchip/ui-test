package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestBase.actions;
import static org.exoplatform.selenium.TestBase.baseUrl;
import static org.exoplatform.selenium.TestBase.captureScreen;
import static org.exoplatform.selenium.TestBase.driver;
import static org.exoplatform.selenium.TestBase.click;
import static org.exoplatform.selenium.TestBase.initSeleniumTest;
import static org.exoplatform.selenium.TestBase.waitForElementPresent;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.PageEditor.selectContentPath;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.createNewFile;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.enableEditMode;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToNodeByPath;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToOverView;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToSiteExplorer;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.loginEcms;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToPageCreationWinzard;
import static org.exoplatform.selenium.TestBase.dragAndDropToObject;
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
	  	String name_content_title="REG_PLF309_ECMS_006";
	  	By ELEMENT_MENU_CONTENT_LINK = By.linkText("Content");
	  	By ELEMENT_ADD_SINGLE_CONTENT_DETAIL_PORTLET = By.xpath("//div[text()='Content Detail']");
	  	By ELEMENT_DROP_TARGET_HAS_LAYOUT = By.xpath("//div[@class='UIRowContainer EmptyContainer']");
	  	By ELEMENT_ICON_EDIT_PORTLET = By.xpath("//a[@class='EditIcon' and @title = 'Edit Portlet']");
	  	By ELEMENT_EDIT_LAYOUT_FINISH_BUTTON = By.xpath("//div[@id='UIPortalComposer']//a[@class='EdittedSaveButton']");
	  	By ELEMENT_CLOSE_BUTTON = By.linkText("Close");
	  	By ELEMENT_FAST_PUBLICH_ICON = By.xpath("//a[@class='FastPublishIcon' and @title = 'Publish']");
	  	
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
		  //Go to Acme home page
	  	goToOverView();
	    //Go to EditPage Editor 
			goToPageCreationWinzard();
			//Add Content List portlet
			click(ELEMENT_MENU_CONTENT_LINK);
			dragAndDropToObject(ELEMENT_ADD_SINGLE_CONTENT_DETAIL_PORTLET,ELEMENT_DROP_TARGET_HAS_LAYOUT);
			click(ELEMENT_ICON_EDIT_PORTLET);
			waitForElementPresent(ELEMENT_ICON_EDIT_PORTLET);
			selectContentPath(ACME_DOCUMENT_FOLDER);
			click(ELEMENT_CLOSE_BUTTON);
			click(ELEMENT_EDIT_LAYOUT_FINISH_BUTTON);
			
		  //Change to Edit mode
	  	enableEditMode(true);
	  	//Fast public content
	  	click(ELEMENT_FAST_PUBLICH_ICON);
	  	info("Document is sent to request approval or published, and can be view in Published mode in SCV and no exception");
	  	captureScreen("REG_PLF308_ECMS_001");
	  	
	  }
	  
}
