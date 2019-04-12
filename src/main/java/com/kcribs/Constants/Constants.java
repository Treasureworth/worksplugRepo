package com.kcribs.Constants;

import org.apache.commons.validator.routines.EmailValidator;

public class Constants {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "kcribs123r";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
    public static final int FAIL = 400;
	public static final int OK = 200;
	public static final int CREATED = 201;
	public static final int NO_CONTENT = 204;
	public static final int NOT_MODIFIED = 304;
	public static final int BAD_REQUEST = 400;
	public static final int UNAUTHORIZED = 401;
	public static final int FORBIDDEN = 403;
	public static final int NOT_FOUND = 404;
	public static final int CONFLICT = 409;
	public static final int SERVER_ERROR = 500;
	public static final String ACTIVATION_CODE_CLIENT = "localhost:4300/account-activation/";
	public static final String ACTIVATION_CODE_PROFESSIONAL = "localhost:4300/account-activation/";
	public static final String RESET_CODE_CLIENT = "localhost:4300/set-new-password/";
	public static final String RESET_CODE_PROFESSIONAL = "localhost:4300/set-new-password/";


	 public static boolean isValidEmail(String email){

	        return EmailValidator.getInstance(true).isValid(email);

	    }

	    

	    public static boolean isValidPhone(String phone){

	        return phone.startsWith("+234") && phone.length() > 13 || phone.startsWith("070") && phone.length() > 9

	        		|| phone.startsWith("080") && phone.length() > 9 || phone.startsWith("090") && phone.length() > 9

	        		|| phone.startsWith("0") && phone.length() > 9;

	    }
}