package org.exoplatform.selenium.platform.cs;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.By;

public class EventCategory extends CsBase {
	public static By ELEMENT_EVENT_CATEGORIES_POPUP = By.xpath("//*[@id='UICalendarPopupWindow']//span[text()='Event Categories']");
	public static By ELEMENT_EVENT_CATEGORIES_NAME_TEXTBOX = By.id("eventCategoryName");
	public static By ELEMENT_EVENT_CATEGORIES_DESCTIPTION_TEXTAREA = By.xpath("//*[@id='UIEventCategoryForm']//*[@id='description']");
	public static String ELEMENT_EVENT_CATEGORIES_DELETE_ICON = "//*[text()='${eventCatName}']/../..//img[@class='DeleteIcon']";
	public static String ELEMENT_EVENT_CATEGORIES_IN_LIST = "//*[@id='UIEventCategoryList']//div[text()='${event}']";
	
	public static void putDataEventCategory(String eventCatName, String desc){
		if (eventCatName != null){
			type(ELEMENT_EVENT_CATEGORIES_NAME_TEXTBOX, eventCatName, true);
		}
		if (desc != null){
			type(ELEMENT_EVENT_CATEGORIES_DESCTIPTION_TEXTAREA, desc, true);
		}
		save();
		waitForElementPresent(By.xpath(ELEMENT_EVENT_CATEGORIES_IN_LIST.replace("${event}", eventCatName)));
	}
	
	public static void addEventCategory(String eventCatName, String desc){
		goToAddEventCategoryByHeaderBar();
		info("Add an event category");
		waitForElementPresent(ELEMENT_EVENT_CATEGORIES_POPUP);
		putDataEventCategory(eventCatName, desc);
		close();
		waitForElementNotPresent(ELEMENT_EVENT_CATEGORIES_POPUP);
	}
	
	public static void addSomeEventCategories(String eventCatName){
		String[] event = eventCatName.split("/");
		if (event.length > 0){
			goToAddEventCategoryByHeaderBar();
			waitForElementPresent(ELEMENT_EVENT_CATEGORIES_POPUP);
			for (int i = 0; i < event.length; i++){
				info("Add event category with name " + event[i]);
				putDataEventCategory(event[i], null);
			}
			close();
			waitForElementNotPresent(ELEMENT_EVENT_CATEGORIES_POPUP);
		}
	}
	
	public static void deleteEventCategory(String eventCatName){
		String[] event = eventCatName.split("/");
		
		goToAddEventCategoryByHeaderBar();
		info("Delete an event category");
		waitForElementPresent(ELEMENT_EVENT_CATEGORIES_POPUP);
		if (event.length > 0){
			for (int i = 0; i < event.length; i++){
				click(ELEMENT_EVENT_CATEGORIES_DELETE_ICON.replace("${eventCatName}", event[i]));
				acceptAlert();
				waitForElementNotPresent(ELEMENT_EVENT_CATEGORIES_DELETE_ICON.replace("${eventCatName}", event[i]));
			}
		}
		close();
		waitForElementNotPresent(ELEMENT_EVENT_CATEGORIES_POPUP);
	}
	
}
