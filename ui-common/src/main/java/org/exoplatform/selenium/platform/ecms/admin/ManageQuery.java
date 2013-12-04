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
public class ManageQuery extends EcmsBase{

	public ManageQuery(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	Button button = new Button(driver);
	ManageAlert magAlert = new ManageAlert(driver);
	ECMainFunction ecMain = new ECMainFunction(driver);
	ManageDrive magDrv = new ManageDrive(driver);
	
	//Elements
	public final By ELEMENT_QUERY_TYPE = By.xpath("//select[@name='type']");
	public final By ELEMENT_ENABLE_CACHE_RESULT = By.id("cache");
	public final By ELEMENT_INPUT_STATEMENT = By.name("statement");
	public final String ELEMENT_EDIT_QUERY_ICON = "//*[contains(text(), '${queryName}')]/../..//*[@class = 'uiIconEditInfo']";
 	public final String ELEMENT_DELETE_QUERY_ICON = "//*[contains(text(), '${queryName}')]/../..//*[@class = 'uiIconDelete']";
	public final By ELEMENT_QUERY_ADD_PERMISSION_ICON = By.xpath("//*[@data-original-title='Add Permission']");
 	
 	/*=======================================================================*/
 	
	//Open form [Add Query]
	public void openAddQueryForm(){
		info("-- Opening [Add Query] Form --");
		click(ecMain.ELEMENT_ADD_QUERY_BUTTON);
		waitForAndGetElement(ELEMENT_INPUT_CATEGORY_NAME);
	}
	
	//Add a new Query
	public void addQuery(String queryName, String queryType, boolean enableCacheResult, String group, String membership, Object...params){
		boolean statement = (Boolean) (params.length > 0 ? params[0]: false);
		String content = (String) (params.length > 1 ? params[1]: "");
		
		if (isElementPresent(ELEMENT_QUERY_TYPE)){
			info("-- Query Form has already opened --");
		}else {
			openAddQueryForm();
		}
		info("-- Adding a new Query --");
		type(ELEMENT_INPUT_CATEGORY_NAME, queryName, true);
		select(ELEMENT_QUERY_TYPE, queryType);
		if (enableCacheResult){
			click(ELEMENT_ENABLE_CACHE_RESULT, 2);
		}
		
		if (isElementPresent(magDrv.ELEMENT_ADD_PERMISSION)){
			click(magDrv.ELEMENT_ADD_PERMISSION);
		}else {
			click(ELEMENT_QUERY_ADD_PERMISSION_ICON);
		}
		userGroup.selectGroup(group, false);
		click(By.linkText(membership));
		Utils.pause(500);
		
		if (statement){
			type(ELEMENT_INPUT_STATEMENT, content, true);
		}
		
		Utils.pause(500);
		button.save();
		waitForAndGetElement(ELEMENT_DELETE_QUERY_ICON.replace("${queryName}", queryName));
	}
	
	//Edit a Query
	public void editQuery(String queryName, String queryType, boolean enableCacheResult, Object...params){
		boolean statement = (Boolean) (params.length > 0 ? params[0]: false);
		String content = (String) (params.length > 1 ? params[1]: "");
		
		boolean permission = (Boolean) (params.length > 2 ? params[2]: false);
		String group = (String) (params.length > 3 ? params[3]: "");
		String membership = (String) (params.length > 4 ? params[4]: "");
		
		info("-- Editing Query... --" + queryName);
		click(ELEMENT_EDIT_QUERY_ICON.replace("${queryName}", queryName));
	
		select(ELEMENT_QUERY_TYPE, queryType);

		if (statement){
			type(ELEMENT_INPUT_STATEMENT, content, true);
		}
		Utils.pause(500);
		
		if (enableCacheResult){
			WebElement element = waitForAndGetElement(ELEMENT_ENABLE_CACHE_RESULT, DEFAULT_TIMEOUT, 1, 2);
			if (!element.isSelected()) {
				click(ELEMENT_ENABLE_CACHE_RESULT, 2);
			} else {
				info("-- Cache Result is already checked. --");
			}
		}else{
			uncheck(ELEMENT_ENABLE_CACHE_RESULT);
		}
		
		if(permission){ 
			if (isElementPresent(magDrv.ELEMENT_ADD_PERMISSION)){
				click(magDrv.ELEMENT_ADD_PERMISSION);
			}else {
				click(ELEMENT_QUERY_ADD_PERMISSION_ICON);
			}
			userGroup.selectGroup(group, false);
			click(By.linkText(membership));
		}	
		button.save();
		Utils.pause(500);
	}
	
	//Delete a Query
	public void deleteQuery(String queryName){
		info("-- Deleting Query... --" + queryName);
		Utils.pause(500);
		click(ELEMENT_DELETE_QUERY_ICON.replace("${queryName}", queryName));
		magAlert.acceptAlert();
		waitForTextNotPresent(queryName);
	}
}