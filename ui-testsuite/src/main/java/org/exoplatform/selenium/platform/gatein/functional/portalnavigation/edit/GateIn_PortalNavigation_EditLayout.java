package org.exoplatform.selenium.platform.gatein.functional.portalnavigation.edit;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;
import static org.exoplatform.selenium.gatein.PortalManagement.*;

public class GateIn_PortalNavigation_EditLayout extends GateInBase{

	//Container tab in Edit layout
	public static final By ELEMENT_TAB_CONTAINERS = By.xpath("//div[contains(text(),'Containers') and @class='MiddleTab']");
	public static final By ELEMENT_ROWS_LAYOUT = By.linkText("Rows Layout");
	public static final By ELEMENT_ONE_ROW_LAYOUT = By.id("oneRow");
	
	//Application Tab in Edit Layout
	public static final By ELEMENT_TAB_APPLICATIONS = By.xpath("//div[contains(text(),'Applications') and @class='MiddleTab']");
	public static final By ELEMENT_APPLICATION_CONTENT_LIST = By.id("Content/ContentListViewerPortlet");
	public static final By ELEMENT_APPLICATION_NEW_ACCOUNT = By.xpath("//div[@title='New Account']");
	
	public static final By ELEMENT_PORTAL_PAGE_COMPONENT = By.id("UIPageBody");
	
	public static final By ELEMENT_DELETE_CONTAINER_ICON = By.xpath("//a[contains(@title,'Delete Container')]");
	public static final By ELEMENT_EDIT_CONTAINER_ICON = By.xpath("//a[@title='Edit Container']");
	
	public static final By ELEMENT_INPUT_WIDTH = By.id("width");
	public static final By ELEMENT_INPUT_HEIGHT = By.id("height");
	public static final By ELEMENT_CONFIRMATION = By.id("UIConfirmation");
	public static final By ELEMENT_CONFIRMATION_YES_OPTION = By.xpath("//div[@id='UIConfirmation']//div[contains(@class, 'UIAction')]//a[contains(text(), 'Yes')]");
			
	public static final By ELEMENT_PAGE_BODY = By.xpath(".//*[@id='UIPageBody']/div/div[1]/div");
	public static final By ELEMENT_BLANK_CONTAINER = By.xpath("//div[@class='UIRowContainer EmptyContainer']");
	public static final By ELEMENT_EMPTY_CONTAINER = By.cssSelector("div.UIRowContainer.EmptyContainer");
	public static final String ELEMENT_EDIT_LAYOUT = "//div[@class='Label' and text()='${navigation}']/../../td[3]//a[@class='EditLayoutIcon']";
	public static final String ELEMENT_EDIT_PAGE_COMPONENT_DRAG_ICON = "//div[@class='UIRowContainer']/div[${number}]//div[@class='DragControlArea']";
	public static final String ELEMENT_EDIT_PAGE_COMPONENT = "//div[@class='UIRowContainer']/div[${portletNumber}]/div";
	public static final String ELEMENT_EDIT_PAGE_COMPONENT_FIRST = ELEMENT_EDIT_PAGE_COMPONENT.replace("${portletNumber}", "1");
	public static final String EMPTY_CONTAINER = ELEMENT_EDIT_PAGE_COMPONENT + "//div[@class='UIRowContainer EmptyContainer']";
	public static final String ELEMENT_EMPTY_CONTAINER_2 = EMPTY_CONTAINER.replace("${portletNumber}", "2");
	public static final String APPLICATION_DRAG_ICON = ELEMENT_EDIT_PAGE_COMPONENT_DRAG_ICON.replace("${number}", "1");
	public static final String ELEMENT_EDIT_CONTAINER = ELEMENT_EDIT_PAGE_COMPONENT.replace("${portletNumber}", "1");
	public static final By ELEMENT_CONTAINER_TITLE = By.xpath("//input[@id='title']");
	public static final By ELEMENT_APPLICATION_ACCOUNT = By.xpath("//div[contains(text(),'Account Portlet')]");
	public static final By ELEMENT_APPLICATION_ACCOUNT_DRAG = By.xpath("//div[contains(text(),'Account Portlet')]/../div[@class='DragControlArea']");
	
	public static final String ELEMENT_CONTENT_CATEGORY = ELEMENT_EDIT_PAGE_CATEGORY_MENU.replace("${categoryLabel}", "Content");
	public static final String ELEMENT_ADMIN_CATEGORY = ELEMENT_EDIT_PAGE_CATEGORY_MENU.replace("${categoryLabel}", "Administration");

	public static final String ELEMENT_CLASSIC_NAVIGATION = "classic";
	
	
	// This locator is different from Platform Base Save & Close
	public static final By ELEMENT_SAVE_CLOSE_BUTTON = By.linkText("Save And Close");
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);		
	}

	@AfterMethod
	public void afterTest(){
		driver.quit();
	}

	/*--Case 050 Portal\Portal Navigation\Edit\Edit Layout 
	 *  Edit current portal with no one for edit right
	 * --*/
	@Test
	public void test07_EditCurrentPortalWithNoOneForEditRight(){

		signIn("root", "gtn");

		goToPortalSites();

		info("-- Edit layout of current Portal --");
		String editLayout = ELEMENT_EDIT_LAYOUT.replace("${navigation}", ELEMENT_CLASSIC_NAVIGATION);
		click(editLayout);

		info("-- Click on Site's Config button --");
		click(ELEMENT_SITE_CONFIG_LINK);
		click(ELEMENT_PERMISSION_SETTING_TAB);

		info("-- Delete Permission --");
		click(ELEMENT_EDIT_PERMISSION_SETTING);
		click(ELELENT_LINK_DELETE_PERMISSION);

		save();

		waitForMessage(MESSAGE_EDIT_EMPTY_PERMISSION_SETTING);
		closeMessageDialog();
		cancel();

		click(ELEMENT_ABORTEDIT_BUTTON);
		waitForTextPresent(ELEMENT_CLASSIC_NAVIGATION);
		signOut();				
	}


	/*--Case 060 Portal\Portal Navigation\Edit\Edit Layout 
	 *  Check when drag & drop Page Body in edit current portal
	 * --*/
	@Test
	public void test17_CheckWhenDragAndDropPageBodyInEditCurrentPortal(){		

		signIn("root", "gtn");

		goToPortalSites();

		info("--Edit layout of current Portal--");
		String editLayout = ELEMENT_EDIT_LAYOUT.replace("${navigation}", ELEMENT_CLASSIC_NAVIGATION);
		click(editLayout);

		waitForTextPresent("Applications");
		click(ELEMENT_TAB_CONTAINERS);
		waitForAndGetElement(ELEMENT_ROWS_LAYOUT);
		click(ELEMENT_ROWS_LAYOUT);

		info("--Move PageBody to the new palce--");
		dragAndDropToObject(ELEMENT_ONE_ROW_LAYOUT, ELEMENT_PORTAL_PAGE_COMPONENT);
		click(ELEMENT_TAB_APPLICATIONS); 
		waitForTextPresent("Administration");
		mouseOver(ELEMENT_PAGE_BODY, true);
		dragAndDropToObject(ELEMENT_PAGE_BODY, ELEMENT_EMPTY_CONTAINER);

		info("--Switch view mode portal--");
		click(ELEMENT_SWITCH_VIEW_MODE_PORTAL);
		waitForTextPresent("Applications");
		click(ELEMENT_SWITCH_VIEW_MODE_PORTAL);

		info("--Check when delete component contains Page Body--");
		click(ELEMENT_TAB_CONTAINERS);
		mouseOver(ELEMENT_PAGE_BODY, true);
		mouseOver(ELEMENT_DELETE_CONTAINER_ICON, true);
		click(ELEMENT_DELETE_CONTAINER_ICON);

		waitForConfirmation(MESSAGE_DELETE_CONTAINER);
		waitForTextPresent(MESSAGE_WARNING_CONTAINER);
		closeMessageDialog();

		info("--SignOut--");
		click(ELEMENT_ABORTEDIT_BUTTON);
		waitForAndGetElement(ELEMENT_CONFIRMATION);
		waitForTextPresent(MESSAGE_QUIT_EDIT_LAYOUT);
		waitForAndGetElement(ELEMENT_CONFIRMATION_YES_OPTION);
		click(ELEMENT_CONFIRMATION_YES_OPTION);
		waitForTextNotPresent(MESSAGE_QUIT_EDIT_LAYOUT);

		signOut();		
	}	

	/*--Case 062 Portal\Portal Navigation\Edit\Edit Layout
	 * Check Finish function after changing container layout
	 * --*/
	@Test
	public void test19_CheckFinishFunctionAfterChangingContainerLayout(){

		signIn("root", "gtn");
		String username = "Root Root";
		waitForTextPresent(username);

		info("--View layout of portal before changing container in portal--");
		captureScreen("case062_BeforeChange");

		info("--Edit layout of current Portal--");
		goToPortalSites();
		String editLayout = ELEMENT_EDIT_LAYOUT.replace("${navigation}", ELEMENT_CLASSIC_NAVIGATION);
		click(editLayout);

		info("--Select Container tab on editting inline composer--");
		waitForTextPresent("Applications");
		click(ELEMENT_TAB_CONTAINERS);
		waitForAndGetElement(ELEMENT_ROWS_LAYOUT);
		click(ELEMENT_ROWS_LAYOUT);	
		dragAndDropToObject(ELEMENT_ONE_ROW_LAYOUT, ELEMENT_PORTAL_PAGE_COMPONENT);

		info("--Edit container layout of current portal--");
		click(ELEMENT_TAB_CONTAINERS);
		mouseOver(ELEMENT_BLANK_CONTAINER, true);
		mouseOver(ELEMENT_EDIT_CONTAINER_ICON, true);
		click(ELEMENT_EDIT_CONTAINER_ICON);

		waitForTextPresent("Container Setting");	
		type(ELEMENT_INPUT_WIDTH, "900px", true);
		type(ELEMENT_INPUT_HEIGHT, "600px", true);
		save();
		waitForTextNotPresent("Container Settings");
		click(ELEMENT_EDIT_LAYOUT_FINISH_BUTTON);
		waitForTextPresent(username);
		signOut();

		info("--Verify that changes on container is saved--");
		driver.get(baseUrl);
		signIn("root", "gtn");
		waitForTextPresent(username);
		captureScreen("case062_AfterChange");

		info("--Delete data--");
		goToPortalSites();
		click(editLayout);
		click(ELEMENT_TAB_CONTAINERS) ;
		mouseOver(ELEMENT_BLANK_CONTAINER, true);
		mouseOver(ELEMENT_DELETE_CONTAINER_ICON, true);
		click(ELEMENT_DELETE_CONTAINER_ICON);
		waitForConfirmation(MESSAGE_DELETE_CONTAINER);

		info("--SignOut--");
		click(ELEMENT_EDIT_LAYOUT_FINISH_BUTTON);
		waitForTextPresent(username);
		signOut();		
	}

	/*--Case 064 Portal\Portal Navigation\Edit\Edit Layout
	 * Check editing container with new valid title while edit current portal
	 * --*/
	@Test
	public void test21_CheckEditingContainerWithNewValidTitleWhileEditCurrentPorta(){

		signIn("root", "gtn");

		goToPortalSites();

		info("--Edit layout of current Portal--");
		String editLayout = ELEMENT_EDIT_LAYOUT.replace("${navigation}", ELEMENT_CLASSIC_NAVIGATION);
		click(editLayout);

		info("--Select Container tab on editing inline composer--");
		waitForTextPresent("Applications");
		click(ELEMENT_TAB_CONTAINERS);
		waitForAndGetElement(ELEMENT_ROWS_LAYOUT);
		click(ELEMENT_ROWS_LAYOUT);	
		dragAndDropToObject(ELEMENT_ONE_ROW_LAYOUT, ELEMENT_PORTAL_PAGE_COMPONENT);

		info("--Edit container layout of current portal--");
		click(ELEMENT_TAB_CONTAINERS);
		mouseOver(ELEMENT_BLANK_CONTAINER, true);
		mouseOver(ELEMENT_EDIT_CONTAINER_ICON, true);
		click(ELEMENT_EDIT_CONTAINER_ICON);

		info("--Edit current title with valid value--");
		waitForTextPresent("Container Setting");
		type(ELEMENT_CONTAINER_TITLE, "test21_CheckEditingContainer", true); //ELEMENT_INPUT_TITLE
		save();
		mouseOver(ELEMENT_BLANK_CONTAINER, true);
		waitForTextPresent("test21_CheckEditingContainer");

		info("--SignOut--");
		click(ELEMENT_ABORTEDIT_BUTTON);
		waitForAndGetElement(ELEMENT_CONFIRMATION);
		waitForTextPresent(MESSAGE_QUIT_EDIT_LAYOUT);
		waitForAndGetElement(ELEMENT_CONFIRMATION_YES_OPTION);
		click(ELEMENT_CONFIRMATION_YES_OPTION);
		waitForTextNotPresent(MESSAGE_QUIT_EDIT_LAYOUT);

		signOut();			
	}

	/*--Case 069 Portal\Portal Navigation\Edit\Edit Layout
	 *  Check Finish function after editing container
	 * --*/
	@Test
	public void test26_CheckFinishFunctionAfterEditingContainer(){
		signIn("root", "gtn");
		String username = "Root Root";
		waitForTextPresent(username);

		info("--View layout of portal before changing container in portal--");
		captureScreen("case069_BeforeChange");

		info("--Edit layout of current Portal--");
		goToPortalSites();
		String editLayout = ELEMENT_EDIT_LAYOUT.replace("${navigation}", ELEMENT_CLASSIC_NAVIGATION);
		click(editLayout);

		info("--Select Container tab on editing inline composer--");
		waitForTextPresent("Applications");
		click(ELEMENT_TAB_CONTAINERS);
		waitForAndGetElement(ELEMENT_ROWS_LAYOUT);
		click(ELEMENT_ROWS_LAYOUT);	
		dragAndDropToObject(ELEMENT_ONE_ROW_LAYOUT, ELEMENT_PORTAL_PAGE_COMPONENT);
		/*click(ELEMENT_TAB_APPLICATIONS); 
		waitForTextPresent("Administration");
		click(ELEMENT_CONTENT_CATEGORY);
		dragAndDropToObject(ELEMENT_APPLICATION_CONTENT_LIST, ELEMENT_EMPTY_CONTAINER);*/

		info("--Edit container layout of current portal--");
		click(ELEMENT_TAB_CONTAINERS);
		mouseOver(ELEMENT_BLANK_CONTAINER, true);
		mouseOver(ELEMENT_EDIT_CONTAINER_ICON, true);
		click(ELEMENT_EDIT_CONTAINER_ICON);
		waitForTextPresent("Container Setting");	
		type(ELEMENT_INPUT_WIDTH, "900px", true);
		save();
		click(ELEMENT_EDIT_LAYOUT_FINISH_BUTTON);
		waitForTextPresent(username);
		signOut();

		info("--Verify that changes on container are saved--");
		driver.get(baseUrl)  ;
		signIn("root", "gtn");
		waitForTextPresent(username);
		captureScreen("case026_AfterChange");

		info("--Delete data--");
		goToPortalSites();
		click(editLayout);
		click(ELEMENT_TAB_CONTAINERS) ;
		mouseOver(ELEMENT_BLANK_CONTAINER, true);
		mouseOver(ELEMENT_DELETE_CONTAINER_ICON, true);
		click(ELEMENT_DELETE_CONTAINER_ICON);
		waitForConfirmation(MESSAGE_DELETE_CONTAINER);

		info("--Sign out--");
		click(ELEMENT_EDIT_LAYOUT_FINISH_BUTTON);
		waitForTextPresent(username);
		signOut();
	}

	/*--Case 075 Portal\Portal Navigation\Edit\Edit Layout
	 *  Check Finish function after changing portlet layout
	 * --*/
	@Test
	public void test32_CheckFinishFunctionAfterChangingPortletLayout(){
		signIn("root", "gtn");
		String username = "Root Root";
		waitForTextPresent(username);

		info("--View layout of portal before changing portlet layout--");
		captureScreen("case32_BeforeChange");

		info("--Edit layout of current Portal--");
		goToPortalSites();
		String editLayout = ELEMENT_EDIT_LAYOUT.replace("${navigation}", ELEMENT_CLASSIC_NAVIGATION);
		click(editLayout);

		info("--Select application tab on edit inline composer --");
		waitForTextPresent("Administration") ;
		click(ELEMENT_ADMIN_CATEGORY);
		dragAndDropToObject(ELEMENT_APPLICATION_NEW_ACCOUNT, ELEMENT_PORTAL_PAGE_COMPONENT);

		click(ELEMENT_TAB_CONTAINERS);
		waitForAndGetElement(ELEMENT_ROWS_LAYOUT);
		click(ELEMENT_ROWS_LAYOUT);	
		dragAndDropToObject(ELEMENT_ONE_ROW_LAYOUT, ELEMENT_PORTAL_PAGE_COMPONENT);

		click(ELEMENT_TAB_APPLICATIONS); 	
		mouseOver(ELEMENT_APPLICATION_ACCOUNT, true);
		dragAndDropToObject(ELEMENT_APPLICATION_ACCOUNT_DRAG, ELEMENT_BLANK_CONTAINER);

		click(ELEMENT_EDIT_LAYOUT_FINISH_BUTTON);
		waitForTextPresent(username);
		signOut();

		info("--Verify changes on container are saved--");	
		driver.get(baseUrl);
		signIn("root", "gtn");
		waitForTextPresent(username);
		captureScreen("case32_AfterChange");

		info("--Delete data--");	
		goToPortalSites();
		click(editLayout);
		click(ELEMENT_TAB_CONTAINERS) ;
		mouseOver(ELEMENT_APPLICATION_ACCOUNT, true);
		click(ELEMENT_DELETE_CONTAINER_ICON);
		waitForConfirmation(MESSAGE_DELETE_CONTAINER);

		info("--Sign out--");
		click(ELEMENT_EDIT_LAYOUT_FINISH_BUTTON);
		waitForTextPresent(username);
		signOut();		
	}

	/*--Case 080 Portal\Portal Navigation\Edit\Edit Layout
	 *  Check when change width/height of portlet with valid value while editing portal
	 * --*/
	@Test
	public void test37_CheckWhenChangeWidthHeightOfPortletWithValidValueWhileEditingPortal(){
		signIn("root", "gtn");
		String username = "Root Root";
		waitForTextPresent(username);

		info("--View layout of portal before changing portlet layout--");
		captureScreen("case080_BeforeChange");

		info("--Edit width/height of portlet of current Portal--");
		goToPortalSites();
		String editLayout = ELEMENT_EDIT_LAYOUT.replace("${navigation}", ELEMENT_CLASSIC_NAVIGATION);
		click(editLayout);

		waitForTextPresent("Administration") ;
		click(ELEMENT_ADMIN_CATEGORY);
		dragAndDropToObject(ELEMENT_APPLICATION_NEW_ACCOUNT, ELEMENT_PORTAL_PAGE_COMPONENT);
		mouseOver(ELEMENT_APPLICATION_ACCOUNT, true);
		mouseOver(ELEMENT_EDIT_PORTLET_ICON, true);
		click(ELEMENT_EDIT_PORTLET_ICON);

		info("--Edit width/height--");
		waitForTextPresent("Portlet Setting");
		type(ELEMENT_INPUT_WIDTH, "300px", true) ;
		type(ELEMENT_INPUT_HEIGHT, "300px", true);
		saveAndClose();
		waitForTextNotPresent("Portlet Setting");

		info("--Switch view mode portal--");
		click(ELEMENT_SWITCH_VIEW_MODE_PORTAL);
		waitForTextPresent("Applications");
		captureScreen("case37_EditWidthHeightOfPortlet");
		click(ELEMENT_SWITCH_VIEW_MODE_PORTAL);

		info("--Change width/height of portlet with blank while editing portal--");
		mouseOver(ELEMENT_EDIT_PAGE_COMPONENT_FIRST, true);
		mouseOver(ELEMENT_EDIT_PORTLET_ICON, true);
		click(ELEMENT_EDIT_PORTLET_ICON);
		waitForTextPresent("Window Setting");
		type(ELEMENT_INPUT_WIDTH, "", true);
		type(ELEMENT_INPUT_HEIGHT,"", true);
		saveAndClose();

		info("--Switch to view mode portal--");
		click(ELEMENT_SWITCH_VIEW_MODE_PORTAL);
		waitForTextPresent("Applications");
		captureScreen("case37_DefaultWidthHeight");	
		click(ELEMENT_SWITCH_VIEW_MODE_PORTAL);

		info("--SignOut--");
		click(ELEMENT_ABORTEDIT_BUTTON);
		waitForAndGetElement(ELEMENT_CONFIRMATION);
		waitForTextPresent(MESSAGE_QUIT_EDIT_LAYOUT);
		waitForAndGetElement(ELEMENT_CONFIRMATION_YES_OPTION);
		click(ELEMENT_CONFIRMATION_YES_OPTION);
		waitForTextNotPresent(MESSAGE_QUIT_EDIT_LAYOUT);

		signOut();	
	}

	/*--Case 092 Portal\Portal Navigation\Edit\Edit Layout
	 *  Check Finish function after editing portlet
	 * --*/
	@Test
	public void test49_CheckFinishFunctionAfterEditedPortlet(){
		signIn("root", "gtn");
		String username = "Root Root";
		waitForTextPresent(username);

		info("--View layout of portal before changing portlet layout--");
		captureScreen("case49_BeforeChange");

		info("--Edit width/height of portlet of current Portal--");
		goToPortalSites();
		String editLayout = ELEMENT_EDIT_LAYOUT.replace("${navigation}", ELEMENT_CLASSIC_NAVIGATION);
		click(editLayout);

		waitForTextPresent("Administration") ;
		click(ELEMENT_COLLABORATION_CATEGORY);
		dragAndDropToObject(ELEMENT_APPLICATION_COLLABORATION_CALENDAR, ELEMENT_PORTAL_PAGE_COMPONENT);
		mouseOver(ELEMENT_EDIT_PAGE_COMPONENT_FIRST, true);
		mouseOver(ELEMENT_EDIT_PORTLET_ICON, true);
		click(ELEMENT_EDIT_PORTLET_ICON);

		info("--Edit width/height--");
		waitForTextPresent("Window Settings");
		type(ELEMENT_INPUT_WIDTH, "300px", true) ;
		type(ELEMENT_INPUT_HEIGHT, "300px", true);
		saveAndClose();
		waitForTextNotPresent("Window Settings") ;	
		click(ELEMENT_EDIT_LAYOUT_FINISH_BUTTON) ;
		waitForTextPresent(username);
		signOut();

		info("--Verify changes on portlet are saved--");	
		driver.get(baseUrl);
		signIn("root", "gtn");
		waitForTextPresent(username);
		captureScreen("case49_AfterChange");

		info("--Delete portlet--");	
		goToPortalSites();
		click(editLayout);
		waitForTextPresent("Applications") ;
		mouseOver(ELEMENT_EDIT_PAGE_COMPONENT_FIRST, true);
		click(ELEMENT_DELETE_PORTLET_ICON);
		waitForConfirmation(MESSAGE_DELETE_PORTLET);

		info("--Sign out--");
		click(ELEMENT_EDIT_LAYOUT_FINISH_BUTTON);
		waitForTextPresent(username);
		signOut();			
	}

	public static void saveAndClose(){
		waitForAndGetElement(ELEMENT_SAVE_CLOSE_BUTTON);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}
}