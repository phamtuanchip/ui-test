package org.exoplatform.selenium.cloud;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author vuna
 *
 */
public class CloudBase extends PlatformBase{

	/*-------------- base Url for eXo Cloud test -------------------*/
	public final String cloudAcceptanceUrl = "http://wks-acc.exoplatform.org/";
	public final String tenantAcceptanceUrl = "http://yopmail.wks-acc.exoplatform.org";
	public final String cloudPreProdUrl = "";
	public final String tenantPreProdeUrl = "";
	public final String cloudUrl = "//*[contains(@href, '${cloudUrl}')]";
	protected String baseCloudUrl;

	/*-------------- Sign up to eXo --------------------------------*/
	public final By ELEMENT_SIGN_UP_BUTTON = By.xpath("//button[text()='Sign up']");
	public final By ELEMENT_CLOUD_INPUT_EMAIL = By.name("email");
	/*-------------- End of Sign up to eXo -------------------------*/

	/*-------------- Yopmail informations --------------------------*/
	public final String YOPMAIL_ADDRESS = "fqa-exo@yopmail.com";
	public final String YOPMAIL_URL = "http://www.yopmail.com/en/";
	public final By ELEMENT_YOPMAIL_LOGIN = By.name("login");
	public final By ELEMENT_YOPMAIL_CHECK_INBOX = By.className("sbut");
	public final By ELEMENT_CHECK_NEW_MAIL = By.className("slientext");
	public final By ELEMENT_YOPMAIL_INBOX_TITLE = By.xpath("//iframe[@name='ifinbox']");
	public final By ELEMENT_YOPMAIL_TITLE = By.xpath("//*[@class ='lms' and text()='Activate Your eXo Account']");
	public final By ELEMENT_YOPMAIL_TITLE_AUX = By.xpath("//*[@class ='lms' and text()='Validate Your eXo Registration']");
	public final By ELEMENT_YOPMAIL_TITLE_WELCOME_EXO = By.xpath("//*[@class ='lms' and text()='Welcome to eXo!']");
	public final By ELEMENT_YOPMAIL_TITLE_WELCOME_EXO_AUX = By.xpath("//*[@class ='lms' and text()='Welcome to the yopmail Social Intranet']");
	public final By ELEMENT_YOPMAIL_CONTENT = By.xpath("//iframe[@name='ifmail']");

	/*------------- Complete a registration ------------------------*/
	public final String ELEMENT_LAST_NAME = "exoplatformsea";
	public final String ELEMENT_PHONE_WORK = "0123456789";
	public final String ELEMENT_COMPANY_NAME = "exoplatform";
	public final String ELEMENT_PASSWORD = "gtngtn";
	public final String ELEMENT_CONFIRM_PASSWORD = "gtngtn";
	public final By ELEMENT_INPUT_FIRST_NAME = By.name("first_name");
	public final By ELEMENT_INPUT_LAST_NAME = By.name("last_name");
	public final By ELEMENT_INPUT_PHONE_WORK = By.name("phone_work");
	public final By ELEMENT_INPUT_PHONE_WORK_AUX = By.name("phone");
	public final By ELEMENT_INPUT_COMPANY_NAME = By.name("company");
	public final By ELEMENT_INPUT_PASSWORD = By.name("password");
	public final By ELEMENT_INPUT_CONFIRM_PASSWORD = By.name("password2");
	/*--------------------------------------------------------------*/

	/*------------ Getting started in 3 steps ----------------------*/
	//Step 1: Complete your profile
	public final By ELEMENT_AVATAR_IMAGE = By.id("avatarImage"); 
	//Move to PlatformBase
	//public final By ELEMENT_SKIP_TO_HOMEPAGE = By.xpath("//*[contains(text(), 'Step 1')]/..//a[contains(text(), 'Skip to homepage')]");
	
	//Step 2: Join Spaces
	public final By ELEMENT_CHECKBOX_GETTING_STARTED = By.xpath("//*[@class='uiCheckbox']/span[text()='Getting Started']");
	//Step 3: Invite Coworkers
	public final By ELEMENT_INPUT_EMAIL_ADDRESS = By.id("email");

	/*------------ Button for eXo Cloud ----------------------------*/
	public final By ELEMENT_BUTTON_CREATE = By.xpath("//button[text()='Create']");
	public final By ELEMENT_BUTTON_JOIN = By.xpath("//button[text()='Join']");
	public final By ELEMENT_BUTTON_NEXT_FIRST_PAGE = By.xpath("//div[1][@class = 'item']//button[text()='Next']");
	public final By ELEMENT_BUTTON_NEXT_SECOND_PAGE = By.xpath("//div[2][@class = 'item']//button[text()='Next']");
	public final By ELEMENT_BUTTON_FINISH_THIRD_PAGE = By.xpath("//div[3][@class = 'item']//button[text()='Finish']");

	/*------------ Common methods for eXo Cloud test ---------------*/

	/**
	 * @author vuna
	 * @param url
	 * <li> 0 to get the acceptance Cloud's url </li>
	 * <li> 1 to get the pre-prod server Cloud's url </li>
	 */
	public void initYopmailAccountTest(int url){
		info("== Initialize Account Test ==");
		activeAccountForYopmail(url);
		switchToNewWindow();
		registerAccountForYopmail();
	} 

	/**
	 * @author vuna
	 * @param opParams: 
	 * <li> 0 to get the acceptance Cloud's url </li>
	 * <li> 1 to get the pre-prod server Cloud's url </li>
	 */
	public void initCloudUrl(int... opParams){
		info("== Initial setting Cloud Url ==");
		int url = (Integer) (opParams.length > 0 ? opParams[0]:0); 
		switch (url) {
		case 0:
			baseCloudUrl = cloudAcceptanceUrl;
			break;
		case 1:
			baseCloudUrl = cloudPreProdUrl;
			break;	
		default:
			break;
		}
		info("== End of initial setting Cloud Url ==");
	}

	/**
	 * @author vuna
	 * Get started your social intranet 
	 */
	public void startExoCloud(){
		info("== Start to login to eXo Cloud ==");
		goToYopmail();
		//click(ELEMENT_YOPMAIL_TITLE_WELCOME_EXO);
		if (waitForAndGetElement(ELEMENT_YOPMAIL_TITLE_WELCOME_EXO, DEFAULT_TIMEOUT, 0) != null){
			click(ELEMENT_YOPMAIL_TITLE_WELCOME_EXO);

		}else{
			click(ELEMENT_YOPMAIL_TITLE_WELCOME_EXO_AUX);
		}

		switchToParentWindow();

		info("== Click on started link ==");
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_YOPMAIL_CONTENT));
		WebElement elementUrl = waitForAndGetElement(cloudUrl.replace("${cloudUrl}", tenantAcceptanceUrl));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementUrl);
		Utils.pause(5000);
	}

	/**
	 * @author vuna
	 * @param emailAddress: email to activate your eXo account
	 */
	public void enterEmail2Signup(String emailAddress){
		info("== Enter an Email to signup ==");
		type(ELEMENT_CLOUD_INPUT_EMAIL, emailAddress, true);
		click(ELEMENT_SIGN_UP_BUTTON);
		Utils.pause(3000);
	}

	/**
	 * @author vuna
	 * @param url
	 * <li> 0 to get the acceptance Cloud's url </li>
	 * <li> 1 to get the pre-prod server Cloud's url </li>
	 */
	public void activeAccountForYopmail(int... url){
		driver.close();
		initSeleniumTest();

		info("== Go to Yopmail Account ==");
		//initCloudUrl(url);	
		//info("== Base Cloud Url is... " + baseCloudUrl);

		goToYopmail();
		//click(ELEMENT_YOPMAIL_TITLE);
		if (waitForAndGetElement(ELEMENT_YOPMAIL_TITLE, DEFAULT_TIMEOUT, 0) != null){
			click(ELEMENT_YOPMAIL_TITLE);
		}
		else{
			click(ELEMENT_YOPMAIL_TITLE_AUX);
		}

		switchToParentWindow();

		info("== Click on registration link ==");
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_YOPMAIL_CONTENT));
		WebElement elementUrl = waitForAndGetElement(cloudUrl.replace("${cloudUrl}", baseUrl));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementUrl);
		Utils.pause(3000);
	}

	/**
	 * @author vuna
	 * Complete a registration for Yopmail account
	 */
	public void registerAccountForYopmail(){	
		info("== Complete a registration for Yopmail account ==");
		waitForAndGetElement(ELEMENT_INPUT_FIRST_NAME);
		driver.manage().window().maximize();
		type(ELEMENT_INPUT_LAST_NAME, ELEMENT_LAST_NAME, true);
		//type(ELEMENT_INPUT_PHONE_WORK, ELEMENT_PHONE_WORK, true);
		//type(ELEMENT_INPUT_COMPANY_NAME, ELEMENT_COMPANY_NAME, true);

		//Phone work
		if (waitForAndGetElement(ELEMENT_INPUT_PHONE_WORK, DEFAULT_TIMEOUT, 0) != null){
			type(ELEMENT_INPUT_PHONE_WORK, ELEMENT_PHONE_WORK, true);
		}
		else{
			type(ELEMENT_INPUT_PHONE_WORK_AUX, ELEMENT_PHONE_WORK, true);
		}
		//Company
		if (waitForAndGetElement(ELEMENT_INPUT_COMPANY_NAME, DEFAULT_TIMEOUT, 0) != null){

			type(ELEMENT_INPUT_COMPANY_NAME, ELEMENT_COMPANY_NAME, true);

		}

		type(ELEMENT_INPUT_PASSWORD, ELEMENT_PASSWORD, true);
		type(ELEMENT_INPUT_CONFIRM_PASSWORD, ELEMENT_CONFIRM_PASSWORD, true);
		//click(ELEMENT_BUTTON_CREATE);
		if (waitForAndGetElement(ELEMENT_BUTTON_CREATE, DEFAULT_TIMEOUT, 0) != null){
			click(ELEMENT_BUTTON_CREATE);
		}else{
			click(ELEMENT_BUTTON_JOIN);
		}
		Utils.pause(5000);
		driver.close();
	}

	/**
	 * @author vuna
	 * Go to Yopmail Account
	 */
	public void goToYopmail(){
		info("== Yomail Account ==");
		driver.navigate().to(YOPMAIL_URL);
		driver.manage().window().maximize();
		type(ELEMENT_YOPMAIL_LOGIN, YOPMAIL_ADDRESS, true);
		click(ELEMENT_YOPMAIL_CHECK_INBOX);
		waitForAndGetElement(ELEMENT_YOPMAIL_INBOX_TITLE);
		click(ELEMENT_CHECK_NEW_MAIL);	
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_YOPMAIL_INBOX_TITLE));
	}

}