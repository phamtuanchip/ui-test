package org.exoplatform.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * <li> This class provides simple way to manage all type of Alert in PLF4 </li>
 * @author vuna2
 * @Date January, 30th, 2013
 *
 */
public class ManageAlert extends TestBase{

	public ManageAlert(WebDriver dr) {
		driver = dr;
	}
	
	Button button = new Button(driver);

	//ECMS > Symlink
	public final By ELEMENT_ALERT = By.xpath("//*[contains(@class, 'popupTitle') and contains(text(), 'Warning')]");
	public final By ELEMENT_MESSAGE = By.xpath("//*[contains(@class, 'warningIcon')]");
	public final By ELEMENT_INFO = By.xpath("//*[contains(@class, 'infoIcon')]");

	//Accept an alert
	public void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			switchToParentWindow();
		} catch (NoAlertPresentException e) {
		}
		Utils.pause(1000);
	}

	//Cancel an Alert
	public void cancelAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			switchToParentWindow();
		} catch (NoAlertPresentException e) {
		}
		Utils.pause(1000);
	}

	//Get Text
	public String getTextFromAlert() {
		Utils.pause(1000);
		try {
			Alert alert = driver.switchTo().alert();
			return alert.getText();
		} catch (NoAlertPresentException e) {
			return "";
		}
	}

	//Accept a confirmation
	public void waitForConfirmation(String confirmationText,int...wait) {
		String message = getTextFromAlert();
		int timeOut = wait.length > 0 ? wait[0] : DEFAULT_TIMEOUT;

		//log("confirmation: " + message);
		if (message.isEmpty()) {
			if (loopCount > timeOut/500) {
				Assert.fail("Message is empty");
			}
			Utils.pause(500);
			loopCount++;
			waitForConfirmation(confirmationText);
			return;
		}

		for (int second = 0;; second++) {
			if (second >= timeOut) {
				Assert.fail("Timeout at waitForConfirmation: " + confirmationText);
			}
			if (message.contains(confirmationText)) {
				break;
			}
			
			Utils.pause(100);
		}
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Utils.pause(3000);
	}

	//Verify a message
	public void verifyAlertMessage(String message){
		Utils.pause(1000);
		//waitForElementPresent(ELEMENT_ALERT);
		//assert isElementPresent(ELEMENT_ALERT):"Not found alert";
		if (isElementPresent(ELEMENT_MESSAGE)){
			assert getText(ELEMENT_MESSAGE).contains(message):"Message is wrong";
		}else if (isElementPresent(ELEMENT_INFO)){
			assert getText(ELEMENT_INFO).contains(message):"Message is wrong";	
		}
		if (waitForAndGetElement(button.ELEMENT_OK_BUTTON, 3000, 0) != null){
			click(button.ELEMENT_OK_BUTTON);
		}
		Utils.pause(1000);
	}
	
	//Input text
	public void inputAlertText(String text){
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(text);
			alert.accept();
			switchToParentWindow();
		} catch (NoAlertPresentException e) {
		}
		Utils.pause(1000);
	}
}