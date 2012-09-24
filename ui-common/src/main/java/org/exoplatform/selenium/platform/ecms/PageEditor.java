package org.exoplatform.selenium.platform.ecms;

public class PageEditor extends EcmsBase {
  	
  	 //create page wizard with step 1,2 without layout
    public static void gotoPageEditor_EmptyLayout(String namePage){
    	goToPageCreationWinzard();
		type(ELEMENT_NEWPAGE_NAME_TEXTBOX, namePage, false);
		click(ELEMENT_NEWPAGE_NEXT_BUTTON);
		pause(500);
		click(ELEMENT_NEWPAGE_NEXT_BUTTON);
    }
	
	//create new page without layout 
	public static void createNewPageEmptyLayout(String namePage){	
		gotoPageEditor_EmptyLayout(namePage);
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);
	}
	
	//create new page has layout - step 1,2
	public static void gotoPageEditor_Layout(String namePage, int numberLayout){
		goToPageCreationWinzard();
		type(ELEMENT_NEWPAGE_NAME_TEXTBOX, namePage, false);
		click(ELEMENT_NEWPAGE_NEXT_BUTTON);
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
		click(ELEMENT_NEWPAGE_NEXT_BUTTON);
	}
	
	//create new page has layout 
	public static void createNewPageWithLayout(String namePage, int numberLayout){
		gotoPageEditor_Layout(namePage, numberLayout);
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);		
	}
	

	//Create new page empty layout add contentdetail, choose ContentPath
	public static void createPage_Empty_ContentDetail_ContentPath(String pageName){
		gotoPageEditor_EmptyLayout(pageName);
		pause(500);
		addContentDetailEmptyLayout();
		pause(500);
		selectContentPath();
		pause(500);
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);			
	}
	
	//Create new page has layout, add contentlist, choose clv path
	public static void createPage_ContentList_CLVpath(String pageName){
		gotoPageEditor_Layout(pageName, 1);
		pause(500);
		addContentList();
		pause(500);
		selectCLVPath();
		pause(500);
		click(ELEMENT_NEWPAGE_SAVE_BUTTON);
	}
	
	//add contentdetail to a page EmptyLayout of portal
	public static void addContentDetailEmptyLayout(){
		click(ELEMENT_CONTENT_LINK);
		dragAndDropToObject(ELEMENT_ADD_CONTENT_DETAIL_PORTLET, ELEMENT_DROP_TARGET_NO_LAYOUT);	
	}
	
	//Add "ContentDetail" to page with selected layout
	public static void addContentDetail(){
		click(ELEMENT_CONTENT_LINK);
		dragAndDropToObject(ELEMENT_ADD_CONTENT_DETAIL_PORTLET,ELEMENT_DROP_TARGET_HAS_LAYOUT);		
	}
	
	//Add "ContentList" to page EmptyLayout
	public static void addContentListEmptyLayout(){
		click(ELEMENT_CONTENT_LINK);
		dragAndDropToObject(ELEMENT_ADD_CONTENT_LIST_PORTLET, ELEMENT_DROP_TARGET_NO_LAYOUT);
	}
	
	//Add "ContentList" to page with selected layout
	public static void addContentList(){
		click(ELEMENT_CONTENT_LINK);
		dragAndDropToObject(ELEMENT_ADD_CONTENT_LIST_PORTLET,ELEMENT_DROP_TARGET_HAS_LAYOUT);		
	}
	
	//Select "ContentPath" in Edit Mode
	public static void selectContentPath(){
		actions.moveToElement(waitForAndGetElement(ELEMENT_FRAME_CONTAIN_PORTLET)).build().perform();
		click(ELEMENT_EDIT_PORTLET_LINK);
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		click(ELEMENT_SELECT_CONTENT_PATH_GENERAL_LINK);
		click(ELEMENT_SELECT_CONTENT_PATH_GENERAL_MANAGED_LINK);
		click(ELEMENT_SELECT_CONTENT_PATH_ACME_LINK);
		click(ELEMENT_SELECT_CONTENT_PATH_ACME_DOC_LINK);
		click(ELEMENT_SELECT_CONTENT_PATH);
		click(ELEMENT_SELECT_CONTENT_PATH_SAVE_BUTTON);
		click(ELEMENT_SELECT_CONTENT_PATH_CLOSE_BUTTON);
	}
	
	//Select "CLVPath" in Edit Mode
	public static void selectCLVPath(){
		actions.moveToElement(waitForAndGetElement(ELEMENT_FRAME_CONTAIN_PORTLET)).build().perform();
		click(ELEMENT_EDIT_PORTLET_LINK);
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		click(ELEMENT_SELECT_CONTENT_PATH_GENERAL_LINK);
		click(ELEMENT_SELECT_CONTENT_PATH_GENERAL_MANAGED_LINK);
		click(ELEMENT_SELECT_CONTENT_PATH_ACME_LINK);
		pause(500);
		click(ELEMENT_SELECT_CLV_PATH);
		click(ELEMENT_SELECT_CONTENT_PATH_SAVE_BUTTON);
		click(ELEMENT_SELECT_CONTENT_PATH_CLOSE_BUTTON);
	}
	
}
