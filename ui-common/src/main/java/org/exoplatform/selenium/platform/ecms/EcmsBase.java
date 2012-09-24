package org.exoplatform.selenium.platform.ecms;

import static org.exoplatform.selenium.TestLogger.info;
import java.util.Set;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class EcmsBase extends PlatformBase {
	
	public static final By ELEMENT_ACME_LINKTEXT = By.linkText("Login to the ACME website");
	public static final By ELEMENT_LOGIN_LINK = By.xpath("//*[@id='AcmeWebSiteLogInLogOut']");
	public static final By ELEMENT_LOGIN_BUTTON = By.xpath("//*[@id='UIPortalLoginFormAction']");
	public static final By ELEMENT_LOGOUT_LINK = By.xpath("//a[@class='TBIcon']");
	public static final By ELEMENT_MENU_SETUP_IMG = By.xpath("//img[@alt='Setup']") ;
	public static final By ELEMENT_MENU_PORTAL_LINK_LINKTEXT = By.linkText("Portal");
	public static final By ELEMENT_MENU_PAGE_LINK_LINKTEXT = By.linkText("Pages");
	public static final By ELEMENT_MENU_CONTENT_LINK = By.linkText("Content");
	public static final By ELEMENT_MENU_SITE_EXPLORER_LINK = By.linkText("Sites Explorer");
	public static final By ELEMENT_MENU_EDIT_LINK = By.linkText("Edit");
	public static final By ELEMENT_MENU_PAGE_LINK = By.linkText("Page");
	public static final By ELEMENT_MENU_ADD_PAGE_LINK = By.linkText("Add Page");
	public static final By ELEMENT_MENU_NEW_CONTENT_LINK = By.linkText("New Content");
	public static final By ELEMENT_MENU_SITE_LINK = By.linkText("Site");
	public static final By ELEMENT_MENU_NAVIGATION_LINK = By.linkText("Navigation");
	
	public static final By ELEMENT_ARTICLE_LINK = By.xpath("//div[@title='Article']");
	public static final By ELEMENT_ARTICLE_TITLE_TEXTBOX = By.id("title");	
	public static final By ELEMENT_ARTICLE_NAME_TEXTBOX = By.id("name");
	public static final By ELEMENT_ARTICLE_SUMMARY_FRAME = By.xpath("//td[@id='cke_contents_summary']/iframe");
	public static final By ELEMENT_ARTICLE_CONTENT_FRAME = By.xpath("//td[@id='cke_contents_content']/iframe");
	public static final By ELEMENT_SAVE_CLOSE_BUTTON = By.linkText("Save & Close");
	
	public static final By ELEMENT_ANNOUCEMENT_LINK = By.linkText("Announcement");
	public static final By ELEMENT_ANNOUCEMENT_NAME_TEXTBOX = By.id("name");
	public static final By ELEMENT_ANNOUCEMENT_SUMMARY_FRAME = By.xpath("//td[@id='cke_contents_exo:summary']/iframe");
	
	public static final By ELEMENT_WEBCONTENT_LINK =By.linkText("Free layout webcontent");
	public static final By ELEMENT_WEBCONTENT_TITLE_TEXTBOX =By.id("title");	
	public static final By ELEMENT_WEBCONTENT_NAME_TEXTBOX =By.id("name");	
	public static final By ELEMENT_WEBCONTENT_CONTENT_FRAME = By.xpath("//td[contains(@id,'cke_contents_htmlData')]/iframe");
	public static final By ELEMENT_WEBCONTENT_ILLUSTRATION_TAB = By.xpath("//div[contains(text(),'Illustration')]");
	public static final By ELEMENT_WEBCONTENT_UPLOAD_FRAME =By.xpath("//*[contains(@id,'uploadFrame')]");
	public static final By ELEMENT_WEBCONTENT_FILE_IMAGE = By.id("file");
	public static final By ELEMENT_WEBCONTENT_SUMMARY_FRAME = By.xpath("//td[@id='cke_contents_exo:summary']/iframe");
	public static final By ELEMENT_WEBCONTENT_ADVANCE_TAB = By.xpath("//div[contains(text(),'Advanced')]");
	public static final By ELEMENT_WEBCONTENT_CSS_TEXTAREA = By.xpath("//textarea[contains(@id,'ContentCSS')]");
	public static final By ELEMENT_WEBCONTENT_JS_TEXTAREA = By.xpath("//textarea[contains(@id,'ContentJS')]");
	
	public static final By ELEMENT_NEWFILE_LINK =By.linkText("File");
	public static final By ELEMENT_NEWFILE_NAME_TEXTBOX =By.id("name");
	public static final By ELEMENT_NEWFILE_CONTENT_FRAME = By.xpath("//td[@id='cke_contents_contentHtml']/iframe");
	public static final By ELEMENT_NEWFILE_TITLE_TEXTBOX =By.id("title0");
	
	public static final By ELEMENT_SAMPLENODE_LINK =By.linkText("Sample node");
	public static final By ELEMENT_SAMPLENODE_TITLE_TEXTBOX = By.id("title");
	public static final By ELEMENT_SAMPLENODE_NAME_TEXTBOX = By.id("name");
	public static final By ELEMENT_SAMPLENODE_ADD_ITEM_LINK = By.xpath("//img[@title = 'Add Item']");	
	public static final By ELEMENT_SAMPLENODE_SELECTITEM = By.id("taxonomyTree");
	public static final String ELEMENT_SAMPLENODE_SELECTITEM_OPTION = "acme";
	public static final By ELEMENT_SAMPLENODE_SELECTITEM_OPTION_LINK = By.xpath("//div[@class='Select16x16Icon']");
	public static final By ELEMENT_SAMPLENODE_DESCRIPTION_TEXTBOX = By.id("description");
	public static final By ELEMENT_SAMPLENODE_UPLOAD_IMG_FRAME = By.xpath("//iframe[contains(@id,'uploadFrame')]");
	public static final By ELEMENT_SAMPLENODE_FILE_IMG = By.id("file") ;
	public static final By ELEMENT_SAMPLENODE_CONTENT_TEXTAREA = By.id("summary");
	public static final By ELEMENT_SAMPLENODE_SUMMARY_TEXTAREA = By.id("content");
	
	public static final By ELEMENT_KOFAX_LINK = By.linkText("Kofax document");
	public static final By ELEMENT_KOFAX_NAME_TEXTBOX = By.id("name");
	public static final By ELEMENT_KOFAX_ADDNODE_LINK = By.xpath("//img[@class='MultiFieldAction AddNewNodeIcon']") ;
	public static final By ELEMENT_KOFAX_ADDNODE_SELECT = By.id("taxonomyTree");
	public static final String ELEMENT_KOFAX_ADDNODE_OPTION = "acme";
	public static final By ELEMENT_KOFAX_ADDNODE_OPTION_LINK = By.xpath("//div[@class='Select16x16Icon']");
	
	
	public static final By ELEMENT_FILEPLAN_LINK = By.linkText("File Plan");
	public static final By ELEMENT_FILEPLAN_NAME = By.id("name");
	public static final By ELEMENT_FILEPLAN_RECORD_TAB = By.xpath("//div[contains(text(),'Record Properties')]");
	public static final By ELEMENT_FILEPLAN_RECORD_TEXTBOX = By.id("recordCategoryIdentifier");
	public static final By ELEMENT_FILEPLAN_DISPOS_TEXTBOX = By.id("dispositionAuthority");
	public static final By ELEMENT_FILEPLAN_DEFAULT_TEXTBOX  = By.id("defaultOriginatingOrganization");
	public static final By ELEMENT_FILEPLAN_RPROCESS_TAB = By.xpath("//div[contains(text(),'Process Properties')]");
	public static final By ELEMENT_FILEPLAN_RPROCESS_TRIGER = By.id("eventTrigger");	
	
	public static final By ELEMENT_PODCAST_LINK = By.linkText("Podcast");
	public static final By ELEMENT_PODCAST_NAME_TEXTBOX = By.id("name");
	public static final By ELEMENT_PODCAST_TITLE_TEXTBOX = By.id("title");
	public static final By ELEMENT_PODCAST_LINK_TEXTBOX = By.id("link");
	
	public static final By ELEMENT_HEAD_LAYOUT_LINK = By.linkText("Picture on head layout webcontent");
	public static final By ELEMENT_HEAD_LAYOUT_NAME_TEXTBOX = By.id("name");
	public static final By ELEMENT_HEAD_LAYOUT_TITLE_TEXTBOX = By.id("title");	
	public static final By ELEMENT_HEAD_LAYOUT_UPLOAD_FRAME = By.xpath("//iframe[contains(@id,'uploadFrame')]")	;
	public static final By ELEMENT_HEAD_LAYOUT_UPLOAD_FILE = By.id("file") ;
	
	public static final By ELEMENT_PRODUCT_LINK = By.linkText("Product");
	public static final By ELEMENT_PRODUCT_NAME_TEXTBOX = By.id("name") ;
	public static final By ELEMENT_PRODUCT_TITLE_TEXTBOX = By.id("title");
	
	public static final By ELEMENT_NEW_FOLDER_LINK = By.linkText("New Folder");
	
	public static final By ELEMENT_NEWPAGE_NAME_TEXTBOX = By.id("pageName");	
	public static final By ELEMENT_NEWPAGE_NEXT_BUTTON = By.linkText("Next");	
	public static final By ELEMENT_NEWPAGE_SAVE_BUTTON = By.xpath("//a[@class='EdittedSaveButton']");
	public static final By ELEMENT_NEWPAGE_LAYOUT_OPTION = By.xpath("//div[@class='DropDownSelectLabel']") ;
	public static final By ELEMENT_NEWPAGE_LAYOUT_COLUMN_PAGE_OPTION = By.linkText("Column Page Configs") ;
	public static final By ELEMENT_NEWPAGE_LAYOUT_ROW_PAGE_OPTION = By.linkText("Row Page Configs");
	public static final By ELEMENT_NEWPAGE_LAYOUT_TAB_PAGE_OPTION = By.linkText("Tabs Page Config");
	public static final By ELEMENT_NEWPAGE_LAYOUT_MIX_PAGE_OPTION = By.linkText("Mix Page Configs");
	public static final By ELEMENT_NEWPAGE_LAYOUT_DEFAULT_OPTION = By.linkText("Page Configs");
	
	public static final By ELEMENT_FOLDER_TITLE_TEXTBOX = By.id("title");
	public static final By ELEMENT_FOLDER_NAME_TEXTBOX = By.id("name");
	public static final By ELEMENT_FOLDER_TYPE_OPTION = By.id("type");
	public static final String ELEMENT_CONTENT_FOLDER_TYPE = "nt:unstructured";
	public static final String ELEMENT_DOCUMENT_FOLDER_TYPE = "nt:folder";
	public static final By ELEMENT_SAVE_BUTTON = By.linkText("Save");
	
	public static final By ELEMENT_SEARCH_TITLEPAGE_TEXTBOX = By.id("pageTitle");
	public static final By ELEMENT_SEARCH_PAGE_ICON = By.xpath("//a[@class='SearchIcon']");
	public static final By ELEMENT_SEARCH_PAGE_ALERT = By.xpath("//[@class= 'UIPopupWindow UIDragObject ExoMessageDecorator']");	
	public static final By ELEMENT_PARTIALLINK_DELETE_DOCUMENT = By.xpath("//a[contains(text(),'Delete')]");
	public static final String ELEMENT_ALERT_CONFIRM_DELETE_DOCUMENT = "Are you sure to delete the node";
	public static final String ELEMENT_ALERT_CONFIRM_DELETE_PAGE = "Are you sure to delete this page?";
	public static final String ELEMENT_ALERT_FIND_AFTER_DELETE_PAGE = "Information" ;
	
	public static final By ELEMENT_SIMPLESEARCH_TEXTBOX = By.id("simpleSearch");
	public static final By ELEMENT_SIMPLESEARCH_SUBMIT = By.id("SimpleSearch");
	
	public static final By ELEMENT_DELETE_PAGE_ICON = By.xpath("//img[@class='DeleteIcon']");
	public static final String ELEMENT_SITE_NAVIGATION_POPUP = "UIPopupWindow-UIEditNavigationPopupContainer";
	public static final By ELEMENT_SITE_NAVIGATION_DELETE_ERROR_ALERT = By.xpath("//div[@class='UIPopupWindow UIDragObject ExoMessageDecorator']");
	public static final String ELEMENT_SITE_NAVIGATION_DELETE_NODE_PARTIALLINK = "Delete";
	public static final By ELEMENT_SITE_NAVIGATION_SAVE_BUTTON = By.linkText("Save");
	
	public static final By ELEMENT_CONTENT_LINK = By.xpath("//a[@title='Content']");
	public static final By ELEMENT_ADD_CONTENT_DETAIL_PORTLET = By.xpath("//div[contains(text(),'Content Detail')]");
	public static final By ELEMENT_DROP_TARGET_NO_LAYOUT = By.xpath("//div[@id='UIPage']");
	public static final By ELEMENT_DROP_TARGET_HAS_LAYOUT = By.xpath("//div[@class='UIRowContainer EmptyContainer']");
	public static final By ELEMENT_ADD_CONTENT_LIST_PORTLET = By.xpath("//div[contains(text(),'Content List')]");
	public static final By ELEMENT_FRAME_CONTAIN_PORTLET = By.xpath("//div[contains(@id,'UIPortlet')]");
	public static final By ELEMENT_EDIT_PORTLET_LINK = By.xpath("//a[@title='Edit Portlet']");
	public static final By ELEMENT_SELECT_CONTENT_PATH_LINK = By.xpath("//img[@class='AddIcon16x16 SelectFolderPathIcon']");
	public static final By ELEMENT_SELECT_CONTENT_PATH_GENERAL_LINK = By.id("GeneralDrives_");
	public static final By ELEMENT_SELECT_CONTENT_PATH_GENERAL_MANAGED_LINK = By.id("GeneralDrives_MANAGEDedSites_");
	public static final By ELEMENT_SELECT_CONTENT_PATH_ACME_LINK = By.id("GeneralDrives_MANAGEDedSites__acme");
	public static final By ELEMENT_SELECT_CONTENT_PATH_ACME_DOC_LINK =By.id("GeneralDrives_MANAGEDedSites__acme_documents") ;
	public static final By ELEMENT_SELECT_CONTENT_PATH_SAVE_BUTTON = By.linkText("Save");
	public static final By ELEMENT_SELECT_CONTENT_PATH_CLOSE_BUTTON =By.linkText("Close") ;
	
	public static final By ELEMENT_SELECT_CONTENT_PATH = By.xpath("//a[@title='offices.jpg']");
	public static final By ELEMENT_SELECT_CLV_PATH = By.xpath("//a[@path='/sites content/live/acme/documents']");
	public static final By ELEMENT_PREFERENCE_LINK =By.xpath("//a[@class='SetupPreferencesButton']");
	public static final String ELEMENT_PREFERENCE_ADVANCE_LINK = "Advanced";
	

	public static final By ELEMENT_UPLOAD_LINK_XPATH = By.xpath("//a[@title='Upload']");
	public static final By ELEMENT_UPLOAD_FILE_NAME_ID = By.id("name");
	public static final By ELEMENT_UPLOAD_IMG_FRAME_XPATH = By.xpath("//iframe[contains(@id,'uploadFrame')]");
	public static final By ELEMENT_UPLOAD_IMG_ID = By.id("file");
	public static final By ELEMENT_UPLOAD_FINISH_XPATH = By.xpath("//div[@class='FileNameLabel']");
	public static final By ELEMENT_UPLOAD_SAVE_BUTTON_LINKTEXT = By.linkText("Save");
	public static final By ELEMENT_UPLOAD_CLOSE_BUTTON_LINKTEXT =By.linkText("Close");

	public static final By ELEMENT_LOCK_OPTION_XPATH = By.xpath("//a[contains(text(),'Lock')]");
	public static final By ELEMENT_UNLOCK_OPTION_XPATH = By.xpath("//a[contains(text(),'UnLock')]");
	public static final By ELEMENT_CHECKIN_OPTION_XPATH = By.xpath("//a[contains(text(),'CheckIn')]");
    public static final By ELEMENT_CHECKOUT_OPTION_XPATH = By.xpath("//a[contains(text(),'CheckOut')]");
    
    //Locator of SetPermission
    public static final By ELEMENT_SYSTEM_LINK = By.linkText("System");
    public static final By ELEMENT_PERMISSION_TAB = By.linkText("Permissions");
    public static final By ELEMENT_SELECT_USER = By.xpath("//img[@alt='Select User']");
    public static final By ELEMENT_SEARCH_TEXTBOX = By.id("QuickSearch");
    public static final By ELEMENT_SEARCH_LINK = By.xpath("//a[@class='SearchIcon' and @title='Quick Search']");
    public static final By ELEMENT_SEARCH_CHOOSE = By.xpath("//img[@class='SelectPageIcon']");
    public static final By ELEMENT_READ_CHECKBOX = By.id("read");
    public static final By ELEMENT_ADD_NODE_CHECKBOX = By.id("add_node");
    public static final By ELEMENT_CLOSE_BUTTON = By.linkText("Close");
    public static final By ELEMENT_DELETE_PERMISSION = By.xpath("//*[@id='PermissionInfo']/table/tbody/tr[4]/td[6]/div/img[2]");
    
    public static final By ELEMENT_DRIVE= By.xpath("//a[@title='Show Drives']");
	public static final By ELEMENT_BUTTON_ADD_CATE=By.linkText("Add Category");
	public static final By ELEMENT_ADD_CATE_POP=By.xpath("//span[text()='Add Category']");
	public static final By ELEMENT_INPUT_CATE_NAME=By.id("name");
	public static final By ELEMENT_BUTTON_SAVE=By.linkText("Save");
	public static final By ELEMENT_LINK_CONTENT_ADMIN=By.linkText("Content administration");
	public static final By ELEMENT_LINK_CONTENT=By.linkText("Content");
    
	//login ECMS
	public static void loginEcms(String username, String password) {
		driver.manage().window().maximize();
		click(ELEMENT_ACME_LINKTEXT);
		click(ELEMENT_LOGIN_LINK);
		type(By.name("username"),username, false);
		type(By.name("password"),password, false);
		click(ELEMENT_LOGIN_BUTTON);	
	}
		
	//logout ECMS
	public static void logoutEcms (){
		actions.moveToElement(waitForAndGetElement(ELEMENT_LOGOUT_LINK)).build().perform();
		click(By.linkText("Logout"));
		driver.get(baseUrl);
	}
	
	// go to content administration
	  	public static void goToContentAdministration()
	  	{
	  		mouseOver(ELEMENT_LINK_SETUP, true);
	  		mouseOver(ELEMENT_LINK_CONTENT, true);
	  		mouseOverAndClick(ELEMENT_LINK_CONTENT_ADMIN);
	  	}
	  	
	  //Enter sites MANAGEDement Form 
	  	public static void goToSiteExplorer(){
	  		actions.moveToElement(waitForAndGetElement(ELEMENT_MENU_SETUP_IMG)).build().perform();
	  		actions.moveToElement(waitForAndGetElement(ELEMENT_MENU_CONTENT_LINK)).build().perform();
	  		click(ELEMENT_MENU_SITE_EXPLORER_LINK);
	  	}
	  	
	  	//Enter create new page form
	  	public static void goToPageCreationWinzard(){
	  		actions.moveToElement(waitForAndGetElement(ELEMENT_MENU_EDIT_LINK)).build().perform();
	  		actions.moveToElement(waitForAndGetElement(ELEMENT_MENU_PAGE_LINK)).build().perform();
	  		click(ELEMENT_MENU_ADD_PAGE_LINK);	
	  	}
	  	
	//Select option from combobox
	public static void selectOption(By locator,String value){
		Select typeFolder = null;
		pause(100);
		while (typeFolder ==null){
			pause(100);
			typeFolder = new Select(waitForAndGetElement(locator));
		}
		typeFolder.selectByValue(value);
	}
	
	//function go to Node
  	public static void goToNode(By locator){
  		click(locator);
  	}
  	
  //go to a node
  	//input: path: path of a node, splitted by  "/" character 
  	public static void goToNodeByPath(String path)
  	{
  		String[] nodes = path.split("/");
  		for (String node: nodes)
  		{
  			goToNode(By.xpath("//a[@title='" + node + " ']"));
  			pause(100);
  		}
  		
  	}
  	
//	public static void inputDataToFrame (By framelocator, String data){
//		driver.switchTo().frame(waitForAndGetElement(framelocator));
//		WebElement inputsummary = driver.switchTo().activeElement();
//		inputsummary.sendKeys(data);
//	}
	//function add data to frame
	public static void inputDataToFrame (By framelocator, String data){
		try {
			WebElement inputsummary = null;
 
			for (int repeat = 0;; repeat++) {
				if (repeat >= DEFAULT_TIMEOUT/WAIT_INTERVAL) {
					Assert.fail("Fail to input data to frame " + framelocator);
				}
				driver.switchTo().frame(waitForAndGetElement(framelocator));
				inputsummary = driver.switchTo().activeElement();
				inputsummary.sendKeys(data);
				if (data.equals(inputsummary.getText())) break;
				switchToParentWindow();
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			inputDataToFrame (framelocator, data);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			inputDataToFrame (framelocator,data);
		} finally {
			loopCount = 0;
		}
	}
	
	//function switch to parent windows
	public static void switchToParentWindow (){
		try
		{
			Set<String> availableWindows = driver.getWindowHandles();
			String WindowIdParent= null;
			int counter = 1;
			for (String windowId : availableWindows) {
				if (counter == 1){
					WindowIdParent = windowId;
				}
				counter++;
			}
			driver.switchTo().window(WindowIdParent);
			pause(1000);
		}
		catch (WebDriverException e)
		{
			e.printStackTrace();
		}
	}
	
	
	//delete node of page, node is subnode of node
	public static void deleteNode(By node, String pageTitle){
		actions.moveToElement(waitForAndGetElement(ELEMENT_MENU_EDIT_LINK)).build().perform();
		actions.moveToElement(waitForAndGetElement(ELEMENT_MENU_SITE_LINK)).build().perform();
		click(ELEMENT_MENU_NAVIGATION_LINK);
		
		waitForElementPresent(By.id(ELEMENT_SITE_NAVIGATION_POPUP));
		if (node != null){
		click(node);
		}
		actions.contextClick(waitForAndGetElement(By.linkText(pageTitle))).perform();
		driver.findElement(By.partialLinkText(ELEMENT_SITE_NAVIGATION_DELETE_NODE_PARTIALLINK)).click();
		acceptAlert();	
		click(ELEMENT_SITE_NAVIGATION_SAVE_BUTTON);
		assert isElementNotPresent(ELEMENT_SITE_NAVIGATION_DELETE_ERROR_ALERT):"Have message error";
		info("Delete node of page successfully");
		pause(1000);
	}
}
