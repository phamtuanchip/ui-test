package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.wiki.Permalink;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * @date 9-July-2013
 * @author lientm
 */
public class Wiki_BasicAction_Other extends Permalink {
	
	ManageAccount magAc;
	Dialog dialog;
	Button button;
	ManageMember magMem;
	
	@BeforeMethod
	public void setUpBeforeTest(){
		getDriverAutoSave();
		driver.get(baseUrl);
		magAc = new ManageAccount(driver);
		dialog = new Dialog(driver);
		button = new Button(driver);
		
		magAc.signIn("john", "gtn"); 
		goToWiki();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	public void addWikiForSpace(String spaceName, String title, String content){
		magMem.goToAllSpaces();
		magMem.addNewSpace(spaceName, "", "Visible", "Validation", "", "");
		goToWikiFromSpace(spaceName);
		addBlankWikiPage(title, content, 0);
	}
	
	/**CaseId: 70044
	 * Export page as PDF
	 */
	@Test
	public void test01_ExportPDF(){
		String title = "Wiki_sniff_export_pdf_title_01";
		String content = "Wiki_sniff_export_pdf_content_01";
		
		addBlankWikiPage(title, content, 0);
		click(By.linkText(title));
		goToExportPageAsPDF();
		Utils.pause(2000);
		//assert checkFileExisted(title + ".pdf");
		
		deleteCurrentWikiPage();
	}
	
	/**CaseId: 68840
	 * Move Page
	 */
	@Test
	public void test02_MovePage(){
		String title1 = "Wiki_move_title_08_1";
		String content1 = "Wiki_move_content_08_1";
		String title2 = "Wiki_move_title_08_2";
		String content2 = "Wiki_move_content_08_2";
		
		info("Add new 2 wiki pages at Wiki Home");		
		addBlankWikiPage(title1, content1, 0);
		goToWikiHome();
		addBlankWikiPage(title2, content2, 0);

		info("Move page 2 to page1");
		movePage(title2, title1);
		
		click(By.linkText(title1));
		deleteCurrentWikiPage();
	}
	
	/**CaseId: 70346
	 * Move page in the cases 2 different spaces
	 */
	@Test
	public void test03_MovePageDifferentSpace(){
		String spaceName1 = "Space091";
		String title1 = "Wiki_move_title_09_1";
		String content1 = "Wiki_move_content_09_1";
		
		String spaceName2 = "Space092";
		String title2 = "Wiki_move_title_09_2";
		String content2 = "Wiki_move_content_09_2";
		
		addWikiForSpace(spaceName1, title1, content1);
		addWikiForSpace(spaceName2, title2, content2);

		info("Move page2 of space2 to page1 of space1");
		movePage(title2, title1, spaceName1);
		waitForAndGetElement(ELEMENT_DISPLAY_MODE.replace("${space}", spaceName1));
		
		magMem.goToAllSpaces();
		goToWikiFromSpace(spaceName2);
		waitForElementNotPresent(By.linkText(title2));
		
		magMem.goToAllSpaces();
		magMem.deleteSpace(spaceName1, 180000);
		magMem.deleteSpace(spaceName2, 180000);
	}
	
	/**CaseId: 70347
	 * Move wiki's page of space to portal
	 */
	@Test
	public void test04_MovePageFromSpaceToPortal(){
		String title1 = "Wiki_move_title_10_1";
		String content1 = "Wiki_move_content_10_1";
		
		String spaceName = "Space10";
		String title2 = "Wiki_move_title_10_2";
		String content2 = "Wiki_move_content_10_2";
		
		addBlankWikiPage(title1, content1, 0);
		addWikiForSpace(spaceName, title2, content2);
		
		info("Move page2 of space to page1 of Intranet");
		movePage(title2, title1, "Intranet");
		waitForAndGetElement(ELEMENT_DISPLAY_MODE.replace("${space}", "Intranet"));
		
		magMem.goToAllSpaces();
		goToWikiFromSpace(spaceName);
		waitForElementNotPresent(By.linkText(title2));
		
		magMem.goToAllSpaces();
		magMem.deleteSpace(spaceName, 180000);
		goToWikiPage("Wiki Home/" + title1);
		deleteCurrentWikiPage();
	}
	
	/**CaseId: 70348
	 * Move page in same space
	 */
	@Test
	public void test05_MovePageInSameSpace(){
		String spaceName = "Space11";
		String title1 = "Wiki_move_title_11_1";
		String content1 = "Wiki_move_content_11_1";		
		String title2 = "Wiki_move_title_11_2";
		String content2 = "Wiki_move_content_11_2";
		
		addWikiForSpace(spaceName, title1, content1);
		goToWikiHome();
		addBlankWikiPage(title2, content2, 0);
		
		info("Move page2 to page1 in same space");
		movePage(title2, title1);
		
		magMem.goToAllSpaces();
		magMem.deleteSpace(spaceName, 180000);
	}
	
	/**CaseId: 70355
	 * Move wiki's page of Portal to Space
	 */
	@Test
	public void test06_MovePageFromPortalToSpace(){		
		String spaceName = "Space12";
		String title1 = "Wiki_move_title_12_1";
		String content1 = "Wiki_move_content_12_1";
		String title2 = "Wiki_move_title_12_2";
		String content2 = "Wiki_move_content_12_2";
		
		addWikiForSpace(spaceName, title1, content1);
		goToWiki();
		addBlankWikiPage(title2, content2, 0);

		
		info("Move page2 of Intranet to page1 of Space");
		movePage(title2, title1, spaceName);
		waitForAndGetElement(ELEMENT_DISPLAY_MODE.replace("${space}", spaceName));
		
		goToWiki();
		waitForElementNotPresent(By.linkText(title2));
		
		magMem.goToAllSpaces();
		magMem.deleteSpace(spaceName, 180000);
	}
	
	/**CaseId: 70361
	 * Move page has the same name with page in target space
	 */
	@Test
	public void test07_MovePageDuplicateName(){
		String spaceName1 = "Space131";
		String title1 = "Wiki_move_title_13_1";
		String content1 = "Wiki_move_content_13_1";
		
		String spaceName2 = "Space132";
		String title2 = "Wiki_move_title_13_2";
		String content2 = "Wiki_move_content_13_2";
		
		addWikiForSpace(spaceName1, title1, content1);
		addWikiForSpace(spaceName2, title1, content2);

		info("Move page2 of space2 to page1 of space1");
		movePage(title1, title1, spaceName1, false);
		waitForTextPresent(MESSAGE_MOVE_PAGE_DUPLICATE_TITLE);
		
		info("Rename page2 of space2");
		click(ELEMENT_RENAME_LINK_WHEN_MOVE_PAGE);
		waitForAndGetElement(ELEMENT_DISPLAY_MODE.replace("${space}", spaceName2));
		assert getValue(ELEMENT_TITLE_WIKI_INPUT).contains(title1);
		addWikiPageSourceEditor(title2, null);
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		
		magMem.goToAllSpaces();
		magMem.deleteSpace(spaceName1, 180000);
		magMem.deleteSpace(spaceName2, 180000);
	}
	
	/**CaseId: 70362
	 * Move page when user has no edit permission at destination page
	 */
	@Test
	public void test08_MovePageWhenNotHaveEditPermissionAtDestPage(){
		String spaceName = "Space14";
		String title1 = "Wiki_move_title_14_1";
		String content1 = "Wiki_move_content_14_1";
		String title2 = "Wiki_move_title_14_2";
		String content2 = "Wiki_move_content_14_2";
		
		goToWiki();
		addBlankWikiPage(title1, content1, 0);		
		magAc.signOut();
		
		magAc.signIn("mary", "gtn");
		addWikiForSpace(spaceName, title2, content2);
		
		info("Move page2 of space to page1 of Intranet");
		movePage(title2, title1, "Intranet", false);
		waitForTextPresent(MESSAGE_NO_PERMISSION_MOVE_PAGE);
		button.ok();
		click(ELEMENT_CANCEL_BUTTON_MOVE_PAGE);

		magMem.goToAllSpaces();
		magMem.deleteSpace(spaceName, 180000);
		magAc.signOut();
		goToWikiPage("Wiki Home/" + title1, ManageAccount.userType.ADMIN);
		deleteCurrentWikiPage();
	}
	
	/**CaseId: 70073
	 * Check permalink when user is member of page
	 */
	@Test
	public void test09_CheckPermalinkWithMemberOfPage(){
		String title = "Wiki_sniff_permalink_title_02";
		String content = "Wiki_sniff_permalink_content_02";
		
		addBlankWikiPage(title, content, 0);
		goToPermalink();
		String permalink = getPermalink();
		dialog.closeMessageDialog();
		magAc.signOut();
		
		goToWikiByPermalink("demo", permalink, true, content);
		
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		deleteCurrentWikiPage();		
	}
	
	/**CaseId: 70075
	 * Check permalink when user is not member of page
	 */
	@Test
	public void test10_CheckPermalinkWithNotMemberOfPage(){
		String title = "Wiki_sniff_permalink_title_03";
		String content = "Wiki_sniff_permalink_content_03";
		
		addBlankWikiPage(title, content, 0);
		deletePagePermission("any");
		goToPermalink();
		String permalink = getPermalink();
		dialog.closeMessageDialog();
		magAc.signOut();
		
		goToWikiByPermalink("demo", permalink, false, content);
		
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		deleteCurrentWikiPage();
	}
	
	/**CaseId: 70251
	 * Check permalink when user is member of space
	 */
	@Test
	public void test11_CheckPermalinkWithUserMemberOfSpace(){
		String spaceName = "Space04";
		String title = "Wiki_sniff_permalink_title_04";
		String content = "Wiki_sniff_permalink_content_04";
		
		magMem.goToAllSpaces();
		magMem.addNewSpace(spaceName, "", "Visible", "Open", "", "");
		magAc.signOut();
		
		info("User demo joint in space");
		magAc.signIn("demo", "gtn");
		magMem.joinOpenSpace(spaceName);
		magAc.signOut();
		
		info("Add new wiki page in space");
		magAc.signIn("john", "gtn");
		magMem.goToMySpacePage();
		goToWikiFromSpace(spaceName);
		addBlankWikiPage(title, content, 0);
		goToPermalink();
		String permalink = getPermalink();
		dialog.closeMessageDialog();
		magAc.signOut();
		
		goToWikiByPermalink("demo", permalink, true, content);
		
		magAc.signIn("john", "gtn");
		magMem.goToAllSpaces();
		magMem.deleteSpace(spaceName, 180000);
	}
	
	/**CaseId: 70252
	 * Check permalink when user is not member of space
	 */
	@Test
	public void test12_CheckPermanlinkWithUserNotMemberOfSpace(){
		String spaceName = "Space05";
		String title = "Wiki_sniff_permalink_title_05";
		String content = "Wiki_sniff_permalink_content_05";
		
		addWikiForSpace(spaceName, title, content);
		goToPermalink();
		String permalink = getPermalink();
		dialog.closeMessageDialog();
		magAc.signOut();
		
		goToWikiByPermalink("demo", permalink, false, content);
		
		magAc.signIn("john", "gtn");
		magMem.goToAllSpaces();
		magMem.deleteSpace(spaceName, 180000);
	}
	
	/**CaseId: 70286 + 70284
	 * Check when change link is public -> check when change link is restricted
	 */
	@Test
	public void test13_CheckWhenChangePermalinkStatus(){
		String title = "Wiki_sniff_permalink_title_06";
		String content = "Wiki_sniff_permalink_content_06";
		String user = "demo";
		
		info("Create new page at restricted status");
		addBlankWikiPage(title, content, 0);
		deletePagePermission("any");
		goToPermalink();
		String permalink = getPermalink();
		dialog.closeMessageDialog();
		magAc.signOut();		
		goToWikiByPermalink(user, permalink, false, content);
		
		info("Make public page");
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		makePublicPage();
		magAc.signOut();	
		
		info("User demo can view page from permalink");
		goToWikiByPermalink(user, permalink, true, content);
		
		info("Make restricted page");
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		makeRestrictedPage();
		magAc.signOut();
		goToWikiByPermalink(user, permalink, false, content);
		
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		deleteCurrentWikiPage();	
	}
	
	/**CaseId: 70289
	 * Change permission for page in Permalink page
	 */
	@Test
	public void test14_ChangePermissionOfPageInPermalink_SelectUser(){
		String title = "Wiki_sniff_permalink_title_07";
		String content = "Wiki_sniff_permalink_content_07";
		String[] userGroup1 = {"mary"};
		
		addBlankWikiPage(title, content, 0);
		deletePagePermission("any");
		
		goToPermissionFromPermalink();
		info("Select user");
		click(ELEMENT_SELECT_USER);
		selectUserPermission(userGroup1[0], 1);
		click(button.ELEMENT_ADD_BUTTON);
		button.save();
		magAc.signOut();
		
		info("Check user can view page");
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.PUBLISHER);
		magAc.signOut();
		
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		deleteCurrentWikiPage();	
	}
	
	@Test
	public void test14_ChangePermissionOfPageInPermalink_SelectGroup(){
		String title = "Wiki_sniff_permalink_title_07";
		String content = "Wiki_sniff_permalink_content_07";
		String[] userGroup2 = {"Development"};
		
		addBlankWikiPage(title, content, 0);
		deletePagePermission("any");
		
		goToPermissionFromPermalink();
		info("Select group");
		click(ELEMENT_SELECT_GROUP);
		selectGroupPermission(userGroup2[0]);
		click(button.ELEMENT_ADD_BUTTON);
		button.save();
		magAc.signOut();
		
		info("Check user can view page");
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.DEVELOPER);
		magAc.signOut();
		
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		deleteCurrentWikiPage();	
	}
	
	@Test
	public void test14_ChangePermissionOfPageInPermalink_SelectMembership(){
		String title = "Wiki_sniff_permalink_title_07";
		String content = "Wiki_sniff_permalink_content_07";
		String[] userGroup3 = {"Platform/Content Management", "author"}; 
		
		addBlankWikiPage(title, content, 0);
		deletePagePermission("any");
		
		goToPermissionFromPermalink();
		info("Select membership");
		click(ELEMENT_SELECT_MEMBERSHIP);
		selectGroupMembership(userGroup3[0], userGroup3[1]);
		click(button.ELEMENT_ADD_BUTTON);
		button.save();
		magAc.signOut();
		
		info("Check user can view page");
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.AUTHOR);
		magAc.signOut();
		
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		deleteCurrentWikiPage();	
	}
	
	/**CaseId: 68841
	 * Watch Page
	 */
	@Test
	public void test15_WatchUnwatchPage(){
		String title = "Wiki_watch_title_15";
		String content = "Wiki_watch_content_15";
		String newTitle = "Wiki_watch_title_15_update";
		String newContent = "Wiki_watch_content_15_update";
				
		addBlankWikiPage(title, content, 0);	
		watchWikiPage();
		magAc.updateUserProfile(null, null, null, "exomailtest01@gmail.com");
		goToWikiPage("Wiki Home/" + title);
		editPageWithCheckPublicActivity(newTitle, newContent);

		String handlesBefore = driver.getWindowHandle();
		goToMail();
		checkAndDeleteMail(By.xpath("//b[contains(text(), '" + newTitle + "')]"), newContent);
		
		driver.switchTo().window(handlesBefore);
		unwatchWikiPage();
		deleteCurrentWikiPage();
	}
}
