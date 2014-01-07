package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author HangNTT
 * date: 23/10/2013
 */
public class HomePageGadget extends PlatformBase{

	//-------Invitation Gadget --------
	public By ELEMENT_SEND_BUTTON = By.id("send");
	public By ELEMENT_INVITATION_GADGET_CLOUD = By.xpath("//*[@id='OfficeRight']//*[@class='UIGadgetPortlet']");
	public By ELEMENT_INVITATION_GADGET_INDEX_DEFAULT_CLOUD = By.xpath("//*[@id='OfficeRight']//*[contains(@class,'UIRowContainer')]/div[2]//*[@class='UIGadgetPortlet']");
	public By ELEMENT_INVITATION_GADGET = By.id("InvitationsPortlet");
	public String ELEMENT_SHOW_CONNECTIONS_REQUEST_USER = "//div[@id='InvitationsPortlet']//div[@class='peopleInviteName']//div[text()='${nameinvitation}']";
	public String ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE= "//div[@id='InvitationsPortlet']//div[@class='spaceInviteInfo']//div[text()='${namespace}']";
	public String ELEMENT_VERIFY_STATUS_SPACE = "//div[@id='InvitationsPortlet']//div[@class='spaceInviteInfo']//div[text()='${namespace}']/../div[@class='spaceproperties']/div[@class='spacevisibility' and contains(text(),'${statusspace}')]";
	public String ELEMENT_SHOW_ACCETPS_BUTTON = "//div[@id='InvitationsPortlet']//div[@class='peopleInviteInfo']//div[text()='${peopleName}']/..//a[text()='Accept']";
	public String ELEMENT_REMOVE_INVITATION_BUTTON = "//div[@id='InvitationsPortlet']//div[@class='peopleInviteInfo']//div[text()='${peopleName}']/..//i[@class='uiIconClose']";
	public String ELEMENT_TITLE_OF_GAGDET = "//div[@id='InvitationsPortlet']/..//span[text()='${number}']";
	public By ELEMENT_FRAME_INVITATION_GADGET_CLOUD = By.id("remote_iframe_0");
	public By ELEMENT_MESSAGE_INVITATION_GADGET = By.id("Message");
	public String ELEMENT_GMAIL_EMAIL = "//span/b[contains(text(),'${user} has invited you to try eXo')]";
	public By ELEMENT_GMAIL_SEND_USER = By.xpath("//*[@class='gD']");

	//-------Getting Started Gadget -------- 
	public By ELEMENT_GETTING_STARTED_GADGET_INDEX_DEFAULT_CLOUD = By.xpath("//*[@id='OfficeRight']//*[contains(@class,'UIRowContainer')]/div[1]//*[@class='GettingStartedContainer']");
	public By ELEMENT_GETTING_STARTED_GADGET_FORM = By.id("GettingStartedContainer");
	public By ELEMENT_PROFILE_PICTURE = By.linkText("Add a profile picture");
	public By ELEMENT_CONNECT_TO_COWORKERS = By.linkText("Connect to coworkers");
	public By ELEMENT_JOIN_A_SPACE = By.linkText("Join a space");
	public By ELEMENT_POST_AN_ACTIVITY = By.linkText("Post an activity");
	public By ELEMENT_UPLOAD_A_DOCUMENT = By.linkText("Upload a document");
	public By ELEMENT_PROFILE_PAGE = By.id("UIProfilePortlet");
	public By ELEMENT_ALL_PEOPLE = By.id("UIAllPeoplePortlet");
	public By ELEMENT_ALL_SPACE = By.id("UIAllSpacesPortlet");
	public By ELEMENT_PERSONAL_DOCUMENT = By.id("UIJCRExplorer");
	public String ELEMENT_FINISH_UPLOAD_FILE = "//ul[@id='gsList']//li[@class='${status}']/a[text()='Upload a document']";
	public String ELEMENT_FINISH_JOIN_TO_SPACE = "//ul[@id='gsList']//li[@class='${status}']/a[text()='Join a space']";
	public By ELEMENT_INPROGRESS_COMPLETE = By.xpath("//div[@id='progress-block']//div[@id='progress-rate']/../../div[@id='progress-label' and contains(text(),'100 %')]");
	public By ELEMENT_CLOSE_GADGET_GETTING_STARTED = By.xpath("//div[@id='DeleteLink']//button[text()='Close']");

	//-------Suggestions Gadget -------- 
	public By ELEMENT_SUGGESTION_GADGET_FORM = By.xpath("//div[@class='uiBox uiSuggestions']//h6[@class='title center' and text()='Suggestions']");
	public String ELEMENT_VERIFY_USER_SUGGESTIONS = "//*[@id='peopleSuggest']//*[@class='peopleName']/a[contains(text(),'${peopleName}')]";
	public String ELEMENT_VERIFY_SPACE_SUGGESTIONS = "//*[@id='spaceSuggest']//*[@class='spaceInfo']/div[@class='spaceName' and text()='${spaceName}']";
	public String ELEMENT_CONNECT_USER_SUGGESTIONS = "//*[@id='peopleSuggest']//*[@class='peopleName']/a[contains(text(),'${peopleName}')]/../..//a[text()='Connect']";
	public String ELEMENT_CONNECT_SPACE_SUGGESTIONS = "//*[@id='spaceSuggest']//*[@class='spaceInfo']/div[@class='spaceName' and contains(text(),'${spaceName}')]/../..//a[text()='Request']";
	public String ELEMENT_REMOVE_USER_SUGGESTIONS = "//*[@id='peopleSuggest']//*[@class='peopleName']/a[contains(text(),'${peopleName}')]/../..//i[@class='uiIconClose']";
	public String ELEMENT_REMOVE_SPACE_SUGGESTIONS="//*[@id='spaceSuggest']//*[@class='spaceInfo']/div[@class='spaceName' and contains(text(),'${spaceName}')]/../..//i[@class='uiIconClose']";
	public String ELEMENT_VERIFY_USER_SUGGESTIONS_INDEX = "//*[@id='peopleSuggest']//li[${index}]//*[@class='peopleName']/a";
	public String ELEMENT_VERIFY_SPACE_SUGGESTIONS_INDEX = "//*[@id='spaceSuggest']//li[${index}]//*[@class='spaceInfo']/*[@class='spaceName']";

	//-------------------Who'sOnline gadget-----------------------------------
	public By ELEMENT_WHOISONLINE_GADGET = By.id("onlineContent");
	public String ELEMENT_ONLINE_USER_ACC_IMG = "//*[@id='tipName']//a[@href='/portal/intranet/activities/${acc}']/img";
	public String ELEMENT_ONLINE_USER_TITLE = "//*[@id='tipName']//td[2]/a[@href='/portal/intranet/activities/${acc}']";
	public String ELEMENT_ONLINE_USER_AVATAR = "//ul[@id='onlineList']//a[@class='avatarXSmall' and @href='/portal/intranet/profile/${acc}']";
	public String ELEMENT_WHOISONLINE_CONNECT_BUTTON = "//*[@id='tiptip_content']//div[@class='connect btn btn-primary' and @data-action='Invite:${acc}']";
	//My Profile tab
	public String ELEMENT_PROFILE_TAB_USER_INFO = "//*[@id='UIUserNavigationPortlet']/ul[@class='nav nav-tabs userNavigation']//a[@href='/portal/intranet/profile/${acc}']";
	//My activity stream tab
	public String ELEMENT_MY_AS_TAB = "//*[@id='UIUserNavigationPortlet']//a[@href='/portal/intranet/activities/${acc}']";

	//-------------------------------------------------------------//

	public HomePageGadget(WebDriver dr) {
		driver = dr;
	}

	/**
	 * Accept an invitation
	 * @param peopleName
	 */
	public void acceptInvitationGadget(String peopleName) {
		info("-- Accept an invitation --");
		mouseOver(ELEMENT_SHOW_ACCETPS_BUTTON.replace("${peopleName}", peopleName),true);
		WebElement element = waitForAndGetElement(ELEMENT_SHOW_ACCETPS_BUTTON.replace("${peopleName}", peopleName), DEFAULT_TIMEOUT,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		//click(SHOW_ACCETPS_BUTTON.replace("${peopleName}", peopleName));
		waitForElementNotPresent(ELEMENT_SHOW_ACCETPS_BUTTON.replace("${peopleName}", peopleName));
		//waitForAndGetElement(REMOVE_INVITATION_BUTTON.replace("${peopleName}", peopleName));
	}

	/**
	 * Remove an invitation
	 * @param peopleName
	 */
	public void removeInvitationGadget(String peopleName){
		info("-- Remove an invitation --");
		//mouseOver(REMOVE_INVITATION_BUTTON.replace("${peopleName}", peopleName),true);
		WebElement element = waitForAndGetElement(ELEMENT_REMOVE_INVITATION_BUTTON.replace("${peopleName}", peopleName), DEFAULT_TIMEOUT,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		//click(REMOVE_INVITATION_BUTTON.replace("${peopleName}", peopleName));
		waitForElementNotPresent(ELEMENT_REMOVE_INVITATION_BUTTON.replace("${peopleName}", peopleName));
	}


	/**
	 * Connect user Suggestions
	 * @param peopleName
	 */
	public void connectUserSuggestionsGadget(String peopleName) {
		info("-- Connect Suggestions user --");
		mouseOver(ELEMENT_CONNECT_USER_SUGGESTIONS.replace("${peopleName}", peopleName),true);
		WebElement element = waitForAndGetElement(ELEMENT_CONNECT_USER_SUGGESTIONS.replace("${peopleName}", peopleName), DEFAULT_TIMEOUT,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		waitForElementNotPresent(ELEMENT_CONNECT_USER_SUGGESTIONS.replace("${peopleName}", peopleName));

	}
	/**
	 * Remove user suggestions
	 * @param peopleName
	 */
	public void removeUserSuggestionsGadget(String peopleName){
		info("-- Remove an invitation --");
		mouseOver(ELEMENT_REMOVE_USER_SUGGESTIONS.replace("${peopleName}", peopleName),true);
		WebElement element = waitForAndGetElement(ELEMENT_REMOVE_USER_SUGGESTIONS.replace("${peopleName}", peopleName), DEFAULT_TIMEOUT,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		waitForElementNotPresent(ELEMENT_REMOVE_USER_SUGGESTIONS.replace("${peopleName}", peopleName));
		Utils.pause(5000);
	}

	/**
	 * Connect space Suggestions
	 * @param peopleName
	 */
	public void connectSpaceSuggestionsGadget(String spaceName) {
		info("-- Connect Suggestions user --");
		mouseOver(ELEMENT_CONNECT_SPACE_SUGGESTIONS.replace("${Name}", spaceName),true);
		WebElement element = waitForAndGetElement(ELEMENT_CONNECT_SPACE_SUGGESTIONS.replace("${spaceName}", spaceName), DEFAULT_TIMEOUT,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		waitForElementNotPresent(ELEMENT_CONNECT_SPACE_SUGGESTIONS.replace("${spaceName}", spaceName));

	}
	/**
	 * Remove space suggestions
	 * @param peopleName
	 */
	public void removeSpaceSuggestionsGadget(String spaceName){
		info("-- Remove an invitation --");
		mouseOver(ELEMENT_REMOVE_SPACE_SUGGESTIONS.replace("${Name}", spaceName),true);
		WebElement element = waitForAndGetElement(ELEMENT_REMOVE_SPACE_SUGGESTIONS.replace("${spaceName}", spaceName), DEFAULT_TIMEOUT,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		waitForElementNotPresent(ELEMENT_REMOVE_SPACE_SUGGESTIONS.replace("${spaceName}", spaceName));
	}

	/**
	 * Connect to another account from Who's Online gadget
	 * @author havtt
	 * @date 06-Nov-2013
	 * 
	 * @param username
	 */
	public void connectPeoplefromWhoisOnlineGadget(String username){
		info("--Connecting from Who's Online gadget--");
		mouseOver(ELEMENT_ONLINE_USER_AVATAR.replace("${acc}",username),true);
		WebElement element = waitForAndGetElement(ELEMENT_WHOISONLINE_CONNECT_BUTTON.replace("${acc}",username), DEFAULT_TIMEOUT,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		Utils.pause(3000);
	}

	/**
	 * Access to another account's activity stream from Who's Online gadget
	 * @author havtt
	 * @date 06-Nov-2013
	 * 
	 * @param username
	 */
	public void accessASfromWhoisOnlineGadget(String username){
		info("--Accessing AS of others from Who's Online gadget--");
		//mouseOver(ELEMENT_ONLINE_USER_AVATAR.replace("${acc}",username),true);
		//click(ELEMENT_ONLINE_USER_AVATAR.replace("${acc}",username));
		WebElement element = waitForAndGetElement(By.xpath(ELEMENT_ONLINE_USER_AVATAR.replace("${acc}",username)));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	/**
	 * Check info of user displayed on Who's online Gadget
	 * @author havtt
	 * @date 06-Nov-2013
	 * @param userName
	 */
	public void checkUserInfoOnWhoisOnlineGadget(String userName){
		waitForAndGetElement(ELEMENT_WHOISONLINE_GADGET);
		mouseOver(ELEMENT_ONLINE_USER_AVATAR.replace("${acc}",userName),true);
		info("Confirm user avatar");
		waitForAndGetElement(ELEMENT_ONLINE_USER_ACC_IMG.replace("${acc}",userName), DEFAULT_TIMEOUT,1,2);
		info("Confirm user name");
		waitForAndGetElement(ELEMENT_ONLINE_USER_TITLE.replace("${acc}",userName), DEFAULT_TIMEOUT,1,2);
	}
}