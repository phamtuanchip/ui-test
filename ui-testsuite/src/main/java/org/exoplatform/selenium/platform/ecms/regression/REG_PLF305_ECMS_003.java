package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
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
	public static final By ELEMENT_IFRAME=By.xpath("//td[contains(@id,'cke_contents_contentHtml')]/iframe");
	private String CONTENT_TO_ADD = "aaa";
	
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
	
	/*-- Case No 003 / ID 003 
	 *-- Edit HTML in Content Explorer
	 *	- Login as root
		- Go to Content Explorer
		- Open drive acme, select a document which has default.html file
		- Right-click on default.html, select Edit
		- In Edit form, edit something then click Save as Draft
		Expected result: No error "ERROR [portal:UIPortalApplication] Error during the processAction phase 
javax.jcr.ItemExistsException: [collaboration] ADD PROPERTY." appears on console.
	 * --*/
	@Test
	public void test03_EditHTMLinContentExplorer(){
		//goto Site Explorer
		info("Go to Site Explorer");
		goToSiteExplorer();	
		
		// Edit default.html
		info("Go to default/web contents/site artifacts");
		goToNodeByPath("default/web contents/site artifacts");
		info("Click on Welcome node");
		click(ELEMENT_WELCOME);	
		pause(1000);
		info("Click on default.html");
		click(ELEMENT_DEFAULT);	
		pause(1000);
		info("Click on Publication > Edit");
		click(ELEMENT_PUBLICATION);
		pause(1000);
		click(ELEMENT_EDIT);
		pause(1000);
		
		info("Add text 'aaa' to Rich Text Editor content");		
		inputDataToFrame(ELEMENT_IFRAME,CONTENT_TO_ADD);		
		switchToParentWindow();		
		
		info("Click Save button");
		click(ELEMENT_SAVE_BUTTON);
		pause(1000);		
		
		info("Click Save & Close button");
		click(ELEMENT_SAVE_AND_CLOSE_BUTTON);		
	}
}
