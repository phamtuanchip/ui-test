package org.exoplatform.selenium.platform.gatein.functional.managepages;

import static org.exoplatform.selenium.TestLogger.debug;

/**
 * @author thaopth
 * @date: 18/09/2012
 */

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;
import static org.exoplatform.selenium.gatein.PageEditor.*;
import static org.exoplatform.selenium.gatein.PageManagement.*;
import static org.exoplatform.selenium.gatein.UserGroupManagement.*;

public class GateIn_ManagePages_Edit extends GateInBase {

	@BeforeMethod
	public void beforeMethods(){
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterTest(){
		signOut();
		driver.quit();
	}

	@Test 
	/* FNC_GTN_POR_MNP_22_019: Change edit right on portal page while editing portal page's properties
	 * Add new user
	 * Add user into group Platform/Administration with membership is "member"
	 * Create page for portal
	 * Change "edit permission" of page
	 * Login as user who belongs to group has edit permission
	 * Verify
	 */
	public void test19_ChangeEditRightOnPortalPage ()
	{
		String USER_NAME= "mimi";
		String PAGE_NAME = "FNC_GTN_POR_MNP_22_019";
		String PAGE_TITLE = "FNC_GTN_POR_MNP_22_019";

		signIn("root", "gtn");

		goToNewStaff();
		addNewUserAccount(USER_NAME, "123456", "123456", USER_NAME, "test", "mimi@exoplatform.com", USER_NAME, "English", true);
		//Go to user and group management page
		debug("Go to user and group management page");
		goToUsersAndGroupsManagement();

		//Select group tab
		debug("Select group tab");
		chooseGroupTab();

		//Select group
		debug("Select group");
		selectGroup("Platform");
		selectGroup("Administrators");

		//Add user to group
		debug("Add user to group");
		addUsersToGroup(USER_NAME, "member", false, true);

		debug("Go to manage page");
		goToManagePages();

		debug("page management");
		editPageAtManagePages(PageType.GROUP, "Page Management");

		//edit access right for page management
		waitForElementPresent(ELEMENT_VIEW_PAGE_PROPERTIES);
		click(ELEMENT_VIEW_PAGE_PROPERTIES);

		waitForElementPresent(ELEMENT_PERMISSION_SETTING_TAB);
		click(ELEMENT_PERMISSION_SETTING_TAB);

		debug("Set view permission for page management");
		setViewPermissions("Platform/Administrators", "*");


		//Change edit permission of page

		waitForElementPresent(ELEMENT_EDIT_PERMISSION_SETTING);
		click(ELEMENT_EDIT_PERMISSION_SETTING);

		debug("Set edit permission for page management");
		setEditPermissions("Platform/Administrators", "member");

		debug("Save edit page form");
		save();

		debug("click finish button");
		waitForElementPresent(ELEMENT_FINISH_ICON);
		click(ELEMENT_FINISH_ICON);

		//Add new page for portal
		debug("Add new page for portal");
		addNewPageAtManagePages(PageType.PORTAL, PAGE_NAME, PAGE_TITLE, true, null, "Platform/Administrators", "manager");
		waitForTextPresent(PAGE_NAME);

		debug("Edit new page");
		editPageAtManagePages(PageType.PORTAL, PAGE_TITLE);

		debug("Change edit permission of new page");
		waitForElementPresent(ELEMENT_VIEW_PAGE_PROPERTIES);
		click(ELEMENT_VIEW_PAGE_PROPERTIES);

		waitForElementPresent(ELEMENT_PERMISSION_SETTING_TAB);
		click(ELEMENT_PERMISSION_SETTING_TAB);

		waitForElementPresent(ELEMENT_EDIT_PERMISSION_SETTING);
		click(ELEMENT_EDIT_PERMISSION_SETTING);

		debug("Set edit permission for new page");
		setEditPermissions("Platform/Administrators", "member");

		save();
		waitForElementPresent(ELEMENT_FINISH_ICON);
		click(ELEMENT_FINISH_ICON);
		waitForElementNotPresent(ELEMENT_FINISH_ICON);
	

		signOut();

		//Login new user and view edit page form
		signIn(USER_NAME, "123456");
		goToManagePages();
//		mouseOver(ELEMENT_EDIT_PAGE_LINK, true);
//		mouseOver(ELEMEMT_PAGE_MENU, true);
//		mouseOverAndClick(ELEMENT_PAGE_LAYOUT);
		goToEditPageGroupEditor();
		waitForTextPresent("View Page properties");

		//Abort edit page form
		click(ELEMENT_ABORTEDIT_BUTTON);
		waitForElementNotPresent(ELEMENT_ABORTEDIT_BUTTON);
		signOut();

		signIn("root", "gtn");

		//Delete data
		goToUsersAndGroupsManagement();
		deleteUser(USER_NAME);
		goToManagePages();
		deletePage(PageType.PORTAL, PAGE_NAME);
		waitForTextNotPresent(PAGE_NAME);

	}

	@Test
	/* FNC_GTN_POR_MNP_22_021: Check change after edit page properties with finish
	 * Create new page
	 * Edit page properties: page title
	 * Verify new title of page
	 * Delete data
	 */
	public void test22_CheckChangesAfterEditPagePropertiesWithFinish () 
	{
		//Define data
		String PAGE_NAME = "FNC_GTN_POR_MNP_22_021";
		String PAGE_TITLE = "FNC_GTN_POR_MNP_22_021";

		signIn("root", "gtn");

		debug("Go to manage pages");
		goToManagePages();

		debug("Add new page");
		addNewPageAtManagePages(PageType.PORTAL, PAGE_NAME, PAGE_TITLE, true, null, "Platform/Administrators", "*");
		waitForTextPresent(PAGE_TITLE);

		debug("Edit page");
		editPageAtManagePages(PageType.PORTAL, PAGE_TITLE);

		//Go to view page properties
		debug("Click view page properties");
		waitForElementPresent(ELEMENT_VIEW_PAGE_PROPERTIES);
		click(ELEMENT_VIEW_PAGE_PROPERTIES);

		//Change page title
		debug("Change page title");
		type(ELEMENT_VIEWPAGE_PAGETITLE, "FNC_GTN_POR_MNP_22_021_test", true);

		debug("Save edit properties form");
		save();

		debug("click finish button");
		waitForElementPresent(ELEMENT_FINISH_ICON);
		click(ELEMENT_FINISH_ICON);

		//Open edit page form again and verify new page title
		editPageAtManagePages(PageType.PORTAL, "FNC_GTN_POR_MNP_22_021_test");

		debug("Click view page properties");
		waitForElementPresent(ELEMENT_VIEW_PAGE_PROPERTIES);
		click(ELEMENT_VIEW_PAGE_PROPERTIES);
		waitForElementPresent(By.xpath("//input[@id='title' and @value='FNC_GTN_POR_MNP_22_021_test']"));

		debug("Cancel and abort edit page form");
		click(ELEMENT_CANCEL_BUTTON);
		click(ELEMENT_ABORTEDIT_BUTTON);

		//Delete data
		deletePage(PageType.PORTAL, "FNC_GTN_POR_MNP_22_021_test");
		waitForTextNotPresent("FNC_GTN_POR_MNP_22_021_test");
	}
}