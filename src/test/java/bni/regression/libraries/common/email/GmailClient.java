package bni.regression.libraries.common.email;

import bni.regression.libraries.common.ReadWritePropertyFile;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

import javax.mail.*;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

public class GmailClient {

    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

    public void checkEmail(String userName, String password) {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", readWritePropertyFile.loadAndReadPropertyFile("emailProtocol", "properties/config.properties"));
        properties.put("mail.imaps.host", readWritePropertyFile.loadAndReadPropertyFile("emailHostName", "properties/config.properties"));
        properties.put("mail.imaps.port", readWritePropertyFile.loadAndReadPropertyFile("emailPort", "properties/config.properties"));
        properties.put("mail.imaps.timeout", "10000");

        Session session = Session.getInstance(properties); // not
        // getDefaultInstance
        IMAPStore store = null;
        Folder inbox = null;

        try {
            store = (IMAPStore) session.getStore("imaps");
            store.connect(userName, password);

            if (!store.hasCapability("IDLE")) {
                throw new RuntimeException("IDLE not supported");
            }

            inbox = (IMAPFolder) store.getFolder("INBOX");
            ensureOpen(inbox, userName, password);
            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                try {
                    System.out.println("Mail Subject:- " + message.getSubject());
                    //System.out.println("Mail Attachment:- " + message.getFileName());
                    String emailBody = this.getTextFromMessage(message);
                    System.out.println("Mail Content:- " + emailBody);
                    Address[] tos = message.getAllRecipients();
                    String receiver = tos == null ? null : ((InternetAddress) tos[0]).getAddress();
                    System.out.println("Mail Receiver:- " + receiver);
                    Address[] froms = message.getFrom();
                    String Sender = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
                    System.out.println("Mail Sender:- " + Sender);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            close(inbox);
            close(store);
        }
    }

    public static void close(final Folder folder) {
        try {
            if (folder != null && folder.isOpen()) {
                folder.close(false);
            }
        } catch (final Exception e) {
            // ignore
        }
    }

    public static void close(final Store store) {
        try {
            if (store != null && store.isConnected()) {
                store.close();
            }
        } catch (final Exception e) {
            // ignore
        }
    }

    public static void ensureOpen(final Folder folder, String userName, String password) throws MessagingException {

        if (folder != null) {
            Store store = folder.getStore();
            if (store != null && !store.isConnected()) {
                store.connect(userName, password);
            }
        } else {
            throw new MessagingException("Unable to open a null folder");
        }

        if (folder.exists() && !folder.isOpen() && (folder.getType() & Folder.HOLDS_MESSAGES) != 0) {
            System.out.println("open folder " + folder.getFullName());
            folder.open(Folder.READ_ONLY);
            if (!folder.isOpen())
                throw new MessagingException("Unable to open folder " + folder.getFullName());
        }
    }
    private String getTextFromMessage(Message message) throws IOException, MessagingException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart) throws IOException, MessagingException {

        int count = mimeMultipart.getCount();
        if (count == 0)
            throw new MessagingException("Multipart with no body parts not supported.");
        boolean multipartAlt = new ContentType(mimeMultipart.getContentType()).match("multipart/alternative");
        if (multipartAlt)
            // alternatives appear in an order of increasing
            // faithfulness to the original content. Customize as req'd.
            return getTextFromBodyPart(mimeMultipart.getBodyPart(count - 1));
        String result = "";
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            result += getTextFromBodyPart(bodyPart);
        }
        return result;
    }

    private String getTextFromBodyPart(
            BodyPart bodyPart) throws IOException, MessagingException {

        String result = "";
        if (bodyPart.isMimeType("text/plain")) {
            result = (String) bodyPart.getContent();
        } else if (bodyPart.isMimeType("text/html")) {
            String html = (String) bodyPart.getContent();
            result = org.jsoup.Jsoup.parse(html).text();
        } else if (bodyPart.getContent() instanceof MimeMultipart){
            result = getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
        }
        return result;
    }
}