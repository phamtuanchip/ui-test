package org.exoplatform.selenium.platform.ecms.contentexplorer;


import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.exoplatform.selenium.TestLogger.info;

/**
 * 
 * @author vuna2
 *
 */
public class AdvancedSearch extends EcmsBase{
	
	Button button = new Button(driver);
	ManageAlert alert = new ManageAlert(driver);
	public AdvancedSearch(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	//Advanced search form
	public final By ELEMENT_SAVED_SEARCH_ICON = By.className("uiIconEcmsSavedSearchesMini"); 
	public final By ELEMENT_ADVANCED_SEARCH_ICON = By.xpath("//*[@data-original-title='Advanced Search']");
	public final By ELEMENT_ADVANCED_SEARCH_TAB = By.xpath("//*[@id='UIECMSearch']//*[contains(text(),'Advanced Search')]");
	public final By ELEMENT_CONSTRAINT_FORM = By.xpath("//*[contains(text(), 'Show/Hide Constraint Form')]");
	public final String ELEMENT_ADD_PROPERTY_ICON = "//label[text()='${property}']/../a[@data-original-title='Add Property']";
	public final By ELEMENT_METADATA_TYPE_SELECT = By.name("metadataType");
	public final By ELEMENT_CONTAIN_EXACTLY_INPUT = By.id("containExactly");
	public final By ELEMENT_CONTAIN_INPUT = By.id("contain");
	public final By ELEMENT_NOT_CONTAIN_INPUT = By.id("notContain");
	public final By ELEMENT_ADD_PROPERTY_BUTTON = By.xpath("//form[@id='UISimpleSearch']//button[text()='Add']");
	public final By ELEMENT_ADD_SELECT_PROPERTY = By.xpath("//form[@id='UISelectPropertyForm']//button[text()='Add']");
	public final By ELEMENT_SEARCH_CONTENT_INPUT = By.id("input");
	public final String ELEMENT_PROPERTY_RADIO = "//input[@value='${value}']";
	public final By ELEMENT_SEARCH_BUTTON = By.xpath("//form[@id='UISimpleSearch']//*[text()='Search']");
	public final By ELEMENT_SEARCH_RESULT_TAB = By.linkText("Search Results");
	public final String ELEMENT_SEARCH_RESULT_TEXT = "//h5[contains(text(),'${result}')]";
	public final By ELEMENT_ADD_DOC_TYPE_ICON = By.xpath("//input[@id='docType']/../a[@data-original-title='Add Node Type']");
	public final String ELEMENT_NODE_TYPE_CHECKBOX = "//input[@id='${type}']";
	public final By ELEMENT_SAVE_BUTTON = By.xpath("//form[@id='UINodeTypeSelectForm']//button[text()='Save']");

	//New query tab
	public final By ELEMENT_NEW_QUERY_TAB = By.linkText("New Query");
	public final By ELEMENT_QUERY_NAME_INPUT = By.id("name");
	public final By ELEMENT_QUERY_STYLE_SELECTBOX = By.name("selectBox");
	public final By ELEMENT_QUERY_INPUT = By.id("query");
	public final By ELEMENT_SEARCH_QUERY_BUTTON = By.xpath("//form[@id='UIJCRAdvancedSearch']//button[text()='Search']");
	public final By ELEMENT_SAVE_QUERY_BUTTON = By.xpath("//form[@id='UIJCRAdvancedSearch']//button[text()='Save']");
	
	//Saved query
	public final String ELEMENT_SAVED_QUERY_TEXT = "//div[text()='${query}']/../..//div[text()='${type}']";
	public final By ELEMENT_SAVED_QUERY_TAB = By.linkText("Saved Query");
	
	public final String ELEMENT_EXECUTE_QUERY_ICON = "//div[text()='${query}']/../..//a[@data-original-title='Execute']";
	public final String ELEMENT_DELETE_QUERY_ICON = "//div[text()='${query}']/../..//a[@data-original-title='Delete']";
	public final String ELEMENT_EDIT_QUERY_ICON = "//div[text()='${query}']/../..//a[@data-original-title='Edit']";
	public final By ELEMENT_STYLE_EDIT_SELECTBOX= By.xpath("//form[@id='EditQueryForm']//select[@name='selectBox']");
	public final By ELEMENT_QUERY_EDIT_INPUT= By.xpath("//form[@id='EditQueryForm']//*[@id='query']");
	public final By ELEMENT_SAVE_EDIT_BUTTON = By.xpath("//form[@id='EditQueryForm']//button[text()='Save']");
	public final String MSG_DELETE_QUERY = "Are you sure you want to delete this query?";
	
	//Advanced search form > Show/Hide constraint form
	public final By ELEMENT_ADD_CATEGORY_IMG = By.xpath("//*[@data-original-title = 'Add category']");
	public final By ELEMENT_ADD_CATEGORY_CHECKBOX = By.id("categoryPro");

	//Go to advanced search in content explorer
	public void goToAdvancedSearch(){
		click(ELEMENT_SAVED_SEARCH_ICON);
		if (isElementNotPresent(By.xpath("//*[@data-original-title = 'Advanced Search']"))){
			click(By.xpath("//*[@data-original-title = 'Advanced Search']"));
		}else if (isElementPresent(ELEMENT_ADVANCED_SEARCH_ICON)){
			click(ELEMENT_ADVANCED_SEARCH_ICON);
		}
		click(ELEMENT_ADVANCED_SEARCH_TAB);
		waitForAndGetElement(ELEMENT_CONSTRAINT_FORM);
		//Utils.pause(500);
	}

	//Open [Add Category] Form in [Site Explorer] > [Advanced Search]
	public void openAddCategoryInAdvancedSearch(){
		if (getElement(ELEMENT_ADD_CATEGORY_IMG) == null){
			click(ELEMENT_CONSTRAINT_FORM);
		}
		if (waitForAndGetElement(ELEMENT_ADD_CATEGORY_CHECKBOX, DEFAULT_TIMEOUT, 0, 2).isSelected() == false){
			click(ELEMENT_ADD_CATEGORY_CHECKBOX, 2);
		}	
		if (isElementPresent(ELEMENT_ADD_CATEGORY_IMG)){
			click(ELEMENT_ADD_CATEGORY_IMG);
		}else {
			click("//*[@data-original-title = 'Add Category']");
		}
		Utils.pause(500);
	}
	/** Add property for advanced search
	 * @author thuntn
	 * @param type = 1: contain exactly:
	 * 			   = 2: Contain:
	 * 			   = 3: Not Contain:
	 * @param property
	 * @param value
	 */
	public void addPropertyToAdvancedSearch(int type, String metaType, String property, String value){
		switch (type) {
		case 1:
			click(ELEMENT_ADD_PROPERTY_ICON.replace("${property}", "Contain Exactly:"));
			select(ELEMENT_METADATA_TYPE_SELECT, metaType);
			click(ELEMENT_PROPERTY_RADIO.replace("${value}", property),2);
			click(ELEMENT_ADD_SELECT_PROPERTY);
			type(ELEMENT_CONTAIN_EXACTLY_INPUT,value,true);
			break;
		case 2:
			click(ELEMENT_ADD_PROPERTY_ICON.replace("${node}", "Contain:"));
			select(ELEMENT_METADATA_TYPE_SELECT, metaType);
			click(ELEMENT_PROPERTY_RADIO.replace("${value}", property),2);
			click(ELEMENT_ADD_SELECT_PROPERTY);
			type(ELEMENT_CONTAIN_INPUT,value,true);
			break;
		case 3:
			click(ELEMENT_ADD_PROPERTY_ICON.replace("${node}", "Not Contain:"));
			select(ELEMENT_METADATA_TYPE_SELECT, metaType);
			click(ELEMENT_PROPERTY_RADIO.replace("${value}", property),2);
			click(ELEMENT_ADD_SELECT_PROPERTY);
			type(ELEMENT_NOT_CONTAIN_INPUT,value,true);
			break;
		}
		click(ELEMENT_ADD_PROPERTY_BUTTON);
		waitForTextPresent("CONTAINS(" + property+ ", '"+value+"')");
		
	}
	/** Search content
	 * @author thuntn
	 * @param content
	 */
	public void searchContent(String content){
		type(ELEMENT_SEARCH_CONTENT_INPUT,content,true);
		Utils.pause(2000);
		click(ELEMENT_SEARCH_BUTTON);
	}
	/** Select path
	 * @author thuntn
	 * @param selectPath
	 */
	public void selectPath(String selectPath){
		String paths [] = selectPath.split("/");
		for (String path : paths)
			click(By.xpath("//div[@title='"+path+"']"));
	}
	/** Select document types
	 * @author thuntn
	 * @param docTypes: is optional, each element is equivalent to one type
	 */
	
	public void selectDocumentType(Boolean isSave, String...docTypes){
		click(ELEMENT_ADD_DOC_TYPE_ICON);
		
		for(int i = 0;i < docTypes.length; i++){
		
			click(ELEMENT_NODE_TYPE_CHECKBOX.replace("${type}", docTypes[i]),2);
		}
		if(isSave)
			click(ELEMENT_SAVE_BUTTON);
	}
	/** Open New query form in advanced search
	 * @author thuntn
	 */
	public void goToNewQuery(){
		info("--Open new query tab--");
		click(ELEMENT_SAVED_SEARCH_ICON);
		click(ELEMENT_ADVANCED_SEARCH_ICON);
		click(ELEMENT_NEW_QUERY_TAB);
	}
	/** Input data into Add query form in advanced search
	 * @author thuntn
	 * @param name
	 * @param style
	 * @param query
	 */
	public void addQuery(String name,String style, String query){
		info("--Add new query--");
		if(name != "")
			type(ELEMENT_QUERY_NAME_INPUT,name, true);
		if(style != "")
			select(ELEMENT_QUERY_STYLE_SELECTBOX,style);
		if(query != "")
			type(ELEMENT_QUERY_INPUT,query,true);
		click(ELEMENT_SAVE_QUERY_BUTTON);
		waitForAndGetElement(ELEMENT_SAVED_QUERY_TEXT.replace("${query}", name).replace("${type}", style.toLowerCase()));
		button.closeWindow();
	}
	/** Open 'Edit query' form in advanced search
	 * @author thuntn
	 * @param query
	 */
	public void goToEditQuery(String query){
		info("--Open 'Edit query' form--");
		click(ELEMENT_SAVED_SEARCH_ICON);
		click(ELEMENT_ADVANCED_SEARCH_ICON);
		click(ELEMENT_SAVED_QUERY_TAB);
		click(ELEMENT_EDIT_QUERY_ICON.replace("${query}", query));
	}
	/** Edit query in advanced search
	 * @author thuntn
	 * @param name
	 * @param style
	 * @param query
	 */
	public void editQuery(String name,String style, String query){
		info("--Edit new query--");
		if(style != "")
			select(ELEMENT_STYLE_EDIT_SELECTBOX,style);
		if(query != "")
			type(ELEMENT_QUERY_EDIT_INPUT,query,true);
		click(ELEMENT_SAVE_EDIT_BUTTON);
		button.closeWindow();
	}
	/**Delete query in advanced search
	 * @author thuntn
	 */
	public void deleteQuery(String name){
		info("--Delete query in advanced search--");
		click(ELEMENT_SAVED_SEARCH_ICON);
		click(ELEMENT_ADVANCED_SEARCH_ICON);
		click(ELEMENT_SAVED_QUERY_TAB);
		click(ELEMENT_DELETE_QUERY_ICON.replace("${query}", name));
		alert.waitForConfirmation(MSG_DELETE_QUERY);
		waitForElementNotPresent(ELEMENT_SEARCH_RESULT_TEXT.replace("${result}",name));
	}
	
	/**Execute query in advanced search
	 * @author thuntn
	 */
	public void executeQuery(String name){
		info("--Execute query in advanced search--");
		click(ELEMENT_SAVED_SEARCH_ICON);
		click(ELEMENT_ADVANCED_SEARCH_ICON);
		click(ELEMENT_SAVED_QUERY_TAB);
		click(ELEMENT_EXECUTE_QUERY_ICON.replace("${query}", name));
	}
}
