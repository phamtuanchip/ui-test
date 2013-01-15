package org.exoplatform.selenium.platform.ecms;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ActionBar extends EcmsBase {
	
	public ActionBar(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	ContentTemplate contentTemp = new ContentTemplate(driver);

	//publication TAB
	public By ELEMENT_PUBLICATION_TAB= By.xpath("//a[contains(text(),'Publication')]");
	public By ELEMENT_TEMPLATE_LIST_TEXT = By.xpath("//div[contains(text(),'Select your template in the list below')]");
	public By ELEMENT_SYMLINK_NAME = By.id("symLinkName");
	//public By ELEMENT_ADD_ITEM	 = By.xpath("//img[@title='Add Item']");
	public By ELEMENT_EDIT_LINK= By.xpath("//a[@title='Edit']");
	public By ELEMENT_NEW_CONTENT_LINK = By.linkText("New Content");
	public By ELEMENT_CATEGORIES_LINK = By.xpath("//a[text()='Categories']");
	public By ELEMENT_CATEGORIES_MORE_LINK = By.xpath("//div[text()='More']/..//a[text()='Categories']");
	public By ELEMENT_PUBLICATION = By.linkText("Publications");
	public By ELEMENT_VERSIONS_LINK=By.linkText("Versions");
	public By ELEMENT_OVERLOAD_THUMBNAIL = By.linkText("Overload Thumbnail");

	//System TAB
	public By ELEMENT_SYSTEM_TAB = By.xpath("//a[contains(@title,'System')]");
	public By ELEMENT_PERMISSION_LINK = By.linkText("Permissions");
	public By ELEMENT_EXPORT_LINK = By.xpath("//a[@class='SubTabIcon DefaultActionIcon ExportNodeIcon' and @title='Export']");
	public By ELEMENT_IMPORT_LINK = By.xpath("//a[@class='SubTabIcon DefaultActionIcon ImportNodeIcon' and @title='Import']");

	//Export Form
	public By ELEMENT_DOC_VIEW = By.id("format_docview");
	public By ELEMENT_ZIP = By.id("zip");
	public By ELEMENT_EXPORT_VERSION = By.linkText("Export Version History");
	public By ELEMENT_EXPORT = By.xpath("//a[@class='ActionButton LightBlueStyle' and text()='Export']");

	//Import Form
	public By ELEMENT_UPLOAD_FILE_FRAME = By.xpath("//label[contains(text(),'Upload File:')]/following::div/iframe[contains(@id,'uploadFrame')]");
	public By ELEMENT_UPLOAD_VERSION_FRAME = By.xpath("//label[contains(text(),'Version History:')]/following::div/iframe[contains(@id,'uploadFrame')]");
	public By ELEMENT_BEHAVIOR = By.name("behavior");
	public By ELEMENT_IMPORT = By.xpath("//a[@class='ActionButton LightBlueStyle' and text()='Import']");

	//Add Category Form
	public By ELEMENT_SELECT_CATEGORY_TAB = By.xpath("//div[text()='Select Category']");
	public By ELEMENT_CATEGORY_TREE_BOX = By.id("taxonomyTree");
	public By ELEMENT_ADD_ROOT_BUTTON = By.xpath("//label[text()='Root Tree']/following::img[@title='Add Root Node']");

	//Permission Management Form
	public By ELEMENT_PERMISSION_MANAGEMENT_POPUP = By.id("UIPopupWindow");
	public String ELEMENT_PERMISSION_MANAGEMENT_TEXT = "Permission Management";
	public By ELEMENT_PERMISSION_MANAGEMENT_GRID = By.xpath("//table[@class='UIGrid']");
	public By READ_CHECKBOX = By.id("read");
	public By ADDNODE_CHECKBOX = By.id("add_node");
	public By SETPRO_CHECKBOX = By.id("set_property");
	public By REMOVE_CHECKBOX = By.id("remove");

	//Collaboration TAB
	public By ELEMENT_COLLABORATION_TAB = By.linkText("Collaboration");
	public By ELEMENT_TAG = By.linkText("Tag");


	//Version Info form
	public By ELEMENT_ICON_VERSION_ADD=By.xpath("//div[@title='Add Label']");
	public By ELEMENT_TEXTBOX_VERSION=By.id("label");


	// Add a category in DMS Administration - Simple View
	public void addCategoryInSimpleView(String name)
	{
		click(ELEMENT_BUTTON_ADD_CATEGORY);
		waitForElementPresent(ELEMENT_ADD_CATEGORY_FORM);
		type(ELEMENT_INPUT_CATEGORY_NAME, name, false);
		click(ELEMENT_SAVE_BUTTON);
		waitForElementPresent(By.xpath("//a[@title='"+ name + " ']"));
	}

	//Go to new content
	public void goToAddNewContent(){
		waitForElementPresent(ELEMENT_NEW_CONTENT_LINK);
		for (int repeat = 1;; repeat++)	{	
			if (repeat >= ACTION_REPEAT) {
				Assert.fail("Cannot perform the action after " + ACTION_REPEAT + "tries");
			}
			mouseOver(ELEMENT_NEW_CONTENT_LINK, true);
			click(ELEMENT_NEW_CONTENT_LINK);

			if (waitForElementNotPresent(ELEMENT_NEW_CONTENT_LINK, 30000,0) == null) return;
			pause(WAIT_INTERVAL);
			info("retry...[" + repeat + "]");
		}
	}

	//Collaboration Tab
	public void goToCollaboration(){
		for (int repeat = 0;; repeat++)	{	
			if (repeat >= ACTION_REPEAT) {
				Assert.fail("Cannot perform the action after " + ACTION_REPEAT + "tries");
			}
			mouseOver(ELEMENT_COLLABORATION_TAB, true);
			click(ELEMENT_COLLABORATION_TAB);

			if (waitForElementPresent(ELEMENT_TAG, 30000, 0) != null) return;
			pause(WAIT_INTERVAL);
			info("retry...[" + repeat + "]");
		}
	}

	public void goToEditDocument(String title)
	{	
		for(int loop = 1;;loop ++)
		{
			if (loop >= ACTION_REPEAT) {
				Assert.fail("Cannot go to the edit page: " + title );
			}
			click(ELEMENT_EDIT_LINK);
			if (waitForAndGetElement(ELEMENT_SAVE_CLOSE_BUTTON, 50000).isDisplayed()) 
				break;
		}
	}

	//Add symlink for node with target node is documents
	public void addSymlink(By path, String name){
		waitForElementPresent(ELEMENT_ADD_SYMLINK);
		click(ELEMENT_ADD_SYMLINK);
		waitForElementPresent(ELEMENT_ADD_SYMLINK_POPUP);
		click(ELEMENT_ADD_ITEM);
		usePaginator(path,"Can not choose target node");
		if (path!=null){
			click(path);
		}
		if (name != "Documents.lnk" ){
			type(ELEMENT_SYMLINK_NAME,name,true);
		}
		assert getValue(ELEMENT_SYMLINK_NAME).equalsIgnoreCase(name);
		click(ELEMENT_SAVE_BUTTON); 
	}

	public void setPermissionForDocument(String user, boolean add, boolean edit, boolean remove, boolean reset)
	{
		click(ELEMENT_SYSTEM_TAB);
		click(ELEMENT_PERMISSION_LINK);
		if (reset)
		{
			click(ELEMENT_DELETE_PERMISSION);
			acceptAlert();
		}
		click(ELEMENT_SELECT_USER);
		waitForElementPresent(ELEMENT_SEARCH_TEXTBOX);
		type(ELEMENT_SEARCH_TEXTBOX,user, false);
		click(ELEMENT_SEARCH_LINK);
		click(ELEMENT_SEARCH_CHOOSE);
		if (waitForAndGetElement(ELEMENT_READ_CHECKBOX).isSelected()==false){
			click(ELEMENT_READ_CHECKBOX);
		}
		if (add)
		{
			if (waitForAndGetElement(ELEMENT_ADD_NODE_CHECKBOX).isSelected()==false){
				click(ELEMENT_ADD_NODE_CHECKBOX);
			}
		}
		else
		{
			if (waitForAndGetElement(ELEMENT_ADD_NODE_CHECKBOX).isSelected()==true){
				click(ELEMENT_ADD_NODE_CHECKBOX);
			}
		}
		if (edit)
		{
			if (waitForAndGetElement(ELEMENT_EDIT_NODE_CHECKBOX).isSelected()==false){
				click(ELEMENT_EDIT_NODE_CHECKBOX);
			}
		}
		else
		{
			if (waitForAndGetElement(ELEMENT_EDIT_NODE_CHECKBOX).isSelected()==true){
				click(ELEMENT_EDIT_NODE_CHECKBOX);
			}
		}
		if (remove)
		{
			if (waitForAndGetElement(ELEMENT_REMOVE_NODE_CHECKBOX).isSelected()==false){
				click(ELEMENT_REMOVE_NODE_CHECKBOX);
			}
		}
		else
		{
			if (waitForAndGetElement(ELEMENT_REMOVE_NODE_CHECKBOX).isSelected()==true){
				click(ELEMENT_REMOVE_NODE_CHECKBOX);
			}
		}
		click(ELEMENT_SAVE_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
	}

	//Export node
	public void exportNode (boolean systemView, boolean zip, boolean exportVersionHistory) {
		waitForElementPresent(ELEMENT_SYSTEM_TAB);
		click(ELEMENT_SYSTEM_TAB);
		pause(500);
		waitForElementPresent(ELEMENT_EXPORT_LINK);
		click(ELEMENT_EXPORT_LINK);
		if (!systemView)
		{
			click(ELEMENT_DOC_VIEW);
		}

		if (zip)
		{
			check(ELEMENT_ZIP);
		}

		if (exportVersionHistory)
		{
			click(ELEMENT_EXPORT_VERSION);
		}
		else
		{
			click(ELEMENT_EXPORT);
		}
		pause(10000);
		waitForElementNotPresent(ELEMENT_EXPORT);
	}

	//Import node
	public void importNode (String linkFile, String linkVersion, String behavior, boolean version) {
		//Click system tab
		waitForElementPresent(ELEMENT_SYSTEM_TAB);
		click(ELEMENT_SYSTEM_TAB);
		//Click import button
		waitForElementPresent(ELEMENT_IMPORT_LINK);
		click(ELEMENT_IMPORT_LINK);
		//Switch to frame upload file
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_UPLOAD_FILE_FRAME));
		waitForElementPresent(ELEMENT_UPLOAD_IMG_ID);
		type(ELEMENT_UPLOAD_IMG_ID, Utils.getAbsoluteFilePath(linkFile), false);
		pause(500);
		switchToParentWindow();

		select(ELEMENT_BEHAVIOR, behavior);
		if (version)
		{		
			driver.switchTo().frame(waitForAndGetElement(ELEMENT_UPLOAD_VERSION_FRAME));
			waitForElementPresent(ELEMENT_UPLOAD_IMG_ID);
			type(ELEMENT_UPLOAD_IMG_ID, Utils.getAbsoluteFilePath(linkVersion), false);
			pause(500);
			switchToParentWindow();
			click(ELEMENT_IMPORT);
			pause(500);
			waitForMessage("Import successfully.");
			click(ELEMENT_OK_BUTTON);
		}
		else 
		{
			click(ELEMENT_IMPORT);
			pause(500);
			waitForMessage("Import successfully.");
			click(ELEMENT_OK_BUTTON);
		}
	}

	//Add category for node
	public void addCategoryForNode (String categoryTree, boolean rootTree, String categoryPath, String categoryName) {
		By ELEMENT_ADD_CATEGORY_SPECIFIC = By.xpath("//div[contains(text(),'"+categoryName+"')]/following::a[@title='select']");
		//			By ELEMENT_CATEGORY_LIST = By.xpath("//th[text()='Category']");

		waitForElementPresent(ELEMENT_CATEGORIES_LINK);
		click(ELEMENT_CATEGORIES_LINK);
		waitForElementPresent(ELEMENT_SELECT_CATEGORY_TAB);
		click(ELEMENT_SELECT_CATEGORY_TAB);
		pause(500);
		select(ELEMENT_CATEGORY_TREE_BOX, categoryTree);
		if (rootTree) {
			click(ELEMENT_ADD_ROOT_BUTTON);
			waitForTextPresent(categoryTree);
			checkUnexpectedError();			
		}
		else {
			String paths [] = categoryPath.split("/");
			for (String path : paths)
				click(By.xpath("//div[@title='"+path+"']"));
			waitForElementPresent(ELEMENT_ADD_CATEGORY_SPECIFIC);
			click(ELEMENT_ADD_CATEGORY_SPECIFIC);
			pause(500);
			checkUnexpectedError();
			waitForTextPresent(categoryPath);

		}

		waitForElementPresent(ELEMENT_CLOSE_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);
		info ("------Category "+categoryName+" is added succesfully");
	}

	// Node Permission
	public void goToPermissionManagement(){
		goToNode(ELEMENT_SYSTEM_TAB);
		goToNode(ELEMENT_PERMISSION_LINK);
	}

	//Function to set permission for node
	public void setNodePermission(boolean read, boolean add, boolean pro, boolean remove){
		info("Set read permission for user: "+read);
		waitForElementPresent(READ_CHECKBOX);
		if ((read && !waitForAndGetElement(READ_CHECKBOX).isSelected()) || (!read && waitForAndGetElement(READ_CHECKBOX).isSelected())){
			click(READ_CHECKBOX);
		}
		info("Set add node permission for user: "+add);
		if ((add && !waitForAndGetElement(ADDNODE_CHECKBOX).isSelected())||(!add && waitForAndGetElement(ADDNODE_CHECKBOX).isSelected())){
			click(ADDNODE_CHECKBOX);
		}
		info("Set 'set propeties' permission for user: "+pro);
		if ((pro && !waitForAndGetElement(SETPRO_CHECKBOX).isSelected())||(!pro && waitForAndGetElement(SETPRO_CHECKBOX).isSelected())){
			click(SETPRO_CHECKBOX);
		}
		info("Set set remove permission for user: "+remove);
		if ((remove && !waitForAndGetElement(REMOVE_CHECKBOX).isSelected())|| (!remove && waitForAndGetElement(REMOVE_CHECKBOX).isSelected())){
			click(REMOVE_CHECKBOX);
		}
	}

	//Function to delete permission
	public void deletePermission(String user, boolean verify){
		By ELEMENT_DELETE_USER_PERMISSION = By.xpath("//div[@title='"+ user +"']/../../td/div/img[@class='DeleteIcon']");
		By ELEMENT_DELETE_USER_PERMISSION_AUX = By.xpath("//div[@id='UITabContent' and @style='display: block;;']//div[@title='"+ user +"']/../../td/div/img[@class='DeleteIcon']");
		if (waitForAndGetElement(ELEMENT_DELETE_USER_PERMISSION_AUX,5000,0) != null){
			click(ELEMENT_DELETE_USER_PERMISSION_AUX);
			acceptAlert();
			if(verify){
				waitForElementNotPresent(ELEMENT_DELETE_USER_PERMISSION_AUX);
			}
			info("Delete a permission is successful");
		}else if (waitForAndGetElement(ELEMENT_DELETE_USER_PERMISSION,30000,0) != null){
			click(ELEMENT_DELETE_USER_PERMISSION);
			acceptAlert();
			if(verify){
				waitForElementNotPresent(ELEMENT_DELETE_USER_PERMISSION);
			}
			info("Delete a permission is successful");
		}else{
			info("Do not see an element to delete");
		}
		pause(500);
	}

	//Function to remove default user and group permission of node except for owner
	public void removeDefaultPermissionOfNode(){ 
		deletePermission("*:/platform/web-contributors", true);
		deletePermission("*:/platform/administrators", true);
		deletePermission("any", true);
	}

	//Function to add a user to group and choose membership type
	public void clickUpLevel(){
		click(By.xpath("//*[@id='UITaxonomyTreeCreateChild']//a[@class='LevelUpArrowIcon']"));
		pause(500);
	}

	//Function to select user to set permission on permission management popup
	public void selectUser(String user){
		By ELEMENT_USER = By.xpath("//div[@title='"+user+"']/../../td/div/img[@class='SelectPageIcon']"); 

		info("Set permission for user "+user);
		click(By.xpath("//img[@title='Select User']"));
		if (waitForAndGetElement(ELEMENT_USER) != null){
			click(ELEMENT_USER);
		}else{
			info("User is not found");
		}
	}

	/*
	 * Add version for a node
	 * + locator: locator of node
		   + version: version name
	 */
	public void addVersionForNode(By locator, String vesion){
		goToNode(locator);
		click(ELEMENT_PUBLICATION_TAB);
		clearCache();
		click(ELEMENT_VERSIONS_LINK);
		click(ELEMENT_ICON_VERSION_ADD);
		type(ELEMENT_TEXTBOX_VERSION,vesion,true);
		click(ELEMENT_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);
	}

	//Upload file Thumbnail
	public void uploadThumbnail(String link){
		click(ELEMENT_OVERLOAD_THUMBNAIL);
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_UPLOAD_IMG_FRAME_XPATH));
		type(ELEMENT_UPLOAD_IMG_ID, Utils.getAbsoluteFilePath(link), false);
		info("Upload file "+ Utils.getAbsoluteFilePath(link));
		switchToParentWindow();
		String links[] = link.split("/");
		int length = links.length;
		waitForElementPresent(By.xpath("//div[contains(text(),'" + links[length-1]+ "')]"));
		click(ELEMENT_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
	}
}
