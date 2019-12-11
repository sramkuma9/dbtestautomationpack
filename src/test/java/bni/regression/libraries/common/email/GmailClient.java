package bni.regression.libraries.common.email;

import bni.regression.libraries.common.ReadWritePropertyFile;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import org.junit.platform.commons.util.StringUtils;

import javax.mail.*;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GmailClient {

    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

    public void checkEmail(String userName, String subject, String toEmailId) {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", readWritePropertyFile.loadAndReadPropertyFile("emailProtocol", "properties/config.properties"));
        properties.put("mail.imaps.host", readWritePropertyFile.loadAndReadPropertyFile("emailHostName", "properties/config.properties"));
        properties.put("mail.imaps.port", readWritePropertyFile.loadAndReadPropertyFile("emailPort", "properties/config.properties"));
        String password = readWritePropertyFile.loadAndReadPropertyFile("emailPassword", "properties/config.properties");
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
            Integer checkSubject = 0;
            for (Message message : messages) {
                try {
                    Address[] tos = message.getAllRecipients();
                    String receiver = tos == null ? null : ((InternetAddress) tos[0]).getAddress();
                    if (message.getSubject().contains(subject) && receiver.equals(toEmailId)) {
                        String emailBody = this.getTextFromMessage(message);
                        //System.out.println("Mail Content:- " + emailBody);
                        Address[] froms = message.getFrom();
                        String Sender = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
                        //System.out.println("Mail Sender:- " + Sender);
                        System.out.println("email received successfully.....");
                        checkSubject++;
                        break;
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
            if (!(checkSubject == 1)) {
                System.out.println("email has not been received.....");
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
            return getTextFromBodyPart(mimeMultipart.getBodyPart(count - 1));
        String result = "";
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            result += getTextFromBodyPart(bodyPart);
            //System.out.println(bodyPart.getFileName());
            if (!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition()) &&
                    StringUtils.isBlank(bodyPart.getFileName())) {
                continue; // dealing with attachments only
            }
            InputStream is = bodyPart.getInputStream();
            File f = new File(readWritePropertyFile.loadAndReadPropertyFile("emailDownloadPath", "properties/config.properties") + bodyPart.getFileName());
            FileOutputStream fos = new FileOutputStream(f);
            byte[] buf = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buf)) != -1) {
                fos.write(buf, 0, bytesRead);
            }
            fos.close();
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
        } else if (bodyPart.getContent() instanceof MimeMultipart) {
            result = getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
        }
        return result;
    }
}