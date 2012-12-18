package org.exoplatform.selenium.platform.gatein.functional.group.administration;

import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.ManageApplications.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;
import static org.exoplatform.selenium.gatein.PageEditor.*;
import static org.exoplatform.selenium.gatein.UserGroupManagement.*;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GateIn_Group_Administration_ApplicationRegistry_EditCategory extends GateInBase
{
	//Define Data
	public String CATE_NAME_01 = "PLT_APPR_05_02_CATE1";
	public String CATE_DISP_NAME_01 = "PLT_APPR_05_02_CATE1";
	public String CATE_NAME_02 = "PLT_APPR_05_02_CATE2";
	public String CATE_DISP_NAME_02 = "PLT_APPR_05_02_CATE2";
	public String APP_ALL_PEOPLE = "All People Portlet";
	public String APP_ANSWER = "Answers Portlet";

	public String DATA_USER_NAME_01 = "user10";
	public String DATA_USER_NAME_02 = "user03";
	public String DATA_PASSWORD = "123456";
	public String DATA_FIRST_NAME = "FirstName";
	public String DATA_LAST_NAME = "LastName";
	public String DATA_EMAIL03 = DATA_USER_NAME_01+"@exoplatform.com";
	public String DATA_EMAIL04 = DATA_USER_NAME_02+"@exoplatform.com";
	public String DATA_LANGUAGE = "English";
	public String PLATFORM_ADMIN_DATA_GROUP = "Platform/Administration";
	public String MANAGER_DATA_MEMBER_SHIP = "manager";

	@BeforeMethod()
	public void beforeTest()
	{
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
		signIn("john", "gtn");
	}

	//Change access right on category from public to be limited by group(s)
	@Test()
	public void test03_ChangeAccessRightFromPublicToLimited()
	{	
		//Add new user belongs to group Plaform/Administrator with membership is member 
		goToNewStaff();
		addNewUserAccount(DATA_USER_NAME_01, DATA_PASSWORD, DATA_PASSWORD, DATA_FIRST_NAME, DATA_LAST_NAME, DATA_EMAIL03, DATA_FIRST_NAME, DATA_LANGUAGE, true);

		//Go to user and group management page
		goToUsersAndGroupsManagement();

		//Select group tab
		chooseGroupTab();

		//Select group
		selectGroup(PLATFORM_ADMIN_DATA_GROUP);
		//		selectGroup("Administration");

		//Add user to group
		addUsersToGroup(DATA_USER_NAME_01, "member", false, true);

		//Go to Application Registry page > setting access right for page is public
		goToApplicationRegistry();
		waitForElementPresent(ELEMENT_CATEGORIES_AREA_TITLE);
		goToEditPageEditor();
		waitForElementPresent(ELEMENT_VIEW_PAGE_PROPERTIES);
		click(ELEMENT_VIEW_PAGE_PROPERTIES);
		waitForElementPresent(ELEMENT_PERMISSION_SETTING_TAB);
		click(ELEMENT_PERMISSION_SETTING_TAB);
		waitForElementPresent(ELEMENT_CHECKBOX_PUBLIC_MODE);
		//		check(ELEMENT_CHECKBOX_PUBLIC_MODE);
		makeItPublic(true);
		save();

		//Setting access right for portlet is public
		mouseOver(ELEMENT_PORTLET_CONTAINER, false);
		click(ELEMENT_EDIT_PORTLET_ICON);
		waitForElementPresent(ELEMENT_ACCESS_PERMISSION_TAB);
		click(ELEMENT_ACCESS_PERMISSION_TAB);
		waitForElementPresent(ELEMENT_CHECKBOX_PUBLIC_MODE);
		makeItPublic(true);
		waitForElementPresent(ELEMENT_SAVE_CLOSE_BUTTON);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
		click(ELEMENT_FINISH_ICON);

		//Add new category
		waitForElementPresent(ELEMENT_CATEGORIES_AREA_TITLE);
		addNewCategoryAtManageApplications(CATE_NAME_01, CATE_DISP_NAME_01, "", true, null, true);

		//Add Apps into added Category
		addApplicationToCategory(CATE_NAME_01, false, "All People", "Portlet", APP_ALL_PEOPLE, true, null, null);
		addApplicationToCategory(CATE_NAME_01, false, "Q&A", "Portlet", APP_ANSWER, true, null, null);

		//Edit permission of added Category from Public --> Limited 	
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put(PLATFORM_ADMIN_DATA_GROUP, MANAGER_DATA_MEMBER_SHIP);
		editCategoryAtManageApplications(CATE_NAME_01, CATE_DISP_NAME_01, "", false, permissions, false);

		//Sign out and Sign in as added user
		waitForTextPresent("John Smith");
		signOut();
		driver.get(baseUrl);
		signIn(DATA_USER_NAME_01, DATA_PASSWORD);

		//Go to Application Registry page 
		goToApplicationRegistry();

		//Verify added user can't see above edited Category 
		waitForElementNotPresent(CATE_NAME_01);

		//Sign out and Sign in as root
		signOut();
		driver.get(baseUrl);
		signIn("root", "gtn");

		//Go to Application Registry page 
		goToApplicationRegistry();

		//Reset default data

		waitForElementPresent(ELEMENT_CATEGORIES_AREA_TITLE);
		goToEditPageEditor();
		waitForElementPresent(ELEMENT_VIEW_PAGE_PROPERTIES);
		click(ELEMENT_VIEW_PAGE_PROPERTIES);
		waitForElementPresent(ELEMENT_PERMISSION_SETTING_TAB);
		click(ELEMENT_PERMISSION_SETTING_TAB);
		waitForElementPresent(ELEMENT_CHECKBOX_PUBLIC_MODE);
		uncheck(ELEMENT_CHECKBOX_PUBLIC_MODE);
		waitForElementPresent(ELEMENT_ADD_PERMISSION_BUTTON);
		//		click(ELEMENT_ADD_PERMISSION_BUTTON);
		setViewPermissions(PLATFORM_ADMIN_DATA_GROUP, "*");
		save();
		pause(500);
		click(ELEMENT_FINISH_ICON);

		//Delete data
		goToApplicationRegistry();
		deleteCategoryAtManageApplications(CATE_NAME_01, true);

		//Delete user
		goToUsersAndGroupsManagement();
		deleteUser(DATA_USER_NAME_01);
	}

	//Change access right on category from limited by group(s) to be public
	@Test()
	public void test04_ChangeAccessRightFromLimitedToPublic()
	{
		By bName02= By.xpath("//a[contains(@title,'"+CATE_NAME_02+"')]");
		//Add new user belongs to group Plaform/Administrator with membership is member 
		goToNewStaff();
		addNewUserAccount(DATA_USER_NAME_02, DATA_PASSWORD, DATA_PASSWORD, DATA_FIRST_NAME, DATA_LAST_NAME, DATA_EMAIL04, DATA_FIRST_NAME, DATA_LANGUAGE, true);

		//Go to user and group management page
		goToUsersAndGroupsManagement();

		//Select group tab
		chooseGroupTab();

		//Select group
		selectGroup(PLATFORM_ADMIN_DATA_GROUP);

		//Add user to group
		addUsersToGroup(DATA_USER_NAME_02, "manager", false, true);

		//Go to Application Registry page > setting access right user
		goToApplicationRegistry();
		waitForElementPresent(ELEMENT_CATEGORIES_AREA_TITLE);
		goToEditPageEditor();
		waitForElementPresent(ELEMENT_VIEW_PAGE_PROPERTIES);
		click(ELEMENT_VIEW_PAGE_PROPERTIES);
		waitForElementPresent(ELEMENT_PERMISSION_SETTING_TAB);
		click(ELEMENT_PERMISSION_SETTING_TAB);
		waitForElementPresent(ELEMENT_CHECKBOX_PUBLIC_MODE);
		makeItPublic(false);
		setViewPermissions(PLATFORM_ADMIN_DATA_GROUP, MANAGER_DATA_MEMBER_SHIP);
		save();
		click(ELEMENT_FINISH_ICON);
		pause(500);
		//Go to Application Registry page 
		//		goToApplicationRegistry();

		//Add new category
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put("Organization", "*");
		addNewCategoryAtManageApplications(CATE_NAME_02, CATE_DISP_NAME_02, "", false, permissions, true);

		//Sign out and Sign in as added user
		signOut();
		driver.get(baseUrl);
		signIn(DATA_USER_NAME_02, DATA_PASSWORD);
		//Go to Application Registry page check no Catetory CATE_NAME_02
		goToApplicationRegistry();
		waitForElementPresent(ELEMENT_CATEGORIES_AREA_TITLE);
		goToEditPageEditor();
		waitForElementNotPresent(bName02);
		click(ELEMENT_FINISH_ICON);
		pause(500);
		//Sign out and Sign in as root user
		signOut();
		driver.get(baseUrl);
		signIn("john", "gtn");
		goToApplicationRegistry();
		//Edit permission of added Category from Limited --> public
		editCategoryAtManageApplications(CATE_NAME_02, CATE_DISP_NAME_02, "", true, null, false);

		//Sign out and Sign in as added user to check can see category
		signOut();
		driver.get(baseUrl);
		signIn(DATA_USER_NAME_02, DATA_PASSWORD);
		//Verify added user can see above edited Category 
		goToApplicationRegistry();
		waitForElementPresent(ELEMENT_CATEGORIES_AREA_TITLE);
		goToEditPageEditor();
		waitForElementPresent(bName02);
		click(ELEMENT_CLOSE_WINDOWS_BUTTON);

		//Log in as root to edit category
		//Sign out and Sign in as root
		signOut();
		driver.get(baseUrl);
		signIn("root", "gtn");

		//Go to Application Registry page 
		goToApplicationRegistry();

		//Verify root can see above edited Category 
		waitForElementPresent(bName02);

		//Delete data
		deleteCategoryAtManageApplications(CATE_NAME_02, true);

		//Delete user
		goToUsersAndGroupsManagement();
		deleteUser(DATA_USER_NAME_02);
	}

	@AfterMethod()
	public void afterTest()
	{
		driver.quit();
	}
}