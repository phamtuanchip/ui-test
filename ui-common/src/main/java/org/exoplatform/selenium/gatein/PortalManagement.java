package org.exoplatform.selenium.gatein;

import static org.exoplatform.selenium.TestLogger.info;
import java.util.Map;

import org.openqa.selenium.By;

public class PortalManagement extends GateInBase {
	//Portal navigation
	public static final String ELEMENT_CLASSIC_NAVIGATION = "classic";
	
	//Edit layout of portal > Site's config > Permission setting
	public static final String MESSAGE_EDIT_EMPTY_PERMISSION_SETTING = "The \"Edit Permission Setting\" list can not be empty.";
	public static final By ELEMENT_SITE_CONFIG_LINK = By.className("PageProfileIcon") ;
	public static final By ELELENT_LINK_DELETE_PERMISSION = By.xpath("//a[text() = 'Delete Permission']");
	//Edit layout
	public static final String MESSAGE_DELETE_PORTLET = "Are you sure you want to delete this portlet?"  ;
	public static final String MESSAGE_DELETE_CONTAINER = "Are you sure you want to delete this Container?";
	public static final String MESSAGE_WARNING_CONTAINER = "This component contains PageBody. Can not delete !";
	public static final String MESSAGE_QUIT_EDIT_LAYOUT = "Modifications have been made. Are you sure you want to close without saving ?";
	
	//Add new portal
	public static void addNewPortal(String portalName, String portalLocale, String portalSkin, String portalSession, 
			boolean publicMode, Map<String, String> permissions, String editGroupId, String editMembership){
		info("--Create new portal--");

		click(ELEMENT_ADD_NEW_PORTAL_LINK);
		waitForTextPresent("Portal Setting");
		type(ELEMENT_INPUT_NAME, portalName, true);
		select(ELEMENT_SELECT_LOCALE, portalLocale);
		select(ELEMENT_SELECT_SKIN, portalSkin);
		click(ELEMENT_PROPERTIES_TAB);
		select(ELEMENT_SELECT_SESSION_ALIVE, portalSession);
		click(ELEMENT_PERMISSION_SETTING_TAB);

		if (publicMode) {
			waitForAndGetElement(ELEMENT_ADD_PERMISSION_BUTTON);
			check(ELEMENT_CHECKBOX_PUBLIC_MODE);
			waitForElementNotPresent(ELEMENT_ADD_PERMISSION_BUTTON);
		} else {
			for (String key : permissions.keySet()) {
				setViewPermissions(key, permissions.get(key));
			}
		}
		click(ELEMENT_EDIT_PERMISSION_SETTING);
		setEditPermissions(editGroupId, editMembership);
		save();
	}

	//Delete a portal	
	public static void deletePortal(String portalName){
		String portalDeleteIcon = ELEMENT_PORTAL_DELETE_ICON.replace("${portalName}", portalName);
		info("--Delete portal (" + portalName + ")--");		
		click(portalDeleteIcon);
		waitForConfirmation("Are you sure you want to delete this portal?");
		//info("--Verify portal is deleted--");
		//		pause(30000);
		waitForTextNotPresent(portalName, 180000);
	}
}
