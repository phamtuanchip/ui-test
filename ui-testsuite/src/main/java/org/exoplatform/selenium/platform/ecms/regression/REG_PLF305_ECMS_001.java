package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.PageEditor.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.selectGroup;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*; 
import static org.exoplatform.selenium.platform.PageManagement.deletePage;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;

import org.exoplatform.selenium.platform.PlatformBase.PageType;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF305_ECMS_001 extends EcmsBase {
	/*-- Data for these test cases --*/
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";
	
	private String ContentFolderTitle = "REG_PLF305_ECMS_001";
	private String ContentFolderName = "regplf305ecms001";
	private String NewFreeLayoutWebContentEnTitle = "FLWC1en";
	private String NewFreeLayoutWebContentEnName = "flwc1en";
	private String NewFreeLayoutWebContentEnCont = "English content";
	private String NewFreeLayoutWebContentBrTitle = "FLWC1br";
	private String NewFreeLayoutWebContentBrName = "flwc1br";
	private String NewFreeLayoutWebContentBrCont = "Brazil Portuguese content";
	private String NewPageName = "Test_REG_PLF305_ECMS_001";
	
	@BeforeMethod
	public void beforeMethods(){
		info("\n\nBEGIN Test case REG_PLF305_ECMS_001 \n\n");
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with " + DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
	}
	
	@AfterMethod
	public void afterMethods(){
		info("Logout ECMS");
		info("\n\nEND Test case REG_PLF305_ECMS_001 \n\n");
		driver.quit();
		actions = null;
	}
	
	/*-- Case No 001 / ID REG_PLF305_ECMS_001 
	 *-- Add  Brazilian Portuguese translation: content is not shown
	 *	- Login as John
	 	- Go to Content > Content administration 
		- Enable Add Translation in Public tab
		- Go to Content > Sites Explorer
		- Create new folder in acme
		- Create new Free layout webcontent "flwc1" in english
		- Create new translation for "flwc1" in brazil portuguese
		- Go to Intranet > Home
		- Add new page 
		- Add portlet
		- Change language to brazil portuguese
		Expected result: Content of "flwc1" translation is appeared correctly		
	 * --*/
	@Test
	public void test01_AddBrazilianPortugueseTranslation(){
		// === Enable Add Translation in Public tab ===		
		info("\n=== Enable Add Translation in Publication tab ===");
		addView("WCM View", "Publication", "addLocalizationLink");
		
		// Go to Sites Explorer
		info("\n=== Go Sites Explorer ===");
		goToSiteExplorer();
		pause(2000);
		info("\n=== Go to node acme ===");
		goToNode("acme");
		pause(2000);
		
		// Create new folder REG_PLF305_ECMS_001 
		info("\n === Create new folder REG_PLF305_ECMS_001 ===");
		createNewContentFolder(ContentFolderTitle, ContentFolderName);
		pause(2000);
		// Go into new folder
		info("\n === Go into new folder REG_PLF305_ECMS_001 ===");
		goToNodeByPath(ContentFolderTitle);
		pause(4000);
		// Create new Free layout webcontent, named "FLWC1en", in english 
		info("\n === Create new Free layout webcontent, named 'FLWC1en' ===");
		goToAddNewContent();
		createNewFreeLayoutWebContent(NewFreeLayoutWebContentEnTitle, NewFreeLayoutWebContentEnName, NewFreeLayoutWebContentEnCont, "", "", "", "");
		pause(5000);
		waitForElementPresent(By.xpath("//p[contains(text(),'"+NewFreeLayoutWebContentEnCont+"')]"));
		//Return to parent folder
		info("\n === Return to parent folder REG_PLF305_ECMS_001 ===");
		goToNodeByPath(ContentFolderTitle);
		pause(4000);
		
		// Create new Free layout webcontent, named "FLWC1br", in brazil portuguese
		info("\n === Create new Free layout webcontent, named 'FLWC1br' ===");
		goToAddNewContent();
		createNewFreeLayoutWebContent(NewFreeLayoutWebContentBrTitle, NewFreeLayoutWebContentBrName, NewFreeLayoutWebContentBrCont, "", "", "", "");
		pause(5000);
		waitForElementPresent(By.xpath("//p[contains(text(),'"+NewFreeLayoutWebContentBrCont+"')]"));
		// Change language of FLWC1br to Brazil portuguese
		click(By.xpath("//a[@class='SubTabIcon DefaultActionIcon EditDocumentIcon' and @title='Edit']"));
		pause(2000);
		selectOption(By.id("content-lang"), "pt_BR");
		pause(2000);
		saveAndClose();
		waitForElementPresent(By.xpath("//p[contains(text(),'"+NewFreeLayoutWebContentBrCont+"')]"));
		info("\n === New Free layout webcontent 'FLWC1br' is created! ===");
		pause(2000);
		
		// Add translation for FLWC1en
		info("\n\n=== Add translation for FLWC1en ===");
		goToNodeByPath(NewFreeLayoutWebContentEnTitle);
		click(By.linkText("Add Translation"));
		pause(2000);
		waitForElementPresent(ELEMENT_ADD_SYMLINK_POPUP);
		click(By.xpath("//img[@class='MultiFieldAction AddNewNodeIcon']"));
		pause(1000);
		click(By.xpath("//a[@class='Item default16x16Icon exo_webContent16x16Icon' and contains(text(),'"+NewFreeLayoutWebContentBrTitle+"')]"));
		pause(2000);
		save();
		waitForElementPresent(By.xpath("//p[contains(text(),'"+NewFreeLayoutWebContentEnCont+"')]"));
		pause(5000);
		info("\n === Translation for FLWC1en is added ! ===");
		
		// Publish FLWC1en
		info("\n\n=== Publish FLWC1en ===");
		click(By.linkText("Publications"));
		pause(2000);
		waitForElementPresent(By.id("UIPopupWindow"));
		click(By.xpath("//a[contains(text(),'Published')]"));
		pause(2000);
		save();
		pause(2000);
		info("\n=== FLWC1en published ! ===");
		
		// Publish FLWC1br
		info("\n\n=== Publish FLWC1br ===");
		info("\n=== Go Sites Explorer ===");
		goToSiteExplorer();
		pause(2000);
		info("\n=== Go to node acme ===");
		goToNode("acme");
		pause(2000);
		goToNodeByPath(ContentFolderTitle);
		pause(4000);
		goToNodeByPath(NewFreeLayoutWebContentBrTitle);
		pause(4000);
//		waitForElementPresent(By.xpath("//p[contains(text(),'"+NewFreeLayoutWebContentBrCont+"')]"));
//		pause(2000);
		waitForElementPresent(By.linkText("Publications"));
		click(By.linkText("Publications"));
		pause(2000);
		waitForElementPresent(By.id("UIPopupWindow"));
		click(By.xpath("//a[contains(text(),'Published')]"));
		pause(2000);
		save();
		pause(2000);
		info("\n=== FLWC1br published ! ===");
		pause(2000);
		
		// Go to intranet
		info("\n\n=== Create a new SCV page ===");
		info("\n=== Go to intranet ===");
		mouseOver(By.xpath("//a[contains(text(),'My Sites')]"),true);
		pause(500);
		click(By.xpath("//a[contains(text(),'intranet')]"));
		pause(3000);
		
		// === Add a new page Test ===
		// Mouser over Edit > Page and click Add Page
		info("\n=== Mouser over Edit > Page and click Add Page ===");
		mouseOver(By.linkText("Edit"),true);
		pause(1000);
		mouseOver(By.linkText("Page"),true);
		pause(1000);
		click(By.linkText("Add Page"));
		goToAddPageEditor();
		pause(5000);
		
		// Fill in page name
		info("\n=== Fill in page name ===");
		type(By.id("pageName"),NewPageName,true);
		pause(2000);
		// Pass to second step
		info("\n=== Pass to second step ===");
		next();
		pause(2000);
		// Pass to third step
		info("\n=== Pass to third step ===");
		next();
		pause(2000);
		
		// ==== Add new SCV portlet ====
		info("\n=== Add new SCV portlet ===");
		click(By.xpath("//a[contains(text(),'Content')]"));
		pause(4000);		
		mouseOver(By.id("Content/SingleContentViewer"),true);
		pause(1000);
		click(By.id("Content/SingleContentViewer"));
		pause(1000);
		dragAndDropToObject("//div[@id='Content/SingleContentViewer']//img", "//div[@id='UIPage']");
		pause(2000);
		
		// Select FLWC1en node in Content Path
		info("\n=== Select FLWC1en node in Content Path ===");
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET,true);		
		click(ELEMENT_EDIT_PORTLET_ICON);
		pause(1000);
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		pause(1000);
		click(By.xpath("//a[contains(text(),'General Drives')]"));
		pause(1000);
		click(By.xpath("//a[contains(text(),'Sites Management')]"));
		pause(1000);
		click(By.linkText("acme"));
		pause(1000);
		click(By.xpath("//a[contains(text(),'"+ContentFolderTitle+"')]"));
		pause(1000);
		click(By.xpath("//div[@id='RightWorkspace']/div/div/table/thead/tr/td/a[contains(text(),'"+NewFreeLayoutWebContentEnTitle+"')]"));		
		pause(2000);
		// Save and close edit portlet popup
		info("\n=== Save and close edit portlet popup ===");
		save();
		pause(2000);
		close();
		pause(2000);
		info("\n=== Click Finish button to close edit mode ===");
		click(By.xpath("//a[@class='EdittedSaveButton' and @title='Finish']"));
		pause(4000);
		
		// ==== END Add new SCV portlet ====
		
		// Change language to Brazil Portuguese
		info("\n=== Change language to Brazil Portuguese ===");
		goToChangeLanguageForUserInterface();
		click(By.linkText("Portuguese - Brazil"));
		pause(1000);
		click(By.linkText("Apply"));
		pause(2000);
		waitForElementPresent(By.xpath("//p[contains(text(),'"+NewFreeLayoutWebContentBrCont+"')]"));
		info("\n=== Brazil Portuguese content appears correctly ! ===");
		
		// Change language to English
		info("\n=== Change language to English ===");
		goToChangeLanguageForUserInterface();
		click(By.xpath("//div[contains(text(),'English')]"));
		pause(1000);
		click(By.linkText("Aplicar"));
		pause(4000);
		
		// Delete page Test_REG_PLF305_ECMS_001 in Page Management portlet
		info("\n\n=== Delete page Test_REG_PLF305_ECMS_001 in Page Management portlet ===");
		goToManagePages();
		pause(4000);
		deletePage(PageType.PORTAL, NewPageName);
		info("\n=== Page Test_REG_PLF305_ECMS_001 is deleted in Page Management portlet ! ===");
		
		// Delete node in navigation
		info("\n\n=== Delete node Test_REG_PLF305_ECMS_001 in navigation ===");
		mouseOver(By.linkText("Edit"),true);
		pause(500);
		mouseOver(By.linkText("Site"),true);
		pause(500);
		click(By.linkText("Navigation"));
		pause(1000);
		rightClickOnElement(By.linkText(NewPageName));
		pause(500);
		click(By.linkText("Delete Node"));
		pause(1000);		
		
		// Click OK on alert popup to confirm delete action
		Alert alert = driver.switchTo().alert();
		alert.accept();
		pause(500);		
		save();		
		pause(4000);
		info("\n=== Node Test_REG_PLF305_ECMS_001 is removed from navigation ! ===");
		
		//Delete folder REG_PLF305_ECMS_001
		info("\n\n=== Delete folder REG_PLF305_ECMS_001 ===");
		info("\n=== Go Sites Explorer ===");
		goToSiteExplorer();
		pause(4000);
		info("\n=== Go to node acme ===");
		goToNode("acme");
		pause(4000);
		info("\n === Delete data in REG_PLF305_ECMS_001 folder ===");
		deleteData(By.xpath("//div[contains(text(),'"+ContentFolderTitle+"')]"));
		pause(5000);
		info("\n === Folder REG_PLF305_ECMS_001 is deleted successfully ! ===");
		pause(2000);
		
	} // End test01_AddBrazilianPortugueseTranslation

}
