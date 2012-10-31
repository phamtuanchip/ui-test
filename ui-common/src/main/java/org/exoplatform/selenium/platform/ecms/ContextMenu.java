package org.exoplatform.selenium.platform.ecms;

import static org.exoplatform.selenium.TestLogger.info;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ContextMenu extends EcmsBase {

	public static final By ELEMENT_MENU_LOCK = By.xpath("//a[contains(text(),'Lock')]");
	public static final By ELEMENT_MENU_UNLOCK = By.xpath("//a[contains(text(),'UnLock')]");
	public static final By ELEMENT_MENU_CHECKIN = By.xpath("//a[contains(text(),'CheckIn')]");
	public static final By ELEMENT_MENU_CHECKOUT = By.xpath("//a[contains(text(),'CheckOut')]");
	public static final By ELEMENT_MENU_RENAME_NODE = By.linkText("Rename");
	public static final By ELEMENT_MENU_PASTE = By.xpath("//a[contains(text(),'Paste')]");
	public static final By ELEMENT_MENU_REFRESH=By.xpath("//a[@title='refresh']");
	public static final By ELEMENT_MENU_DELETE = By.xpath("//a[contains(text(),'Delete')]");
	
	//lock node
	public static void lockNode(By locator){
		for(int repeat=0;; repeat ++)
		{
			if (repeat >= ACTION_REPEAT) {
				Assert.fail("Cannot perfotrm this action after " + ACTION_REPEAT + " tries");
			}
			rightClickOnElement(locator);
			if (isDisplay(waitForAndGetElement(ELEMENT_MENU_LOCK))) break;
			pause(WAIT_INTERVAL);
			info("Retry...[" + repeat + "]");
		}

		click(ELEMENT_MENU_LOCK);
	}

	//check node is being locked
	public static boolean checkLockNode(Object locator){
		boolean locked;
		By by = locator instanceof By ? (By)locator : By.xpath((String)locator);
		//		actions.contextClick(unlock).build().perform();
		rightClickOnElement(by);
		pause(500);
		if (isElementPresent(ELEMENT_MENU_UNLOCK)) {
			locked = true;
		} else 
			locked =false;
		WebElement unlock = waitForAndGetElement(by);
		unlock.sendKeys(Keys.RETURN);
		return locked;
	}

	public static void checkInNode(By locator){
		for(int repeat=0;; repeat ++)
		{
			if (repeat >= ACTION_REPEAT) {
				Assert.fail("Cannot perfotrm this action after " + ACTION_REPEAT + " tries");
			}
			rightClickOnElement(locator);
			if (isDisplay(waitForAndGetElement(ELEMENT_MENU_CHECKIN, 30000, 0))) break;
			info("Retry...[" + repeat + "]");
		}

		click(ELEMENT_MENU_CHECKIN);
		info("Check in node successfully");

	}

	//check out a node
	public static void checkOutNode(By locator){
		rightClickOnElement(locator);
		WebElement out = waitForAndGetElement(ELEMENT_MENU_CHECKOUT);                
		if (isDisplay(out)){
			click(ELEMENT_MENU_CHECKOUT);
			info("Check out node successfully");
		}else{
			if (loopCount > 10)
			{
				loopCount=0;
				return;
			}
			loopCount++;
			checkOutNode(locator);    
			info("Can not checkout the node!");
		}
	}  

	//delete level 1 node
	public static void deleteDocument(By locator, int...timeout){
		int iTimeout = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
		for(int repeat=0;; repeat ++)
		{
			if (repeat >= ACTION_REPEAT) {
				Assert.fail("Cannot perform this action after " + ACTION_REPEAT + " tries");
			}
			rightClickOnElement(locator);
			info("before display " + repeat);
			if (isDisplay(waitForAndGetElement(ELEMENT_MENU_DELETE, DEFAULT_TIMEOUT, 0))) 
			{	
				info("element is displayed ");
				click(ELEMENT_MENU_DELETE);
				waitForTextPresent("Confirm Deletion");
				click(By.linkText("OK"));
				break;
			}
			info("Retry...[" + repeat + "]");

		}
		waitForElementNotPresent(By.linkText("OK"));
		click(ELEMENT_MENU_REFRESH);
		waitForElementNotPresent(locator, iTimeout);
		info(locator.toString() + " was deleted successfully");		
	}

	//Define common function
	public static void unLockNode(By locator){
		rightClickOnElement(locator);
		click(ELEMENT_MENU_UNLOCK);
	}

	public static void pasteNode(By locator) {
		for (int i =0;; i++){
			if (i>DEFAULT_TIMEOUT/WAIT_INTERVAL){
				Assert.fail("Timeout");
			}
			rightClickOnElement(locator);
			if (isElementPresent(ELEMENT_PASTE_NODE)){
				click(ELEMENT_PASTE_NODE);
				return;
			}
			pause(WAIT_INTERVAL);
		}
	}
}
