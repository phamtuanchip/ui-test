package org.exoplatform.selenium.platform.ks.functional.forum.topic;

import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.ks.ForumManageCategory.addCategoryInForum;
import static org.exoplatform.selenium.platform.ks.ForumManagement.*;
import static org.exoplatform.selenium.platform.ks.TopicManagement.*;
import org.exoplatform.selenium.platform.ks.ForumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
/**
 * 
 * @author thuntn
 * @date: 26/12/2012
 */
public class KS_Forum_Topic_Sort extends ForumBase {
	public String ELEMENT_FORUM = "//a[@title='{$forum}']";
	public String ELEMENT_CATEGORY = "//a[@title='{$category}']";

	@Test
	public void test01_SortTopicByRating() {
		String category = "Sort category 01";
		String[] audience = {};
		String forum = "Sort forum 01";
		String[] add = {forum, "1", "", "", ""};
		String[] userGroup = {};
		String topic1 = "Sort topic 01";	
		String topic2 = "Sort topic 02";
		String topic3 = "Sort topic 03";
		String[] user_topic = {};
		String bTopic1= "//tbody/tr[1]//a[contains(text(),'{$topic}')]";
		String bTopic2= "//tbody/tr[2]//a[contains(text(),'{$topic}')]";
		String bTopic3= "//tbody/tr[3]//a[contains(text(),'{$topic}')]";

		//Add category, forum, topic
		addCategoryInForum(category, "1", 0, audience, "", 0);
		addForum(category, add, 0, userGroup, true, "", "", false, 0);

		//Start 3 topic
		click(ELEMENT_START_TOPIC_BUTTON);
		startTopic(topic1, topic1, "", "", "", "", "", "", 0, user_topic);
		click(ELEMENT_START_TOPIC_BUTTON);
		startTopic(topic2, topic2, "", "", "", "", "", "", 0, user_topic);
		click(ELEMENT_START_TOPIC_BUTTON);
		startTopic(topic3, topic3, "", "", "", "", "", "", 0, user_topic);

		//Vote topics
		click(By.linkText(topic1));
		voteTopic(2);
		click(ELEMENT_FORUM.replace("{$forum}", forum));
		click(By.linkText(topic2));
		voteTopic(3);
		click(ELEMENT_FORUM.replace("{$forum}", forum));	  
		click(By.linkText(topic3));
		voteTopic(4);
		click(ELEMENT_FORUM.replace("{$forum}", forum));	 
		//Check expected result

		//Check order of topics before sorting 
		waitForElementPresent(bTopic1.replace("{$topic}", topic3));
		waitForElementPresent(bTopic2.replace("{$topic}", topic2));
		waitForElementPresent(bTopic3.replace("{$topic}", topic1));

		//Sort and check order of topics
		click(ELEMENT_RATING_TOPIC);
		waitForElementPresent(bTopic1.replace("{$topic}", topic1));
		waitForElementPresent(bTopic2.replace("{$topic}", topic2));
		waitForElementPresent(bTopic3.replace("{$topic}", topic3));

		//Sort again and check order of topics
		click(ELEMENT_RATING_TOPIC);
		waitForElementPresent(bTopic1.replace("{$topic}", topic3));
		waitForElementPresent(bTopic2.replace("{$topic}", topic2));
		waitForElementPresent(bTopic3.replace("{$topic}", topic1));

		click(ELEMENT_CATEGORY.replace("{$category}", category));
		deleteCategory(category);
		assert false;
	}
	@BeforeMethod
	public void beforeTest(){
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		signIn("john", "gtn");
		goToForums();
	}

	@AfterMethod
	public void afterTest(){
		signOut();
		driver.manage().deleteAllCookies();
		driver.quit();
	}

}
