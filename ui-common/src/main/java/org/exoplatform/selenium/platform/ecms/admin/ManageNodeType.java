package org.exoplatform.selenium.platform.ecms.admin;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author vuna2
 *
 */
public class ManageNodeType extends EcmsBase{

	public ManageNodeType(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	ManageAlert alt = new ManageAlert(driver);
	Button button = new Button(driver);
	ManageNamespace magNamespace = new ManageNamespace(driver);
	//ManageView magView = new ManageView(driver);
	
	/*//Repository
	public final By ELEMENT_CONTENT_TYPES = By.xpath("//div[contains(text(),'Content Types')]");
	public final By ELEMENT_MANAGE_NODETYPE = By.linkText("Manage Node Type");*/
	public final By ELEMENT_NODETYPE_TEXT = By.id("NodeTypeText");
	public final By ELEMENT_NODETYPE_SEARCH = By.xpath("//*[@title = 'Search']");
	public final String ELEMENT_POPUP_TAB = "//*[contains(@class, 'popupContent')]//*[text()='${tab}']";
	//Popup > Node Type tab
	public final By ELEMENT_NODE_TYPE_NAME = By.name("nodeTypeName");
	public final By ELEMENT_MIXIN_TYPE = By.name("mixinType");
	public final By ELEMENT_ORDERABLE_CHILD_NODE = By.name("hasOrderableChildNodes");
    public final By ELEMENT_PRIMARY_ITEM_NAME = By.name("primaryItemName");
	public final By ELEMENT_ADD_SUPER_TYPE_ICON = By.xpath("//*[@class='actionIcon']/i[contains(@class, 'uiIconAddSuperType')]");
	public final String ELEMENT_PREVIEW_ICON = "//*[text() = '${nodeName}']/../..//*[contains(@class, 'uiIconPreview')]";
	//Popup > Super Type tab
	public final By ELEMENT_ADD_TYPE_BUTTON = By.xpath("//button[text()='Add Type']");
	public final String ELEMENT_SELECT_NODE_TYPE_NAME = "//*[@class='uiCheckbox']/input[@name='{nodeName}']";
	
	//Message
	public final String MESSAGE_FOR_NO_INPUT_KEYWORD = "The value of the field is empty.";
	public final String MESSAGE_FOR_SPECIAL_KEYWORD = "There are some invalid characters. Please enter another value.";
	public final String MESSAGE_FOR_NOT_MATCH_KEYWORD = "There isn't any node type with condition search.";
	public final String MESSAGE_NODE_REGISTERED_SUCCESSFULLY = "The '${nodeName}' node type was registered successfully.";
	
	//Do a search in Node Types Tab
	public void doNodeTypeSearch(String keyword){
		type(ELEMENT_NODETYPE_TEXT, keyword, true);
		if (isElementPresent(ELEMENT_NODETYPE_SEARCH)){
			click(ELEMENT_NODETYPE_SEARCH);
		}else{
			WebElement search = driver.findElement(By.xpath("//*[contains(@href,'SearchNodeType')]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", search);
		}
		Utils.pause(1000);
	}
	
	/**
	 * Add a new node type
	 * @param nodeTypeName
	 * @param nodeSuperType
	 * @param params
	 * 
	 */
	public void addNewNodeType(String nodeTypeName, String nodeSuperType, Object...params){
		String namespace = (String) (params.length > 0 ? params[0] : "");
		Boolean isMixinType = (Boolean) (params.length > 1 ? params[1]: false) ;
		Boolean orderChildNode = (Boolean) (params.length > 2 ? params[2]: false) ;
		String prItemName = (String) (params.length > 3 ? params[3] : "");
		
		info("-- Creating a new node type --");
		click(button.ELEMENT_ADD_BUTTON);
		waitForAndGetElement(ELEMENT_POPUP_TAB.replace("${tab}", "Node Type"));
		
		//Select a namespace
		if (!namespace.isEmpty()){
			select(magNamespace.ELEMENT_INPUT_NAMESPACE_PREFIX, namespace);
		}
		
		//Input node type name
		type(ELEMENT_NODE_TYPE_NAME, nodeTypeName, true);
		
		//Options
		if (isMixinType){
			select(ELEMENT_MIXIN_TYPE, "true");
		}
		if (orderChildNode){
			select(ELEMENT_ORDERABLE_CHILD_NODE, "true");
		}
		if (!prItemName.isEmpty()){
			type(ELEMENT_PRIMARY_ITEM_NAME, prItemName, true);
		}
		
		//Select Super Types
		click(ELEMENT_ADD_SUPER_TYPE_ICON);
		String[] temp;			 
		String delimiter = "/";
		temp = nodeSuperType.split(delimiter);
		for(int i =0; i < temp.length ; i++){
			info("Selecting Node... " + temp[i]);
		    click(ELEMENT_SELECT_NODE_TYPE_NAME.replace("{nodeName}", temp[i]), 2);
		    Utils.pause(200);
		}	
		click(ELEMENT_ADD_TYPE_BUTTON);
		button.save();
		alt.verifyAlertMessage(MESSAGE_NODE_REGISTERED_SUCCESSFULLY.replace("${nodeName}", nodeTypeName));
	}
	
	/**
	 * View Node Type
	 * @param nodeTypeName
	 */
	public void viewNodeType(String nodeTypeName){
		info("-- View node type: " + nodeTypeName);
		doNodeTypeSearch(nodeTypeName);
		if (isTextNotPresent(nodeTypeName)){
			info("-- Cannot find Node Type: " + nodeTypeName);
		}else{
			click(ELEMENT_PREVIEW_ICON.replace("${nodeName}", nodeTypeName));
			WebElement element = waitForAndGetElement(ELEMENT_NODE_TYPE_NAME);
			String nType = element.getAttribute("value");
			assert nType.equals(nodeTypeName): "Wrong display [Node Type Name]";
			button.close();
		}
		Utils.pause(500);
	}
}