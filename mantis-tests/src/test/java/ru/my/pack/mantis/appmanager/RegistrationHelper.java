package ru.my.pack.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.my.pack.mantis.model.MailMessage;

import java.util.List;

public class RegistrationHelper extends HelperBase {


    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void registrationNewUser(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
//        click(By.xpath("//form[@id='signup-form']/fieldset/input[2]"));
    }

    public void followingTheConfirmationLinkForAnewUser(String confimationLink, String password) {
        wd.get(confimationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//form[@id='account-update-form']/fieldset/span/button/span"));

    }
    public void userAuthorization(String name, String password){
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), name );
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password );
        click(By.cssSelector("input[type='submit']"));
    }
    public void resetPasswordFromUser(String username){
        wd.get(app.getProperty("web.baseUrl") + "/manage_overview_page.php");
        click(By.xpath("//div[@id='main-container']/div[2]/div[2]/div/ul/li[2]/a"));
        click(By.linkText(username));
        click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));

    }
    public String findConfimationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
