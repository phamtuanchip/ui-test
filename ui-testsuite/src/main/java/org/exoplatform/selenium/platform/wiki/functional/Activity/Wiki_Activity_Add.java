package org.exoplatform.selenium.platform.wiki.functional.Activity;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.wiki.Permalink;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author havtt
 * @date 05-Nov-2013
 *
 */

public class Wiki_Activity_Add extends Permalink{
	
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
		magSpace = new SpaceManagement(driver,this.plfVersion);
		navBar = new NavigationToolbar(driver,this.plfVersion);
		magAcc = new ManageAccount(driver,this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethod(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	/**
	 * Add a wiki's activity after create a wiki page in space
	 * 
	 * @author havtt
	 * @date 04-Nov-2013
	 * caseID 77129
	 * 
	 */
	@Test(groups="pending")
	//Related issue: WIKI-336
	public void test01_AddWikiActivityfromSpaceWiki() {
		String SPACE_01 = "SPACE01";
		String WIKI_CONTENT = "AS_01";
		
		info("User create a new space");
		magSpace.goToAllSpaces();
		magSpace.addNewSpace(SPACE_01, SPACE_01);
		
		info("User go to Space Wiki");
		magSpace.goToSpaceMenu("Wiki");
		
		info("Create a wiki page and make Space Wiki public");
		addBlankWikiPage(WIKI_CONTENT,WIKI_CONTENT,2);
		makePublicPage(true);
		
		info("Go to Homepage Activity Stream to check activity update");
		navBar.goToHomePage();
		waitForTextPresent(WIKI_CONTENT);
		
		info("Restore data");
		magSpace.restoreData(SPACE_01);
	}
}
