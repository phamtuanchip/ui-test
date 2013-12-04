package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.Map;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;

public class GroupNavigation extends PlatformBase {
	public Button button;
	ManageAlert alert;
	PageManagement pageMag;

	//Manage Group Navigation screen
	public final By ELEMENT_MANAGE_NAVIGATION_POPUP = By.xpath("//*[text()='Navigation Management']");
	public final String ELEMENT_GROUP_NAME = "//*[@class='siteName' and text()='${groupName}']";
	public final String ELEMENT_DELETE_NAVIGATION_ICON = "//*[text()='${groupName}']/../..//*[text()='Delete Navigation']";
	public final String ELEMENT_EDIT_PROPERTIES_ICON = "//*[text()='${groupName}']/../..//*[text()='Edit Properties']";
	//public final String ELEMENT_EDIT_NAVIGATION_ICON = "//*[text()='${groupName}']/../..//*[text()='Edit Navigation']";
	public final String ELEMENT_NAVIGATION_NODE = "//a[contains(text(),'${nodeName}')]"; 
	public final String ELEMENT_GROUP_TITLE = "//div[@title='${groupTitle}']"; 
	public final String ELEMENT_GROUP_NAVIGATION_POSITION = "//*[@id='UIGroupNavigationGrid']/table[${index}]/tbody/tr[${number}]" + ELEMENT_GROUP_TITLE;

	public final By ELEMENT_GROUP_ADD_NAVIGATION_BUTTON = By.linkText("Add Navigation");

	//Click on [Edit Properties] icon
	public final By ELEMENT_GROUP_NAVIGATION_PRIORITY = By.name("priority");
	public final By ELEMENT_GROUP_NAVIGATION_OWNER_TYPE = By.id("ownerType");
	public final By ELEMENT_GROUP_NAVIGATION_OWNER_ID = By.id("ownerId");

	//Add Navigation for group screen
	public final String ELEMENT_GROUP_SELECT_ADD_NAVIGATION = "//*[contains(text(), '${groupName}')]/..//*[text()='Add Navigation']";

	//Home Page/Left Panel/Group Navigation
	public final String ELEMENT_NODE_NAVIGATION_LEFT_PANEL = "//*[@class='groupNavigation']//*[contains(text(), '${groupName}')]";
	public final String ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL = ELEMENT_NODE_NAVIGATION_LEFT_PANEL.replace("${groupName}", "${groupName}") + "/../*[contains(@class, 'arrowIcon')]";

	/*======================== Common Function ===========================*/

	//Add a node for group at group navigation
	public void addNodeForGroup(String currentNavigation, String currentNodeLabel, boolean useAddNodeLink, String nodeName, boolean extendedLabelMode, 
			Map<String, String> languages, String nodeLabel, String pageName, String pageTitle, boolean verifyPage, boolean verifyNode){
		button = new Button(driver);
		//String node = ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeLabel);
		String currentNode="";
		if(currentNodeLabel!="")
			currentNode = ELEMENT_NODE_LINK.replace("${nodeLabel}", currentNodeLabel);
		else
			currentNode = "//*[@class='uiIconUpLevel uiIconLightGray']";
		editNavigation(currentNavigation);
		info("-- Add a new node for portal/group navigation --");		
		if (useAddNodeLink){
			click(currentNode);
			click(ELEMENT_ADD_NODE_LINK);
		}else{
			click(currentNode);
			Utils.pause(500);
			rightClickOnElement(currentNode);
			if (currentNode.equals(ELEMENT_NAVIGATION_HOME_NODE)) {
				click(ELEMENT_NODE_ADD_NEW_TOP_NODE);
			} else {
				click(ELEMENT_NODE_ADD_NEW);
			}		
		}
		//waitForTextPresent("Page Node");
		type(ELEMENT_INPUT_NAME, nodeName, true);

		if (extendedLabelMode) {
			for (String language : languages.keySet()) {
				select(ELEMENT_SELECT_LANGUAGE, language);
				Utils.pause(500);
			}
		} else {
			uncheck(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE);
			type(ELEMENT_INPUT_LABEL, nodeLabel, true);
		}

		click(ELEMENT_PAGE_SELECTOR_TAB);

		if (pageName != null & pageTitle != null) {
			info("--Create new page");
			type(ELEMENT_INPUT_PAGE_NAME, pageName, true);
			type(ELEMENT_INPUT_PAGE_TITLE, pageTitle, true);
			click(ELEMENT_CREATE_PAGE_LINK);
			if (verifyPage) {
				//waitForTextPresent(pageName);
				waitForElementNotPresent(ELEMENT_CREATE_PAGE_LINK);
			} else {
				return;
			}
		} else {
			//info("-- Select Page --");
			Utils.pause(500);
			click(ELEMENT_SEARCH_SELECTOR_PAGE_LINK);
			click(ELEMENT_SELECT_HOME_PAGE);
		}

		info("-- Save to add node for portal/group --");
		Utils.pause(1000);
		button.save();
		if (verifyNode) {
			waitForAndGetElement(ELEMENT_NAVIGATION_NODE.replace("${nodeName}", nodeName));
			button.save();
			waitForElementNotPresent(ELEMENT_MANAGE_NAVIGATION_POPUP);
		}
	}

	//Edit node in group navigation
	public void editNodeInGroupNavigation(String groupName, String nodeName, Object...params){
		pageMag = new PageManagement(driver);
		button = new Button(driver);
		String nodeLabel = (String) (params.length > 0 ? params[0]: "");
		String pageTitle = (String) (params.length > 1 ? params[1]: "");
		String option = (String) (params.length > 2 ? params[2]: "portal");
		info("Edit node for group: " + groupName);
		editNavigation(groupName);
		rightClickOnElement(ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeName));
		click(ELEMENT_EDIT_SELECTED_NODE);

		if(!nodeLabel.isEmpty()){
			if (waitForAndGetElement(ELEMENT_INPUT_LABEL, 5000, 0) != null){
				type(ELEMENT_INPUT_LABEL, nodeLabel, true);
			}else{
				type(ELEMENT_INPUT_LABEL_1, nodeLabel, true);
			}
		}
		Utils.pause(1000);
		//Selector page
		if (!pageTitle.isEmpty()){
			click(ELEMENT_PAGE_SELECTOR_TAB);
			click(ELEMENT_SEARCH_SELECTOR_PAGE_LINK);
			type(ELEMENT_INPUT_PAGE_TITLE, pageTitle, true);
			Utils.pause(1000);
			select(ELEMENT_SELECT_SEARCH_OPTION, option);
			Utils.pause(2000);
			click(pageMag.ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON);
			Utils.pause(2000);
			click(ELEMENT_SELECT_SEARCHED_PAGE);
		}	
		Utils.pause(2000);
		button.save();
		Utils.pause(1000);
		if(!nodeLabel.isEmpty()){
			waitForAndGetElement(ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeLabel));
		}
		//button.save();
	}

	/**function add new navigation for group
	 * @author lientm
	 * @param groupName
	 */
	public void addNewNavigationForGroup(String groupName){
		button = new Button(driver);
		info("Add navigation for group " + groupName);
		click(ELEMENT_GROUP_ADD_NAVIGATION_BUTTON);
		click(ELEMENT_GROUP_SELECT_ADD_NAVIGATION.replace("${groupName}", groupName));
		waitForElementNotPresent(ELEMENT_GROUP_SELECT_ADD_NAVIGATION.replace("${groupName}", groupName));
		button.cancel();
		waitForAndGetElement(ELEMENT_GROUP_NAME.replace("${groupName}", groupName));
	}

	/**function delete navigation for group
	 * @author lientm
	 * @param groupName
	 */
	public void deleteNavigationForGroup(String groupName){
		alert = new ManageAlert(driver);
		info("Delete navigation of group " + groupName);
		click(ELEMENT_DELETE_NAVIGATION_ICON.replace("${groupName}", groupName));
		alert.acceptAlert();
		waitForElementNotPresent(ELEMENT_DELETE_NAVIGATION_ICON.replace("${groupName}", groupName));
	}
}