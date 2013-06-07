package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*; 
import static org.exoplatform.selenium.platform.PageManagement.deletePageAtManagePageAndPortalNavigation;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
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
	private String NewFolderButton = "New Folder";
	private String FreeLayoutWebContentLink = "Free layout webcontent";
	
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
	
	private void goToIntranet(){
		info("\n=== Go to intranet ===");
		waitForElementPresent(By.xpath("//a[@class='ArrowIcon TBIcon' and contains(text(),'My Sites')]"));
		mouseOver(By.xpath("//a[@class='ArrowIcon TBIcon' and contains(text(),'My Sites')]"),true);
		pause(500);
		click(By.xpath("//a[contains(text(),'intranet')]"));
		waitForElementPresent(By.linkText("All Updates"));
	}
	
	private void unSelectCheckboxList(String viewList){
		String[] temp = viewList.split("/");
		if (temp.length != 0){
			for (int i=0; i < temp.length ; i++ ){
				if (waitForAndGetElement(By.id(temp[i])) != null){
					if(waitForAndGetElement(By.id(temp[i])).isSelected() == true){
						click(By.id(temp[i]));
						info("Uncheck checkbox with id " + temp[i]);
					}
				}else{
					info("Can not found checkbox with id " + temp[i]);
				}
			}
		}else{
			info("Input checkbox list wrong");
		}
	}
	
	private void removeView(String view, String tab, String viewadd ){
		goToContentAdministration();
		click(ELEMENT_CONTENT_PRESENT);
		click(ELEMENT_MANAGE_VIEW);
		click(By.xpath("//div[@title='" + view + "']/../..//*[@class='EditInfoIcon']"));
		click(By.xpath("//a[contains(text(),'" + tab + "')]"));
		unSelectCheckboxList(viewadd);
		save();
		save();
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
		waitForElementPresent(By.xpath("//div[@title='WCM View']"));
		
		// Go to Sites Explorer
		info("\n=== Go Sites Explorer ===");
		goToSiteExplorer();
		waitForElementPresent(By.xpath("//div[contains(text(),'Sites Management')]"));
		info("\n=== Go to node acme ===");
		goToNode("acme");
		waitForElementPresent(By.linkText(NewFolderButton));
		
		// Create new folder REG_PLF305_ECMS_001 
		info("\n === Create new folder REG_PLF305_ECMS_001 ===");
		createNewContentFolder(ContentFolderTitle, ContentFolderName);
		waitForElementPresent(By.linkText(ContentFolderTitle));
		// Go into new folder
		info("\n === Go into new folder REG_PLF305_ECMS_001 ===");
		goToNodeByPath(ContentFolderTitle);
		waitForElementPresent(By.linkText(NewFolderButton));
		
		// Create new Free layout webcontent, named "FLWC1en", in english 
		info("\n === Create new Free layout webcontent, named 'FLWC1en' ===");
		waitForElementPresent(By.linkText("New Content"));
		mouseOver(By.linkText("New Content"),false);
		goToAddNewContent();
		waitForElementPresent(By.linkText(FreeLayoutWebContentLink));
		createNewFreeLayoutWebContent(NewFreeLayoutWebContentEnTitle, NewFreeLayoutWebContentEnName, NewFreeLayoutWebContentEnCont, "", "", "", "");
		waitForElementPresent(By.xpath("//div[contains(text(),'Document View')]"));
		
		//Return to parent folder
		info("\n === Return to parent folder REG_PLF305_ECMS_001 ===");
		goToNodeByPath(ContentFolderTitle);
		waitForElementPresent(By.linkText(NewFolderButton));
		
		// Create new Free layout webcontent, named "FLWC1br", in brazil portuguese
		info("\n === Create new Free layout webcontent, named 'FLWC1br' ===");
		waitForElementPresent(By.linkText("New Content"));
		mouseOver(By.linkText("New Content"),false);
		goToAddNewContent();
		waitForElementPresent(By.linkText(FreeLayoutWebContentLink));
		createNewFreeLayoutWebContent(NewFreeLayoutWebContentBrTitle, NewFreeLayoutWebContentBrName, NewFreeLayoutWebContentBrCont, "", "", "", "");
		waitForElementPresent(By.xpath("//div[contains(text(),'Document View')]"));
		// Change language of FLWC1br to Brazil portuguese
		waitForElementPresent(By.xpath("//a[@class='SubTabIcon DefaultActionIcon EditDocumentIcon' and @title='Edit']"));
		click(By.xpath("//a[@class='SubTabIcon DefaultActionIcon EditDocumentIcon' and @title='Edit']"));
		waitForElementPresent(By.id("content-lang"));
		selectOption(By.id("content-lang"), "pt_BR");
		pause(1000);
		saveAndClose();
		waitForElementPresent(By.xpath("//div[contains(text(),'Document View')]"));
		info("\n === New Free layout webcontent 'FLWC1br' is created! ===");
		
		// Add translation for FLWC1en
		info("\n\n=== Add translation for FLWC1en ===");
		waitForElementPresent(By.linkText(NewFreeLayoutWebContentEnTitle));
		mouseOver(By.linkText(NewFreeLayoutWebContentEnTitle),false);
		goToNodeByPath(NewFreeLayoutWebContentEnTitle);
		waitForElementPresent(By.xpath("//div[contains(text(),'Document View')]"));
		click(By.linkText("Add Translation"));
		waitForElementPresent(ELEMENT_ADD_SYMLINK_POPUP);
		click(By.xpath("//img[@class='MultiFieldAction AddNewNodeIcon']"));
		waitForElementPresent(By.xpath("//a[@class='Item default16x16Icon exo_webContent16x16Icon' and contains(text(),'"+NewFreeLayoutWebContentBrTitle+"')]"));
		click(By.xpath("//a[@class='Item default16x16Icon exo_webContent16x16Icon' and contains(text(),'"+NewFreeLayoutWebContentBrTitle+"')]"));
		pause(1000);
		waitForElementPresent(By.linkText("Save"));
		save();
		waitForElementPresent(By.xpath("//div[contains(text(),'Document View')]"));
		info("\n === Translation for FLWC1en is added ! ===");
		
		// Publish FLWC1en
		info("\n\n=== Publish FLWC1en ===");
		waitForElementPresent(By.linkText("Publications"));
		mouseOver(By.linkText("Publications"),false);
		click(By.linkText("Publications"));
		waitForElementPresent(By.id("UIPopupWindow"));		
		waitForElementPresent(By.xpath("//a[contains(text(),'Published')]"));
		mouseOver(By.xpath("//a[contains(text(),'Published')]"),false);
		pause(2000);
		click(By.xpath("//a[contains(text(),'Published')]"));
		// Wait for publish task completed
		waitForElementPresent(By.xpath("//td[contains(text(),'Published')]"));
		save();
		waitForElementPresent(By.xpath("//div[contains(text(),'Document View')]"));
		info("\n=== FLWC1en published ! ===");
		
		
		// Publish FLWC1br
		info("\n\n=== Publish FLWC1br ===");
		info("\n=== Go to node FLWC1br ===");
		goToNodeByPath(NewFreeLayoutWebContentBrTitle);
		waitForElementPresent(By.xpath("//p[contains(text(),'"+NewFreeLayoutWebContentBrCont+"')]"));
		info("\n=== Click on Publications tab ===");
		waitForElementPresent(By.linkText("Publications"));
		click(By.linkText("Publications"));
		info("\n=== Click on Published ===");
		waitForElementPresent(By.xpath("//a[contains(text(),'Published')]"));
		click(By.xpath("//a[contains(text(),'Published')]"));
		// Wait for publish task completed
		waitForElementPresent(By.xpath("//td[contains(text(),'Published')]"));
		info("\n=== Click Save ===");
		save();		
		waitForElementPresent(By.xpath("//p[contains(text(),'"+NewFreeLayoutWebContentBrCont+"')]"));
		info("\n=== FLWC1br published ! ===");	
		
				
		// ==== Create a new SCV page ====
		info("\n\n=== Create a new SCV page ===");
		goToIntranet();
		
		// === Add a new page Test ===
		// Mouser over Edit > Page and click Add Page
		info("\n=== Mouser over Edit > Page and click Add Page ===");
		goToAddPageEditor();
		
		// Fill in page name
		info("\n=== Fill in page name ===");
		// Wait Node Name appears
		waitForElementPresent(By.id("pageName"));
		type(By.id("pageName"),NewPageName,false);
		pause(1000);
		// Pass to second step
		info("\n=== Pass to second step ===");
		next();
		waitForElementPresent(By.linkText("Next"));
		// Pass to third step
		info("\n=== Pass to third step ===");
		next();
		pause(4000);
		
		// ==== Add new SCV portlet ====
		info("\n\n=== Add new SCV portlet ===");
		waitForElementPresent(By.xpath("//a[@title='Content']"));
		click(By.xpath("//a[@title='Content']"));
		waitForElementPresent(By.id("Content/SingleContentViewer"));		
		mouseOver(By.id("Content/SingleContentViewer"),true);
		pause(1000);
		click(By.id("Content/SingleContentViewer"));
		pause(1000);
		dragAndDropToObject("//div[@id='Content/SingleContentViewer']//img", "//div[@id='UIPage']");
		
		// Select FLWC1en node in Content Path
		info("\n=== Select FLWC1en node in Content Path ===");
		waitForElementPresent(ELEMENT_FRAME_CONTAIN_PORTLET);
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET,true);	
		waitForElementPresent(ELEMENT_EDIT_PORTLET_ICON);
		click(ELEMENT_EDIT_PORTLET_ICON);
		waitForElementPresent(ELEMENT_SELECT_CONTENT_PATH_LINK);
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		waitForElementPresent(By.xpath("//a[contains(text(),'General Drives')]"));
		click(By.xpath("//a[contains(text(),'General Drives')]"));
		waitForElementPresent(By.xpath("//a[contains(text(),'Sites Management')]"));
		click(By.xpath("//a[contains(text(),'Sites Management')]"));
		waitForElementPresent(By.linkText("acme"));
		click(By.linkText("acme"));
		waitForElementPresent(By.xpath("//a[contains(text(),'"+ContentFolderTitle+"')]"));
		click(By.xpath("//a[contains(text(),'"+ContentFolderTitle+"')]"));
		waitForElementPresent(By.xpath("//div[@id='RightWorkspace']/div/div/table/thead/tr/td/a[contains(text(),'"+NewFreeLayoutWebContentEnTitle+"')]"));
		click(By.xpath("//div[@id='RightWorkspace']/div/div/table/thead/tr/td/a[contains(text(),'"+NewFreeLayoutWebContentEnTitle+"')]"));		
		waitForElementPresent(By.linkText("Save"));
		// Save and close edit portlet popup
		info("\n=== Save and close edit portlet popup ===");
		save();
		pause(1000);
		close();
		waitForElementPresent(By.xpath("//a[@class='EdittedSaveButton' and @title='Finish']"));
		info("\n=== Click Finish button to close edit mode ===");
		click(By.xpath("//a[@class='EdittedSaveButton' and @title='Finish']"));
		waitForElementPresent(By.xpath("//p[contains(text(),'"+NewFreeLayoutWebContentEnCont+"')]"));
		pause(4000);
		
		// ==== END Add new SCV portlet ====
		
		// Change language to Brazil Portuguese
		info("\n\n=== Change language to Brazil Portuguese ===");
		goToChangeLanguageForUserInterface();
		waitForElementPresent(By.linkText("Portuguese - Brazil"));
		click(By.linkText("Portuguese - Brazil"));
		waitForElementPresent(By.linkText("Apply"));
		click(By.linkText("Apply"));
		waitForElementPresent(By.xpath("//p[contains(text(),'"+NewFreeLayoutWebContentBrCont+"')]"));
		info("\n=== Brazil Portuguese content appears correctly ! ===");
		pause(4000);
		
		// Change language to English
		info("\n\n=== Change language to English ===");
		goToChangeLanguageForUserInterface();
		waitForElementPresent(By.xpath("//div[contains(text(),'English')]"));
		click(By.xpath("//div[contains(text(),'English')]"));
		waitForElementPresent(By.linkText("Aplicar"));		
		click(By.linkText("Aplicar"));
		waitForElementPresent(By.xpath("//p[contains(text(),'"+NewFreeLayoutWebContentEnCont+"')]"));
		
		// Delete node in navigation & Page Management
		deletePageAtManagePageAndPortalNavigation(NewPageName, true, "intranet", false, null);						
		
		//Delete folder REG_PLF305_ECMS_001
		info("\n\n=== Delete folder REG_PLF305_ECMS_001 ===");
		info("\n=== Go Sites Explorer ===");
		goToSiteExplorer();
		waitForElementPresent(By.linkText("acme"));
		info("\n=== Go to node acme ===");
		goToNode("acme");
		waitForElementPresent(By.xpath("//div[contains(text(),'"+ContentFolderTitle+"')]"));
		info("\n === Delete data in REG_PLF305_ECMS_001 folder ===");
		deleteData(By.xpath("//div[contains(text(),'"+ContentFolderTitle+"')]"));
		waitForElementNotPresent(By.xpath("//div[contains(text(),'"+ContentFolderTitle+"')]"));
		info("\n === Folder REG_PLF305_ECMS_001 is deleted successfully ! ===");
		pause(2000);
		
		// Remove Add Translation in Public tab of WCM view
		info("\n\n=== Remove Add Translation in Public tab of WCM view ===");
		removeView("WCM View", "Publication", "addLocalizationLink");
		
	} // End test01_AddBrazilianPortugueseTranslation

}
