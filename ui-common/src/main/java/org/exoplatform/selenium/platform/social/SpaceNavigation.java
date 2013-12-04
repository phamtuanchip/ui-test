package org.exoplatform.selenium.platform.social;

import static org.exoplatform.selenium.TestLogger.debug;
import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PageManagement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 *@author HangNTT
 * @date: 09/11/2012
 */
public class SpaceNavigation extends SocialBase{

	Button button;
	Dialog dialog;
	ManageAlert magAlert;
	PageManagement pageMag;

	// Go to My Spaces > Select a space > Settings
	// Navigations Tab
	public final By UP_LEVEL = By.xpath("//a[@title='Up Level']");

	public final By ADD_NODE_BUTTON = By.xpath("//button[text()='Add Node']");
	public final By NODE_NAME = By.xpath("//input[@id='name']");
	public final By NODE_LABEL = By.xpath("//input[@id='label' or contains(@id, 'Label')]");
	public final By PAGE_SELECTOR = By.xpath("//a[text()='Page Selector']");
	public final By PAGE_NAME = By.id("pageName");
	public final By CREATE_PAGE_BUTTON = By.xpath("//i[@class='uiIconAddPage uiIconWhite']");
	public final String NODE_PATH = "//a[@class='NodeIcon DefaultPageIcon' and @title='${nodeLabel}']";
	public final By ELEMENT_INPUT_POPUP_SEARCH_TITLE = By.xpath("//div[@class='QuickSet']/input[@id='pageTitle']"); 
	public final By ELEMENT_SELECT_PAGE = By.xpath("//div[@id='UIRepeater']//table//tbody/tr/td[5]/div[@class='ActionContainer']/img");
	public final String WARNING_EXISTING_NODE = "This node name already exists.";
	public final By SAVE_PAGE_BUTTON = By.xpath("//div[@class='uiAction']/button[text()='Save']");
	
	public final String ELEMENT_NODE_LINK_FORM = "//ul[@class='nodeGroup']//a[@title='${nodeLabel}']";
	public final String ELEMENT_NODE_LINK_FORM_COLLAPSE = "//ul[@class='nodeGroup']//a[@title='${nodeLabel}' and @ class= 'uiIconNode nodeSelected collapseIcon']";
	public final String ELEMENT_RIGHT_CLICK_ADD_NODE_FORM_ = "//div[@class='TopLeftRightClickPopupMenu']/div[@class='UIContextMenuContainer']//a[@class='ItemIcon AddNode16x16Icon']";
	public final String ELEMENT_ADD_NEW_NODE_RIGHT_CLICK = "//div[@id='SpaceNavigationNodePopupMenu']/div[@class='uiContextMenuContainer']//i[@class='uiIconAddNode']";
	public final String ELEMENT_DELETE_NODE = "//div[@id='SpaceNavigationNodePopupMenu']/div[@class='uiContextMenuContainer']//i[@class='uiIconDeleteNode']";
	public final String ELEMENT_EDIT_NODE = "//div[@id='SpaceNavigationNodePopupMenu']/div[@class='uiContextMenuContainer']//i[@class='uiIconEditSelectedNode']";
	public final By ELEMENT_CUT_NODE_LINK = By.linkText("Cut Node");
	//	public final By ELEMENT_PASTE_NODE_LINK = By.linkText("Paste Node");
	public final By ELEMENT_PASTE_NODE_LINK = By.xpath("//div[@id='SpaceNavigationNodePopupMenu']/div[@class='uiContextMenuContainer']//i[@class='uiIconPasteNode']");
	public final By ELEMENT_COPY_NODE_LINK = By.linkText("Copy Node");
	public final By ELEMENT_MOVE_UP_LINK = By.linkText("Move Up");
	public final By ELEMENT_MOVE_DOWN_LINK = By.linkText("Move Down");
	public final By ELEMENT_EDIT_NODE_PAGE = By.linkText("Edit Node's Page");

	// Spacesetting -> Navigation -> Edit Page
	public final By SAVE_NAVITATION_BUTTON = By.xpath("//div[@id='UISpaceNavigationManagement']/div[@class='uiAction uiActionBorder']//button[text()='Save']");
	public By ELEMENT_PORTLET_LAYOUT = By.className("PortletLayoutDecorator");
	public By ELEMENT_SAVECLOSE_LINK = By.linkText("Save And Close");
	public By ELEMENT_WINDOWN_SETTINGS_TAB = By.xpath("//div[text()='Window Settings']");
	public By ELEMENT_DESCRIPTION_AREA = By.id("description");
	public By ELEMENT_TAB_CONTAINERS = By.xpath("//div[contains(text(),'Containers') and @class='MiddleTab']");
	public By ELEMENT_ROWS_LAYOUT = By.xpath("//a[@class='TabLabel and @title='row']");
	public String ELEMENT_EDIT_PAGE_COMPONENT = "//div[@class='UIRowContainer']/div[${portletNumber}]/div";
	public By ELEMENT_EDIT_ICON = By.xpath("//a[@class = 'EditIcon' and @title='Edit Container']");
	public By ELEMENT_PERMISSION_TAB = By.xpath("//div[text()='Access Permission']");

	public SpaceNavigation(WebDriver dr){
		driver = dr;
		button = new Button(driver);
		dialog = new Dialog(driver);
		magAlert = new ManageAlert(driver);
		pageMag = new PageManagement(driver);
	}

	// Add node don't select page in Manage page
	public void addNodeDoNotSelectPage(String nodeName, Object...params)
	{
		String nodeLabel = (String) (params.length > 0 ? params[0]: "");
		String pageName = (String) (params.length > 1 ? params[1]: "");
		//Click add node button
		click(ADD_NODE_BUTTON);
		//Input node name
		waitForAndGetElement(NODE_NAME);
		type(NODE_NAME, nodeName, true);
		//Input node label
		if(nodeLabel!=""){
			waitForAndGetElement(NODE_LABEL);
			type(NODE_LABEL, nodeLabel, true);
		}
		if(pageName!=""){
			//Click Page selector tab
			waitForAndGetElement(PAGE_SELECTOR);
			click(PAGE_SELECTOR);
			//Input page name
			waitForAndGetElement(PAGE_NAME);
			type(PAGE_NAME,pageName,true);
			//Click create page
			waitForAndGetElement(CREATE_PAGE_BUTTON);
			click(CREATE_PAGE_BUTTON);
			Utils.pause(1000);
		}
		click(SAVE_PAGE_BUTTON);
		Utils.pause(1000);
		if(nodeLabel!="")
			waitForAndGetElement("//a[contains(text(),'"+nodeLabel+"')]");
		else
			waitForAndGetElement("//a[contains(text(),'"+nodeName+"')]");
		waitForAndGetElement(SAVE_NAVITATION_BUTTON);
		click(SAVE_NAVITATION_BUTTON);
	}
	/**
	 * Updated by thaopth
	 * Edit function to do action search and select an existing page
	 * Date: 04/12/2012
	 * @param currentNodeLabel
	 * @param useAddNodeLink
	 * @param nodeName
	 * @param extendedLabelMode
	 * @param languages
	 * @param nodeLabel
	 * @param pageName
	 * @param pageTitle
	 * @param verifyPage
	 * @param verifyNode
	 * @param select
	 */
	// Add node when select a page in Manage page
	public void addNodeWhenSelectpage(String nodePath, boolean useAddNodeButton, String nodeName, boolean extendedLabelMode,
			String language, String nodeLabel, String pageName, String pageTitle, boolean searchPage){
		info("--Click add node button--");	
		String []paths = nodePath.split("/");
		if (useAddNodeButton){
			for(String path:paths){
				if(waitForAndGetElement(By.xpath(ELEMENT_NODE_LINK_FORM_COLLAPSE.replace("${nodeLabel}",path)),DEFAULT_TIMEOUT,0)!=null)
					click(ELEMENT_NODE_LINK_FORM.replace("${nodeLabel}",path));
			}
			click(ADD_NODE_BUTTON);
		} else{
			for(String path:paths){
				if(waitForAndGetElement(By.xpath(ELEMENT_NODE_LINK_FORM_COLLAPSE.replace("${nodeLabel}",path)),DEFAULT_TIMEOUT,0)!=null)
					click(ELEMENT_NODE_LINK_FORM.replace("${nodeLabel}",path));
			}
			Utils.pause(500);
			info("--Click right menu--");
			rightClickOnElement(ELEMENT_NODE_LINK_FORM.replace("${nodeLabel}",paths[paths.length-1]));
			click(ELEMENT_ADD_NEW_NODE_RIGHT_CLICK);
		}
		waitForAndGetElement("//*[contains(text(),'Page Node Setting')]");
		type(NODE_NAME, nodeName, true);
		if (extendedLabelMode) {
				select(ELEMENT_SELECT_LANGUAGE, language);
				Utils.pause(500);
		} else {
			uncheck(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE,2);
			type(NODE_LABEL, nodeLabel, true);
		}
		info("-- Select page selector tab --");
		waitForAndGetElement(PAGE_SELECTOR);
		click(PAGE_SELECTOR);

		if (!searchPage) {
			info("--Create new page");
			waitForAndGetElement("//*[contains(text(),'Selected Page Info')]");
			type(ELEMENT_INPUT_PAGE_NAME, pageName, true);
			type(ELEMENT_INPUT_PAGE_TITLE, pageTitle, true);
			click(CREATE_PAGE_BUTTON);
			waitForElementNotPresent(CREATE_PAGE_BUTTON);
		} else {
			info("-- Select Page --");
			click(ELEMENT_SEARCH_SELECTOR_PAGE_LINK);
			Utils.pause(1000);
			type(ELEMENT_INPUT_PAGE_TITLE, pageTitle, true);
			click(ELEMENT_SELECT_PAGE_SEARCH);
			click(ELEMENT_SELECT_PAGE_BUTTON);
		}
		info("-- Save add node for portal --");
		Utils.pause(1000);
		click(SAVE_PAGE_BUTTON);
		Utils.pause(1000);
		if(nodeLabel!="")
			waitForAndGetElement("//a[contains(@title,'"+nodeLabel+"')]");
		else
			waitForAndGetElement("//a[contains(@title,'"+nodeName+"')]");
		waitForAndGetElement(SAVE_NAVITATION_BUTTON);
		click(SAVE_NAVITATION_BUTTON);
	}

	// Edit a node 
	public void editNode(String nodePath, String nodeName, Object...params){
		String nodeLabel = (String) (params.length > 0 ? params[0]: "");
		String pageTitle = (String) (params.length > 1 ? params[1]: "");
		String option = (String) (params.length > 2 ? params[2]: "portal");
		String []paths = nodePath.split("/");
		for(String path:paths){
			if(waitForAndGetElement(By.xpath(ELEMENT_NODE_LINK_FORM_COLLAPSE.replace("${nodeLabel}",path)),DEFAULT_TIMEOUT,0)!=null)
				click(ELEMENT_NODE_LINK_FORM.replace("${nodeLabel}",path));
		}
		rightClickOnElement(ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeName));
		click(ELEMENT_EDIT_SELECTED_NODE);
		if(!nodeLabel.isEmpty()){
			type(NODE_LABEL, nodeLabel, true);
		}
		//Selector page
		if (!pageTitle.isEmpty()){
			click(PAGE_SELECTOR);
			click(ELEMENT_SEARCH_SELECTOR_PAGE_LINK);
			type(ELEMENT_INPUT_PAGE_TITLE, pageTitle, true);
			select(ELEMENT_SELECT_SEARCH_OPTION, option);
			click(pageMag.ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON);
			click(ELEMENT_SELECT_SEARCHED_PAGE);
		}	
		click(SAVE_PAGE_BUTTON);
		if(!nodeLabel.isEmpty()){
			waitForAndGetElement(ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeLabel));
		}
		else
			waitForAndGetElement(ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeName));
	}

	//Delete a node from Social navigation
	public void deleteNode(String nodePath, String nodeName){
		info("--Delete a node from navigation--");
		String []paths = nodePath.split("/");
		for(String path:paths){
			if(waitForAndGetElement(By.xpath(ELEMENT_NODE_LINK_FORM_COLLAPSE.replace("${nodeLabel}",path)),DEFAULT_TIMEOUT,0)!=null)
				click(ELEMENT_NODE_LINK_FORM.replace("${nodeLabel}",path));
		}
		rightClickOnElement(ELEMENT_NODE_LINK_FORM.replace("${nodeLabel}",nodeName));
		click(ELEMENT_DELETE_NODE);
		magAlert.waitForConfirmation("Are you sure you want to delete this node?");
		magAlert.acceptAlert();
		waitForElementNotPresent(ELEMENT_NODE_LINK_FORM.replace("${nodeLabel}",nodeName));
		waitForAndGetElement(SAVE_NAVITATION_BUTTON);
		click(SAVE_NAVITATION_BUTTON);
		waitForElementNotPresent("//a[contains(text(),'"+nodeName+"')]");
	}
	/**
	 * Updated by ThaoPTH
	 * Add function cutSpaceNode(), copySpaceNode(), pasteSpaceNode()
	 * Date: 06/12/2012
	 */
	public void cutSpaceNode(By locator)	{
		for (int i =0;; i++){
			if (i > DEFAULT_TIMEOUT/WAIT_INTERVAL) {
				Assert.fail("Timeout");
			}
			rightClickOnElement(locator);
			if (waitForAndGetElement(ELEMENT_CUT_NODE_LINK,30000,0)!=null){
				debug("==Cut node " + locator + "==");
				click((ELEMENT_CUT_NODE_LINK));
				return;
			}
			Utils.pause(WAIT_INTERVAL);
		}
	}

	public void copySpaceNode(By locator)	{
		for (int i =0;; i++){
			if (i > ACTION_REPEAT) {
				Assert.fail("Timeout");
			}
			rightClickOnElement(locator);
			if (waitForAndGetElement(ELEMENT_COPY_NODE_LINK,5000,0)!=null){
				debug("==Cut node " + locator + "==");
				click((ELEMENT_COPY_NODE_LINK));
				return;
			}
			Utils.pause(WAIT_INTERVAL);
		}
	}

	public void pasteSpaceNode(By locator)	{
		for (int i =0;; i++){
			if (i > ACTION_REPEAT) {
				Assert.fail("Timeout");
			}
			rightClickOnElement(locator);
			if (waitForAndGetElement(ELEMENT_PASTE_NODE_LINK,5000,0)!=null){
				debug("==Cut node " + locator + "==");
				click((ELEMENT_PASTE_NODE_LINK));
				return;
			}
			Utils.pause(WAIT_INTERVAL);
		}
	}

	/**
	 * Create functions: moveUpNode() and moveDownNode(), editNodePage()
	 * @author: ThaoPTH
	 * Date: 03/12/2012
	 */
	public void moveUpNode(String nodeName) {

		waitForAndGetElement(By.xpath("//a[@class='NodeIcon DefaultPageIcon' and text()='"+nodeName+"']"));

		rightClickOnElement(By.xpath("//a[@class='NodeIcon DefaultPageIcon' and text()='"+nodeName+"']"));

		waitForAndGetElement(ELEMENT_MOVE_UP_LINK);

		click(ELEMENT_MOVE_UP_LINK);

	}

	public void moveDownNode(String nodeName) {

		waitForAndGetElement(By.xpath("//a[@class='NodeIcon DefaultPageIcon' and text()='"+nodeName+"']"));

		rightClickOnElement(By.xpath("//a[@class='NodeIcon DefaultPageIcon' and text()='"+nodeName+"']"));

		waitForAndGetElement(ELEMENT_MOVE_DOWN_LINK);

		click(ELEMENT_MOVE_DOWN_LINK);
	}

	public void editNodePage(String nodeName) {;
	waitForAndGetElement(By.xpath("//a[@class='NodeIcon DefaultPageIcon' and text()='"+nodeName+"']"));

	rightClickOnElement(By.xpath("//a[@class='NodeIcon DefaultPageIcon' and text()='"+nodeName+"']"));

	waitForAndGetElement(ELEMENT_EDIT_NODE_PAGE);

	click(ELEMENT_EDIT_NODE_PAGE);
	}
}

