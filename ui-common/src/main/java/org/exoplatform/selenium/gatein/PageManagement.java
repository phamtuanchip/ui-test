package org.exoplatform.selenium.gatein;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.gatein.NavigationManagement.deleteNode;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PageManagement extends GateInBase {
	/*
	 * Page Management
	 * */
	public static By ELEMENT_ADD_PAGE_BUTTON = By.linkText("Add New Page");
	public static String ELEMENT_INPUT_SEARCH_TITLE = "//input[@id='pageTitle']";
	public static String ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON = "//form[@id='UIPageSearchForm']/div[2]/a[@class='SearchIcon']";
	public static String ELEMENT_PAGE_EDIT_ICON = "//div[@id='UIVirtualList']//table//tr/td/div[contains(@title, '${page}')]/../../td[5]//img[@class='EditInfoIcon']";
	public static String ELEMENT_PAGE_DELETE_ICON = "//div[@id='UIVirtualList']//table//tr/td/div[contains(@title, '${page}')]/../../td[5]//img[@class='DeleteIcon']";

	//Add New Page Form (shown after click [Add New Page] button in Page Management)
	public static By ELEMENT_PAGE_NAME_INPUT = By.xpath("//input[@id='name']");
	public static By ELEMENT_PAGE_TITLE_INPUT = By.xpath("//input[@id='title']");
	public static String ELEMENT_SELECT_OWNER_TYPE = "//select[@name='ownerType']";
	public static By ELEMENT_OWNER_ID_CLASSIC = By.xpath("//input[@id='ownerId' and @value='classic']");
	public static By ELEMENT_OWNER_ID_GROUP = By.xpath("//select[@name='ownerId']");
		
	//Message
	public static String MESSAGE_DELETE_PAGE = "Do you want to delete this page?";
	
	//Add a new page in PageManagement
	public static void addNewPageAtManagePages(PageType type, String pageName, String pageTitle, boolean publicMode, 
			Map<String, String> permissions, String groupId, String membership ){

		click(By.linkText("Add New Page"));
		waitForTextPresent("Page Setting");	
		switch (type){
		case PORTAL:
			select(ELEMENT_SELECT_OWNER_TYPE, "portal");
			break;
		case GROUP:	
			select(ELEMENT_SELECT_OWNER_TYPE, "group");
			waitForElementPresent(ELEMENT_OWNER_ID_GROUP);
			break;
		default:
			break;
		}
		type(ELEMENT_PAGE_NAME_INPUT, pageName, true);
		type(ELEMENT_PAGE_TITLE_INPUT, pageTitle, true);		

		//showMaxWindow
		check(ELEMENT_CHECKBOX_MAX_WINDOWS);
		if (waitForAndGetElement(ELEMENT_CHECKBOX_MAX_WINDOWS).isSelected())
		click(ELEMENT_PERMISSION_SETTING_TAB);	
		WebElement element = waitForAndGetElement(ELEMENT_CHECKBOX_PUBLIC_MODE);		
		if (publicMode & !element.isSelected()) {
			waitForAndGetElement(ELEMENT_ADD_PERMISSION_BUTTON);
			check(ELEMENT_CHECKBOX_PUBLIC_MODE);
			waitForElementNotPresent(ELEMENT_ADD_PERMISSION_BUTTON);
		} else if (publicMode & element.isSelected()){
			waitForElementNotPresent(ELEMENT_ADD_PERMISSION_BUTTON);
		} else {
			for (String key : permissions.keySet()) {
				setViewPermissions(key, permissions.get(key));
			}
		}		
		click(ELEMENT_EDIT_PERMISSION_SETTING);
		setEditPermissions(groupId, membership);
		save();
		pause(1000);
		searchPageByTitle(type, pageTitle);
	}

	//Edit a page at Manage Pages
	public static void editPageAtManagePages(PageType type, String pageTitle){
		String pageEditIcon = ELEMENT_PAGE_EDIT_ICON.replace("${page}", pageTitle);
		searchPageByTitle(type, pageTitle);
		click(pageEditIcon);
		pause(1000);
	}

	//Delete a page
	public static void deletePage(PageType type, String pageTitle, int...wait){
		int waitTime = wait.length > 0 ? wait[0] : DEFAULT_TIMEOUT;
		String pageDeleteIcon = ELEMENT_PAGE_DELETE_ICON.replace("${page}", pageTitle);
		searchPageByTitle(type, pageTitle);
		click(pageDeleteIcon);
		waitForConfirmation(MESSAGE_DELETE_PAGE);
		waitForMessage("No result found.",waitTime);
		closeMessageDialog();
		waitForTextNotPresent(pageTitle);
	}

	// Search a page in Manage Pages
	public static void searchPageByTitle(PageType type, String pageTitle){
		type(ELEMENT_INPUT_SEARCH_TITLE, pageTitle, true);
		switch (type){
		case PORTAL:
			select(ELEMENT_SELECT_SEARCH_OPTION, "portal");
			break;
		case GROUP:
			select(ELEMENT_SELECT_SEARCH_OPTION, "group");
			break;
		default:
			break;
		}
		click(ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON);
		pause(1000);
		waitForTextPresent(pageTitle);
	}

	// Input data for page
	public static void addNewPageEditor(String nodeName, String displayName, String language, String categoryTitle, 
			Map<String, String> portletIds, boolean extendedLabelMode){

		type(ELEMENT_INPUT_NODE_NAME, nodeName, true);
		WebElement element = waitForAndGetElement(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE);
		if (extendedLabelMode){
			Assert.assertTrue(element.isSelected());
			select(ELEMENT_SELECT_LANGUAGE, language);

		}else {
			uncheck(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE);
			type(ELEMENT_INPUT_PAGE_DISPLAY_NAME, displayName, true);
		}

		click(ELEMENT_PAGE_EDITOR_NEXT_STEP);
		waitForTextPresent("Empty Layout");
		click(ELEMENT_PAGE_EDITOR_NEXT_STEP);

		String category = ELEMENT_EDIT_PAGE_CATEGORY_MENU.replace("${categoryLabel}", categoryTitle);
		click(category);

		for (String portletId : portletIds.keySet()) {
			String elementEditPagePage = ELEMENT_EDIT_PAGE_PAGE;
			//String verification = PORTLET_LABEL.replace("${portletName}", portletIdsAndVerifications.get(portletId));
			dragAndDropToObject("//div[@id='" + portletId + "']//img", elementEditPagePage);

			if(portletIds.get(portletId) != ""){
				dragAndDropToObject("//div[@id='" + portletIds.get(portletId) + "']//img", elementEditPagePage);
			}
		}
		pause(500);
		click(ELEMENT_PAGE_FINISH_BUTTON);
		waitForTextNotPresent("Page Editor");
	}

	public static void deletePageAtManagePageAndPortalNavigation(String pageName, boolean PageTypePortal, String portalName, 
			boolean PageTypeGroup, String groupName){
		info("-- Deleting "+ pageName +" at Manage page and Portal Navigation--");
		goToManagePages();
		if (PageTypePortal){
			deletePage(PageType.PORTAL, pageName);
			//delete page at Portal navigation
			goToPortalSites();
			deleteNode(portalName, "", pageName, true);
		}
		if (PageTypeGroup){
			deletePage(PageType.GROUP, pageName);
			//delete page at Portal/Group navigation
			goToGroupSites();
			deleteNode(groupName, "", pageName, true);
		}
	}
}
