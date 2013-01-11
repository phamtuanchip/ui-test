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


public class REG_PLF305_ECMS_003 extends EcmsBase {
	/*-- Data for these test cases --*/
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";
	
	public static By ELEMENT_WELCOME = By.xpath("//a[contains(text(),'Welcome')]");
	public static By ELEMENT_DEFAULT = By.xpath("//a[contains(text(),'default.html')]");
	public static By ELEMENT_PUBLICATION = By.xpath("//a[contains(text(),'Publication')]");
	public static By ELEMENT_EDIT = By.xpath("//a[@class='SubTabIcon DefaultActionIcon EditDocumentIcon' and contains(text(),'Edit')]");
	public static By ELEMENT_SOURCE = By.xpath("//a[@class='cke_off cke_button_source' and @title='Source')]");
	public static By ELEMENT_TEXTAREA = By.xpath("//textarea[@class='cke_source cke_enable_context_menu']");
	public static final By ELEMENT_SAVE_AND_CLOSE_BUTTON = By.linkText("Save & Close");
	public static By ELEMENT_CONTENT = By.xpath("//div[@class='ContainerLeft FL']");	
	public static final By ELEMENT_IFRAME=By.xpath("//td[@class='cke_contents' and @role='presentation']/iframe");
	private String CONTENT_TO_ADD = "aaa";
	
	@BeforeMethod
	public void beforeMethods(){
		info("\n\n |||||||| Test case REG_PLF305_ECMS_003 - BEGIN |||||||| \n");
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
	
	/*-- Case No 003 / ID 003 
	 *-- Edit HTML in Content Explorer
	 *	- Login as John
		- Go to Content Explorer
		- Go to acme folder
		- Create new sub-folder "REG_PLF305_ECMS_003"
		- Go into new folder
		- Create new Free layout webcontent, named "FLWC1"
		- Edit file default.html of FLWC1
		- Click Save		
		Expected result: No error "ERROR [portal:UIPortalApplication] Error during the processAction phase 
javax.jcr.ItemExistsException: [collaboration] ADD PROPERTY." appears on console.
	 * --*/
	@Test
	public void test03_EditHTMLinContentExplorer(){
		//goto Site Explorer
		info("\n === Go to Site Explorer ===");
		goToSiteExplorer();	
		
		// Go to acme folder
		info("\n === Go to acme folder ===");
		goToNodeByPath("acme");
		pause(4000);
		
		// Create new folder REG_PLF305_ECMS_003 - OK
		info("\n === Create new folder REG_PLF305_ECMS_003 ===");
		createNewContentFolder("REG_PLF305_ECMS_003", "regplf305ecms003");
		pause(1000);
		
		// Go into new folder - OK
		info("\n === Go into new folder REG_PLF305_ECMS_003 ===");
		goToNodeByPath("REG_PLF305_ECMS_003");
		pause(4000);
		
		// Create new Free layout webcontent, named "FLWC1" - OK
		info("\n === Create new Free layout webcontent, named FLWC1 ===");
		goToAddNewContent();
		createNewFreeLayoutWebContent("FLWC1", "flwc1", "First content", "", "", "", "");
		pause(5000);
		
		// Go to default.html of FLWC1 - OK
//		info("\n === Go to default.html of FLWC1 - temps used ===");
//		goToNodeByPath("FLWC1");
//		pause(4000);
		click(By.xpath("//a[contains(text(),'default.html')]"));
		pause(4000);
		
		// Click Edit 
		info("\n === Edit default.html ===");
		waitForElementPresent(By.xpath("//a[@class='SubTabIcon DefaultActionIcon EditDocumentIcon']"));
		click(By.xpath("//a[@class='SubTabIcon DefaultActionIcon EditDocumentIcon']"));
		pause(4000);
		info("\n === Update content of default.html ===");
		inputDataToFrame(By.xpath("//td[contains(@id,'cke_contents_contentHtml')]/iframe"),"Second content",true);
		pause(2000);
		switchToParentWindow();
		pause(2000);
		
		// Click Save button		
		info("\n === Click Save & Close button ===");
		saveAndClose();
		pause(10000);
		
		info("\n\n ====================================");
		info("\n ===== ATTENTION !!!! \n");
		info("\n If you don't see any \n ***ERROR [portal:UIPortalApplication] Error during the processAction phase*** \n" +
				" on console, that means this test case is PASSED. \n" +
				"Otherwise, please clone issue ECMS-1940. Thanks!");
		info("\n ==================================== \n");
		
		
		// Return to acme folder
		info("\n === Return to acme folder ===");
		goToNodeByPath("acme");
		pause(4000);
		info("\n === Delete data in REG_PLF305_ECMS_003 folder ===");
		deleteData(By.xpath("//div[contains(text(),'REG_PLF305_ECMS_003')]"));
		pause(5000);
				
		// END
		info("\n\n ||||| END Test case REG_PLF305_ECMS_003 ||||| \n\n\n");
	}
}
