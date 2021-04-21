package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 500: Design login page for demo cart application")
@Story ("US 100: Develop a feature with all login page scenarios")
@Feature ("F 001: User login test")
public class LoginPageTest extends BaseTest {

	@Description ("Login Page Title Test")
	@Severity (SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is: " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description ("Login Page URL Test")
	@Severity (SeverityLevel.MINOR)
	@Test(priority = 2, enabled = true)//this test will no participate in execution
	public void loginPageUrlTest() {
		String url = loginPage.getLoginPageUrl();
		Assert.assertTrue(url.contains(Constants.LOGIN_URL_VALUE));
	}

	@Description ("Login Page forgot PWD link Test")
	@Severity (SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Description ("Login Test")
	@Severity (SeverityLevel.CRITICAL)
	@Test(priority = 7)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test (priority = 4)
	public void newCustomerContinueTest() {
		Assert.assertTrue(loginPage.isNewCustContinueLinkExist());
	}
	
	@Test(priority = 5)
	public void returningCustomerLabelTest() {
		Assert.assertTrue(loginPage.isReturningCustlabelExist());
	}
	
	@Test (priority = 6)
	public void rightLoginListTest() {
		List<String> loginList = loginPage.rightSideLoginList();
		loginList.stream().forEach(e -> System.out.println(e));
		Assert.assertEquals(loginList, Constants.EXP_LOGIN_LIST);
	}
	
	/**
	 * negative test scenario
	 * another approach - negative scenarios can be maintained in another class as well
	 * @return
	 */
	@DataProvider
	public Object[][] loginNegativeData() {
		return new Object [][] {{"test@gmail.com","test@123"},
								{" ","tet@123"},
								{" "," "}};
	}
	
	@Description ("Login Negative Test")
	@Severity (SeverityLevel.CRITICAL)
	@Test(priority = 0, dataProvider = "loginNegativeData")
	public void loginNegativeTest(String un, String pwd) {
		loginPage.doLoginWithWrongData(un, pwd);
	}
}
