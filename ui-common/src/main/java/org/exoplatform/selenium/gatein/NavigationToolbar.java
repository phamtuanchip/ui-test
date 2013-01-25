package org.exoplatform.selenium.gatein;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.gatein.ManageAccount.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationToolbar extends GateInBase {
	
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
			mouseOver(ELEMENT_MENU_GROUP, false);
			pause(500);
			if (waitForAndGetElement(ELEMENT_MENU_ADMIN,15000,0)!=null) {	
				mouseOver(ELEMENT_MENU_ADMIN, false);
				if (waitForAndGetElement(ELEMENT_MENU_MANAGE_PAGE,15000,0)!=null){
					click(ELEMENT_MENU_MANAGE_PAGE);
					break;
				}
			}
		}
	}

	//Go to Dashboard
	public static void goToDashboard(){
		info("--Go to Dashboard page--");
		
		click(By.linkText("Dashboard"));	
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
		info("--Go to Group Site Management--");
		click(ELEMENT_MENU_GROUP);
		pause(500);
	}

	//Go to add page locator with Editor
	public static void goToAddPageEditor(){
		waitForAndGetElement(By.xpath(ELEMENT_LINK_EDITOR));
		mouseOver(ELEMENT_LINK_EDITOR, true);
		pause(500);
		
		click(ELEMENT_ADD_PAGE_MENU);
		pause(500);
	}
	/**
	 * @author thuntn
	 */
	//Go to add page locator with Editor
		public static void goToAddPageGroupEditor(){
			waitForAndGetElement(By.xpath(ELEMENT_LINK_GROUP_EDITOR));
			mouseOver(ELEMENT_LINK_GROUP_EDITOR, true);
			pause(500);
			
			click(ELEMENT_ADD_PAGE_MENU);
			pause(500);
		}
/**
 * @update by thuntn for gatein
 */
	public static void goToUsersAndGroupsManagement() {
		info("--Go to Users and groups management--");
		goToPage(ELEMENT_ICON_USER_SEARCH,ELEMENT_MENU_GROUP,ELEMENT_MENU_ORGANIZATION,ELEMENT_MENU_USER_GROUPS);
	}
	/**
	 * @update: thuntn
	 */
	//Go to Portal Application Registry
	public static void goToApplicationRegistry() {
		info("--Go to Portal Application Registry--");
		mouseOver(ELEMENT_MENU_GROUP, false);
		pause(500);
		mouseOver(ELEMENT_MENU_ADMIN,true);
		click(ELEMENT_APPLICATIONS_LINK);
		pause(500);
	}

	public static void goToEditPageEditor () {
		info("----Go to Edit page editor----");
		mouseOver(ELEMENT_MENU_EDIT,false);
		pause(500);
		
		click(ELEMENT_EDIT_PAGE_MENU);
		pause(500);
	}
	public static void goToEditPageGroupEditor () {
		info("----Go to Edit page editor----");
		mouseOver(ELEMENT_LINK_GROUP_EDITOR,false);
		pause(500);
		
		click(ELEMENT_EDIT_PAGE_MENU);
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
		String registerPageLink = baseUrl.concat("/classic/register");
		driverTest.navigate().to(registerPageLink);
		waitForTextPresent(TEXT_REGISTER_ACCOUNT);
	}
}
