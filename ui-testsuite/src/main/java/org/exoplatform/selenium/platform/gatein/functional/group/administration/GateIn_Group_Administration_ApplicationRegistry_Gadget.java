package org.exoplatform.selenium.platform.gatein.functional.group.administration;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.ManageApplications.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;

public class GateIn_Group_Administration_ApplicationRegistry_Gadget extends GateInBase {

	public static By OK_BUTTON = By.linkText("OK");

	@BeforeMethod
	public void beforeMethods(){
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterTest(){
		signOut();
		driver.quit();
	}

	@Test
	public void test02_AddRemoteGadgetWithValidUrl () {
		String DATA_URL = "http://www.labpixies.com/gadgads/gumtree/gumtree.xml";
		By DATA_GADGET_LINK = By.xpath("//a[@title='Gumtree - Londons free classifieds']");
		String DATA_GADGET_NAME = "Gumtree - Londons free classifieds";

		signIn("root", "gtn");
		goToApplicationRegistry();
		click(ELEMENT_GADGET_LINK);
		addRemoteGadget(DATA_URL);
		debug("Verify new gadget is added");
		waitForElementPresent(DATA_GADGET_LINK);

		debug("Delete gadget");
		deleteGadget(DATA_GADGET_NAME);
	}

	@Test
	public void test05_AddRemoteGadgetWithExistingUrl () {
		String DATA_URL = "http://www.labpixies.com/campaigns/todo/todo.xml";
		By DATA_GADGET_LINK = By.xpath("//a[@title='To-Do List']");
		String DATA_GADGET_NAME = "To-Do List";
		String MESSAGE_URL_EXIST = "This url is existing, please select another one!";

		signIn("root", "gtn");
		goToApplicationRegistry();
		click(ELEMENT_GADGET_LINK);
		addRemoteGadget(DATA_URL);
		debug("Verify new gadget is added");
		waitForElementPresent(DATA_GADGET_LINK);

		debug("Add gadget with URL existing");
		addRemoteGadget(DATA_URL);
		waitForMessage(MESSAGE_URL_EXIST);
		click(OK_BUTTON);

		debug("Delete gadget");
		deleteGadget(DATA_GADGET_NAME);	
	}
}