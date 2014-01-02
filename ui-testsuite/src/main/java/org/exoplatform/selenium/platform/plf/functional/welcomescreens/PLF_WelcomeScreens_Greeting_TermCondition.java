package org.exoplatform.selenium.platform.plf.functional.welcomescreens;

import org.exoplatform.selenium.platform.PlatformBase;
import static org.exoplatform.selenium.TestLogger.info;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PLF_WelcomeScreens_Greeting_TermCondition extends PlatformBase{

	@BeforeMethod(groups="terms")
	public void beforeMethods() {
		info("Open browser and get base url");
		initSeleniumTestWithOutTermAndCondition();
		driver.get(baseUrl);
	}

	@AfterMethod(groups="terms")
	public void afterMethods() {
		info("Quit");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/*
	 * == Display Terms and Conditions screen at the first launch ==
	 * Case_ID: 79241
	 * Launch the app for the first time (/portal)
	 * Check: The screen "Terms and Conditions Agreement" is displayed.
	 */
	@Test(groups="terms")
	public void test01_DisplayTermsConditionsScreenAtTheFirstFaunch(){

		waitForAndGetElement(By.xpath(ELEMENT_TERM_CONDITION_BOX));
	}

	/*
	 * == Not remove the Terms and Conditions screen by change URL ==
	 * Case_ID: 79242
	 * - Launch the app for the first time (/portal)
	 * - Change the URL to other sites (intranet)
	 * Check: - The screen "Terms and Conditions Agreement" still displayed
	 */
	@Test(groups="terms")
	public void test02_NotRemoveTermsConditionsScreenByChangeURL (){
		String SPECIFIC_URL = "http://localhost:8080/portal/intranet";

		info("Open Base URL");
		driver.get(baseUrl);
		waitForAndGetElement(By.xpath(ELEMENT_TERM_CONDITION_BOX));

		info("Get new url");
		driver.get(SPECIFIC_URL);
		waitForAndGetElement(By.xpath(ELEMENT_TERM_CONDITION_BOX));
	}

	/*
	 * == Display legal Terms and Conditions ==
	 * Case_ID: 79243
	 * - Launch the app for the first time (/port
	 * Check: - The screen "Terms and Conditions Agreement" is displayed.
	          - The Legal Terms and conditions are displayed in a scrollbare lightbox
	 */
	@Test(groups="terms")
	public void test03_DisplayLegalTermsAndConditions(){

		waitForAndGetElement(By.xpath(ELEMENT_TERM_CONDITION_BOX));

		info("Check Background color is [rgba (255, 255, 255, 1)]");
		WebElement element1 = waitForAndGetElement(ELEMENT_TERM_CONDITION_CONTENT);
		String bgrColor = element1.getCssValue("background-color");
		info("Background color:" + bgrColor);
		assert bgrColor.equalsIgnoreCase("rgba(255, 255, 255, 1)");

		info("Check content is scrollable");
		WebElement element2 = waitForAndGetElement(ELEMENT_TERM_CONDITION_CONTENT);
		String scroll = element2.getCssValue("overflow-y");
		info("Content is scrollable:" + scroll);
		assert scroll.equalsIgnoreCase("scroll");
	}

	/*
	 * == Display "Continue" button ==
	 * Case_ID: 79244
	 * - Launch the app for the first time (/port
	 * Check: - The screen "Terms and Conditions Agreement" is displayed.
			  - The button "Continue" is displayed disable by default
	 */
	@Test(groups="terms")
	public void test04_DisplayContinueButton(){

		info("Check Continue button is disable.");
		waitForAndGetElement(ELEMENT_CONTINUE_BUTTON_DISABLE);
	}

	/*
	 * == Activate the "Continue" button ==
	 * Case_ID: 79245
	 * - Launch the app for the first time (/port)
	 * - Check the checkbox: "I agree with these terms and conditions"
	 * Check: - The screen "Terms and Conditions Agreement" is displayed.
			  - The button "Continue" becomes enabled
	 */
	@Test(groups="terms")
	public void test05_ActivateContinueButton(){

		info("Check Continue button is disable.");
		waitForAndGetElement(ELEMENT_CONTINUE_BUTTON_DISABLE);

		info("Check the checkbox: I agree with these terms and conditions");
		check(ELEMENT_AGREEMENT_CHECKBOX, 2);

		info("Check Continue button is enabled.");
		waitForAndGetElement(ELEMENT_CONTINUE_BUTTON);
	}

	/*
	 * == Display the checkbox "I agree with these terms and conditions" ==
	 * Case_ID: 79246
	 * - Launch the app for the first time (/port)
	 * Check: - The screen "Terms and Conditions Agreement" is displayed.
			  - The checkbox: "I agree with these terms and conditions" is displayed in the bottom of the lightbox
	 */
	@Test(groups="terms")
	public void test06_DisplayCheckboxIAgreeWithTheseTermsAndConditions(){

		info("Display the checkbox: I agree with these terms and conditions");
		waitForAndGetElement(ELEMENT_AGREEMENT_CHECKBOX, 1,2);
	}

	/*
	 * == Not display Terms and Conditions screen for an Unlocked instance ==
	 * Case_ID: 79287
	 *  - Connect to the session with the submitted account
		- Click on the button "Remove this banner"
		- Input valid data
		- Click Submit
		- Down the server 
		- Start the server again
	 * Check: - The session is logged and unlocked
		- The screen Terms and conditions isn't displayed
	 */
	@Test(groups="pending")
	public void test07_NotDisplayTermsAndConditionsScreenForAnUnlockedInstance(){

	}

	/*
	 * == Not display the Term Conditions when the PLF is started in developer mode ==
	 * Case_ID: 79314
	 *  - Run the server with a command
			+ "start_eXo.bat --dev", for Windows
			+ "./start_eXo.sh --dev" for Linux
		- Open platform in browser
	 * Check: 	- The Intranet Homepage is displayed
				- The trial banner is not displayed in the footer of the page
				- The Term condition screen is not displayed 
	 */
	@Test(groups="pending")
	public void test08_NotDisplayTermConditionsWhenPLFIsStartedInDeveloperMode(){

	}

	/*
	 * == Display the Greeting screen ==
	 * Case_ID: 79262
	 *  - Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		- Input all mandatory fields
		- Click on the button "Submit"
	 * Check: 	- The "Greetings!" screen is displayed
				- A text message is displayed:  "You are almost done. Add your colleagues to your new social intranet and start collaborating together."
				- A button "Start" is displayed
	 */
	@Test(groups="terms")
	public void test09_DisplayGreetingScreen(){
		By ELEMENT_GREETINGS_SCREEN = By.xpath("//div[@id='Greetings']");
		By GREETING_TEXT_MESSAGE = By.xpath("//*[contains(text(),'You are almost done')]");


		info("Check Continue button is disable.");
		waitForAndGetElement(ELEMENT_CONTINUE_BUTTON_DISABLE);

		info("Check the checkbox: I agree with these terms and conditions");
		check(ELEMENT_AGREEMENT_CHECKBOX, 2);

		info("Click Continue button");
		click(ELEMENT_CONTINUE_BUTTON);

		accountSetupWithoutGreeting();
		info("Verify greeting screen");
		waitForAndGetElement(ELEMENT_GREETINGS_SCREEN);
		waitForAndGetElement(GREETING_TEXT_MESSAGE);
		waitForAndGetElement(ELEMENT_START_BUTTON);
	}

	/*
	 * == Not display the Greeting screen when the PLF is started in developer mode ==
	 * Case_ID: 79312
	 *  - Run the server with a command
		+ "start_eXo.bat --dev", for Windows
		+ "./start_eXo.sh --dev" for Linux
		- Open platform in browser
	 * Check: 	- The Greeting screen still not displayed 
	 */
	@Test(groups="pending")
	public void test10_NotDisplayGreetingScreenWhenPLFIsStartedInDeveloperMode(){

	}
}
