package org.exoplatform.selenium.platform.gatein.functional.portalnavigation.edit.editnavigation.managenode;

import java.util.Map;
import java.util.HashMap;
import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationManagement.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;
import static org.exoplatform.selenium.gatein.PageManagement.*;
import static org.exoplatform.selenium.gatein.UserGroupManagement.*;


public class GateIn_PortalNavigation_Edit_EditNavigation_ManageNode_Create extends GateInBase {
	//Define data
	public static final By ADD_NODE_BUTTON = By.xpath("//a[text()='Add Node']");
	public static final By ADD_NODE_MENU = By.xpath("//a[contains(text(),'Add new Node')]");
	public static final By NODE_NAME = By.xpath("//input[@id='name']");
	public static final By UP_LEVEL = By.xpath("//a[@title='Up Level']");
	public static By NODE_LABEL = By.xpath("//input[@id='i18nizedLabel']");
	public static By PAGE_SELECTOR = By.xpath("//div[text()='Page Selector']");
	public static By PAGE_NAME = By.id("pageName");
	public static By CREATE_PAGE_BUTTON = By.linkText("Create Page");
	public static By PAGE_LINK = By.linkText("PORNAV_14_01_043");
	public static By ELEMENT_EDIT_SITE_LAYOUT = By.linkText("Layout");
	public static By ELEMENT_ADD_SITE_LINK = By.linkText("Add Site");
	public final static String ELEMENT_VIEW_PROPERTY="//a[@class='PageProfileIcon']";
	public final static String ELEMENT_PERMISSION_SETTING_TAB= "//div[contains(text(),'Permission Setting')]";
	public final static String ELEMENT_GROUP_ORGANIZATION = "//a[@title='Organization']";
	public final static String ELEMENT_GROUP_EXECUTIVE="Management/Executive Board";
	public final static String ELEMENT_GROUP_PLATFORM="Platform";
	public final static String ELEMENT_GROUP_ADMIN="Administrators";
	
	public final static By ELEMENT_CLASSIC_MENU = By.linkText("Classic");
	public final static By ELEMENT_HOME_MENU = By.linkText("Home");
	public final static By ELEMENT_LAYOUT_MENU = By.linkText("Edit Layout");
	public final static By ELEMENT_ADD_PAGE_MENU = By.linkText("Add New Page");
	public final static By ELEMENT_EDIT_PAGE_MENU = By.linkText("Edit Page");
	
	
//	public static WebElement element = null;

	/*------------------Common function---------------------*/

	//Add node 
	public void addNode (String nodeNameInput, String nodeLabelInput, String pageNameInput)
	{
		WebElement element;
		//Click add node button
		pause(1000);
		//Input node name
		element = waitForAndGetElement(NODE_NAME);
		element.sendKeys(nodeNameInput);
		pause(500);
		//Input node label
		element = waitForAndGetElement(NODE_LABEL);
		element.sendKeys(nodeLabelInput);
		//Click Page selector tab
		element = waitForAndGetElement(PAGE_SELECTOR);
		element.click();
		//Input page name
		element = waitForAndGetElement(PAGE_NAME);
		element.sendKeys(pageNameInput);
		type(By.id("pageTitle"),pageNameInput,true);
		//Click create page
		element = waitForAndGetElement(CREATE_PAGE_BUTTON);
		element.click();
		pause(1000);
	}
	//Up level
//	public void upLevel ()
//	{
//		element = waitForAndGetElement(UP_LEVEL);
//		element.click();
//		pause(1000);
//		click(UP_LEVEL);
//	}
	//Add node by searching page
	public void addNodeForPortal(String currentNavigation, String currentNodeLabel, boolean useAddNodeLink, String nodeName, boolean extendedLabelMode, 
			Map<String, String> languages, String nodeLabel, String pageName, String pageTitle, boolean verifyPage, boolean verifyNode, boolean select){
		String ELEMENT_SELECT_PAGE_SEARCH="//a[@title='Quick Search']";
		String ELEMENT_SELECT_PAGE_BUTTON= "//img[@title='Select Page']";
		String ELEMENT_SEARCH_SELECT_PAGE_BUTTON= "//a[text()='Search and Select Page']";
		
		//String node = ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeLabel);
		String currentNode = ELEMENT_NODE_LINK.replace("${nodeLabel}", currentNodeLabel);
		editNavigation(currentNavigation);

		info("--Adding new node at navigation--");		
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

		if (!select) {
			info("--Create new page");
			type(ELEMENT_INPUT_PAGE_NAME, pageName, true);
			type(ELEMENT_INPUT_PAGE_TITLE, pageTitle, true);
			click(ELEMENT_CREATE_PAGE_LINK);
			if (verifyPage) {
				waitForElementNotPresent(ELEMENT_CREATE_PAGE_LINK);
			} else {
				return;
			}
		} else {
			//info("-- Select Page --");
			
			click(ELEMENT_SEARCH_SELECT_PAGE_BUTTON);
			pause(1000);
						
			WebElement element = waitForAndGetElement(By.xpath("//div[@class='QuickSet']/input[@id='pageTitle']"));
			element.sendKeys(pageTitle);
			
			click(ELEMENT_SELECT_PAGE_SEARCH);
			
			click(ELEMENT_SELECT_PAGE_BUTTON);
			
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
	
	@BeforeMethod
	public void beforeMethods(){
		initSeleniumTest();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterTest(){
		signOut();
		driver.quit();
	}
	
	//FNC_GTN_POR_PORNAVIGATION_14_01_006: Create node at first level by using add node button
	@Test 
	public void test06_CreateNodeAtFirstLevel()
	{

		info("Sign as root");
		signIn("root", "gtn");

		//Go to portal site
		info("Go to portal site");
		goToPortalSites();

		// Edit classic navigation
		info("Open edit classic navigation form");
		editNavigation("classic");

		//Up Level
		info("Click up level");
		click(UP_LEVEL);
		click(ADD_NODE_BUTTON);
		//Add node
		info("Add node at the first level");
		addNode("PORNAV_14_01_006", "PORNAV_14_01_006", "page_PORNAV_14_01_006");

		//Click Save on add node form
		info("Click Save on add node form");
		save();
		pause(1000);

		//Click Save on Navigation form
		info("Click save on navigation form");
		save();
		pause(2000);

		//Verify new node
		info("verify new node");
		editNavigation("classic");
		assert isElementPresent(By.linkText("PORNAV_14_01_006")):"New node is created";
		pause(1000);

		/*-----------------------Delete data----------------*/

		info("Delete node");
		deleteNode("classic", "PORNAV_14_01_006", "PORNAV_14_01_006", true);

		info("Verify node is deleted");
		editNavigation("classic");
		waitForElementNotPresent("//a[@title='PORNAV_14_01_006']");

		info("Go to manage page");
		goToManagePages();

		info("Delete page");
		deletePage(PageType.PORTAL,"page_PORNAV_14_01_006");

		info("Verify page is deleted");
		waitForElementNotPresent("//div[@title='page_PORNAV_14_01_006']");

	}
	
	//FNC_GTN_POR_PORNAVIGATION_14_01_007: Create node at first level by right click
	@Test 
	
	public void test07_AddNodeAtFirstLevelByRightClick()
	{
		info("Sign as root");
		signIn("root", "gtn");

		info("Go to portal site");
		goToPortalSites();

		info("Open edit classic navigation form");
		editNavigation("classic");

		//Up Level
		info("Click up level");
		click(UP_LEVEL);

		//Right click
		info("Right click on blank area");
		rightClickOnElement(ELEMENT_NAVIGATION_HOME_NODE);
		click(ADD_NODE_MENU);
		//Add node
		info("Add new node");
		addNode("PORNAV_14_01_007", "PORNAV_14_01_007", "page_PORNAV_14_01_007");

		//Click Save on add node form
		info("Click Save on add node form");
		save();
		pause(1000);

		//Click Save on Navigation form
		info("Click save on navigation form");
		save();
		pause(2000);

		//Verify new node
		info("verify new node");
		editNavigation("classic");
		assert isElementPresent(By.linkText("PORNAV_14_01_007")): "New node is created";
		pause(1000);

		/*----------------------Delete data----------------*/
		//Delete node
		info("Delete node");
		deleteNode("classic", "PORNAV_14_01_007", "PORNAV_14_01_007", true);

		//Verify node is not present
		info("Verify node is deleted");
		editNavigation("classic");
		assert isElementNotPresent("//a[@title='PORNAV_14_01_007']"):"Node is not present";

		//Go to manage page
		info("Go to manage page");
		goToManagePages();

		//Delete page
		info("Delete page");
		deletePage(PageType.PORTAL,"page_PORNAV_14_01_007");

		//Verify Page node present
		info("Verify page is deleted");
		waitForElementNotPresent("//div[@title='page_PORNAV_14_01_007']");


	}
	//FNC_GTN_POR_PORNAVIGATION_14_01_008: Add child node by right click
	@Test
	public void test08_AddChildNodeByRightClick()
	{
		//Define data
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		info("Sign as root");
		signIn("root", "gtn");

		//Go to portal site
		info("Go to portal site");
		goToPortalSites();

		//Add child node for existing node by right click
		info("Add child node for existing node by right click");
		addNodeForPortal("classic", "Home", false, "PORNAV_14_01_007", true, languages, "PORNAV_14_01_007", null, null, true, true, true);

		//Delete data
		info("Delete node");
		deleteNode("classic", "Home", "PORNAV_14_01_007", true);


	}
	//FNC_GTN_POR_PORNAVIGATION_14_01_016: Add 2 nodes with same name in one level
	@Test
	public void test16_AddNodeSameNameInOneLevel()
	{
		//Define data
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		info("Sign as root");
		signIn("root", "gtn");

		//Go to portal site
		info("Go to portal site");
		goToPortalSites();

		//Add child node for existing node
		info("Add child node for Overview node");
		addNodeForPortal("classic", "Home", true, "PORNAV_14_01_016", true, languages, "PORNAV_14_01_016", "page_PORNAV_14_01_016", "page_PORNAV_14_01_016", true, true, false);

		//Open edit navigation form
		info("Open edit navigation form of amce site");
		editNavigation("classic");

		//Add node with the same name in parent node
		info("Add node with the same name with PORNAV_14_01_016");
		click("//a[@title='Home']");
		click(ADD_NODE_BUTTON);
		addNode("PORNAV_14_01_016", "PORNAV_14_01_016", "page_PORNAV_14_01_016");
		//Click Save on add node form
		save();
		//Verify warning message
		info("Verify warning message: This node name already exists");
		waitForTextPresent(MSG_ADD_SAME_NODE);

		//Confirm warning message
		info("Confirm warning message");
		click(ELEMENT_OK_BUTTON);
		pause(1000);

		//Close add node form
		info("Close add node form");
		click(ELEMENT_CLOSE_WINDOW);
		
		/*-----------------------Delete data--------------------*/
		deleteNode("classic", "Home", "PORNAV_14_01_016", true);		
		goToManagePages();
		deletePage(PageType.PORTAL, "page_PORNAV_14_01_016");
	}
	
	//FNC_GTN_POR_PORNAVIGATION_14_01_017: Add 2 nodes with same name in Different Level
	@Test 
	public void test17_AddNodeSameNameInDifferentLevel()
	{
		//Define data
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");
		String nodeName = "PORNAV_14_01_017";

		signIn("root", "gtn");

		//Go to portal site
		info("Go to portal site");
		goToPortalSites();

		//Add node
		info("Add child node for an existing node: Home");
		addNodeForPortal("classic", "Home", true, nodeName, true, languages, nodeName, "page01_PORNAV_14_01_017", "page01_PORNAV_14_01_017", true, true,false);

		//Add node with same name in other parent node
		info("Add node for an existing node: SiteMap");
		addNodeForPortal("classic", "SiteMap", false, nodeName, true, languages, nodeName, "page02_PORNAV_14_01_017", "page02_PORNAV_14_01_017", true, true,false);

		/*------------------------------Delete data-------------------------*/
		info("Delete node");
		editNavigation("classic");
		//Select parent node: Forum
		if(waitForAndGetElement("//a[@title='PORNAV_14_01_017']",5000,0) == null)
		click(ELEMENT_NAVIGATION_CLASSIC_HOME);
		rightClickOnElement("//a[@title='PORNAV_14_01_017']");
		click(By.linkText("Delete Node"));
		waitForConfirmation(MSG_DELETE_NODE);
		save();
		assert isElementNotPresent(By.linkText(nodeName)):"Node is not present";
		pause(500);
		
		//Select parent node: Wiki
		editNavigation("classic");
		if(waitForAndGetElement("//a[@title='PORNAV_14_01_017']",5000,0) == null)
		click(ELEMENT_NAVIGATION_CLASSIC_SITEMAP);
		rightClickOnElement("//a[@title='PORNAV_14_01_017']");
		click(By.linkText("Delete Node"));
		waitForConfirmation(MSG_DELETE_NODE);
		save();
		assert isElementNotPresent(By.linkText(nodeName)):"Node is not present";
		pause(500);
		
		//deleteNode("classic", "Forums", "PORNAV_14_01_017", true);
		//deleteNode("classic", "Wiki", nodeName, true);

		//Go to manage page
		info("Go to manage page");
		goToManagePages();
		info("Delete page");
		//Delete page1
		deletePage(PageType.PORTAL,"page01_PORNAV_14_01_017");
		//Delete page2
		deletePage(PageType.PORTAL,"page02_PORNAV_14_01_017");
	}
	
	//FNC_GTN_POR_PORNAVIGATION_14_01_032: Add node with page name is same with existing
	@Test
	public void test32_AddNodeWithPageNameSameWithExisting()
	{
		//Define data
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		signIn("root", "gtn");

		//Go to portal site
		info("Go to portal site");
		goToPortalSites();

		//Add node
		info("Add new node");
		addNodeForPortal("classic", "Home", true, "PORNAV_14_01_032", true, languages, "PORNAV_14_01_032", "page_PORNAV_14_01_032", "page_PORNAV_14_01_032", true, true,false);

		/*Add node with same page name*/
		info("Add node with name the same as page name");
		editNavigation("classic");
		//Select parent node
		click(ELEMENT_NAVIGATION_CLASSIC_SITEMAP);
		//Add node
		click(ADD_NODE_BUTTON);
		addNode("PORNAV_14_01_032_02", "PORNAV_14_01_032_02", "page_PORNAV_14_01_032");

		//Verify warning message
		debug("Verify warning message: This page name already exists");
		waitForMessage(MSG_ADD_SAME_PAGE);
		//Confirm warning message
		debug("Confirm warning message");
		click(ELEMENT_OK_BUTTON);
		//Close add node form				
		click(ELEMENT_CLOSE_WINDOW);

		/*-----------------------------Delete data----------------------------*/
		//Delete node
		deleteNode("classic", "Home", "PORNAV_14_01_032", true);

		//Delete Page
		goToManagePages();
		deletePage(PageType.PORTAL, "page_PORNAV_14_01_032");
	}

	//FNC_GTN_POR_PORNAVIGATION_14_01_043: Show site editor
	@Test
	public void test43_ShowSiteEditor()
	{
		//Define data
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		signIn("root", "gtn");

		//Go to add user page
		debug("Go to New Staff page");
		goToNewStaff();

		//Add new user
		debug("Add new user");
		addNewUserAccount("tester", "123456", "123456", "first", "last", "tester@exoplatform.com", "test", "English", true);
		info("add user successsfully");

		//Go to user and group management page
		debug("Go to user and group management page");
		goToUsersAndGroupsManagement();

		//Select group tab
		debug("Select group tab");
		chooseGroupTab();

		//Select group
		debug("Select group");
		selectGroup("Platform");
		selectGroup("Administrators");

		//Add user to group
		debug("Add user to group");
		addUsersToGroup("tester", "manager", false, true);
		//Logout root
		debug("Logout root");
		driver.get(baseUrl);
		signOut();

		//Login as new user
		debug("Sign in as new user");
		signIn("tester", "123456");

		//Go to page management
		debug("Go to manage page");
		goToManagePages();
		//Create page
		debug("Add page");
		addNewPageAtManagePages(PageType.PORTAL, "page_PORNAV_14_01_043", "page_PORNAV_14_01_043", true, null, "Platform/Administrators", "manager");
		//Logout
		signOut();

		//Login as root
		signIn("root", "gtn");

		//Go to site link
		debug("Go to portal site");
		goToPortalSites();

		//Add node
		debug("Add node with added page above");
		addNodeForPortal("classic", "Home", true, "PORNAV_14_01_043", true, languages, "PORNAV_14_01_043", "page_PORNAV_14_01_043","page_PORNAV_14_01_043", true, true, true);

		//Logout root
		debug("Logout root");
		signOut();

		//Login new user
		debug("Login new user");
		signIn("tester", "123456");
		//Mouse over My sites link	
		waitForElementPresent(ELEMENT_SITE_MENU);
		mouseOver(ELEMENT_SITE_MENU, true);
		//Mouse over classic link
		waitForElementPresent(ELEMENT_CLASSIC_MENU);
		mouseOver(ELEMENT_CLASSIC_MENU, true);
		//Mouse over Home link
		waitForElementPresent(ELEMENT_HOME_MENU);
		mouseOver(ELEMENT_HOME_MENU, true);
		//Click on new page
		waitForElementPresent(PAGE_LINK);
		click(PAGE_LINK);

		/*--------------Verify Site editor menu-------*/
		//Mouse over site editor link
		debug("mouse over site editor link");
		waitForElementPresent(ELEMENT_LINK_EDITOR);
		mouseOver(ELEMENT_LINK_EDITOR, true);
		//Verify edit page link
		waitForElementPresent(ELEMENT_ADD_PAGE_MENU);
		waitForElementPresent(ELEMENT_EDIT_PAGE_MENU);
		//Verify edit page layout link
		waitForElementPresent(ELEMENT_LAYOUT_MENU);
		signOut();
		
		/*----------------Delete data---------------*/
		signIn("root", "gtn");
		goToPortalSites();
		deleteNode("classic", "Home", "PORNAV_14_01_043", true);
		goToManagePages();
		deletePage(PageType.PORTAL, "page_PORNAV_14_01_043");
		goToUsersAndGroupsManagement();
		deleteUser("tester");

	}
	// FNC_GTN_POR_PORNAVIGATION_14_01_044: Check Site Editor menu when a user have a Edit right
		@Test
		public void test44_CheckEditMenuWithEditRight() {
			//data
			String username="checkmenu2";
			String pass="123456";
			Map <String, String> language = new HashMap<String, String>();
			language.put("English", "");
			String pageName = "FNC_GTN_POR_14_01";
			String pageTitle= "FNC_GTN_POR_14_01";
			String nodeName="FNC_GTN_POR_14_01";
			
			signIn("root", "gtn");
			info("Check Site Editor menu when a user has a Edit right");
					
			//create a user		
			goToNewStaff();
			addNewUserAccount(username, pass, pass, "check", "menu", "menu4@exoplatform.com", "", "", true);
			
			//add the user to groups
			goToUsersAndGroupsManagement();
			chooseGroupTab();
			click(ELEMENT_GROUP_ORGANIZATION);
			selectGroup(ELEMENT_GROUP_EXECUTIVE);
			addUsersToGroup(username, "manager", true, true);
			click("//a[@class='FL' and text()='Organization']");
			selectGroup(ELEMENT_GROUP_PLATFORM);
			selectGroup(ELEMENT_GROUP_ADMIN);
			addUsersToGroup(username, "member", true, true);
			
			//create a page
			goToManagePages();
			addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle, 
					true, null, "Platform/Administrators", "member");
					
			// create a node
			goToPortalSites(); 
			addNodeForPortal("classic", "Home", true, nodeName, true, language,
					"FNC_GTN_POR_14_01", pageName, pageTitle, true, true, true); 
			
			//edit a page
			goToManagePages();
			editPageAtManagePages(PageType.PORTAL, pageName);
			click(ELEMENT_VIEW_PROPERTY);
			click(ELEMENT_PERMISSION_SETTING_TAB);
			click(ELEMENT_EDIT_PERMISSION_SETTING);
			setEditPermissions("Platform/Administrators", "*");
			click(ELEMENT_SAVE_BUTTON);
			click(ELEMENT_FINISH_ICON);
			pause(1000);
			signOut();
			
			//login as a user that have just been created  
			signIn(username, pass);
			
			//go to the new page
			mouseOver(ELEMENT_SITE_MENU, true);
			pause(500);
			mouseOver(ELEMENT_CLASSIC_MENU, true);
			pause(500);
			mouseOver(ELEMENT_HOME_MENU, true);
			pause(500);
			waitForAndGetElement(By.linkText(nodeName)).click();
			pause(500);
			
			//check expected result
			mouseOver(ELEMENT_MENU_EDIT,true);
			pause(500);

			//check whether to view Edit page, Edit layout, Add page
			waitForElementPresent(ELEMENT_LAYOUT_MENU);
			waitForElementPresent(ELEMENT_ADD_PAGE_MENU);
			pause(500);
			waitForElementPresent(ELEMENT_EDIT_PAGE_MENU);
			signOut();

			signIn("root", "gtn");
			//delete user
			goToUsersAndGroupsManagement();
			chooseUserTab();
			deleteUser(username);
			
			//delete page
			goToManagePages();
			deletePage(PageType.PORTAL, pageName);  
			
			//delete node
			goToPortalSites();
			deleteNode("classic", "Home", nodeName, true);
			
		}
		//FNC_GTN_POR_PORNAVIGATION_14_01_045: Check Site Editor menu when a user has not a Edit right
		@Test
		public void test45_CheckEditMenuWithoutEditRight() {
			//data
			String username="checkmenu1";
			String pass="123456";
			Map <String, String> language = new HashMap<String, String>();
			language.put("English", "");
			String pageName = "FNC_GTN_POR_14_01";
			String pageTitle= "FNC_GTN_POR_14_01";
			String nodeName="FNC_GTN_POR_14_01";
			
			info("Check Site Editor menu when a user has not a Edit right");
			signIn("root", "gtn");
			
			//create a user		
			goToNewStaff();
			addNewUserAccount(username, pass, pass, "check", "menu", "menu1@exoplatform.com", "", "", true);
			
			//add the user to groups
			goToUsersAndGroupsManagement();
			chooseGroupTab();
			click(ELEMENT_GROUP_ORGANIZATION);
			selectGroup(ELEMENT_GROUP_EXECUTIVE);
			addUsersToGroup(username, "manager", true, true);
			click("//a[@class='FL' and text()='Organization']");
			selectGroup(ELEMENT_GROUP_PLATFORM);
			selectGroup(ELEMENT_GROUP_ADMIN);
			addUsersToGroup(username, "member", true, true);
			
			//create a page
			goToManagePages();
			addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle, 
					true, null, "Platform/Administrators", "member");
					
			// create a node
			goToPortalSites(); 
			addNodeForPortal("classic", "Home", true, nodeName, true, language,
					"FNC_GTN_POR_14_01", pageName, pageTitle, true, true, true); 
			
			//edit a page
			goToManagePages();
			editPageAtManagePages(PageType.PORTAL, pageName);
			click(ELEMENT_VIEW_PROPERTY);
			click(ELEMENT_PERMISSION_SETTING_TAB);
			click(ELEMENT_EDIT_PERMISSION_SETTING);
			setEditPermissions("Platform/Administrators", "manager");
			click(ELEMENT_SAVE_BUTTON);
			click(ELEMENT_FINISH_ICON);
			pause(1000);
			signOut();
			
			//login as a user that have just been created  
			signIn(username, pass);
			
			//go to the new page

			mouseOver(ELEMENT_SITE_MENU,true);
			mouseOver(ELEMENT_CLASSIC_MENU, true);
			mouseOver(ELEMENT_HOME_MENU,true);
			click(By.linkText(nodeName));
			pause(500);
			
			//check expected result

			mouseOver(ELEMENT_MENU_EDIT,true);
			pause(500);
	
			waitForElementPresent(ELEMENT_LAYOUT_MENU);
			signOut();

			signIn("root", "gtn");
			//delete user
			goToUsersAndGroupsManagement();
			chooseUserTab();
			deleteUser(username); 
			
			//delete page
			goToManagePages();
			deletePage(PageType.PORTAL, pageName);  
			
			//delete node
			goToPortalSites();
			deleteNode("classic", "Home", nodeName, true);
		}
}