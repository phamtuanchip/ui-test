package org.exoplatform.selenium.platform.ecms.regression;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.commons.lang3.RandomStringUtils;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.createNewCssFile;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.deleteDocument;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;

import org.exoplatform.selenium.platform.ecms.EcmsBase;

public class REG_PLF304_ECMS_001 extends EcmsBase {

  /*-- Data for these test cases --*/
  final public String DATA_USER = "john";
  final public String DATA_PASS = "gtn";
  private final String ACME_DOCUMENT_FOLDER = "acme/documents";
  private By CSS_FILE_LINK = By.linkText("CSS File");
  private String CSS_PRIORITY = "10";

  @BeforeMethod
  public void beforeMethods(){
    initSeleniumTest();
    driver.get(baseUrl);
    actions = new Actions(driver);
    info("Login ECMS with " + DATA_USER);
    loginEcms(DATA_USER, DATA_PASS);
  }

  @AfterMethod
  public void afterMethods(){
    info("Logout ECMS");
    driver.quit();
    actions = null;
  }
  /**
   * REG_PLF304_ECMS_001: Check life cycle in Manage Publications
   * @Steps:
   * - Create css file 
   * - Select this document
   * - Select another lifecycle other than Authoring
   * @see
   * - Authoring lifecycle is not always used
   * 
   */
  
  @Test
  public void REG_PLF304_ECMS_001() {
    String CSS_TITLE = RandomStringUtils.randomAlphanumeric(7);
    String CSS_CONTENT = "body {color:red;}";
    By ELEMENT_CSS = By.linkText(CSS_TITLE);
    By CHOOSE_PUBLICATION_LIFECYCLE =  By.xpath("(//img[@alt='Enrol'])[2]");
    By COMPLETE_PUBLICATION = By.xpath("(//span[contains(text(),'Manage Publication' )])");
    By OBSOLETE_STATUS = By.xpath("//a[contains(text(),'Obsolete')]");
    By ELEMENT_PUBLICATION_TAB= By.xpath("//a[contains(text(),'Publications')]");
    By ELEMENT_CLOSE_BUTTON = By.xpath("//a[contains(text(),'Close')]");
    By DRIVER_SITES_MANAGEMENT = By.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");

    info("Start REG_PLF304_ECMS_001");

    //goto Site Explorer
    info("Go to Site Explorer");
    goToSiteExplorer();

    info("Choose Management Sites");
    chooseDrive(DRIVER_SITES_MANAGEMENT);
    
    // Go to document of acme file
    info("Go to acme document folder");
    goToNodeByPath(ACME_DOCUMENT_FOLDER);
    //pause(1000);


    // Create a css file
    info ("Create css file");
    goToAddNewContent();
    click(CSS_FILE_LINK);
    createNewCssFile(CSS_TITLE, CSS_PRIORITY, CSS_CONTENT);

    // Publish CSS file
    info("Publish css file");
    waitForElementPresent(ELEMENT_CSS);
    click(ELEMENT_CSS);
    waitForElementPresent(ELEMENT_PUBLICATION_TAB);
    click(ELEMENT_PUBLICATION_TAB);

    //Choose Stage and Base lifecycle
    waitForElementPresent(CHOOSE_PUBLICATION_LIFECYCLE);
    click(CHOOSE_PUBLICATION_LIFECYCLE);


    // Verify Stage and base
    waitForElementPresent(COMPLETE_PUBLICATION);
    
    assert isElementPresent(OBSOLETE_STATUS);
    click(ELEMENT_CLOSE_BUTTON);
    deleteDocument(ELEMENT_CSS);
    info("End REG_PLF304_ECMS_001");
  }
}
