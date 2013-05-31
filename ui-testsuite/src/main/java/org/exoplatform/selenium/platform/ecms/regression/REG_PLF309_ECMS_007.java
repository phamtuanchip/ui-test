package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestBase.actions;
import static org.exoplatform.selenium.TestBase.baseUrl;
import static org.exoplatform.selenium.TestBase.driver;
import static org.exoplatform.selenium.TestBase.initSeleniumTest;
import static org.exoplatform.selenium.TestBase.pause;
import static org.exoplatform.selenium.TestBase.mouseOverAndClick;
import static org.exoplatform.selenium.TestBase.rightClickOnElement;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.uploadFile;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToNodeByPath;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToSiteExplorer;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.loginEcms;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF309_ECMS_007 {

	/*-- Data for these test cases --*/
	final public String DATA_USER = "john";
	final public String DATA_PASS = "gtn";

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with " + DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout ECMS");
		driver.quit();
		actions = null;
	}

	/**
	 * Step 1: - Go to Content Explorer - Upload new file Step 2: - Right click on
	 * uploaded file and select Download And Allow Edition EXPECTED: File is
	 * uploaded successfully successfully The link should be shown:
	 * http://localhost:8080/rest/private/jcr/repository/collaboration ….
	 */

	@Test
	public void CheckWebDavLink() {
		By DRIVER_SITES_MANAGEMENT = By
				.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");
		String ACME_DOCUMENT_FOLDER = "acme/documents";
		String fileName = "REG_PLF309_ECMS_007_file.png";
		By ELEMENT_UPLOADED = By.xpath("//div[@title='" + fileName + "']");
		By ELEMENT_DOWNLOAD_AND_ALLOW_EDITION = By
				.xpath("//div[contains(text(),'Download And Allow Edition')]");
		String webDavLink = "http://localhost:8080/rest/private/jcr/repository/collaboration/sites%20content/live/acme/documents/"
				+ fileName;

		info("Go to Site Explorer");
		goToSiteExplorer();

		// Choose Management Sites
		info("Choose Management Sites");
		chooseDrive(DRIVER_SITES_MANAGEMENT);

		info("Go to acme document folder");
		goToNodeByPath(ACME_DOCUMENT_FOLDER);
		info("Upload file");
		uploadFile(fileName, "TestData/" + fileName);
	  info("Right click on the uploaded file");
		rightClickOnElement(ELEMENT_UPLOADED);
		pause(1000);
		info("Get the current url of the new window");
		Set<String> beforePopup = driver.getWindowHandles();
		// click which causes to open new window
		driver.findElement(ELEMENT_DOWNLOAD_AND_ALLOW_EDITION).click();
		// get all the window handles after the popup window appears
		Set<String> afterPopup = driver.getWindowHandles();
		// remove all the handles from before the popup window appears
		afterPopup.removeAll(beforePopup);
		// there should be only one window handle left
		if (afterPopup.size() == 1) {
			driver.switchTo().window((String) afterPopup.toArray()[0]);
		}
		info("Get the webdav url");
		String currentUrl = driver.getCurrentUrl();
		info("WebDav link: " + currentUrl);

		info("Verify the form of webdav link");
		Assert.assertTrue(currentUrl.equals(webDavLink));

	}

}
