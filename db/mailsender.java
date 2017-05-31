import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


public class mailsender {
	public static void main(String[] args) throws Exception{
		String data = args[0];
		String[] mail_data = data.split(":Parsing:");
		
		  Properties props = new Properties();
		  
		  props.put("mail.transport.protocol", "smtp");
		  props.put("mail.smtp.host", "smtp.gmail.com");
		  props.put("mail.smtp.port", "465");

		        props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		        props.put("mail.smtp.user", "w01095013411@gmail.com");
		        props.put("mail.smtp.auth", "true");
		    
		 Authenticator auth = new Authenticator(){
		        protected PasswordAuthentication getPasswordAuthentication() {
		              return new PasswordAuthentication("w01095013411@gmail.com","1q2w3e4r!!");
		           }
		   };

		  Session mailSession = Session.getDefaultInstance(props, auth);
		  
		  Message msg = new MimeMessage(mailSession);
		  
		  msg.setFrom(new InternetAddress("w01095013411@gmail.com"));
		  msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail_data[0], false));

		  Multipart mp = new MimeMultipart();
		  MimeBodyPart mbp1 = new MimeBodyPart();
		  
		  mbp1.setText(mail_data[2]);
		  mp.addBodyPart(mbp1);
	
		  MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	      mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
	      mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
	      mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	      mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
	      mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
	      CommandMap.setDefaultCommandMap(mc);
	         
	      msg.setContent(mp);
	     
		  msg.setSubject(mail_data[1]);
		  msg.setSentDate(new Date());
		  
		  Transport.send(msg); 
		  
		  long thisTime = System.currentTimeMillis();
		  
		  File Directory = new File("C:/saveEml"); 
  		  if (!Directory.exists()) {
  		  	 Directory.mkdirs();
  		  	 System.out.println("사용자 디렉토리를 생성했습니다.");
  		  }else{
   		  	 System.out.println("디렉토리 존재.");
  	 	  }
		  
		  File emlFile = new File("C:/saveEml/"+mail_data[0]+"_"+thisTime+".eml");
		  emlFile.createNewFile();
		  msg.writeTo(new FileOutputStream(emlFile));
		 
	}

}
