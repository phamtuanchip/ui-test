package org.exoplatform.selenium.platform.wiki;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Provides all methods of editing a wiki page directly in the wiki markup language.
 * 
 * 
 */
public class RichTextMode extends Template {
	Button but = new Button(driver);
	
	//Link menu
	public By ELEMENT_LINK = By.xpath("//*[text()='Link']");
	public By ELEMENT_WIKI_PAGE_LINK = By.xpath("//*[text()='Wiki Page...']");
	
	//Add wiki page link popup
	public By ELEMENT_SEARCH_TAB = By.xpath("//div[text()='Search']");
	public By ELEMENT_SEARCH_TEXTBOX = By.xpath("//input[@title='Type a keyword to search for a wiki page']");
	public By ELEMENT_SEARCH_BUTTON = By.xpath("//button[text()='Search']");
	public String ELEMENT_PAGE_SELECTED = "//*[@class='xPagePreview' and @title='${page}']";
	public String ELEMENT_PAGE_SELECTED_PLF41 = "//*[@class='xPagesSelector xPagesSearch' and @aria-hidden='false']//*[@class='xPagePreview' and @title='${page}']";
	public By ELEMENT_LABEL_LINK_TEXTBOX = By.xpath("//input[@title='Type the label of the created link.']");
	public By ELEMENT_TOOLTIP_LINK_TEXTBOX = By.xpath("//input[@title='Type the tooltip of the created link, which appears when mouse is over the link.']");
	
	//Table
	public By ELEMENT_TABLE_LINK = By.xpath("//*[text()='Table']");
	public By ELEMENT_INSERT_TABLE_LINK = By.xpath("//*[text()='Insert Table...']");
	public By ELEMENT_ROW_TEXTBOX = By.xpath("//*[@title='Row count']");
	public By ELEMENT_COLUMN_TEXTBOX = By.xpath("//*[@title='Column count']");
	
	//Macro
	public By ELEMENT_MACRO_LINK = By.xpath("//*[text()='Macro']");
	public By ELEMENT_INSERT_MACRO_LINK = By.xpath("//*[text()='Insert Macro...']");
	public By ELEMENT_MACRO_CATEGORY_SELECT = By.xpath("//select[@title='Select a macro category']");
	public String ELEMENT_MACRO_LABEL = "//*[text()='${macro}']";
	
	//Macro: Tip message
	public By ELEMENT_CONTENT_MESSAGE_TEXTAREA = By.id("pd-content-input");
	public String ELEMENT_TIP_MESSAGE_MACRO = "//*[@class='box tipmessage' and text()='${message}']";
	
	//Macro: Color
	public By ELEMENT_COLOR_TEXTBOX = By.id("pd-name-input");
	public By ELEMENT_COLOR_MESSAGE = By.id("pd-content-input");
	
	/**
	 * Add link to a Wiki page
	 * 
	 * @param search
	 * 			parameter to choose whether to search page link or not
	 * @param page
	 * 			Wiki page that will be the target link
	 * @param label
	 * 			label of link that will be added into Wiki page
	 * @param tooltip
	 * 			
	 */
	public void insertPageLink2WikiPage(boolean search, String page, String label, String tooltip){
		mouseOverAndClick(ELEMENT_LINK);
		mouseOverAndClick(ELEMENT_WIKI_PAGE_LINK);
		Utils.pause(500);
		info("Create link to the page " + page);
		if (search){
			click(ELEMENT_SEARCH_TAB);
			type(ELEMENT_SEARCH_TEXTBOX, page, true);
			click(ELEMENT_SEARCH_BUTTON);
		}
		if(waitForAndGetElement(ELEMENT_PAGE_SELECTED.replace("${page}", page), 5000,0)!=null)
			click(ELEMENT_PAGE_SELECTED.replace("${page}", page));
		else
			click(ELEMENT_PAGE_SELECTED_PLF41.replace("${page}", page));
		click(but.ELEMENT_SELECT_BUTTON);
		type(ELEMENT_LABEL_LINK_TEXTBOX, label, true);
		type(ELEMENT_TOOLTIP_LINK_TEXTBOX, tooltip, true);
		Utils.pause(500);
		click(but.ELEMENT_CREATE_LINK_BUTTON);
		waitForElementNotPresent(but.ELEMENT_CREATE_LINK_BUTTON);
	}
	
	/**
	 * Select a macro in a Wiki page editor
	 * 
	 * @param cat
	 * 			category to which a macro that will be chosen belongs
	 * @param macro
	 * 			macro name that will be chosen
	 * 
	 * @see #createTipMessageMacro(String)
	 * @see #createColorMacro(String, String)
	 */
	public void goToSelectAMacro(String cat, String macro){
		info("Go to a macro: " + macro);
		mouseOverAndClick(ELEMENT_MACRO_LINK);
		mouseOverAndClick(ELEMENT_INSERT_MACRO_LINK);
		select(ELEMENT_MACRO_CATEGORY_SELECT, cat);
		Utils.pause(1000);
		click(ELEMENT_MACRO_LABEL.replace("${macro}", macro));
		click(but.ELEMENT_SELECT_BUTTON);
		Utils.pause(3000);
	}
	
	/**
	 * Add macro: "Tip Message"
	 * 
	 * @param message
	 * 			 message that will be displayed in macro
	 */
	public void createTipMessageMacro(String message){
		goToSelectAMacro("Formatting", "Tip Message");
		type(ELEMENT_CONTENT_MESSAGE_TEXTAREA, message, true);
		click(but.ELEMENT_CREATE_MACRO_BUTTON);
		waitForAndGetElement(ELEMENT_TIP_MESSAGE_MACRO.replace("${message}", message));
	}
	
	/**
	 * Add macro: "color" into a Wiki page
	 * 
	 * @param color
	 * 			color setting of macro
	 * @param message
	 * 			message setting of macro
	 */
	public void createColorMacro(String color, String message){
		goToSelectAMacro("Formatting", "Color");
		type(ELEMENT_COLOR_TEXTBOX, color, true);
		type(ELEMENT_COLOR_MESSAGE, message, true);
		click(but.ELEMENT_CREATE_MACRO_BUTTON);
	}
	
	/**
	 * Add table to a Wiki page
	 * 
	 * @param rows
	 * 			Number of rows that will be added in the table
	 * @param columns
	 * 			Number of columns that will be added in the table
	 */
	public void insertTable2WikiPage(String rows, String columns){
		mouseOverAndClick(ELEMENT_TABLE_LINK);
		mouseOverAndClick(ELEMENT_INSERT_TABLE_LINK);
		type(ELEMENT_ROW_TEXTBOX, rows, true);
		type(ELEMENT_COLUMN_TEXTBOX, columns, true);
		click(but.ELEMENT_INSERT_TABLE);
		waitForAndGetElement("//table");
	}
	
	/**
	 * Click End then Enter in content frame in Rich text mode of Wiki page editor
	 */
	public void typeEnterInRichText(){
		try {
			WebElement inputsummary = null;
			driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
			inputsummary = driver.switchTo().activeElement();
			inputsummary.click();
			inputsummary.sendKeys(Keys.END);
			inputsummary.sendKeys(Keys.ENTER);
			switchToParentWindow();
			Utils.pause(1000);
			driver.switchTo().defaultContent();
		}catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			driver.switchTo().defaultContent();
			typeEnterInRichText();
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			driver.switchTo().defaultContent();
			typeEnterInRichText();
		}catch (WebDriverException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			driver.switchTo().defaultContent();
			typeEnterInRichText();
		}
		finally {
			loopCount = 0;
		}
	}
}
