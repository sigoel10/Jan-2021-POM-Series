package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. By locators:
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@type='submit']");
	private By forgotPwdLink = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By newCustContinueLink = By.xpath("//div[@class='well']//a[text()='Continue']");
	private By returningCustLabel = By.xpath("//div[@class='well']/h2[text()='Returning Customer']");
	private By rightSideLinks = By.cssSelector("aside#column-right a");
	private By register = By.linkText("Register");
	private By loginErrorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	// 2. constructors
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// 3. Page actions (methods)

	@Step ("getting login page title")
	public String getLoginPageTitle() {
		return elementUtil.waitForTitle(5, Constants.LOGIN_PAGE_TITLE);
	}

	@Step("Getting Login Page URL")
	public String getLoginPageUrl() {
		return elementUtil.getPageUrl();
	}

	@Step("Getting forgot pwd link exist")
	public boolean isForgotPwdLinkExist() {
		return elementUtil.doIsDisplayed(forgotPwdLink);
	}

	/**
	 * This method will do login
	 * 
	 * @param un
	 * @param pwd
	 * @return this method will return AccountPage class object
	 */
	@Step("login with username : {0} and password: {1}") //this is used to show data in report
	public AccountsPage doLogin(String un, String pwd) {
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);

		return new AccountsPage(driver);
	}
	
	@Step("login with username : {0} and password: {1}") //this is used to show data in report
	public boolean doLoginWithWrongData(String un, String pwd) {
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);

		return elementUtil.doIsDisplayed(loginErrorMsg);
	}

	public boolean isNewCustContinueLinkExist() {
		return elementUtil.doIsDisplayed(newCustContinueLink);
	}

	public boolean isReturningCustlabelExist() {
		return elementUtil.doIsDisplayed(returningCustLabel);
	}

	public List<String> rightSideLoginList() {
		List<String> rightLoginListValue = new ArrayList<String>();
		List<WebElement> rightList = elementUtil.waitForVisibilityOfElements(rightSideLinks, 5);
		for (WebElement e : rightList) {
			rightLoginListValue.add(e.getText());
		}
		return rightLoginListValue;
	}

	@Step("navigating to registration Page")
	public RegistrationPage nevigateToRegisterPage() {
		elementUtil.doClick(register);
		return new RegistrationPage(driver);
	}

}
