package ru.my.pack.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my.pack.mantis.model.User;
import ru.my.pack.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void changePasswordTest() throws IOException, MessagingException {
        app.registration().userAuthorization(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        User user = randomUser(app.dbHelper().allUsers());
        String newPassword = "newPassword";
        app.registration().resetPasswordFromUser(user.getUsername());
        
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
        String confimationLink = app.registration().findConfimationLink(mailMessages, user.getEmail());
        app.registration().followingTheConfirmationLinkForAnewUser(confimationLink, newPassword);
        assertTrue(app.newSession().login(user.getUsername(), newPassword));


    }

    private User randomUser(List<User> allUsers) {
        List<User> copy = new ArrayList<>(allUsers);
        for (User user : copy){
            if(user.getUsername().equals("administrator")){
                allUsers.remove(user);
            }
        }
        return allUsers.get((int)Math.random() * allUsers.size());

    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
