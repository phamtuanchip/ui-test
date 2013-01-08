/*
 * Copyright (C) 2003-2012 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.selenium.platform.ks.functional.wiki.pageaction;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.PageEditor.ELEMENT_CLOSE_WINDOWS_BUTTON;

import org.exoplatform.selenium.platform.ks.Wiki;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Created by The eXo Platform SAS
 * Author : Hoang Manh Dung
 *          dunghm@exoplatform.com
 * Dec 10, 2012  
 */
public class KS_Wiki_Action_Add extends Wiki{
  @BeforeMethod
  public void beforeMethod(){
    initSeleniumTest();
    actions = new Actions(driver);
    driver.get(baseUrl);
    driver.manage().window().maximize();
    signIn("john", "gtn");    
  }

  @AfterMethod
  public void afterMethod(){
    signOut();
    driver.manage().deleteAllCookies();
    driver.quit();
  }
  /**
   * Test Case ID 001
   * Create new page using Blank Template
   */
  @Test
  public static void test01_AddNewPageWithBlankTemplate(){
    String title = "TestCase 001";
    String content = title + " Content";
    int mode = 0;
    //Create new page with a blank template
    goToWiki();
    addBlankWikiPage(title, content, mode);
    info("Create new page successfully");
    //Reset data
    goToWikiPage("Wiki Home/"+title);
    deleteWikiPage();
  }
  
  /**
   * Test Case ID 002
   * Create new page using existing template 
   */
  @Test
  public static void test02_AddNewPageWithTemplate(){
    String title = "TestCase 002";
    int mode = 0;
    String template = "Two-Column Layout";
    //Create new page with a blank template
    goToWiki();
    waitForTextPresent("Wiki Home");
    addTemplateWikiPage(title, mode, template);
    info("Create new page successfully");
    //Reset data
    goToWikiPage("Wiki Home/"+title);
    deleteWikiPage();
  }
  
  /**
   * Test Case ID 003
   * Add new page when title is blank
   */
  @Test
  public static void test03_AddNewPageWithBlankTitle(){
    String title = "";
    String content = "TestCase 003 Content";
    int mode = 0;
    String errorMessage = "The page title is required.";
    //Create new page with a blank template
    goToWiki();
    addWikiPageWithInvalid(title, content, mode, errorMessage);
  }
  
  /**
   * Test Case ID 004
   * Add new page when content is blank
   */
  @Test
  public static void test04_AddNewPageWithBlankContent(){
    String title = "TestCase 004";
    String content = "";
    int mode = 0;    
    //Create new page with a blank template
    goToWiki();
    addBlankWikiPage(title, content, mode);
    //Reset data
    goToWikiPage("Wiki Home/"+title);
    deleteWikiPage();
  }
  /**
   * Test Case ID 005
   * Add new page has the same title with existing page
   */
  @Test
  public static void test05_AddNewPageWithSameTitle(){
    String title = "TestCase 005";
    String content = title + " Content";
    int mode = 0;
    String errorMessage = "The page title already exists. Please select another one.";
    //Create new page with a blank template
    goToWiki();
    addBlankWikiPage(title, content, mode);
    
    //Create a new page with the same title
    addWikiPageWithInvalid(title, content, mode, errorMessage);
    cancel();
    //waitForWikiConfirmation("Are you sure to leave this page?");
    
    //Reset data
    goToWikiPage("Wiki Home/"+title);
    deleteWikiPage();
  }
  
  /**
   * Test Case ID 006
   * Add new page when user does not have add page permission on space
   */
  @Test
  public static void test06_AddNewPageWithNotPermission(){    
    int mode = 0;
    String[] userGroup = {"james"};
    String[] userGroupR = {"any"};
    By ELEMEN_USER = By.xpath("//div[@class='Id' and @title='" + userGroup[0] + "']");
    //Add view permission for James
    goToWiki();
    deleteSpacePermission(userGroupR[0]);
    addSpacePermission(mode, userGroup);
    waitForElementPresent(ELEMEN_USER);
    
    //Verify that James can't add new page
    signOut();
    signIn("james","gtn");
    goToWiki();
    waitForElementNotPresent(ELEMENT_ADD_PAGE_LINK);
    
    //Reset data
    signOut();
    signIn("john","gtn");
    goToWiki();
    deleteSpacePermission(userGroup[0]);
    addSpacePermission(0, userGroupR);
    check(By.id("EDITPAGE" + userGroupR[0]));
    save();
    waitForMessage(MSG_PERMISSION_SAVE);
    closeMessageDialog();
    waitForTextNotPresent(MSG_PERMISSION_SAVE);
  }
  
  /**
   * Test Case ID 007
   * Preview a page
   */ 
  @Test
  public static void test07_PreviewPage(){
    String title = "TestCase 007";
    String content = title + " Content";
    int mode = 0;
    By uiWikiPageTitlePreview = By.xpath("//div[@class='UIWikiPageTitlePreview' and contains(text(),'" + title + "')]");
    By wikiContent = By.xpath("//div[@class='WikiContent']/p[contains(text(),'" + title + "')]");
    
    goToWiki();
    previewWikiPage(title, content, mode);
    waitForElementPresent(uiWikiPageTitlePreview);
    waitForElementPresent(wikiContent);
    
    click(ELEMENT_CLOSE_WINDOWS_BUTTON);
  }
  
  /**
   * Test Case ID 008
   * Cancel creating new wiki page
   */ 
  @Test
  public static void test08_CanelAddNewPage(){
    String title = "TestCase 008";
    String content = title + " Content";
    int mode = 0;   
    //Cancel creating a new wiki page
    goToWiki();
    cancelCreatingWikiPage(title, content, mode);
  }
  
  /**
   * Test Case ID 012
   * Create new page with Bold effect
   */  
  @Test
  public static void test12_addNewPageWithBold(){
    String title = "TestCase 012";
    String content = "**" +title + " Content**";
    int mode = 0;
    By ELEMENT_CONTENT = By.xpath("//strong[text()='" + content.replace("**", "") + "']");
    //Create new page with a blank template
    goToWiki();
    addBlankWikiPage(title, content, mode, false);
    waitForElementPresent(ELEMENT_CONTENT);
    info("Create new page successfully");
    //Reset data
    goToWikiPage("Wiki Home/"+title);
    deleteWikiPage();    
  }
  
  /**
   * Test Case ID 013
   * Create new page with Italic effect
   */  
  @Test
  public static void test13_addNewPageWithItalic(){
    String title = "TestCase 013";
    String content = "//" +title + " Content//";
    int mode = 0;
    //Create new page with a blank template
    goToWiki();
    addBlankWikiPage(title, content, mode, false);
    waitForElementPresent(By.xpath("//em[text()='" + content.replace("//", "") + "']"));
    info("Create new page successfully");
    //Reset data
    goToWikiPage("Wiki Home/"+title);
    deleteWikiPage();    
  }
  
  /**
   * Test Case ID 014
   * Create new page with Strike effect
   */  
  @Test
  public static void test14_addNewPageWithStrike(){
    String title = "TestCase 014";
    String content = "--" +title + " Content--";
    int mode = 0;
    By ELEMENT_CONTENT = By.xpath("//del[text()='" + content.replace("--", "") + "']");
    //Create new page with a blank template
    goToWiki();
    addBlankWikiPage(title, content, mode, false);
    waitForElementPresent(ELEMENT_CONTENT);
    info("Create new page successfully");
    //Reset data
    goToWikiPage("Wiki Home/"+title);
    deleteWikiPage();
  }
  
  /**
   * Test Case ID 015
   * Create new page with Underline effect
   */  
  @Test
  public static void test15_addNewPageWithUnderline(){
    String title = "TestCase 015";
    String content = "__" +title + " Content__";
    int mode = 0;
    By ELEMENT_CONTENT = By.xpath("//ins[text()='" + content.replace("__", "") + "']");
    //Create new page with a blank template
    goToWiki();
    addBlankWikiPage(title, content, mode, false);
    waitForElementPresent(ELEMENT_CONTENT);
    info("Create new page successfully");
    //Reset data
    goToWikiPage("Wiki Home/"+title);
    deleteWikiPage();
  }
  
  /**
   * Test Case ID 016
   * Create new page with Heading effect
   */  
  @Test
  public static void test16_addNewPageWithHeading(){
    String title = "TestCase 016";
    String content = "=" +title + " Content=";
    int mode = 0;
    By ELEMENT_CONTENT = By.xpath("//h1/span[text()='" + content.replace("=", "") + "']");
    //Create new page with a blank template
    goToWiki();
    addBlankWikiPage(title, content, mode, false);
    waitForElementPresent(ELEMENT_CONTENT);
    info("Create new page successfully");
    //Reset data
    goToWikiPage("Wiki Home/"+title);
    deleteWikiPage();
  }
  
  /**
   * Test Case ID 019
   * Create new page with Bulleted list effect
   */  
  @Test
  public static void test19_addNewPageWithList(){
    String title = "TestCase 019";
    String content = "* item 1\n";
    content += "** item 2\n";
    content += "*** item 3\n";
    content += "* item 4\n";
    int mode = 0;
    By ELEMENT_CONTENT = By.xpath("//div[@class='WikiContent']//ul/li[text()='item 1']/ul/li[text()='item 2']/ul/li[text()='item 3']/../../../../following::li[text()='item 4']");
    //Create new page with a blank template
    goToWiki();
    addBlankWikiPage(title, content, mode, false);
    waitForElementPresent(ELEMENT_CONTENT);
    info("Create new page successfully");
    //Reset data
    goToWikiPage("Wiki Home/"+title);
    deleteWikiPage();
  }
  
  /**
   * Test Case ID 020
   * Create new page with Numbered list effect
   */  
  @Test
  public static void test20_addNewPageWithNumberedList(){
    String title = "TestCase 020";
    String content = "1. item 1\n";
    content += "11. item 2\n";
    content += "111. item 3\n";
    content += "1. item 4\n";
    int mode = 0;
    By ELEMENT_CONTENT = By.xpath("//div[@class='WikiContent']//ol/li[text()='item 1']/ol/li[text()='item 2']/ol/li[text()='item 3']/../../../../following::li[text()='item 4']");
    //Create new page with a blank template
    goToWiki();
    addBlankWikiPage(title, content, mode, false);
    waitForElementPresent(ELEMENT_CONTENT);
    info("Create new page successfully");
    //Reset data
    goToWikiPage("Wiki Home/"+title);
    deleteWikiPage();
  }
  /**
   * Test Case ID 021
   * Create new page with  Table effect
   */  
  @Test
  public static void test21_addNewPageWithTable(){
    String title = "TestCase 021";
    String content = "|=Title 1|=Title 2\n";
    content += "|Word 1|Word 2\n";
    int mode = 0;
    By ELEMENT_CONTENT = By.xpath("//div[@class='WikiContent']//table/tbody/tr/th[text()='Title 1']/../th[text()='Title 2']/../following::tr/td[text()='Word 1']/../td[text()='Word 2']");
    //Create new page with a blank template
    goToWiki();
    addBlankWikiPage(title, content, mode, false);
    waitForElementPresent(ELEMENT_CONTENT);
    info("Create new page successfully");
    //Reset data
    goToWikiPage("Wiki Home/"+title);
    deleteWikiPage();
  }

  /**
   * Test Case ID 022
   * Create new page with Link effect
   */
  @Test
  public static void test22_addNewPageWithLink(){
    String title = "TestCase 022";
    String content = "[[" + title + " Content]]";
    int mode = 0;
    By ELEMENT_CONTENT = By.xpath("//span[@class='wikicreatelink']/a/span[text()='" + content.replace("[[", "").replace("]]", "") + "']");
    //Create new page with a blank template
    goToWiki();
    addBlankWikiPage(title, content, mode, false);
    waitForElementPresent(ELEMENT_CONTENT);
    info("Create new page successfully");
    //Reset data
    goToWikiPage("Wiki Home/"+title);
    deleteWikiPage();
  }
  
}
