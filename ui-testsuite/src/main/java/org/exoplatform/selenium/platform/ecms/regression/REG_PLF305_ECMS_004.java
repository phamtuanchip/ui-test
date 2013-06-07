package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.createNewFreeLayoutWebContent;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.deleteDocument;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF305_ECMS_004 extends EcmsBase{
	/*-- Data for these test cases --*/
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";	
	
	
	@BeforeMethod
	public void beforeMethods(){
		info("\n\n======= BEGIN Test REG_PLF305_ECMS_004 ======");
		info("\n======= Upload illustration image in free layout web content ======\n\n");
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with " + DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
	}
	
	@AfterMethod
	public void afterMethods(){
		info("Logout ECMS");
		info("\n\n======= END Test REG_PLF305_ECMS_004 ======\n\n");
		driver.quit();
		actions = null;
	}
	
	/*-- Case No 004 / ID 004 
	 *-- Upload illustration image in free layout web content
	 *	- Add a new Free Layout web content "wc1" and fill mandatory fields
		- In Illustration tab, upload an illustration image
		- Save as draft and close
		Expected result: the uploaded illustration image is located under wc1/medias/images/illustration
	 * --*/
	@Test
	public void test04_UploadIllustrationImageInFreeLayoutWebContent(){
		info("\n === Go to Site Explorer ===");
		goToSiteExplorer();
		info("\n === Go to node 'intranet' ===");
		goToNode("intranet");
		info("\n === Click add new content ===");
		goToAddNewContent();
		pause(3000);
		
		info("\n === Create new free layout webcontent 'wc1' and upload an illustration image ===");
		String title = "wc1";
		String mainContent = "Example of free layout webcontent";		
		String img = "TestData/ECMS_DMS_SE_BasicAction_CutPaste.png";
		String summary = "Test image";
		
		createNewFreeLayoutWebContent(title, title, mainContent, img, summary, "", "");
		
		info("\n\n === Go to folder medias > images ===");
		// Go to folder medias
		info("\n === Go to folder medias ===");
		waitForElementPresent(By.linkText(title));
		mouseOver(By.linkText(title),false);
		goToNode(title);
		waitForElementPresent(By.xpath("(//a[contains(text(),'medias')])[2]"));
		mouseOver(By.xpath("(//a[contains(text(),'medias')])[2]"),false);
		click(By.xpath("(//a[contains(text(),'medias')])[2]"));
		// Go to folder images
		info("\n === Go to folder images ===");
		waitForElementPresent(By.linkText("images"));
		mouseOver(By.linkText("images"),false);
		goToNode("images");
		
		info("\n\n === Check that uploaded illustration image is located under wc1/medias/images/illustration");
		waitForElementPresent(By.xpath("//div[contains(text(),'illustration')]"));
		mouseOver(By.xpath("//div[contains(text(),'illustration')]"),false);
		info("\n === Uploaded illustration image is found in wc1/medias/images/ ! OK !!! ===");
		
		info("\n\n === Delete free layout webcontent");
		goToNode("intranet");
		waitForElementPresent(By.linkText(title));
		deleteDocument(By.linkText(title));
		info("\n === Free layout webcontent wc1 is deleted ! ===");
				
	} // End test04_UploadIllustrationImageInFreeLayoutWebContent

}
