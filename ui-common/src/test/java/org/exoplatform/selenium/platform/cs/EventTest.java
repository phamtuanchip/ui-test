package org.exoplatform.selenium.platform.cs;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;
import org.exoplatform.selenium.platform.cs.Event.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author vuna2
 *
 */
public class EventTest extends Event{
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		actions = new Actions(driver);
		signIn(DATA_USER, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() {
		info("-- Logout --");
		signOut();
		driver.quit();
		actions = null;
	}

	@Test
	public void test00_EventTest(){
		String[] eventInformation = {"test event", "test event descriptions"};
		boolean setTime = true;
		String[] time = {"2", "10:00", "3", "17:00"};
		boolean allDay = false;
		String[] typeCalendarAndEventCategory = {"Default", "Meeting"};

		String location = "8th floor";
		String priority = "High";
		boolean repeat = true;
		String[] typeRepeat = {"Weekly", "5", "After"};
		String[] occurrencesAndDate = {"10"}; 
		boolean attachFile = false;

		goToCalendarPage();

		goToEvent();

		//quickAddEvent(typeAddEvent.QUICKLY, eventInformation, setTime, time, allDay, typeCalendarAndEventCategory);
		//quickAddEvent(typeAddEvent.QUICKLY, eventInformation, false, time, true, typeCalendarAndEventCategory);
		//click(ELEMENT_MORE_DETAILS_BUTTON);	
		//quickAddEvent(typeAddEvent.DETAIL, eventInformation, setTime, time, allDay, typeCalendarAndEventCategory);

		addEventInMoreDetails(typeAddEvent.DETAIL, eventInformation, setTime, time, allDay, typeCalendarAndEventCategory, 
				location, priority, repeat, typeRepeat, occurrencesAndDate);

		pause(2000);
	}

	@Test
	public void test01_EventTest(){
		String[] eventInformation = {"testSummary", "testDescription"}; 
		boolean setTime = true; 
		String[] time = {"2", "10:00", "3", "17:00"}; 
		boolean allDay = false;
		String[] typeCalendarAndEventCategory = {"Default", "Meeting"};
		String typeCalendar = "Default"; 
		String typeEventCategory = "Meeting";	

		String location = "";
		boolean repeat = true;
		boolean attachFile = true;
		String pathFile = "TestData/Winter.jpg";
		String path = getAbsoluteFilePath(pathFile);

		goToCalendarPage();

		pause(1000);

	}

	@Test
	public void test02_EventTest(){
		boolean remindByMail = true;
		String reminderTime = "10 minutes"; 
		boolean remindRepeat = true;
		String reminderRepeatTime = "10 minutes";
		boolean addMorePeople = true;
		String[] userInfo = {"Mary", "mary"};
		boolean showNotification = true;
		boolean repeatPopUpNotification = true;
		String[] popupReminderTime = {"10 minutes", "20 minutes"};
		goToCalendarPage();
		goToRemindersTab();
		eventReminders(remindByMail, reminderTime, 
				remindRepeat, reminderRepeatTime,
				addMorePeople, userInfo,
				showNotification, repeatPopUpNotification, popupReminderTime);
		pause(3000);
	}

	@Test
	public void test03_EventTest(){
		boolean addMorePeople = true;
		String[] userInfo = {"Mary", "mary"};
		String inviteMessage = "Test Exo Platform";

		boolean applySelectedDate = true;
		String[] time = {"02:30", "03:00"};
		boolean allDay = false;

		goToCalendarPage();
		//goToParticipantsTab();
		//eventParticipants(participantPrivacy.PUBLIC, participantAvailable.AVAILABLE, participantInviteType.ALWAYS, addMorePeople, userInfo, inviteMessage);
		goToScheduleTab();
		eventSchedule(false, time, true, addMorePeople, userInfo[1]);
	}

}
