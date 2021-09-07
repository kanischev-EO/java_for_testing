package ru.my.pack.addressbook.test;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.my.pack.addressbook.appmanager.ApplicationManager;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  @BeforeSuite
  public void setUp(){
    app.init();
  }

  @AfterSuite
  public void tearDown(){
    app.stop();
  }

}
