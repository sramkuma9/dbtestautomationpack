package bni.regression.libraries.common;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

public class EmailClient {

    //private static final String username = "dbselenium@gmail.com";
    //private static final String password = "selenium1!";

    //public static void main(String[] args) {

    public void checkEmail(String hostName, String port, String protocol, String userName, String password){

        Properties properties = new Properties();
        // properties.put("mail.debug", "true");
        properties.put("mail.store.protocol", protocol);
        properties.put("mail.imaps.host", hostName);
        properties.put("mail.imaps.port", port);
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
                            Address[] froms = message.getFrom();
                            String email = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
                            System.out.println("Mail Sender:- " + email);
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
}