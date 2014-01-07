package org.exoplatform.selenium.platform.wiki.functional.Activity;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author havtt
 * @date 05-Nov-2013
 *
 */

public class Wiki_Activity_Update extends BasicAction{

	ManageAccount magAcc;
	HomePageActivity as;
	SpaceManagement magSpace;
	NavigationToolbar navBar;

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		as = new HomePageActivity(driver,this.plfVersion);
		magAcc = new ManageAccount(driver,this.plfVersion);
		magSpace = new SpaceManagement(driver,this.plfVersion);
		navBar = new NavigationToolbar(driver,this.plfVersion);
		magAcc.signIn("fqa", "gtngtn");
	}

	@AfterMethod
	public void afterMethod(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Update wiki's activity after edit wiki page with inactive notification
	 * 
	 * @author havtt
	 * @date 05-Nov-2013
	 * caseID 77122
	 * 
	 */
	@Test(groups="pending")
	//Related issue: WIKI-336
	public void test01_CheckWikiActivityAfterEditWikiPage() {
		String SPACE_01 = "SPACE001";
		String WIKI_CONTENT = "AS_01";
		String WIKI_CONTENT_EDITED = "AS_01_edited";

		info("User create a new space");
		magSpace.goToAllSpaces();
		magSpace.addNewSpace(SPACE_01, SPACE_01);

		info("User go to Space Wiki");
		magSpace.goToSpaceMenu("Wiki");

		info("Create a wiki page");
		addBlankWikiPage(WIKI_CONTENT,WIKI_CONTENT,02);

		info("User post a Wiki activity on AS");
		editWikiPage(WIKI_CONTENT_EDITED, WIKI_CONTENT_EDITED, 0);

		info("User go to Space Activity Stream");
		magSpace.goToSpaceMenu("Activity Stream");

		info("Confirm if Wiki Activity updated and no comment added");
		//Content of Activity updated or not
		as.checkActivityInfoOfWiki(WIKI_CONTENT_EDITED,WIKI_CONTENT_EDITED,"2");
		//Comment of Activity is added or not
		waitForElementNotPresent(By.xpath(as.ELEMENT_WIKI_COMMENT_EDIT_TITLE));
		waitForElementNotPresent(By.xpath(as.ELEMENT_WIKI_COMMENT_EDIT_CONTENT));

		info("User delete activity on AS");
		as.deleteActivity(WIKI_CONTENT_EDITED);

		info("Restore data");
		magSpace.deleteSpace(SPACE_01);
	}
}