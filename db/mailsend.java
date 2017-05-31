

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
import javax.mail.BodyPart;
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


/**
 * @author Ray
 *
 */


public class mailsend {

	public static void main(String[] args) throws Exception{
		   String data = args[0];

		   String cc ="";
		   String subject = "";
		   String content = "<img src='cid:werg1016'>";
		   String[] mail_data = data.split(":Parsing:");
		   
		   
		   
		   
		   BodyPart messageBodyPart = new MimeBodyPart();
		   String htmlText = "<HTML>" +
				    "<HEAD><TITLE></TITLE></HEAD>" +
				    "<BODY>" +
				     "<table><tr><td>"+
				     "<br>"+
				     " <br>"+
				     " </td>"+
				     "</tr>"+
				     "<tr>"+
				     " <td width=100%><img src=\"cid:werg1016\"></img></td>"+
				     "</tr>"+
				     "</table>"+
				     "</BODY>" +
				    "</HTML>";

	   messageBodyPart.setContent(htmlText,"text/html");
	   
	   String Filename = mail_data[2];
		  // Properties 설정
		  // 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
		  Properties props = new Properties();
		  
		  // G-Mail SMTP 사용시
		  props.put("mail.transport.protocol", "smtp");// 프로토콜 설정
		  props.put("mail.smtp.host", "smtp.gmail.com");// gmail SMTP 서비스 주소(호스트)
		  props.put("mail.smtp.port", "465");// gmail SMTP 서비스 포트 설정
		  // 로그인 할때 Transport Layer Security(TLS)를 사용할 것인지 설정
		  // gmail 에선 tls가 필수가 아니므로 해도 그만 안해도 그만
//		        props.put("mail.smtp.starttls.enable","true");
		        // gmail 인증용 Secure Socket Layer(SSL) 설정
		        // gmail 에서 인증때 사용해주므로 요건 안해주면 안됨
		        props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		        props.put("mail.smtp.user","w01095013411@gmail.com");
		        props.put("mail.smtp.auth", "true");// SMTP 인증을 설정
		        /**
		         * SMTP 인증이 필요한 경우 반드시 Properties 에 SMTP 인증을 사용한다고 설정하여야 한다.
		         * 그렇지 않으면 인증을 시도조차 하지 않는다.
		         * 그리고 Authenticator 클래스를 상속받은 SMTPAuthenticator 클래스를 생성한다.
		         * getPasswordAuthentication() 메소드만 override 하면 된다.
		         * 머 사실 다른 메소드는 final 메소드여서 override 할 수 조차 없다. -ㅅ-;
		         */
		 Authenticator auth = new Authenticator(){
		        protected PasswordAuthentication getPasswordAuthentication() {
		              return new PasswordAuthentication("w01095013411@gmail.com","1q2w3e4r!!");
		           }
		   };


		  Session mailSession = Session.getDefaultInstance(props, auth);
		  
		  // create a message
		  Message msg = new MimeMessage(mailSession);
		  
		  // set the from and to address
		  msg.setFrom(new InternetAddress("w01095013411@gmail.com"));//보내는 사람 설정
		  msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail_data[0], false));//받는 사람설정
		  
		  if(!cc.trim().equals("")) {
		   msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, false));
		  }
		  
		  MimeMultipart mp = new MimeMultipart("related");
		  MimeBodyPart mbp1 = new MimeBodyPart();
		  mp.addBodyPart(messageBodyPart);
		  mbp1.setText(content);
		  mp.addBodyPart(mbp1);
		  if(Filename != null){

				  if(fileSizeCheck(Filename)){

					  MimeBodyPart mbp2 = new MimeBodyPart();
		              FileDataSource fds = new FileDataSource(Filename);
		              mbp2.setDataHandler(new DataHandler(fds));
		              mbp2.setFileName(fds.getName());
		              mbp2.addHeader("Content-ID", "<werg1016>");
		              mbp2.setDisposition("inline"); 
		              mbp2.setContentID("werg1016");
		              mp.addBodyPart(mbp2);
		              
		            }else{
		                throw new Exception("file size overflow !");
		            }


			  
			  
		  }
		  MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	      mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
	      mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
	      mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	      mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
	      mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
	      CommandMap.setDefaultCommandMap(mc);
	         
	      msg.setContent(mp);
		  // Setting the Subject and Content Type
		  msg.setSubject(mail_data[1]); // 제목 설정
		  msg.setSentDate(new Date());// 보내는 날짜 설정
		  

		  Transport.send(msg);  // 메일 보내기
		  
		  long thisTime = System.currentTimeMillis();
		  
		  
		  File Directory = new File("C:/saveEml"); 
  		  if (!Directory.exists()) {
  		  	 Directory.mkdirs();
  		  	 System.out.println("사용자 디렉토리를 생성했습니다.");
  		  }else{
   		  	 System.out.println("디렉토리 존재.");
  	 	  }
		  
		  File emlFile = new File("C:/saveEml/"+"w01095013411@gmail.com"+thisTime+".eml");
		  emlFile.createNewFile();
		  msg.writeTo(new FileOutputStream(emlFile));
		  
			 }

	private static boolean fileSizeCheck(String filename) {
		// TODO Auto-generated method stub
		 if (new File(filename).length() > (1024 * 1024 * 2.5)) {
	            return false;
	        }
	        return true;

	}
	
	

}
