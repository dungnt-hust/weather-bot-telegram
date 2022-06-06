package app.coreproject.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailUtil {
    private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    public static void sendEmail(String fromEmail, String password, String name, String toEmail, String subject,
                                 String body, boolean isHtml) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
        props.put("mail.smtp.port", "587"); // TLS Port
//        props.put("mail.smtp.port", "465");  //SSL port
        props.put("mail.smtp.auth", "true"); // enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
        // *** BEGIN CHANGE
        props.put("mail.smtp.user", fromEmail);

        // creates a new session, no Authenticator (will connect() later)
        Session session = Session.getDefaultInstance(props);
// *** END CHANGE
        System.out.println("1");
        // create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            // override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        System.out.println("2");
//        Session session = Session.getInstance(props, auth);
        System.out.println("3");
        try {
            MimeMessage msg = new MimeMessage(session);
            // set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress("no_reply@example.com", name));
            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
            msg.setSubject(subject, "UTF-8");

            if (isHtml) {
                msg.setText(body, "UTF-8", "html");
            } else {
                msg.setText(body, "UTF-8");
            }

//            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("4");
//            Transport.send(msg);
            // *** BEGIN CHANGE
            // sends the e-mail
            Transport t = session.getTransport("smtp");
            t.connect(fromEmail, password);
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
            // *** END CHANGE
            System.out.println("5");
            logger.info("Send email success to Email:" + toEmail + " Subject:" + subject);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi mail" + e.toString());
            logger.error(e.toString());
            logger.error("Send email fail to Email:" + toEmail + " Subject:" + subject);
        }
    }
}
