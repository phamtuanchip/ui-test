package org.exoplatform.selenium.platform.ks.functional.wiki.basicaction;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.platform.ks.KsBase;
import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;
import static org.exoplatform.selenium.platform.ks.Wiki.*;

/**
 * 
 * @author HangNTT
 * @date: 7/12/2012
 */
public class KS_Wiki_BasicAction_Move extends KsBase {

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		signIn("john", "gtn");
	}

	String[] user1= {"james"};

	//Move a page when user have edit permission on page 
	@Test
	public void test01_MoveAPageWhenUserHavePermisionOnPage() {

		String PAGE_NAME1 = "wiki1";
		String PAGE_NAME2 = "wiki2";
		
		By ELEMENT_PAGE1 = By.linkText(PAGE_NAME1);
		By ELEMENT_PAGE2 = By.linkText(PAGE_NAME2);
		
		goToWiki();

		addBlankWikiPage(PAGE_NAME1, PAGE_NAME1, 0);

		click(ELEMENT_WIKI_HOME);

		addBlankWikiPage(PAGE_NAME2, PAGE_NAME2, 0);

		click(ELEMENT_PAGE1);
		
		deletePermission("any");

		addPagePermission(0,user1);

		editPagePermission("james", true, true);

		movePage(PAGE_NAME1,PAGE_NAME2);

		click(ELEMENT_PAGE2);

		deleteWikiPage();
	}
	
	//Move a page when user have edit permission on page 
	@Test
	public void test02_MoveAPageWhenAnyUserHavePermisionOnPage() {

		String PAGE_NAME1 = "wiki3";
		String PAGE_NAME2 = "wiki4";
		
		By ELEMENT_PAGE1 = By.linkText(PAGE_NAME1);
		By ELEMENT_PAGE2 = By.linkText(PAGE_NAME2);

		goToWiki();

		addBlankWikiPage(PAGE_NAME1, PAGE_NAME1, 0);

		click(ELEMENT_WIKI_HOME);

		addBlankWikiPage(PAGE_NAME2, PAGE_NAME2, 0);

		signOut();

		signIn("mary", "gtn");

		goToWiki();

		click(ELEMENT_PAGE2);

		movePage(PAGE_NAME2,PAGE_NAME1);

		click(ELEMENT_PAGE1);

		deleteWikiPage();
	}
	
	//Move a page when user have edit permission on page 
	@Test
	public void test03_MoveAPageWhenUserDoesNotHavePermisionOnPage() {

		String PAGE_NAME1 = "wiki5";
		
		By ELEMENT_PAGE1 = By.linkText(PAGE_NAME1);

		goToWiki();

		addBlankWikiPage(PAGE_NAME1, PAGE_NAME1, 0);

		addPagePermission(0,user1);

		deletePermission("any");

		signOut();

		signIn("james", "gtn");

		goToWiki();

		click(ELEMENT_PAGE1);

		mouseOver(ELEMENT_MORE_LINK, true);

		waitForElementNotPresent(ELEMENT_MOVE_PAGE_LINK);

		signOut();

		signIn("john", "gtn");

		goToWiki();

		click(ELEMENT_PAGE1);

		deleteWikiPage();
	}
	
	//Move A Page When User have don't Permission On Destination Page
	@Test
	public void test04_MoveAPageWhenUserDoNotHavePermisionOnDestinationPage() {

		String PAGE_NAME1 = "wiki6";
		String PAGE_NAME2 = "wiki7";
		
		By ELEMENT_PAGE1 = By.linkText(PAGE_NAME1);
		By ELEMENT_PAGE2 = By.linkText(PAGE_NAME2);

		String ELEMENT_VERIRY_MESSAGE = "You have no edit permission at destination page";

		goToWiki();

		addBlankWikiPage(PAGE_NAME1, PAGE_NAME1, 0);

		addPagePermission(0,user1);

		deletePermission("any");

		click(ELEMENT_WIKI_HOME);

		addBlankWikiPage(PAGE_NAME2, PAGE_NAME2, 0);

		signOut();

		signIn("james", "gtn");

		goToWiki();

		click(ELEMENT_PAGE2);

		movePage(PAGE_NAME2,PAGE_NAME1);

		waitForTextPresent(ELEMENT_VERIRY_MESSAGE);

		click(ELEMENT_OK_BUTTON);

		waitForElementNotPresent(ELEMENT_OK_BUTTON);

		cancel();

		signOut();

		signIn("john", "gtn");

		goToWiki();

		click(ELEMENT_PAGE1);

		deleteWikiPage();

		click(ELEMENT_PAGE2);

		deleteWikiPage();

	}

	@AfterMethod
	public void afterTest(){
		signOut();
		driver.manage().deleteAllCookies();
		driver.quit();
	}
}
