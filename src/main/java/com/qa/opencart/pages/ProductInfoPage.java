package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	ElementUtil elementUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails li img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.xpath("//input[@name='quantity']");
	private By addToCartBtn = By.id("button-cart");
	private By successMsg = By.cssSelector("div.alert.alert-success.alert-dismissible");
	private By viewCartBtn = By.cssSelector("span#cart-total");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getProductHeaderText() {
		return elementUtil.doGetText(productHeader);
	}

	public int getProductImagesCount() {
		return elementUtil.getElements(productImages).size();
	}

	/**
	 * this method will collect the product metadata and pricing data information in
	 * the form of HashMap this method will return productnforMap.
	 * @return 
	 */
	public Map<String, String> getProductInformation() {
		Map<String, String> productInforMap = new HashMap<String, String>();
		productInforMap.put("name", getProductHeaderText());

		List<WebElement> metaDataList = elementUtil.getElements(productMetaData);
		System.out.println("total poduct meta data: " + metaDataList.size());
		
		//meta data:
		for (WebElement e : metaDataList) {
			String meta[] = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInforMap.put(metaKey, metaValue);
		}
		
		//price:
		List<WebElement> priceList = elementUtil.getElements(productPriceData);
		String price = priceList.get(0).getText().trim();
		String Exprice = priceList.get(1).getText().trim();
		
		productInforMap.put("price", price);
		productInforMap.put("ExTexPrice", Exprice);
		
		return productInforMap;
	}
	
	public void selectQuantity(String qty) {
		elementUtil.doSendKeys(quantity, qty);
	}
	
	public void addToCart() {
		elementUtil.doClick(addToCartBtn);
	}
	
//	public String getSuccessMessage() {
//		return elementUtil.doGetText(successMsg);
//	}
	
	public String getSuccessMessage() {
		elementUtil.waitForElementVisible(successMsg, 5);
		System.out.println(elementUtil.doGetText(successMsg));
		return elementUtil.doGetText(successMsg);
	}
	
	public ShoppingCartPage viewCart() {
		elementUtil.doClick(viewCartBtn);
		return new ShoppingCartPage(driver);
	}
	
}
