package org.exoplatform.selenium.platform.ecms.admin;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author vuna2
 *
 */
public class ManageView extends EcmsBase{

	public ManageView(WebDriver dr,String...plfVersion) {
		super(dr);
		// TODO Auto-generated constructor stub
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
	}

	Button button = new Button(driver);
	ManageAlert alt = new ManageAlert(driver);
	ECMainFunction ecMain = new ECMainFunction(driver);
    
	//Views
	//Settings tab / View Tab
	public final By ELEMENT_SETTING_TAB = By.xpath("//*[contains(@class, 'popup')]//*[text()='Setting']");
	public final By ELEMENT_VIEW_TAB = By.xpath("//*[contains(@class, 'popup')]//*[text()='View']");
	public final By ELEMENT_HIDE_EXPLORER_PANEL = By.name("hideExplorerPanel");
	public final By ELEMENT_SELECTED_TEMPLATE = By.xpath("//*[@name='template']/option[@selected='selected']");
	
	//Action tab
	public final By ELEMENT_ACTION_TAB = By.xpath("//*[contains(@class, 'popup')]//*[text()='Action']");
	public final String ELEMENT_ACTION_TAB_NAME = "//*[contains(@class, 'popup')]//*[text()='${tabName}']";
	public final String ELEMENT_EDIT_ACTION_ICON = "//*[contains(@class, 'popup')]//*[text()='${tabName}']/../..//i[@class='uiIconEdit']";
	public final By ELEMENT_SAVE_BUTTON_EDIT_VIEW_TAB = By.xpath("//*[text()='Add/Edit Tab']/.. /..//*[text()='Save']");
	//*[contains(@class, 'popupTitle')]/../..//*[text()='Admin']/../..//*[@class='uiIconEdit']

	//Permission tab
	public final By ELEMENT_ADDVIEW_PERMISSION_TAB = By.xpath("//*[text()='Permission']");
	public final By ELEMENT_ADDVIEW_PERMISSION_BUTTON = By.xpath("//*[@id='UIViewPermissionForm']//*[text()='Add']");
	public final String ELEMENT_VERIFY_PERMISSION = "//*[contains(@class, 'popup')]//*[@id='UIViewPermissionList']//*[text()='${permission}']";
	
	public final String ELEMENT_VIEW_ICON = "//*[text() = '${viewName}']/../..//*[contains(@class, 'uiIconView')]";
	public final String ELEMENT_DISPLAY_VIEW_ITEM = "//*[@class='Text' and contains(text(),'${viewName}')]"; 
	
	//Views > Explorer Template
	public final By ELEMENT_EDIT_EXPLORER_TEMPLATE_TAB = By.xpath("//*[contains(@class, 'popupTitle') and text()='Edit Explorer Template']");
	
	/*================================================================================*/

	/**
	 * Action: View, Edit, Delete
	 * 
	 */
	public void actionOnSelectedView(String viewName, String option, Object...params){
		Boolean verify = (Boolean) (params.length > 0 ? params[0]: false);
		waitForTextPresent(viewName);
		//View
		if (option.equals("View")){
			click(ELEMENT_VIEW_ICON.replace("${viewName}", viewName));
		}else if (option.equals("Edit")){
			//Edit
			click(ELEMENT_EDIT_VIEW.replace("${viewName}", viewName));
			waitForAndGetElement(ELEMENT_EDIT_VIEW_FORM);	
		}else if (option.equals("Delete")){
			//Delete
			click(By.xpath(ELEMENT_DELETE_VIEW.replace("${viewName}", viewName)));     
			alt.acceptAlert();
			if(verify == true) waitForElementNotPresent(By.xpath(ELEMENT_EDIT_VIEW.replace("${viewName}", viewName)));
		}
		Utils.pause(1000);
	}
	
	/**
	 * View a selected view
	 * 
	 */
	public void viewSelectedView(String viewName, Object...params){
		Boolean verifyTemplate = (Boolean) (params.length > 0 ? params[0]: false);
		String template = (String) (params.length > 1 ? params[1]: "");
		
		Boolean verifyAction = (Boolean) (params.length > 2 ? params[2]: false);
		String tab = (String) (params.length > 3 ? params[3]: "");
		String property = (String) (params.length > 4 ? params[4]: "");
		
		Boolean verifyPermission = (Boolean) (params.length > 5 ? params[5]: false);
		String permission = (String) (params.length > 6 ? params[6]: "");
		
		info("-- The current view... " + viewName);
		actionOnSelectedView(viewName, "View");
		waitForAndGetElement(ELEMENT_TEMPLATE_VIEW);
		
		//View Settings tab
		if (verifyTemplate){
			WebElement element = waitForAndGetElement(ELEMENT_SELECTED_TEMPLATE, 3000, 1, 2);
			String temp = element.getAttribute("value");
			info("-----");
			assert temp.equals(template): "Template is not defined";
		}
		//Action tab
		click(ELEMENT_ACTION_TAB);
		if (verifyAction){
			waitForAndGetElement(ELEMENT_ACTION_TAB_NAME.replace("${tabName}", tab));
			waitForTextPresent(property);
		}
		//Permission tab
		click(ELEMENT_ADDVIEW_PERMISSION_TAB);
		if (verifyPermission){
			waitForAndGetElement(ELEMENT_VERIFY_PERMISSION.replace("${permission}", permission));
		}
		button.close();
		Utils.pause(1000);
	}

	/**
	 * function add a view 
	 * @view: name of view need add sub view
	 * @tab: name of tab on action bar that need add view
	 * @viewadd: list of sub view need add
	 */
	/*public void addView(String view, String tab, String viewadd ){
		ecMain.goToManageViews();
		//click(By.xpath("//div[@title='" + view + "']/../..//*[@class='EditInfoIcon']"));
		actionOnSelectedView(view, "Edit");
		click(By.xpath("//a[contains(text(),'" + tab + "')]"));
		selectCheckBoxList(viewadd);
		button.save();
		button.save();
	}*/

	/**
	 * Delete a view
	 * @param viewName : View Name
	 * @param confirmMessage : Confirm Message
	 */
	/*public void deleteView(String viewName, String confirmMessage, boolean verify){
		waitForTextPresent(viewName);
		click(By.xpath(ELEMENT_DELETE_VIEW.replace("${viewName}", viewName)));     
		alt.waitForConfirmation(confirmMessage);
		if(verify == true) waitForElementNotPresent(By.xpath(ELEMENT_EDIT_VIEW.replace("${viewName}", viewName)));
		Utils.pause(1000);
	}*/

	/**
	 * Edit View 
	 * @param viewName : View Name
	 */
	public void editView(String viewName, String template, boolean enableVersion, boolean hideExplorerPanel, Object...params){
		boolean editAction = (Boolean) (params.length > 0 ? params[0] : false);
		String tab =  (String) (params.length > 1 ? params[1] : "");
		String property =  (String) (params.length > 2 ? params[2] : "");
		
		boolean editPermission = (Boolean) (params.length > 3 ? params[3] : false); 
		boolean selectGroup = (Boolean) (params.length > 4 ? params[4] : false);
		String group =  (String) (params.length > 5 ? params[5] : "");
		String membership =  (String) (params.length > 6 ? params[6] : "");
		boolean selectUser = (Boolean) (params.length > 7 ? params[7] : false);
		String user =  (String) (params.length > 8 ? params[8] : "");
		
		waitForAndGetElement(By.xpath(ELEMENT_DISPLAY_VIEW_ITEM.replace("${viewName}", viewName)));
		
		info("-- Editing a view -- " + viewName);
		actionOnSelectedView(viewName, "Edit");
		//Settings tab
		if (!template.isEmpty()){
			select(ELEMENT_TEMPLATE_VIEW, template);
		}
		if (enableVersion){
			click(ELEMENT_ENABLE_VERSION, 2);
		}
		if (waitForAndGetElement(ELEMENT_HIDE_EXPLORER_PANEL, 3000, 1, 2)!= null){
			if (hideExplorerPanel){
				check(ELEMENT_HIDE_EXPLORER_PANEL, 2);
			}else {
				uncheck(ELEMENT_HIDE_EXPLORER_PANEL, 2);
			}
		}
		//Edit Action
		if (editAction){
			click(ELEMENT_ACTION_TAB);
			click(ELEMENT_EDIT_ACTION_ICON.replace("${tabName}", tab));
			selectCheckBoxList(property);
			click(ELEMENT_SAVE_BUTTON_EDIT_VIEW_TAB);
			waitForTextNotPresent("Tab Name");
		}
		//Set permission
		if (editPermission){
			click(ELEMENT_ADDVIEW_PERMISSION_TAB);
			if (selectGroup){
				selectMembership(group, membership, "Select Member");
			}
			if(selectUser){
				selectUser(user);
			}
			click(ELEMENT_ADDVIEW_PERMISSION_BUTTON);
			}
		button.save();
		Utils.pause(1000);
	}

	/**
	 * @param anchor: Button's label to open form
	 * @param formTitle: Form's title   
	 */
	public void openAddViewForm(String anchor, String formTitle){
		//click(By.linkText(anchor));     
		click(By.xpath("//*[text()='" + anchor + "']"));
		waitForAndGetElement(By.xpath("//*[contains(@class, 'popupTitle') and text()='" + formTitle + "']"));
	}

	/**
	 * 
	 * @param name : Template name
	 * @param template : Template view
	 */
	public void fillAddNewViewForm(String name, String template, String tabName, String property, Object...params) {
		boolean enableVersion = (Boolean) (params.length > 0 ? params[0] : false);
		boolean selectGroup = (Boolean) (params.length > 1 ? params[1] : false);
		String group =  (String) (params.length > 2 ? params[2] : "");
		String membership =  (String) (params.length > 3 ? params[3] : "");
		boolean selectUser = (Boolean) (params.length > 4 ? params[4] : false);
		String user =  (String) (params.length > 5 ? params[5] : "");

		//addTab
		click(ELEMENT_ACTION_TAB);
		click(button.ELEMENT_ADD_BUTTON);
		type(ELEMENT_TAB_NAME, tabName, true);
		selectCheckBoxList(property);
		click(ELEMENT_SAVE_BUTTON_EDIT_VIEW_TAB);
		waitForTextNotPresent("Tab Name");

		//Set permission
		click(ELEMENT_ADDVIEW_PERMISSION_TAB);
		if (selectGroup){
			selectMembership(group, membership, "Select Member");
		}
		if(selectUser){
			selectUser(user);
		}
		click(ELEMENT_ADDVIEW_PERMISSION_BUTTON);

		//Settings/View Tab
		if (waitForAndGetElement(ELEMENT_SETTING_TAB, 3000, 0, 2) != null){
			click(ELEMENT_SETTING_TAB);
		}else {
			click(ELEMENT_VIEW_TAB);
		}
		Utils.pause(500);
		type(ELEMENT_TEMPLATE_VIEW_NAME, name, true);
		select(ELEMENT_TEMPLATE_VIEW, template);
		if (enableVersion){
			click(ELEMENT_ENABLE_VERSION, 2);
		}
		button.save();
		select(By.xpath("//select[contains(@id,'maxPageSize')]"),"20");
		waitForAndGetElement(ELEMENT_EDIT_VIEW.replace("${viewName}", name));
		Utils.pause(1000);
	}

	/**
	 * Fill data to Add New EXPLORER Template Form
	 * @param templateContent : Template Content
	 * @param templateName : Template Name
	 * @param templateType : Template Type
	 */
	public void fillExplorerTemplateForm(String templateContent, String templateName, String templateType){
		type(ELEMENT_EXPLORER_TEMPLATE_CONTENT, templateContent, true);
		type(ELEMENT_EXPLORER_TEMPLATE_NAME, templateName, true);
		select(ELEMENT_EXPLORER_TEMPLATE_TYPE, templateType);
		button.save();      
		waitForAndGetElement(By.xpath(ELEMENT_DISPLAY_VIEW_ITEM.replace("${viewName}", templateName)));
	}

	/**
	 * Add tab to view
	 * @param tabname : Tab name
	 * @param property : Tab Property
	 */
	public void addTab(String tabname, String property){
		ecMain.goToManageViews();
		click(ELEMENT_EDIT_VIEW.replace("${viewName}", "Web"));
		click(ELEMENT_ACTION_TAB);
		click(button.ELEMENT_ADD_BUTTON);
		type(ELEMENT_TAB_NAME, tabname, true);
		selectCheckBoxList(property);
		button.save();
		waitForTextNotPresent("Tab Name");
		button.save();
		Utils.pause(1000);
	}

	/**
	 * Add an action to a view
	 * @param viewAction
	 */
	public void setup2ShowViewAction(String viewAction, String...viewType){
		String view = viewType.length > 0 ? viewType[0] : "Web";
		String tab = viewType.length > 1 ? viewType[1] : "Authoring";

		ecMain.goToManageViews();
		click(ELEMENT_EDIT_VIEW.replace("${viewName}", view));
		click(ELEMENT_ACTION_TAB);
		click(ELEMENT_EDIT_ACTION_ICON.replace("${tabName}", tab));
		selectCheckBoxList(viewAction);
		click(ELEMENT_SAVE_BUTTON_EDIT_VIEW_TAB);
		button.save();
		Utils.pause(1000);
	}

	/**
	 * Create version for a view
	 * @param viewName : View Name
	 * @param versionNumber : Number of version that you want to create. It require 2 version at least
	 */
	public void createVersion(String viewName, int versionNumber){
		if(versionNumber < 2){
			assert false : ("Number of versions is required 2 at least");
		}
		for(int i = 0; i < versionNumber ; i++) {
			//editView(viewName);
			click(ELEMENT_EDIT_VIEW.replace("${viewName}", viewName));
			waitForAndGetElement(ELEMENT_EDIT_EXPLORER_TEMPLATE_TAB);
			if(getElement(ELEMENT_ENABLE_VERSION).isEnabled()){ 
				click(ELEMENT_ENABLE_VERSION, 2);          
			}
			button.save();
			Utils.pause(500);
		}
	}

	/**
	 * Restore a view to a certain version
	 * @param viewName : View Name
	 * @param orderVersion : Version that want to restore
	 */
	public void restoreVersion(String viewName, int orderVersion){
		//editView(viewName);
		click(ELEMENT_EDIT_VIEW.replace("${viewName}", viewName));
		waitForAndGetElement(ELEMENT_EDIT_EXPLORER_TEMPLATE_TAB);
		String order = "" + orderVersion;
		if (isElementPresent(ELEMENT_VERSION_OPTION)){
			select(ELEMENT_VERSION_OPTION, order);
		}else if (isElementPresent(By.name("version"))){
			select(By.name("version"), order);
		} 
		//click(By.linkText("Restore"));
		click(button.ELEMENT_RESTORE_BUTTON);
		//waitForElementPresent(By.xpath("//div[@class='Text' and contains(text(),'" + viewName + "')]/ancestor::tr//div[@class='Text' and text()='" + orderVersion + "']"));
		alt.waitForMessage("Version 1 was restored successfully");
		click(button.ELEMENT_OK_BUTTON);
		Utils.pause(500);
	}
}