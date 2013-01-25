package org.exoplatform.selenium.gatein;

import static org.exoplatform.selenium.TestLogger.info;
import org.openqa.selenium.By;

public class ManageAccount extends GateInBase {

	//[Create a New Account] Screen (Public Mode)
	public static final By ELEMENT_SUBSCRIBE_BUTTON = By.xpath(".//*[@id='UIRegisterForm']/div[2]/div/div/a[1]");
	public static final By ELEMENT_RESET_BUTTON = By.xpath(".//*[@id='UIRegisterForm']/div[2]/div/div/a[2]");
	public static final By ELEMENT_REGISTER_LINK = By.xpath("//a[contains(@class,'Register')]");
	public static final By ELEMENT_INPUT_CONFIRM_PASSWORD_PUBLIC_MODE = By.id("confirmPassword");
	public static final By ELEMENT_INPUT_EMAIL_PUBLIC_MODE = By.id("emailAddress");
	public static final By ELEMENT_REGISTER_ACCOUNT_LINK = By.xpath("//a[contains(text(),'Register')]");
	public static final By ELEMENT_USER_PROFILE_SETTING_LANGUAGE = By.name("user.language");
	
	public static final String MESSAGE_SUCCESSFULLY_REGISTERED_ACCOUNT_INFRENCH = "Votre compte a été créé";
	public static final String MESSAGE_UPDATE_INFO = "The user profile has been updated.";
	public static final String MESSAGE_SUCCESSFULLY_REGISTERED_ACCOUNT = "You have successfully registered a new account!";
	public static final String MESSAGE_DUPLICATE_ACCOUNT = "This user name already exists, please enter a different name.";
	public static final String MESSAGE_ALERT_PASSWORD = "Password and Confirm Password must be the same.";
	public static final String MESSAGE_INVALID_EMAIL_ADDRESS = "Your email address is invalid. Please enter a different address.";
	public static final String TEXT_REGISTER_ACCOUNT = "Register New Account";
	
	//change language
	public static final String ELEMENT_CURRENT_PORTAL_CONFIG = ELEMENT_SELECT_EDIT_PORTAL_CONFIG.replace("${portalName}", "intranet");
	public static final String TEXT_CHANGE_LANGUAGE="Interface Language Setting";
	public static final By ELEMENT_USER_SETTING_LANGUAGE = By.linkText("French");
	public static final By ELEMENT_USER_SETTING_LANGUAGE_ORIGINAL = By.linkText("Anglais");
	public static final By ELEMENT_PORTAL_CHANGE_LANGUAGE = By.xpath("//a[@class='Language']");
	
	public static final By ELEMENT_APPLY_BUTTON_INFRENCH = By.linkText("Appliquer");
	

	//Sign-in function for eXoGTN
	public static void signIn(String username, String password) {
		info("--Sign in as " + username + "--");
		//click(By.linkText("Login to the ACME social intranet"));
		//click("//a/b[text()='Sign in']");
		click(ELEMENT_SIGN_IN_LINK);
		type(ELEMENT_INPUT_USERNAME, username, true);
		type(ELEMENT_INPUT_PASSWORD, password, true);
		click(ELEMENT_SIGN_IN_CONFIRM_BUTTON);
		waitForElementNotPresent(ELEMENT_SIGN_IN_CONFIRM_BUTTON);
	}
/**
 * @update by thuntn
 */
//	//Sign-out for eXoGTN
	public static void signOut(){
		mouseOver(ELEMENT_SIGN_OUT_ICON, true);
		click(ELEMENT_SIGN_OUT_LINK);
		pause(500);
		driver.get(baseUrl);
	}

	// Add a new user account
	// setting -> user -> add users
	public static void addNewUserAccount(String username, String password, String confirmPassword, String firstName, 
			String lastName, String email, String userNameGiven, String language, boolean verify) {

		info("--Create a new user using \"New Staff\" portlet--");
		type(ELEMENT_INPUT_USERNAME, username, true);
		type(ELEMENT_INPUT_PASSWORD, password, true);
		type(ELEMENT_INPUT_CONFIRM_PASSWORD, confirmPassword, true);
		type(ELEMENT_INPUT_FIRSTNAME, firstName, true);
		type(ELEMENT_INPUT_LASTNAME, lastName, true);
		type(ELEMENT_INPUT_EMAIL, email, true);
		click(ELEMENT_USER_PROFILE_TAB);
		waitForTextPresent("Given Name:");
		type(ELEMENT_INPUT_USER_NAME_GIVEN, userNameGiven, true);
		select(ELEMENT_SELECT_USER_LANGUAGE, language);
		click(ELEMENT_ACCOUNT_SETTING_TAB);
		save();

		if (verify) {
			waitForMessage("You have registered a new account.");
			closeMessageDialog();
		}
	}

	//Add a new user account in public mode
	public static void addNewUserAccountInPublicMode(String username, String password, String confirmPassword, String firstName,
			String lastName, String email, boolean verify){

		info("-- Add new user account in public mode --");
		type(ELEMENT_INPUT_USERNAME, username, true);
		type(ELEMENT_INPUT_PASSWORD, password, true);
		type(ELEMENT_INPUT_CONFIRM_PASSWORD_PUBLIC_MODE, confirmPassword, true);
		type(ELEMENT_INPUT_FIRSTNAME, firstName, true);
		type(ELEMENT_INPUT_LASTNAME, lastName, true);
		type(ELEMENT_INPUT_EMAIL_PUBLIC_MODE, email, true);
		click(ELEMENT_SUBSCRIBE_BUTTON);
		if (verify) {
			waitForMessage(MESSAGE_SUCCESSFULLY_REGISTERED_ACCOUNT);
			closeMessageDialog();
		}
	}
}
