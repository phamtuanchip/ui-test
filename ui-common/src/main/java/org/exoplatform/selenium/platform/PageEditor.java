package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;

public class PageEditor extends EcmsBase {
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
	//Permisstion setting tab
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

	
	//Create page wizard without layout
	public static void goToPageEditor_EmptyLayout(String pageName){
		goToPageCreationWinzard();
		type(ELEMENT_NEWPAGE_NAME_TEXTBOX, pageName, false);
		click(ELEMENT_NEXT_BUTTON);
		waitForElementPresent(ELEMENT_ADDWIZARD_TEXT2);
		click(ELEMENT_NEXT_BUTTON);
	}

	//Create new page without content 
	public static void createNewPageEmptyLayout(String pageName){	
		goToPageEditor_EmptyLayout(pageName);
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);
	}

	//create new page having layout - step 1,2
	public static void gotoPageEditorAndSelectLayout(String pageName, int numberLayout){
		goToPageCreationWinzard();
		type(ELEMENT_NEWPAGE_NAME_TEXTBOX, pageName, false);
		click(ELEMENT_NEXT_BUTTON);
		click(ELEMENT_NEWPAGE_LAYOUT_OPTION);
		switch (numberLayout){
		case 1: click(ELEMENT_NEWPAGE_LAYOUT_COLUMN_PAGE_OPTION);
		break;
		case 2: click(ELEMENT_NEWPAGE_LAYOUT_ROW_PAGE_OPTION);
		break;
		case 3: click(ELEMENT_NEWPAGE_LAYOUT_TAB_PAGE_OPTION);
		break;
		case 4: click(ELEMENT_NEWPAGE_LAYOUT_MIX_PAGE_OPTION);
		break;		
		default: click(ELEMENT_NEWPAGE_LAYOUT_DEFAULT_OPTION);
		break;
		}
		click(ELEMENT_NEXT_BUTTON);
	}

	//Create new page having layout 
	public static void createNewPageWithLayout(String pageName, int numberLayout){
		gotoPageEditorAndSelectLayout(pageName, numberLayout);
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);		
	}


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

	//Create new CLV with layout and content
	public static void createPage_ContentList_CLVpath(String pageName, String path, String clv){
		gotoPageEditorAndSelectLayout(pageName, 1);
		pause(500);
		addContentList();
		pause(500);
		selectCLVPath(path, clv);
		pause(500);
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);
	}

	//Add content detail to an empty layout page
	public static void addContentDetailEmptyLayout(){
		click(ELEMENT_MENU_CONTENT_LINK);
		dragAndDropToObject(ELEMENT_ADD_CONTENT_DETAIL_PORTLET, ELEMENT_DROP_TARGET_NO_LAYOUT);	
	}

	//Add "ContentDetail" to page with selected layout
	public static void addContentDetail(){
		click(ELEMENT_MENU_CONTENT_LINK);
		dragAndDropToObject(ELEMENT_ADD_CONTENT_DETAIL_PORTLET,ELEMENT_DROP_TARGET_HAS_LAYOUT);		
	}

	//Add "ContentList" to EmptyLayout page
	public static void addContentListEmptyLayout(){
		click(ELEMENT_MENU_CONTENT_LINK);
		dragAndDropToObject(ELEMENT_ADD_CONTENT_LIST_PORTLET, ELEMENT_DROP_TARGET_NO_LAYOUT);
	}

	//Add "ContentList" to page with selected layout
	public static void addContentList(){
		click(ELEMENT_MENU_CONTENT_LINK);
		dragAndDropToObject(ELEMENT_ADD_CONTENT_LIST_PORTLET,ELEMENT_DROP_TARGET_HAS_LAYOUT);		
	}

	//Select "ContentPath" in edit portlet
	public static void selectContentPath(String pathContent){
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET,true);	
		click(ELEMENT_EDIT_PORTLET_ICON);
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		selectGroup(pathContent);
		click(ELEMENT_SAVE_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);
	}

	/*Select "CLVPath" in Edit Mode
	 * @mode:  content: if select mode "By content"
	 * 		   other value: if select mode "By folder"	
	 */
	public static void selectCLVPath(String path, String clv, String...mode){
		By ELEMENT_SELECT_CLV_PATH = By.xpath("//td/a[text()='" + clv + "']");

		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET, true);
		click(ELEMENT_EDIT_PORTLET_ICON);
		if (mode.length >0){ 
			if (mode[0] == "content")
				click(ELEMENT_RADIO_MODE_CONTENT);
			else 
				click(ELEMENT_RADIO_MODE_FOLDER);
		}

		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		selectGroup(path);
		click(ELEMENT_SELECT_CLV_PATH);
		if (mode.length >0){ 
			if (mode[0] == "content"){
				click(ELEMENT_SAVE_BUTTON);
				waitForElementNotPresent(ELEMENT_SELECT_CLV_PATH);
			}		
		}
		click(ELEMENT_SAVE_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);
	}

	/*-- Add common functions for Single Content Viewer/Add SCV
	 *-- Page Editor 
	 *-- Add a new SCV page and select a content path 
	 *-- @author: VuNA
	 *-- @date: 23/10/2012
	 *--*/

	//Add a new SCV page and add a selected content path to this page
	//Use a default page with Empty layout 
	public static void addSCVPageAndContentFolderPaths(String pageName, String contentPath){
		info("-- Add a content path to SCV page: "+ pageName +" --");
		goToPageEditor_EmptyLayout(pageName);
		//Drag and drop Content Detail portlet into this page
		addContentDetailEmptyLayout();
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_NEWPAGE_SAVE_BUTTON);
		goToEditPageEditor();
		//Select ContentPath
		selectContentPathInEditMode(contentPath, false);
		click(By.xpath("//*[@id='UIPageEditor']/div[1]/a[2]"));
	}

	//Select a content path to add to SCV page
	//Use as default: boolean inEditModeWindows = false
	public static void selectContentPathInEditMode(String contentPath, boolean inEditModeWindows){
		info("-- Select the content path: "+ contentPath +"--");
		String ELEMENT_SELECT_CONTENT_FOLDER_PATHS = "//a[@title='${pathName}']";
		String[] pathNames = contentPath.split("/");
		if(inEditModeWindows){
			for(String path : pathNames){
				String pathToSelect = ELEMENT_SELECT_CONTENT_FOLDER_PATHS.replace("${pathName}", path);
				click(pathToSelect);
			}
		}
		else{
			selectContentPath(contentPath);
		}	
	}

	//---Function to create new page with content by query portlet, 
	//---@author: Nhungvt
	public static void createPage_ContentByQuery_EmptyLayout(String pageName)
	{
		goToPageEditor_EmptyLayout(pageName);
		click(ELEMENT_MENU_CONTENT_LINK);
		dragAndDropToObject(ELEMENT_CONTENTS_BY_QUERY_PORTLET, ELEMENT_DROP_TARGET_NO_LAYOUT);
		pause(500);
	}


	//function select home path on content list reference form
	public static void selectHomePathOnContentList(String groupPath, String node){
		By ELEMENT_NODE = By.xpath("//td/a[contains(text(),'" + node + "')]");

		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		waitForElementPresent(ELEMENT_FOLDER_BROWSER);
		if (getElement(ELEMENT_HOMEPATH_ROOT) != null){
			click(ELEMENT_HOMEPATH_ROOT);
		}
		selectGroup(groupPath);
		waitForAndGetElement(ELEMENT_NODE);
		if (getElement(ELEMENT_NODE) != null){
			click(ELEMENT_NODE);
		}else{
			info("Not found category");
		}
	}

	//function remove a portlet
	public static void removePortlet(By sign, By elementPortlet, By iconDelete){
		if (waitForAndGetElement(sign, 7000, 0) != null){
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
