package com.kcribs.Configuration;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.kcribs.Constants.Constants;



public class KcribsEmailLib {
	public KcribsEmailLib() {

	}

	String to, body;
	String subject;
		public KcribsEmailLib(String to, String subject, String Body) {
		this.to = to;
		this.subject = subject;
		this.body = Body;

	}

	public void sendMail() {
		final String username = "support@worksplug.com";
		final String password = "WorksplugsupportteaM";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username, "WORKSPLUG REGISTRATION"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setContent(body, "text/html");
			Transport.send(message);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

//	public String htmlRegisterProfessional(String username, Long activationcode)
//			throws NoSuchAlgorithmException, UnsupportedEncodingException {
//		String professionalLink = Constants.ACTIVATION_CODE_PROFESSIONAL + activationcode;
//		return "";
//
//	}
	
	public String htmlRegisterUsers(String username, Long activationCode)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String clientLink = Constants.ACTIVATION_CODE_CLIENT + activationCode;

		 String emailContent = "<!doctype html>\n" + 
		 		"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
		 		"\n" + 
		 		"<head>\n" + 
		 		"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + 
		 		"    <title>Responsive Email Template</title>\n" + 
		 		"\n" + 
		 		"    <style type=\"text/css\">\n" + 
		 		"        .ReadMsgBody {\n" + 
		 		"            width: 100%;\n" + 
		 		"            background-color: #ffffff;\n" + 
		 		"        }\n" + 
		 		"        \n" + 
		 		"        .ExternalClass {\n" + 
		 		"            width: 100%;\n" + 
		 		"            background-color: #ffffff;\n" + 
		 		"        }\n" + 
		 		"        \n" + 
		 		"        body {\n" + 
		 		"            width: 100%;\n" + 
		 		"            background-color: #ffffff;\n" + 
		 		"            margin: 0;\n" + 
		 		"            padding: 0;\n" + 
		 		"            -webkit-font-smoothing: antialiased;\n" + 
		 		"            font-family: Arial, Helvetica, sans-serif\n" + 
		 		"        }\n" + 
		 		"        \n" + 
		 		"        table {\n" + 
		 		"            border-collapse: collapse;\n" + 
		 		"        }\n" + 
		 		"        \n" + 
		 		"        @media only screen and (max-width: 640px) {\n" + 
		 		"            body[yahoo] .deviceWidth {\n" + 
		 		"                width: 440px!important;\n" + 
		 		"                padding: 0;\n" + 
		 		"            }\n" + 
		 		"            body[yahoo] .center {\n" + 
		 		"                text-align: center!important;\n" + 
		 		"            }\n" + 
		 		"        }\n" + 
		 		"        \n" + 
		 		"        @media only screen and (max-width: 479px) {\n" + 
		 		"            body[yahoo] .deviceWidth {\n" + 
		 		"                width: 280px!important;\n" + 
		 		"                padding: 0;\n" + 
		 		"            }\n" + 
		 		"            body[yahoo] .center {\n" + 
		 		"                text-align: center!important;\n" + 
		 		"            }\n" + 
		 		"        }\n" + 
		 		"    </style>\n" + 
		 		"</head>\n" + 
		 		"\n" + 
		 		"<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" yahoo=\"fix\" style=\"font-family: Arial, Helvetica, sans-serif\">\n" + 
		 		"\n" + 
		 		"    <!-- Wrapper -->\n" + 
		 		"    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" + 
		 		"        <tr>\n" + 
		 		"            <td width=\"100%\" valign=\"top\" bgcolor=\"#ffffff\" style=\"padding-top:20px\">\n" + 
		 		"\n" + 
		 		"                <!--Start Header-->\n" + 
		 		"                <table width=\"700\" bgcolor=\"#fff\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
		 		"                    <tr>\n" + 
		 		"                        <td style=\"padding: 6px 0px 0px\">\n" + 
		 		"                            <table width=\"680\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
		 		"                                <tr>\n" + 
		 		"                                    <td width=\"100%\">\n" + 
		 		"                                        <!--Start logo-->\n" + 
		 		"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"deviceWidth\">\n" + 
		 		"                                            <tr>\n" + 
		 		"                                                <td class=\"center\" style=\"padding: 10px 0px 10px 0px\">\n" + 
		 		"                                                    <a href=\"#\"><img src=\"https://res.cloudinary.com/worksplug/image/upload/v1555001476/wplogo.png\" width=\"150px\"></a>\n" + 
		 		"                                                </td>\n" + 
		 		"                                            </tr>\n" + 
		 		"                                        </table>\n" + 
		 		"                                        <!--End logo-->\n" + 
		 		"                                        <!--Start nav-->\n" + 
		 		"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"right\" class=\"deviceWidth\">\n" + 
		 		"                                            <tr>\n" + 
		 		"                                                <td class=\"center\" style=\"font-size: 13px; color: #272727; font-weight: light; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 10px 0px 10px 0px;\">\n" + 
		 		"                                                    <a href=\"#\" style=\"text-decoration: none; color: #3b3b3b;\">SERVICES</a> &nbsp; &nbsp;\n" + 
		 		"                                                    <a href=\"#\" style=\"text-decoration: none; color: #3b3b3b;\">BLOG</a>\n" + 
		 		"                                                </td>\n" + 
		 		"                                            </tr>\n" + 
		 		"                                        </table>\n" + 
		 		"                                        <!--End nav-->\n" + 
		 		"                                    </td>\n" + 
		 		"                                </tr>\n" + 
		 		"                            </table>\n" + 
		 		"                        </td>\n" + 
		 		"                    </tr>\n" + 
		 		"                </table>\n" + 
		 		"                <!--End Header-->\n" + 
		 		"\n" + 
		 		"                <!--Start Two Blocks-->\n" + 
		 		"                <table width=\"700\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
		 		"                    <tr>\n" + 
		 		"                        <td width=\"100%\" bgcolor=\"#ffffff\">\n" + 
		 		"                            <!--Right box-->\n" + 
		 		"                            <table width=\"98%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"deviceWidth\">\n" + 
		 		"                                <tr>\n" + 
		 		"                                    <td class=\"center\" style=\"font-size: 14px; color: #687074;\">\n" + 
		 		"\n" + 
		 		"                                        <p>Dear "+ username+",</p>\n" + 
		 		"\n" + 
		 		"                                        You registered with us at WORKSPLUG a few moment ago, we are glad to have you join several other users.\n" + 
		 		"\n" + 
		 		"                                        <p></p>\n" + 
		 		"\n" + 
		 		"                                        However you are just one step away to having endless access to Professionals and prospective clients on WORKSPLUG.\n" + 
		 		"                                    </td>\n" + 
		 		"                                    <td class=\"center\">\n" + 
		 		"\n" + 
		 		"                                    </td>\n" + 
		 		"                                </tr>\n" + 
		 		"                            </table>\n" + 
		 		"                            <!--End right box-->\n" + 
		 		"                        </td>\n" + 
		 		"                    </tr>\n" + 
		 		"                </table>\n" + 
		 		"                <!--End Two Blocks -->\n" + 
		 		"\n" + 
		 		"                <div style=\"height:15px\">&nbsp;</div>\n" + 
		 		"                <!-- divider -->\n" + 
		 		"\n" + 
		 		"                <!--Start Discount -->\n" + 
		 		"                <table width=\"700\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
		 		"                    <tr>\n" + 
		 		"                        <td width=\"100%\" bgcolor=\"#ffffff\">\n" + 
		 		"                            <!-- Left Box  -->\n" + 
		 		"                            <table width=\"70%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"deviceWidth\">\n" + 
		 		"                                <tr>\n" + 
		 		"                                    <td class=\"center\">\n" + 
		 		"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" + 
		 		"                                            <tr>\n" + 
		 		"                                                <td class=\"center\" style=\"font-size: 16px; color: #687074; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 20px 10px 10px;\">\n" + 
		 		"                                                    Copy the link below to a new Tab on your browser to activate account\n" + 
		 		"                                                </td>\n" + 
		 		"                                            </tr>\n" + 
		 		"                                        </table>\n" + 
		 		"                                    </td>\n" + 
		 		"                                </tr>\n" + 
		 		"                            </table>\n" + 
		 		"                            <!--End Left Box-->\n" + 
		 		"                            <!--Right Box-->\n" + 
		 		"                            <table width=\"30%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
		 		"                                <tr>\n" + 
		 		"                                    <td style=\" padding: 15px 20px 30px;\">\n" + 
		 		"                                        <table align=\"center\">\n" + 
		 		"                                            <tr>\n" + 
		 		"                                                <td valign=\"top\" style=\"padding: 7px 15px; text-align: center; background-color: #bdc3c7;\" class=\"center\">\n" + 
		 		"                                                    <a style=\"color: #fff; font-size: 12px; font-weight: bold; text-decoration: none; font-family: Arial, sans-serif; text-alight: center;\">"+ clientLink+ "</a>\n" + 
		 		"                                                </td>\n" + 
		 		"                                            </tr>\n" + 
		 		"                                        </table>\n" + 
		 		"                                    </td>\n" + 
		 		"                                </tr>\n" + 
		 		"                            </table>\n" + 
		 		"                            <!--End Right Box-->\n" + 
		 		"                        </td>\n" + 
		 		"                    </tr>\n" + 
		 		"                </table>\n" + 
		 		"                <!--End Discount -->\n" + 
		 		"                <div style=\"margin: 25px\"></div>\n" + 
		 		"                <!--Start Weekly Prize-->\n" + 
		 		"                <table width=\"700\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
		 		"                    <tr>\n" + 
		 		"                        <td width=\"100%\" bgcolor=\"#a5d1da\" class=\"center\">\n" + 
		 		"                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" + 
		 		"                                <tr>\n" + 
		 		"                                    <td valign=\"top\" style=\"padding: 20px 10px \" class=\"center\">\n" + 
		 		"                                        <a href=\"#\"><img width=\"32\" hight=\"32\" src=\"https://res.cloudinary.com/worksplug/image/upload/v1553531678/resources/facebook.png\"></a>\n" + 
		 		"                                    </td>\n" + 
		 		"                                    <td valign=\"top\" style=\"padding: 20px 10px \" class=\"center\">\n" + 
		 		"                                        <a href=\"#\"><img width=\"32\" hight=\"32\" src=\"https://res.cloudinary.com/worksplug/image/upload/v1553531679/resources/twitter.png\"></a>\n" + 
		 		"                                    </td>\n" + 
		 		"                                    <td valign=\"top\" style=\"padding: 20px 10px \" class=\"center\">\n" + 
		 		"                                        <a href=\"#\"><img width=\"32\" hight=\"32\" src=\"https://res.cloudinary.com/worksplug/image/upload/v1553531679/resources/youtube.png\"></a>\n" + 
		 		"                                    </td>\n" + 
		 		"                                </tr>\n" + 
		 		"                            </table>\n" + 
		 		"                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" + 
		 		"                                <tr>\n" + 
		 		"                                    <td class=\"center\" style=\"font-size: 16px; color: #ffffff; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 0px 10px; \">\n" + 
		 		"                                        Stay Conneted With Us\n" + 
		 		"                                    </td>\n" + 
		 		"                                </tr>\n" + 
		 		"                                <td class=\"center\" style=\"font-size: 12px; color: #ffffff; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 20px 10px; \">\n" + 
		 		"                                    KCRIBS will like to have you updated on our social media platforms. </br>Our support will also be available on this platform to give quick responses or help in any way our help is needed.\n" + 
		 		"                                </td>\n" + 
		 		"                    </tr>\n" + 
		 		"                    </table>\n" + 
		 		"                    </td>\n" + 
		 		"        </tr>\n" + 
		 		"        </table>\n" + 
		 		"        <!--Weekly Prize-->\n" + 
		 		"\n" + 
		 		"        <!-- Footer -->\n" + 
		 		"        <table width=\"700\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
		 		"            <tr>\n" + 
		 		"                <td bgcolor=\"#ffffff\" class=\"center\" style=\"font-size: 12px; color: #687074; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 20px 50px 0px 50px; \">\n" + 
		 		"                    Copyright © KCRIBS 2019\n" + 
		 		"                </td>\n" + 
		 		"\n" + 
		 		"            </tr>\n" + 
		 		"            <tr>\n" + 
		 		"                <td bgcolor=\"#ffffff\" class=\"center\" style=\"font-size: 12px; color: #687074; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 20px 10px; \">\n" + 
		 		"                    If you would prefer to receive email communications from KCRIBS click <a style=\"text-decoration: none; color: #bdc3c7;\" href=\"#\">here</a>\n" + 
		 		"                </td>\n" + 
		 		"            </tr>\n" + 
		 		"        </table>\n" + 
		 		"        <!--End Footer-->\n" + 
		 		"\n" + 
		 		"        </td>\n" + 
		 		"        </tr>\n" + 
		 		"    </table>\n" + 
		 		"    <!-- End Wrapper -->\n" + 
		 		"</body>\n" + 
		 		"\n" + 
		 		"</html>";
		return emailContent;

	}
	

	public String htmlRegisterProfessional(String username, Long activationCode)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String clientLink = Constants.ACTIVATION_CODE_PROFESSIONAL + activationCode;

		 String emailContent = "<!doctype html>\n" + 
			 		"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
			 		"\n" + 
			 		"<head>\n" + 
			 		"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + 
			 		"    <title>Responsive Email Template</title>\n" + 
			 		"\n" + 
			 		"    <style type=\"text/css\">\n" + 
			 		"        .ReadMsgBody {\n" + 
			 		"            width: 100%;\n" + 
			 		"            background-color: #ffffff;\n" + 
			 		"        }\n" + 
			 		"        \n" + 
			 		"        .ExternalClass {\n" + 
			 		"            width: 100%;\n" + 
			 		"            background-color: #ffffff;\n" + 
			 		"        }\n" + 
			 		"        \n" + 
			 		"        body {\n" + 
			 		"            width: 100%;\n" + 
			 		"            background-color: #ffffff;\n" + 
			 		"            margin: 0;\n" + 
			 		"            padding: 0;\n" + 
			 		"            -webkit-font-smoothing: antialiased;\n" + 
			 		"            font-family: Arial, Helvetica, sans-serif\n" + 
			 		"        }\n" + 
			 		"        \n" + 
			 		"        table {\n" + 
			 		"            border-collapse: collapse;\n" + 
			 		"        }\n" + 
			 		"        \n" + 
			 		"        @media only screen and (max-width: 640px) {\n" + 
			 		"            body[yahoo] .deviceWidth {\n" + 
			 		"                width: 440px!important;\n" + 
			 		"                padding: 0;\n" + 
			 		"            }\n" + 
			 		"            body[yahoo] .center {\n" + 
			 		"                text-align: center!important;\n" + 
			 		"            }\n" + 
			 		"        }\n" + 
			 		"        \n" + 
			 		"        @media only screen and (max-width: 479px) {\n" + 
			 		"            body[yahoo] .deviceWidth {\n" + 
			 		"                width: 280px!important;\n" + 
			 		"                padding: 0;\n" + 
			 		"            }\n" + 
			 		"            body[yahoo] .center {\n" + 
			 		"                text-align: center!important;\n" + 
			 		"            }\n" + 
			 		"        }\n" + 
			 		"    </style>\n" + 
			 		"</head>\n" + 
			 		"\n" + 
			 		"<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" yahoo=\"fix\" style=\"font-family: Arial, Helvetica, sans-serif\">\n" + 
			 		"\n" + 
			 		"    <!-- Wrapper -->\n" + 
			 		"    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" + 
			 		"        <tr>\n" + 
			 		"            <td width=\"100%\" valign=\"top\" bgcolor=\"#ffffff\" style=\"padding-top:20px\">\n" + 
			 		"\n" + 
			 		"                <!--Start Header-->\n" + 
			 		"                <table width=\"700\" bgcolor=\"#fff\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
			 		"                    <tr>\n" + 
			 		"                        <td style=\"padding: 6px 0px 0px\">\n" + 
			 		"                            <table width=\"680\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
			 		"                                <tr>\n" + 
			 		"                                    <td width=\"100%\">\n" + 
			 		"                                        <!--Start logo-->\n" + 
			 		"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"deviceWidth\">\n" + 
			 		"                                            <tr>\n" + 
			 		"                                                <td class=\"center\" style=\"padding: 10px 0px 10px 0px\">\n" + 
			 		"                                                    <a href=\"#\"><img src=\"https://res.cloudinary.com/worksplug/image/upload/v1555001476/wplogo.png\" width=\"150px\"></a>\n" + 
			 		"                                                </td>\n" + 
			 		"                                            </tr>\n" + 
			 		"                                        </table>\n" + 
			 		"                                        <!--End logo-->\n" + 
			 		"                                        <!--Start nav-->\n" + 
			 		"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"right\" class=\"deviceWidth\">\n" + 
			 		"                                            <tr>\n" + 
			 		"                                                <td class=\"center\" style=\"font-size: 13px; color: #272727; font-weight: light; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 10px 0px 10px 0px;\">\n" + 
			 		"                                                    <a href=\"#\" style=\"text-decoration: none; color: #3b3b3b;\">SERVICES</a> &nbsp; &nbsp;\n" + 
			 		"                                                    <a href=\"#\" style=\"text-decoration: none; color: #3b3b3b;\">BLOG</a>\n" + 
			 		"                                                </td>\n" + 
			 		"                                            </tr>\n" + 
			 		"                                        </table>\n" + 
			 		"                                        <!--End nav-->\n" + 
			 		"                                    </td>\n" + 
			 		"                                </tr>\n" + 
			 		"                            </table>\n" + 
			 		"                        </td>\n" + 
			 		"                    </tr>\n" + 
			 		"                </table>\n" + 
			 		"                <!--End Header-->\n" + 
			 		"\n" + 
			 		"                <!--Start Two Blocks-->\n" + 
			 		"                <table width=\"700\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
			 		"                    <tr>\n" + 
			 		"                        <td width=\"100%\" bgcolor=\"#ffffff\">\n" + 
			 		"                            <!--Right box-->\n" + 
			 		"                            <table width=\"98%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"deviceWidth\">\n" + 
			 		"                                <tr>\n" + 
			 		"                                    <td class=\"center\" style=\"font-size: 14px; color: #687074;\">\n" + 
			 		"\n" + 
			 		"                                        <p>Dear "+ username+",</p>\n" + 
			 		"\n" + 
			 		"                                        You registered with us at WORKSPLUG a few moment ago as a PROFESSIONAL, we are glad to have you join several other users.\n" + 
			 		"\n" + 
			 		"                                        <p></p>\n" + 
			 		"\n" + 
			 		"                                        However are just one step away to having endless access to Professionals and prospective clients on WORKSPLUG.\n" + 
			 		"                                    </td>\n" + 
			 		"                                    <td class=\"center\">\n" + 
			 		"\n" + 
			 		"                                    </td>\n" + 
			 		"                                </tr>\n" + 
			 		"                            </table>\n" + 
			 		"                            <!--End right box-->\n" + 
			 		"                        </td>\n" + 
			 		"                    </tr>\n" + 
			 		"                </table>\n" + 
			 		"                <!--End Two Blocks -->\n" + 
			 		"\n" + 
			 		"                <div style=\"height:15px\">&nbsp;</div>\n" + 
			 		"                <!-- divider -->\n" + 
			 		"\n" + 
			 		"                <!--Start Discount -->\n" + 
			 		"                <table width=\"700\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
			 		"                    <tr>\n" + 
			 		"                        <td width=\"100%\" bgcolor=\"#ffffff\">\n" + 
			 		"                            <!-- Left Box  -->\n" + 
			 		"                            <table width=\"70%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"deviceWidth\">\n" + 
			 		"                                <tr>\n" + 
			 		"                                    <td class=\"center\">\n" + 
			 		"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" + 
			 		"                                            <tr>\n" + 
			 		"                                                <td class=\"center\" style=\"font-size: 16px; color: #687074; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 20px 10px 10px;\">\n" + 
			 		"                                                    Copy the link below to a new Tab on your browser to activate account\n" + 
			 		"                                                </td>\n" + 
			 		"                                            </tr>\n" + 
			 		"                                        </table>\n" + 
			 		"                                    </td>\n" + 
			 		"                                </tr>\n" + 
			 		"                            </table>\n" + 
			 		"                            <!--End Left Box-->\n" + 
			 		"                            <!--Right Box-->\n" + 
			 		"                            <table width=\"30%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
			 		"                                <tr>\n" + 
			 		"                                    <td style=\" padding: 15px 20px 30px;\">\n" + 
			 		"                                        <table align=\"center\">\n" + 
			 		"                                            <tr>\n" + 
			 		"                                                <td valign=\"top\" style=\"padding: 7px 15px; text-align: center; background-color: #bdc3c7;\" class=\"center\">\n" + 
			 		"                                                    <a style=\"color: #fff; font-size: 12px; font-weight: bold; text-decoration: none; font-family: Arial, sans-serif; text-alight: center;\">"+ clientLink+ "</a>\n" + 
			 		"                                                </td>\n" + 
			 		"                                            </tr>\n" + 
			 		"                                        </table>\n" + 
			 		"                                    </td>\n" + 
			 		"                                </tr>\n" + 
			 		"                            </table>\n" + 
			 		"                            <!--End Right Box-->\n" + 
			 		"                        </td>\n" + 
			 		"                    </tr>\n" + 
			 		"                </table>\n" + 
			 		"                <!--End Discount -->\n" + 
			 		"                <div style=\"margin: 25px\"></div>\n" + 
			 		"                <!--Start Weekly Prize-->\n" + 
			 		"                <table width=\"700\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
			 		"                    <tr>\n" + 
			 		"                        <td width=\"100%\" bgcolor=\"#a5d1da\" class=\"center\">\n" + 
			 		"                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" + 
			 		"                                <tr>\n" + 
			 		"                                    <td valign=\"top\" style=\"padding: 20px 10px \" class=\"center\">\n" + 
			 		"                                        <a href=\"#\"><img width=\"32\" hight=\"32\" src=\"https://res.cloudinary.com/worksplug/image/upload/v1553531678/resources/facebook.png\"></a>\n" + 
			 		"                                    </td>\n" + 
			 		"                                    <td valign=\"top\" style=\"padding: 20px 10px \" class=\"center\">\n" + 
			 		"                                        <a href=\"#\"><img width=\"32\" hight=\"32\" src=\"https://res.cloudinary.com/worksplug/image/upload/v1553531679/resources/twitter.png\"></a>\n" + 
			 		"                                    </td>\n" + 
			 		"                                    <td valign=\"top\" style=\"padding: 20px 10px \" class=\"center\">\n" + 
			 		"                                        <a href=\"#\"><img width=\"32\" hight=\"32\" src=\"https://res.cloudinary.com/worksplug/image/upload/v1553531679/resources/youtube.png\"></a>\n" + 
			 		"                                    </td>\n" + 
			 		"                                </tr>\n" + 
			 		"                            </table>\n" + 
			 		"                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" + 
			 		"                                <tr>\n" + 
			 		"                                    <td class=\"center\" style=\"font-size: 16px; color: #ffffff; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 0px 10px; \">\n" + 
			 		"                                        Stay Conneted With Us\n" + 
			 		"                                    </td>\n" + 
			 		"                                </tr>\n" + 
			 		"                                <td class=\"center\" style=\"font-size: 12px; color: #ffffff; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 20px 10px; \">\n" + 
			 		"                                    KCRIBS will like to have you updated on our social media platforms. </br>Our support will also be available on this platform to give quick responses or help in any way our help is needed.\n" + 
			 		"                                </td>\n" + 
			 		"                    </tr>\n" + 
			 		"                    </table>\n" + 
			 		"                    </td>\n" + 
			 		"        </tr>\n" + 
			 		"        </table>\n" + 
			 		"        <!--Weekly Prize-->\n" + 
			 		"\n" + 
			 		"        <!-- Footer -->\n" + 
			 		"        <table width=\"700\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
			 		"            <tr>\n" + 
			 		"                <td bgcolor=\"#ffffff\" class=\"center\" style=\"font-size: 12px; color: #687074; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 20px 50px 0px 50px; \">\n" + 
			 		"                    Copyright © KCRIBS 2019\n" + 
			 		"                </td>\n" + 
			 		"\n" + 
			 		"            </tr>\n" + 
			 		"            <tr>\n" + 
			 		"                <td bgcolor=\"#ffffff\" class=\"center\" style=\"font-size: 12px; color: #687074; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 20px 10px; \">\n" + 
			 		"                    If you would prefer to receive email communications from KCRIBS click <a style=\"text-decoration: none; color: #bdc3c7;\" href=\"#\">here</a>\n" + 
			 		"                </td>\n" + 
			 		"            </tr>\n" + 
			 		"        </table>\n" + 
			 		"        <!--End Footer-->\n" + 
			 		"\n" + 
			 		"        </td>\n" + 
			 		"        </tr>\n" + 
			 		"    </table>\n" + 
			 		"    <!-- End Wrapper -->\n" + 
			 		"</body>\n" + 
			 		"\n" + 
			 		"</html>";
		return emailContent;


	}

	public String htmlResetUsers(long activationCode, String firstName) {
		String clientLink = Constants.RESET_CODE_CLIENT + activationCode;

		 String emailContent = "<!doctype html>\n" + 
			 		"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
			 		"\n" + 
			 		"<head>\n" + 
			 		"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + 
			 		"    <title>Responsive Email Template</title>\n" + 
			 		"\n" + 
			 		"    <style type=\"text/css\">\n" + 
			 		"        .ReadMsgBody {\n" + 
			 		"            width: 100%;\n" + 
			 		"            background-color: #ffffff;\n" + 
			 		"        }\n" + 
			 		"        \n" + 
			 		"        .ExternalClass {\n" + 
			 		"            width: 100%;\n" + 
			 		"            background-color: #ffffff;\n" + 
			 		"        }\n" + 
			 		"        \n" + 
			 		"        body {\n" + 
			 		"            width: 100%;\n" + 
			 		"            background-color: #ffffff;\n" + 
			 		"            margin: 0;\n" + 
			 		"            padding: 0;\n" + 
			 		"            -webkit-font-smoothing: antialiased;\n" + 
			 		"            font-family: Arial, Helvetica, sans-serif\n" + 
			 		"        }\n" + 
			 		"        \n" + 
			 		"        table {\n" + 
			 		"            border-collapse: collapse;\n" + 
			 		"        }\n" + 
			 		"        \n" + 
			 		"        @media only screen and (max-width: 640px) {\n" + 
			 		"            body[yahoo] .deviceWidth {\n" + 
			 		"                width: 440px!important;\n" + 
			 		"                padding: 0;\n" + 
			 		"            }\n" + 
			 		"            body[yahoo] .center {\n" + 
			 		"                text-align: center!important;\n" + 
			 		"            }\n" + 
			 		"        }\n" + 
			 		"        \n" + 
			 		"        @media only screen and (max-width: 479px) {\n" + 
			 		"            body[yahoo] .deviceWidth {\n" + 
			 		"                width: 280px!important;\n" + 
			 		"                padding: 0;\n" + 
			 		"            }\n" + 
			 		"            body[yahoo] .center {\n" + 
			 		"                text-align: center!important;\n" + 
			 		"            }\n" + 
			 		"        }\n" + 
			 		"    </style>\n" + 
			 		"</head>\n" + 
			 		"\n" + 
			 		"<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" yahoo=\"fix\" style=\"font-family: Arial, Helvetica, sans-serif\">\n" + 
			 		"\n" + 
			 		"    <!-- Wrapper -->\n" + 
			 		"    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" + 
			 		"        <tr>\n" + 
			 		"            <td width=\"100%\" valign=\"top\" bgcolor=\"#ffffff\" style=\"padding-top:20px\">\n" + 
			 		"\n" + 
			 		"                <!--Start Header-->\n" + 
			 		"                <table width=\"700\" bgcolor=\"#fff\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
			 		"                    <tr>\n" + 
			 		"                        <td style=\"padding: 6px 0px 0px\">\n" + 
			 		"                            <table width=\"680\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
			 		"                                <tr>\n" + 
			 		"                                    <td width=\"100%\">\n" + 
			 		"                                        <!--Start logo-->\n" + 
			 		"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"deviceWidth\">\n" + 
			 		"                                            <tr>\n" + 
			 		"                                                <td class=\"center\" style=\"padding: 10px 0px 10px 0px\">\n" + 
			 		"                                                    <a href=\"#\"><img src=\"https://res.cloudinary.com/worksplug/image/upload/v1555001476/wplogo.png\" width=\"150px\"></a>\n" + 
			 		"                                                </td>\n" + 
			 		"                                            </tr>\n" + 
			 		"                                        </table>\n" + 
			 		"                                        <!--End logo-->\n" + 
			 		"                                        <!--Start nav-->\n" + 
			 		"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"right\" class=\"deviceWidth\">\n" + 
			 		"                                            <tr>\n" + 
			 		"                                                <td class=\"center\" style=\"font-size: 13px; color: #272727; font-weight: light; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 10px 0px 10px 0px;\">\n" + 
			 		"                                                    <a href=\"#\" style=\"text-decoration: none; color: #3b3b3b;\">SERVICES</a> &nbsp; &nbsp;\n" + 
			 		"                                                    <a href=\"#\" style=\"text-decoration: none; color: #3b3b3b;\">BLOG</a>\n" + 
			 		"                                                </td>\n" + 
			 		"                                            </tr>\n" + 
			 		"                                        </table>\n" + 
			 		"                                        <!--End nav-->\n" + 
			 		"                                    </td>\n" + 
			 		"                                </tr>\n" + 
			 		"                            </table>\n" + 
			 		"                        </td>\n" + 
			 		"                    </tr>\n" + 
			 		"                </table>\n" + 
			 		"                <!--End Header-->\n" + 
			 		"\n" + 
			 		"                <!--Start Two Blocks-->\n" + 
			 		"                <table width=\"700\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
			 		"                    <tr>\n" + 
			 		"                        <td width=\"100%\" bgcolor=\"#ffffff\">\n" + 
			 		"                            <!--Right box-->\n" + 
			 		"                            <table width=\"98%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"deviceWidth\">\n" + 
			 		"                                <tr>\n" + 
			 		"                                    <td class=\"center\" style=\"font-size: 14px; color: #687074;\">\n" + 
			 		"\n" + 
			 		"                                        <p>Dear "+ firstName +",</p>\n" + 
			 		"\n" + 
			 		"                                        You requested for a password reset on your account with us at WORKSPLUG.\n" + 
			 		"\n" + 
			 		"                                        <p></p>\n" + 
			 		"\n" + 
			 		"                                    </td>\n" + 
			 		"                                    <td class=\"center\">\n" + 
			 		"\n" + 
			 		"                                    </td>\n" + 
			 		"                                </tr>\n" + 
			 		"                            </table>\n" + 
			 		"                            <!--End right box-->\n" + 
			 		"                        </td>\n" + 
			 		"                    </tr>\n" + 
			 		"                </table>\n" + 
			 		"                <!--End Two Blocks -->\n" + 
			 		"\n" + 
			 		"                <div style=\"height:15px\">&nbsp;</div>\n" + 
			 		"                <!-- divider -->\n" + 
			 		"\n" + 
			 		"                <!--Start Discount -->\n" + 
			 		"                <table width=\"700\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
			 		"                    <tr>\n" + 
			 		"                        <td width=\"100%\" bgcolor=\"#ffffff\">\n" + 
			 		"                            <!-- Left Box  -->\n" + 
			 		"                            <table width=\"70%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" class=\"deviceWidth\">\n" + 
			 		"                                <tr>\n" + 
			 		"                                    <td class=\"center\">\n" + 
			 		"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" + 
			 		"                                            <tr>\n" + 
			 		"                                                <td class=\"center\" style=\"font-size: 16px; color: #687074; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 20px 10px 10px;\">\n" + 
			 		"                                                    Click the link below to reset your account.\n" + 
			 		"                                                </td>\n" + 
			 		"                                            </tr>\n" + 
			 		"                                        </table>\n" + 
			 		"                                    </td>\n" + 
			 		"                                </tr>\n" + 
			 		"                            </table>\n" + 
			 		"                            <!--End Left Box-->\n" + 
			 		"                            <!--Right Box-->\n" + 
			 		"                            <table width=\"30%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
			 		"                                <tr>\n" + 
			 		"                                    <td style=\" padding: 15px 20px 30px;\">\n" + 
			 		"                                        <table align=\"center\">\n" + 
			 		"                                            <tr>\n" + 
			 		"                                                <td valign=\"top\" style=\"padding: 7px 15px; text-align: center; background-color: #bdc3c7;\" class=\"center\">\n" + 
			 		"                                                    <a style=\"color: #fff; font-size: 12px; font-weight: bold; text-decoration: none; font-family: Arial, sans-serif; text-alight: center;\">"+ clientLink+ "</a>\n" +
			 		"                                                </td>\n" + 
			 		"                                            </tr>\n" + 
			 		"                                        </table>\n" + 
			 		"                                    </td>\n" + 
			 		"                                </tr>\n" + 
			 		"                            </table>\n" + 
			 		"                            <!--End Right Box-->\n" + 
			 		"                        </td>\n" + 
			 		"                    </tr>\n" + 
			 		"                </table>\n" + 
			 		"                <!--End Discount -->\n" + 
			 		"                <div style=\"margin: 25px\"></div>\n" + 
			 		"                <!--Start Weekly Prize-->\n" + 
			 		"                <table width=\"700\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
			 		"                    <tr>\n" + 
			 		"                        <td width=\"100%\" bgcolor=\"#a5d1da\" class=\"center\">\n" + 
			 		"                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" + 
			 		"                                <tr>\n" + 
			 		"                                    <td valign=\"top\" style=\"padding: 20px 10px \" class=\"center\">\n" + 
			 		"                                        <a href=\"#\"><img width=\"32\" hight=\"32\" src=\"https://res.cloudinary.com/worksplug/image/upload/v1553531678/resources/facebook.png\"></a>\n" + 
			 		"                                    </td>\n" + 
			 		"                                    <td valign=\"top\" style=\"padding: 20px 10px \" class=\"center\">\n" + 
			 		"                                        <a href=\"#\"><img width=\"32\" hight=\"32\" src=\"https://res.cloudinary.com/worksplug/image/upload/v1553531679/resources/twitter.png\"></a>\n" + 
			 		"                                    </td>\n" + 
			 		"                                    <td valign=\"top\" style=\"padding: 20px 10px \" class=\"center\">\n" + 
			 		"                                        <a href=\"#\"><img width=\"32\" hight=\"32\" src=\"https://res.cloudinary.com/worksplug/image/upload/v1553531679/resources/youtube.png\"></a>\n" + 
			 		"                                    </td>\n" + 
			 		"                                </tr>\n" + 
			 		"                            </table>\n" + 
			 		"                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" + 
			 		"                                <tr>\n" + 
			 		"                                    <td class=\"center\" style=\"font-size: 16px; color: #ffffff; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 0px 10px; \">\n" + 
			 		"                                        Stay Conneted With Us\n" + 
			 		"                                    </td>\n" + 
			 		"                                </tr>\n" + 
			 		"                                <td class=\"center\" style=\"font-size: 12px; color: #ffffff; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 20px 10px; \">\n" + 
			 		"                                    KCRIBS will like to have you updated on our social media platforms. </br>Our support will also be available on this platform to give quick responses or help in any way our help is needed.\n" + 
			 		"                                </td>\n" + 
			 		"                    </tr>\n" + 
			 		"                    </table>\n" + 
			 		"                    </td>\n" + 
			 		"        </tr>\n" + 
			 		"        </table>\n" + 
			 		"        <!--Weekly Prize-->\n" + 
			 		"\n" + 
			 		"        <!-- Footer -->\n" + 
			 		"        <table width=\"700\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"deviceWidth\">\n" + 
			 		"            <tr>\n" + 
			 		"                <td bgcolor=\"#ffffff\" class=\"center\" style=\"font-size: 12px; color: #687074; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 20px 50px 0px 50px; \">\n" + 
			 		"                    Copyright © KCRIBS 2019\n" + 
			 		"                </td>\n" + 
			 		"\n" + 
			 		"            </tr>\n" + 
			 		"            <tr>\n" + 
			 		"                <td bgcolor=\"#ffffff\" class=\"center\" style=\"font-size: 12px; color: #687074; font-weight: bold; text-align: center; font-family: Arial, Helvetica, sans-serif; line-height: 25px; vertical-align: middle; padding: 20px 10px; \">\n" + 
			 		"                    If you would prefer to receive email communications from KCRIBS click <a style=\"text-decoration: none; color: #bdc3c7;\" href=\"#\">here</a>\n" + 
			 		"                </td>\n" + 
			 		"            </tr>\n" + 
			 		"        </table>\n" + 
			 		"        <!--End Footer-->\n" + 
			 		"\n" + 
			 		"        </td>\n" + 
			 		"        </tr>\n" + 
			 		"    </table>\n" + 
			 		"    <!-- End Wrapper -->\n" + 
			 		"</body>\n" + 
			 		"\n" + 
			 		"</html>";
		return emailContent;

	}

	

}
