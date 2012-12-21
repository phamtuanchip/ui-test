package org.exoplatform.selenium.platform.gatein.functional.managepages;

import static org.exoplatform.selenium.TestLogger.*;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;
import static org.exoplatform.selenium.gatein.PageEditor.*;
import static org.exoplatform.selenium.gatein.PageManagement.*;

/**
 * @author NhungVT, ThaoPTH
 * @date: 18/09/2012	
 */

public class GateIn_ManagePages_Create extends GateInBase 
{
	WebElement ELEMENT = null;
	//Define data
	public String INTRANET_LINK = "";
	public String PAGE_NAME = "";
	public String PAGE_TITLE = "";
	
	public String ADMINISTRATION_LINK = "//a[text()='Administration']";
	public String MANAGEMENT_LINK = "//a[text()='Management']";
	public String HOME_LINK = "//a[@title='Home']";
	public String EMPTY_LAYOUT = "//div[text()='Empty Layout']";
	
	//Messages
	public String BLANK_NODE_NAME_MESSAGE = "The field \"Node Name\" is required.";
	public String NODE_NAME_START_NUMBER_MESSAGE = "The \"Node Name\" field must start with a letter and must not contain special characters.\"";
	public String NODE_NAME_START_SPECIAL_MESSAGE = "Only alpha, digit, dash and underscore characters allowed for the field \"Node Name\".";
	public String PAGE_NAME_EXIST_MESSAGE = "This page name already exists.";
	
	@BeforeMethod()
	public void beforeTest()
	{
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		signIn("root", "gtn");
	}
	
	/*--------------- Manage Page - Create #1 ---------------*/
	
	//Check showing add new page form (Portal, Group)
	@Test()
	public void test01_CheckShowNewPageForm()
	{
		info("-START test01_CheckShowNewPageForm");
		
		//Check Showing on Portal's page
		
		//Goto Edit >  Page > Add Page of Portal
		goToAddPageEditor();
		
		//Verify Left panel 
		waitForElementPresent(HOME_LINK);
		
		//Verify Right panel
		waitForElementPresent(NODE_NAME_INPUT);
		
		//Click Abort button
		click(ELEMENT_ABORT_BUTTON);
		
		//Check Showing on Group's page
		
		//Goto Settings > Administration > Management
		goToApplicationRegistry();
		
		//Goto Edit >  Page > Add Page of Group
		goToAddPageGroupEditor();
		
		//Verify Left panel 
		waitForElementPresent(ELEMENT_APPLICATION_REGISTRY_CREATE);
		
		//Verify Right panel
		waitForElementPresent(NODE_NAME_INPUT);
		
		//Click Abort button
		click(ELEMENT_ABORT_BUTTON);
		
		info("-END test01_CheckShowNewPageForm");
	}
	
	//Check showing pages list of selected navigation at step 1 of Create page  form
	@Test()
	public void test06_CheckShowPageListOfSelectedNavigation()
	{
		info("-START test06_CheckShowPageListOfSelectedNavigation");
		
		//Check Showing on Group's page
		
		//Goto Settings > Administration > Management
		goToApplicationRegistry();
		
		goToAddPageGroupEditor();
		
		//verify list of pages in the left
		waitForElementPresent(ELEMENT_APPLICATION_REGISTRY_CREATE);
		
		
		waitForElementPresent(SERVICE_MANAGEMENT_LINK);
		waitForElementPresent(PAGE_MANAGER_LINK);
		waitForElementPresent(SITE_EXPORT_IMPORT_LINK);
		click(ELEMENT_APPLICATION_REGISTRY_CREATE);
		//Click on Up Level icon
		click(UP_LEVEL_ICON);
		
		//Verify Selected Page Node is Administration
		waitForElementPresent(ADMIN_LINK);
		click(UP_LEVEL_ICON);
		
		//Verify Selected Page Node is /default
				waitForElementPresent(DEFAULT_NODE);
		//Click Abort button
		click(ELEMENT_ABORT_BUTTON);
		
		//Check Showing on Portal's page
		
		//Goto My Sites > intranet > Home
		mouseOver(ELEMENT_SITE_MENU, true);
		pause(500);
		mouseOverAndClick(ELEMENT_CLASSIC_MENU);
		pause(500);
		
		//Goto Edit >  Page > Add Page of Portal
		goToAddPageEditor();
		
		//Click on Home
		waitForElementPresent(HOME_LINK);
		click(HOME_LINK);
		
		//Click on Up Level icon
		waitForElementPresent(UP_LEVEL_ICON);
		click(UP_LEVEL_ICON);
		
		//Verify Selected Page Node is /default
		waitForElementPresent(DEFAULT_NODE);
		
		//Click Abort button
		click(ELEMENT_ABORT_BUTTON);
		
		info("-END test06_CheckShowPageListOfSelectedNavigation");
	}
	
	//Complete step 1 in Create page  with valid value
	@Test()
	public void test07_CompleteStep1WithValidValue()
	{
		info("-START test07_CompleteStep1WithValidValue");
		
		//Goto Edit >  Page > Add Page of Portal
		goToAddPageEditor();
		
		//Verify Left panel 
		waitForElementPresent(HOME_LINK);
		
		//Verify Right panel
		waitForElementPresent(NODE_NAME_INPUT);
		
		//Input valid value for all fields at Step1
		type(NODE_NAME_INPUT, "Page1", true);
		
		//Click Next button
		next();
		
		//Verify Step 2
		waitForTextPresent("Select a Page Layout Template");
		waitForElementPresent(EMPTY_LAYOUT);
		
		//Click Abort button
		click(ELEMENT_ABORT_BUTTON);
		
		info("-END test07_CompleteStep1WithValidValue");
	}
	
	//Complete step 1 in Create page with blank Node Name
	@Test()
	public void test08_CompleteStep1WithBlankNodeName()
	{
		info("-START test08_CompleteStep1WithBlankNodeName");
		
		//Goto Edit >  Page > Add Page of Portal
		goToAddPageEditor();
		
		//Verify Left panel 
		waitForElementPresent(HOME_LINK);
		
		//Verify Right panel
		waitForElementPresent(NODE_NAME_INPUT);
		
		//Click Next button with Blank Node Name
		next();
		
		//Verify alert message
		waitForTextPresent(BLANK_NODE_NAME_MESSAGE);
		click(ELEMENT_OK_BUTTON);
		
		//Click Abort button
		click(ELEMENT_ABORT_BUTTON);
		
		info("-END test08_CompleteStep1WithBlankNodeName");
	}
	
	//Complete step 1 in Create page with Node Name start with number
	@Test()
	public void test09_CompleteStep1WithNodeNameStartNumber()
	{
		info("-START test09_CompleteStep1WithNodeNameStartNumber");
		
		//Goto Edit >  Page > Add Page of Portal
		goToAddPageEditor();
		
		//Verify Left panel 
		waitForElementPresent(HOME_LINK);
		
		//Verify Right panel
		waitForElementPresent(NODE_NAME_INPUT);
		
		//Input node name start with number at Step1
		type(NODE_NAME_INPUT, "1Page", true);
		
		//Click Next button
		next();
		
		//Verify Step 2
		waitForTextPresent(NODE_NAME_START_NUMBER_MESSAGE);
		click(ELEMENT_OK_BUTTON);
		
		//Click Abort button
		click(ELEMENT_ABORT_BUTTON);
		
		info("-END test09_CompleteStep1WithNodeNameStartNumber");
	}
	
	//Complete step 1 in Create page wizard with Node Name start with dot and underscore characters 
	@Test()
	public void test10_CompleteStep1WithNodeNameStartSpeacialChars()
	{		
		info("-START test10_CompleteStep1WithNodeNameStartSpeacialChars");
		
		//Goto Edit >  Page > Add Page of Portal
		goToAddPageEditor();
		
		//Verify Left panel 
		waitForElementPresent(HOME_LINK);
		
		//Verify Right panel
		waitForElementPresent(NODE_NAME_INPUT);
		
		//Input node name start with dash char at Step1
		type(NODE_NAME_INPUT, "-Page", true);
		
		//Click Next button
		next();
		
		//Verify Step 2
		waitForTextPresent(NODE_NAME_START_NUMBER_MESSAGE);
		click(ELEMENT_OK_BUTTON);
		
		//Reset Node Name value
		ELEMENT = waitForAndGetElement(By.xpath(NODE_NAME_INPUT));
		ELEMENT.clear();
		
		//Input node name start with dot char at Step1
		type(NODE_NAME_INPUT, ".Page", true);
		
		//Click Next button
		next();
		
		//Verify Step 2
		waitForTextPresent(NODE_NAME_START_SPECIAL_MESSAGE);
		click(ELEMENT_OK_BUTTON);
		
		//Click Abort button
		click(ELEMENT_ABORT_BUTTON);
		
		info("-END test10_CompleteStep1WithNodeNameStartSpeacialChars");
	}
	
	/*--------------- Manage Page - Create #2 ---------------*/
	
	@Test 
	/* FNC_GTN_POR_MNP_20_022
	 * Create portal page
	 * Create group page same name as portal page
	 */
	public void test22_CreateGroupPageSameNameWithPortalPage()
	{
		info("-START test22_CreateGroupPageSameNameWithPortalPage");
		
		goToManagePages();
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);
		
		PAGE_NAME = "FNC_GTN_POR_MNP_20_022";
		PAGE_TITLE = "FNC_GTN_POR_MNP_20_022";

		debug("Add new page for current portal");
		addNewPageAtManagePages(PageType.PORTAL, PAGE_NAME, PAGE_TITLE, true, null, "Platform/Administrators", "manager");
		debug("Verify new page is create successfully");
		waitForTextPresent(PAGE_NAME);

		debug("Add new page with same name for group");
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);
		addNewPageAtManagePages(PageType.GROUP, PAGE_NAME, PAGE_TITLE, true, null, "Platform/Administrators", "manager");
		waitForTextPresent(PAGE_NAME);

		//Delete data
		goToManagePages();
		deletePage(PageType.PORTAL, PAGE_NAME);
		waitForTextNotPresent(PAGE_NAME);
		deletePage(PageType.GROUP, PAGE_NAME);
		waitForTextNotPresent(PAGE_NAME);
		
		info("-END test22_CreateGroupPageSameNameWithPortalPage");
	}
	
	@Test
	/* FNC_GTN_POR_MNP_20_023
	 * Create page for group
	 */
	public void test23_CreatePageForGroup ()
	{
		info("-START test23_CreatePageForGroup");
		
		PAGE_NAME = "FNC_GTN_POR_MNP_20_023";
		PAGE_TITLE = "FNC_GTN_POR_MNP_20_023";
		
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put("Platform/Users", "manager");

		debug("Go to manage page");
		goToManagePages();

		debug("add new page");
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);
		addNewPageAtManagePages(PageType.GROUP, PAGE_NAME, PAGE_TITLE, false, permissions,"Platform/Administrators", "manager");
		waitForTextPresent(PAGE_NAME);

		//Delete data
		deletePage(PageType.GROUP, PAGE_NAME);
		waitForTextNotPresent(PAGE_NAME);
		
		info("-END test23_CreatePageForGroup");
	}
	
	@Test
	/* FNC_GTN_POR_MNP_20_024
	 * Create page for portal
	 */
	public void test24_CreatePortalPage () 
	{
		info("-START test24_CreatePortalPage");
		
		PAGE_NAME = "FNC_GTN_POR_MNP_20_024";
		PAGE_TITLE = "FNC_GTN_POR_MNP_20_024";

		debug("Go to manage page");
		goToManagePages();

		debug("add new page");
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);
		addNewPageAtManagePages(PageType.PORTAL, PAGE_NAME, PAGE_TITLE, true, null,"Platform/Administrators", "manager");
		waitForTextPresent(PAGE_NAME);

		//Deleta data
		debug("Delete page");
		deletePage(PageType.PORTAL, PAGE_NAME);
		waitForTextNotPresent(PAGE_NAME);
		
		info("-END test24_CreatePortalPage");
	}
	
	@Test
	/* FNC_GTN_POR_MNP_20_025
	 * Create page for portal
	 * Create page same name in one portal
	 */
	public void test25_CreatePagesSameNameInSamePortal () 
	{
		info("-START test25_CreatePagesSameNameInSamePortal");
		
		INTRANET_LINK = baseUrl+"/portal/intranet/";
		PAGE_NAME = "FNC_GTN_POR_MNP_20_025";
		PAGE_TITLE = "FNC_GTN_POR_MNP_20_025";


		debug("Go to manage page");
		goToManagePages();
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);

		debug("Add new page");
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);
		addNewPageAtManagePages(PageType.PORTAL, PAGE_NAME, PAGE_TITLE, true, null, "Platform/Users", "*");
		waitForTextPresent(PAGE_NAME);

		goToManagePages();

		click(ELEMENT_ADD_PAGE_BUTTON);

		waitForTextPresent("Page Setting");

		select(ELEMENT_SELECT_OWNER_TYPE, "portal");
		waitForElementPresent(ELEMENT_OWNER_ID_CLASSIC);

		type(ELEMENT_PAGE_NAME_INPUT, PAGE_NAME, true);
		type(ELEMENT_PAGE_TITLE_INPUT, PAGE_TITLE, true);
		save();

		waitForMessage(PAGE_NAME_EXIST_MESSAGE);
		click(ELEMENT_OK_BUTTON);
		click(ELEMENT_CANCEL_BUTTON);

		//Delete data
		goToManagePages();
		deletePage(PageType.PORTAL, PAGE_NAME);
		
		info("-END test25_CreatePagesSameNameInSamePortal");
	}
	
	@Test
	/* FNC_GTN_POR_MNP_20_028
	 * Create page for group
	 * Create portal page with name same as group page
	 */
	public void test28_CreatePortalPageSameNameAsGroupPage ()
	{
		info("-START test28_CreatePortalPageSameNameAsGroupPage");
		
		INTRANET_LINK = baseUrl+"/portal/intranet";
		PAGE_NAME = "FNC_GTN_POR_MNP_20_028";
		PAGE_TITLE = "FNC_GTN_POR_MNP_20_028";
		
		debug("Go to manage page");
		goToManagePages();
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);

		debug("Add new page for group");
		addNewPageAtManagePages(PageType.GROUP, PAGE_NAME, PAGE_TITLE, true, null, "Platform/Users", "*");
		waitForTextPresent(PAGE_NAME);
		
		debug("Add new page for portal");
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);
		addNewPageAtManagePages(PageType.PORTAL, PAGE_NAME, PAGE_TITLE, true, null, "Platform/Administrators", "manager");
		waitForTextPresent(PAGE_NAME);
		
		//Delete data
		deletePage(PageType.PORTAL, PAGE_NAME);
		waitForTextNotPresent(PAGE_NAME);
		deletePage(PageType.GROUP, PAGE_NAME);
		waitForTextNotPresent(PAGE_NAME);		
		
		info("-END test28_CreatePortalPageSameNameAsGroupPage");
	}
	
	@AfterMethod()
	public void afterTest()
	{
		driver.quit();
	}
}