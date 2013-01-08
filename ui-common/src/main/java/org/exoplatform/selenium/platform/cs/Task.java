package org.exoplatform.selenium.platform.cs;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ks.KsBase.selectUserPermission;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
public class Task extends Calendar {
	
	//---------------------------Add task form-------------------------------------------------------
	public static By ELEMENT_QUICK_ADD_TASK_POPUP = By.xpath("//span[@class='PopupTitle' and text()='Quick Add Task']");
	public static By ELEMENT_RIGHT_PANEL_ADD_TASK = By.xpath("//a[contains(text(), 'Add New Task')]");
	public static By ELEMENT_TASK_NAME = By.xpath("//form[@id='UIQuickAddTask']//*[@id='eventName']");
	public static By ELEMENT_TASK_NOTE = By.xpath("//form[@id='UIQuickAddTask']//*[@id='description']");
	public static By ELEMENT_TASK_FROM_DATE = By.xpath("//*[@id='UIQuickAddEvent']//*[@name='from']");
	public static By ELEMENT_TASK_FROM_TIME = By.xpath("//*[@id='UIQuickAddEvent']//*[@id='fromTime']/../input[@class='UIComboboxInput']");
	public static By ELEMENT_TASK_TO_DATE = By.xpath("//*[@id='UIQuickAddEvent']//*[@name='to']");
	public static By ELEMENT_TASK_TO_TIME = By.xpath("//*[@id='UIQuickAddEvent']//*[@id='toTime']/../input[@class='UIComboboxInput']");
	public static By ELEMENT_TASK_ALL_DAY = By.xpath("//*[@id='UIQuickAddEvent']//*[@id='allDay']");
	public static By ELEMENT_TASK_CALENDAR = By.xpath("//*[@id='UIQuickAddEvent']//select[@name='calendar']");
	public static By ELEMENT_TASK_CATEGORY = By.xpath("//*[@id='UIQuickAddTask']//*[@id='category']");
	public static By ELEMENT_TASK_MORE_DETAIL_BUTTON = By.xpath("//*[contains(@onclick,'UIQuickAddTask') and text()='More Details']");
	
	//----------------------Add/Edit More detail task form--------------------------------------------------
	//-----Detail tab-----------
	public static By ELEMENT_ADD_TASK_DETAIL_POPUP = By.xpath("//span[@class='PopupTitle' and text()='Add/Edit Tasks']");
	public static By ELEMENT_ADD_TASK_DETAIL_NAME = By.xpath("//form[@id='UITaskForm']//*[@id='eventName']");
	public static By ELEMENT_ADD_TASK_DETAIL_NOTE = By.xpath("//form[@id='UITaskForm']//*[@id='description']");
	public static By ELEMENT_ADD_TASK_DETAIL_DELEGATIONS = By.id("delegation");
	public static By ELEMENT_ADD_TASK_DETAIL_SELECT_USER = By.xpath("//a[@title='Select User']/img");
	public static By ELEMENT_ADD_TASK_DETAIL_FROM_DATE = By.xpath("//*[@id='UITaskForm']//*[@name='from']");
	public static By ELEMENT_ADD_TASK_DETAIL_FROM_TIME = By.xpath("//*[@id='UITaskForm']//*[@id='fromTime']/../input[@class='UIComboboxInput']");
	public static By ELEMENT_ADD_TASK_DETAIL_TO_DATE = By.xpath("//*[@id='UITaskForm']//*[@name='to']");
	public static By ELEMENT_ADD_TASK_DETAIL_TO_TIME = By.xpath("//*[@id='UITaskForm']//*[@id='toTime']/../input[@class='UIComboboxInput']");
	public static By ELEMENT_ADD_TASK_DETAIL_ALL_DAY = By.xpath("//*[@id='UITaskForm']//*[@id='allDay']");
	public static By ELEMENT_ADD_TASK_DETAIL_PRIORITY = By.id("priority");
	public static By ELEMENT_ADD_TASK_DETAIL_CALENDAR = By.xpath("//*[@id='UITaskForm']//select[@name='calendar']");
	public static By ELEMENT_ADD_TASK_DETAIL_CATEGORY = By.xpath("//*[@id='UITaskForm']//*[@id='category']");
	public static By ELEMENT_ADD_TASK_DETAIL_STATUS = By.id("status");
	public static By ELEMENT_ADD_TASK_DETAIL_ATTACH = By.xpath("//*[@id='UITaskForm']//a[@title='Attach File']/img[@class='AddNewNodeIcon']");
	public static String ELEMENT_ADD_TASK_DETAIL_ATTACH_FRAME = "//*[@id='UIAttachFileForm']//tbody/tr[${No}]//iframe";
	public static By ELEMENT_ADD_TASK_DETAIL_ATTACH_FILE = By.id("file");
	public static String ELEMENT_ADD_TASK_DETAIL_ATTACH_FINISH = "//*[@id='UIAttachFileForm']//tbody/tr[${No}]//div[contains(text(),'${fileName}')]";
	//-------Reminder tab-------
	public static By ELEMENT_ADD_TASK_DETAIL_REMAIDER_TAB = By.xpath("//*[@class='MiddleTab' and text()='Reminders']");
	public static By ELEMENT_ADD_TASK_DETAIL_SEND_EMAIL = By.id("mailReminder");
	public static By ELEMENT_ADD_TASK_DETAIL_MAIL_REMINDER_TIME = By.id("mailReminderTime");
	public static By ELEMENT_ADD_TASK_DETAIL_MAIL_REMINDER_REPEAT = By.id("emailIsRepeat");
	public static By ELEMENT_ADD_TASK_DETAIL_MAIL_REMINDER_SELECT_USER = By.xpath("//a[@title='Select Email']");
	public static By ELEMENT_ADD_TASK_DETAIL_USER_SEARCH = By.id("keyWord");
	public static By ELEMENT_ADD_TASK_DETAIL_USER_SEARCH_BUTTON = By.xpath("//a[@title='Search']");
	public static By ELEMENT_ADD_TASK_DETAIL_USER_ADD_BUTTON = By.linkText("Add");
	public static By ELEMENT_ADD_TASK_DETAIL_SELECT_MAIL_POPUP = By.id("UICalendarChildPopupWindow");
	public static By ELEMENT_ADD_TASK_DETAIL_REMINDER_POPUP = By.id("popupReminder");
	public static By ELEMENT_ADD_TASK_DETAIL_REMINDER_POPUP_TIME = By.id("popupReminderTime");
	public static By ELEMENT_ADD_TASK_DETAIL_REMINDER_POPUP_REPEAT = By.id("popupIsRepeat");
	
	//--------------------Task's menu action----------------------------------------------------------
	public static String ELEMENT_TASK_VIEW_DAY_WEEK = "//*[@class='EventContainer' and contains(text(),'${taskName}')]/..//p";
	public static String ELEMENT_TASK_VIEW_MONTH = "//*[@id='UIMonthView']//*[@class='EventLabel EventPriority' and text()='${taskName}']";
	public static By ELEMENT_TASK_EDIT = By.xpath("//*[@id='tmpMenuElement']//a[@class='ItemIcon EditEventIcon']");
	public static By ELEMENT_TASK_DELETE = By.xpath("//*[@id='tmpMenuElement']//a[@class='ItemIcon DeleteEventIcon']");
	public static By ELEMENT_TASK_EXPORT = By.xpath("//*[@id='tmpMenuElement']//a[@class='ItemIcon ExportCalendarIcon']");
	public static By ELEMENT_TASK_VIEW = By.xpath("//*[@id='tmpMenuElement']//a[@class='ItemIcon ViewEventIcon']");
	public static String ELEMENT_CONTAINER_TASK_DESTINATION = "//*[@startfull='${dateTime}']";
	public static String ELEMENT_CONTAINER_TASK_NEW = "//*[@startfull='${dateTime}']//*[@class='EventContainer' and contains(text(),'${taskName}')]/..//p";
	
	//----------------------View task-----------------------------------------------------------------
	public static By ELEMENT_TASK_QUICK_VIEW_POPUP = By.xpath("//*[@class='BgMCEvent']");
	public static By ELEMENT_TASK_VIEW_TIME = By.xpath("//*[@class='BgMCEvent']/div[@class='Time']");
	public static String ELEMENT_TASK_VIEW_TITLE = "//*[@class='BgMCEvent']/div[@class='Title' and text()='${taskName}']";	
	public static By ELEMENT_TASK_VIEW_STATUS = By.xpath("//*[@class='BgMCEvent']/div[@class='Status']");
	public static By ELEMENT_TASK_VIEW_DETAIL_POPUP = By.id("UICalendarPopupWindow");
	public static String ELEMENT_TASK_VIEW_DETAIL_NAME = "//*[@class='TaskDescription' and text()='${taskName}']";
	public static By ELEMENT_TASK_VIEW_CLOSE_POPUP = By.xpath("//*[@id='UICalendarPopupWindow']//a[@title='Close Window']");
	
	//--------------------------------------------common function-------------------------------------------------
	/**function go to add task from a calendar
	 * @author lientm
	 * @param calendarName: name of calendar
	 * @param calType: type of calendar: = 0: with personal calendar
	 * 									1: with share calendar
	 * 									2: with group calendar
	 */
	public static void goToTaskFromCalendar(String calendarName, String calType){
		String id = getIDOfCalendar(calendarName);
		String color = getColorOfCalendar(calendarName);
		info("Go to add task for calendar " + id + ", it has color " + color);
		String script = "javascript:eXo.calendar.UICalendarPortlet.addQuickShowHiddenWithId(this,%202,%20'objectId=" + id +
				"&calType=" + calType + "&calColor=" + color + "&categoryId=defaultEventCategoryIdAll');";
		((JavascriptExecutor) driver).executeScript(script);
		waitForElementPresent(ELEMENT_QUICK_ADD_TASK_POPUP);
	}
	
	/**function input data in add new task form
	 * @author lientm
	 * @param name: name of task
	 * @param note: note of task
	 * @param allDay: = true: full day
	 *                = false: not full day
	 * @param from: format mm/dd/yyyy hh:mm
	 * @param to: format mm/dd/yyyy hh:mm
	 * @param opt[0]: calendar name
	 * 		  opt[1]: category name
	 */
	public static void inputDataTask(String name, String note, boolean allDay, String from, String to,  String...opt){
		if (name != null){
			type(ELEMENT_TASK_NAME, name, true);
		}
		if (note != null){
			type(ELEMENT_TASK_NOTE, note, true);
		}
		WebElement day = waitForAndGetElement(ELEMENT_TASK_ALL_DAY);
		if (allDay && !day.isSelected()){
			check(ELEMENT_TASK_ALL_DAY);
		} else if (!allDay && day.isSelected()){
			uncheck(ELEMENT_TASK_ALL_DAY);
		}
		if (from != null){
			if (from != ""){
				if (allDay){
					type(ELEMENT_TASK_FROM_DATE, from, true);
				}else {
					String[] dateTime = from.split(" ");
					type(ELEMENT_TASK_FROM_DATE, dateTime[0], true);
					type(ELEMENT_TASK_FROM_TIME, dateTime[1], true);
				}
			} else {
				type(ELEMENT_TASK_FROM_DATE, from, true);
				type(ELEMENT_TASK_FROM_TIME, from, true);
			}
		}
		if (to != null){
			if (to != ""){
				if (allDay){
					type(ELEMENT_TASK_TO_DATE, to, true);
				}else{
					String[] dateTime = to.split(" ");
					type(ELEMENT_TASK_TO_DATE, dateTime[0], true);
					type(ELEMENT_TASK_TO_TIME, dateTime[1], true);
				}
			} else {
				type(ELEMENT_TASK_TO_DATE, to, true);
				type(ELEMENT_TASK_TO_TIME, to, true);
			}
		}
		if (opt.length > 0){
			select(ELEMENT_TASK_CALENDAR, opt[0]);
		}
		if (opt.length > 1){
			select(ELEMENT_TASK_CATEGORY, opt[1]);
		}
	}
	
	/**function add new task form menu -> task
	 * @author lientm
	 */
	public static void addTaskFromMenu(String name, String note, boolean allDay, String from, String to,  String...opt){
		info("Add new task with name: " + name + "from Menu -> Task");
		goToTask();
		inputDataTask(name, note, allDay, from, to, opt);
		save();
		if (opt.length < 3){
			waitForElementNotPresent(ELEMENT_QUICK_ADD_TASK_POPUP);
		}
	}
	
	/**function quick add new task
	 * @author lientm
	 * @param name: name of task
	 * @param calendarName: name of calendar
	 */
	public static void quickAddTask(String name, String calendarName){
		addTaskFromMenu(name, null, false, null, null, calendarName);
	}
	
	/**function add new task from calendar -> add task
	 * @author lientm
	 */
	public static void addTaskFromCalendar(String calendarName, String calType, String name, String note, String from, String to, boolean allDay, String...opt){
		goToTaskFromCalendar(calendarName, calType);
		inputDataTask(name, note, allDay, from, to, opt);
		save();
		if (opt.length < 3){
			waitForElementNotPresent(ELEMENT_QUICK_ADD_TASK_POPUP);
		}
	}
	
	/**function go to add detail task (from Menu -> Task -> more detail)
	 * @author lientm
	 */
	public static void goToAddDetailTask(){
		info("Go to add Detail task");
		goToTask();
		click(ELEMENT_TASK_MORE_DETAIL_BUTTON);
		waitForElementPresent(ELEMENT_ADD_TASK_DETAIL_POPUP);
	}
	
	/**function select user delegation when add detail task
	 * @author lientm
	 * @param user: user name
	 * @param type: way to search use
	 */
	public static void selectUserDelegation(String user, int... type){
		click(ELEMENT_ADD_TASK_DETAIL_SELECT_USER);
		waitForTextPresent("Select Users");
		selectUserPermission(user, type);
	}
	
	/** function: Attach file in attach popup
	 * @author lientm
	 * @param number: number of upload container that need upload file
	 * @param filePath: path to file upload
	 */
	public static void attachAFile(String number, String filePath){		
		By element_frame = By.xpath(ELEMENT_ADD_TASK_DETAIL_ATTACH_FRAME.replace("${No}", number));
		
		if (filePath != ""){
			driver.switchTo().frame(waitForAndGetElement(element_frame));
			info("Upload file " + getAbsoluteFilePath(filePath));
			type(ELEMENT_ADD_TASK_DETAIL_ATTACH_FILE, getAbsoluteFilePath(filePath), false);
			switchToParentWindow();
			String links[] = filePath.split("/");
			int length = links.length;
			By element_finish = By.xpath(ELEMENT_ADD_TASK_DETAIL_ATTACH_FINISH.replace("${No}", number).replace("${fileName}", links[length-1]));
			waitForElementPresent(element_finish);
			info("Upload file " + filePath + "successfully");
		}
	}

	/** function: attach some file in attach popup
	 * @author lientm
	 * @param file: file path
	 */
	public static void attachSomeFiles(String...file){
		if (file.length > 0){
			click(ELEMENT_ADD_TASK_DETAIL_ATTACH);
			waitForTextPresent("Attach files");
			for (int i = 0; i < file.length; i ++ ){				
				attachAFile(Integer.toString(i + 1), file[i]);
			}
			save();
		}	
	}
	
	/**function input data in detail tab in add detail task form
	 * @author lientm
	 * @param name: name of task
	 * @param note: note of task
	 * @param user: use delegation
	 * @param allDay: = true: full day
	 *                = false: not full day
	 * @param from: format mm/dd/yyyy hh:mm
	 * @param to: format mm/dd/yyyy hh:mm
	 * @param opt[0]: choose priority
	 *        opt[1]: choose calendar
	 *        opt[2]: choose category
	 *        opt[3]: choose status
	 * @param file: file attach
	 */
	public static void inputDataDetailTask_DetailTab(String name, String note, String user, boolean allDay, String from
			, String to, String[] opt, String... file){
		if (name != null){
			type(ELEMENT_ADD_TASK_DETAIL_NAME, name, true);
		}
		if (note != null){
			type(ELEMENT_ADD_TASK_DETAIL_NOTE, note, true);
		}
		if (user!= null && user != ""){
			selectUserDelegation(user);
		}
		WebElement day = waitForAndGetElement(ELEMENT_ADD_TASK_DETAIL_ALL_DAY);
		if (allDay && !day.isSelected()){
			check(ELEMENT_ADD_TASK_DETAIL_ALL_DAY);
		} else if (!allDay && day.isSelected()){
			uncheck(ELEMENT_ADD_TASK_DETAIL_ALL_DAY);
		}
		if (from != null){
			if (from != ""){
				if (allDay){
					type(ELEMENT_ADD_TASK_DETAIL_FROM_DATE, from, true);
				}else{
					String[] dateTime = from.split(" ");
					type(ELEMENT_ADD_TASK_DETAIL_FROM_DATE, dateTime[0], true);
					type(ELEMENT_ADD_TASK_DETAIL_FROM_TIME, dateTime[1], true);
				}
			} else {
				type(ELEMENT_ADD_TASK_DETAIL_FROM_DATE, from, true);
				type(ELEMENT_ADD_TASK_DETAIL_FROM_TIME, from, true);
			}
		}
		if (to != null){
			if (to != ""){
				if (allDay){
					type(ELEMENT_ADD_TASK_DETAIL_TO_DATE, to, true);
				}else{
					String[] dateTime = to.split(" ");
					type(ELEMENT_ADD_TASK_DETAIL_TO_DATE, dateTime[0], true);
					type(ELEMENT_ADD_TASK_DETAIL_TO_TIME, dateTime[1], true);
				}
			} else {
				type(ELEMENT_ADD_TASK_DETAIL_TO_DATE, to, true);
				type(ELEMENT_ADD_TASK_DETAIL_TO_TIME, to, true);
			}
		}
		if (opt[0] != null && opt[0] != ""){
			select(ELEMENT_ADD_TASK_DETAIL_PRIORITY, opt[0]);
		}
		if (opt[1] != null && opt[1] != ""){
			select(ELEMENT_ADD_TASK_DETAIL_CALENDAR, opt[1]);
		}
		if (opt[2] != null && opt[2] != ""){
			select(ELEMENT_ADD_TASK_DETAIL_CATEGORY, opt[2]);
		}
		if (opt[3] != null && opt[3] != ""){
			select(ELEMENT_ADD_TASK_DETAIL_STATUS, opt[2]);
		}
		attachSomeFiles(file);
	}
	
	/**function select mail in reminder tab when add detail task
	 * @author lientm
	 * @param user (eg: demo/mary)
	 */
	public static void selectMailReminder(String user){
		if (user != null && user != ""){
			click(ELEMENT_ADD_TASK_DETAIL_MAIL_REMINDER_SELECT_USER);
			waitForElementPresent(ELEMENT_ADD_TASK_DETAIL_SELECT_MAIL_POPUP);
			String[] userName = user.split("/");
			for (int i = 0; i < userName.length; i ++){
				WebElement checkUser = waitForAndGetElement(By.id(userName[i]), 5000, 0);
				if (checkUser == null){
					type(ELEMENT_ADD_TASK_DETAIL_USER_SEARCH, userName[i], true);
					click(ELEMENT_ADD_TASK_DETAIL_USER_SEARCH_BUTTON);
				}
				check(By.id(userName[i]));
			}
			click(ELEMENT_ADD_TASK_DETAIL_USER_ADD_BUTTON);
			waitForElementNotPresent(ELEMENT_ADD_TASK_DETAIL_SELECT_MAIL_POPUP);
		}	
	}
	
	/**function input data in reminder tab in add detail task form
	 * @author lientm
	 * @param byMail: choose send notification by email
	 * @param popup: choose show notification by popup
	 * @param repeat: choose repeat reminder or not
	 * @param opt[0]: time send email
	 * 		  opt[1]: user to send mail
	 *        opt[2]: time open popup
	 */
	public static void inputDataDetailTask_ReminderTab(boolean byMail, boolean popup, boolean repeat, String...opt){
		click(ELEMENT_ADD_TASK_DETAIL_REMAIDER_TAB);
		WebElement mail = waitForAndGetElement(ELEMENT_ADD_TASK_DETAIL_SEND_EMAIL);
		if (byMail && !mail.isSelected()){
			check(ELEMENT_ADD_TASK_DETAIL_SEND_EMAIL);
			if (opt[0] != ""){
				select(ELEMENT_ADD_TASK_DETAIL_MAIL_REMINDER_TIME, opt[0]);
			}
			if (repeat){
				check(ELEMENT_ADD_TASK_DETAIL_MAIL_REMINDER_REPEAT);
			}
			selectMailReminder(opt[1]);		
		} else if (!byMail && mail.isSelected()){
			uncheck(ELEMENT_ADD_TASK_DETAIL_SEND_EMAIL);
		}
		WebElement re_popup = waitForAndGetElement(ELEMENT_ADD_TASK_DETAIL_REMINDER_POPUP);
		if (popup && !re_popup.isSelected()){
			check(ELEMENT_ADD_TASK_DETAIL_REMINDER_POPUP);
			if (opt[2] != ""){
				select(ELEMENT_ADD_TASK_DETAIL_REMINDER_POPUP_TIME, opt[2]);
			}
			if (repeat){
				check(ELEMENT_ADD_TASK_DETAIL_REMINDER_POPUP_REPEAT);
			}
		} else if (!popup && re_popup.isSelected()){
			uncheck(ELEMENT_ADD_TASK_DETAIL_REMINDER_POPUP);
		}
	}
	
	/**function: add new detail task
	 * @author lientm
	 * @param name: name of task
	 * @param note: note of task
	 * @param user: use delegation
	 * @param allDay: = true: full day
	 *                = false: not full day
	 * @param from: format mm/dd/yyyy hh:mm
	 * @param to: format mm/dd/yyyy hh:mm
	 * @param opt[0]: choose priority
	 *        opt[1]: choose calendar
	 *        opt[2]: choose category
	 *        opt[3]: choose status
	 * @param byMail: choose send notification by email
	 * @param popup: choose show notification by popup
	 * @param repeat: choose repeat reminder or not
	 * @param email: email to send reminder
	 * @param file
	 */
	public static void addDetailTask(String name, String note, String user, boolean allDay, String from
			, String to, String[] opt, boolean byMail, boolean popup, boolean repeat, String email, String... file){
		goToAddDetailTask();
		info("Add new detail task with name: " + name);
		inputDataDetailTask_DetailTab(name, note, user, allDay, from, to, opt, file);
		inputDataDetailTask_ReminderTab(byMail, popup, repeat, "", email, "");
		save();
		waitForElementNotPresent(ELEMENT_ADD_TASK_DETAIL_POPUP);
		info("Add a detail task successfully");
	}
	
	/**function quick add a detail task
	 * @author lientm
	 * @param name: name of task
	 * @param calendarName: choose calendar
	 */
	public static void quickAddDetailTask(String name, String calendarName){
		String[] opt = {null, calendarName, null, null};
		goToAddDetailTask();
		inputDataDetailTask_DetailTab(name, null, "", false, null, null, opt);
		save();
		waitForElementNotPresent(ELEMENT_ADD_TASK_DETAIL_POPUP);
	}
	
	/**function get object (task/event) follow view mode
	 * @author lientm
	 * @param taskName: name of task/event
	 * @param mode: = 1: day/week view
	 *              !=1: Month view
	 * @return By object is task/event on view mode screen
	 */
	public static By getTaskFromViewMode(String taskName, int...mode){
		By task;
		if (mode.length == 0){
			task = By.xpath(ELEMENT_TASK_VIEW_DAY_WEEK.replace("${taskName}", taskName));
		}else{
			if (mode[0] == 1){
				task = By.xpath(ELEMENT_TASK_VIEW_DAY_WEEK.replace("${taskName}", taskName));
			}else {
				task = By.xpath(ELEMENT_TASK_VIEW_MONTH.replace("${taskName}", taskName));
			}		
		}
		return task;
	}
	
	/**function go to edit task
	 * @author lientm
	 * @param taskName: task name
	 * @param mode: view mode
	 */
	public static void goToEditTask(String taskName, int...mode){
		By task = getTaskFromViewMode(taskName, mode);
		info("Go to edit task");
		for (int i = 0; i < 5; i++){
			rightClickOnElement(task);
			WebElement edit = waitForAndGetElement(ELEMENT_TASK_EDIT, 5000, 0);
			if (edit != null){
				click(ELEMENT_TASK_EDIT);
				waitForElementPresent(ELEMENT_ADD_TASK_DETAIL_POPUP);
				break;
			}
		}	
	}
	
	/**function edit a task
	 * @author lientm
	 * @param name: name of task
	 * @param note: note of task
	 * @param user: use delegation
	 * @param allDay: = true: full day
	 *                = false: not full day
	 * @param from: format mm/dd/yyyy hh:mm
	 * @param to: format mm/dd/yyyy hh:mm
	 * @param opt[0]: choose priority
	 *        opt[1]: choose calendar
	 *        opt[2]: choose category
	 *        opt[3]: choose status
	 * @param repeat: choose repeat reminder or not
	 * @param email: email to send reminder
	 * @param file: file attach
	 */
	public static void editTask(String name, String note, String user, boolean allDay, String from
			, String to, String[] opt, boolean byMail, boolean popup, boolean repeat, String email, String... file){
		info("Edit task");
		inputDataDetailTask_DetailTab(name, note, user, allDay, from, to, opt, file);
		inputDataDetailTask_ReminderTab(byMail, popup, repeat, "", email, "");
		save();
		waitForElementNotPresent(ELEMENT_ADD_TASK_DETAIL_POPUP);
	}
	
	/**function delete a task
	 * @author lientm
	 * @param taskName: name of task
	 * @param mode: view mode
	 */
	public static void deleteTask(String taskName, int...mode){
		By task = getTaskFromViewMode(taskName, mode);

		for (int i = 0; i < 5; i++){
			rightClickOnElement(task);
			WebElement delete = waitForAndGetElement(ELEMENT_TASK_DELETE, 5000, 0);
			if (delete != null){
				click(ELEMENT_TASK_DELETE);
				break;
			}
		}
		acceptAlert();
		waitForElementNotPresent(task);
	}
	
	/**function drag drop a task
	 * @author lientm
	 * @param taskName: name of task
	 * @param dateTime: new date and time of vew position (format: Wed Jan 16 2013 00:00:00)
	 */
	public static void dragDropTask_WeekView(String taskName, String dateTime){
		By task = By.xpath(ELEMENT_TASK_VIEW_DAY_WEEK.replace("${taskName}", taskName));
		By element_destination = By.xpath(ELEMENT_CONTAINER_TASK_DESTINATION.replace("${dateTime}", dateTime));
		By task_new = By.xpath(ELEMENT_CONTAINER_TASK_NEW.replace("${taskName}", taskName).replace("${dateTime}", dateTime));
		
		info("Drag drop task to " + dateTime);
		dragAndDropToObject(task, element_destination);
		waitForElementPresent(task_new);
		info("Move task successfully");
	}
	
	/**function export task
	 * @author lientm
	 * @param taskName: name of task
	 * @param fileName: file name export
	 * @param mode: view mode
	 */
	public static void exportTask(String taskName, String fileName, int...mode){
		By task = getTaskFromViewMode(taskName, mode);

		for (int i = 0; i < 5; i++){
			rightClickOnElement(task);
			WebElement export = waitForAndGetElement(ELEMENT_TASK_EXPORT, 5000, 0);
			if (export != null){
				click(ELEMENT_TASK_EXPORT);
				break;
			}
		}
		exportItem(fileName);
	}
	
	/**function view a task by mouse over its
	 * @author lientm
	 * @param taskName: name of task
	 * @param mode: view mode
	 */
	public static void viewTaskByMouseOver(String taskName, int...mode){
		By task = getTaskFromViewMode(taskName, mode);
		By task_title = By.xpath(ELEMENT_TASK_VIEW_TITLE.replace("${taskName}", taskName));
		
		mouseOver(task, true);
		waitForElementPresent(ELEMENT_TASK_QUICK_VIEW_POPUP);
		waitForElementPresent(ELEMENT_TASK_VIEW_TIME);
		waitForElementPresent(task_title);
		waitForElementPresent(ELEMENT_TASK_VIEW_STATUS);
	}
	
	/**function view a task by right click -> view
	 * @author lientm
	 * @param taskName: name of task
	 * @param mode: view mode
	 */
	public static void viewTaskByRightClick(String taskName, int...mode){
		By task = getTaskFromViewMode(taskName, mode);
		By task_name = By.xpath(ELEMENT_TASK_VIEW_DETAIL_NAME.replace("${taskName}", taskName));
		
		for (int i = 0; i < 5; i++){
			rightClickOnElement(task);
			WebElement delete = waitForAndGetElement(ELEMENT_TASK_VIEW, 5000, 0);
			if (delete != null){
				click(ELEMENT_TASK_VIEW);
				break;
			}
		}
		waitForElementPresent(ELEMENT_TASK_VIEW_DETAIL_POPUP);
		waitForElementPresent(task_name);
		click(ELEMENT_TASK_VIEW_CLOSE_POPUP);
		waitForElementNotPresent(ELEMENT_TASK_VIEW_DETAIL_POPUP);
	}
}
