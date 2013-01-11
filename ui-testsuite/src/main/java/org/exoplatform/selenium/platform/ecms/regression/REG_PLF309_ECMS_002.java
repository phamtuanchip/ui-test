package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestBase.actions;
import static org.exoplatform.selenium.TestBase.baseUrl;
import static org.exoplatform.selenium.TestBase.captureScreen;
import static org.exoplatform.selenium.TestBase.click;
import static org.exoplatform.selenium.TestBase.driver;
import static org.exoplatform.selenium.TestBase.initSeleniumTest;
import static org.exoplatform.selenium.TestBase.pause;
import static org.exoplatform.selenium.TestBase.type;
import static org.exoplatform.selenium.TestBase.waitForElementPresent;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.PageEditor.addContentListEmptyLayout;
import static org.exoplatform.selenium.platform.PageEditor.selectCLVPath;
import static org.exoplatform.selenium.platform.ecms.ActionBar.deletePermission;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.enableEditMode;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToNode;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToNodeByPath;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToOverView;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToPageCreationWinzard;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToSiteExplorer;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.loginEcms;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.addView_withName;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF309_ECMS_002 {
	
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
		 * Step1:
		 * - Open the Sites Explorer
		 * - Go to collaboration/sites content/live/acme/documents
		 * - Click on View Permission on action bar
		 * Step 2:
		 * - Remove all permissions and keep only permissions for *:/platform/administrators group
		 * Step 3
		 * - Open form to add new page
		 * - Drag and drop Content List portlet into this page
		 * - Click Edit icon of this portlet
		 * - Choose: By Folder mode
		 * - Point this CLV to display contents of folder collaboration/sites content/live/acme/documents
		 * - Save
		 * Step 4
		 * - Change to Edit mode
		 */
		
		@Test
		public void checkCLVdisplayed() {
			String COLLABORATION_SITECONTENT_ACME_DOCUMENT = "sites content/live/acme/documents";
			String CONTENT_LIST_PATH = "General Drives/collaboration/sites content/live/acme/";
			By ELEMENT_COLLABORATION_DRIVER = By.xpath("//a[@class='DriveLabel' and @title = 'collaboration']");
			By ELEMENT_ADMIN_VIEW = By.xpath("//a[@class='DefaultView e_admin-view ViewIcon' and @title = 'Admin View']");
			By ELEMENT_PERMISSION_TAB = By.xpath("//a[@class='TabLabel' and @title = 'Permission']");
			By ELEMENT_SAVEPAGE_BUTTON = By.xpath("//a[@class='EdittedSaveButton' and @title = 'Finish']");
			By ELEMENT_PERMISSION_LINK = By.linkText("Permissions");
			By ELEMENT_NEXT_LINK = By.linkText("Next");
			By ELEMENT_PAGE_NAME = By.xpath("//input[@id='pageName']");
			
			info("Start REG_PLF309_ECMS_002");
			
			// Add View Permission tab in Admin view
			info("Add View Permission tab in Admin view");
			addView_withName("Permission" ,"Admin View", "Add Tab", "viewPermissions");

			//goto Site Explorer
			info("Go to Site Explorer");
			goToSiteExplorer();
			
			// Go to collaboration/sites content/live/acme/documents
			info("Go to collaboration/sites content/live/acme/documents");
			chooseDrive(ELEMENT_COLLABORATION_DRIVER);
			goToNodeByPath(COLLABORATION_SITECONTENT_ACME_DOCUMENT);
			
			//Change to Admin View
			info("Change to Admin View");
			click(ELEMENT_ADMIN_VIEW);
			waitForElementPresent(ELEMENT_ADMIN_VIEW);
			//Change to Permission tab
			info("Change to Permission tab");
			click(ELEMENT_PERMISSION_TAB);
			// Click to View Permission on action bar
			info("Click to View Permission on action bar");
			goToNode(ELEMENT_PERMISSION_LINK);
			// Remove all permissions and keep only permissions for *:/platform/administrators group
			info(" Remove all permissions and keep only permissions for *:/platform/administrators group");
			deletePermission("*:/platform/web-contributors", true);
			deletePermission("any", true);
			
			goToOverView();
			//Go to EditPage Editor 
			goToPageCreationWinzard();
			
			type(ELEMENT_PAGE_NAME,"REG_PLF309_ECMS_002",false);
			click(ELEMENT_NEXT_LINK);
			pause(1000);
			click(ELEMENT_NEXT_LINK);
			pause(1000);
			//Add Content List portlet
			info("Add Content List portlet into page");
			addContentListEmptyLayout();
			//Select content path
			info("Select content path");
			selectCLVPath(CONTENT_LIST_PATH,"documents");
			pause(1000);
			click(ELEMENT_SAVEPAGE_BUTTON);
			captureScreen("REG_PLF309_ECMS_002_1");
			
			// Step 4: Change to edit mode
			enableEditMode(true);
			captureScreen("REG_PLF309_ECMS_005_2");
			
		}

}
