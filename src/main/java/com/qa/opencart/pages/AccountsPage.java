package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private ElementUtil elementUtil;
	private WebDriver driver;

	private By accSections = By.cssSelector("div#content h2");
	private By header = By.cssSelector("div#logo a");
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");
	private By sublinksMyAccountSection = By.cssSelector("div#content ul:nth-of-type(1) li a");
	private By sublinksMyOrdersSection = By.cssSelector("div#content ul:nth-of-type(2) li a");
	private By sublinksMyAffliateAccountSection = By.cssSelector("div#content ul:nth-of-type(3) li a");
	private By sublinksNewsletterSection = By.cssSelector("div#content ul:nth-of-type(4) li a");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getAccPageTitle() {
		return elementUtil.waitForTitle(5, Constants.ACCOUNTS_PAGE_TITLE);
	}

	public String getAccPageUrl() {
		return elementUtil.getPageUrl();
	}

	public String getAccPageHeader() {
		return elementUtil.doGetText(header);
	}

	public List<String> getAccSectionsList() {
		List<String> accSectionValueList = new ArrayList<String>();
		List<WebElement> accSectionList = elementUtil.waitForVisibilityOfElements(accSections, 5);
		for (WebElement e : accSectionList) {
			accSectionValueList.add(e.getText());
		}
		Collections.sort(accSectionValueList);
		return accSectionValueList;
	}

	public boolean isLogoutExist() {
		return elementUtil.doIsDisplayed(logoutLink);
	}

	/**
	 * search method - This method is used to search the product
	 * @param productName
	 * @return next landing page class object
	 */
	public SearchResultPage doSearch(String productName) {
		System.out.println("Searching the product: " + productName);
		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doClick(searchButton);
		return new SearchResultPage(driver);
	}

	public List<String> getMyAccountSublinks() {
		List<String> MyAccountSubLinks = new ArrayList<String>();
		List<WebElement> MyAccountList = elementUtil.getElements(sublinksMyAccountSection);
		for (WebElement e : MyAccountList) {
			MyAccountSubLinks.add(e.getText());
		}
		return MyAccountSubLinks;
	}

	public List<String> getMyOrdersSublinks() {
		List<String> MyOrderstSubLinks = new ArrayList<String>();
		List<WebElement> MyOrdersList = elementUtil.getElements(sublinksMyOrdersSection);
		for (WebElement e : MyOrdersList) {
			MyOrderstSubLinks.add(e.getText());
		}
		return MyOrderstSubLinks;
	}

	public List<String> getMyAffiliateAccSublinks() {
		List<String> MyAffAccountSubLinks = new ArrayList<String>();
		List<WebElement> MyAffAccountList = elementUtil.getElements(sublinksMyAffliateAccountSection);
		for (WebElement e : MyAffAccountList) {
			MyAffAccountSubLinks.add(e.getText());
		}
		return MyAffAccountSubLinks;
	}

	public List<String> getNewslettertSublinks() {
		List<String> NewsletterSubLinks = new ArrayList<String>();
		List<WebElement> NewsletterList = elementUtil.getElements(sublinksNewsletterSection);
		for (WebElement e : NewsletterList) {
			NewsletterSubLinks.add(e.getText());
		}
		return NewsletterSubLinks;
	}
}
