package org.exoplatform.selenium.platform.forum.functional.forum.category;

import static org.exoplatform.selenium.TestLogger.info;






import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @author khanhnt
 * Done 7/15 test cases
 */
public class Forum_Forum_Category_Add extends ForumBase {

	ManageAccount acc;
	ForumManageCategory fmCat;
	ForumManageForum fmForum;

	@BeforeMethod
	public void setUpBeforeTest() {
		initSeleniumTest();
		driver.get(baseUrl);
		fmCat = new ForumManageCategory(driver);
		acc = new ManageAccount(driver);
		acc.signIn(DATA_USER_JOHN, DATA_PASS);
		button = new Button(driver);
		fmForum = new ForumManageForum(driver);
	}

	@AfterMethod
	public void afterTest() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:72920. Test Case Name: Add a new category in case invalid data
	 * entry for the Restricted audience field. Created by khanhnt at 2013/11/20
	 * 09:34:03
	 */
	@Test
	public void test01_AddANewCategoryInCaseInvalidDataEntryForTheRestrictedAudienceField() {
		info("Test 1: Add a new category in case invalid data entry for the Restricted audience field");
		String catName = "test 01 Category name";
		String order = "0";
		int chooseRestricted = 1;
		String[] restricted = { "invaliduser" };
		String description = "Description";
		int setPermission = 0;
		String[] userGroup = null;
		boolean permission = false;

		goToForums();
		fmCat.goToAddCategory();
		fmCat.inputDataCategoryInForum(catName, order, chooseRestricted,
				restricted, description, setPermission, userGroup, permission);
		button.save();

		waitForMessage(fmCat.MESSAGE_RESTRICTED_AUDIENCE_INVALID+restricted[0]+".");
		click(ELEMENT_OK_INFOR_POPUP);

	}

	/**
	 * Case ID:73040. Test Case Name: Add a new category in case valid group
	 * entry for the Restricted audience field. Created by khanhnt at 2013/11/20
	 * 09:34:03
	 */
	@Test
	public void test02_AddANewCategoryInCaseValidGroupEntryForTheRestrictedAudienceField() {
		info("Test 2: Add a new category in case valid group entry for the Restricted audience field");

		String catName = "test 02 Category name";
		String order = "0";
		int chooseRestricted = 1;
		String[] restricted = { "/developers" };
		String description = "Description";
		int setPermission = 0;
		String[] userGroup = null;
		boolean permission = false;

		goToForums();
		fmCat.addNewCategoryInForum(catName, order, chooseRestricted,
				restricted, description, setPermission, userGroup, permission);

		// determine if john who is a member of development group can view the
		// new category
		goToForumHome();
		waitForAndGetElement(By.linkText(catName));

		// determine if mary who is not a of development group can view the new
		// category or not
		fmCat.checkRightOfViewCategory(DATA_USER_MARY, DATA_PASS, catName, description, false);

		acc.signOut();
		acc.signIn(DATA_USER_JOHN, DATA_PASS);
		goToForums();
		waitForAndGetElement(By.linkText(catName)).click();
		waitForAndGetElement(fmCat.ELEMENT_MANAGE_CATEGORY);
		fmCat.deleteCategoryInForum(catName);

	}

	/**
	 * Case ID:73005. Test Case Name: Add a new category in case valid role
	 * entry for the Restricted audience field. Created by khanhnt at 2013/11/20
	 * 09:34:03
	 */
	@Test
	public void test03_AddANewCategoryInCaseValidRoleEntryForTheRestrictedAudienceField() {
		info("Test 3: Add a new category in case valid role entry for the Restricted audience field");

		String catName = "test 03 Category name";
		String order = "0";
		int chooseRestricted = 1;
		String[] restricted = { "author:/developers" };
		String description = "Description";
		int setPermission = 0;
		String[] userGroup = null;
		boolean permission = false;

		goToForums();
		fmCat.addNewCategoryInForum(catName, order, chooseRestricted,
				restricted, description, setPermission, userGroup, permission);

		// determine if john who is a member of development group can view the
		// new category
		goToForumHome();

		waitForAndGetElement(By.linkText(catName));

		// determine if mary who is not a of development group can view the new
		// category or not
		fmCat.checkRightOfViewCategory(DATA_USER_MARY, DATA_PASS, catName, description, false);

		acc.signOut();
		acc.signIn(DATA_USER_JOHN, DATA_PASS);
		goToForums();
		waitForAndGetElement(By.linkText(catName)).click();
		waitForAndGetElement(fmCat.ELEMENT_MANAGE_CATEGORY);
		fmCat.deleteCategoryInForum(catName);
	}

	/**
	 * Case ID:72965. Test Case Name: Add a new category in case valid user
	 * entry for the Restricted audience field. Created by khanhnt at 2013/11/20
	 * 09:34:03
	 */
	@Test
	public void test04_AddANewCategoryInCaseValidUserEntryForTheRestrictedAudienceField() {
		info("Test 4: Add a new category in case valid user entry for the Restricted audience field");
		String catName = "test 04 Category name";
		String order = "0";
		int chooseRestricted = 1;
		String[] restricted = { "john" };
		String description = "Description";
		int setPermission = 0;
		String[] userGroup = null;
		boolean permission = false;

		goToForums();
		fmCat.addNewCategoryInForum(catName, order, chooseRestricted,
				restricted, description, setPermission, userGroup, permission);

		// determine if john who is a member of development group can view the
		// new category
		goToForumHome();

		waitForAndGetElement(By.linkText(catName));

		// determine if mary who is not a of development group can view the new
		// category or not
		fmCat.checkRightOfViewCategory(DATA_USER_MARY, DATA_PASS, catName, description, false);

		acc.signOut();
		acc.signIn(DATA_USER_JOHN, DATA_PASS);
		goToForums();
		waitForAndGetElement(By.linkText(catName)).click();
		waitForAndGetElement(fmCat.ELEMENT_MANAGE_CATEGORY);
		fmCat.deleteCategoryInForum(catName);
	}

	/**
	 * Case ID:73075. Test Case Name: Add a new category in case valid user,
	 * role and group entry for Restricted audience field at once. Created by
	 * khanhnt at 2013/11/20 09:34:03
	 */
	@Test
	public void test05_AddANewCategoryInCaseValidUserRoleAndGroupEntryForRestrictedAudienceFieldAtOnce() {
		info("Test 5: Add a new category in case valid user, role and group entry for Restricted audience field at once");

		String catName = "test 05 Category name";
		String order = "0";
		int chooseRestricted = 1;
		String[] restricted = { "john,author:/developers,/organization/management" };
		String description = "Description";
		int setPermission = 0;
		String[] userGroup = null;
		boolean permission = false;

		goToForums();
		fmCat.addNewCategoryInForum(catName, order, chooseRestricted,
				restricted, description, setPermission, userGroup, permission);

		// determine if john who is a member of development group can view the
		// new category
		goToForumHome();
		waitForAndGetElement(By.linkText(catName));

		// determine if mary who is not a of development group can view the new
		// category or not
		acc.signOut();
		acc.signIn(DATA_USER_MARY, DATA_PASS);
		goToForums();
		fmCat.checkRightOfViewCategory(DATA_USER_MARY, DATA_PASS, catName, description, false);

		acc.signOut();
		acc.signIn(DATA_USER_JOHN, DATA_PASS);
		goToForums();
		waitForAndGetElement(By.linkText(catName)).click();
		waitForAndGetElement(fmCat.ELEMENT_MANAGE_CATEGORY);
		fmCat.deleteCategoryInForum(catName);
	}

	/**
	 * Case ID:72870. Test Case Name: Add a new category with blank Restricted
	 * audience. Created by khanhnt at 2013/11/20 09:34:03
	 */
	@Test
	public void test06_AddANewCategoryWithBlankRestrictedAudience() {
		info("Test 6: Add a new category with blank Restricted audience");
		String catName = "test 06 Category name";
		String order = "0";
		int chooseRestricted = 1;
		String[] restricted = { "" };
		String description = "Description";
		int setPermission = 0;
		String[] userGroup = null;
		boolean permission = false;

		goToForums();
		fmCat.addNewCategoryInForum(catName, order, chooseRestricted,
				restricted, description, setPermission, userGroup, permission);

		// determine if john who is a member of development group can view the
		// new category
		goToForumHome();

		waitForAndGetElement(By.linkText(catName));

		// determine if mary who is not a of development group can view the new
		// category or not
		acc.signOut();
		acc.signIn(DATA_USER_MARY, DATA_PASS);
		goToForums();
		fmCat.checkRightOfViewCategory(DATA_USER_MARY, DATA_PASS, catName, description, false);

		acc.signOut();
		acc.signIn(DATA_USER_JOHN, DATA_PASS);
		goToForums();
		waitForAndGetElement(By.linkText(catName)).click();
		waitForAndGetElement(fmCat.ELEMENT_MANAGE_CATEGORY);
		fmCat.deleteCategoryInForum(catName);

	}

	/*
	 * Case ID:73180. Test Case Name: Assign Moderator right for invalid user
	 * when add category. Created by khanhnt at 2013/11/20 09:34:03
	 *//*
	@Test(groups={"pending"})
	public void test07_AssignModeratorRightForInvalidUserWhenAddCategory() {

	}*/

	/*
	 * Case ID:73106. Test Case Name: Assign Moderator right when add category.
	 * Created by khanhnt at 2013/11/20 09:34:03
	 *//*
	@Test(groups={"pending"})
	public void test08_AssignModeratorRightWhenAddCategory() {
		info("Test 8: Assign Moderator right when add category");

	}*/

	/*
	 * Case ID:73192. Test Case Name: Assign right "Who Can create topic" for
	 * invalid user when add new category. Created by khanhnt at 2013/11/20
	 * 09:34:03
	 *//*
	@Test(groups={"pending"})
	public void test09_AssignRightWhoCanCreateTopicForInvalidUserWhenAddNewCategory() {
		info("Test 9: Assign right Who Can create topic for invalid user when add new category");

	}*/

	/*
	 * Case ID:73202. Test Case Name: Assign right "Who Can post " for invalid
	 * user when add new category. Created by khanhnt at 2013/11/20 09:34:03
	 *//*
	@Test(groups={"pending"})
	public void test010_AssignRightWhoCanPostForInvalidUserWhenAddNewCategory() {
		info("Test 10: Assign right Who Can post  for invalid user when add new category");

	}*/

	/*
	 * Case ID:73211. Test Case Name: Assign right "Who Can view posts" for
	 * invalid user when add new category. Created by khanhnt at 2013/11/20
	 * 09:34:03
	 *//*
	@Test(groups={"pending"})
	public void test11_AssignRightWhoCanViewPostsForInvalidUserWhenAddNewCategory() {
		info("Test 11 Assign right Who Can view posts for invalid user when add new category");

	}*/

	/*
	 * Case ID:73127. Test Case Name: Assign right to user can create topic when
	 * add new category. Created by khanhnt at 2013/11/20 09:34:03
	 *//*
	@Test(groups={"pending"})
	public void test12_AssignRightToUserCanCreateTopicWhenAddNewCategory() {
		info("Test 12 Assign right to user can create topic when add new category");

	}*/

	/*
	 * Case ID:73148. Test Case Name: Assign right to user can post in all
	 * topics when add new category. Created by khanhnt at 2013/11/20 09:34:03
	 *//*
	@Test(groups={"pending"})
	public void test13_AssignRightToUserCanPostInAllTopicsWhenAddNewCategory() {
		info("Test 13 Assign right to user can post in all topics when add new category");

	}*/

	/*
	 * Case ID:73166. Test Case Name: Assign right to user can view all topics
	 * when add new category. Created by khanhnt at 2013/11/20 09:34:03
	 *//*
	@Test(groups={"pending"})
	public void test14_AssignRightToUserCanViewAllTopicsWhenAddNewCategory() {
		info("Test 14 Assign right to user can view all topics when add new category");

	}*/

	/**
	 * Case ID:72278. Test Case Name: Check add a new category permission.
	 * Created by khanhnt at 2013/11/20 09:34:03
	 */
	@Test
	public void test15_CheckAddANewCategoryPermission() {
		info("Test 15 Check add a new category permission");

		info("Test 15 Check with administration");
		// test with administration user

		goToForums();
		waitForAndGetElement(ELEMENT_ADD_CATEGORY);

		info("Test 15 Check with moderator");
		// test with Mary, who is moderator
		acc.signOut();
		acc.signIn(DATA_USER_MARY, DATA_PASS);
		waitForElementNotPresent(ELEMENT_ADD_CATEGORY);


		info("Test 15 Check with normal user");
		// test with James, who is a normal user
		acc.signOut();
		acc.signIn("james", DATA_PASS);
		goToForums();
		waitForElementNotPresent(ELEMENT_ADD_CATEGORY);

		info("Test 15 Check with guest");
		// test with a guest
		acc.signOut();
		waitForElementNotPresent(ELEMENT_ADD_CATEGORY);
	}

}