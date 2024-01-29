import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class email {
    String username = "backups202324@gmail.com";
    String password = "whgo yqcl sspl uxoy";

    public void sendSuccessEmail(String email, String path, String log){
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        //Session session = Session.getInstance(prop, new javax.mail.Authenticator() {

          //  protected PasswordAuthentication getPasswordAuthentication() {

            //    return new PasswordAuthentication(username, password);

            //}

       // });
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        //session.setDebug(true);

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            message.setSubject("Backup");

            message.setText("Your backup has been done succesfully!!\n" +
                    "Website backed up succesfully to: "+ path+"\n"+
                    "Backup log updated at: "+log);

            Transport.send(message);

            System.out.println("Email sent successfully to: "+email);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendError(String email, String errorMsg){
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            message.setSubject("Error in the backup!!");

            message.setText("We send this message to inform you that there has been an error during the backup.\n" +
                    "The error message is the following:\n"+errorMsg);

            Transport.send(message);

            System.out.println("Email sent successfully to: "+email);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



}
