package com.qa.opencart.tests;

import java.util.Random;

import org.apache.poi.ss.formula.eval.ConcatEval;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void setUpRegister() {
		registrationPage = loginPage.nevigateToRegisterPage();
	}

	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	/**
	 * this method is used to generate random numbers
	 * @return concatenated string with random number
	 */
	public String getRandomEmail() {
		Random random = new Random();
		String email = "testautomation" + random.nextInt(1000) + "@gmail.com";
		return email;
	}

	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, 
									 String telephone, 
									 String password, String subscribe) {
		Assert.assertTrue(
				registrationPage.accountRegistration(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	}
}
