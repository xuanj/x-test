import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailTest1 {  
    public static void main(String[] args) throws MessagingException {  
        Properties props = new Properties();  
        // ����debug����  
        props.setProperty("mail.debug", "true");  
        // ���ͷ�������Ҫ�����֤  
        props.setProperty("mail.smtp.auth", "true");  
        // �����ʼ�������������  
        props.setProperty("mail.host", "smtp.sina.com");  
        // �����ʼ�Э������  
        props.setProperty("mail.transport.protocol", "smtp");  
          
        // ���û�����Ϣ  
        Session session = Session.getInstance(props);  
          
        // �����ʼ�����  
        Message msg = new MimeMessage(session);  
        msg.setSubject("JavaMail����");  
        // �����ʼ�����  
        msg.setText("����һ����JavaMail���͵��ʼ���");  
        // ���÷�����  
        msg.setFrom(new InternetAddress("15577194527@sina.com"));  
          
        Transport transport = session.getTransport();  
        // �����ʼ�������  
        transport.connect("15577194527", "sina123456");  
        // �����ʼ�  
        transport.sendMessage(msg, new Address[] {new InternetAddress("xuanjian@live.com")});  
        // �ر�����  
        transport.close();  
    }  
}  