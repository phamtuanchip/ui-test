package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestBase.click;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.NavigationToolbar.goToEditPageEditor;
import static org.exoplatform.selenium.platform.PlatformBase.ELEMENT_SAVE_BUTTON;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.createNewArticle;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.goToManageDriver;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.selectCheckboxList;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.SiteExplorer;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF307_ECMS_006 extends EcmsBase{
	public static final String DATA_USER = "root";
	public static final String DATA_PASS = "gtn";
	public static By ELEMENT_RELATION_TAB = By.linkText("Relation");
	public static By ELEMENT_GENERAL_DRIVER = By.xpath("//a[contains(text(),'General Drives')]");
	public static By ELEMENT_SITE_MANAGEMENT=By.xpath("//a[contains(text(),'Sites Management')]");
	//publication form
	public static By ELEMENT_PUBLIC_STATUS = By.xpath("//a[contains(text(), 'Published')]");
	public static By ELMENET_CURRENT_STATUS = By.xpath("//a[@class='CurrentStatus']");
	public static By ELEMENT_CURRENT_PUBLIC_STATUS = By.xpath("//a[@class='CurrentStatus' and contains(text(), 'Published')]");
	public static By ELEMENT_PUBLICATION_TAB_LINK= By.xpath("//a[contains(text(),'Publication')]");

	
	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with "+DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() throws Exception {
		info("Logout ECMS");
		logoutEcms();
		driver.manage().deleteAllCookies();
		driver.quit();
		actions = null;
	}
	/*
	 * Goto Content Explorer
	 * Create new article
	 * Save as drafs
	 * Manage relation
	 * Add related link
	 * Publication
	 * Publish this article
	 * Create new Page
	 * Choose article as content
	 * Click on the link
	 * Expected: The related links are not clickable : code source displayed and if there is no related documents, label link is still displayed
	 */
	@Test
	public void test_displayContentPortlet(){
		String fileName="REG_PLF307_ECMS_006";
		String ELEMENT_ARTICLE_RELATION_TITLE="REG_PLF307_ECMS_006_RELATION";
		String ELEMENT_ATICLE_SUMMARY="contents";
		By ELEMENT_CREATED = By.xpath("//div[@title='"+fileName+"']");
		By ELEMENT_RELATIONS_LINK = By.linkText("Relations");
		By ELEMENT_COLLABORATION_DRIVER = By.xpath("//a[@class='DriveLabel' and @title = 'collaboration']");
		By ELEMENT_SITE_MANAGEMENT_DRIVER = By.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");
		By ELEMENT_SELECT_RELATION_TAB =By.xpath("//div[text()='Select Relation']");
		By ELEMENT_SELECT_RELATION=By.xpath("//div[@class='Select16x16Icon' and contains(@style,'margin-left:15px;')]");
		By ELEMENT_ADMIN_VIEW = By.xpath("//a[@class='DefaultView e_admin-view ViewIcon' and @title = 'Admin View']");
		By ELEMENT_WCM_VIEW = By.xpath("//a[@class='DefaultView wcm-view ViewIcon' and @title = 'WCM View']");
		By ELEMENT_CLOSE_BUTTON = By.linkText("Close");
		String driveName = "Managed Sites";
		By ELEMENT_APPLY_VIEW_TAB = By.xpath("//div[contains(text(),'Apply Views')]");
		By locator = By.xpath("//div[@title='" + driveName + "']/../..//*[@class='Edit16x16Icon']");
		Map<String, String> ELEMENT_PORTLET_ID = new HashMap<String, String>();
		ELEMENT_PORTLET_ID.put("Content/ContentListViewerPortlet","");
		String namePage="REG_PLF307_ECMS_006_PAGE";
		String pathContent = "General Drives/Sites Management";

		info("Go to Site Explorer");
		goToSiteExplorer();
		info("Go to Site Manage Driver");
		chooseDrive(ELEMENT_SITE_MANAGEMENT_DRIVER);
		info("Add new content");
		goToAddNewContent();
		info("Create an article");
		createNewArticle(fileName, fileName, ELEMENT_ATICLE_SUMMARY, ELEMENT_ATICLE_SUMMARY);
		pause(1000);
		info("Choose Collaboration driver");
		chooseDrive(ELEMENT_COLLABORATION_DRIVER);
		info("Add new content");
		goToAddNewContent();
		info("Create an article");
		createNewArticle(ELEMENT_ARTICLE_RELATION_TITLE, ELEMENT_ARTICLE_RELATION_TITLE, ELEMENT_ATICLE_SUMMARY, ELEMENT_ATICLE_SUMMARY);
		pause(1000);
		info("STARTING ......Add Admin view to Sites Managed Drive");
	  info("Go to Manage Driver");
  	goToManageDriver();
  	info("Click to Edit button of Managed Sites");
  	click(locator);
    info("Choose applied view: Admin View");
  	click(ELEMENT_APPLY_VIEW_TAB);
  	selectCheckboxList("e_admin-view");
  	click(ELEMENT_SAVE_BUTTON);
  	info("END add ADMIN VIEW");
  	
  	info("Go to Site Explorer");
		goToSiteExplorer();
		info("Go to Site Manage Drive");
		chooseDrive(ELEMENT_SITE_MANAGEMENT_DRIVER);
		pause(1000);
		info("Open the created article");
		doubleClickOnElement(ELEMENT_CREATED);
		pause(3000);
		info("Change to Admin View");
		click(ELEMENT_ADMIN_VIEW);
		info("Click to Relations link");
		click(ELEMENT_RELATIONS_LINK);
		pause(1000);
		click(ELEMENT_SELECT_RELATION_TAB);
		pause(1000);
		info("Cloose add link for document");
		click(ELEMENT_SELECT_RELATION);
		click(ELEMENT_CLOSE_BUTTON);
		pause(1000);
		info("Change to WCM View");
		click(ELEMENT_WCM_VIEW);
		info("Open the created article");
		doubleClickOnElement(ELEMENT_CREATED);
		info("Publish document");
		SiteExplorer.publishDocument();
		info("Go to ACME Home Page");
		goToOverView();
		info("Go to Add Page Editor with Editor");
		PageEditor.goToPageEditor_EmptyLayout(namePage);
		info("Create CLV page");
		PageEditor.addContentListEmptyLayout();
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);
		pause(1000);
		info("Edit CLV page");
		goToEditPageEditor();
		info("Select CLV path");
		PageEditor.selectCLVPath(pathContent,"REG_PLF307_ECMS_006","content");
		pause(1000);
		info("Finish edit page");
		click(ELEMENT_PAGE_EDIT_FINISH);
		captureScreen("REG_PLF307_ECMS_006");
	}
}
