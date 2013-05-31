package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.createNewArticle;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.createNewCssFile;

import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.ecms.ActionBar;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.SiteExplorer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.commons.lang3.RandomStringUtils;

public class REG_PLF304_ECMS_004 extends EcmsBase {
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
}
