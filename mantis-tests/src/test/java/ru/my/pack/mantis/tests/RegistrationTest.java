package ru.my.pack.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.my.pack.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTest extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localadmin", now);
//        String email = String.format("user%s@localhost", now);
        app.registration().registrationNewUser(user, email);
        app.james().createUser(user, password);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
     List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        String confimationLink = app.registration().findConfimationLink(mailMessages, email);
        app.registration().followingTheConfirmationLinkForAnewUser(confimationLink, password);
        assertTrue(app.newSession().login(user, password));


    }



    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
