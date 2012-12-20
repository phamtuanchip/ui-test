package org.exoplatform.selenium.gatein;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.gatein.PageManagement.*;

import java.util.Map;
import org.openqa.selenium.By;

public class NavigationManagement extends GateInBase {
	public static final By ELEMENT_INPUT_POPUP_SEARCH_TITLE = By.xpath("//div[@class='QuickSet']/input[@id='pageTitle']"); 
	public static final By ELEMENT_SELECT_PAGE = By.xpath("//div[@id='UIRepeater']//table//tbody/tr/td[5]/div[@class='ActionContainer']/img");
	public static final String MSG_DELETE_NODE = "Are you sure you want to delete this node?";
	public static final String MSG_ADD_SAME_NODE = "This node name already exists."; 
	public static final String MSG_ADD_SAME_PAGE = "This page name already exists.";
	public static final String MSG_SAME_SOURCE = "The source and the destination must be different.";
	public static final By ELEMENT_NAVIGATION_CLASSIC_HOME = By.xpath("//a[@class='NodeIcon DefaultPageIcon NodeSelected' and @title='Home']");
	public static final By ELEMENT_NAVIGATION_CLASSIC_SITEMAP = By.xpath("//a[contains(@class,'NodeIcon DefaultPageIcon') and @title='SiteMap']");
	public static final String EDIT_CLASSIC_NAVIGATION = "//div[text()='classic']/../..//a[text()='Edit Navigation']";
	public static final String EDIT_MOBILE_NAVIGATION = "//div[text()='mobile']/../..//a[text()='Edit Navigation']";
	
	// Add a node for portal at portal navigation
	public static void addNodeForPortal(String currentNavigation, String currentNodeLabel, boolean useAddNodeLink, String nodeName, boolean extendedLabelMode,
			Map<String, String> languages, String nodeLabel, String pageName, String pageTitle, boolean verifyPage, boolean verifyNode){

		//String node = ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeLabel);
		String currentNode = ELEMENT_NODE_LINK.replace("${nodeLabel}", currentNodeLabel);
		editNavigation(currentNavigation);

		info("--Add new node at navigation--");	
		if (useAddNodeLink){
			click(currentNode);
			click(ELEMENT_ADD_NODE_LINK);
		}else{

			click(currentNode);
			pause(500);
			rightClickOnElement(currentNode);
			if (currentNode.equals(ELEMENT_NAVIGATION_HOME_NODE)) {
				click(ELEMENT_NODE_ADD_NEW_TOP_NODE);
			} else {
				click(ELEMENT_NODE_ADD_NEW);
			}	
		}
		waitForTextPresent("Page Node Setting");
		type(ELEMENT_INPUT_NAME, nodeName, true);

		if (extendedLabelMode) {
			for (String language : languages.keySet()) {
				select(ELEMENT_SELECT_LANGUAGE, language);
				pause(500);
			}
		} else {
			uncheck(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE);
			type(ELEMENT_INPUT_LABEL, nodeLabel, true);
		}

		click(ELEMENT_PAGE_SELECTOR_TAB);

		if (pageName != null) {
			info("-- Create new page --");
			type(ELEMENT_INPUT_PAGE_NAME, pageName, true);
			type(ELEMENT_INPUT_PAGE_TITLE, pageTitle, true);
			click(ELEMENT_CREATE_PAGE_LINK);
			if (verifyPage) {
				waitForElementNotPresent(ELEMENT_CREATE_PAGE_LINK);
			} else {
				return;
			}
		} else {
			info("-- Select Page --");
			pause(500);
			click(ELEMENT_SEARCH_SELECT_PAGE_LINK);
			type(ELEMENT_INPUT_POPUP_SEARCH_TITLE, pageTitle, true);
			click(ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON);
			click(ELEMENT_SELECT_PAGE);
		}

		info("-- Save add node for portal --");
		pause(1000);
		save();
		if (verifyNode) {
			waitForTextNotPresent("Page Node Settings");
			waitForTextPresent(nodeName);
			save();
			waitForTextNotPresent("Navigation Management");
		}
	}

	// Edit a node 
	public static void editNode(String currentNavigation, String nodeNameHome, String nodeName, boolean extendedLabelMode, Map<String, String> languages, 
			String nodeLabel, String pageName, String pageTitle, boolean firstLevel){

		String currentNodeHome = ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeNameHome);
		String currentNodeName = ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeName);
		editNavigation(currentNavigation);
		//currentNodeHome.equals(ELEMENT_NAVIGATION_NODE_AREA)
		if (firstLevel){
			click(currentNodeName);
			rightClickOnElement(currentNodeName);
			click(ELEMENT_NODE_EDIT);	
		}else {
			click(currentNodeHome);
			click(currentNodeName);
			rightClickOnElement(currentNodeName);
			click(ELEMENT_NODE_EDIT);	

		}
		waitForTextPresent("Page Node Settings");
		if (extendedLabelMode) {
			for (String language : languages.keySet()) {
				select(ELEMENT_SELECT_LANGUAGE, language);
				pause(500);
			}
		} else {
			uncheck(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE);
			type(ELEMENT_INPUT_LABEL, nodeLabel, true);
		}

		click(ELEMENT_PAGE_SELECTOR_TAB);
		click(ELEMENT_CLEAR_PAGE_LINK);
		type(ELEMENT_INPUT_PAGE_NAME, pageName, true);
		type(ELEMENT_INPUT_PAGE_TITLE, pageTitle, true);
		click(ELEMENT_CREATE_PAGE_LINK);
		pause(1000);
		save();
		pause(1000);
		save();
		waitForTextNotPresent("Navigation Management");
	}

	//Delete a node from Portal navigation
	public static void deleteNode(String currentNavigation, String nodeNameHome, String nodeName, boolean firstLevel){
		info("--Delete a node from navigation--");
		String currentNodeHome = ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeNameHome);	
		String currentNodeName = ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeName);
		editNavigation(currentNavigation);
		//currentNodeHome.equals(ELEMENT_NAVIGATION_NODE_AREA)
		if (firstLevel){
			click(currentNodeName);
			rightClickOnElement(currentNodeName);
			click(ELEMENT_NODE_DELETE);
			waitForConfirmation(MSG_DELETE_NODE);
			waitForElementNotPresent(currentNodeName);
			save();		
		}else {
			click(currentNodeHome);
			click(currentNodeName);
			rightClickOnElement(currentNodeName);
			click(ELEMENT_NODE_DELETE);
			waitForConfirmation(MSG_DELETE_NODE);
			waitForTextNotPresent(currentNodeName);
			save();		
		}
		waitForTextNotPresent("Navigation Management");
	}

}
