package org.exoplatform.selenium.platform.ks.functional.wiki.attachment;

import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.ks.Wiki;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KS_Wiki_Attachment_Delete extends Wiki{

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		signIn("john", "gtn"); 
		goToWiki();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	public static String MSG_CANCEL_CREATE_PAGE="Are you sure to leave this page?";
	public static By ELEMENT_OK_BUTTON=By.xpath("//input[@value='OK']");
	
	/**
	 * Case 01: Delete attach file for page
	 * Create a wiki page
	 * Scroll down, click Upload, choose an image
	 * verify attachment link
	 * Delete attachment
	 * Click Cancel
	 */
	@Test
	public static void test01_DeleteAttachment(){

		String DATA_WIKI_PAGE_NAME="Blank_Wiki_Page_With_Image";
		String DATA_WIKI_PAGE_CONTENT="Blank_Wiki_Page_With_Image_Content";
		String ATTACHMENT_NAME="Winter.jpg";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME+"";
		By ATTACHMENT_FILE_LINK=By.xpath("//a[text()='"+ATTACHMENT_NAME+"']");

		info("Delete attachment at wiki page");
		
		info("--Add a wiki page from blank--");
		mouseOver(ELEMENT_ADD_PAGE_LINK, true);
		mouseOverAndClick(ELEMENT_BLANK_PAGE_LINK);
		type(ELEMENT_TITLE_WIKI_INPUT, DATA_WIKI_PAGE_NAME, true);
		
		type(ELEMENT_CONTENT_WIKI_INPUT,DATA_WIKI_PAGE_CONTENT,true);
		
		attachFileInWiki(ATTACHMENT_PATH);
		waitForElementPresent(ATTACHMENT_FILE_LINK);
		
		String removeIcon= ELEMENT_REMOVE_ATTACHMENT.replace("{$file}", ATTACHMENT_NAME);
		click(removeIcon);
		waitForElementNotPresent(removeIcon);
		waitForElementNotPresent(ATTACHMENT_FILE_LINK);
		
		pause(1000);
		click(ELEMENT_CANCEL_BUTTON);
		waitForTextPresent(MSG_CANCEL_CREATE_PAGE);
		click(ELEMENT_OK_BUTTON);
	}
}
