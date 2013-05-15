package org.exoplatform.selenium.platform.ks.functional.wiki.basicaction;

import org.exoplatform.selenium.platform.ks.KsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.platform.ks.Wiki.*;
/**
 * 
 * @author thaopth
 * Date: 07/12/2012
 */

public class KS_Wiki_BasicAction_Edit extends KsBase {
	public String admin = "john";
	public String pass = "gtn";

	@BeforeMethod
	public void beforeMethods(){
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		actions = new Actions(driver);
		signIn(admin, pass);
	}

	@AfterMethod
	public void afterMethods(){
		info("-- Finished: test case --");
		//signOut();
		driver.quit();
		actions = null;
	}
	/* KS/Wiki/Basic Action/Edit
	 * Case 01: Edit wiki page
	 */
	@Test
	public void test01_EditPage () {
		//Define data test
		String DATA_WIKI_TITLE = "wikipage01";
		String DATA_WIKI_CONTENT = "test01_content before edit";
		String DATA_WIKI_CONTENT_EDITED = "Wiki content afer editing";

		goToWiki();

		addBlankWikiPage(DATA_WIKI_TITLE, DATA_WIKI_CONTENT, 0);

		editWikiPage(DATA_WIKI_TITLE, DATA_WIKI_CONTENT_EDITED, 0,false);

		deleteWikiPage();
	}
	/*
	 * KS/Wiki/Basic Action/Edit
	 * Case 02: Edit page when the title is the same with existing page
	 */
	@Test
	public void test02_EditPageWithSameArticle () {
		//Define data test
		String DATA_WIKI_TITLE1 = "wikipage02a";
		String DATA_WIKI_TITLE2 = "wikipage02b";
		String DATA_WIKI_CONTENT1 = "Cotent of wiki page 02a";
		String DATA_WIKI_CONTENT2 = "Content of wiki page 02b";
		String DATA_WARNING_MESSAGE = "The page title already exists. Please select another one.";

		goToWiki();

		addBlankWikiPage(DATA_WIKI_TITLE1, DATA_WIKI_CONTENT1, 0);

		goToWikiPage("Wiki Home");

		addBlankWikiPage(DATA_WIKI_TITLE2, DATA_WIKI_CONTENT2, 0);

		info("--Edit a wiki page 2--");

		click(ELEMENT_EDIT_PAGE_LINK);

		type(ELEMENT_TITLE_WIKI_INPUT,DATA_WIKI_TITLE1,true);

		save();

		waitForTextPresent(DATA_WARNING_MESSAGE);

		click(ELEMENT_OK_BUTTON);

		//Clear data

		goToWiki();

		goToWikiPage(DATA_WIKI_TITLE1);

		deleteWikiPage();

		goToWikiPage(DATA_WIKI_TITLE2);

		deleteWikiPage();							
	}
	/* KS/wiki/basic action/edit
	 * Edit paragraph when the level of header is equal to  paragraph below
	 */
	@Test
	public void test03_EditParagraphWhenTheLevelOfHearIsEqualToParagraphBelow () {
		
		String DATA_WIKI_TITLE = "Test edit wiki with paragraph1";
		String DATA_WIKI_CONTENT = "= paragraph1 = \n = paragraph2=";
		String DATA_PARAGRAPH1_NEW = "= test edit paragraph=";

		goToWiki();

		addBlankWikiPage(DATA_WIKI_TITLE, DATA_WIKI_CONTENT, 0, false);

		editParagraph("paragraph1",DATA_PARAGRAPH1_NEW);
	
		waitForElementPresent(By.xpath("//span[text()='test edit paragraph']"));
		
		//Clear data
		
		deleteWikiPage();	
	}
	/*
	 * KS/wiki/basic action/edit
	 * Case 04: Edit paragraph when the level of header is greater than paragraph below
	 */
	@Test
	public void test04_EditParagraphWhenTheLevelOfHeaderGreaterThanParagraphBelow () {
		String DATA_WIKI_TITLE = "Test edit wiki with paragraph1";
		String DATA_WIKI_CONTENT = "= level1 = \n == level2 ==";
		String DATA_PARAGRAPH2_NEW = "== test edit paragraph level2 ==";

		goToWiki();

		addBlankWikiPage(DATA_WIKI_TITLE, DATA_WIKI_CONTENT, 0, false);

		editParagraph("level2",DATA_PARAGRAPH2_NEW);
	
		waitForElementPresent(By.xpath("//span[text()='test edit paragraph level2']"));
		
		//Clear data
		
		deleteWikiPage();	
	}
	
	/*
	 * KS/Wiki/Basic Action/Edit
	 * Case 05: Minor edit
	 */
	
	@Test
	public void test05_MinorEdit () {
		String DATA_WIKI_TITLE = "Test minor edit";
		String DATA_WIKI_CONTENT = "Wiki content";
		String DATA_NEW_WIKI_CONTENT = "New content";
		
		goToWiki();

		addBlankWikiPage(DATA_WIKI_TITLE, DATA_WIKI_CONTENT, 0);
		
		goToIntranet();
		
		captureScreen("BeforeMinorEdit");
		
		goToWiki();
		
		goToWikiPage(DATA_WIKI_TITLE);
		
		editWikiPage(DATA_WIKI_TITLE, DATA_NEW_WIKI_CONTENT, 0,true);
		
		goToIntranet();
		
		captureScreen("AfterMinorEdit");
		
		//Clear data
		
		goToWiki();
		
		goToWikiPage(DATA_WIKI_TITLE);
		
		deleteWikiPage();
	}
}
