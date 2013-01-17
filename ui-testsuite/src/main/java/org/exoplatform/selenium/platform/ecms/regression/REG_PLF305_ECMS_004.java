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
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with " + DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
	}
	
	@AfterMethod
	public void afterMethods(){
		info("Logout ECMS");
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
		info("Go to Site Explorer");
		goToSiteExplorer();
		info("Go to node 'intranet'");
		goToNode("intranet");
		info("Click add new content");
		goToAddNewContent();
		pause(3000);
		
		info("Create new free layout webcontent");
		String title = "wc1";
		String mainContent = "Example of free layout webcontent";		
		String img = "TestData/ECMS_DMS_SE_BasicAction_CutPaste.png";
		String summary = "Test image";
		
		createNewFreeLayoutWebContent(title, title, mainContent, img, summary, "", "");
		
		info("Go to folder medias > images");
		pause(2000);
		goToNode(title);
		click(By.xpath("(//a[contains(text(),'medias')])[2]"));
		goToNode("images");
		pause(5000);
		
		info("Check that uploaded illustration image is located under wc1/medias/images/illustration");
		assert isElementPresent(By.xpath("//div[@title='illustration']"));
		pause(3000);
		
		info("Delete free layout webcontent");
		goToNode("intranet");
		deleteDocument(By.xpath("//a[contains(text(),'"+title+"')]"));
		pause(3000);
				
	} // End test04_UploadIllustrationImageInFreeLayoutWebContent

}
