package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE = "Account Login11";
	public static final String LOGIN_URL_VALUE = "account/login";
	public static final List<String> EXP_LOGIN_LIST = Arrays.asList("Login",
																	"Register",
																	"Forgotten Password",
																	"My Account",
																	"Address Book",
																	"Wish List",
																	"Order History",
																	"Downloads",
																	"Recurring payments",
																	"Reward Points",
																	"Returns",
																	"Transactions",
																	"Newsletter");
	
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String ACCOUNTS_PAGE_HEADER = "Your Store";
	
	public static final List<String> EXP_ACC_SEC_LIST = 
										Arrays.asList("My Account",
														"My Orders",
														"My Affiliate Account",
														"Newsletter");
	
	public static final List<String> EXP_MY_ACCOUNTS_SUBLIST = Arrays.asList(
																"Edit your account information",
																"Change your password",
																"Modify your address book entries",
																"Modify your wish list");
	
	public static final List<String> EXP_MY_ORDERS_SUBLIST = Arrays.asList(
																"View your order history",
																"Downloads",
																"Your Reward Points",
																"View your return requests",
																"Your Transactions",
																"Recurring payments");
	
	public static final List<String> EXP_MY_AFF_ACCOUNT_SUBLIST = Arrays.asList("Register for an affiliate account");
	
	public static final List<String> EXP_NEWSLETTER_SUBLIST = Arrays.asList("Subscribe / unsubscribe to newsletter");
	
	public static final String ADD_TO_CART_SUCCESS_MSG = "Success: You have added";
	
	public static final String REGISTRATION_PAGE_TITLE = "Register Account";
	public static final String ACCOUNT_REGISTER_SUCCESS_MSG = "Your Account Has Been Created!";
	
//	*******************************sheet Names************************************************
	public static final String REGISTER_SHEET_NAME = "register";
}
