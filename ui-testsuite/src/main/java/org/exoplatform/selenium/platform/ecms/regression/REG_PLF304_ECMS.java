package org.exoplatform.selenium.platform.ecms.regression;

import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.SiteExplorer;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.ecms.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.ActionBar;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.createNewCssFile;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.uploadFile;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.createNewFile;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.createNewArticle;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.ELEMENT_NEWFILE_NAME_TEXTBOX;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.ELEMENT_NEWFILE_CONTENT_FRAME;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.ELEMENT_SAVE_CLOSE_BUTTON;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.ELEMENT_NEWFILE_LINK;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.deleteDocument;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.commons.lang3.RandomStringUtils;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class REG_PLF304_ECMS extends EcmsBase {
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

		info("Start REG_PLF304_ECMS_001");

		//goto Site Explorer
		info("Go to Site Explorer");
		goToSiteExplorer();

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
    type(ContentTemplate.ELEMENT_NEWFILE_NAME_TEXTBOX, RandomStringUtils.randomAlphanumeric(10), false);
    type(ELEMENT_NEWFILE_CATEGORY,RandomStringUtils.randomAlphanumeric(10), false);
    inputDataToFrame( ContentTemplate.ELEMENT_NEWFILE_CONTENT_FRAME ,RandomStringUtils.randomAlphanumeric(10));
    switchToParentWindow();
    click(ELEMENT_SAVE_CLOSE_BUTTON);
    pause(500);
    assert isTextPresent("Categories are wrong. Please select good categories");
    
    info("End REG_PLF304_ECMS_002");
  }
  
  /**
   * REG_PLF304_ECMS_004: Download attachment in Content Detail portlet
   * @Steps:
   * - Create a document with attachment
   * - Download attachment in Content Detail portlet
   * @see
   * - Download is ok
   */
  @Test
  public void REG_PLF304_ECMS_004() {
    String mockArticleData = RandomStringUtils.randomAlphanumeric(10);
    String mockAttachmentData = RandomStringUtils.randomAlphanumeric(10);
    String mockAttachmentContent = mockAttachmentData + " content";
    
    String elementMockAttachmentData = "//a[contains(text(),\""+ mockAttachmentData +  "\")]";
    
    info("Start REG_PLF304_ECMS_004");

    //goto Site Explorer
    info("Go to Site Explorer");
    goToSiteExplorer();

    // Go to document of acme file
    info("Go to acme document folder");
    goToNodeByPath(ACME_DOCUMENT_FOLDER);
    
    info("Add new article");
    goToAddNewContent();
    createNewArticle( mockArticleData, mockArticleData, mockArticleData, mockArticleData);
    click(ActionBar.ELEMENT_PUBLICATION_TAB);
    SiteExplorer.publishDocument();
    
    // Create a css file
    info ("Create css file");
    goToAddNewContent();
    click(CSS_FILE_LINK);
    createNewCssFile(mockAttachmentData, CSS_PRIORITY , mockAttachmentContent);
    
    
    //Go to ACME Home Page
    info("Go to ACME Home Page");
    goToOverView();
    
    info("Create new page with SCV");
    PageEditor.addSCVPageAndContentFolderPaths(RandomStringUtils.randomAlphanumeric(10), "General Drives/Sites Management/"+ ACME_DOCUMENT_FOLDER + "/" + mockArticleData );    
    pause (500);
    
    click(By.xpath(elementMockAttachmentData));
    pause (500);
    assert isTextPresent(mockAttachmentContent);
    
    info("End REG_PLF304_ECMS_004");
    
  }
  
  
  private void improveGoToNodebyPath(String path){
    By ELEMENT_ADDRESS_BAR = By.xpath("//input[@name='address']");

    waitForElementPresent(ELEMENT_ADDRESS_BAR);
    type(ELEMENT_ADDRESS_BAR, path, true);
    actions.sendKeys(Keys.RETURN).perform();
    pause(500);
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
