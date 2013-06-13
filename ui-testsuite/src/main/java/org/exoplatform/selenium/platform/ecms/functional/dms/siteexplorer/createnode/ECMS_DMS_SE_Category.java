package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*;

public class ECMS_DMS_SE_Category extends EcmsBase{
	public static String USER = "john";
	public static String PASS = "gtn";
	
	public static final String ELEMENT_PATH_SYSTEM = "exo:ecm/exo:taxonomyTrees/storage/System";
	public static final By ELEMENT_VIEW_SIMPLE = By.xpath("//a[@title='Simple View']");
	public static final By ELEMENT_LINK_SYSTEM=By.linkText("System");
	public static final By ELEMENT_ICON_EDIT_CATE=By.xpath("//img[@alt='Edit Category Tree'][1]");

	// Add category that name contains special characters
	@Test
	public void test04_AddCategoryWithSpecialCharacter() {
		String DATA_CATE_NAME_04="FNC_ECMS_FEX_CREATE_13_04_^%$@@!#";
		By bCate = By.xpath("//a[@title='"+ DATA_CATE_NAME_04+ " ']");
		info("Add category that the name contains special characters");
		//choose drive and go to exo:ecm/exo:taxonomyTrees/storage/System
		chooseDrive(ELEMENT_DMS_ADMIN_DRIVE);
		goToNodeByPath(ELEMENT_PATH_SYSTEM);
		pause(200);
		click(ELEMENT_VIEW_SIMPLE);
		pause(500);

		//add category
		addCategoryInSimpleView(DATA_CATE_NAME_04);
		assert isElementPresent(bCate):"Fail to create a category!";
		pause(500);
		//delete data
		deleteDocument(bCate);
		waitForElementNotPresent(bCate);

	}
	//Add category, then check if it displays in ECM administration
	@Test
	public void test08_AddCategoryThenCheckDisplay() {
		String DATA_CATE_NAME_08 = "FNC_ECMS_FEX_CREATE_13_04";
		String DATA_CATE_NAME_08_TITLE = "fncecmsfexcreate1304";
		By bCate=By.xpath("//a[@title='"+ DATA_CATE_NAME_08+ " ']");
		info("Add category, then check if to display in ECM administration!");
		//choose drive and go to exo:ecm/exo:taxonomyTrees/storage/System
		chooseDrive(ELEMENT_DMS_ADMIN_DRIVE);
		goToNodeByPath(ELEMENT_PATH_SYSTEM);
		pause(200);
		click(ELEMENT_VIEW_SIMPLE);
		pause(500);

		//add category
		addCategoryInSimpleView(DATA_CATE_NAME_08);
		assert isElementPresent(bCate):"Fail to create a category!";
		pause(500);

		//check display 
		goToContentAdministration();
		click(ELEMENT_WCM_CATEGORY_TAG);
		pause(500);
		click(ELEMENT_MANAGE_CATEGORIES_LINK);
		pause(500);
		click(ELEMENT_ICON_EDIT_CATE);
		pause(500);
		click(ELEMENT_LINK_SYSTEM);
		waitForTextPresent(DATA_CATE_NAME_08_TITLE);
		click(ELEMENT_CLOSE_BUTTON);

		//go to Site explorer and delete data
		goToSiteExplorer();
		deleteDocument(bCate);
		waitForElementNotPresent(bCate);
	}
	@BeforeMethod
	public void beforeMethod() {
		initSeleniumTest();
		driver.get(baseUrl);

		actions = new Actions(driver);
		loginEcms(USER, PASS);
		goToSiteExplorer();
	}

	@AfterMethod
	public void afterMethod() {
		logoutEcms();
		driver.quit();
	}
}