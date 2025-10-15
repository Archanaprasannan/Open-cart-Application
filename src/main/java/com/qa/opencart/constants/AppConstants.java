package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
	
public static final int DEFAULT_TIMEOUT=5;
public static final int MEDIUM_TIMEOUT=10;
public static final int LONG_TIMEOUT=15;
public static final String LOGIN_PAGE_TITLE="Account Login";
public static final String ACCOUNT_PAGE_TITLE="My Account";
public static final String LOGIN_PAGE_FRACTION_URL="route=account/login";
public static final String ACCOUNT_PAGE_FRACTION_URL="route=account/account";

public static final List<String> ACCOUNT_PAGE_HEADERS=List.of("My Account","My Orders","My Affiliate Account","Newsletter");

public static final String REGISTER_SUCCESS_MESSAGE="Your Account Has Been Created!";
public static final String REGISTER_TEST_DATA="register";
public static final String PRODUCTINFO_TEST_DATA="product";
}
