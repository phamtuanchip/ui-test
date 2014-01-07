package org.exoplatform.selenium.platform.wiki.functional.Activity;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author havtt
 * @date 05-Nov-2013
 *
 */

public class Wiki_Activity_Delete extends BasicAction{
	
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
	 * Delete a Wiki activity from activity stream by owner
	 * 
	 * @author havtt
	 * @date 04-Nov-2013
	 * caseID 77127
	 * 
	 */
	@Test(groups="pending")
	//Related issue: WIKI-336
	public void test01_DelWikiActivityfromSpaceAS() {
		String SPACE_01 = "Space01";
		String WIKI_CONTENT = "AStream_01";
		
		info("User create a new space");
		magSpace.goToAllSpaces();
		magSpace.addNewSpace(SPACE_01, SPACE_01);
		
		info("User go to Space Wiki");
		magSpace.goToSpaceMenu("Wiki");
		
		info("Create a wiki page");
		addBlankWikiPage(WIKI_CONTENT,WIKI_CONTENT,2);
		
		info("User go to Space Activity Stream");
		magSpace.goToSpaceMenu("Activity Stream");
		
		info("User delete activity on AS");
		as.deleteActivity(WIKI_CONTENT);
		
		info("Restore data");
		magSpace.restoreData(SPACE_01);
	}
	
	/**
	 * Delete a Wiki activity after remove wiki's page of space
	 * 
	 * @author havtt
	 * @date 04-Nov-2013
	 * caseID 77615
	 * 
	 */
	
	@Test(groups="pending")
	//Related issue: WIKI-336
	public void test03_DelWikiActivityAfterwikiPageRemoved() {
		
	}
}
