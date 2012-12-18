package org.exoplatform.selenium.platform.gatein.functional.portalnavigation.edit;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationManagement.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;


public class GateIn_PortalNavigation_EditNavigation_Node_CopyPaste extends GateInBase 
{
	//Define Data
	public String EDIT_ACME_NAVIGATION = "//div[text()='acme']/following::a[text()='Edit Navigation']";
	public String EDIT_INT_NAVIGATION = "//div[text()='intranet']/following::a[text()='Edit Navigation']";
	public String UP_LEVEL = "//a[contains(@title,'Up Level')]";
	public String ADD_NODE_BUTTON = "//a[contains(text(),'Add Node')]";
	public String NODE_NAME_TEXTBOX = "name";
	public String CLOSE_NAVIGATION = "//a[contains(@title,'Close Window')]";
	public String CHILD_NODE = "(//a[contains(text(),'Node1')])[2]";

	//Global variables
	public String FIRST_NODE = "";
	public String SECOND_NODE = "";
//	public WebElement ELEMENT = null;

	//Messages
	public String SAME_SOURCE_MESSAGE = "The source and the destination must be different.";
	public String SAME_PLACE_MESSAGE = "This node name already exists.";

	@BeforeMethod()
	public void beforeTest()
	{
		initSeleniumTest();
		//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		actions = new Actions(driver);
		signIn("john", "gtn");
	}

	//Define using methods
	public void addNode(String addNodeButton, String nodeNameTextbox, String nodeNameInput)
	{
		//Click on Add Node Button
		click(addNodeButton);
		type(By.name(nodeNameTextbox), nodeNameInput, false);
		
		//Click button Save on Add Node screen
		save();
		pause(1000);
		
		//Click button Save on Navigation Management screen
		save();
		pause(1000);
	}

	//Copy/Paste a node into another node in the same navigation
	@Test()
	public void test01_CopyPasteNodeInSameNavigation()
	{
		info("-START test01_CopyPasteNodeInSameNavigation");

		//Goto Edit Navigation 
		info("---Goto Edit Navigation");
		goToPortalSites();

		//Click on Edit Navigation of acme
		info("---Click on Edit Navigation of ACME");
		click(EDIT_ACME_NAVIGATION);

		info("---Add Test Data");

		//Add Node1
		info("-----Add Node1");
		waitForElementPresent(By.xpath(UP_LEVEL));
		click(UP_LEVEL);
		FIRST_NODE = "Node1";
		addNode(ADD_NODE_BUTTON, NODE_NAME_TEXTBOX, FIRST_NODE);
		pause(1000);

		//Click on Edit Navigation of acme
		info("---Click on Edit Navigation of ACME");
		click(EDIT_ACME_NAVIGATION);

		//Add Node2
		info("-----Add Node2");
		waitForElementPresent(By.xpath(UP_LEVEL));
		click(UP_LEVEL);
		SECOND_NODE = "Node2";
		addNode(ADD_NODE_BUTTON, NODE_NAME_TEXTBOX, SECOND_NODE);
		pause(1000);

		//Click on Edit Navigation of acme
		info("-----Click on Edit Navigation of ACME");
		click(EDIT_ACME_NAVIGATION);

		//Verify Node2 presents
		info("-----Verify Node2 has been added");
		waitForTextPresent(SECOND_NODE);

		//Click on Edit Navigation of acme
		info("---Click on Edit Navigation of ACME");
		click(EDIT_ACME_NAVIGATION);
		pause(500);

		info("---Copy Node1 Paste to Node2");
		waitForTextPresent(FIRST_NODE);
		copyNode(By.xpath("//a[text()='" + FIRST_NODE + "']"));
		pasteNode(By.xpath("//a[text()='"+ SECOND_NODE +"']"));
		pause(1000);

		save();
		pause(1000);

		//Click on Edit Navigation of acme
		info("---Click on Edit Navigation of ACME");
		click(EDIT_ACME_NAVIGATION);

		//Verify Node2 has a child node named Node1
		click("//a[text()='" + SECOND_NODE + "']");
		info("---Verify Node2 has a child node named Node1");
		waitForElementPresent(CHILD_NODE);
		assert isElementPresent(CHILD_NODE):"Can not found Node1";

		info("---Delete test Data");

		//Delete Node1
		info("-----Delete Node1");
		deleteNode("acme", FIRST_NODE, FIRST_NODE, true);

		//Delete Node2
		info("-----Delete Node2");
		deleteNode("acme", SECOND_NODE, SECOND_NODE, true);

		info("-END test01_CopyPasteNodeInSameNavigation");
	}

	//Copy/Paste a node into another node in different navigation
	@Test()
	public void test02_CopyPasteNodeInDiffirentNavigation()
	{
		info("-START test02_CopyPasteNodeInDiffirentNavigation");

		//Goto Edit Navigation 
		info("---Goto Edit Navigation");
		goToPortalSites();

		//Click on Edit Navigation of acme
		info("---Click on Edit Navigation of ACME");
		click(EDIT_ACME_NAVIGATION);

		info("---Add Test Data");

		//Add Node3
		info("-----Add Node3");
		waitForElementPresent(By.xpath(UP_LEVEL));
		click(UP_LEVEL);
		FIRST_NODE = "Node3";
		addNode(ADD_NODE_BUTTON, NODE_NAME_TEXTBOX, FIRST_NODE);
		pause(1000);

		//Click on Edit Navigation of ACME
		info("-----Click on Edit Navigation of ACME");
		click(EDIT_ACME_NAVIGATION);

		//Verify Node3 presents
		waitForTextPresent(FIRST_NODE);

		//Close Navigation Management screen
		click(CLOSE_NAVIGATION);

		//Click on Edit Navigation of Intranet
		info("----- Click on Edit Navigation of INTRANET");
		click(EDIT_INT_NAVIGATION);

		//Add Node4
		info("-----Add Node4");
		waitForElementPresent(By.xpath(UP_LEVEL));
		click(UP_LEVEL);
		SECOND_NODE = "Node4";
		addNode(ADD_NODE_BUTTON, NODE_NAME_TEXTBOX, SECOND_NODE);
		pause(1000);

		//Click on Edit Navigation of Intranet
		info("-----Click on Edit Navigation of INTRANET");
		click(EDIT_INT_NAVIGATION);
		pause(1000);

		//Verify Node4 presents
		waitForTextPresent(SECOND_NODE);

		//Close Navigation Management screen
		click(CLOSE_NAVIGATION);

		//Click on Edit Navigation of ACME
		info("-----Click on Edit Navigation of ACME");
		click(EDIT_ACME_NAVIGATION);

		info("---Can't Copy Node3 to Node4");

		copyNode(By.xpath("//a[text()='" + FIRST_NODE +"']"));
		click(CLOSE_NAVIGATION);

		//Click on Edit Navigation of Intranet
		info("-----Click on Edit Navigation of INTRANET");
		click(EDIT_INT_NAVIGATION);
		pause(1000);

		//Right click on Node4
		rightClickOnElement(By.linkText(SECOND_NODE));

		//Confirm Paste Node item not existed
		info("-----Paste Node item not existed");
		waitForElementNotPresent(By.linkText("Paste Node"));
		pause(1000);

		info("---Delete test Data");

		//Delete Node4
		info("-----Delete Node4");
		deleteNode("intranet", SECOND_NODE, SECOND_NODE, true);

		//Click on Edit Navigation of ACME
		info("-----Click on Edit Navigation of ACME");
		click(EDIT_ACME_NAVIGATION);

		//Delete Node3
		info("---Delete Node3");
		deleteNode("acme", FIRST_NODE, FIRST_NODE, true);
		info("-END test02_CopyPasteNodeInDiffirentNavigation");
	}

	//Copy/Paste a node into the same place
	@Test()
	public void test03_CopyPasteNodesInSamePlace()
	{
		info("-START test03_CopyPasteNodesInSamePlace");

		//Goto Edit Navigation 
		info("---Goto Edit Navigation");
		goToPortalSites();

		//Click on Edit Navigation of acme
		info("-----Click on Edit Navigation of ACME");
		click(EDIT_ACME_NAVIGATION);

		info("---Add Test Data");

		//Add Node5
		info("-----Add Node5");
		waitForElementPresent(By.xpath(UP_LEVEL));
		click(UP_LEVEL);
		FIRST_NODE = "Node5";
		addNode(ADD_NODE_BUTTON, NODE_NAME_TEXTBOX, FIRST_NODE);
		pause(1000);

		//Click on Edit Navigation of acme
		click(EDIT_ACME_NAVIGATION);

		//Verify Node5 presents
		info("-----Verify Node5 present");
		waitForTextPresent(FIRST_NODE);

		//Add Node6
		waitForElementPresent(By.xpath(UP_LEVEL));
		click(UP_LEVEL);
		SECOND_NODE = "Node6";
		addNode(ADD_NODE_BUTTON, NODE_NAME_TEXTBOX, SECOND_NODE);

		//Click on Edit Navigation of acme
		click(EDIT_ACME_NAVIGATION);

		//Verify Node6 presents
		waitForTextPresent(SECOND_NODE);

		info("---Copy Node5 to Node6");

		copyNode(By.xpath("//a[text()='" + FIRST_NODE + "']"));
		pasteNode(By.xpath("//a[text()='"+ SECOND_NODE +"']"));

		//Save
		save();
		pause(500);

		//Click on Edit Navigation of acme
		click(EDIT_ACME_NAVIGATION);

		copyNode(By.xpath("//a[text()='" + FIRST_NODE + "']"));
		pasteNode(By.xpath("//a[text()='"+ SECOND_NODE +"']"));

		//Verify display message to notice that Node5 already existed
		waitForTextPresent(SAME_PLACE_MESSAGE);
		click(ELEMENT_OK_BUTTON);

		info("---Delete Test Data");

		//Delete Node5
		deleteNode("acme", FIRST_NODE, FIRST_NODE, true);
		pause(1000);

		//Click on Edit Navigation of acme
		click(EDIT_ACME_NAVIGATION);

		//Delete Node6
		deleteNode("acme", SECOND_NODE, SECOND_NODE, true);
		pause(1000);

		info("-END test03_CopyPasteNodesInSamePlace");
	}

	//Copy/Paste a node into the same this source
	@Test()
	public void test04_CopyPasteNodeInSameSource()
	{
		info("-START test04_CopyPasteNodeInSameSource");

		//Goto Edit Navigation 
		info("---Goto Edit Navigation");
		goToPortalSites();

		//Click on Edit Navigation of acme
		click(EDIT_ACME_NAVIGATION);

		//Add Node7
		waitForElementPresent(By.xpath(UP_LEVEL));
		click(UP_LEVEL);
		FIRST_NODE = "Node7";
		addNode(ADD_NODE_BUTTON, NODE_NAME_TEXTBOX, FIRST_NODE);

		//Click on Edit Navigation of acme
		click(EDIT_ACME_NAVIGATION);

		//Verify Node7 presents
		waitForTextPresent(FIRST_NODE);

		copyNode(By.xpath("//a[text()='" + FIRST_NODE + "']"));
		pasteNode(By.xpath("//a[text()='"+ SECOND_NODE +"']"));
		pause(1000);

		//Verify display message to notice that The source and the destination must be different
		waitForTextPresent(SAME_SOURCE_MESSAGE);
		click(ELEMENT_OK_BUTTON);

		//Delete Node7
		deleteNode("acme", FIRST_NODE, FIRST_NODE, true);
		pause(1000);

		info("-END test04_CopyPasteNodeInSameSource");
	}

	@AfterMethod()
	public void afterTest() 
	{
		signOut();
		driver.quit();
	}
}
