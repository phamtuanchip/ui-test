package org.exoplatform.selenium.platform.ecms.admin;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * 
 * @author vuna2
 *
 */
public class ManageTemplate extends EcmsBase{

	public ManageTemplate(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	NavigationToolbar navToolbar = new NavigationToolbar(driver);
	Button button = new Button(driver);
	Dialog dialog = new Dialog(driver);
	ManageAlert alt = new ManageAlert(driver);
	ManageView magView = new ManageView(driver);

	//Manage Template    
	public final By ELEMENT_TEMPLATE_LABEL = By.name("label");
	public final By ELEMENT_TEMPLATE_NAME = By.name("name");
	public final By ELEMENT_IS_DOCUMENT_TEMPLATE = By.id("isDocumentTemplate");
	public final String ELEMENT_TEMPLATE_TAB = "//*[text()='${popupTitle}']/../..//*[text()='Template']";
	public final By ELEMENT_DIALOG_TAB = By.xpath("//*[text()='Dialog']");
	public final By ELEMENT_VIEW_TAB = By.xpath("//*[text()='View']");
	public final By ELEMENT_CSS_TAB = By.xpath("//*[text()='CSS']");
	public final By ELEMENT_TEMPLATE_FORM = By.xpath("//span[contains(text(),'Template Form')]");
	public final By ELEMENT_ADD_TEMPLATE_BUTTON = By.xpath("//*[text()='Add Template']");
	public final By ELEMENT_NAVIGATION_ADD_TEMPLATE_BUTTON = By.xpath("//*[@class='CateTemplateList']//*[text()='Add Template']");
	public final By ELEMENT_PAGINATOR_ADD_TEMPLATE_BUTTON = By.xpath("//*[@class='PageTemplateList']//*[text()='Add Template']");
	public final String ELEMENT_TAB = "//*[@class = 'title' and contains(text(), '${typeTemplate}')]/..//*[text()='${tab}']";
	public final By ELEMENT_ACTION_TAB = By.xpath(ELEMENT_TAB.replace("${typeTemplate}", "Documents").replace("${tab}", "Actions"));
	public final By ELEMENT_OTHER_TAB = By.xpath(ELEMENT_TAB.replace("${typeTemplate}", "Documents").replace("${tab}", "Others"));
	public final String ELEMENT_VIEW_TEMPLATE_ICON = "//*[contains(text(),'${templateName}')]/..//*[@class='uiIconView']";
	public final String ELEMENT_EDIT_METADATA_TEMPLATE_ICON = "//*[contains(text(),'${templateName}')]/..//*[@class='uiIconEdit']";
	public final String ELEMENT_EDIT_TEMPLATE_ICON = "//*[contains(text(),'${templateName}')]/../..//*[@class='uiIconEdit']";
	public final String ELEMENT_DELETE_TEMPLATE_ICON = "//*[contains(text(),'${templateName}')]/../..//*[@class='uiIconDelete']";
	public final String ELEMENT_DELETE_METADATA_TEMPLATE_ICON = "//*[contains(text(),'${templateName}')]/..//*[@class='uiIconDelete']";
	
	/*Added by PhuongDT
	 *Date: 28/08/2013 
	*/
	public final By ELEMENT_ADD_TEMPLATE_DOCUMENT_BUTTON = By.xpath("//*[@id = 'tab-UITemplateContainer']//*[text()='Add Template']");
	public final By ELEMENT_ADD_TEMPLATE_ACTION_BUTTON = By.xpath("//*[@id = 'tab-UIActionsTemplateContainer']//*[text()='Add Template']");
	public final By ELEMENT_ADD_TEMPLATE_OTHER_BUTTON = By.xpath("//*[@id = 'tab-UIOthersTemplateContainer']//*[text()='Add Template']");
	public final By ELEMENT_TEMPLATE_ACTION_DIALOG_CONTENT = By.id("dialog");
	
	
	/*End Add*/
	//Edit template
	public final String ELEMENT_CONTENT = "//*[@id='${tab}']//*[@name='content']";
	public final String ELEMENT_INPUT_NAME = "//*[@id='${tab}']//*[@name='name']";
	public final String ELEMENT_ADD_PERMISSION = "//*[@id='${tab}']//*[contains(@class, 'uiIconAddPermission')]";
	public final String ELEMENT_TEMPLATE_SAVE_BUTTON = "//*[@id='${tab}']//*[text()='Save']";
	public final By ELEMENT_TEMPLATE_TAB_SAVE_BUTTON = By.xpath(ELEMENT_TEMPLATE_SAVE_BUTTON.replace("${tab}", "UITemplateEditForm"));

	public final By ELEMENT_DIALOG_CONTENT = By.xpath(ELEMENT_CONTENT.replace("${tab}", "UIDialogTab"));
	public final By ELEMENT_DIALOG_NAME = By.xpath(ELEMENT_INPUT_NAME.replace("${tab}", "UIDialogTab"));
	public final By ELEMENT_DIALOG_PERMISSION_ICON = By.xpath(ELEMENT_ADD_PERMISSION.replace("${tab}", "UIDialogTab"));
	public final By ELEMENT_DIALOG_SAVE_BUTTON = By.xpath(ELEMENT_TEMPLATE_SAVE_BUTTON.replace("${tab}", "UIDialogTab"));

	public final By ELEMENT_VIEW_CONTENT = By.xpath(ELEMENT_CONTENT.replace("${tab}", "UIViewTab"));
	public final By ELEMENT_VIEW_NAME = By.xpath(ELEMENT_INPUT_NAME.replace("${tab}", "UIViewTab"));
	public final By ELEMENT_VIEW_PERMISSION_ICON = By.xpath(ELEMENT_ADD_PERMISSION.replace("${tab}", "UIViewTab"));
	public final By ELEMENT_VIEW_SAVE_BUTTON = By.xpath(ELEMENT_TEMPLATE_SAVE_BUTTON.replace("${tab}", "UIViewTab"));

	public final By ELEMENT_CSS_CONTENT = By.xpath(ELEMENT_CONTENT.replace("${tab}", "UISkinTab"));
	public final By ELEMENT_CSS_NAME = By.xpath(ELEMENT_INPUT_NAME.replace("${tab}", "UISkinTab"));
	public final By ELEMENT_CSS_PERMISSION_ICON = By.xpath(ELEMENT_ADD_PERMISSION.replace("${tab}", "UISkinTab"));
	public final By ELEMENT_CSS_SAVE_BUTTON = By.xpath(ELEMENT_TEMPLATE_SAVE_BUTTON.replace("${tab}", "UISkinTab"));

	//Manage template > List template
	public final By ELEMENT_LIST_TEMPLATE_CONTENT = By.name("content");
	public final By ELEMENT_LIST_TEMPLATE_TITLE = By.name("title");
	public final By ELEMENT_LIST_TEMPLATE_NAME = By.name("template");
	public final By ELEMENT_LIST_TEMPLATE_TYPE = By.name("type");
	public final By ELEMENT_LIST_TEMPLATE_CONTENT_TAB = By.xpath(ELEMENT_TAB.replace("${typeTemplate}", "List").replace("${tab}", "Content"));
	public final By ELEMENT_LIST_TEMPLATE_NAVIGATION_TAB = By.xpath(ELEMENT_TAB.replace("${typeTemplate}", "List").replace("${tab}", "Navigation"));
	public final By ELEMENT_LIST_TEMPLATE_PAGINATOR_TAB = By.xpath(ELEMENT_TAB.replace("${typeTemplate}", "List").replace("${tab}", "Paginator"));
	public final By ELEMENT_SELECT_MEMBERSHIP = By.xpath("//img[@alt='Select Membership']");
	public final By ELEMENT_SELECT_USERGROUP = By.id("SelectUserOrGroup");
	public final By ELEMENT_SELECT_MEMBERSHIP_TYPE = By.linkText("platform");
	//public final By ELEMENT_SELECT_MEMBERSHIP_WEB_CONTRIBUTORS = By.linkText("web-contributors");

	//Manage Template > Metadata
	public final By ELEMENT_METADATA_INFORMATION_POPUP = By.xpath("//*[contains(@class, 'popupContent')]//*[text()='Metadata Information']");
	public final By ELEMENT_METADATA_ELEMENTS = By.className("metadataElements");
	public final String ELEMENT_METADATA_TAB = "//*[contains(@class, 'popupContent')]//*[text()='${tab}']";
	public final By ELEMENT_INPUT_METADATA_LABEL = By.name("metadataLabel");
	public final By ELEMENT_METADATA_ADD_PERMISSION_ICON = By.xpath("//*[contains(@class, 'uiIconAddPermission')]");
	public final By ELEMENT_INPUT_DIALOG_TEMPLATE = By.name("dialogTemplate");
	public final By ELEMENT_INPUT_VIEW_TEMPLATE = By.name("viewTemplate");
	
	/*==================================================================*/

	/* 
	 * Modified by: PhuongDT 
	 * Date: 28/08/2013
	 * Content: Change ELEMENT_ADD_TEMPLATE_BUTTON
	*/
	//Open Add New Template Form
	public void openAddNewTemplateForm(Object...params) {
		String temp = (String) (params.length > 0 ? params[0] : "");
		info("-- Open [Add Template] form --");
		if (temp.equals("Actions")){
			click(ELEMENT_ACTION_TAB);
			click(ELEMENT_ADD_TEMPLATE_ACTION_BUTTON);
		}else if (temp.equals("Others")){
			click(ELEMENT_OTHER_TAB);
			click(ELEMENT_ADD_TEMPLATE_OTHER_BUTTON);
		}else {
			info("-- Add a new template for documents--");
			click(ELEMENT_ADD_TEMPLATE_DOCUMENT_BUTTON);
		}
		//click(ELEMENT_ADD_TEMPLATE_BUTTON);	
		waitForAndGetElement(By.xpath("//*[contains(@class, 'popupTitle') and text()='Template Form']"));	    
	}

	/**
	 * Fill data to Add New Template Form
	 * @param label: Template's label
	 * @param groupId: Group string is separate by slash, for example platform/web-contributors
	 * @param membership: Membership
	 */
	public void fillInTemplateForm(String templateName, String templateLabel, String groupPath, String membership, Object... params) {   
		boolean isDocumentTemplate = (Boolean) (params.length > 0 ? params[0]: false);

		select(ELEMENT_TEMPLATE_NAME, templateName);
		type(ELEMENT_TEMPLATE_LABEL, templateLabel, false);
		if (isDocumentTemplate){
			click(ELEMENT_IS_DOCUMENT_TEMPLATE, 2);
		}
		selectMembership(groupPath, membership, "AddPermission");      
		//Switch between tabs
		//click(ELEMENT_DIALOG_TAB);   
		String dialogContent =  (String) (params.length > 1 ? params[1]: "");
		if(!dialogContent.isEmpty())
			fillInTemplateDialogTab(dialogContent);
		click(ELEMENT_VIEW_TAB);
		click(ELEMENT_CSS_TAB);
		button.save();
		waitForAndGetElement(By.xpath("//div[@class='Text' and contains(text(),'" + templateName + "')]"));    
		Utils.pause(1000);
	}
	/*End Modified*/
	/**
	 * @Added by: PhuongDT
	 * @date: 26/08/2013
	 * Content: Add content to dialog tab
	 */
	public void fillInTemplateDialogTab(String dialogContent){
		click(ELEMENT_DIALOG_TAB); 
		String content = Utils.getFileContent(dialogContent);
		Utils.pause(500);
		/*send key to dialog: use copy to clipboard*/
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		StringSelection strSel = new StringSelection(content);
		clipboard.setContents(strSel, null);
		
		WebElement element = driver.findElement(ELEMENT_TEMPLATE_ACTION_DIALOG_CONTENT);
		element.clear();
		element.sendKeys(Keys.CONTROL+"v");
	}
	/*End Added*/

	//Edit Template form
	public void editTemplate(String templateName, String templateLabel, Object... params){
		Boolean dialog = (Boolean) (params.length > 0 ? params[0]: false);
		String dialogContent =  (String) (params.length > 1 ? params[1]: "");
		String dialogName = (String) (params.length > 2 ? params[2]: "");
		String dialogGroupPer = (String) (params.length > 3 ? params[3]: "");
		String dialogMembershipPer = (String) (params.length > 4 ? params[4]: "");

		Boolean view = (Boolean) (params.length > 5 ? params[5]: false);
		String viewName = (String) (params.length > 6 ? params[6]: "");
		String viewContent = (String) (params.length > 7 ? params[7]: ""); 
		String viewGroupPer = (String) (params.length > 8 ? params[8]: ""); 
		String viewMembershipPer = (String) (params.length > 9 ? params[9]: "");

		Boolean css = (Boolean) (params.length > 10 ? params[10]: false);
		String cssContent = (String) (params.length > 11 ? params[11]: "");
		String cssName = (String) (params.length > 12 ? params[12]: "");
		String cssGroupPer = (String) (params.length > 13 ? params[13]: "");
		String cssMembershipPer = (String) (params.length > 14 ? params[14]: ""); 

		info("-- Edit Template --" + templateName);
		click(ELEMENT_EDIT_TEMPLATE_ICON.replace("${templateName}", templateName));	
		//Dialog tab
		if (dialog){
			click(ELEMENT_DIALOG_TAB);
			Utils.pause(500);
			String content = Utils.getFileContent(dialogContent);
			type(ELEMENT_DIALOG_CONTENT, content, true);
			type(ELEMENT_DIALOG_NAME, dialogName, true);
			click(ELEMENT_DIALOG_PERMISSION_ICON);
			userGroup.selectGroup(dialogGroupPer, false);
			click(By.linkText(dialogMembershipPer));
			click(ELEMENT_DIALOG_SAVE_BUTTON);
			//waitForTextPresent(dialogName);
			Utils.pause(1000);
		}
		//View tab
		if (view){
			click(ELEMENT_VIEW_TAB);
			Utils.pause(500);
			String content = Utils.getFileContent(viewContent);
			type(ELEMENT_VIEW_CONTENT, content, true);
			type(ELEMENT_VIEW_NAME, viewName, true);
			click(ELEMENT_VIEW_PERMISSION_ICON);
			userGroup.selectGroup(viewGroupPer, false);
			click(By.linkText(viewMembershipPer));
			click(ELEMENT_VIEW_SAVE_BUTTON);
			//waitForTextPresent(viewName);
			Utils.pause(1000);
		}
		//Css tab
		if (css){
			click(ELEMENT_CSS_TAB);
			Utils.pause(500);
			String content = Utils.getFileContent(cssContent);
			type(ELEMENT_CSS_CONTENT, content, true);
			type(ELEMENT_CSS_NAME, cssName, true);
			click(ELEMENT_CSS_PERMISSION_ICON);
			userGroup.selectGroup(cssGroupPer, false);
			click(By.linkText(cssMembershipPer));
			click(ELEMENT_CSS_SAVE_BUTTON);
			//waitForTextPresent(cssName);
			Utils.pause(1000);
		}
		click(ELEMENT_TEMPLATE_TAB.replace("${popupTitle}", "View & Edit Template"));
		Utils.pause(1000);
		if (!templateLabel.isEmpty()){
			type(ELEMENT_TEMPLATE_LABEL, templateLabel, true);
			Utils.pause(500);
		}
		click(ELEMENT_TEMPLATE_TAB_SAVE_BUTTON);
		if (!templateLabel.isEmpty()){
			waitForAndGetElement(ELEMENT_EDIT_TEMPLATE_ICON.replace("${templateName}", templateLabel));
		}
	}

	//Delete a template 
	public void deleleTemplate(String templateName, Object... params){
		info("-- Deleting the template: --" + templateName);
		boolean notDeleteTem = (Boolean) (params.length > 0 ? params[0]: true);

		By locator = By.xpath(ELEMENT_DELETE_TEMPLATE_ICON.replace("${templateName}", templateName));
		//click(locator);
		WebElement delete = driver.findElement(locator);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", delete);
		//alt.waitForConfirmation(confirmMessage);
		if (notDeleteTem){
			alt.acceptAlert();
			waitForElementNotPresent(By.xpath("//div[@class='Text' and contains(text(),'" + templateName + "')]"));
		}
		Utils.pause(1000);
	}

	//Open Add New List Template Form
	public void openAddListTemplateForm(String templateType){
		info("-- Open [Add Template] Form --");
		Utils.pause(500);
		if ( ExpectedConditions.alertIsPresent() != null ){
			alt = new ManageAlert(driver);
			alt.acceptAlert();
			info("-- An alert is present --");
		}
		if (waitForAndGetElement(ELEMENT_LIST_TEMPLATE_CONTENT, 3000, 0) == null){
			if (templateType.equals("Content")){
				click(ELEMENT_ADD_TEMPLATE_BUTTON);
			}else if (templateType.equals("Navigation")){
				click(ELEMENT_LIST_TEMPLATE_NAVIGATION_TAB);
				click(ELEMENT_NAVIGATION_ADD_TEMPLATE_BUTTON);
			}else if (templateType.equals("Paginator")){
				click(ELEMENT_LIST_TEMPLATE_PAGINATOR_TAB);
				click(ELEMENT_PAGINATOR_ADD_TEMPLATE_BUTTON);
			}
		}
	}

	//Add a new List Template
	public void addNewListTemplate(String templateTitle, String templateName, String templateType, Object... params){
		boolean content = (Boolean) (params.length > 0 ? params[0]: false);
		String templateContent = (String) (params.length > 1 ? params[1]: "");
		boolean getFile = (Boolean) (params.length > 2 ? params[2]: false);
		boolean verify = (Boolean) (params.length > 3 ? params[3]: false);

		//Open 
		openAddListTemplateForm(templateType);
		info("-- Adding a new list template ... --");

		//Add Content
		if (content){
			if (getFile){
				String cTemp = Utils.getFileContent(templateContent);
				Utils.pause(500);
				type(ELEMENT_LIST_TEMPLATE_CONTENT, cTemp, true);
			}else {
				type(ELEMENT_LIST_TEMPLATE_CONTENT, templateContent, true);
			}
		}
		type(ELEMENT_LIST_TEMPLATE_TITLE, templateTitle, false);
		type(ELEMENT_LIST_TEMPLATE_NAME, templateName, false);
		select(ELEMENT_LIST_TEMPLATE_TYPE, templateType);
		button.save();
		if ( ExpectedConditions.alertIsPresent() != null ){
			alt.acceptAlert();
		}
		if (verify){		
			if (templateType.equals("Content")){
				waitForTextPresent(templateTitle);
			}else if (templateType.equals("Navigation")){
				click(ELEMENT_LIST_TEMPLATE_NAVIGATION_TAB);
				waitForTextPresent(templateTitle);
			}else if (templateType.equals("Paginator")){
				click(ELEMENT_LIST_TEMPLATE_PAGINATOR_TAB);
				waitForTextPresent(templateTitle);
			}
		}
		Utils.pause(500);
	}

	//Edit a List Template
	public void editListTemplate(String templateTitle, String editTemplateTitle, String templateType, Object...params){
		boolean content = (Boolean) (params.length > 0 ? params[0]: false);
		String templateContent = (String) (params.length > 1 ? params[1]: "");
		boolean getFile = (Boolean) (params.length > 2 ? params[2]: false);

		info("-- Edit List Template --" + templateTitle);
		if (templateType.equals("Content")){
			click(ELEMENT_LIST_TEMPLATE_CONTENT_TAB);
			click(ELEMENT_EDIT_TEMPLATE_ICON.replace("${templateName}", templateTitle));
		}else if (templateType.equals("Navigation")){
			click(ELEMENT_LIST_TEMPLATE_NAVIGATION_TAB);
			click(ELEMENT_EDIT_TEMPLATE_ICON.replace("${templateName}", templateTitle));
		}else if (templateType.equals("Paginator")){
			click(ELEMENT_LIST_TEMPLATE_PAGINATOR_TAB);
			click(ELEMENT_EDIT_TEMPLATE_ICON.replace("${templateName}", templateTitle));
		}

		//Edit a content
		if (content){
			if (getFile){
				String cTemp = Utils.getFileContent(templateContent);
				Utils.pause(500);
				type(ELEMENT_LIST_TEMPLATE_CONTENT, cTemp, true);
			}else {
				type(ELEMENT_LIST_TEMPLATE_CONTENT, templateContent, true);
			}
		}

		//Edit the title
		if (!editTemplateTitle.isEmpty()){
			type(ELEMENT_LIST_TEMPLATE_TITLE, editTemplateTitle, true);
		}

		//Select Template Type
		select(ELEMENT_LIST_TEMPLATE_TYPE, templateType);
		button.save();
		if ( ExpectedConditions.alertIsPresent() != null ){
			alt.acceptAlert();
		}
		if (templateType.equals("Content") && !editTemplateTitle.isEmpty()){
			waitForTextPresent(editTemplateTitle);
		}else if (templateType.equals("Navigation") && !editTemplateTitle.isEmpty()){
			click(ELEMENT_LIST_TEMPLATE_NAVIGATION_TAB);
			waitForTextPresent(editTemplateTitle);
		}else if (templateType.equals("Paginator") && !editTemplateTitle.isEmpty()){
			click(ELEMENT_LIST_TEMPLATE_PAGINATOR_TAB);
			waitForTextPresent(editTemplateTitle);
		}
		Utils.pause(500);
	}

	//Actions on Selected Metadata: View/Edit/Delete
	public void actionsOnMetadata(String metadata, String option){
		info(option + " Metadata " + metadata);
		if (option.equals("View")){
			click(ELEMENT_VIEW_TEMPLATE_ICON.replace("${templateName}", metadata));
			waitForAndGetElement(ELEMENT_METADATA_INFORMATION_POPUP);
			waitForAndGetElement(ELEMENT_METADATA_ELEMENTS);
			button.close();
		}else if (option.equals("Edit")){
			click(ELEMENT_EDIT_METADATA_TEMPLATE_ICON.replace("${templateName}", metadata));
			waitForAndGetElement(ELEMENT_METADATA_TAB.replace("${tab}", "Metadata Type"));
		}else if (option.equals("Delete")){
			click(ELEMENT_DELETE_METADATA_TEMPLATE_ICON.replace("${templateName}", metadata));
			alt.acceptAlert();
			click(button.ELEMENT_OK_BUTTON);
			waitForElementNotPresent(ELEMENT_DELETE_METADATA_TEMPLATE_ICON.replace("${templateName}", metadata));
		}
		Utils.pause(500);	
	}

	//Edit a metadata
	//permission = {group, membership}
	public void editMetadata(String metadata, String[] permission, String label, Object...params){
		String group = permission[0];
		String membership = permission[1];
		
		Boolean dialog = (Boolean) (params.length > 0 ? params[0]: false);
		String dialogTemplate = (String) (params.length > 1 ? params[1]: "");
		Boolean view = (Boolean) (params.length > 2 ? params[2]: false);
		String viewTemplate = (String) (params.length > 3 ? params[3]: "");
		
		info("-- Editing Metadata " + metadata);
		actionsOnMetadata(metadata, "Edit");

		//Edit a permission
		click(ELEMENT_METADATA_ADD_PERMISSION_ICON);
		userGroup.selectGroup(group, false);
		click(By.linkText(membership));

		//Edit Metadata label
		if (!label.isEmpty()){
			info("-- Edit Metadata Label --");
			type(ELEMENT_INPUT_METADATA_LABEL, label, true);
		}

		//Edit a dialog template
		if (dialog){
			info("-- Edit a dialog template --");
			click(ELEMENT_METADATA_TAB.replace("${tab}", "Dialog Template"));
			String cTemp = Utils.getFileContent(dialogTemplate);
			Utils.pause(500);
			type(ELEMENT_INPUT_DIALOG_TEMPLATE, cTemp, true);
		}else {
			info("-- Edit a dialog template --");
			click(ELEMENT_METADATA_TAB.replace("${tab}", "Dialog Template"));
			type(ELEMENT_INPUT_DIALOG_TEMPLATE, dialogTemplate, true);
		}
		
		//Edit a view template
		if (view){
			info("-- Edit a view template --");
			click(ELEMENT_METADATA_TAB.replace("${tab}", "View Template"));
			String cTemp = Utils.getFileContent(viewTemplate);
			Utils.pause(500);
			type(ELEMENT_INPUT_VIEW_TEMPLATE, cTemp, true);
		}else{
			info("-- Edit a view template --");
			click(ELEMENT_METADATA_TAB.replace("${tab}", "View Template"));
			type(ELEMENT_INPUT_VIEW_TEMPLATE, viewTemplate, true);
		}	
		button.apply();
		if (!label.isEmpty()){
			waitForTextPresent(label);
		}
		Utils.pause(500);
	}
	////////////
}