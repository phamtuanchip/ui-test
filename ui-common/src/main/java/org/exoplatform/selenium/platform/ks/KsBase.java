package org.exoplatform.selenium.platform.ks;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.ManageApplications.*;
import static org.exoplatform.selenium.platform.PageManagement.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.ELEMENT_FRAME_CONTAIN_PORTLET;

/**
 * @author: HaKT
 * date: 09/11/2012
 */

public class KsBase extends PlatformBase {
	public static String IMPORT_APPLICATION_CONFIRMATION="This will automatically import all gadgets and portlets into new categories.";
	public static String ELEMENT_MYSITE_LINK = "//a[@class='ArrowIcon TBIcon' and contains(text(),'My Sites')]";
	public static By ELEMENT_INTRANET_LINK = By.linkText("intranet");
	public static String DATA_ANSWER_PAGE_NAME="Answer";
	public static String CATEGORY_TITLE="Collaboration";
	public static By ELEMENT_UP_LEVEL=By.xpath("//a[@title='Up Level']");
	public static By ELEMENT_FORUM_LINK = By.linkText("Forums");
	public static By ELEMENT_WIKI_LINK=By.xpath("//a[text()='intranet']/..//a[text()='Wiki']");
	public static By ELEMENT_ANSWER_LINK=By.linkText("Answer");
	public static By ELEMENT_ANSWER_LINK_XPATH = By.xpath("//a[text()='Answer']");
	public static String DATA_ANSWER_FAQ_PAGE_NAME="AnswerFAQ";

	//set permission screen
	public static String ELEMENT_USER_CHECKBOX = "//*[text()='${user}']/../..//*[@type='checkbox']"; 
	public static By ELEMENT_ADD_BUTTON = By.linkText("Add");
	public static By ELEMENT_SELECT_THIS_GROUP = By.linkText("Select this Group");
	public static By ELEMENT_SEARCH_ICON=By.xpath("//a[@title='Quick Search']");
	public static By ELEMENT_SELECT_ROLE_POPUP = By.xpath("//span[@class='PopupTitle' and text()='Select a role']");
	public static By ELEMENT_SELECT_GROUP_POPUP = By.xpath("//span[@class='PopupTitle' and text()='Select a group']");
	public static By ELEMENT_SEARCH_USER_INPUT = By.id("QuickSearch");
	public static By ELEMENT_QUICK_SEARCH_BUTTON = By.xpath("//a[@title='Quick Search']");
	public static By ELEMENT_SELECT_SEARCH = By.id("filter");
	
	//attach file popup
	public static By ELEMENT_POPUP_UPLOAD_FILE = By.xpath("//span[@class='PopupTitle' and text()='Attach File']");
	public static By ELEMENT_ATTACH_FILE = By.linkText("Attach files");
	public static String ELEMENT_UPLOAD_FILE = "//*[contains(@id, 'UploadInputContainer')][${No}]/div/input[@name='file']";

	public static final String MSG_SAVE_ANSWER_PORTLET_SETTING="The settings have been saved.";
	
	//user admin
	public static String DATA_USER_ADMIN = "john";
	public static String DATA_PASS_ADMIN = "gtn";

	/**
	 * Create answer page at root path
	 * @author hakt
	 */
	public static void createAnswerPageAtRootPath() {
		Map<String, String> ANSWER_PORTLET_ID = new HashMap<String, String>();
		ANSWER_PORTLET_ID.put("Collaboration/AnswersPortlet", "");
		info("Go to Application");
		goToApplicationRegistry();

		info("Configure to show import applications");
		showImportApplication(true);

		info("Click Import Application");
		click(ELEMENT_IMPORT_APPLICATION);
		waitForConfirmation(IMPORT_APPLICATION_CONFIRMATION);
		pause(1000);

		info("--Go to intranet--");
		goToIntranet();

		info("Go to Add page editor");
		goToAddPageEditor();

		info("Up level");
		click(ELEMENT_UP_LEVEL);

		info("Create Answer page");
		addNewPageEditor(DATA_ANSWER_PAGE_NAME, DATA_ANSWER_PAGE_NAME,"",CATEGORY_TITLE, 
				ANSWER_PORTLET_ID, false);	
	}

	/**
	 * Create Answer and FAQ page at root path
	 * @author hakt
	 */
	public static void createAnswerAndFAQPage()	{
		Map<String, String> ANSWER_FQA_PORTLET_ID = new HashMap<String, String>();
		ANSWER_FQA_PORTLET_ID.put("faq/local._faq.AnswersPortlet", "faq/local._faq.FAQPortlet");
		
		info("Configure to show import applications");
		showImportApplication(true);

		info("Click Import Application");
		click(ELEMENT_IMPORT_APPLICATION);
		waitForConfirmation(IMPORT_APPLICATION_CONFIRMATION);
		pause(1000);

		info("--Go to intranet--");
		goToIntranet();

		info("Go to Add page editor");
		goToAddPageEditor();

		info("Up level");
		click(ELEMENT_UP_LEVEL);

		info("Create Answer&FAQ page");
		addNewPageEditor(DATA_ANSWER_FAQ_PAGE_NAME, DATA_ANSWER_FAQ_PAGE_NAME,"",CATEGORY_TITLE, 
				ANSWER_FQA_PORTLET_ID, false);	
	}

	/**
	 * Go to Intranet
	 * @author hakt
	 */
	public static void goToIntranet(){
		info("--Go to Intranet--");
		waitForAndGetElement(By.xpath(ELEMENT_MYSITE_LINK));
		mouseOver(ELEMENT_MYSITE_LINK, true);
		pause(500);
		click(ELEMENT_INTRANET_LINK);
		pause(500);
	}

	/**
	 * Go to Forum
	 * @author hakt
	 */
	public static void goToForums(){
		info("--Go to Forums--");
		waitForAndGetElement(ELEMENT_MYSITE);
		mouseOver(ELEMENT_MYSITE, true);
		mouseOver(ELEMENT_INTRANET_LINK, true);
		click(ELEMENT_FORUM_LINK);
	}

	/**
	 * Go to Wiki
	 * @author hakt
	 */
	public static void goToWiki(){
		info("--Go to Wiki--");
		waitForAndGetElement(By.xpath(ELEMENT_MYSITE_LINK));
		mouseOver(ELEMENT_MYSITE_LINK, true);
		pause(500);
		mouseOver(ELEMENT_INTRANET_LINK, true);
		pause(500);
		click(ELEMENT_WIKI_LINK);
		pause(500);	
	}

	/** 
	 * Go to Answer
	 * @author hakt
	 */
	public static void goToAnswer(){
		info("--Go to Answer--");
	    waitForAndGetElement(By.xpath(ELEMENT_MYSITE_LINK));
	    mouseOver(ELEMENT_MYSITE_LINK, true);
	    pause(500);
	    mouseOver(ELEMENT_INTRANET_LINK, true);
	    WebElement answer = waitForAndGetElement(ELEMENT_ANSWER_LINK, 10000, 0);
	    WebElement answerXpath = waitForAndGetElement(ELEMENT_ANSWER_LINK_XPATH, 10000, 0);
	    if (answer == null || answerXpath == null){
	            createAnswerPageAtRootPath();                        
	    	} else {
	            click(ELEMENT_ANSWER_LINK);
	    } 
	}
	

	/** 
	 * Edit Answer at Page management, end at clicking edit answer portlet
	 * @author hakt
	 */
	public static void editAnswerAtPageManagement(){
		goToManagePages();
		editPageAtManagePages(PageType.PORTAL, "Answer");
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET,true);	
		click(ELEMENT_EDIT_PORTLET_ICON);
		pause(1000);
	}

	/** 
	 * Save Answer at Page management, After configuring answer portlet
	 * @author hakt
	 */
	public static void saveAnswerPageAfterEditing(){
		pause(1000);
		save();
		waitForTextPresent(MSG_SAVE_ANSWER_PORTLET_SETTING);
		click(By.linkText("OK"));
		close();
		pause(500);
		click(ELEMENT_PAGE_FINISH_BUTTON);
		waitForTextNotPresent("Page Editor");
	}

	/**
	 * @author hakt
	 * This function to configure answerPortlet
	 * @param elementToConfig: Tab to select, eg Display Mode
	 * @param elementToCheck: item to check
	 * @param check: true: uncheck checkbox
	 * 				 false: keep original status
	 */
	public static void configAnswerPorlet(By elementToConfig, By elementToCheck, boolean check){
		click(elementToConfig);
		WebElement element = waitForAndGetElement(elementToCheck);
		if (element.isSelected() == check){
			click(elementToCheck);
		}
	}

	/**function: select a user when set permission for a element
	 * @author lientm
	 * @param element: id of element need set permission
	 * @param user: user that needs to set permission
	 */
	public static void selectUserPermission(String user, int...type){
		String[] temp = user.split("/");
		if (temp.length > 0){
			for (int i = 0; i < temp.length; i ++){
				By ELEMENT_USER = By.xpath(ELEMENT_USER_CHECKBOX.replace("${user}", temp[i]));
				By ELEMENT_FIRST = By.xpath("//*[@id='UIListUsers']/tbody/tr[2]/td/*[text()='" + temp[i] + "']");
				By ELEMENT_SECOND = By.xpath("//*[@id='UIListUsers']/tbody/tr[3]");
				if (type.length > i){
					type(ELEMENT_SEARCH_USER_INPUT, temp[i], true);
					switch (type[i]){
					case 1:
						select(ELEMENT_SELECT_SEARCH, "Username");
						break;
					case 2:	
						select(ELEMENT_SELECT_SEARCH, "First Name");
						break;
					case 3:	
						select(ELEMENT_SELECT_SEARCH, "Last Name");
						break;
					case 4:	
						select(ELEMENT_SELECT_SEARCH, "Email Name");
						break;
					default:
						break;
					}
					click(ELEMENT_QUICK_SEARCH_BUTTON);
					waitForElementPresent(ELEMENT_FIRST);
					waitForElementNotPresent(ELEMENT_SECOND);
				}
				waitForElementPresent(ELEMENT_USER);
				if (waitForAndGetElement(ELEMENT_USER, 10000, 0).isSelected() == false){
					click(ELEMENT_USER);
				}
			}
		}
		click(ELEMENT_ADD_BUTTON);
		waitForElementNotPresent(ELEMENT_SEARCH_ICON);
	}

	/**function: select a group when set permission
	 * @author lientm
	 * @param element: id of element need set permission
	 * @param grouppath: group path
	 */
	public static void selectGroupPermission( String grouppath){
		/*//By ELEMENT_SELECT_GROUP = By.xpath(ELEMENT_SELECT_GROUP_ICON.replace("${element}", element));

		waitForElementPresent(ELEMENT_SELECT_GROUP);
		click(ELEMENT_SELECT_GROUP);*/
		waitForElementPresent(ELEMENT_SELECT_GROUP_POPUP);
		selectGroup(grouppath);
		click(ELEMENT_SELECT_THIS_GROUP);
	}

	/**function: select group and membership when set permission
	 * @author lientm
	 * @param element: id of element need set permission
	 * @param groupPath: path to group
	 * @param membership
	 */
	public static void selectGroupMembership(String groupPath, String membership){
		/*By ELEMENT_SELECT_ROLE = By.xpath(ELEMENT_SELECT_ROLE_ICON.replace("${element}", element));

		waitForElementPresent(ELEMENT_SELECT_ROLE);
		click(ELEMENT_SELECT_ROLE);*/
		waitForElementPresent(ELEMENT_SELECT_ROLE_POPUP);
		selectGroup(groupPath);	
		click(By.linkText(membership));
		//waitForTextPresent(membership);
	}

	/** function: input data to a frame in other frame
	 * @author lientm
	 * @param frame1
	 * @param frame2
	 * @param data
	 * @param validate = null: clear data before
	 *                 = true: clear data before
	 *                 = false: not clear data before
	 */

	public static void inputDataToFrameInFrame(By frame1, By frame2, String data, boolean...validate){
		boolean valid = validate.length > 0 ? validate[0]: true;
		try {
			WebElement inputsummary = null;

			for (int repeat = 0;; repeat++) {
				if (repeat >= DEFAULT_TIMEOUT/WAIT_INTERVAL) {
					info("Fail to input data to frame");
				}
				driver.switchTo().frame(waitForAndGetElement(frame1));
				driver.switchTo().frame(waitForAndGetElement(frame2));
				inputsummary = driver.switchTo().activeElement();
				if (valid){
					((JavascriptExecutor) driver).executeScript("document.body.innerHTML='" + data + "'");
				}else {
					inputsummary.sendKeys(data); break;
				}
				info(inputsummary.getText());
				if (data.equals(inputsummary.getText())) 
					break;
				switchToParentWindow();
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			inputDataToFrameInFrame(frame1, frame2, data, validate);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			inputDataToFrameInFrame(frame1, frame2, data, validate);
		} finally {
			loopCount = 0;
		}
	}

	/** function: Attach file in attach popup
	 * @author lientm
	 * @param number: number of upload container that need upload file
	 * @param filePath: path to file upload
	 */
	public static void attachFile(String number, String filePath){
		By ELEMENT_UPLOAD = By.xpath(ELEMENT_UPLOAD_FILE.replace("${No}", number));

		if (filePath != ""){
			if (waitForAndGetElement(ELEMENT_UPLOAD) != null) {
				info("Upload file " + getAbsoluteFilePath(filePath));
				type(ELEMENT_UPLOAD, getAbsoluteFilePath(filePath), false);
				String links[] = filePath.split("/");
				int length = links.length;
				waitForElementPresent(By.xpath("//*[contains(@id, 'UploadInputContainer' )][" + number + "]/*//div[contains(text(),'" + links[length-1] + "')]"));
				info("Upload file " + filePath + "successfully");
			} else {
				info("Can not found upload locator");
			}
		}
	}

	/** function: attach some file in attach popup
	 * @author lientm
	 * @param file: file path
	 */
	public static void attachSomeFile(String...file){
		for (int i = 0; i < file.length; i ++ ){				
			attachFile(Integer.toString(i + 1), file[i]);
		}
		save();
	}
}
