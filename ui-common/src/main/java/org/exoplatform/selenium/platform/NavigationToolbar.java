package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationToolbar extends PlatformBase {
	
	public static final String MESSAGE_EDIT_PERMISSION_SETTINGS = "The \"Edit Permission Setting\" list can not be empty.";
	//Go to portal sites
	public static void goToPortalSites() {
		info("--Go to Portal Site Management--");
				
		click(ELEMENT_LINK_SITES);
		pause(500);
	}

	//Go to Portal Manage Pages	
	public static void goToManagePages() {
		info("--Go to Portal Site Management--");
		//		waitForAndGetElement(By.xpath(ELEMENT_LINK_SETUP));
		for(;;){
			mouseOver(ELEMENT_LINK_SETUP, false);
			pause(500);
			if (waitForAndGetElement(ELEMENT_LINK_PORTAL,15000,0)!=null) {	
				mouseOver(ELEMENT_LINK_PORTAL, false);
				if (waitForAndGetElement(ELEMENT_LINK_PAGES,15000,0)!=null){
					click(ELEMENT_LINK_PAGES);
					break;
				}
			}
		}
	}

	//Go to Dashboard
	public static void goToDashboard(){
		info("--Go to Dashboard page--");
		WebElement element = driver.findElement(By.id("UserNavigationTabsContainer"));
		actions.moveToElement(element).build().perform();
		driver.findElement(By.linkText("Dashboard")).click();	
	}
	/**
	 * @update for gatein by thuntn
	 */
	//Go to User management page
	public static void goToNewStaff() {
		//info("Go to New Staff");
		goToPage(ELEMENT_SEARCH_ICON_REGISTER, ELEMENT_MENU_GROUP, ELEMENT_MENU_ORGANIZATION, ELEMENT_MENU_STAFF);
	}

	//Go to My Account
	public static void goToMyAccount(){
		WebElement UI = driver.findElement(By.id("UserNavigationTabsContainer"));
		actions.moveToElement(UI).build().perform();
		driver.findElement(By.linkText("My Account")).click();	
		pause(500);
	}

	//Go to Portal/Group Sites
	public static void goToGroupSites(){
		info("--Go to Portal Site Management--");
		waitForAndGetElement(By.xpath(ELEMENT_LINK_SETUP));
		mouseOver(ELEMENT_LINK_SETUP, false);
		pause(500);
		mouseOver(ELEMENT_LINK_PORTAL, false);
		pause(500);
		WebElement element;
		element = waitForAndGetElement(ELEMENT_LINK_GROUP);
		actions.moveToElement(element).click(element).build().perform();
		pause(500);
	}

	//Go to add page locator with Editor
	public static void goToAddPageEditor(){
		waitForAndGetElement(By.xpath(ELEMENT_LINK_EDITOR));
		mouseOver(ELEMENT_LINK_EDITOR, true);
		pause(500);
		mouseOver(ELEMENT_LINK_EDITOR_PAGE, true);
		pause(500);
		WebElement element = waitForAndGetElement(ELEMENT_LINK_EDITOR_ADD_PAGE);
		actions.moveToElement(element).click(element).build().perform();
		pause(500);
	}
/**
 * @update by thuntn for gatein
 */
	public static void goToUsersAndGroupsManagement() {
		info("--Go to Users and groups management--");
		goToPage(ELEMENT_ICON_USER_SEARCH,ELEMENT_MENU_GROUP,ELEMENT_MENU_ORGANIZATION,ELEMENT_MENU_USER_GROUPS);
	}

	//Go to Portal Application Registry
	public static void goToApplicationRegistry() {
		info("--Go to Portal Application Registry--");
		mouseOver(ELEMENT_LINK_SETUP, false);
		pause(500);
		waitForElementPresent(ELEMENT_APPLICATIONS_LINK);
		click(ELEMENT_APPLICATIONS_LINK);
		pause(500);
	}

	public static void goToEditPageEditor () {
		info("----Go to Edit page editor----");
		mouseOver(ELEMENT_MENU_EDIT_LINK,false);
		pause(500);
		
		click(ELEMENT_MENU_EDIT_LAYOUT);
		pause(500);
	}
	/**
	 * @update: thuntn
	 */
	//Go to change language for user interface
	public static void goToChangeLanguageForUserInterface(){
		info("--Go to change language for user interface--");
		mouseOver(ELEMENT_SIGN_OUT_ICON,true);
		click(By.linkText("Change Language"));	
		pause(500);
	}

	//Go to register page in public mode
	public static void goToRegisterPageInPublicMode(WebDriver driverTest){
		String registerPageLink = baseUrl.concat("/portal/classic/register");
		driverTest.navigate().to(registerPageLink);
		waitForTextPresent(TEXT_REGISTER_ACCOUNT);
	}
}
