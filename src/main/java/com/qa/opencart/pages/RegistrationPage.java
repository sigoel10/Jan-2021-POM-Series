package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private ElementUtil elementUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By pwdConfirm = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline']) [position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline']) [position()=2]/input");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By successRegisterMsg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
	}

	public String getRegisterAccountTitle() {
		return elementUtil.waitForTitle(5, Constants.REGISTRATION_PAGE_TITLE);
	}

	public String getRegistrationPageUrl() {
		return elementUtil.getPageUrl();
	}

	public boolean accountRegistration(String firstName, String LastName, String email, String telephone,
			String password, String subscribe) {

		elementUtil.doSendKeys(this.firstName, firstName);
		elementUtil.doSendKeys(this.lastName, LastName);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.pwdConfirm, password);

		if (subscribe.equals("Yes")) {
			elementUtil.doClick(subscribeYes);
		} else {
			elementUtil.doClick(subscribeNo);
		}
		elementUtil.doClick(agreeCheckBox);
		elementUtil.doClick(continueButton);
		String mesg = elementUtil.waitForElementVisible(successRegisterMsg, 5).getText();
		System.out.println(mesg);
		if (mesg.contains(Constants.ACCOUNT_REGISTER_SUCCESS_MSG)) {
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
}
