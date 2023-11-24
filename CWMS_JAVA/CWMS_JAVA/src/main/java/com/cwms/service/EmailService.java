package com.cwms.service;


import java.util.Properties;
import javax.mail.util.ByteArrayDataSource;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
//	
//	    public boolean sendEmailWithHtmlContent(String subject, String htmlContent, String to) {
//	        boolean success = false;
//
//	        String from = "rushikeshnirmal88@gmail.com";
//	        String host = "smtp.gmail.com";
//	        String username = "rushikeshnirmal88@gmail.com";
//	        String password = "bcoqpbgkllkvbfmf"; // Update with your actual password
//
//	        Properties properties = new Properties();
//	        properties.put("mail.smtp.auth", "true");
//	        properties.put("mail.smtp.starttls.enable", "true");
//	        properties.put("mail.smtp.host", host);
//	        properties.put("mail.smtp.port", "587");
//
//	        Session session = Session.getInstance(properties, new Authenticator() {
//	            @Override
//	            protected PasswordAuthentication getPasswordAuthentication() {
//	                return new PasswordAuthentication(username, password);
//	            }
//	        });
//
//	        session.setDebug(true);
//
//	        MimeMessage mimeMessage = new MimeMessage(session);
//	        try {
//	            mimeMessage.setFrom(new InternetAddress(from));
//	            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//	            mimeMessage.setSubject(subject);
//
//	            
////	            MimeBodyPart fileMine = new MimeBodyPart();
////	          String path = "/Users/macbook/Downloads/detention_data.xlsx";
////	          File file = new File(path);
////           fileMine.attachFile(file); 
//           
//           
//           
//	            MimeMultipart mimeMultipart = new MimeMultipart("related");
//
//	            MimeBodyPart htmlPart = new MimeBodyPart();
//	            htmlPart.setContent(htmlContent, "text/html");
//
//	            mimeMultipart.addBodyPart(htmlPart);
//	           // mimeMultipart.addBodyPart(fileMine);
//
//	            mimeMessage.setContent(mimeMultipart);
//
//	            Transport.send(mimeMessage);
//	            System.out.println("Sent HTML message successfully...");
//
//	            success = true;
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//
//	        return success;
//	    }
//	

	
	
	    
	@Value("${spring.from.mail}")
    private String from;  
	 
	 @Value("${spring.mail.host}")
	 private String host;
	 
	 @Value("${spring.mail.username}")
	 private String username;
	 
	 @Value("${spring.mail.password}")
	 private String password;
	 
	public boolean sendEmailWithHtmlContentAndAttachment(String subject, String htmlContent, String to,
			String attachmentFilePath, String fromEmail, String ccEmail) {
		boolean success = false;
		//String from = "rushikeshnirmal88@gmail.com";
//		String host = "smtp.gmail.com";
//		String username = "rushikeshnirmal88@gmail.com";
//		String password = "bcoqpbgkllkvbfmf"; // Update with your actual password

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		session.setDebug(true);

		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setFrom(new InternetAddress(fromEmail));

			// mimeMessage.setFrom(new InternetAddress(from));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mimeMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(ccEmail));

			mimeMessage.setSubject(subject);

			MimeMultipart mimeMultipart = new MimeMultipart("related");

			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(htmlContent, "text/html");

			mimeMultipart.addBodyPart(htmlPart);

			// Add the attachment
			MimeBodyPart attachmentPart = new MimeBodyPart();
			FileDataSource fileDataSource = new FileDataSource(attachmentFilePath);
			attachmentPart.setDataHandler(new DataHandler(fileDataSource));
			attachmentPart.setFileName(fileDataSource.getName());
			mimeMultipart.addBodyPart(attachmentPart);

			mimeMessage.setContent(mimeMultipart);

			Transport.send(mimeMessage);
			System.out.println("Sent HTML message with attachment successfully...");

			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}
	public boolean sendEmailWithHtmlContent(String subject, String htmlContent, String to,
		    String fromEmail, String ccMail) {
		    boolean success = false;

		    Properties properties = new Properties();

		    properties.put("mail.smtp.auth", "true");
		    properties.put("mail.smtp.starttls.enable", "true");
		    properties.put("mail.smtp.host", host);
		    properties.put("mail.smtp.port", "587");

		    Session session = Session.getInstance(properties, new Authenticator() {
		        @Override
		        protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(username, password);
		        }
		    });

		    session.setDebug(true);

		    MimeMessage mimeMessage = new MimeMessage(session);
		    try {
		        mimeMessage.setFrom(new InternetAddress(fromEmail));

		        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		        mimeMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(ccMail));

		        mimeMessage.setSubject(subject);

		        MimeBodyPart htmlPart = new MimeBodyPart();
		        htmlPart.setContent(htmlContent, "text/html");

		        MimeMultipart mimeMultipart = new MimeMultipart("related");
		        mimeMultipart.addBodyPart(htmlPart);

		        mimeMessage.setContent(mimeMultipart);

		        Transport.send(mimeMessage);
		        System.out.println("Sent HTML message successfully...");

		        success = true;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return success;
		}
	
	
	
	public boolean SendOtpOnEmail(String to,String otp ,String nop)
	{
		
		String subject = "Otp for Verification";     
        
        String ccEmail = "sanketghodake316@gmail.com";   
                
        String htmlContent = "<html><body>" +
                "<p>Dear Sir/Madam, Please validate your identity in DGDC E-Custodian with OTP <strong>" + otp + "</strong> for number of packages <strong>" + nop + "</strong>.</p>" +
                "</body></html>"; 
        
        
        
      return sendEmailWithHtmlContent(subject,
				htmlContent, to, from,ccEmail);
		
	}
//	 Email for send Bill to Party 	
	public boolean sendEmailWithTwoPdfAttachments(String to, byte[] pdfBytes1, byte[] pdfBytes2, String body, String ccEmail, String fileName1, String fileName2) {
	    String subject = "Your Subject";
	    boolean success = false;

	    Properties properties = new Properties();
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.host", host);
	    properties.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(properties, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    });

	    session.setDebug(true);

	    MimeMessage mimeMessage = new MimeMessage(session);
	    try {
	        mimeMessage.setFrom(new InternetAddress(from));
	        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        mimeMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(ccEmail));
	        mimeMessage.setSubject(subject);

	        MimeMultipart mimeMultipart = new MimeMultipart("related");

	        // Attachment 1
	        MimeBodyPart attachmentPart1 = new MimeBodyPart();
	        attachmentPart1.setDataHandler(new DataHandler(new ByteArrayDataSource(pdfBytes1, "application/pdf")));
	        attachmentPart1.setFileName(fileName1);
	        mimeMultipart.addBodyPart(attachmentPart1);

	        // Attachment 2
	        MimeBodyPart attachmentPart2 = new MimeBodyPart();
	        attachmentPart2.setDataHandler(new DataHandler(new ByteArrayDataSource(pdfBytes2, "application/pdf")));
	        attachmentPart2.setFileName(fileName2);
	        mimeMultipart.addBodyPart(attachmentPart2);

	        // Adding the body/content
	        MimeBodyPart bodyPart = new MimeBodyPart();
	        bodyPart.setContent(body, "text/html");
	        mimeMultipart.addBodyPart(bodyPart);

	        mimeMessage.setContent(mimeMultipart);

	        Transport.send(mimeMessage);

	        success = true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return success;
	}
	
	}