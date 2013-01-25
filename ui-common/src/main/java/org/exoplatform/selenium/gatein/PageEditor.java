package org.exoplatform.selenium.gatein;

import static org.exoplatform.selenium.TestLogger.info;
import org.openqa.selenium.By;

public class PageEditor extends GateInBase {
	/** 
		Page Creation Wizard: Select a Navigation Node and create the Page 
	**/
	public static String SITE_EXPORT_IMPORT_LINK = "//div[@class='NullItemIcon']/a[@title='Site Export/Import']";
	public static String SERVICE_MANAGEMENT_LINK = "//div[@class='NullItemIcon']/a[@title='Services Management']";
	public static String PAGE_MANAGER_LINK = "//div[@class='NullItemIcon']/a[@title='Page Management']";
	public static String ADMIN_LINK = "//a[@title='Administration']";
	public static String USERS_GROUP_MANAGER_LINK = "//a[@title='User and Group Manager']";
	public static String UP_LEVEL_ICON = "//a[@title='Up Level']";
	public static String DEFAULT_NODE = "//div[contains(text(),'/default')]";
	public static String NODE_NAME_INPUT = "//input[@id='pageName']";
	public static By ELEMENT_APPLICATION_REGISTRY_CREATE=By.xpath("//div[@class='NullItemIcon']/a[@title='Application Registry']");
	//Page editor - Edit inline composer
	public static final String ELEMENT_ADMIN_CATEGORY = ELEMENT_EDIT_PAGE_CATEGORY_MENU.replace("${categoryLabel}", "Administration");
	public static final By ELEMENT_APPLICATION_NEW_ACCOUNT = By.xpath("//div[@title='New Account']");
	public static final By ELEMENT_APPLICATION_REGISTRY = By.id("Administration/ApplicationRegistryPortlet");
	/* Page Editor - View Page Properties*/
	//View Page Properties form -> PlatformBase/Permission setting tab
	public static By ELEMENT_VIEW_PAGE_PROPERTIES = By.xpath("//a[text()='View Page properties']");
	//View Page Properties form (there are 2 tabs in this form)
	//Page Setting Tab
	public static By ELEMENT_VIEWPAGE_PAGETITLE = By.xpath("//input[@id='title']");
	//Permission setting tab
	//View Page Properties form End
	
	/*-- Site Editor/Edit Page/Edit Mode 
	
	 * --*/
	public static final By ELEMENT_SEARCH_BUTTON = By.xpath("//a[text()='Search']");
	public static final By ELEMENT_CLOSE_WINDOWS_BUTTON = By.xpath("//a[@class='CloseButton']"); 
	public static final By ELEMENT_CONFIRM_YES_BUTTON = By.xpath("//a[contains(text(), 'Yes')]");
	public static final By ELEMENT_RADIO_MODE_CONTENT = By.id("UICLVConfigDisplayModeFormRadioBoxInput_ManualViewerMode"); 
	public static final By ELEMENT_RADIO_MODE_FOLDER = By.id("UICLVConfigDisplayModeFormRadioBoxInput_AutoViewerMode");
	public static final By ELEMENT_ADDWIZARD_TEXT2 = By.xpath("//div[@class='StepTitle' and contains(text(),'Select a Page Layout Template.')]");

	
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
