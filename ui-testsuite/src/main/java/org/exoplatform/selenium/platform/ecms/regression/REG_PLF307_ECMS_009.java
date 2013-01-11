package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF307_ECMS_009 extends EcmsBase{
	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";
	public static final By ELEMENT_ACME_DRIVER = By.linkText("acme-category");
	public static final By DATA_CHOOSE_NAME = By.xpath("//div[@title='Fire']");
	public static final By ELEMENT_COLLABORATION_TAB = By.xpath("//a[contains(text(),'Collaboration')]");
	public static final By ELEMENT_COMMENT_ACTION_BAR = By.xpath("//a[contains(text(),'Comment')]");
	public static final By ELEMENT_CONTENT_COMMENT_FRAME = By.xpath("//iframe[contains(@title,'Rich text editor, comment, press ALT 0 for help.')]");
	public static final By ELEMENT_SAVE_BUTTON = By.xpath("//a[contains(text(),'Save')]");
	public static final By ELEMENT_BACK_BUTTON = By.xpath("//a[@title = 'Back to the previous node']");
	public static final By ELEMENT_QUICK_SEARCH_BUTTON = By.xpath("//a[@title = 'Quick Search']");
	public static final By ELEMENT_SEARCH_INPUT = By.xpath("//input[@id='simpleSearch']");
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
	/*CASE:
	 * - Go to the drive Events in the Sites Explore
	 * - Make a comment on any document on this drive
	 * - Back to the root and do a search with any word which is found in the comment
	 * Expected result: Search result contains the node having the relevant comment
	 */
	@Test
	public void test_searchResultContainsinComment(){
		String ELEMENT_CONTENT_COMMENT="ecms regression test";
		
		info("Go to Site Explorer");
		goToSiteExplorer();
		info("Choose acme-category drive");
		chooseDrive(ELEMENT_ACME_DRIVER);
		pause(1000);
		info("Choose document to comment");
		doubleClickOnElement(DATA_CHOOSE_NAME);
		pause(1000);
		info("Choose Collaboration tab");
		click(ELEMENT_COLLABORATION_TAB);
		info("Click to the Comment link on Action Bar");
		click(ELEMENT_COMMENT_ACTION_BAR);
		info("Type the content to comment");
		waitForElementPresent(ELEMENT_CONTENT_COMMENT_FRAME);
		type(ELEMENT_CONTENT_COMMENT_FRAME,ELEMENT_CONTENT_COMMENT, false);
		info("Save the comment");
		click(ELEMENT_SAVE_BUTTON);
		info("Back to previous node");
		click(ELEMENT_BACK_BUTTON);
		info("Quick Search");
		info("Type the keyword to search");
		waitForElementPresent(ELEMENT_SEARCH_INPUT);
		type(ELEMENT_SEARCH_INPUT,ELEMENT_CONTENT_COMMENT,false);
		click(ELEMENT_QUICK_SEARCH_BUTTON);
		pause(3000);
		info("Check the result");
		
	}

}
