package org.exoplatform.selenium.platform.calendar;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @Date 10 Oct 2013
 * @author thuntn
 *
 */
public class Task extends CalendarBase{


	//------------------------Task Menu-------------------------------------------------------
	public String ELEMENT_TASK_MENU_DELETE = "//*[@id='tmpMenuElement']//i[@class='uiIconDelete uiIconLightGray']";
	public String ELEMENT_TASK_MENU_EDIT = "//*[@id='tmpMenuElement']//i[@class='uiIconEdit uiIconLightGray']";

	//Add quick task
	public By ELEMENT_QUICK_ADD_TASK_POPUP = By.xpath("//span[@class='PopupTitle popupTitle' and text()='Quick Add Task']");
	public By ELEMENT_INPUT_TASK_TITLE = By.xpath("//*[@id='UIQuickAddTask']//*[@name='eventName']");
	public By ELEMENT_INPUT_TASK_NOTE = By.xpath("//*[@id='UIQuickAddTask']//*[@id='description']");
	public By ELEMENT_CHECKBOX_TASK_ALLDAY = By.xpath("//*[@id='UIQuickAddTask']//*[@name='allDay']");
	public By ELEMENT_INPUT_TASK_FROM = By.xpath("//*[@id='UIQuickAddTask']//*[@name='from']");
	public By ELEMENT_INPUT_TASK_TO = By.xpath("//*[@id='UIQuickAddTask']//*[@name='to']");
	public By ELEMENT_INPUT_TASK_FROM_TIME = By.xpath("//input[@id='fromTime' and @style='visibility: visible;']");
	public By ELEMENT_INPUT_TASK_TO_TIME = By.xpath("//input[@id='fromTime' and @style='visibility: visible;']");
	public By ELEMENT_INPUT_TASK_CALENDAR = By.xpath("//*[@id='UIQuickAddTask']//*[@name='calendar']");
	public By ELEMENT_INPUT_TASK_CATEGORY = By.xpath("//*[@id='UIQuickAddTask']//*[@name='category']");
	public By ELEMENT_BUTTON_TASK = By.id("UIActionBarQuickAddTask");
	public By ELEMENT_BUTTON_TASK_SAVE = By.xpath("//*[@id='UIQuickAddTaskPopupWindow']//*[text()='Save']");
	public By ELEMENT_BUTTON_TASK_MORE_DETAILS = By.xpath("//*[@id='QuickAddEventContainer']//*[text()='More Details']");

	//Task Form (details)
	public By ELEMENT_ADD_EDIT_TASK_POPUP = By.xpath("//span[@class='PopupTitle popupTitle' and text()='Add/Edit Tasks']");
	public By ELEMENT_BUTTON_TASK_SAVE_EDIT = By.xpath("//*[@id='UITaskForm']//*[text()='Save']");
	public By ELEMENT_ADD_EDIT_TASK_TITLE = By.xpath("//*[@id='UITaskForm']//*[@name='eventName']");
	public By ELEMENT_ADD_EDIT_TASK_NOTE = By.xpath("//*[@id='UITaskForm']//*[@id='description']");
	public By ELEMENT_ADD_EDIT_TASK_ALLDAY = By.xpath("//*[@id='UITaskForm']//*[@name='allDay']");
	public By ELEMENT_ADD_EDIT_TASK_FROM = By.xpath("//*[@id='UITaskForm']//*[@name='from']");
	public By ELEMENT_ADD_EDIT_TASK_TO = By.xpath("//*[@id='UITaskForm']//*[@name='to']");
	public By ELEMENT_ADD_EDIT_TASK_FROM_TIME = By.xpath("//*[@id='UITaskForm'//input[@id='fromTime' and @style='visibility: visible;']");
	public By ELEMENT_ADD_EDIT_TASK_TO_TIME = By.xpath("//*[@id='UITaskForm'//input[@id='fromTime' and @style='visibility: visible;']");
	public By ELEMENT_ADD_EDIT_TASK_CALENDAR = By.xpath("//*[@id='UITaskForm'//*[@name='calendar']");
	public By ELEMENT_ADD_EDIT_TASK_CATEGORY = By.xpath("//*[@id='UITaskForm'//*[@name='category']");
	public By ELEMENT_TASK_ADD_ATTACHMENT = By.xpath("//button[contains(@onclick,'AddAttachment')]");
	public By ELEMENT_TASK_FILE_INPUT = By.xpath("//*[@id='upload']//*[@name='file']");
	public By ELEMENT_TASK_STATUS = By.name("status");
	public By ELEMENT_ATTACH_SAVE_BUTTON = By.xpath("//form[@id='UIAttachFileForm']//*[text()='Save']");

	//----------------------- Task Reminder ---------------------------------------------
	public By ELEMENT_TAB_REMINDER = By.xpath("//*[@id='UIPopupAddTaskContainer']//a[text()='Reminders']");
	public By ELEMENT_CHECKBOX_POPUP_REMINDER = By.id("popupReminder");
	public By ELEMENT_CHECKBOX_EMAIL_REMINDER = By.id("mailReminder");
	public By ELEMENT_CHECKBOX_EMAIL_REMINDER_REPEAT = By.id("emailIsRepeat");
	public By ELEMENT_CHECKBOX_POPUP_REMINDER_REPEAT = By.id("popupIsRepeat");
	public By ELEMENT_BUTTON_REMINDER_ADD_MORE_EMAIL = By.xpath("//*[@id='eventReminder']//i[@class='uiIconPlus uiIconLightGray']");

	//Task preview
	public String ELEMENT_TASK_PREVIEW_TITLE = "//*[@id='UIPreviewPopup']//div[@class='titleList']/*[text()='${task}']";

	//----------------------------------DateTime----------------------------------------
	public int HAFL_HOUR = 30; //minutes
	public int FULL_HOUR = 60; //minutes

	public Task(WebDriver dr){
		driver = dr;
		button = new Button(driver);
	}

	/*============== Go to Task =============*/
	/**
	 * Open form Add a task
	 * @author thuntn
	 */
	public void goToAddTask(){
		info("Go to Add Task page"); 
		click(ELEMENT_BUTTON_TASK);
	}

	/**
	 * Open Edit task form
	 * @param oldName: old name of task
	 */
	public void goToEditTaskForm(String oldName){
		info("Open Edit Task form");
		if(waitForAndGetElement(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", oldName),15000,0) != null)
			rightClickOnElement(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", oldName),2);
		else
			rightClickOnElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}", oldName),2);
		Utils.pause(3000);
		click(ELEMENT_TASK_MENU_EDIT);
		waitForAndGetElement(ELEMENT_ADD_EDIT_TASK_POPUP);
	}

	/**
	 * Setting Pop-up reminder for task on Quick Add Task form
	 * @author havtt
	 */
	public void gotoSetPopupReminder() {

		click(ELEMENT_BUTTON_TASK_MORE_DETAILS);
		Utils.pause(3000);
		click(ELEMENT_TAB_REMINDER);
		Utils.pause(3000);
		click(ELEMENT_CHECKBOX_POPUP_REMINDER);
	}

	/**
	 * Setting E-mail reminder for task on Quick Add Task form
	 * @author havtt
	 */
	public void gotoSetEmailReminder() {
		click(ELEMENT_BUTTON_TASK_MORE_DETAILS);
		Utils.pause(5000);
		click(ELEMENT_TAB_REMINDER);
	}	
	/*==============End of =================*/

	
	/*==========Input data form =========*/
	/**Input into basic fields of Quick task form and tab details of Add task form
	 * 
	 * @param name: name of a task
	 * @param note: note of a task 
	 * @param opt: opt[0]: calendar
	 * opt[1]: category
	 */
	public void inputBasicQuickTask(String name, String note, String...opt){
		boolean quick = (waitForAndGetElement(ELEMENT_QUICK_ADD_TASK_POPUP,5000,0) != null) ? true : false;
		if(quick){
			if (name != null){
				type(ELEMENT_INPUT_TASK_TITLE, name, true);
			}
			if (note != null){
				type(ELEMENT_INPUT_TASK_NOTE, note, true);
			}

			if (opt.length > 0 && opt[0] != null){
				select(ELEMENT_INPUT_TASK_CALENDAR, opt[0]);
			}
			if (opt.length > 1 && opt[1] != null){
				select(ELEMENT_INPUT_TASK_CATEGORY, opt[1]);
			}
		}else{
			if (name != null){
				type(ELEMENT_ADD_EDIT_TASK_TITLE, name, true);
			}
			if (note != null){
				type(ELEMENT_ADD_EDIT_TASK_NOTE, note, true);
			}

			if (opt.length > 0 && opt[0] != null){
				select(ELEMENT_ADD_EDIT_TASK_CALENDAR, opt[0]);
			}
			if (opt.length > 1 && opt[1] != null){
				select(ELEMENT_ADD_EDIT_TASK_CATEGORY, opt[1]);
			}

		}
	}

	/**
	 * Input into From, To fields of a task
	 * @param from: from date, time, eg: 11/06/2013 14:00
	 * @param to: To date, time fields, eg: 11/06/2013 14:00
	 * @param allDay: all day field of task
	 */
	public void inputFromToTask(String from, String to, boolean allDay){
		boolean quick = (waitForAndGetElement(ELEMENT_QUICK_ADD_TASK_POPUP,5000,0) != null) ? true : false; 
		if(quick){
			if(allDay){
				check(ELEMENT_CHECKBOX_TASK_ALLDAY,2);
				if ((from != null) & (from != ""))
					type(ELEMENT_INPUT_TASK_FROM, from, true);
				check(ELEMENT_CHECKBOX_TASK_ALLDAY,2);
				if ((to != null) & (to != ""))
					type(ELEMENT_INPUT_TASK_TO, to, true);

			}else {
				uncheck(ELEMENT_CHECKBOX_TASK_ALLDAY,2);
				if ((from != null) & (from != "")){
					String[] dateTimeFrom = from.split(" ");
					if(dateTimeFrom.length > 0)
						type(ELEMENT_INPUT_TASK_FROM, dateTimeFrom[0], true);
					if(dateTimeFrom.length > 1)
						type(ELEMENT_INPUT_TASK_FROM_TIME, dateTimeFrom[1], false);
				}
				if ((to != null) & (to != "")){
					String[] dateTimeTo = to.split(" ");

					if(dateTimeTo.length > 0)
						type(ELEMENT_INPUT_TASK_TO, dateTimeTo[0], true);
					if(dateTimeTo.length > 1)
						type(ELEMENT_INPUT_TASK_TO_TIME, dateTimeTo[1], false);
				}
			}

		}else{
			if(allDay){
				check(ELEMENT_ADD_EDIT_TASK_ALLDAY,2);
				if ((from != null) & (from != ""))
					type(ELEMENT_ADD_EDIT_TASK_FROM, from, true);

				if ((to != null) & (to != ""))
					type(ELEMENT_ADD_EDIT_TASK_TO, to, true);
			}
			else{
				uncheck(ELEMENT_ADD_EDIT_TASK_ALLDAY,2);
				if ((from != null) & (from != "")){
					String[] dateTime = from.split(" ");
					if(dateTime.length > 0)
						type(ELEMENT_ADD_EDIT_TASK_FROM, dateTime[0], true);
					if(dateTime.length > 1)
						type(ELEMENT_ADD_EDIT_TASK_FROM_TIME, dateTime[1], false);
				}
				if ((to != null) & (to != "")){

					String[] dateTime = to.split(" ");
					type(ELEMENT_ADD_EDIT_TASK_TO, dateTime[0], true);
					if(dateTime.length > 1)
						type(ELEMENT_ADD_EDIT_TASK_TO_TIME, dateTime[1], false);

				}
			}
		}
	}
	
	/**Input into other fields in tab details of a task
	 * 
	 * @param status: Task Status of a task 
	 * 
	 */
	public void inputOtherFieldsTabDetailsTask(String status){
		
		if ((status != null) & (status != "")){
			select(ELEMENT_TASK_STATUS, status);
		}
	}

	/** Input into tab Detail of Add task form
	 * @author thuntn
	 * @param name: name of a task
	 * @param note: note of a task 
	 * @param from: from of a task
	 * @param to: to of a task
	 * @param allDay: all day of a task
	 * @param path: path of attachment of a task
	 * @param opt
	 */
	public void inputDataTabDetailTask(String name, String note, String from, String to, boolean allDay, String path,  String...opt){
		inputBasicQuickTask(name, note, opt);
		inputFromToTask(from, to, allDay);
		if((path != "") & (path != null)){
			attachFileToTask(path);
			click(ELEMENT_ATTACH_SAVE_BUTTON);
		}
		if((opt.length > 2) & (opt.length <= 3))
			inputOtherFieldsTabDetailsTask(opt[2]);
		
	}

	/**Attach file in Add task form
	 * @author thuntn
	 * @param path: path of attachment of a task
	 */
	public void attachFileToTask(String path){

		click(ELEMENT_TASK_ADD_ATTACHMENT);
		WebElement eFile = waitForAndGetElement(ELEMENT_TASK_FILE_INPUT,DEFAULT_TIMEOUT,1,2);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';",eFile);

		eFile.sendKeys(Utils.getAbsoluteFilePath(path));
		String[] links = path.split("/");
		waitForAndGetElement("//*[text()='"+links[links.length-1]+"']");
		switchToParentWindow();

	}
	/*======End of Input data*/

	/*==========Add/Edit/Delete a Task======*/
	/** Quick add task
	 * @author thuntn
	 * @param name
	 * @param note
	 * @param from
	 * @param to
	 * @param allDay
	 * @param opt
	 */
	public void addQuickTask(String name, String note, String from, String to, boolean allDay, String...opt){
		goToAddTask();
		inputBasicQuickTask(name, note, opt);
		inputFromToTask(from, to, allDay);
		click(ELEMENT_BUTTON_TASK_SAVE);

	}
	/**Edit a task by right click (just edit some fields, pls write more)
	 * 
	 * @param oldName: old name of a task
	 * @param name: new name of a task
	 * @param note: new note of a task 
	 * @param from: new from of a task
	 * @param to: new to of a task
	 * @param allDay: new all day of a task
	 * @param path: new path of attachment of a task
	 * @param opt
	 */
	public void editTask(String oldName, String name, String note,String from, String to, boolean allDay, String path, String...opt) {
		info("Edit a task");
		goToEditTaskForm(oldName);
		inputDataTabDetailTask(name, note, from, to, allDay, path, opt);

		click(ELEMENT_BUTTON_TASK_SAVE_EDIT);
	}


	/*================End of ....======================*/



	/**Set time for Pop-up reminder on current date
	 * 
	 * @author havtt
	 * 
	 * @param fromTime Start time of a task
	 */
	public void setTimePopupReminder() {
		int CURRENT_MIN = getMinute();
		if (CURRENT_MIN == HAFL_HOUR) {
			System.out.print("Set time for Pop-up reminder");	
			//TO-DO: need to fix the issue FQA-1352
		}		
	}
}