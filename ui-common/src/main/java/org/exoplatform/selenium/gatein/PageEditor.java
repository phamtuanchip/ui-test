package org.exoplatform.selenium.gatein;

import static org.exoplatform.selenium.TestLogger.info;
import org.openqa.selenium.By;

public class PageEditor extends GateInBase {
	/** 
		Page Creation Wizard: Select a Navigation Node and create the Page 
	**/
	public static String PORTAL_MANAGEMENT_LINK = "//a[@title='Portal Administration']";
	public static String APPLICATION_MANAGER_LINK = "//a[@title='Application Manager']";
	public static String PAGE_MANAGER_LINK = "//a[@title='Page Manager']";
	public static String ADD_USERS_LINK = "//a[@title='Add Users']";
	public static String USERS_GROUP_MANAGER_LINK = "//a[@title='User and Group Manager']";
	public static String UP_LEVEL_ICON = "//a[@title='Up Level']";
	public static String DEFAULT_NODE = "//div[contains(text(),'/default')]";
	public static String NODE_NAME_INPUT = "//input[@id='pageName']";
	
	/* Page Editor - View Page Properties*/
	//View Page Properties form -> PlatformBase/Permission setting tab
	public static By ELEMENT_VIEW_PAGE_PROPERTIES = By.xpath("//a[text()='View Page properties']");
	//View Page Properties form (there are 2 tabs in this form)
	//Page Setting Tab
	public static By ELEMENT_VIEWPAGE_PAGETITLE = By.xpath("//input[@id='title']");
	//Permission setting tab
	//View Page Properties form End
	
	/*-- Site Editor/Edit Page/Edit Mode 
	 *-- Select Content Path/Content Search Form Tab  
	 * --*/
	public static final By ELEMENT_SEARCH_BUTTON = By.xpath("//a[text()='Search']");
	public static final By ELEMENT_CLOSE_WINDOWS_BUTTON = By.xpath("//a[@class='CloseButton']"); 
	public static final By ELEMENT_CONFIRM_YES_BUTTON = By.xpath("//a[contains(text(), 'Yes')]");
	public static final By ELEMENT_RADIO_MODE_CONTENT = By.id("UICLVConfigDisplayModeFormRadioBoxInput_ManualViewerMode"); 
	public static final By ELEMENT_RADIO_MODE_FOLDER = By.id("UICLVConfigDisplayModeFormRadioBoxInput_AutoViewerMode");
	public static final By ELEMENT_ADDWIZARD_TEXT2 = By.xpath("//div[@class='StepTitle' and contains(text(),'Select a Page Layout Template.')]");

	//Edit "content list" portlet 
	public static final By ELEMENT_EDITPAGE_CONTENT_DELETE = By.xpath("//div[@class='DeleteIcon']");
	public static final By ELEMENT_TAB_SEARCH_RESULT=By.xpath("//div[@class='SelectedTab']/div/div/div[contains(text(),'Search Result')]");
	public static final By ELEMENT_CLOSE_POPUP_BUTTON=By.xpath("//a[@title='Close Window']");
	public static final By ELEMENT_SEARCH_FORM_CONTENT = By.xpath("//input[@name='WcmRadio' and @id='content']");
	public static final By ELEMENT_INPUT_NAME_SEARCH_FORM_EDIT_MODE = By.xpath("//input[@id='name' and @type='text']");
	public static final By ELEMENT_CHECK_BOX_WORD_PHRASE_EDIT_MODE = By.xpath("//input[@id='content' and @type='radio']");
	public static final By ELEMENT_INPUT_NAME_SEARCH_WORD_PHRASE_EDIT_MODE = By.xpath("//input[@id='content' and @type='text']");
	public static final By ELEMENT_CONTENT_SEARCH_FORM_TAB = By.xpath("//div[@class='MiddleTab' and text() = 'Content Search Form']");

	//create new page having layout - step 1,2

	//Create empty layout SCV (Single Content Viewer) with content
	//	public static void createPage_EmptyLayout_ContentDetail_ContentPath(String pageName, String contentPath){
	//		goToPageEditor_EmptyLayout(pageName);
	//		pause(500);
	//		addContentDetailEmptyLayout();
	//		pause(500);
	//		selectContentPath(contentPath);
	//		pause(500);
	//		click(ELEMENT_NEWPAGE_SAVE_BUTTON);			
	//	}





	/*-- Add common functions for Single Content Viewer/Add SCV
	 *-- Page Editor 
	 *-- Add a new SCV page and select a content path 
	 *-- @author: VuNA
	 *-- @date: 23/10/2012
	 *--*/

	
	//function remove a portlet
	public static void removePortlet(By sign, By elementPortlet, By iconDelete){
		if (waitForAndGetElement(sign) != null){
			mouseOver(elementPortlet, true);
			click(iconDelete);
			acceptAlert();
			click(ELEMENT_PAGE_EDIT_FINISH);
			info("remove portlet is successful");
		}else{
			info("portlet has already deleted");
			click(ELEMENT_PAGE_CLOSE);
		}
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_FINISH,50000);
	}
}
