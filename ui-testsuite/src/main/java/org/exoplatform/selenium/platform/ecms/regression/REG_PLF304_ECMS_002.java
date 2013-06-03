package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.openManageTemplateForm;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.commons.lang3.RandomStringUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.exoplatform.selenium.platform.ecms.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.EcmsBase;

public class REG_PLF304_ECMS_002 extends EcmsBase {
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
   * REG_PLF304_ECMS_002: Check Category validation
   * @Steps:
   * - Modify dialog of template “File”
   * - Input invalid category
   * @see
   * - A message says that the category is wrong.
   * 
   */
  
  @Test
  public void REG_PLF304_ECMS_002() {

    By NUMBER_OF_ELEMENTS = By.xpath("//select[starts-with(@id, 'maxPageSize_')]");
    By ELEMENT_DIALOG = By.xpath("//div[@class='MiddleTab' and contains(text(),'Dialog')]");
    By TEXT_AREA = By.xpath("//textarea[@id='content']");
    By ELEMENT_SAVE_BUTTON = By.xpath("//a[contains(text(),'Refresh') and @class[contains(.,'ActionButton')]]/parent::div//a[contains(text(),'Save')]");
    By ELEMENT_NEWFILE_CATEGORY = By.xpath("//input[@id='categories0']");
    
    info("Start REG_PLF304_ECMS_002");

    // Go to Content Administration
    openManageTemplateForm();

    // Increase Max template to 100
    info("Select 100 items per pages");
    select(NUMBER_OF_ELEMENTS,"100");
    waitForTextPresent("nt:file");

    //Edit nt file
    info("Edit nt:file");
    By locator = By.xpath("//div[@class='Text' and contains(text(),'nt:file')]/ancestor::tr//img[@class='EditIcon']");
    click(locator);
    waitForElementPresent(ELEMENT_DIALOG);

    info("Switch to tab view");
    click(ELEMENT_DIALOG);

    // Edit dialog
    info ("Edit dialog");
    waitForElementPresent(By.xpath("//div[@class='Text' and contains(text(),'dialog1')]"));
    By locator1 = By.xpath("//div[@class='Text' and contains(text(),'dialog1')]/ancestor::tr//img[@class='EditIcon']");
    click(locator1);
    String content = readTextFile(getAbsoluteFilePath("TestData/REG_PLF_304_ECMS/resources/dialog1"));
    setClipboardContents(content);
    
    WebElement element = waitForAndGetElement(TEXT_AREA, 5000, 0);
    element.clear();
    element.click();
    element.sendKeys(Keys.CONTROL + "v");
    click(ELEMENT_SAVE_BUTTON);
    pause(500);
    
    
    //goto Site Explorer
    info("Go to Site Explorer");
    goToSiteExplorer();
    
    // Go to document of acme file
    info("Go to acme document folder");
    goToNodeByPath(ACME_DOCUMENT_FOLDER);
    //pause(1000);


    // Create a css file
    info ("Create nt:file");
    goToAddNewContent();
    click(ContentTemplate.ELEMENT_NEWFILE_LINK);
    waitForElementPresent(ContentTemplate.ELEMENT_NEWFILE_NAME_TEXTBOX);
    type(ContentTemplate.ELEMENT_NEWFILE_NAME_TEXTBOX, RandomStringUtils.randomAlphanumeric(10), false);
    type(ELEMENT_NEWFILE_CATEGORY,RandomStringUtils.randomAlphanumeric(10), false);
    inputDataToFrame( ContentTemplate.ELEMENT_NEWFILE_CONTENT_FRAME ,RandomStringUtils.randomAlphanumeric(10));
    switchToParentWindow();
    click(ELEMENT_SAVE_CLOSE_BUTTON);
    pause(500);
    assert isTextPresent("Categories are wrong. Please select good categories");
    info("End REG_PLF304_ECMS_002");
  }
  
  public String readTextFile(String fileName) {

    String returnValue = "";
    FileReader file = null;

    try {
      file = new FileReader(fileName);
      BufferedReader reader = new BufferedReader(file);
      String line = "";
      while ((line = reader.readLine()) != null) {
        returnValue += line + "\n";
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      if (file != null) {
        try {
          file.close();
        } catch (IOException e) {
        }
      }
    }
    return returnValue;
  }
  
  public static void setClipboardContents(String text) {
    StringSelection stringSelection = new StringSelection( text );
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, null);
  }
}
