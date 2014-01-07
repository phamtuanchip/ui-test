package org.exoplatform.selenium.platform.ecms.wcm;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author vuna2
 *
 */
public class ContentListPreference extends EcmsBase{

	public ContentListPreference(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	Button button = new Button(driver);
	
	//Elements
	public final By ELEMENT_PREFERENCE_LATEST_NEWS_ICON = By.xpath("//*[@data-original-title='RSS Feed']/ancestor::div[contains(@id, 'UICLVPresentation')]/..//*[@class='uiIconSetting']");
	
	//Content List Preference Tab
	public final By ELEMENT_CONTENT_SELECTION_TAB = By.xpath("//*[text()='Content Selection']");
	public final By ELEMENT_DISPLAY_SETTINGS_TAB = By.xpath("//*[text()='Display Settings']");
	public final By ELEMENT_ADVANCED_TAB = By.xpath("//*[text()='Advanced']");
	//Display settings
	public final By ELEMENT_SHOW_TITLE = By.id("UICLVConfigShowTitleFormCheckboxInput");
	public final By ELEMENT_SHOW_IMAGE = By.id("UICLVConfigShowIllustrationFormCheckboxInput");
	public final By ELEMENT_SHOW_SUMMARY = By.id("UICLVConfigShowSummaryFormCheckboxInput");
	public final By ELEMENT_SHOW_HEADER = By.id("UICLVConfigShowHeaderFormCheckboxInput");
	public final By ELEMENT_SHOW_DATE = By.id("UICLVConfigShowDateCreatedFormCheckboxInput");
	public final By ELEMENT_SHOW_LINK = By.id("UICLVConfigShowLinkFormCheckboxInput");
	
	/*=====================================================================*/
	
	//Go to Content List Preference
	public void goToContentListPreference(Object locator, Object preference){
		info("-- Open Content List Preference --");
		mouseOver(locator, true);
		Utils.pause(1000);
		click(preference,2);
		//waitForTextPresent("Content Selection");
	}

	//Set Display options
	public void setDisplayOptions(By option, Object...params){
		Boolean save = (Boolean) (params.length > 0? params[0]: false);
		Boolean cancel = (Boolean) (params.length > 1? params[1]: false);
		
		info("-- Display settings --");
		click(ELEMENT_DISPLAY_SETTINGS_TAB);
		Utils.pause(500);
		WebElement element = waitForAndGetElement(option, 3000, 0, 2);
		if(!element.isSelected()){
			click(option, 2);
		}
		if (save){
			button.save();
		}
		if (cancel){
			button.cancel();
		}
		Utils.pause(500);
	}
}
