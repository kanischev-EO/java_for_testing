package ru.my.pack.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.my.pack.addressbook.model.ContactData;

public class ContactHelper extends  HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitAddContact() {
   click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void initContactCreation(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstName() );
    type(By.name("lastname"),contactData.getLastName() );
    type(By.name("address"),contactData.getAddress() );
    type(By.name("mobile"),contactData.getPhoneNumber() );
    type(By.name("email"),contactData.getEmail() );
  }

  public void gotoAddContactPage() {
   click(By.linkText("add new"));
  }
  public void returnHomePage() {
   click(By.linkText("home"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeAlertWindow() {
    wd.switchTo().alert().accept();
  }

  public void updateContact() {
    click(By.xpath("//img[@alt='Edit']"));
  }
}
