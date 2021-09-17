package ru.my.pack.mantis.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.my.pack.mantis.appmanager.HttpSession;

import java.io.IOException;



public class LogginTests extends TestBase {
    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        Assert.assertTrue(session.login("administrator", "root"));
        Assert.assertTrue(session.isLoggedInAs("administrator"));
    }
}
