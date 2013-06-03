package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF305_ECMS_008 extends EcmsBase {
	/*-- Data for these test cases --*/
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";
	
	private String DATA_ARTICLE_NAME = "Article1";
	private By ELEMENT_ARTICLE = By.linkText(DATA_ARTICLE_NAME);
	private By ELEMENT_SAVE_BUTTON = By.linkText("Save");
	
	
	@BeforeMethod
	public void beforeMethods(){
		info("\n\nBEGIN Test case REG_PLF305_ECMS_008 \n\n");
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with " + DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
	}
	
	@AfterMethod
	public void afterMethods(){
		info("Logout ECMS");
		info("\n\nEND Test case REG_PLF305_ECMS_008 \n\n");
		driver.quit();
		actions = null;
	}
	
	/*-- Case No 008 / ID REG_PLF305_ECMS_008 
	 *-- Publish document in a locked folder
	 *	- Go to the Drive "acme"
		- Lock the "documents" folder
		- Create a new document in the "documents" folder
		- Click on the "publish" button 		
		Expected result: Publish document successfully. 
		No exception: "javax.jcr.lock.LockException: Node /Documents is locked..."
	 * --*/
	@Test
	public void test08_PublishDocumentInLockedFolder(){
		info("\n=== Go to Site Explorer ===");
		goToSiteExplorer();		
		pause(1000);
		
		info("\n=== Go to node acme ===");
		goToNode("acme");
		pause(1000);
		
		info("\n=== Lock node documents ===");
		lockNode(By.xpath("//a[contains(text(),'documents')]"));
		pause(1000);
		
		info("\n=== Go to node documents ===");
		click(By.xpath("//a[contains(text(),'documents')]"));
		pause(1000);
		
		info("\n=== Add new article in folder documents locked ===");
		goToAddNewContent();
		pause(1000);
		createNewArticle(DATA_ARTICLE_NAME, DATA_ARTICLE_NAME, "", "");
		waitForElementPresent(ELEMENT_ARTICLE);
		assert isElementPresent(ELEMENT_ARTICLE):"Create new article unsuccessfully";
		info("\n=== Create new article successfully");
		pause(1000);
		
		info("\n=== Click Publication ====");
		click(By.xpath("//a[contains(text(),'Publications')]"));
		pause(500);
		click(ELEMENT_SAVE_BUTTON);
		pause(500);
		waitForElementPresent(ELEMENT_ARTICLE);
		assert isElementPresent(ELEMENT_ARTICLE):"Publish new article unsuccessfully";
		info("\n=== Public new article successfully ===");
		
		info("\n=== Delete new article ===");
		deleteDocument(ELEMENT_ARTICLE);
		pause(3000);
	} // End test08_PublishDocumentInLockedFolder

}
