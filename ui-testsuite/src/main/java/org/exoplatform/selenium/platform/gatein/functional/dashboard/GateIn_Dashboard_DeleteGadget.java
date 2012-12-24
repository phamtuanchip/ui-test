package org.exoplatform.selenium.platform.gatein.functional.dashboard;

import static org.exoplatform.selenium.gatein.DashBoard.*;
import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.goToDashboard;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *@author NhungVT
 *@date: 28/09/2012
 */

public class GateIn_Dashboard_DeleteGadget extends GateInBase 
{
	//Define data
	public String GADGET_NAME = "Calendar";
	public String GADGET_TITLE_DISPLAY = "Calendar";
	public By GADGET_DIRECTORY_LIST = By.xpath("//div[@class='UIPopupWindow UIDragObject NormalStyle']");
	public By AGENDA_GADGET_ON_LIST = By.id("Gadgets/Calendar");
			//By.xpath("//div[@class='GadgetTitle' and @title='"+GADGET_NAME+"']");

	public void deleteGadgetOnDashboard(String gadgetTitleDisplay)
	{
		String action = "Delete Gadget";
		By deleteGadgetIcon = By.xpath("//span[text()='"+gadgetTitleDisplay+"']/preceding::span[@title='"+action+"']");
		waitForAndGetElement(deleteGadgetIcon);
		click(deleteGadgetIcon);
		waitForConfirmation("Are you sure to delete this gadget?");
		waitForTextNotPresent(gadgetTitleDisplay);
	}
	
	@BeforeMethod
	public void beforeTest()
	{
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		signIn("root", "gtn");
	}
	
	//Delete gadget with  deleting confirmation
	@Test(groups={"pending"})
	public void test01_DeleteGadgetWithConfirmation()
	{		
		//Goto DashBoard
		goToDashboard();
		waitForTextPresent(MESSAGE_DRAG_GADGETS_HERE);

		//Click on Add Gadgets link
		waitForElementPresent(ELEMENT_ADD_GADGETS_LINK);
		click(ELEMENT_ADD_GADGETS_LINK);
		waitForElementPresent(GADGET_DIRECTORY_LIST);

		//Drag My Agenda Gadget on list and Drop into Container
		WebElement e = waitForAndGetElement(AGENDA_GADGET_ON_LIST);
		By b = By.xpath("//div[@class='UIColumns ClearFix']");
		dragAndDropToObject(AGENDA_GADGET_ON_LIST, b);
		//actions.dragAndDropBy(e, 10, 10).build().perform();
		
		//click(ELEMENT_CLOSE_WINDOW);
		waitForTextPresent(GADGET_TITLE_DISPLAY);
		
		//Delete data
		deleteGadgetOnDashboard(GADGET_TITLE_DISPLAY);
	}
	
	@AfterMethod
	public void afterTest()
	{
		signOut();
		driver.quit();
	}
}