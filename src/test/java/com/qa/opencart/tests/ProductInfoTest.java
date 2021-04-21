package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class ProductInfoTest extends BaseTest {

	SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	/**
	 * when we don'tneed to fetch data from excel sheet
	 * same data provider can be used for other test cases also
	 */
	@DataProvider
	public Object[][] searchData() {
		return new Object [][] {{"Macbook"},
								{"iphone"},
								{"iMac"}};
	}
	
	@Test (priority = 1, dataProvider = "searchData")
	public void productCountTest(String productName) {
		searchResPage = accPage.doSearch(productName);
		Assert.assertTrue(searchResPage.getProductResultsCount() >0);
	}

	@Test (priority = 2, enabled = false)
	public void productInfoTest() {
		searchResPage = accPage.doSearch("iMac");
		productInfoPage = searchResPage.selectProductFromResults("iMac");
		Assert.assertEquals(productInfoPage.getProductHeaderText(), "iMac");
	}

	@Test (priority = 3, enabled = false)
	public void productImagesTest() {
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProductFromResults("MacBook Pro");
		Assert.assertTrue(productInfoPage.getProductImagesCount() == 4);
	}
/**
 * hard assertions - the moment the assertion is getting failed, it will not jump to the next line.
 * immediately your test will be terminated and test will be marked as failed.
 * Soft Assert - if any of the assertion fails, other assertions will be verified and result will show
 * how many assertions are passed and how many are failed. test doesn't terminate immediately.
 */
	@Test (priority = 4, enabled = false)
	public void getProductInfoTest() {
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProductFromResults("MacBook Pro");
		Map<String, String> actualProductMetaData = productInfoPage.getProductInformation();
		actualProductMetaData.forEach((k,v)-> System.out.println(k + ":" + v));
		
		softAssert.assertEquals(actualProductMetaData.get("name"),"MacBook Pro");
		softAssert.assertEquals(actualProductMetaData.get("Brand"),"Apple");
		softAssert.assertEquals(actualProductMetaData.get("Availability"),"Out Of Stock");
		softAssert.assertEquals(actualProductMetaData.get("price"),"$2,000.00");
		
		softAssert.assertAll();
	}
	
	@Test (priority = 5, enabled = false)
	public void addToCart() {
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProductFromResults("MacBook Pro");
		productInfoPage.selectQuantity("2");
		productInfoPage.addToCart();
		Assert.assertTrue(productInfoPage.getSuccessMessage().contains(Constants.ADD_TO_CART_SUCCESS_MSG));
	}

}
