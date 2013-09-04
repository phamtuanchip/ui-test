package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;




/**
 * Update: vuna2
 * @author lienTM
 *
 */
public class HomePageActivity extends PlatformBase{

	Button button;
	Dialog dialog;
	ManageAlert alert;
	ForumManagePost post;

	public HomePageActivity(WebDriver dr){
		driver = dr;
		post = new ForumManagePost(driver);
	}
	//Comment box
	public final String ELEMENT_ACTIVITY_COMMENT_CONTENT = "//*[contains(text(),'${title}')]/../../../..//*[@class='contentComment' and contains(text(), '${comment}')]";
	public final String ELEMENT_ACTIVITY_DELETE_COMMENT_ICON = "//*[@class='contentComment' and contains(text(), '${comment}')]/../..//*[contains(@id, 'DeleteCommentButton')]";
	public final String DATA_MESSAGE_CONFIRM_DELETE_COMMENT = "Are you sure you want to delete this comment?";

	public final String ELEMENT_COMMENTBOX="//*[text()='${title}']/../../../..//div[@class='exo-mentions']/div[contains(@id,'DisplayCommentTextarea')]";
	public final String ELEMENT_ICON_COMMENT = "//*[text()='${title}']/../../../..//div[@class='actionBar clearfix forumActivityIcon']//i[@class='uiIconComment uiIconLightGray']";

	//Content activity
	public final String ELEMENT_CONTENT_NAME = "//a[@title='@{fileName}']";
	public final String ELEMENT_CONTENT_TYPE_ICON = "//a[@title='@{fileName}']/../..//*[@class='${icon}']";
	public final String ELEMENT_CONTENT_TYPE = "//a[@title='@{fileName}']/..//*[@class='versionFile' and contains(text(), '${type}')]";
	public final String ELEMENT_CONTENT_DESCRIPTION = "//a[@title='@{fileName}']/..//*[@class='descriptionText' and text()='${des}']";
	public final String ELEMENT_CONTENT_VERSION = "//a[@title='@{fileName}']/..//*[contains(text(), '${version} -')]";
	public final String ELEMENT_CONTENT_STATUS = "//a[@title='@{fileName}']/..//*[contains(text(), '${status}')]";
	public final String ELEMENT_CONTENT_SUMMARY = "//*[@title='@{fileName}']/..//p";
	public final String ELEMENT_CONTENT_COMMENT_EDIT_TITLE = "//*[text()='@{fileName}']/../../../..//*[@class='commentRight']//*[contains(text(),'Title has been updated to: ${title}')]";
	public final String ELEMENT_CONTENT_COMMENT_ADD_A_TAG = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Tag: ${tags} has been added.']";
	public final String ELEMENT_CONTENT_COMMENT_ADD_TAGS = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Tags: ${tags} have been added.']";
	public final String ELEMENT_CONTENT_COMMENT_PUBLISH = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Publication has been published.']";
	public final String ELEMENT_CONTENT_EDIT_LINK = "//a[@title='@{fileName}']/../../../..//*[@class='uiIconEdit uiIconLightGray']";
	public final String ELEMENT_CONTENT_VIEW_LINK = "//a[@title='@{fileName}']/../../../..//*[@class='uiIconWatch uiIconLightGray']";
	public final String ELEMENT_CONTENT_COMMENT_MOVING = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Publication has been moved to: ${path}']";

	//File activity
	public final String ELEMENT_FILE_SIZE = "//a[@title='@{fileName}']/..//*[@class='versionFile' and contains(text(), '${size}')]";
	public final String ELEMENT_FILE_COMMENT_ADD_CATEGORY = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Category: ${category} has been added.']";
	public final By ELEMENT_FILE_VIEW_POPUP = By.id("UISocialPopupWindow");
	public final String ELEMENT_FILE_VIEW_NAME = "//*[@id='UISocialPopupWindow']//*[text()='${fileName}']";
	public final String ELEMENT_FILE_COMMENT_MOVING = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='File has been moved to: ${path}']";

	//Edit screen from click Edit in activity
	public final By ELEMENT_CONTENT_EDIT_SCREEN_FROM_ACTIVITY = By.id("UIJCRExplorerPortlet");
	public final By ELEMENT_CONTENT_EDIT_SCREEN_BACK = By.xpath("//a[@data-original-title='Back']");

	//Wiki activity
	public final String ELEMENT_WIKI_COMMENT_EDIT_TITLE = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'title has been updated to: ${title}')]";
	public final String ELEMENT_WIKI_COMMENT_EDIT_CONTENT = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'content has been edited')]";
	public final String ELEMENT_ACTIVITY_WIKI_TITLE = "//*[@class='linkTitle' and text()='${title}']";
	public final String ELEMENT_ACTIVITY_WIKI_CONTENT = "//*[@class='linkTitle' and text()='${title}']/../../..//*[@class='contentWiki theContent']/*[@class='text']";
	public final String ELEMENT_ACTIVITY_WIKI_VERSION = "//*[@class='linkTitle' and text()='${title}']/../..//*[@class = 'pull-right versionLabel' and contains(text(), 'Version: ${version}')]";
	public final String ELEMENT_ACTIVITY_MOVE_WIKI_PAGE = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'Page has been moved to: ${path}')]";

	//Answer activity
	public final String ELEMENT_QUESTION_CONTENT = "//a[text()='${title}']/../../..//div[@class='contentAnswer theContent']//p";
	public final String ELEMENT_QUESTION_NUM_ANSWER = "//div[@class='contentAnswer theContent']//span[contains(text(),'${number} Answer')]";
	public final String ELEMENT_QUESTION_NUM_COMMENT = "//div[@class='contentAnswer theContent']//span[contains(text(),'${number} Comment')]";
	public final String ELEMENT_QUESTION_COMMENT = "//a[text()='${title}']/../../../..//div[@class='commentList']//p[@class='contentComment' and contains(text(), '${comment}')]";
	public final String ELEMENT_QUESTION_RATE = "//a[@class='linkTitle' and text()='${title}']/../..//div[@class='avgRatingImages sumaryRate']/i[@class='voted'][${rate}]";
	public final String ELEMENT_QUESTION_HAFT_RATE = "//a[@class='linkTitle' and text()='${title}']/../..//div[@data-original-title='Average']/i[@class='votedHaft']";
	public final String ELEMENT_QUESTION_VIEW_COMMENT = "//a[text()='${title}']/../../../..//div[@class='commentListInfo clearfix']/a";
	//Forum activity
	public final String ELEMENT_FORUM_ACT_CONTENT = "//a[text()='${title}']/../../..//div[@class='contentForum theContent']//p";
	public final String ELEMENT_FORUM_NUMBER_REPLY = "//a[text()='${title}']/../../..//div[@class='contentForum theContent']/span[text()='${number} Replies']";
	public final String ELEMENT_FORUM_ONE_REPLY = "//a[text()='${title}']/../../..//div[@class='contentForum theContent']/span[text()='${number} Reply']";
	public final String ELEMENT_TOPIC_RATE = "//a[@class='textBold linkTitle' and text()='${title}']/../..//div[@class='avgRatingImages sumaryRate']/i[@class='voted'][${rate}]";
	public final String ELEMENT_TOPIC_HAFT_RATE = "//a[@class='textBold linkTitle' and text()='${title}']/../..//div[@data-original-title='Average']/i[@class='votedHaft']";
	public final String ELEMENT_TOPIC_REPLY = "//a[contains(text(),'${title}')]/../../../..//i[@class='uiIconReply uiIconLightGray']";
	public final String ELEMENT_TOPIC_LAST_REPLY = "//a[contains(text(),'${title}')]/../../../..//i[@class='uiIconSocLastestReply uiIconSocLightGray']";
	public final String ELEMENT_REPLY_VIEW = "//a[contains(text(),'${title}')]/../../../..//*[@class='contentComment' and contains(text(), '${comment}')]/../../..//a[@class='viewComment' and contains(text(),'View')]";

	//Poll activity
	public final String ELEMENT_POLL_ACTIVITY = "//div[@class='uiBox roundedBottom introBox pollShare']//a[contains(text(),'${poll}')]";
	public final String ELEMENT_VOTE = "//div[@class='uiBox roundedBottom introBox pollShare']//a[contains(text(),'${poll}')]/../../..//div[@class='titleVote' and contains(text(),'${vote}')]";
	public final String ELEMENT_VOTE_PROGRESSBAR = "//div[@class='uiBox roundedBottom introBox pollShare']//a[contains(text(),'${poll}')]/../../..//div[@class='titleVote' and contains(text(),'${vote}')]/..//div[@class='progressBar']";
	public final String ELEMENT_VOTE_RATE = "//div[@class='uiBox roundedBottom introBox pollShare']//a[contains(text(),'${poll}')]/../../..//div[@class='titleVote' and contains(text(),'${vote}')]/..//div[@class='progressing' and contains(text(),'(${rate} vote)')]";
	public final String ELEMENT_POLL_VOTE = "//a[contains(text(),'${poll}')]/../../../..//i[@class='uiIconSocVote uiIconSocLightGray']";
	public final String ELEMENT_TOPIC_COMMENT = "//a[contains(text(),'${title}')]/../../../..//*[@class='contentComment' and contains(text(), '${comment}')]/../..";


	/** function check info in activity of a content/file
	 * @author lientm
	 * @param name
	 * @param iconType
	 * @param contentType
	 * @param size
	 * @param content
	 * @param version
	 * @param des
	 * @param status
	 */
	public void checkInforAfterAddingDocument(String name, String iconType, String contentType, String size, String content, 
			String version, String des, String status){
		info("Check name of content");
		waitForAndGetElement(ELEMENT_CONTENT_NAME.replace("@{fileName}", name));
		if (iconType != ""){
			info("Check icon content type");
			waitForAndGetElement(ELEMENT_CONTENT_TYPE_ICON.replace("@{fileName}", name).replace("${icon}", iconType));	
		}
		if (contentType != ""){
			info("Check content type");
			waitForAndGetElement(ELEMENT_CONTENT_TYPE.replace("@{fileName}", name).replace("${type}", contentType));
		}
		if (content != ""){
			info("Check content summary");
			String[] sum = getText(ELEMENT_CONTENT_SUMMARY.replace("@{fileName}", name)).split("\n");
			String[] cont = content.split("/");
			if (cont.length > 4){
				assert sum[4].equalsIgnoreCase("...");
				for (int i = 0; i < 4; i++){
					assert sum[i].equalsIgnoreCase(cont[i]);
				}
			}else {
				for (int i = 0; i < cont.length; i ++){
					assert sum[i].equalsIgnoreCase(cont[i]);
				}
			}
		}
		if (size != ""){
			info("Check content size");
			waitForAndGetElement(ELEMENT_FILE_SIZE.replace("@{fileName}", name).replace("${size}", size));
		}
		if (version != ""){
			info("Check content version");
			waitForAndGetElement(ELEMENT_CONTENT_VERSION.replace("@{fileName}", name).replace("${version}", version));
		}
		if (des != ""){
			info("Check content description");
			waitForAndGetElement(ELEMENT_CONTENT_DESCRIPTION.replace("@{fileName}", name).replace("${des}", des));
		}
		if (status != ""){
			info("Check content status");
			waitForAndGetElement(ELEMENT_CONTENT_STATUS.replace("@{fileName}", name).replace("${status}", status));
		}
	}

	/**Function check add comment in activity after editing title of a file/content
	 * @author lientm
	 * @param name
	 * @param titleEdit
	 */
	public void checkTitleAfterEditing(String name, String titleEdit){
		waitForAndGetElement(ELEMENT_CONTENT_COMMENT_EDIT_TITLE.replace("@{fileName}", titleEdit).replace("${title}", titleEdit));
	}

	/** function check add comment in activity after adding tags for a content/file
	 * @author lientm
	 * @param name
	 * @param tags
	 * @param number
	 */
	public void checkTagAfterAddingToContent(String name, String tags, int number){
		if (number == 1){
			waitForAndGetElement(ELEMENT_CONTENT_COMMENT_ADD_A_TAG.replace("@{fileName}", name).replace("${tags}", tags));
		}else {
			waitForAndGetElement(ELEMENT_CONTENT_COMMENT_ADD_TAGS.replace("@{fileName}", name).replace("${tags}", tags));
		}
	}

	/** function check add comment in activity after publishing a conent/file
	 * @author lientm
	 * @param name
	 */
	public void checkStatusAfterPublishAContent(String name){
		waitForAndGetElement(ELEMENT_CONTENT_COMMENT_PUBLISH.replace("@{fileName}", name));
	}

	/** function check add comment in activity after adding new category for a content/file
	 * @author lientm
	 * @param name
	 * @param category
	 */
	public void checkCategoryAfterAddingToContent(String name, String category){
		waitForAndGetElement(ELEMENT_FILE_COMMENT_ADD_CATEGORY.replace("@{fileName}", name).replace("${category}", category));
	}

	/**function check go to edit screen after clicking Edit icon in activity
	 * @author lientm
	 * @param name
	 */
	public void goToEditFromContentActivity(String name){
		button = new Button(driver);
		click(ELEMENT_CONTENT_EDIT_LINK.replace("@{fileName}", name));
		waitForAndGetElement(ELEMENT_CONTENT_EDIT_SCREEN_FROM_ACTIVITY);
		waitForAndGetElement(button.ELEMENT_SAVE_CLOSE_BUTTON);
	}

	/** function back homepage from edit screen
	 * @author lientm
	 */
	public void backToHomePageFromEditContentScreen(){
		click(ELEMENT_CONTENT_EDIT_SCREEN_BACK);
		waitForElementNotPresent(ELEMENT_CONTENT_EDIT_SCREEN_BACK);
	}

	/**function check go to view screen after clicking View icon in activity of a content
	 * @author lientm
	 * @param name
	 */
	public void goToViewFromContentActivity(String name){
		click(ELEMENT_CONTENT_VIEW_LINK.replace("@{fileName}", name));
		waitForAndGetElement(ELEMENT_CONTENT_EDIT_SCREEN_FROM_ACTIVITY);
		assert getValue(By.id("address")).contains(name);
	}

	/**function check go to view screen after clicking View icon in activity of a file
	 * @author lientm
	 * @param name
	 */
	public void goToViewFromFileActivity(String name){
		dialog = new Dialog(driver);
		click(ELEMENT_CONTENT_VIEW_LINK.replace("@{fileName}", name));
		waitForAndGetElement(ELEMENT_FILE_VIEW_POPUP);
		waitForAndGetElement(ELEMENT_FILE_VIEW_NAME.replace("${fileName}", name));
		dialog.closeMessageDialog();
	}

	/** function check add comment in activity after moving a conent/file
	 * @author lientm
	 * @param name
	 * @param path
	 * @param content
	 */
	public void checkContentAfterMovingANode(String name, String path, boolean content){
		if (content){
			waitForAndGetElement(ELEMENT_CONTENT_COMMENT_MOVING.replace("@{fileName}", name).replace("${path}", path + name));
		}else {
			waitForAndGetElement(ELEMENT_FILE_COMMENT_MOVING.replace("@{fileName}", name).replace("${path}", path + name));
		}
	}

	/**
	 * function check info in activity's header of wiki activity
	 * @param title
	 * @param content
	 * @param version
	 */
	public void checkActivityInfoOfWiki(String title, String content, String version){
		waitForAndGetElement(ELEMENT_ACTIVITY_WIKI_TITLE.replace("${title}", title));
		if (content != ""){
			info("Check content");
			String[] sum = getText(ELEMENT_ACTIVITY_WIKI_CONTENT.replace("${title}", title)).split("\n");
			String[] cont = content.split("/");
			if (cont.length > 4){
				assert sum[4].equalsIgnoreCase("...");
				for (int i = 0; i < 4; i++){
					assert sum[i].equalsIgnoreCase(cont[i]);
				}
			}else {
				for (int i = 0; i < cont.length; i ++){
					assert sum[i].equalsIgnoreCase(cont[i]);
				}
			}
		}
		if (version != ""){
			info("Check version");
			waitForAndGetElement(ELEMENT_ACTIVITY_WIKI_VERSION.replace("${title}", title).replace("${version}", version));
		}
	}

	/**
	 * function delete a comment in activity
	 * @param comment
	 * @param title
	 */
	public void deleteComment(String title, String comment){
		alert = new ManageAlert(driver);
		button = new Button(driver);
		mouseOver(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", title).replace("${comment}", comment), false);
		click(ELEMENT_ACTIVITY_DELETE_COMMENT_ICON.replace("${comment}", comment), 2);
		waitForMessage(DATA_MESSAGE_CONFIRM_DELETE_COMMENT);
		button.ok();
		waitForElementNotPresent(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${comment}", comment));
	}

	/**
	 * function check comment in activity after moving a page
	 * @param title
	 * @param path
	 */
	public void checkCommentAfterMoveWikiPage(String title, String path){
		waitForAndGetElement(ELEMENT_ACTIVITY_MOVE_WIKI_PAGE.replace("${title}", title).replace("${path}", path));
	}


	/**
	 * function check comment in activity after moving a topic
	 * @param title
	 * @param path
	 */
	public void checkCommentAfterMoveTopic(String title, String path){
		waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", title).replace("${comment}", "Topic have been moved to: " + path));
	}

	/** Check content and number of lines of content on activity
	 * @author thuntn
	 * @param name
	 * @param content
	 */
	public void checkNumberOfLineOfContent(String activityContent, String content){
		String[] sum = activityContent.split("\n");
		String[] cont = content.split("<br>");
		char[] character = activityContent.toCharArray();
		char[] contChar = content.toCharArray();

		info("Check content and number of lines of content on activity");
		if (activityContent.contains("...")){
			String sumary = activityContent.replace("...", "");
			sum = sumary.split("\n");
			character = sumary.toCharArray();
			if(content.contains("...")){
				String contentTemp = content.replace("...", "");
				cont = contentTemp.split("<br>");
				contChar = contentTemp.toCharArray();
			}
		}
		if (sum.length > 4){
			Assert.assertFalse(false, "This content has more than 4 lines");

		}else {
			if (sum.length == 4) 
				for (int i = 0; i < sum.length; i ++){
					assert sum[i].equalsIgnoreCase(cont[i]);
				}
			else {
				if (contChar.length >= 430) {
					assert (character.length == 430);
					for (int i = 0; i < 430; i ++){
						assert (character[i]==contChar[i]);
					}
				}else{
					for (int i = 0; i < contChar.length; i ++){
						assert (character[i]==contChar[i]);
					}
				} 
			}
		}

	} 
	/** Check Rate of Question
	 * @author thuntn
	 * @param name: name of question
	 * @param rate: rate of question
	 */
	public void checkRateQuestion(String name, Double rate){
		int round = rate.intValue();
		Double remaining = rate - round;
		for (int i = 1; i <= rate ; i++){
			waitForAndGetElement(ELEMENT_QUESTION_RATE.replace("${title}", name).replace("${rate}", Integer.toString(round)));
		}
		if (remaining != 0 ){
			waitForAndGetElement(ELEMENT_QUESTION_HAFT_RATE.replace("${title}", name));
		}
	}
	/** Check number of answer, and comment of Answer
	 * @author thuntn
	 * @param question
	 * @param number
	 * @param answer
	 */
	public void checkAnswerOfQuestion(String question, String...answer){
		int number = answer.length;
		info("Check for comment for Answer of question on activity");

		//Check number of answer
		waitForAndGetElement(ELEMENT_QUESTION_NUM_ANSWER.replace("${number}", Integer.toString(number)));

		for (int i = 0; i < number; i ++){
			waitForAndGetElement(ELEMENT_QUESTION_COMMENT.replace("${title}", question).replace("${comment}", "Answer has been submitted: " + answer[i]));
		}
	}

	/** Check number of answer, and comment of Answer
	 * @author thuntn
	 * @param question
	 * @param number
	 * @param comment
	 */
	public void checkCommentOfQuestion(String question, String...comment){
		int number = comment.length;
		info("Check for comment of question on activity");

		//Check number of comment
		waitForAndGetElement(ELEMENT_QUESTION_NUM_COMMENT.replace("${number}", Integer.toString(number)));
		for (int i = 0; i < number; i ++){
			waitForAndGetElement(ELEMENT_QUESTION_COMMENT.replace("${title}", question).replace("${comment}", comment[i]));
		}
	}
	/** Check comment on activity after activating a question
	 * @author thuntn
	 * @param question
	 */
	public void checkActivateQuestion(String question){
		info("Check for comment of question after activating a question");

		waitForAndGetElement(ELEMENT_QUESTION_COMMENT.replace("${title}", question).replace("${comment}", "Question has been activated."));
	}

	/** Check comment on activity after deactivating a question
	 * @author thuntn
	 * @param question
	 */
	public void checkDeactivateQuestion(String question){
		info("Check for comment of question after activating a question");

		waitForAndGetElement(ELEMENT_QUESTION_COMMENT.replace("${title}", question).replace("${comment}", "Question has been unactivated."));
	}

	/** Check reply of a topic, and number of reply
	 * @author thuntn
	 * @param topic
	 * @param reply
	 */
	public void checkReplyForum(String topic, String...reply){
		int number = reply.length;

		info("Check reply of a topic, and number of reply");
		//Check number of reply
		if (number == 1)
			waitForAndGetElement(ELEMENT_FORUM_ONE_REPLY.replace("${title}", topic).replace("${number}", Integer.toString(number)));
		else 
			waitForAndGetElement(ELEMENT_FORUM_NUMBER_REPLY.replace("${title}", topic).replace("${number}", Integer.toString(number)));

		for(int i = 0; i < number; i++){
			waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", topic).replace("${comment}", reply[i]));
		}

	}

	/** Check Rate of Topic
	 * @author thuntn
	 * @param name: name of topic
	 * @param rate: rate of topic
	 */
	public void checkRateTopic(String topic, Double rate){
		int round = rate.intValue();
		Double remaining = rate - round;

		info("Check Rate of Topic");
		for (int i = 1; i <= rate ; i++){
			waitForAndGetElement(ELEMENT_TOPIC_RATE.replace("${title}", topic).replace("${rate}", Integer.toString(round)));
		}
		if (remaining != 0 ){
			waitForAndGetElement(ELEMENT_TOPIC_HAFT_RATE.replace("${title}", topic));
		}
	} 
	/** Check activity after updating a topic
	 * @author thuntn
	 * @param topicTitle: Title of topic
	 * @param newContent: new content of topic 
	 */
	public void checkUpdateTopic(String topicTitle, String newContent){

		info("Check activity after updating a topic");
		//Check content
		checkNumberOfLineOfContent(getText((ELEMENT_FORUM_ACT_CONTENT.replace("${title}", topicTitle))), newContent);
		//Check comment
		waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", topicTitle).replace("${comment}", "Content has been edited."));

	}

	/** Check activity after Locking a topic
	 * @author thuntn
	 * @param topicTitle: Title of topic
	 */
	public void checkLockTopic(String topicTitle){

		info("Check activity after Locking a topic");

		waitForAndGetElement(By.linkText(topicTitle));
		waitForElementNotPresent(ELEMENT_ICON_COMMENT.replace("${title}", topicTitle));
		waitForElementNotPresent(ELEMENT_COMMENTBOX.replace("${title}", topicTitle));
		waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", topicTitle).replace("${comment}", "Topic has been locked."));
	}

	/** Check activity after Unlocking a topic
	 * @author thuntn
	 * @param topicTitle: Title of topic
	 */
	public void checkUnlockTopic(String topicTitle){

		info("Check activity after Unlocking a topic");

		waitForAndGetElement(By.linkText(topicTitle));
		//Check if icon comment, comment box appear
		waitForAndGetElement(ELEMENT_ICON_COMMENT.replace("${title}", topicTitle));
		click(ELEMENT_ICON_COMMENT.replace("${title}", topicTitle));
		waitForAndGetElement(ELEMENT_COMMENTBOX.replace("${title}", topicTitle));
		waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", topicTitle).replace("${comment}", "Topic has been unlocked."));

	}

	/** Check activity after adding a poll
	 * @author thuntn
	 * @param topic: title of topic
	 * @param poll: title of poll
	 * @param options: array of options of the poll
	 * @param rate: array of rate of options, corresponding to options array
	 */
	public void checkAddPoll(String topic, String poll,String[] options, String[] rate){
		int length = options.length;

		info("Check activity after adding a poll");
		//Check comment of topic activity
		waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", topic).replace("${comment}", "A poll has been added to the topic."));
		//Check poll activity
		waitForAndGetElement(ELEMENT_POLL_ACTIVITY.replace("${poll}", poll));

		//Check vote, rate of each vote
		for(int i = 0; i < length; i ++){
			waitForAndGetElement(ELEMENT_VOTE.replace("${poll}", poll).replace("${vote}", options[i]));
			waitForAndGetElement(ELEMENT_VOTE_PROGRESSBAR.replace("${poll}", poll).replace("${vote}", options[i]));
			if (i < rate.length)
				waitForAndGetElement(ELEMENT_VOTE_RATE.replace("${poll}", poll).replace("${vote}", options[i]).replace("${rate}", rate[i]));
		}

	}

	/** Check activity after editing a poll
	 * @author thuntn
	 */
	public void checkEditPoll(String poll){
		info("Check activity after editing a poll");
		waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", poll).replace("${comment}", "Poll has been updated."));
	}

	/** Open Poll from activity
	 * @author thuntn
	 * @param poll
	 */
	public void openVoteFromActivity(String poll){
		info("Open Poll from activity");
		click(ELEMENT_POLL_VOTE.replace("${poll}", poll));

		waitForAndGetElement(By.linkText("Vote Now"));
	}

	/** Open Reply form from activity
	 * @author thuntn
	 * @param topic
	 */
	public void openReplyFormFromActivity(String topic){
		info("Open Reply form from activity");
		click(ELEMENT_TOPIC_REPLY.replace("${title}", topic));
		waitForAndGetElement(post.ELEMENT_POST_POPUP_NEW);
		assert waitForAndGetElement(post.ELEMENT_POST_TITLE).getAttribute("value").equalsIgnoreCase("Re: " + topic);

		click(post.ELEMENT_POST_CANCEL);
		waitForElementNotPresent(post.ELEMENT_POST_POPUP_NEW);
	}
	/** Open last reply of topic
	 * @author thuntn
	 * @param topic
	 */
	public void openLastReplyFromActivity(String topic, String lastReply){
		info("Open last reply of topic");
		click(ELEMENT_TOPIC_LAST_REPLY.replace("${title}", topic));
		waitForAndGetElement(post.ELEMENT_POST_REPLY_BUTTON);
		waitForTextPresent(lastReply);

	}
	/**View a reply by clicking on View in activity
	 * @author thuntn
	 * @param topic
	 * @param reply
	 */
	public void viewReplyFromActivity(String topic, String reply){
		info("View a reply by clicking on View in activity");
		Actions actions = new Actions(driver);
		WebElement element = waitForAndGetElement(ELEMENT_TOPIC_COMMENT.replace("${title}",topic).replace("${comment}", reply));

		Locatable hoverItem = (Locatable) driver.findElement(By.xpath("//div[@class='footerWLC backOutdated']"));
		int y = hoverItem.getCoordinates().onPage().getY();
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+y+");");
		actions.moveToElement(element).click().perform();
		click(ELEMENT_REPLY_VIEW.replace("${title}", topic).replace("${comment}", reply),2);
		waitForAndGetElement(post.ELEMENT_POST_REPLY_BUTTON);
		waitForTextPresent(reply);
	}
}