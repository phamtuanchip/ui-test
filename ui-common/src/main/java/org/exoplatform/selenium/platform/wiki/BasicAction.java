package org.exoplatform.selenium.platform.wiki;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Migrate to PLF 4
 * @author vuna2
 *
 */
public class BasicAction extends Permission{

	//Dialog dialog = new Dialog(driver);
	//Button button = new Button(driver);
	//ManageAlert magAlert;

	Dialog dialog = new Dialog(driver);
	Button button = new Button(driver);
	ManageAlert magAlert = new ManageAlert(driver);

	// Wiki page
	/*===================== Add Page ====================*/	

	/** Add a wiki page from blank
	 * @author thuntn
	 * @param title
	 * @param content
	 * @param mode =1: edit a wiki page in richtext
	 * mode =0 : edit a wiki page in source editor
	 */
	public void addBlankWikiPage(String title, String content, int mode, Object... option){
		info("-- Adding a new wiki page... --");
		//boolean  verify = option.length > 0 ? option[0] : false;
		//boolean ca = (Boolean) (option.length > 0 ? option[0] : false);
		String message = (String) (option.length > 1 ? option[1] : "");	
		goToAddBlankPage();
		driver.navigate().refresh();
		Utils.pause(2000);
		
		info("-- Add a wiki page from blank page --");
		if (mode == 1){ 
			addWikiPageRichText(title, content);
		}
		else{
			addWikiPageSourceEditor(title, content);
		}
		switchToParentWindow();
		Utils.pause(500);
		boolean ca = (Boolean) (option.length > 0 ? option[0] : false);
		if (ca){
			info("-- Cancel Wiki Page --");
			click(ELEMENT_CANCEL_BUTTON_ADD_PAGE);
			waitForWikiConfirmation(MESSAGE_CANCEL_CREATE_PAGE);
			//waitForWikiConfirmation("Are you sure to leave this page?");
			waitForElementNotPresent(ELEMENT_TITLE_WIKI_INPUT);
		}else{
			info("-- Saving Wiki Page... --");
			click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
			//waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		}

		if (!message.isEmpty()){ 
			info(message);
			//waitForMessage(message);
			if (waitForAndGetElement(button.ELEMENT_OK_BUTTON, 3000, 0, 2) != null){
				click(button.ELEMENT_OK_BUTTON);
				click(ELEMENT_CANCEL_BUTTON_ADD_PAGE);
			}else {
				click(ELEMENT_CONFIRM_BUTTON_ADD_PAGE);
			}
		}

		/*if (verify){
			waitForTextPresent(content);
		}*/
		Utils.pause(1000);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);
	}

	/**
	 * Modify data with source editor
	 * @author hakt
	 * updated: thuntn
	 * @param title
	 * @param content
	 */
	public void addWikiPageSourceEditor(String title, String content){
		info("Modify data with source editor");
		if(title != null){
			type(ELEMENT_TITLE_WIKI_INPUT, title, true);
		}	
		if(content != null){
			type(ELEMENT_CONTENT_WIKI_INPUT,content,true);
		}	
		//waitForAndGetElement(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		Utils.pause(1000);
	}

	/**
	 * Modify data with rich editor
	 * @author hakt
	 * updated: thuntn
	 * @param title
	 * @param content
	 */
	public void addWikiPageRichText(String title, String content){
		if(title != null)
			type(ELEMENT_TITLE_WIKI_INPUT, title, true);
		click(ELEMENT_RICHTEXT_BUTTON);
		waitForAndGetElement(ELEMENT_SOURCE_EDITOR_BUTTON);
		if (content != null){
			inputDataToFrame(ELEMENT_CONTENT_WIKI_FRAME, content,true);
			Utils.pause(1000);
			driver.switchTo().defaultContent();
		}
		//waitForAndGetElement(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		Utils.pause(1000);
	}

	/**
	 * @author vuna2
	 * @param totalPages: number of created page (int)
	 * @param wikiParentPath: parent (path) of created page 
	 * @param titlePage: page name (String)
	 * @param contentPage: page content (String)  
	 * @param mode = 1 : edit a wiki page in richtext
	 *        mode = 0 : edit a wiki page in source editor
	 */
	public void addBlankWikiPageAdvanceForm(int totalPages, String[] wikiParentPath, String[] titlePage, String[] contentPage, int mode){
		goToWiki();	
		for (int i = 0; i < totalPages; i++){
			goToWikiPage(wikiParentPath[i]);
			addBlankWikiPage(titlePage[i], contentPage[i], mode);
		}
		Utils.pause(1000);
	}

	/** Edit a wiki page
	 * @author thuntn
	 * @param title
	 * @param content
	 * @param mode =0 : edit a wiki page in source editor
	 * mode =1: edit a wiki page in richtext
	 * If you don't want to edit any field, you can pass null value to the respective parameter
	 */
	public void editWikiPage(String title, String content, int mode)
	{
		info("--Edit a wiki page--");
		Utils.pause(1000);
		mouseOverAndClick(ELEMENT_EDIT_PAGE_LINK);
		waitForElementNotPresent(ELEMENT_EDIT_PAGE_LINK);
		driver.navigate().refresh();
		Utils.pause(2000);
		if(mode == 0){
			addWikiPageSourceEditor(title, content);
		}else{
			addWikiPageRichText(title, content);
		}
		//In PLF4, there is no more Minor Edit Option
		/*if (minorEdit) {
			click(ELEMENT_MINOR_EDIT_BUTTON);
			waitForElementNotPresent(ELEMENT_MINOR_EDIT_BUTTON);
		}*/		
		//save();
		switchToParentWindow();
		Utils.pause(500);
		//click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		mouseOverAndClick(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		Utils.pause(2000);
	}

	//===========Delete wiki page ===========//
	/** Delete a wiki page
	 * @author thuntn
	 */
	public void deleteCurrentWikiPage(boolean... cancel)
	{
		boolean ca = cancel.length > 0 ? cancel[0] : false;
		info("--Delete a wiki page--");
		goToDeletePage();
		if (ca){
			click(button.ELEMENT_CANCEL_BUTTON);
			waitForElementNotPresent(button.ELEMENT_CANCEL_BUTTON);
		}else{
			click(button.ELEMENT_OK_BUTTON);
			waitForElementNotPresent(button.ELEMENT_OK_BUTTON);
		}
		Utils.pause(1000);
	}

	/**
	 * @author vuna2
	 * @param wikiPath: an element path indicates how to access wiki page (eg, "Wiki home/WikiTest")
	 */
	public void deleteWikiPage(String[] wikiPath){
		String[] nodes = null;
		String pageName = "";
		Utils.pause(500);
		for(int i = 0; i < wikiPath.length; i++){
			nodes = wikiPath[i].split("/");
			pageName = nodes[nodes.length-1];
			goToWikiPage(wikiPath[i]);
			deleteCurrentWikiPage();
			waitForTextNotPresent(pageName);
		}
	}

	//=========== Preview wiki page  =========//
	/**
	 * Preview wiki page before saving
	 * @param title : Page title
	 * @param content : Page content
	 * @param mode : editor mode
	 *             - 1 for RichTextEditor
	 *             - 0 for textarea element
	 * @author dunghm
	 */
	public void previewWikiPage(String title, String content,int mode){
		mouseOverAndClick(ELEMENT_ADD_PAGE_LINK);
		mouseOverAndClick(ELEMENT_BLANK_PAGE_LINK);
		type(ELEMENT_TITLE_WIKI_INPUT, title, true);
		if ( mode == 1 ){
			click(ELEMENT_RICHTEXT_BUTTON);
			waitForAndGetElement(ELEMENT_SOURCE_EDITOR_BUTTON);
			inputDataToFrame(ELEMENT_CONTENT_WIKI_FRAME, content,true);
			driver.switchTo().defaultContent();
		}
		else {
			type(ELEMENT_CONTENT_WIKI_INPUT,content,true);
		}
		click(ELEMENT_PREVIEW_BUTTON);
		waitForAndGetElement(ELEMENT_PREVIEW_SCREEN);
	}

	/**
	 * Verify a specific confirm message in wiki 
	 * @param message : message for confirmation
	 * @param isCancel : OK will be click by default, but if isCancel is set true, Cancel button will be click
	 */
	public void waitForWikiConfirmation(String message, boolean...isCancel){
		//By btnOK = By.xpath("//input[@type='button' and @value='OK']");
		//By btnCancel = By.xpath("//input[@type='button' and @value='Cancel']");
		button = new Button(driver);
		By messageLocator = By.xpath("//div[@class='confirmMessage' and text()='" + message + "']");
		waitForAndGetElement(messageLocator);
		if(isCancel.length > 0 && (isCancel[0] == true)) 
			//click(btnCancel);
			button.cancel();
		else
			//click(btnOK);
			click(button.ELEMENT_OK_BUTTON);
		waitForElementNotPresent(messageLocator);
	}

	/*================ Related page ============*/
	/**
	 * @author vuna2
	 * @param wikiPath: an element path indicates how to access wiki page (eg, "Wiki home/WikiTest")
	 * @param pageName: name of related page (String)
	 */
	public void addRelatedPage(String wikiPath, String pageName, Object...opts){
		String space = (String) (opts.length > 0 ? opts[0] : "");
		Boolean verify = (Boolean) (opts.length > 1 ? opts[1] : true);
		
		button = new Button(driver);
		//goToWikiPage(wikiPath);
		goToPageInfo(null, wikiPath);
		click(ELEMENT_ADD_MORE_RELATION_BUTTON);
		if (space != ""){
			click(ELEMENT_SELECT_SPACE);
			if (space == "Intranet"){
				click(ELEMENT_PORTAL_NAME_SELECTED);
			}else {
				click(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", space.toLowerCase()));
			}
		}
		click(By.xpath(ELEMENT_SELECTED_PAGE.replace("${relatedPage}", pageName)));
		Utils.pause(500);
		click(button.ELEMENT_SELECT_BUTTON);
		if (verify){
			waitForAndGetElement(ELEMENT_RELATED_PAGE.replace("${relatedPage}", pageName));
		}
		Utils.pause(500);
	}

	/**
	 * @author vuna2
	 * @param delete: boolean (true = delete a page / false = just click on Remove link)
	 * @param direct: boolean (user is currently stay in page info window)
	 * @param wikiPath: an element path indicates how to access wiki page (eg, "Wiki home/WikiTest")
	 * @param pageName: name of related page (String)
	 */
	public void removeRelatedPage(boolean delete, boolean direct, String wikiPath, String pageName){
		magAlert = new ManageAlert(driver);
		if (direct){
			click(By.xpath(ELEMENT_REMOVE_RELATED_PAGE_LINK.replace("${relatedPage}", pageName)));
		}else{
			goToWikiPage(wikiPath);
			goToPageInfo();
			click(By.xpath(ELEMENT_REMOVE_RELATED_PAGE_LINK.replace("${relatedPage}", pageName)));
		}
		if (delete){
			magAlert.acceptAlert();
			waitForElementNotPresent(ELEMENT_RELATED_PAGE.replace("${relatedPage}", pageName));
		}else{
			magAlert.cancelAlert();
			waitForAndGetElement(ELEMENT_RELATED_PAGE.replace("${relatedPage}", pageName));
		}
		Utils.pause(1000);
	}

	//////////
	//==== Common of common functions ====//
	/**
	 * @author vuna2
	 * @param totalPages: number of created page (int)
	 * @param wikiParentPath: parent (path) of created page 
	 * @param pageInfo: (pageInfo[0] = page name / pageInfo[1] =  page content)
	 * @param mode = 1 : edit a wiki page in richtext
	 *        mode = 0 : edit a wiki page in source editor
	 * @param wikiPath : an element path indicates how to access wiki page (eg, "Wiki home/WikiTest")
	 * @param pageName : name of related page (String
	 */
	public void addBlankWikiPageAndRelatePage(int totalPages, String[] wikiParentPath, String[][] pageInfo, int mode, String wikiPath, String pageName){
		addBlankWikiPageAdvanceForm(totalPages, wikiParentPath, pageInfo[0], pageInfo[1], mode);
		addRelatedPage(wikiPath, pageName);		
	}

	/**
	 * @author vuna2
	 * @param totalPages: number of created page (int)
	 * @param wikiParentPath: parent (path) of created page 
	 * @param pageInfo: (pageInfo[0] = page name / pageInfo[1] =  page content)
	 * @param mode = 1 : edit a wiki page in richtext
	 *        mode = 0 : edit a wiki page in source editor	 * @param editInfo
	 * @param editInfo : an Array Of Booleans (viewPage/editPage/deletePermission)       
	 * @param user: users or groups that we want to change their permissions
	 */
	public void addBlankWikiPageAndEditPagePermissions(int totalPages, String[] wikiParentPath, String[][] pageInfo, int mode, 
			boolean[] editInfo, String user, Integer... type){
		int notDisplay = 0;
		if (type.length > 0){
			if (!(type[0] instanceof Integer)) {
				throw new IllegalArgumentException("-- Argument should be an Integer --");
			}
			notDisplay = (Integer)type[0];
		}
		addBlankWikiPageAdvanceForm(totalPages, wikiParentPath, pageInfo[0], pageInfo[1], mode);
		editPagePermission(user, editInfo[0], editInfo[1], editInfo[2], notDisplay);
	}

	/**
	 * @author vuna2
	 * @param user: (type: Root, Admin, Author, Developer or Publisher)
	 * @param wikiPath: an element path indicates how to access wiki page (eg, "Wiki home/WikiTest")
	 */
	public void resetDataByDeleteWikiPage(ManageAccount.userType user, String[] wikiPath){
		magAcc = new ManageAccount(driver);
		if (isElementNotPresent(ELEMENT_INPUT_USERNAME)){
			magAcc.signOut();
		}else{
			info("-- User.logIn: " + user);
		}
		magAcc.userSignIn(user);
		goToWiki();
		deleteWikiPage(wikiPath);
	}

	//////////
	/**
	 * @lienTM
	 * function check a user can view and edit wiki page 
	 * @param element_page
	 * @param content: old content of page
	 * @param new_content: New content of page
	 */
	public void checkViewEditPage(By element_page, String content, String new_content){
		goToWiki();
		click(element_page);
		waitForTextPresent(content);
		editWikiPage(null, new_content, 0);
		waitForTextPresent(new_content);
		info("User can view and edit page");
	}

	/**
	 * @lienTM
	 * function: check permission default when just add user for wiki page then add Edit page permission
	 * @param user: user/group
	 */
	public void checkAndEditPagePermission(String user, Integer... type){
		int notDisplay = 0;
		if (type.length > 0){
			if (!(type[0] instanceof Integer)) {
				throw new IllegalArgumentException("-- Argument should be an Integer --");
			}
			notDisplay = (Integer)type[0];
		}

		By EditPage = By.xpath(ELEMENT_EDIT_PAGE_PERMISSIONS.replace("${user}", user)) ;
		//By.xpath(ELEMENT_EDIT_PAGE_CHECK.replace("{$user}", user));
		By ViewPage = By.xpath(ELEMENT_VIEW_PAGE_PERMISSIONS.replace("${user}", user));
		//By.xpath(ELEMENT_VIEW_PAGE_CHECK.replace("{$user}", user));

		info("Check user permission default has view permission but does not have edit page permission");
		goToPagePermission();
		assert !waitForAndGetElement(EditPage, 5000, 1, notDisplay).isSelected();
		assert waitForAndGetElement(ViewPage, 5000, 1, notDisplay).isSelected();
		if (isElementPresent(button.ELEMENT_CLOSE_BUTTON)){
			button.close();
		}else if (isElementPresent(By.xpath("//*[contains(@class, 'popupTitle') and text()='Page Permissions']/..//*[contains(@class, 'uiIconClose')]"))){
			click(By.xpath("//*[contains(@class, 'popupTitle') and text()='Page Permissions']/..//*[contains(@class, 'uiIconClose')]"));
		}else {
			click(button.ELEMENT_CANCEL_BUTTON);
		}
		waitForElementNotPresent(ELEMENT_PAGE_PERMISSION_POPUP);

		info("Add edit page permission for " + user);
		editPagePermission(user, true, true, false, notDisplay);
		waitForElementNotPresent(ELEMENT_PAGE_PERMISSION_POPUP);
	}

	//Wiki page
	/*================= Paragraph =================*/
	/**
	 * @author thaopth
	 * @param paragraphTitle: input paragraph title without space character
	 * @param paragraphContent: input paragraph content with heading followed help tips
	 */
	public void editParagraph (String paragraphTitle, String paragraphContent) {
		info("-- Editing a paragraph... " + paragraphTitle);
		String ELEMENT_PARAGRAPH_ID = "H"+paragraphTitle;

		mouseOver(By.id(ELEMENT_PARAGRAPH_ID), true);
		//click(By.xpath("//*[@title='Edit section: " + paragraphTitle + "']"), 2);

		WebElement element = waitForAndGetElement(By.xpath("//*[@data-original-title='Edit Section: " + paragraphTitle + "']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		Utils.pause(500);
		driver.navigate().refresh();
		Utils.pause(2000);
		type(ELEMENT_CONTENT_WIKI_INPUT, paragraphContent, true);
		switchToParentWindow();
		Utils.pause(500);
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);
	}
	
	/**
	 * function add blank wiki page has attachment
	 * @param title
	 * @param content
	 * @param link
	 */
	public void addBlankWikiPageHasAttachment(String title, String content, String link){
		goToAddBlankPage();
		info("Add new wiki page having attachment");
		addWikiPageSourceEditor(title, content);
		String[] upload = link.split(";");
		for (int i = 0; i < upload.length; i++){
			attachFileInWiki("TestData/" + upload[i], 2);
		}
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForAndGetElement(By.xpath(ELEMENT_ATTACHMENT_NUMBER.replace("${No}", "" + upload.length)));
	}
	
	/** function edit wiki page that check public activity
	 * @author lientm
	 * @param title
	 * @param content
	 */
	public void editPageWithCheckPublicActivity(String title, String content, String...comment){
		mouseOverAndClick(ELEMENT_EDIT_PAGE_LINK);
		addWikiPageSourceEditor(title, content);
		if (comment.length > 0){
			type(ELEMENT_COMMENT_TEXTBOX, comment[0], true);
		}
		click(ELEMENT_PUBLIC_ACTIVITY_CHECKBOX, 2);
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);
	}
	
	/**
	 * function add new blank wiki page at source editor mode with content including many line
	 * @param title
	 * @param content
	 */
	public void addWikiPageWithContentMultiLine(String title, String content){
		goToAddBlankPage();
		info("Modify data with source editor");
		if(title != null)
			type(ELEMENT_TITLE_WIKI_INPUT, title, true);
		if(content != null){
			String[] line = content.split("/");
			for (int i = 0; i < line.length; i ++){
				type(ELEMENT_CONTENT_WIKI_INPUT, line[i] , false);
				type(ELEMENT_CONTENT_WIKI_INPUT, Keys.ENTER.toString(), false);
			}
		}
		Utils.pause(1000);
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);
	}
}
