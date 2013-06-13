package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.basicaction;

import static org.exoplatform.selenium.TestLogger.info;
import java.util.List;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*;

/*
 * @author: Lientm
 * @date: 24/09/2012
 */
public class ECMS_DMS_SE_BasicAction_AddSymlink extends EcmsBase{

	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";
	public static final By ELEMENT_SELECT_PATH_DOCUMENTS = By.xpath("//div[contains(text(),'Documents')]/../../td/a/div[@class='Select16x16Icon']");
	public static final By ELEMENT_LIVE = By.xpath("//a[@title='Live ']");

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with "+DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() {
//		info("Logout ECMS");
//		logoutEcms();
		driver.manage().deleteAllCookies();
		driver.quit();
		actions = null;
	}
	  
	  /*case01: Open form to add Symlink for node using icon in action bar
	   * set view add symlink
	   * go to add symlink
	   * check form
	   */
	  @Test
	  public void test01_CheckFormAddSymlinkForNode(){		  
		  //set view add symlink
		  setup2ShowViewSymlink();	
		  
		  //go to add symlink
		  info("Add symlink for acme node");
		  goToSiteExplorer();
		  goToNode(ELEMENT_SIDEBAR_ACME);
		  mouseOverAndClick(ELEMENT_COLLABORATION);
		  click(ELEMENT_ADD_SYMLINK);
		  
		  //check Add symlink form
		  waitForElementPresent(ELEMENT_ADD_SYMLINK_POPUP);
		  assert isElementPresent(ELEMENT_ADD_SYMLINK_POPUP):"Not found Add symlink form";
		  assert isTextPresent("Symlink Manager"):"Add symlink form is not true";
		  assert isElementPresent(ELEMENT_PATH_NODE):"Add symlink form is not true";
		  assert isElementPresent(ELEMENT_SYMLINK_NAME):"Add symlink form is not true";	  
		  info("Check add symlink popup successfully");
	  }
	  
	  /*case02: Add Symlink when right click on node but do not select this node
	   * go to acme
	   * right click on document -> add symlink
	   * check symlink
	   */
	  @Test(groups={"pending"})
	  public void test02_AddSymlinkWhenRightClick(){
		  String DATA_FOLDER = "ECMS_DMS_SE_BasicAction_AddSymlink_folder_02";
		  By ELEMENT_FOLDER = By.linkText(DATA_FOLDER);
		  By ELEMENT_SYMLINK = By.xpath("//a[text()='" + DATA_FOLDER + ".lnk']");
		  
		  //create node: content folder
		  goToSiteExplorer();
		  createNewContentFolder(DATA_FOLDER, DATA_FOLDER);
		  waitForElementPresent(ELEMENT_FOLDER);
		  info("Create new content folder successfully");
		  
		  // add symlink
		  info("Add symlink for node");
		  rightClickOnElement(ELEMENT_FOLDER);
		  click(ELEMENT_MENU_ADD_SYMLINK);
		  
		  // check symlink
		  waitForElementPresent(ELEMENT_SYMLINK);
		  assert isElementPresent(ELEMENT_SYMLINK):"Add new symlink unsuccessfully";
		  info("Add symlink for documents successful");
		  
		  //delete symlink
		  deleteData(ELEMENT_FOLDER);
	  }
	  
	  /*case03: Add Symlink for some nodes at the same time
	   * go to acme
	   * choose 2 node
	   * add symlink
	   * check symlink
	   */
//	  @Test(groups={"pending"})
//	  public void test03_AddSymlinkForSomenode(){
//		  By CATEGORIES = By.xpath("//div[@title='categories ']");
//		  By CATEGORIES_SYMLINK = By.xpath("//div[@title='categories.lnk ']");
//		  By CSS = By.xpath("//div[@title='css ']");
//		  By CSS_SYMLINK = By.xpath("//div[@title='css.lnk ']");
//
//		  //go to acme
//		  goToSiteExplorer();
//		  goToNode(ELEMENT_ACME);
//
//		  //choose 2 node
//		  WebElement ELEMENT_CATEGORIES = waitForAndGetElement(CATEGORIES);
//		  WebElement ELEMENT_CSS = waitForAndGetElement(CSS);
//		  //actions.keyDown(Keys.CONTROL).click(ELEMENT_CATEGORIES).keyDown(Keys.SHIFT).click(ELEMENT_CSS).keyUp(Keys.CONTROL).build().perform();
//		  //click(CATEGORIES);
//		  actions.keyDown(Keys.CONTROL).click(ELEMENT_CSS).release().perform();
//		  //actions.keyDown(Keys.CONTROL).moveToElement(ELEMENT_CATEGORIES).click().moveToElement(ELEMENT_CSS).click().keyUp(Keys.CONTROL).build().perform();
//
//		  // add symlink
//		  rightClickOnElement(CATEGORIES);
//		  click(ELEMENT_ADD_SYMLINK);
//		  //check symlink
//		  waitForElementPresent(CATEGORIES_SYMLINK);
//		  assert isElementPresent(CATEGORIES_SYMLINK):"Add symlink for categories unsuccessfully";
//		  waitForElementPresent(CSS_SYMLINK);
//		  assert isElementPresent(CSS_SYMLINK):"Add symlink for css unsuccessfully";
//		  info("Add symlink for categories and symlink successfully");
//		  //delete symlink
//		  goToNode(CATEGORIES_SYMLINK);
//		  deleteDocument(CATEGORIES_SYMLINK);
//		  waitForElementNotPresent(CATEGORIES_SYMLINK);
//		  goToNode(CSS_SYMLINK);
//		  deleteDocument(CSS_SYMLINK);
//		  waitForElementNotPresent(CSS_SYMLINK);
//	  }
	  
	  /*case04: Add Symlink for root
	   * go to site explorer
	   * add symlink
	   */
	  @Test
	  public void test04_AddSymlinkForRoot(){	
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_04";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  
		  //go to add symlink
		  goToSiteExplorer();
		  goToNode(ELEMENT_COLLABORATION_TAB);
		  
		  //add symlink for root
		  info("Add symlink for root");
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS, DATA_SYMLINK);
		  
		  //check symlink
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  click(ELEMENT_DOCUMENT_SYMLINK);
		  waitForElementPresent(ELEMENT_LIVE);
		  assert isElementPresent(ELEMENT_LIVE):"Add symlink for documents node unsuccessfully";
		  info("Add symlink for document node successfully");
		  
		  //delete symlink
		  deleteData(ELEMENT_DOCUMENT_SYMLINK);
	  }
	  
	  /*case05: Add Symlink for node is Content Folder
	   * create new content folder
	   * add symlink for node with target node = documents
	   * check symlink
	   */
	  @Test
	  public void test05_AddSymlinkForContentFolder(){
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_05";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER);
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_05";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  
		  //create new content folder
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //add symlink
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  goToNode(ELEMENT_COLLABORATION);
		  info("Add symlink for content folder");
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  assert isElementPresent(ELEMENT_DOCUMENT_SYMLINK):"Add symlink for content folder unsuccessfully";
		  
		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER);
	  }
	  
	  /*case06: Add Symlink for node is Document Folder
	   * create new document folder
	   * add symlink for node with target node = documents
	   * check symlink
	   */
	  @Test
	  public void test06_AddSymlinkForDocumentFolder(){
		  String DATA_DOCUMENT_FOLDER = "ECMS_DMS_SE_Addsymlink_documentfolder_06";
		  By ELEMENT_DOCUMENT_FOLDER = By.linkText(DATA_DOCUMENT_FOLDER);
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_06";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  
		  //create new document folder
		  goToSiteExplorer();
		  info("Create new document folder");
		  createNewDocumentFolder(DATA_DOCUMENT_FOLDER, DATA_DOCUMENT_FOLDER);
		  waitForElementPresent(ELEMENT_DOCUMENT_FOLDER);
		  assert isElementPresent(ELEMENT_DOCUMENT_FOLDER):"Create new document folder unsuccessfully";
		  info("Create new document folder successfully");
		  
		  //add symlink
		  goToNode(ELEMENT_DOCUMENT_FOLDER);
		  goToNode(ELEMENT_COLLABORATION);
		  info("Add symlink for document folder");
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  goToNode(ELEMENT_DOCUMENT_FOLDER);
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  assert isElementPresent(ELEMENT_DOCUMENT_SYMLINK):"Add symlink for document folder unsuccessfully";
		  
		  //delete data
		  deleteData(ELEMENT_DOCUMENT_FOLDER);
	  }
	  
	  /*case07: Add Symlink for node is Article document
	   * create new article document
	   * add symlink to article
	   * check cannot add symlink
	   */
	  @Test
	  public void test07_AddSymlinkForArticleDocument(){
		  String DATA_ARTICLE = "ECMS_DMS_SE_Addsymlink_article_07";
		  By ELEMENT_ARTICLE = By.linkText(DATA_ARTICLE);
		  
		  //create new article document
		  goToSiteExplorer();
		  goToAddNewContent();
		  info("Create new article document");
		  createNewArticle(DATA_ARTICLE, DATA_ARTICLE, "", "");
		  waitForElementPresent(ELEMENT_ARTICLE);
		  assert isElementPresent(ELEMENT_ARTICLE):"Create new article unsuccessfully";
		  info("Create new article successfully");
		  
		  info("Check not having Add Symlink in action bar for article");
		  goToNode(ELEMENT_ARTICLE);
		  goToNode(ELEMENT_COLLABORATION);
		  waitForElementNotPresent(ELEMENT_ADD_SYMLINK);
		  
		  //delete data
		  deleteData(ELEMENT_ARTICLE);
	  }
	  
	  /*case08: Add Symlink for node is File document
	   * create new file document
	   * add symlink to file document
	   * check add successfully
	   * --> update follow issue ECMS-3541 -> can not add symlink into file document
	   */
	  @Test
	  public void test08_AddSymlinkForFileDocument(){
		  String DATA_FILE_DOCUMENT = "ECMS_DMS_SE_Addsymlink_filedocument_08";
		  By ELEMENT_FILE_DOCUMENT = By.linkText(DATA_FILE_DOCUMENT);
		  //String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_08";
		  //By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  
		  //create new file document
		  goToSiteExplorer();
		  goToAddNewContent();
		  info("Create new file document");
		  createNewFile(DATA_FILE_DOCUMENT, DATA_FILE_DOCUMENT, "");
		  waitForElementPresent(ELEMENT_FILE_DOCUMENT);
		  assert isElementPresent(ELEMENT_FILE_DOCUMENT):"Create new file document unsuccessfully";
		  info("Create new file document successfully");
		  
		  //add symlink
		  goToNode(ELEMENT_FILE_DOCUMENT);
		  goToNode(ELEMENT_COLLABORATION);
		  waitForElementNotPresent(ELEMENT_ADD_SYMLINK);
//		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
//		  goToNode(ELEMENT_FILE_DOCUMENT);
//		  info("Add symlink for file document");
//		  checkPreferenceOption("enableStructure");
//		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
//		  assert isElementPresent(ELEMENT_DOCUMENT_SYMLINK):"Add symlink for file document unsuccessfully";
//		  
		  //delete data
		  deleteData(ELEMENT_FILE_DOCUMENT);
	  }
	  
	  /*case09: Add Symlink for node is Podcast  document
	   * create new podcast document
	   * add symlink
	   * check add successfully
	   */
	  @Test
	  public void test09_AddSymlinkForPodcastDocument(){
		  String DATA_PODCAST_DOCUMENT = "ECMS_DMS_SE_Addsymlink_podcast_09";
		  By ELEMENT_PODCAST_DOCUMENT = By.linkText(DATA_PODCAST_DOCUMENT);
//		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_09";
//		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  
		  //create new podcast document
		  goToSiteExplorer();
		  goToAddNewContent();
		  info("Create new podcast document");
		  createNewPodcast(DATA_PODCAST_DOCUMENT, DATA_PODCAST_DOCUMENT, DATA_PODCAST_DOCUMENT);
		  waitForElementPresent(ELEMENT_PODCAST_DOCUMENT);
		  assert isElementPresent(ELEMENT_PODCAST_DOCUMENT):"Create new podcast document unsuccessfully";
		  info("Create new podcast document successfully");
		  
		  //add symlink
		  goToNode(ELEMENT_PODCAST_DOCUMENT);
		  goToNode(ELEMENT_COLLABORATION);
		  waitForElementNotPresent(ELEMENT_ADD_SYMLINK);
//		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
//		  goToNode(ELEMENT_PODCAST_DOCUMENT);
//		  info("Add symlink for podcast document");
//		  checkPreferenceOption("enableStructure");
//		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
//		  assert isElementPresent(ELEMENT_DOCUMENT_SYMLINK):"Add symlink for podcast document unsuccessfully";
//		  
		  //delete data
		  deleteData(ELEMENT_PODCAST_DOCUMENT);
	  }
	  
	  /*case10: Add Symlink for node is Sample Node document
	   * create new sample node
	   * add symlink
	   * check cannot add symlink
	   */
	  @Test
	  public void test10_AddSymlinkForSampleNode(){
		  String DATA_SAMPLE = "ECMS_DMS_SE_Addsymlink_sample_10";
		  By ELEMENT_SAMPLE = By.linkText(DATA_SAMPLE);
		  
		  //create new sample document
		  goToSiteExplorer();
		  goToAddNewContent();
		  info("Create new sample node");
		  createNewSampleNode(DATA_SAMPLE, DATA_SAMPLE, "");
		  waitForElementPresent(ELEMENT_SAMPLE);
		  assert isElementPresent(ELEMENT_SAMPLE):"Create new sample node unsuccessfully";
		  info("Create new sample node successfully");
		  
		  //add symlink
		  goToNode(ELEMENT_SAMPLE);
		  goToNode(ELEMENT_COLLABORATION);
		  info("Check cannot add symlink for sample node");
		  waitForElementNotPresent(ELEMENT_ADD_SYMLINK);
//		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,"Documents.lnk");
//		  checkAlertWarning("An error occurred while creating the symlink.");
//		  click(ELEMENT_CANCEL_BUTTON);
		  
		  //delete data
		  deleteData(ELEMENT_SAMPLE);
	  }
	  
	  /*case11: Add Symlink for node is File Plan document
	   * create new file plan document
	   * add symlink
	   * check add symlink successfully
	   */
	  @Test
	  public void test11_AddSymlinkForFilePlan(){
		  String DATA_FILE_PLAN = "ECMS_DMS_SE_Addsymlink_fileplan_11";
		  By ELEMENT_FILE_PLAN = By.linkText(DATA_FILE_PLAN);
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_11";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  
		  //create new file plan 
		  goToSiteExplorer();
		  goToAddNewContent();
		  info("Create new file plan");
		  createNewFilePlan(DATA_FILE_PLAN, DATA_FILE_PLAN, DATA_FILE_PLAN, DATA_FILE_PLAN, DATA_FILE_PLAN);
		  waitForElementPresent(ELEMENT_FILE_PLAN);
		  assert isElementPresent(ELEMENT_FILE_PLAN):"Create new file plan unsuccessfully";
		  info("Create new file plan successfully");
		  
		  //add symlink
		  goToNode(ELEMENT_FILE_PLAN);
		  goToNode(ELEMENT_COLLABORATION);
		  info("Add symlink for file plan");
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  goToNode(ELEMENT_FILE_PLAN);
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  assert isElementPresent(ELEMENT_DOCUMENT_SYMLINK):"Add symlink for file plan unsuccessfully";
		  
		  //delete data
		  deleteData(ELEMENT_FILE_PLAN);
	  }
	  
	  /*case12: Add Symlink for node is Kofax document
	   * create new kofax
	   * add symlink
	   * check add successful
	   */
	  @Test
	  public void test12_AddSymlinkForKofax(){
		  String DATA_KOFAX = "ECMS_DMS_SE_Addsymlink_kofax_12";
		  By ELEMENT_KOFAX = By.linkText(DATA_KOFAX);
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_12";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  
		  //create new kofax document
		  goToSiteExplorer();
		  goToAddNewContent();
		  info("Create new kofax document");
		  createNewKofax(DATA_KOFAX);
		  waitForElementPresent(ELEMENT_KOFAX);
		  assert isElementPresent(ELEMENT_KOFAX):"Create new kofax unsuccessfully";
		  info("Create new kofax successfully");
		  
		  //add symlink
		  goToNode(ELEMENT_KOFAX);
		  goToNode(ELEMENT_COLLABORATION);
		  info("Add symlink for kofax document");
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  goToNode(ELEMENT_KOFAX);
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  assert isElementPresent(ELEMENT_DOCUMENT_SYMLINK):"Add symlink for kofax unsuccessfully";
		  info("Add symlink for kofax document successfully");
		  
		  //delete data
		  deleteData(ELEMENT_KOFAX);
	  }
	  
	  /*case13: Add Symlink for node is uploaded file
	   * upload file
	   * add symlink
	   * check add symlink successfully
	   */
	  @Test
	  public void test13_AddSymlinkForUploadedFile(){
		  String DATA_UPLOAD = "ECMS_DMS_SE_Addsymlink_upload_13";
		  String DATA_UPLOAD_FILE_LINK = "TestData/ECMS_DMS_SE_Addsymlink.jpg";
		  By ELEMENT_UPLOAD_FILE = By.xpath("//a[@title='"+DATA_UPLOAD+".jpg "+"']");
//		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_13";
//		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  
		  //create new kofax document
		  goToSiteExplorer();
		  info("upload new file");
		  uploadFile(DATA_UPLOAD, DATA_UPLOAD_FILE_LINK);
		  waitForElementPresent(ELEMENT_UPLOAD_FILE);
		  assert isElementPresent(ELEMENT_UPLOAD_FILE):"Create new kofax unsuccessfully";
		  info("Create new kofax successfully");
		  
		  //add symlink
		  goToNode(ELEMENT_UPLOAD_FILE);
		  goToNode(ELEMENT_COLLABORATION);
		  waitForElementNotPresent(ELEMENT_ADD_SYMLINK);
//		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
//		  goToNode(ELEMENT_UPLOAD_FILE);
//		  info("Add symlink for uploaded file");
//		  checkPreferenceOption("enableStructure");
//		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
//		  assert isElementPresent(ELEMENT_DOCUMENT_SYMLINK):"Add symlink for kofax unsuccessfully";
//		  info("Add symlink for kofax document successfully");
		  
		  //delete data
		  deleteData(ELEMENT_UPLOAD_FILE);
	  }
	  
	  /*case14: Add Symlink for node is Symlink
	   * create new node - content folder
	   * add symlink for this node
	   * add symlink for symlink node
	   * check add successfully
	   */
	  @Test
	  public void test14_AddSymlinkForSymlinkNode(){
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_14";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER);
		  By ELEMENT_SELECT_PATH_USER = By.xpath("//div[contains(text(),'Users')]/../../td/a/div[@class='Select16x16Icon']");
		  By ELEMENT_USER_SYMLINK = By.linkText("Users.lnk");
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_14";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  
		  
		  //create new content folder
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //add symlink for content folder
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  goToNode(ELEMENT_COLLABORATION);
		  info("Add symlink for content folder");
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  assert isElementPresent(ELEMENT_DOCUMENT_SYMLINK):"Add symlink for content folder unsuccessfully";
		  info("Add symlink for content folder successfully");
		  
		  //add symlink for symlink node
		  goToNode(ELEMENT_DOCUMENT_SYMLINK);
		  goToNode(ELEMENT_COLLABORATION);
		  info("Add symlink for exiting symlink");
		  addSymlink(ELEMENT_SELECT_PATH_USER,"Users.lnk");
		  goToNode(ELEMENT_DOCUMENT_SYMLINK);
		  waitForElementPresent(ELEMENT_USER_SYMLINK);
		  assert isElementPresent(ELEMENT_USER_SYMLINK):"Add symlink for symlink unsuccessfully";
		  info("Add symlink for symlink node successfully");
		  
		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER);
	  }
	  
	  /*case15: Add Symlink for node to the node is Symlink
	   * add symlink for acme/documents -> documents.lnk
	   * add symlink for acme  
	   * check cannot found target node is symlink documents.lnk
	   */
	  @Test
	  public void test15_AddSymlinkForNodeToSymlinkNode(){
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_15";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER);
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText("documents.lnk");
		  By ELEMENT_SYMLINK = By.xpath("//div[@title='documents.lnk' and @class='NodeIcon  exo_documentFolder16x16Icon default16x16Icon']");
		  
		  //create new node - content folder
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //create symlink for acme/document
		  click(ELEMENT_SIDEBAR_ACME);
		  info("Add symlink for documents");
		  rightClickOnElement(ELEMENT_SIDEBAR_ACME_DOCUMENTS);
		  waitForElementPresent(ELEMENT_MENU_ADD_SYMLINK);
		  click(ELEMENT_MENU_ADD_SYMLINK);
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  assert isElementPresent(ELEMENT_DOCUMENT_SYMLINK):"Add new symlink unsuccessfully";
		  info("Add symlink for documents successfully");
		   
		  //check cannot add symlink for node with target node is symlink documents.lnk
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  goToNode(ELEMENT_COLLABORATION);
		  goToTargetNodeInRoot();
		  usePaginator(ELEMENT_VIEWAREA_ACME,"cannot found acme node");
		  click(ELEMENT_VIEWAREA_ACME); 
		  
		  waitForElementNotPresent(ELEMENT_SYMLINK);
		  info("cannot select target node is a symlink node");
		  click(ELEMENT_CLOSE_WINDOW);
		  		  
		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER);
		  goToNode(ELEMENT_SIDEBAR_ACME);
		  deleteData(ELEMENT_DOCUMENT_SYMLINK);

	  }
	  
	  /*case16: Add Symlink when 'Symlink Name' field is blank
	   * create symlink for acme with name symlink blank
	   */
	  @Test
	  public void test16_AddSymlinkWithBlankName(){
		  //go to acme
		  goToSiteExplorer();
		  goToNode(ELEMENT_SIDEBAR_ACME);
		  
		  //add symlink with blank name
		  info("Add symlink with blank name");
		  goToNode(ELEMENT_COLLABORATION);
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS, "");
		  
		  //check alert
		  checkAlertWarning("The field \"Symlink Name\" is required.");
		  info("cannot add symlink with blank name");
	  }
	  
	  /*case17: Add Symlink when “Path Node” field is blank
	   * create symlink for acme with path node blank
	   */
	  @Test
	  public void test17_AddSymlinkWithBlankPathNode(){		  
		  //go to acme
		  goToSiteExplorer();
		  goToNode(ELEMENT_SIDEBAR_ACME);
		  
		  //add symlink with path node blank
		  info("Add symlink with blank path node");
		  goToNode(ELEMENT_COLLABORATION);
		  waitForElementPresent(ELEMENT_ADD_SYMLINK);
		  click(ELEMENT_ADD_SYMLINK);
		  waitForElementPresent(ELEMENT_ADD_SYMLINK_POPUP);
		  type(ELEMENT_SYMLINK_NAME,"Test",true);
		  click(ELEMENT_SAVE_BUTTON);
		  
		  //check alert
		  checkAlertInfo("Please enter the path node.");
		  info("cannot add symlink with blank path node");
	  }
	  
	  /*case18: Add Symlink when 'Symlink Name' field contains special characters like @, #,$..
	   * 
	   */
	  @Test(groups={"pending"})
	  public void test18_AddSymlinkWithNameContainsSpecialCharacter(){
		  //go to acme
		  goToSiteExplorer();
		  goToNode(ELEMENT_SIDEBAR_ACME);
		  
		  //add symlink with blank name
		  info("Add symlink with name contains special characters");
		  goToNode(ELEMENT_COLLABORATION);
		  for(int i=0;i< DATA_SPECIAL_CHARACTER.length; i++){
			  info("Input symlink name contains character: "+DATA_SPECIAL_CHARACTER[i]);
			  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS, DATA_SPECIAL_CHARACTER[i]);
			  //check alert
			  checkAlertWarning("An error occurred while creating the symlink.");
			  info("cannot add symlink with name contains qspecial characters");
			  click(ELEMENT_CANCEL_BUTTON);
		  }
	  }
	  
	  /*case19: Check the displaying of workspace when user dose not permission to view it when add Symlink 
	   * login with mary
	   * create new node - content folder in acme/document
	   * check cannot add symlink for this node with system workspace
	   */
	  @Test
	  public void test19_CheckDisplayWorkspaceWhenUserHasNotPermission(){
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_19";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER);
		  By ELEMENT_DMS_SYSTEM = By.xpath("//div[contains(text(),'exo:ecm')]/../../td/a/div[@class='Select16x16Icon']");
		  
		  
		  //login with mary
		  logoutEcms();
		  info("Login ECMS with user: mary");
		  loginEcms("mary", "gtn");
		  goToSiteExplorer();
		  
		  //create new node - content folder
		  info("create new content folder");
		  click(ELEMENT_SIDEBAR_ACME);
		  click(ELEMENT_SIDEBAR_ACME_DOCUMENTS);
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //check cannot add symlink for this node with system workspace
		  click(ELEMENT_CONTENT_FOLDER);
		  click(ELEMENT_COLLABORATION);
		  click(ELEMENT_ADD_SYMLINK);
		  waitForElementPresent(ELEMENT_ADD_SYMLINK_POPUP);
		  click(ELEMENT_ADD_ITEM);
		  selectOption(ELEMENT_SYMLINK_WORKSPACE, "dms-system");
		  click(ELEMENT_DMS_SYSTEM);
		  click(ELEMENT_SAVE_BUTTON);
		  
		  //check alert
		  checkAlertWarning("Access denied! You do not have the permission to add symlink to this node.");
		  info("cannot add symlink with user has not permission to view it");

		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER);
	  }
	  
	  /*case20: Check the displaying of node when user dose not permission to view it when add Symlink 
	   * login with john
	   * create new node - content folder
	   * set permission for this node: user mary does not have permission view node
	   * login with mary
	   * add symlink and choose target node
	   * check user does not see that content node
	   */
	  @Test
	  public void test20_CheckDisplayNodeWhenUserNotHaveViewPermission(){	  
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_20";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER);
		  By ELEMENT_CONTENT_DIV = By.xpath("//div[@title='"+DATA_CONTENT_FOLDER+"']");
		  
		  //create new content folder
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //set permission for this node: user mary does not have permission view node 
		  info("Set for user mary does not have view permission");
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  waitForElementPresent(ELEMENT_SYSTEM_TAB);
	      click(ELEMENT_SYSTEM_TAB);
	      waitForElementPresent(ELEMENT_PERMISSION_LINK);
	      click(ELEMENT_PERMISSION_LINK);
	      removeDefaultPermissionOfNode();
          click(ELEMENT_CLOSE_BUTTON);
          logoutEcms();
          
          //login with mary
          info("Login with mary");
          loginEcms("mary", "gtn");
		  goToSiteExplorer();
		  
		  //add symlink and choose target node
		  goToNode(ELEMENT_SIDEBAR_ACME);
		  goToNode(ELEMENT_SIDEBAR_ACME_DOCUMENTS);
		  info("Add symlink for document");
		  waitForElementPresent(ELEMENT_COLLABORATION, 10000);
		  goToNode(ELEMENT_COLLABORATION);
		  goToTargetNodeInRoot();
		  
		  //check user does not see that content node
//		  usePaginator(ELEMENT_CONTENT_DIV,"cannot found content folder node");
		  waitForElementNotPresent(ELEMENT_CONTENT_DIV);
		  info("cannot see node when user does not have view permission");
		  click(ELEMENT_CLOSE_WINDOW);
		  
		  //delete data
		  info("Delete content folder");
		  logoutEcms();
		  loginEcms(DATA_USER, DATA_PASS);
		  goToSiteExplorer();
		  deleteData(ELEMENT_CONTENT_FOLDER);
	  }
	  
	  /*case21: Add new Symlink has the same name with existing Symlink in Content Folder
	   * create new content folder
	   * add 2 symlink have same name for content folder
	   * check add successfully
	   */
	  @Test
	  public void test21_Add2SymlinksHaveSameNameInContentFolder(){
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_21";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER);
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_21";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);

		  //create new content folder
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //add 2 symlink have same name for content folder
		  info("Add 2 symlinks have same name in content folder");
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  goToNode(ELEMENT_COLLABORATION);
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  pause(1000);
		  WebElement e = waitForAndGetElement("//a[@title='" + DATA_CONTENT_FOLDER + " " + "']/../../../..//*[@class='NodeGroup']");		  
		  List<WebElement> document = e.findElements(ELEMENT_DOCUMENT_SYMLINK);
		  
		  assert (document.size()==2):"Add 2 symlink unsuccessfully";
		  info("Add 2 symlink same name in content folder successfully");

		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER);		  
	  }
	  
	  /*case22: Add new Symlink has the same name with existing Symlink in Document Folder
	   * create new document folder
	   * add 2 symlink have same name for document folder
	   * check cannot add
	   */
	  @Test
	  public void test22_Add2SymlinksHaveSameNameInDocumentFolder(){
		  String DATA_DOCUMENT_FOLDER = "ECMS_DMS_SE_Addsymlink_documentfolder_22";
		  By ELEMENT_DOCUMENT_FOLDER = By.linkText(DATA_DOCUMENT_FOLDER);
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_22";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);

		  //create new document folder
		  goToSiteExplorer();
		  info("Create new document folder with user john");
		  createNewDocumentFolder(DATA_DOCUMENT_FOLDER, DATA_DOCUMENT_FOLDER);
		  waitForElementPresent(ELEMENT_DOCUMENT_FOLDER);
		  assert isElementPresent(ELEMENT_DOCUMENT_FOLDER):"Create new document folder unsuccessfully";
		  info("Create new document folder successfully");
		  
		  //add 2 symlink have same name for document folder
		  info("Add 2 symlinks have same name in document folder");
		  goToNode(ELEMENT_DOCUMENT_FOLDER);
		  goToNode(ELEMENT_COLLABORATION);
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  
		  //check alert
		  checkAlertWarning("The node name already exists.");
		  info("cannot add 2 symlink same name in document folder");
		  
		  //delete data
		  deleteData(ELEMENT_DOCUMENT_FOLDER);		  
	  }
	  
	  /*case23: Add new Symlink has the same name with existing Symlink in document  
	   * create new document - file document
	   * check cannot add 2 symlinks have samve name in document
	   */
	  @Test
	  public void test23_Add2SymlinksHaveSameNameInDocument(){
		  String DATA_FILE_PLAN = "ECMS_DMS_SE_Addsymlink_fileplan_23";
		  By ELEMENT_FILE_PLAN = By.linkText(DATA_FILE_PLAN);
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_23";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);

		  //create new file document
		  goToSiteExplorer();
		  goToAddNewContent();
		  info("Create new file plan");
		  createNewFilePlan(DATA_FILE_PLAN, DATA_FILE_PLAN, DATA_FILE_PLAN, DATA_FILE_PLAN, DATA_FILE_PLAN);
		  waitForElementPresent(ELEMENT_FILE_PLAN);
		  info("Create new file document successfully");
		  
		  //add 2 symlink have same name for file document
		  info("Add 2 symlinks have same name in file document");
		  goToNode(ELEMENT_FILE_PLAN);
		  goToNode(ELEMENT_COLLABORATION);
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
//		  info("enable DMS");
//		  pause(100);
//		  checkPreferenceOption("enableStructure");
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  
		  //check alert
		  checkAlertWarning("The node name already exists.");
		  info("cannot add 2 symlink same name in file document");
		  
		  //delete data
		  deleteData(ELEMENT_FILE_PLAN);
	  }
	  
	  /*case24: Add new Symlink has the same name with existing Symlink in uploaded file
	   * upload new file
	   * check cannot add 2 symlinks have same name in uploaded file
	   * --> Update follow issue ECMS-3541 -> can not add symlink for upload file
	   */
//	  @Test
//	  public void test24_Add2SymlinksHaveSameNameInUploadFile(){
//		  String DATA_UPLOAD = "ECMS_DMS_SE_Addsymlink_uploadfile_24";
//		  String DATA_UPLOAD_FILE_LINK = "TestData/ECMS_DMS_SE_Addsymlink.jpg"; 
//		  By ELEMENT_UPLOAD_FILE = By.xpath("//a[@title='"+DATA_UPLOAD+".jpg "+"']");
//		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_24";
//		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
//
//		  //upload new file
//		  goToSiteExplorer();
//		  info("Upload new file");
//		  uploadFile(DATA_UPLOAD, DATA_UPLOAD_FILE_LINK);
//		  waitForElementPresent(ELEMENT_UPLOAD_FILE);
//		  goToNode(ELEMENT_UPLOAD_FILE);
//		  assert isElementPresent(ELEMENT_UPLOAD_FILE):"Upload file unsuccessfully";
//		  info("Upload file successfully");
//		  
//		  //add 2 symlink have same name for uploaded file
//		  info("Add 2 symlinks have same name in upload file");
//		  goToNode(ELEMENT_COLLABORATION);
//		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
//		  pause(100);
//		  checkPreferenceOption("enableStructure");
//		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
//		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
//		  
//		  //check alert
//		  checkAlertWarning("The node name already exists.");
//		  info("cannot add 2 symlink same name in uploaded file");
//		  
//		  //delete data
//		  deleteData(ELEMENT_UPLOAD_FILE);
//	  }
	  
	  /*case25: Add Symlink for node when user does not have permission to add in this node
	   * create new node - content folder
	   * set for user mary does not have add node permission
	   * login with mary
	   * check cannot add symlink for node
	   */
	  @Test
	  public void test25_AddSymlinkForNodeWhenUserNotHasAddNodePermission(){
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_25";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER); 
		  
		  //create new content folder
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //set for user mary does not have add node permission 
		  info("Set for user mary does not have add node permission");
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  click(ELEMENT_SYSTEM_TAB);
		  click(ELEMENT_PERMISSION_LINK);
		  removeDefaultPermissionOfNode();
		  selectUser("mary");
		  setNodePermission(true, false, false, false);
		  save();
		  logoutEcms();
		  
		  //login with mary
		  info("Login with mary");
		  loginEcms("mary", "gtn");
		  goToSiteExplorer();
		  
		  //check cannot add symlink for node
		  info("Check cannot add symlink for node");
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  goToNode(ELEMENT_COLLABORATION);
		  click(ELEMENT_ADD_SYMLINK);		  
		  checkAlertWarning("You do not have permission to add a new node.");
		  info("cannot add symlink for node because user does not have add node permission");
		  logoutEcms();
		  
		  //delete data
		  loginEcms(DATA_USER, DATA_PASS);
		  goToSiteExplorer();
		  deleteData(ELEMENT_CONTENT_FOLDER);
	  }
	  
	  /*Case26: Add Symlink for node when node is locked by locker
	   * create new node - content folder
	   * lock node
	   * add symlink for node
	   * check add node successfully
	   */
	  @Test
	  public void test26_AddSymlinkForNodeIsLockedByLocker(){
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_26";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER); 
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_26";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  
		  //create new content folder
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //lock node
		  info("Lock node");
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  lockNode(ELEMENT_CONTENT_FOLDER);
		  assert checkLockNode(ELEMENT_CONTENT_FOLDER):"Lock node unsuccessfully";
		  
		  //add symlink for node
		  info("Add symlink for locked node");
		  goToNode(ELEMENT_COLLABORATION);
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  
		  //check add symlink successful
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  assert isElementPresent(ELEMENT_DOCUMENT_SYMLINK):"Add symlink for content folder unsuccessfully";
		  info("Add symlink for content folder successfully");
		  
		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER);
	  }
	  
	  /*case27: Add Symlink for node when node is locked by user is not locker
	   * create new node with user John
	   * lock node by John
	   * login with mary
	   * check user mary can not add symlink for node
	   */
	  @Test
	  public void test27_AddSymlinkForNodeIsLockedByUserIsNotLocker(){
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_27";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER); 
		  
		  //create new content folder
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //lock node
		  info("Lock node");
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  lockNode(ELEMENT_CONTENT_FOLDER);
		  assert checkLockNode(ELEMENT_CONTENT_FOLDER):"Lock node unsuccessfully";
		  driver.close();
		  
		  //login with mary
		  info("Init new session");
		  initSeleniumTest();
		  driver.get(baseUrl);
		  actions = new Actions(driver);
		  info("Login with Mary");
		  loginEcms("mary", "gtn");
		  
		  //check cannot add symlink for node by user mary
		  goToSiteExplorer();
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  waitForElementNotPresent(ELEMENT_ADD_SYMLINK);
		  
		  rightClickOnElement(ELEMENT_CONTENT_FOLDER);
		  waitForElementNotPresent(ELEMENT_MENU_ADD_SYMLINK);
		  info("cannot add symlink for node by user is not locker");
		  logoutEcms();
		  
		  //delete data
		  loginEcms(DATA_USER, DATA_PASS);
		  goToSiteExplorer();
		  deleteData(ELEMENT_CONTENT_FOLDER);			
	  }
	  
	  /*case28: Add Symlink for node to the node is being locked
	   * create new node - content folder
	   * lock this node
	   * go to acme/document to add symlink
	   * add symlink to locked node
	   * check add successful
	   */
	  //@Test(groups={"pending"}) -> Case N/A
	  public void test28_AddSymlinkForNodeToLockedNode(){
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_28";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER);
		  By ELEMENT_CONTENT_FOLDER_DIV = By.xpath("//div[contains(text(),'" + DATA_CONTENT_FOLDER + "')]/../../td/a/div[@class='Select16x16Icon']");
		  By ELEMENT_CONTENT_SYMLINK = By.xpath("//a[@title='documents ']/../../../../*//a[@title='"+DATA_CONTENT_FOLDER+" ']");
		  
		  //create new content folder
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //lock node
		  info("Lock node");
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  lockNode(ELEMENT_CONTENT_FOLDER);
		  assert checkLockNode(ELEMENT_CONTENT_FOLDER):"Lock node unsuccessfully";
		  
		  //go to acme/documents
		  goToNode(ELEMENT_SIDEBAR_ACME);
		  goToNode(ELEMENT_SIDEBAR_ACME_DOCUMENTS);
		  
		  //add symlink for node to locked content node
		  info("Add symlink for documents to locked node");
		  goToNode(ELEMENT_COLLABORATION);
		  goToTargetNodeInRoot();
		  usePaginator(ELEMENT_CONTENT_FOLDER_DIV,"cannot choose target node");
		  click(ELEMENT_CONTENT_FOLDER_DIV);
		  click(ELEMENT_SAVE_BUTTON);
		  
		  //check add symlink successfully
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  waitForElementPresent(ELEMENT_CONTENT_SYMLINK);
		  assert isElementPresent(ELEMENT_CONTENT_SYMLINK):"Add symlink unsuccessfully";
		  info("Add symlink to locked node successfully");
		  
		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER);	  
	  }
	  
	  /*case29: Add Symlink for node when node is check in status
	   * create new content folder
	   * check in node
	   * check cannot add symlink for node
	   */
	  @Test
	  public void test29_AddSymlinkForCheckInNode(){
		  String DATA_FILE = "ECMS_DMS_SE_Addsymlink_FILE_29";
		  By ELEMENT_FILE = By.linkText(DATA_FILE);
		  
		  //create new article
		  goToSiteExplorer();
		  goToAddNewContent();
		  info("Create new file document with user john");
		  createNewFile(DATA_FILE, DATA_FILE, DATA_FILE);
		  waitForElementPresent(ELEMENT_FILE);
		  assert isElementPresent(ELEMENT_FILE):"Create new file document unsuccessfully";
		  info("Create new file document successfully");
		  
		  //check in node
		  info("Check in node");
		  checkInNode(ELEMENT_FILE);
		  
		  //check cannot add symlink
		  rightClickOnElement(ELEMENT_FILE);
		  pause(500);
		  waitForElementNotPresent(ELEMENT_MENU_ADD_SYMLINK);
		  goToNode(ELEMENT_FILE);
		  goToNode(ELEMENT_COLLABORATION);
		  waitForElementNotPresent(ELEMENT_ADD_SYMLINK);
		  info("cannot add symlink for checked in node");
		  
		  //delete data
		  checkOutNode(ELEMENT_FILE);
		  deleteData(ELEMENT_FILE);
	  }
	  
	  /*Case30: Add Symlink for node using drag and drop
	   * create new node - content folder
	   * create new document - article
	   * drag drop article to content folder
	   * check symlink of article in content folder
	   */
//	  @Test
//	  public void test30_AddSymlinkForNodeUsingDragDrop(){
//		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_30";
//		  By ELEMENT_CONTENT_FOLDER = By.xpath("//a[contains(@title,'"+DATA_CONTENT_FOLDER+"')]"); 
//		  String DATA_ARTICLE = "ECMS_DMS_SE_Addsymlink_article_30";
//		  By ELEMENT_ARTICLE = By.xpath("//a[@title='"+DATA_ARTICLE+" "+"']");
//		  By ELEMENT_ARTICLE_DIV = By.xpath("//a[@title='"+DATA_ARTICLE+" "+"']");
//		  
//		  //create new content folder
//		  goToSiteExplorer();
//		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
//		  
//		  //create new article document
//		  goToAddNewContent();
//		  createNewArticle(DATA_ARTICLE, DATA_ARTICLE, "", "");
//		  waitForElementPresent(ELEMENT_ARTICLE);
//		  assert isElementPresent(ELEMENT_ARTICLE):"Create new article unsuccessfully";
//		  info("Create new article successfully");
//		  
//		  //drag and drop article to content folder
//		  WebElement ELEMENT_ARTICLE_DOCUMENT = waitForAndGetElement(ELEMENT_ARTICLE);
//		  WebElement ELEMENT_CONTENT = waitForAndGetElement(ELEMENT_CONTENT_FOLDER);
//		  pause(1000);
//		  actions.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).moveToElement(ELEMENT_ARTICLE_DOCUMENT).clickAndHold().moveToElement(ELEMENT_CONTENT).release().keyUp(Keys.SHIFT).keyUp(Keys.CONTROL).build().perform();
//		  pause(1000);
//		  
//		  //check add symlink
//		  goToNode(ELEMENT_CONTENT_FOLDER);
//		  pause(5000);
//		  waitForElementPresent(ELEMENT_ARTICLE_DIV);
//		  assert isElementPresent(ELEMENT_ARTICLE_DIV):"Add symlink unsuccessfully";
//		  info("Add symlink for node successfully");
//		  
//		  //delete data
//		  deleteData(ELEMENT_CONTENT_FOLDER);
//		  deleteData(ELEMENT_ARTICLE);
//	  }
//	  
	  /*Case31: Edit the name of added link node in “Symlink Name”
	   * create node - content folder
	   * add symlink with input name
	   */
	  @Test
	  public void test31_AddSymlinkWithInputName(){
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_31";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER);
		  String DATA_SYMLINK_NAME = "ECMS_DMS_SE_Addsymlink_31";
		  By ELEMENT_SYMLINK = By.linkText(DATA_SYMLINK_NAME);
		  
		  //create new content folder
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //add symlink
		  info("Add symlink for content folder with name is node default name");
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  goToNode(ELEMENT_COLLABORATION);
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS, DATA_SYMLINK_NAME);
		  
		  //check add successfully
		  waitForElementPresent(ELEMENT_SYMLINK);
		  assert isElementPresent(ELEMENT_SYMLINK):"cannot add symlink";
		  
		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER);
	  }
	  
	  /*case32: View content of Symlink
	   * create new node - content folder
	   * add symlink for node with target node is folder: documents
	   * check can view all subnode of documents in document.lnk
	   */
	  @Test
	  public void test32_ViewContentOfSymlink(){
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_32";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER);
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_32";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  
		  //create new content folder
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //add symlink with target node - documents for this content folder
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  goToNode(ELEMENT_COLLABORATION);
		  info("Add symlink for node with target node is documents");
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  goToNode(ELEMENT_DOCUMENT_SYMLINK);
		  
		  //check subnode of symlink
		  waitForElementPresent(By.xpath("//div[@title='Live ']"));
		  assert isElementPresent(By.xpath("//div[@title='Live ']")):"check subnode of element is not true";
		  assert isElementPresent(By.xpath("//div[@title='Pending ']")):"check subnode of element is not true";
		  assert isElementPresent(By.xpath("//div[@title='Trash ']")):"check subnode of element is not true";
		  assert isElementPresent(By.xpath("//div[@title='Validation Requests ']")):"check subnode of element is not true";

		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER);
	  }
	  
	  /*case33: Remove Symlink of node
	   * create new node - content folder
	   * add symlink for node
	   * add path node -> remove path node
	   */
	  @Test
	  public void test33_RemoveSymlinkOfNode(){
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_33";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER);
		  By ELEMENT_SELECT_PATH_ACME = By.xpath("//div[contains(text(),'acme')]/../../td/a/div[@class='Select16x16Icon']");
		  By ELEMENT_REMOVE = By.xpath("//img[@title='Remove Item']");
		  
		  //create new content folder
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //add symlink for node
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  goToNode(ELEMENT_COLLABORATION);
		  goToTargetNodeInRoot();
		  usePaginator(ELEMENT_SELECT_PATH_ACME,"cannot choose target node");
		  click(ELEMENT_SELECT_PATH_ACME);
		  waitForElementPresent(ELEMENT_REMOVE);
		  click(ELEMENT_REMOVE);
		  
		  //check after remove
		  assert getValue(ELEMENT_PATH_NODE).isEmpty():"Path target node is not empty";
		  assert getValue(ELEMENT_SYMLINK_NAME).isEmpty():"Name symlink is not empty";
		  
		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER);
	  }
	  
	  /*case34: Rename the node which is the target node
	   * create 2 new node - content folder 1, content folder 2
	   * add symlink for content folder 1 with target node is content folder 2
	   * rename content folder 2
	   * check symlink is not change name
	   */
	  @Test(groups={"pending"})
	  public void test34_RenameNodeIsTargetNodeOfSymlink(){
		  String DATA_CONTENT_FOLDER_1 = "ECMS_DMS_SE_Addsymlink_contentfolder_34_1";
		  By ELEMENT_CONTENT_FOLDER_1 = By.linkText(DATA_CONTENT_FOLDER_1);
		  String DATA_CONTENT_FOLDER_2 = "ECMS_DMS_SE_Addsymlink_contentfolder_34_2";
		  By ELEMENT_CONTENT_FOLDER_2 = By.xpath("//div[@id='UITreeExplorer']/*//a[@title='"+DATA_CONTENT_FOLDER_2+" ']");
		  By ELEMENT_CONTENT_FOLDER_2_PATH = By.xpath("//div[contains(text(),'" + DATA_CONTENT_FOLDER_2 + "')]/../../td/a/div[@class='Select16x16Icon']");
		  By ELEMENT_CONTENT_FOLDER_2_SYMLINK = By.xpath("//a[@title='"+DATA_CONTENT_FOLDER_1+" ']/../../../../*//a[@title='"+DATA_CONTENT_FOLDER_2+" ']");
		  String DATA_CONTENT_FOLDER_2_NEW = "ECMS_DMS_SE_Addsymlink_contentfolder_34_2_new";
		  By ELEMENT_CONTENT_FOLDER_2_NEW = By.xpath("//div[@id='UITreeExplorer']/*//a[@title='"+DATA_CONTENT_FOLDER_2_NEW+" ']");
		  
		  //create new content folder 1
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER_1, ELEMENT_CONTENT_FOLDER_1);
		  
		  //create new content folder 2
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER_2, ELEMENT_CONTENT_FOLDER_2);
		  
		  //add symlink for content folder 1 with target node = content folder 2
		  info("Add symlink for content folder 1 with target node is content folder 2");
		  goToNode(ELEMENT_CONTENT_FOLDER_1);
		  goToNode(ELEMENT_COLLABORATION);
		  goToTargetNodeInRoot();
		  usePaginator(ELEMENT_CONTENT_FOLDER_2_PATH,"cannot choose target node");
		  click(ELEMENT_CONTENT_FOLDER_2_PATH);
		  click(ELEMENT_SAVE_BUTTON); 
		  
		  //check add successfully
		  goToNode(ELEMENT_CONTENT_FOLDER_1);
		  waitForElementPresent(ELEMENT_CONTENT_FOLDER_2_SYMLINK);
		  assert isElementPresent(ELEMENT_CONTENT_FOLDER_2_SYMLINK):"Add symlink for content folder 1 unsuccessfully";
		  info("Add symlink for content folder 1 successfully");
		  
		  //rename content folder 2
		  goToNode(ELEMENT_CONTENT_FOLDER_2);
		  renameNode(ELEMENT_CONTENT_FOLDER_2,DATA_CONTENT_FOLDER_2_NEW,ELEMENT_CONTENT_FOLDER_2_NEW );
		  
		  //check name of symlink is not change
		  goToNode(ELEMENT_CONTENT_FOLDER_1);
		  waitForElementPresent(ELEMENT_CONTENT_FOLDER_2_SYMLINK);
		  assert isElementPresent(ELEMENT_CONTENT_FOLDER_2_SYMLINK):"symlink is changed name";
		  info("Symlink is kept old name");
		  
		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER_2_NEW);
		  deleteData(ELEMENT_CONTENT_FOLDER_1);		  
	  }
	  
	  /*case35: Delete the node which is the target node
	   * create 2 content folder: 1 and 2
	   * add symlink for content folder 1 with target node = content folder 2
	   * delete folder 2
	   * check symlink is deleted too
	   */
	  @Test
	  public void test35_DeleteNodeWithIsTargetNodeOfSymlink(){
		  String DATA_CONTENT_FOLDER_1 = "ECMS_DMS_SE_Addsymlink_contentfolder_35_1";
		  By ELEMENT_CONTENT_FOLDER_1 = By.linkText(DATA_CONTENT_FOLDER_1);
		  String DATA_CONTENT_FOLDER_2 = "ECMS_DMS_SE_Addsymlink_contentfolder_35_2";
		  By ELEMENT_CONTENT_FOLDER_2 = By.xpath("//div[@id='UITreeExplorer']/*//div[@class='Node']/div/div/div/a[@title='"+DATA_CONTENT_FOLDER_2+" ']");
		  By ELEMENT_CONTENT_FOLDER_2_PATH = By.xpath("//div[contains(text(),'" + DATA_CONTENT_FOLDER_2 + "')]/../../td/a/div[@class='Select16x16Icon']");
		  By ELEMENT_CONTENT_FOLDER_2_SYMLINK = By.xpath("//a[@title='"+DATA_CONTENT_FOLDER_1+" ']/../../../../*//a[@title='"+DATA_CONTENT_FOLDER_2+" ']");
		  
		  //create new content folder 1
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER_1, ELEMENT_CONTENT_FOLDER_1);
		  
		  //create new content folder 2
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER_2,ELEMENT_CONTENT_FOLDER_2);
		  
		  //add symlink for content folder 1 with target node = content folder 2
		  info("Add symlink for content folder 1 with target node is content folder 2");
		  goToNode(ELEMENT_CONTENT_FOLDER_1);
		  goToNode(ELEMENT_COLLABORATION);
		  goToTargetNodeInRoot();
		  usePaginator(ELEMENT_CONTENT_FOLDER_2_PATH,"cannot choose target node");
		  click(ELEMENT_CONTENT_FOLDER_2_PATH);
		  click(ELEMENT_SAVE_BUTTON); 
		  
		  //check add successfully
		  goToNode(ELEMENT_CONTENT_FOLDER_1);
		  waitForElementPresent(ELEMENT_CONTENT_FOLDER_2_SYMLINK);
		  assert isElementPresent(ELEMENT_CONTENT_FOLDER_2_SYMLINK):"Add symlink for content folder 1 unsuccessfully";
		  info("Add symlink for content folder 1 successfully");
		  
		  //delete content folder 2
		  deleteData(ELEMENT_CONTENT_FOLDER_2);
		  
		  //check symlink is deleted 
		  goToNode(ELEMENT_CONTENT_FOLDER_1);
		  waitForElementNotPresent(ELEMENT_CONTENT_FOLDER_2_SYMLINK);
		  info("Symlink is deleted too");
		  
		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER_1);
	  }
	  
	  /*case36: Rename Symlink node
	   * create new node - content folder
	   * add symlink for node
	   * rename symlink
	   */
	  @Test
	  public void test36_RenameSymlink(){
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_36";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER);
		  String DATA_SYMLINK_NAME_NEW = "ECMS_DMS_SE_Addsymlink_36_new";
		  By ELEMENT_SYMLINK_NEW = By.linkText(DATA_SYMLINK_NAME_NEW);
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_36";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  
		  //create new content folder
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //add symlink for node
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  goToNode(ELEMENT_COLLABORATION);
		  info("Add symlink for node");
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  
		  //rename symlink
		  goToNode(ELEMENT_DOCUMENT_SYMLINK);
		  renameNode(ELEMENT_DOCUMENT_SYMLINK, DATA_SYMLINK_NAME_NEW, ELEMENT_SYMLINK_NEW);
		  
		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER);	  
	  }
	  
	  /*case37: Delete Symlink node
	   * create new node - content folder
	   * add symlink for node
	   * delete symlink
	   */
	  @Test
	  public void test37_DeleteSymlinkOfNode(){
		  String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_Addsymlink_contentfolder_37";
		  By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER);
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_37";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  
		  //create new content folder
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER, ELEMENT_CONTENT_FOLDER);
		  
		  //add symlink for node
		  goToNode(ELEMENT_CONTENT_FOLDER);
		  goToNode(ELEMENT_COLLABORATION);
		  info("Add symlink for node");
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  
		  //delete symlink
		  deleteData(ELEMENT_DOCUMENT_SYMLINK);
		  waitForElementPresent(ELEMENT_CONTENT_FOLDER);
		  
		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER);
	  }
	  
	  /*case38: Copy Symlink node to other node
	   * create 2 node: content folder 1 and 2
	   * create symlink for content folder 1
	   * copy symlink from content folder 1 to content folder 2
	   */
	  @Test
	  public void test38_CopySymlinkNodeToOtherNode(){
		  String DATA_CONTENT_FOLDER_1 = "ECMS_DMS_SE_Addsymlink_contentfolder_38_1";
		  By ELEMENT_CONTENT_FOLDER_1 = By.linkText(DATA_CONTENT_FOLDER_1);
		  String DATA_CONTENT_FOLDER_2 = "ECMS_DMS_SE_Addsymlink_contentfolder_38_2";
		  By ELEMENT_CONTENT_FOLDER_2 = By.linkText(DATA_CONTENT_FOLDER_2);
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_38";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  By ELEMENT_SYMLINK_CHILD = By.xpath("//a[@title='"+DATA_CONTENT_FOLDER_2+" "+"']/../../../../*//a[@title='"+DATA_SYMLINK+" ']");
		  
		  
		  //create new content folder 1
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER_1, ELEMENT_CONTENT_FOLDER_1);
		  
		  //create new content folder 2
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER_2,ELEMENT_CONTENT_FOLDER_2);
		  
		  //create symlink for content folder 1
		  info("Add symlink for content folder 1");
		  goToNode(ELEMENT_CONTENT_FOLDER_1);
		  goToNode(ELEMENT_COLLABORATION);
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  info("Add symlink successfully");
		  
		  //copy symlink from content folder 1 to content folder 2
		  copyAndPasteNode(ELEMENT_DOCUMENT_SYMLINK,ELEMENT_CONTENT_FOLDER_2);
		  
		  //check copy successfully
		  goToNode(ELEMENT_CONTENT_FOLDER_2);
		  waitForElementPresent(ELEMENT_SYMLINK_CHILD);
		  assert isElementPresent(ELEMENT_SYMLINK_CHILD):"Copy symlink unsuccessfully";
		  info("Copy symlink successfully");
		  
		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER_1);
		  deleteData(ELEMENT_CONTENT_FOLDER_2);		  
	  }

	  /*case39: Cut Symlink node to other node
	   * create 2 node: content folder 1 and 2
	   * create symlink for content folder 1
	   * cut symlink form content folder 1 to content folder 2 
	   */
	  @Test
	  public void test39_CutSymlinkNodeToOtherNode(){
		  String DATA_CONTENT_FOLDER_1 = "ECMS_DMS_SE_Addsymlink_contentfolder_39_1";
		  By ELEMENT_CONTENT_FOLDER_1 = By.linkText(DATA_CONTENT_FOLDER_1);
		  String DATA_CONTENT_FOLDER_2 = "ECMS_DMS_SE_Addsymlink_contentfolder_39_2";
		  By ELEMENT_CONTENT_FOLDER_2 = By.linkText(DATA_CONTENT_FOLDER_2);
		  String DATA_SYMLINK = "ECMS_DMS_SE_Addsymlink_symlink_39";
		  By ELEMENT_DOCUMENT_SYMLINK = By.linkText(DATA_SYMLINK);
		  By ELEMENT_SYMLINK_CHILD_FOLDER2 = By.xpath("//a[@title='"+DATA_CONTENT_FOLDER_2+" "+"']/../../../../*//a[@title='"+DATA_SYMLINK+" ']");
		  By ELEMENT_SYMLINK_CHILD_FOLDER1 = By.xpath("//a[@title='"+DATA_CONTENT_FOLDER_1+" "+"']/../../../../*//a[@title='"+DATA_SYMLINK+" ']");
		  
		  //create new content folder 1
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER_1, ELEMENT_CONTENT_FOLDER_1);
		  
		  //create new content folder 2
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER_2,ELEMENT_CONTENT_FOLDER_2);
		  
		  //create symlink for content folder 1
		  info("Add symlink for content folder 1");
		  goToNode(ELEMENT_CONTENT_FOLDER_1);
		  goToNode(ELEMENT_COLLABORATION);
		  addSymlink(ELEMENT_SELECT_PATH_DOCUMENTS,DATA_SYMLINK);
		  waitForElementPresent(ELEMENT_DOCUMENT_SYMLINK);
		  info("Add symlink successfully");
		  
		  //cut symlink from content folder 1 to content folder 2
		  cutAndPasteNode(ELEMENT_DOCUMENT_SYMLINK,ELEMENT_CONTENT_FOLDER_2);
		  
		  //check cut successfully
		  goToNode(ELEMENT_CONTENT_FOLDER_2);
		  waitForElementPresent(ELEMENT_SYMLINK_CHILD_FOLDER2);
		  assert isElementPresent(ELEMENT_SYMLINK_CHILD_FOLDER2):"Copy symlink unsuccessfully";
		  info("Copy symlink successfully");
		  
		  goToNode(ELEMENT_CONTENT_FOLDER_1);
		  waitForElementNotPresent(ELEMENT_SYMLINK_CHILD_FOLDER1);
		  
		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER_2);
		  goToNode(ELEMENT_CONTENT_FOLDER_1);
		  deleteDocument(ELEMENT_CONTENT_FOLDER_1);
	  }
	  
	  /*case40: Create child nodes in Symlink node
	   * create new node: content folder
	   * add symlink for node
	   * add content folder for symlink
	   * check add successfully
	   */
	  @Test
	  public void test40_CreateChildNodeInSymlinkNode(){
		  String DATA_CONTENT_FOLDER_1 = "ECMS_DMS_SE_Addsymlink_contentfolder_40_1";
		  By ELEMENT_CONTENT_FOLDER_1 = By.linkText(DATA_CONTENT_FOLDER_1);
		  String DATA_CONTENT_FOLDER_2 = "ECMS_DMS_SE_Addsymlink_contentfolder_40_2";
		  By ELEMENT_CONTENT_FOLDER_2 =  By.linkText(DATA_CONTENT_FOLDER_2);
		  By ELEMENT_CONTENT_FOLDER_2_PATH = By.xpath("//div[contains(text(),'" + DATA_CONTENT_FOLDER_2 + "')]/../../td/a/div[@class='Select16x16Icon']");
		  By ELEMENT_CONTENT_FOLDER_2_SYMLINK = By.xpath("//a[@title='"+DATA_CONTENT_FOLDER_1+" ']/../../../../*//a[@title='"+DATA_CONTENT_FOLDER_2+" ']");
		  String DATA_CONTENT_FOLDER_3 = "ECMS_DMS_SE_Addsymlink_contentfolder_40_3";
		  By ELEMENT_CONTENT_FOLDER_3 = By.linkText(DATA_CONTENT_FOLDER_3);
		  
		  //create new content folder 1
		  goToSiteExplorer();
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER_1, ELEMENT_CONTENT_FOLDER_1);
		  
		  //create new content folder 2
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER_2,ELEMENT_CONTENT_FOLDER_2);
		  
		  //add symlink for content folder 1 with target node = content folder 2
		  info("Add symlink for content folder 1 with target node is content folder 2");
		  goToNode(ELEMENT_CONTENT_FOLDER_1);
		  goToNode(ELEMENT_COLLABORATION);
		  goToTargetNodeInRoot();
		  usePaginator(ELEMENT_CONTENT_FOLDER_2_PATH,"cannot found target node");
		  click(ELEMENT_CONTENT_FOLDER_2_PATH);
		  click(ELEMENT_SAVE_BUTTON); 
		  
		  //add content folder for symlink
		  goToNode(ELEMENT_CONTENT_FOLDER_2_SYMLINK);
		  waitForElementPresent(ELEMENT_PUBLICATION_TAB);
		  goToNode(ELEMENT_PUBLICATION_TAB);
		  createAndCheckContentFolder(DATA_CONTENT_FOLDER_3, ELEMENT_CONTENT_FOLDER_3);
		  
		  //delete data
		  deleteData(ELEMENT_CONTENT_FOLDER_1);
		  deleteData(ELEMENT_CONTENT_FOLDER_2);
	  }
}
