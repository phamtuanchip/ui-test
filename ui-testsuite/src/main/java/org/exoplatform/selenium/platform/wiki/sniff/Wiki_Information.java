package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.wiki.Version;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author lientm
 * @date: 1-July-2013
 */

public class Wiki_Information extends Version {
	
	ManageAccount magAc;
	Button but;
	ManageMember magMem;
	
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAc = new ManageAccount(driver);
		but = new Button(driver);
		magMem = new ManageMember(driver);
		
		magAc.signIn("john", "gtn"); 
		goToWiki();
	}

	@AfterMethod
	public void afterTest(){
		magAc.signOut();
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	/**CaseId: 68843 -> Show Page Information
	 */
	@Test
	public void test01_PageInformation(){
		String title = "Wiki_sniff_infor_page_title_01";
		String content = "Wiki_sniff_infor_page_content_01";
		String link = "Wiki_Sniff_Attachment_01.doc";
		String newTitle = "Wiki_sniff_infor_page_title_01_update";
		String newContent = "Wiki_sniff_infor_page_content_01_update";
		
		addBlankWikiPageHasAttachment(title, content, link);
		editWikiPage(newTitle, newContent, 0);
		
		info("View Information of page");
		waitForAndGetElement(ELEMENT_CREATOR_PAGE_INFO.replace("${fullName}", "John Smith"));
		waitForAndGetElement(ELEMENT_UPDATER_PAGE_INFO.replace("${fullName}", "John Smith"));
		waitForAndGetElement(ELEMENT_VIEW_CHANGE);
		waitForAndGetElement(ELEMENT_ATTACHMENT_NUMBER.replace("${No}", "1"));
		waitForAndGetElement(ELEMENT_VERSION_LINK.replace("{$version}", "2"));
		
		deleteCurrentWikiPage();
	}
	
	/**CaseId: 70047 -> Show Page History
	 * 
	 */
	@Test
	public void test02_PageHistory(){
		String title = "Wiki_sniff_infor_page_title_02";
		String content = "Wiki_sniff_infor_page_content_02";
		String newTitle = "Wiki_sniff_infor_page_title_02_update";
		String newContent = "Wiki_sniff_infor_page_content_02_update";
		
		addBlankWikiPage(title, content, 0);
		editWikiPage(newTitle, newContent, 0);
		
		goToPageInfoFromCurrentPage();
		viewPageHistory();
		
		info("Compare 2 version of page");
		compareVersion("1", "2");
		waitForAndGetElement(ELEMENT_LINE_REMOVE.replace("${lineRemove}", content));
		waitForAndGetElement(ELEMENT_LINE_ADD.replace("${lineAdd}", newContent));
		
		click(By.linkText(newTitle));
		deleteCurrentWikiPage();
	}
	
	/**CaseId: 70337 -> Add Relation between 2 pages different space
	 * 
	 */
	@Test
	public void test03_AddRelationDifferentSpace(){
		String spaceName1 = "RelationSpace031";
		String title1 = "Wiki_sniff_relation_title_03_1";
		String content1 = "Wiki_sniff_relation_content_03_1";
		
		String spaceName2 = "RelationSpace032";
		String title2 = "Wiki_sniff_relation_title_03_2";
		String content2 = "Wiki_sniff_relation_content_03_2";
		
		magMem.goToAllSpaces();
		magMem.addNewSpace(spaceName1, "", "Visible", "Validation", "", "");
		goToWikiFromSpace(spaceName1);
		addBlankWikiPage(title1, content1, 0);
		
		magMem.goToAllSpaces();
		magMem.addNewSpace(spaceName2, "", "Visible", "Validation", "", "");
		goToWikiFromSpace(spaceName2);
		addBlankWikiPage(title2, content2, 0);
		
		info("Add relation for page2 of space2 to page1 of space1");
		addRelatedPage("Wiki Home/" + title2, title1, spaceName1);
		
		magMem.goToAllSpaces();
		magMem.deleteSpace(spaceName1, 180000);
		magMem.deleteSpace(spaceName2, 180000);
	}
	
	/**CaseId: 70340 -> Add Relation with intranet portal
	 * 
	 */
	@Test
	public void test04_AddRelationWithIntranetPortal(){
		String title1 = "Wiki_relation_title_04_1";
		String content1 = "Wiki_relation_content_04_1";
		
		String spaceName = "relationSpace04";
		String title2 = "Wiki_relation_title_04_2";
		String content2 = "Wiki_relation_content_04_2";
		
		addBlankWikiPage(title1, content1, 0);
		
		magMem.goToAllSpaces();
		magMem.addNewSpace(spaceName, "", "Visible", "Validation", "", "");
		goToWikiFromSpace(spaceName);
		addBlankWikiPage(title2, content2, 0);
		
		info("Add relation for page2 of space2 to page1 of space1");
		addRelatedPage("Wiki Home/" + title2, title1, "Intranet");
		
		magMem.goToAllSpaces();
		magMem.deleteSpace(spaceName, 180000);
		goToWikiPage("Wiki Home/" + title1);
		deleteCurrentWikiPage();
	}
	
	/**CaseId: 70341 -> Add Relation same space
	 * 
	 */
	@Test
	public void test05_AddRelationSameSpace(){
		String spaceName = "RelationSpace05";
		String title1 = "Wiki_relation_title_05_1";
		String content1 = "Wiki_relation_content_05_1";		
		String title2 = "Wiki_relation_title_05_2";
		String content2 = "Wiki_relation_content_05_2";
		
		magMem.goToAllSpaces();
		magMem.addNewSpace(spaceName, "", "Visible", "Validation", "", "");
		goToWikiFromSpace(spaceName);
		addBlankWikiPage(title1, content1, 0);
		goToWikiHome();
		addBlankWikiPage(title2, content2, 0);
		
		info("Move page2 to page1 in same space");
		addRelatedPage("Wiki Home/" + title2, title1);
		
		magMem.goToAllSpaces();
		magMem.deleteSpace(spaceName, 180000);
	}
	
	/**CaseId: 70342 -> Add relation in the case there is no space
	 * 
	 */
	@Test
	public void test06_AddRelation_NoSpace(){
		String title = "Wiki_sniff_infor_page_title_06";
		String content = "Wiki_sniff_infor_page_content_06";
		
		magAc.signOut();
		magAc.signIn("fqa", "gtngtn");
		goToWiki();
		addBlankWikiPage(title, content, 0);
		goToAddRelation();
		click(ELEMENT_SELECT_SPACE);
		waitForAndGetElement(ELEMENT_NO_SPACE_OPTION);
		but.cancel();
		
		click(By.linkText(title));
		deleteCurrentWikiPage();
	}
	
	/**CaseId: 70344 -> Delete relation
	 * 
	 */
	@Test
	public void test07_DeleteRelation(){
		String title1 = "Wiki_sniff_infor_page_title_07_1";
		String content1 = "Wiki_sniff_infor_page_content_07_1";
		String title2 = "Wiki_sniff_infor_page_title_07_2";
		String content2 = "Wiki_sniff_infor_page_content_07_2";
		
		addBlankWikiPage(title1, content1, 0);
		goToWikiHome();
		
		addBlankWikiPage(title2, content2, 0);
		addRelatedPage("Wiki Home/" + title1, title2);
		
		removeRelatedPage(true, true, "", title2);
		
		click(By.linkText(title1));
		deleteCurrentWikiPage();
		click(By.linkText(title2));
		deleteCurrentWikiPage();
	}
}
