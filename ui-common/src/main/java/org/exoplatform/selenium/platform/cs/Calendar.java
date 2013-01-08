package org.exoplatform.selenium.platform.cs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;
import static org.exoplatform.selenium.TestLogger.*;

public class Calendar extends CsBase {
	//General
	public static By ELEMENT_ACTION_CAL_ICON = By.xpath("//div[@title='Calendar Actions']");
	public static By ELEMENT_ADD_CALENDAR = By.xpath("//a[contains(text(),'Add Calendar')]");
	public static By ELEMENT_ADD_GROUP = By.xpath("//a[contains(text(),'Add Group')]");
	public static String ELEMENT_SETTING_CALENDAR = "//a[@title='{$calendar}']/ancestor::li//div[@class='IconHoverSetting']";
	public static By ELEMENT_EDIT_CALENDAR = By.xpath("//a[@class='ItemIcon  EditCalendarIcon' and contains(text(),'Edit')]"); 
	public static By ELEMENT_DELETE_CALENDAR = By.xpath("//a[contains(@title,'Remove')]");
	public static By ELEMENT_SHARE_CALENDAR = By.xpath("//a[contains(@title,'Share')]");
	public static String ELEMENT_MY_CAL_ICON = "//h6[contains(text(),'Personal Calendars')]/../..//a[@title='{$calendar}']/ancestor::li";
	public static String ELEMENT_SHARE_CAL_ICON = "//h6[contains(text(),'Shared Calendars')]/../..//a[@title='{$calendar}']/ancestor::li";
	public static String ELEMENT_GROUP_CAL_ICON = "//h6[contains(text(),'Group Calendars')]/../..//a[@title='{$calendar}']/ancestor::li";
	public static String ELEMENT_CALENDAR_ICON = "//a[@title='{$calendar}']/ancestor::li";
	public static String ELEMENT_CAL_CONTAIN_ICON = "//a[@title='{$calendar}']/ancestor::li";
	public static String ELEMENT_COLOR_CAL_PANE_ICON = "//a[contains(@class,'{$color}')]/img";
	public static String ELEMENT_COLOR_CAL_ICON = "//a[@title='{$calendar}']/ancestor::li//div[@class='CalendarCheckboxBlock']/a[@class='{$color}']";
	public static String ELEMENT_COLOR_ICON = "//a[@title='{$calendar}']/ancestor::li//div[@class='CalendarCheckboxBlock']/a";
	public static By ELEMENT_ADD_REMOTE_CAL_LINK = By.xpath("//*[@id='tmpMenuElement']//a[contains(text(),'Remote Calendar')]");
	public static By ELEMENT_IMPORT_CAL_LINK = By.xpath("//*[@id='tmpMenuElement']//a[contains(text(),'Import')]");
	public static String ELEMENT_GROUP_LINK = "//div[@title='{$group}']";
	public static String ELEMENT_COLOR_CHECK = "//a[@title='{$calendar}']/..//span[@class='ColorOpacity']";
	public static By ELEMENT_SELECT_THIS_GROUP = By.linkText("Select this Group");

	public static String ELEMENT_GROUP_ICON = "//div[@title='{$group}']/..";
	//-----Add a calendar-----
	public static By ELEMENT_NAME_CAL_INPUT = By.id("displayName");
	public static By ELEMENT_DESC_CAL_INPUT = By.xpath("//div[@class='calendarDetail']//*[@id='description']");
	public static By ELEMENT_GROUP_CAL_INPUT = By.xpath("//div[@class='calendarDetail']//select[@name='category']");
	public static By ELEMENT_COUNTRY_CAL_INPUT = By.id("locale");
	public static By ELEMENT_TIME_ZONE_CAL_INPUT = By.id("timeZone");
	public static By ELEMENT_COLOR_CAL_INPUT = By.xpath("//div[@class='UIColorPickerInput']/span");
	public static String ELEMENT_SELECT_USER_CAL_ICON = "//input[@id='{$group}']/ancestor::tr//a[@title='Select User']";
	public static String ELEMENT_SELECT_MEM_CAL_ICON = "//input[@id='{$group}']/ancestor::tr//a[@title='Select Membership']";
	public static By ELEMENT_GROUP_TAB_CAL = By.xpath("//div[@class='MiddleTab' and text()='Groups']");
	public static By ELEMENT_ADD_GROUP_CAL_ICON = By.xpath("//img[@class='AddNewNodeIcon']");
	public static String ELEMENT_COLOR_ADD_CAL_ICON = "//div[@class='calendarDetail']//a[contains(@class,'{$color}')]/img";

	//--------------Add a group screen-------------
	public static By ELEMENT_GROUP_NAME_INPUT = By.id("categoryName");
	public static By ELEMENT_GROUP_DESC_INPUT = By.xpath("//form[@id='UICalendarCategoryForm']//*[@id='description']");
	public static By ELEMENT_GROUP_SAVE_BUTTON = By.xpath("//form[@id='UICalendarCategoryForm']//a[text()='Save']");

	//-------------Share a calendar--------------
	public static By ELEMENT_TITLE_SHARE_CAL_FORM = By.xpath("//span[@class='PopupTitle' and text()='Share Calendar']");
	public static By ELEMENT_USER_NAME_INPUT = By.id("username");
	public static By ELEMENT_GROUP_INPUT = By.id("group");
	public static By ELEMENT_SELECT_USER_SHARE = By.xpath("//a[@title='User']");
	public static By ELEMENT_SELECT_USER_TITLE = By.xpath("//span[@class='PopupTitle' and text()='Select Users']");
	public static By ELEMENT_SELECT_GROUP_TITLE = By.xpath("//span[@class='PopupTitle' and text()='Group Selector']");
	public static By ELEMENT_SELECT_GROUP_SHARE = By.xpath("//a[@title='Group']");
	public static By ELEMENT_EDIT_PERMISSION_CHECK = By.id("canEdit");
	public static String ELEMENT_EDIT_SHARE_ICON = "//div[@title='{$user}']/../..//img[@alt='Edit']";
	public static String ELEMENT_DELETE_SHARE_ICON = "//div[@title='{$user}']/../..//img[@alt='Delete']";
	public static By ELEMENT_ADD_BUTTON = By.linkText("Add");
	//----------Add a remote calendar-------------
	//Subscribe a calendar
	public static By ELEMENT_ICALENDAR_RADIO = By.id("type_ICalendar(.ics)");
	public static By ELEMENT_CALDAV_RADIO = By.id("type_CalDAV");
	public static By ELEMENT_URL_REMOTE_CAL = By.id("url");
	// Add a remote calendar
	public static By ELEMENT_NAME_REMOTE_CAL_INPUT = By.xpath("//div[@class='UIForm UIRemoteCalendar']//*[@id='name']");
	public static By ELEMENT_DESC_REMOTE_CAL_INPUT = By.xpath("//div[@class='UIForm UIRemoteCalendar']//*[@id='description']");
	public static By ELEMENT_COLOR_REMOTE_CAL_INPUT = By.xpath("//div[@class='UIColorPickerInput']");
	public static By ELEMENT_BEFORE_DATE_REMOTE_INPUT = By.id("beforeDate");
	public static By ELEMENT_AFTER_DATE_REMOTE_INPUT = By.id("afterDate");
	public static By ELEMENT_USE_AUTHEN_REMOTE_CHECK = By.xpath("//input[@id='useAuthentication']");
	public static By ELEMENT_USERNAME_REMOTE_INPUT = By.id("username");
	public static By ELEMENT_PASS_REMOTE_INPUT = By.id("password");
	public static String ELEMENT_COLOR_PANE_REMOTE_ICON = "//div[@class='UIForm UIRemoteCalendar']//a[contains(@class,'{$color}')]/img";
	public static String MSG_ADD_REMOTE_SUCCESS = "The remote calendar was imported successfully.";

	//-----------Import calendars------------
	public static By ELEMENT_GROUP_IMPORT_CAL_INPUT = By.name("category");
	public static By ELEMENT_UPLOAD_CAL_INPUT = By.id("file"); 
	public static By ELEMENT_UPLOAD_CAL_FRAME = By.xpath("//div[@class='UploadIframe']/iframe");
	public static String ELEMENT_UPLOAD_FILE_NAME = "//div[@class='FileNameLabel' and contains(text(),'{$filename}')]"; 
	public static By ELEMENT_DESC_IMPORT_INPUT = By.xpath("//form[@id='UIImportForm']//*[@id='description']");
	public static By ELEMENT_GROUP_IMPORT_INPUT = By.xpath("//form[@id='UIImportForm']//select[@name='category']");
	public static String ELEMENT_COLOR_IMPORT_ICON = "//form[@id='UIImportForm']//a[contains(@class,'{$color}')]/img";
	public static String MSG_DELETE_SHARE_CAL = "Are you sure to delete this category?";
	/**
	 * @author thuntn
	 * @param name: name of calendar
	 * @param desc: description of calendar
	 * @param group: group of calendar
	 * @param color: color of calendar, ex: Pink, RosyBrown...
	 * @param groupUser: user groups have edit permission
	 * @param user: users have edit permission 
	 * @param membership: membership edit permission, optional
	 */
	public void addCalendar(String name, String desc, String group, String color, String[] groupUser, String[] user, String...membership ) {
		info("--Add a calendar--");

		goToAddCalendarForm();
		inputFormCalendarDetail(name, desc, group, color);
		click(ELEMENT_GROUP_TAB_CAL);
		inputFormCalendarGroups(groupUser, user, membership);
		save();
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
	}
	/** Input data to Form 'Add Calendar', tab Details
	 * @author thuntn
	 * @param name: name of calendar
	 * @param desc: description of calendar
	 * @param group: group of calendar
	 * @param color: color of calendar, ex: Pink, RosyBrown...
	 */
	public static void inputFormCalendarDetail(String name, String desc, String group, String color){
		info("--Input data to Form 'Add Calendar'--");
		if (name != null)
			type(ELEMENT_NAME_CAL_INPUT, name, true);
		if (desc != null)
			type(ELEMENT_DESC_CAL_INPUT, desc, true);
		if (group != null){
			WebElement option = null; 
			click(ELEMENT_GROUP_CAL_INPUT);
			option = waitForElementPresent(By.xpath("//option[text()='"+ group +"']"),15000,0);
			if (option != null){
				select(ELEMENT_GROUP_CAL_INPUT,group);
			}else{
				click(ELEMENT_ADD_GROUP_CAL_ICON);
				inputFormGroup(group,group);
				click(ELEMENT_GROUP_SAVE_BUTTON);
				close();
				waitForElementNotPresent(ELEMENT_GROUP_SAVE_BUTTON);
			}
		}
		if (color != null){
			click(ELEMENT_COLOR_CAL_INPUT); 
			click(ELEMENT_COLOR_ADD_CAL_ICON.replace("{$color}", color));
		}
	}

	/** Select groups for a calendar
	 * @author thuntn
	 * @param groupUser: user groups have edit permission, ex: {"/developers"}
	 * @param user: users have edit permission, ex: {"mary/james"} 
	 * @param membership: membership edit permission, optional
	 */
	public static void inputFormCalendarGroups(String[] group, String[] user, String[] membership){
		int length = group.length;
		info("--Select groups for a calendar--");

		for(int i = 0;i < length;i++){
			click(By.id(group[i]));
			if (user.length > i){
				String[] users = user[i].split("/");

				for(int j = 0; j < users.length; j++){
					click(ELEMENT_SELECT_USER_CAL_ICON.replace("{$group}", group[i]));
					click(By.linkText(users[j]));
				}
			}
			if (membership.length > 0){
				String memberships[] = membership[i].split("/");
				for(int k = 0; k < memberships.length; k++){
					click(ELEMENT_SELECT_MEM_CAL_ICON.replace("{$group}", group[i]));
					click(By.linkText(memberships[k]));
				}
			}
		}

	}
	/** Add a group
	 * @author thuntn
	 * @param name: name of group
	 * @param desc: description of group
	 */
	public static void addGroup(String name, String desc){
		info("--Add a group--");
		goToAddGroupForm();
		inputFormGroup(name, desc);
		save();
		waitForElementNotPresent(ELEMENT_GROUP_NAME_INPUT);
	}
	/** Input a form 'Add/Edit Group'
	 * @author thuntn
	 * @param name: name of group, if null, not input anything
	 * @param desc: description of group, if null, not input
	 */
	public static void inputFormGroup(String name, String desc){
		info("--Input data into a form Group--");
		if (name != null)
			type(ELEMENT_GROUP_NAME_INPUT, name,true);
		if (desc != null)
			type(ELEMENT_GROUP_DESC_INPUT, desc, true);
	}
	/** Get color of a calendar
	 * @author thuntn
	 * @param calendar: name
	 * @return: color of calendar
	 */
	public static String getColorOfCalendar(String calendar){
		String color = waitForAndGetElement(ELEMENT_COLOR_ICON.replace("{$calendar}", calendar)).getAttribute("class");
		return color;
	}
	/** Get ID of a calendar
	 * @author thuntn
	 * @param calendar
	 * @return: ID of calendar
	 */
	public static String getIDOfCalendar(String calendar){
		WebElement wCalendar = waitForAndGetElement(ELEMENT_CALENDAR_ICON.replace("{$calendar}", calendar));
		String idCal = wCalendar.getAttribute("id");
		return idCal;
	}
	/** Execute actions for a calendar by javascript
	 * @author thuntn
	 * @param idCal: id of calendar
	 * @param color: color of calendar
	 * @param type: type of calendar: = 0: with personal calendar
	 * 									1: with share calendar
	 * 									2: with group calendar
	 * 									
	 */
	public static void executeActionCalendar(String idCal, String action, String color, int type){
		((JavascriptExecutor)driver).executeScript("javascript:eXo.webui.UIForm.submitEvent('"+IDPAGE+"#UICalendars','"+action+"','&objectId="+idCal+"&calType="+type+"&calColor="+color+"')");
	}
	/** Go to a form 'Edit Calendar'
	 * @author thuntn
	 */
	public static void goToEditCalendar(String calendar){

		info("--Go to edit calendar form--");

		String idCal = getIDOfCalendar(calendar);
		String color = getColorOfCalendar(calendar);
		if(waitForAndGetElement(ELEMENT_MY_CAL_ICON.replace("{$calendar}", calendar),5000,0) != null){
			executeActionCalendar(idCal,"EditCalendar", color, 0);
		}else{
			if(waitForAndGetElement(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", calendar),2000,0) != null){
				executeActionCalendar(idCal,"EditCalendar", color, 1);
			}else{
				executeActionCalendar(idCal,"EditCalendar", color, 2);
			}
		}

		waitForElementPresent(ELEMENT_NAME_CAL_INPUT);
	}
	/** Edit a calendar
	 * @author thuntn
	 * @param oldName: old name of calendar
	 * @param name: new name of calendar, if don't change value, pass null to this
	 * @param desc: new description of calendar, if don't change value, pass null to this
	 * @param group: new group of calendar, if don't change value, pass null to this
	 * @param color: new color, if don't change value, pass null to this, ex: Pink, RosyBrown...
	 * @param groupUser: new user group can edit calendar, if don't change value, pass an empty array to this
	 * @param user: new user, if don't change value, pass an empty array to this
	 * @param membership: new membership, if don't change value, not pass to this
	 */
	public static void editCalendar(String oldName, String name, String desc, String group, String color, String[] groupUser, String[] user, String...membership){

		goToEditCalendar(oldName);

		info("--Edit a calendar--");
		inputFormCalendarDetail(name, desc, group, color);
		if (waitForAndGetElement(ELEMENT_GROUP_TAB_CAL,5000,0) != null){
			click(ELEMENT_GROUP_TAB_CAL);
			inputFormCalendarGroups(groupUser, user, membership);
		}
		save();
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
	}
	/** Delete a calendar
	 * @author thuntn
	 * @param name: name of calendar
	 */
	public static void deleteCalendar(String name){

		info("--Delete a Calendar-");
		String idCal = getIDOfCalendar(name);
		String color = getColorOfCalendar(name);
		if(waitForAndGetElement(ELEMENT_MY_CAL_ICON.replace("{$calendar}", name),5000,0) != null){
			executeActionCalendar(idCal,"RemoveCalendar", color, 0);
		}else{
			if(waitForAndGetElement(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", name),2000,0) != null){
				executeActionCalendar(idCal,"RemoveCalendar", color, 1);
			}else{
				executeActionCalendar(idCal,"RemoveCalendar", color, 2);
			}
		}
		waitForElementNotPresent(ELEMENT_CALENDAR_ICON.replace("{$calendar}", name));
	}

	/** Go to a form 'Share a calendar' 
	 * @author thuntn
	 * @param name: name of calendar
	 */
	public static void goToShareCalendar(String name){
		info("--Go to a form 'Share a calendar'--");
		String idCal = getIDOfCalendar(name);
		String color = getColorOfCalendar(name);
		if(waitForAndGetElement(ELEMENT_MY_CAL_ICON,5000,0) != null){
			executeActionCalendar(idCal,"ShareCalendar", color, 0);
		}else{
			if(waitForAndGetElement(ELEMENT_SHARE_CAL_ICON,2000,0) != null){
				executeActionCalendar(idCal,"ShareCalendar", color, 1);
			}else{
				executeActionCalendar(idCal,"ShareCalendar", color, 2);
			}
		}
		waitForElementPresent(ELEMENT_TITLE_SHARE_CAL_FORM);
	}
	/** Input data to a form 'Share a Calendar'
	 * @author thuntn
	 * @param user: users which this calendar is shared with
	 * @param group: groups which this calendar is shared with
	 * @param mode: mode, if mode[0] = 0: type user directly
	 * 						 mode[0] != 0: select user via icon
	 * 					  if mode[1] = 0: type group directly
	 * 						 mode[1] != 0: select group via icon
	 * 				optional, if not pass, by default, type user/group directly
	 */
	public static void inputFormShareCalendar(String[] user, String[] group, boolean canEdit, int...mode){
		int modeUser = mode.length > 0 ? mode[0] : 0;
		int modeGroup = mode.length > 1 ? mode[1] : 0;		
		info("--Input data to a form 'Share a Calendar'--");
		if (modeUser == 0){
			for(int i = 0; i < user.length; i++){
				info("loop: " + i + " user " + user[i]);
				type(ELEMENT_USER_NAME_INPUT, user[i] + ",",false);

			}
		}else{
			click(ELEMENT_SELECT_USER_SHARE);
			waitForElementPresent(ELEMENT_SELECT_USER_TITLE);
			for(int i = 0; i < user.length; i++){
				click(By.id(user[i]));
			}
			click(ELEMENT_ADD_BUTTON);
		}
		if(modeGroup == 0){
			if(group != null){
				for(int i = 0;i < group.length; i++){
					type(ELEMENT_GROUP_INPUT, group[i], true);
				}
			}
		}else{
			for(int i = 0; i < group.length; i++ ){
				click(ELEMENT_SELECT_GROUP_SHARE);
				waitForElementPresent(ELEMENT_SELECT_GROUP_TITLE);
				selectGroup(group[i]);
				click(By.linkText("Select this Group"));
			}
		}
		if(canEdit)
			check(ELEMENT_EDIT_PERMISSION_CHECK);
		else
			uncheck(ELEMENT_EDIT_PERMISSION_CHECK);
	}
	/** Share a calendar
	 * @author thuntn
	 * @param name: name of calendar
	 * @param user: users which this calendar is shared with
	 * @param group: groups which this calendar is shared with, ex: {"Platform/Users"} if mode =1, {"/platform/users"} if mode =0
	 * @param mode: mode, if mode[0] = 0: type user directly
	 * 						 mode[0] != 0: select user via icon
	 * 					  if mode[1] = 0: type group directly
	 * 						 mode[1] != 0: select group via icon
	 * 				optional, if not pass, by default, type user/group directly
	 */
	public static void shareCalendar(String name, String[] user, String[] group,boolean canEdit, int...mode){

		goToShareCalendar(name);
		inputFormShareCalendar(user, group,canEdit, mode);

		save();
		waitForElementPresent(ELEMENT_EDIT_SHARE_ICON.replace("{$user}", user[0]));
		cancel();
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
		info("Share a calendar successfully!");
	}
	/** Change color of Calendar
	 * @author thuntn
	 * @param name: name of calendar
	 * @param color: color of calendar, ex: Pink, RosyBrown...
	 */
	public static void changeCalendarColor(String name, String color){
		info("--Change color of Calendar--");
		String idCal = getIDOfCalendar(name);
		if(waitForAndGetElement(ELEMENT_MY_CAL_ICON.replace("{$calendar}", name),5000,0) != null){
			executeActionCalendar(idCal,"ChangeColor", color, 0);
		}else{
			if(waitForAndGetElement(ELEMENT_SHARE_CAL_ICON.replace("{$calendar}", name),2000,0) != null){
				executeActionCalendar(idCal,"ChangeColor", color, 1);
			}else{
				executeActionCalendar(idCal,"ChangeColor", color, 2);
			}
		}
		waitForElementPresent(ELEMENT_COLOR_CAL_ICON.replace("{$calendar}", name).replace("{$color}", color));
	}
	/** Input data to a form 'Subscribe a calendar'
	 * @author thuntn
	 * @param type: if 0, select ICalendar, 
	 * 				else, select CALDAV type
	 * @param url: url
	 */
	public static void inputFormSubscribeCalendar(int type, String url){

		info("--Input data to a form 'Subscribe a calendar'--");
		if (type == 0)
			click(ELEMENT_ICALENDAR_RADIO);
		else
			click(ELEMENT_CALDAV_RADIO);
		if (url != null)
			type(ELEMENT_URL_REMOTE_CAL, url, true);
	}
	/** Go to add a remote Calendar
	 * @author thuntn
	 */
	public static void goToAddRemoteCalendar(){
		info("--Go to add a remote calendar--");

		click(ELEMENT_ACTION_CAL_ICON);
		click(ELEMENT_ADD_REMOTE_CAL_LINK);
		waitForElementPresent(ELEMENT_ICALENDAR_RADIO);
	}
	/** Subscribe a calendar
	 * @author thuntn
	 * @param type: if 0, select ICalendar, 
	 * 				else, select CALDAV type
	 * @param url: url
	 */
	public static void subscribeCalendar(int type, String url){
		goToAddRemoteCalendar();
		inputFormSubscribeCalendar(type, url);

	}
	/** Input data to a form Remote calendar
	 * @author thuntn
	 * @param name: name of calendar
	 * @param desc: description
	 * @param color: name of color, ex: Pink, RosyBrown...
	 * @param beDate: before Date of calendar
	 * @param afDate: after Date
	 * @param authen: true: use authentication
	 * 				  false: not use
	 * @param user: user name
	 * @param pass: password
	 */
	public static void inputFormRemoteCalendar(String name, String desc, String color, String beDate, String afDate, boolean authen, String user, String pass){
		info("--Input data to a form Remote calendar--");

		if(name != null)
			type(ELEMENT_NAME_REMOTE_CAL_INPUT,name,true);
		if (desc != null)
			type(ELEMENT_DESC_REMOTE_CAL_INPUT, desc, true);
		if (color != null){
			click(ELEMENT_COLOR_REMOTE_CAL_INPUT);
			click(ELEMENT_COLOR_PANE_REMOTE_ICON.replace("{$color}", color));
		}
		if (beDate != null){
			select(ELEMENT_BEFORE_DATE_REMOTE_INPUT,beDate);
		}
		if(afDate != null){
			select(ELEMENT_AFTER_DATE_REMOTE_INPUT,afDate);
		}
		if (authen){
			check(ELEMENT_USE_AUTHEN_REMOTE_CHECK);
			if(user != null)
				type(ELEMENT_USERNAME_REMOTE_INPUT,user,true);
			if (pass != null)
				type(ELEMENT_PASS_REMOTE_INPUT,pass,true);
		}else{
			uncheck(ELEMENT_USE_AUTHEN_REMOTE_CHECK);
		}

	}
	/** Add a remote calendar
	 * @author thuntn
	 * @param type: if 0, select ICalendar, 
	 * 				else, select CALDAV type
	 * @param url: url
	 * @param name: name of calendar
	 * @param desc: description
	 * @param color: name of color, ex: Pink, RosyBrown...
	 * @param beDate: before Date of calendar
	 * @param afDate: after Date
	 * @param authen: true: use authentication
	 * 				  false: not use
	 * @param user: user name
	 * @param pass: password
	 */
	public static void addRemoteCalendar(int type, String url, String name, String desc, String color, String beDate, String afDate, boolean authen, String user, String pass){
		subscribeCalendar(type, url);
		next();
		inputFormRemoteCalendar(name, desc, color, beDate, afDate, authen, user, pass);
		save();
		waitForElementNotPresent(ELEMENT_USE_AUTHEN_REMOTE_CHECK);
		waitForMessage(MSG_ADD_REMOTE_SUCCESS);
		closeMessageDialog();
		info("Add a remote calendar successfully!");
	}
	/** Upload a calendar
	 * @author thuntn
	 * @param path: path of calendar
	 */
	public static void uploadCalendar(String path){
		info("--Upload Calendar--");
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_UPLOAD_CAL_FRAME));
		type(ELEMENT_UPLOAD_CAL_INPUT,getAbsoluteFilePath(path),false);
		info("import " + getAbsoluteFilePath(path));
		//		driver.switchTo().defaultContent();
		switchToParentWindow();
	}
	/** Go to import a calendar
	 * @author thuntn
	 */
	public static void goToImportCalendar(){
		info("--Go to import a calendar--");
		click(ELEMENT_ACTION_CAL_ICON);
		click(ELEMENT_IMPORT_CAL_LINK);
		waitForElementPresent(ELEMENT_UPLOAD_CAL_FRAME);
	}
	/** Import default calendar, not edit default value
	 * @author thuntn
	 * @param path: path of calendar
	 */
	public static void importDefaultCalendar(String path){
		goToImportCalendar();
		uploadCalendar(path);
		String[] paths= path.split("/");
		waitForElementPresent(ELEMENT_UPLOAD_FILE_NAME.replace("{$filename}", paths[paths.length-1]));
		save();
		waitForElementNotPresent(ELEMENT_UPLOAD_CAL_INPUT);
	}
	/** Input data to Form 'Import calendar'
	 * @author thuntn
	 * @param name: name of calendar
	 * @param desc: description of calendar
	 * @param group: group of calendar
	 * @param color: color of calendar, ex: Pink, RosyBrown...
	 */
	public static void inputFormCalendarImport(String name, String desc, String group, String color){
		String id = getIDOfGroup(group);
		
		info("--Input data to Form 'Import calendar'-- "+ id);
		if (name != null)
			type(ELEMENT_NAME_CAL_INPUT, name, true);
		if (desc != null)
			type(ELEMENT_DESC_IMPORT_INPUT, desc, true);
		if (group != null){
			click(ELEMENT_GROUP_IMPORT_INPUT);
			click("//option[text()='"+group+"']");
		}
		if (color != null){
			click(ELEMENT_COLOR_CAL_INPUT); 
			click(ELEMENT_COLOR_IMPORT_ICON.replace("{$color}", color));
		}
	}
	/** Import a calendar,  default value can be edited by following parameters 
	 * @author thuntn
	 * @param path: path of calendar
	 * @param name: name
	 * @param desc: description
	 * @param group: group
	 * @param color: color;
	 */
	public static void importCalendar(String path, String name, String desc, String group, String color){
		info("--Import calendar--");

		goToImportCalendar();
		uploadCalendar(path);
		String[] paths= path.split("/");
		waitForElementPresent(ELEMENT_UPLOAD_FILE_NAME.replace("{$filename}", paths[paths.length-1]));
		inputFormCalendarImport(name, desc, group, color);
		save();
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
	}
	/** Get ID of a group 
	 * @author thuntn
	 * @param group: name of group
	 * @return: ID
	 */
	public static String getIDOfGroup(String group){
		WebElement eGroup = waitForAndGetElement(ELEMENT_GROUP_ICON.replace("{$group}", group));
		return eGroup.getAttribute("id");
	}
	/** Go to import a calendar to a group
	 * @author thuntn
	 * @param group: name of group to which calendar will be imported  
	 */
	public static void goToImportCalendarToGroup(String group){
		info("--Go to import a calendar to a group--");

		executeImportGroup();
		waitForElementPresent(ELEMENT_DESC_IMPORT_INPUT);
	}
	/** Import a calendar to a group
	 * @author thuntn
	 * @param group: name of group
	 * @param path: path of calendar
	 * @param name: name of calendar, if null, not input
	 * @param desc: description of calendar, if null, not input
	 * @param group: group of calendar, if null, not input
	 * @param color: color of calendar, ex: Pink, RosyBrown..., if null, not input
	 */
	public static void importCalendarToGroup(String group, String path,String name, String desc, String groupSelect, String color){
		info("--Import a calendar to a group--");
		
		goToImportCalendarToGroup(group);
		uploadCalendar(path);
		String[] paths= path.split("/");
		waitForElementPresent(ELEMENT_UPLOAD_FILE_NAME.replace("{$filename}", paths[paths.length-1]));
		inputFormCalendarImport(name, desc, groupSelect, color);
		save();
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
	}
	/** Execute script to open a form "Import a Calendar to a group"
	 * @author thuntn
	 */
	public static void executeImportGroup(){
		((JavascriptExecutor)driver).executeScript("javascript:eXo.webui.UIForm.submitForm('"+IDPAGE+"#UICalendars','ImportCalendar',true)");

	}
	/** Execute actions for a group by javascript 
	 * @author thuntn
	 * @param groupID: ID of group
	 * @param action: actions, ex: EditCalendar
	 */
	public static void executeActionGroup(String groupID, String action){
		((JavascriptExecutor)driver).executeScript("javascript:eXo.webui.UIForm.submitEvent('"+IDPAGE+"#UICalendars','"+action+"','&objectId="+groupID+"')");
		
	}
	/** Edit calendar share permission
	 * @author thuntn
	 * @param name: name of calendar
	 * @param oldUserGroup
	 * @param newUser: users which this calendar is shared with
	 * @param newGroup: groups which this calendar is shared with, ex: {"Platform/Users"} if mode =1, {"/platform/users"} if mode =0
	 * @param newCanEdit
	 * @param mode: mode, if mode[0] = 0: type user directly
	 * 						 mode[0] != 0: select user via icon
	 * 					  if mode[1] = 0: type group directly
	 * 						 mode[1] != 0: select group via icon
	 * 					  optional, if not pass, by default, type user/group directly
	 */
	public static void editShareCalendar(String name, String oldUserGroup, String[] newUser,String[] newGroup, boolean newCanEdit, int...mode){
		int[] newMode = {0,0};
		newMode[0] = mode.length > 0 ? mode[0] : 0;
		newMode[1] = mode.length > 1 ? mode[1] : 0;
		
		goToShareCalendar(name);
		click(ELEMENT_EDIT_SHARE_ICON.replace("{$user}", oldUserGroup));
		if(newUser != null)
			waitForAndGetElement(ELEMENT_USER_NAME_INPUT).clear();
		if (newGroup != null)
			waitForAndGetElement(ELEMENT_GROUP_INPUT).clear();
		inputFormShareCalendar(newUser, newGroup, newCanEdit, newMode);
		save();
	}
	/** Delete a calendar share permission
	 * @author thuntn
	 * @param calendar: name of calendar
	 * @param oldUserGroup: name of user/group which need to be edited
	 */
	public static void deleteShareCalendar(String calendar, String oldUserGroup){
		info("--Delete a calendar share permission--");
		goToShareCalendar(calendar);
		click(ELEMENT_DELETE_SHARE_ICON.replace("{$user}", oldUserGroup));
		waitForConfirmation(MSG_DELETE_SHARE_CAL);
		waitForElementNotPresent(ELEMENT_DELETE_SHARE_ICON.replace("{$user}", oldUserGroup));
	}
	
	/**function set up to show/hire calendar
	 * @author lientm
	 * @param calendarName: name of calendar that needs to set
	 * @param show: true -> show calendar
	 *              false -> hire calendar
	 */
	public static void settingHireOrShowCalendar(String calendarName, boolean show){
		String calendarId = getIDOfCalendar(calendarName);
		By element_calendar = By.xpath(ELEMENT_CALENDAR_CHECKBOX.replace("${calendarId}", calendarId));
		
		goToSettings();
		click(ELEMENT_DISPLAY_CALENDAR_TAB);
		WebElement checkbox = waitForAndGetElement(element_calendar);
		if (show && !checkbox.isSelected()){
			check(element_calendar);
		} else if (!show && checkbox.isSelected()){
			uncheck(element_calendar);
		}
		save();
		waitForElementNotPresent(element_calendar);
	}
	
	/**function export a calendar
	 * @author lientm
	 * @param calendarName: name of calendar
	 * @param fileName: name of file export
	 * @param type: type of calendar: = 0: with personal calendar
	 * 									1: with share calendar
	 * 									2: with group calendar
	 */
	public static void exportCalendar(String calendarName, String fileName, int type){
		String idCal = getIDOfCalendar(calendarName);
		String color = getColorOfCalendar(calendarName);
		executeActionCalendar(idCal, "ExportCalendar", color, type);
		exportItem(fileName);
	}
}
