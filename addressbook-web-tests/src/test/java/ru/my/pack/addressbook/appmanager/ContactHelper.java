package ru.my.pack.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.my.pack.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitAddContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }
  public void submitModifyContact(){
    click(By.name("update"));
  }

  public void initContactCreation(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getPhoneNumber());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void gotoAddContactPage() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
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

  public void create(ContactData contact, boolean creation) {
    initContactCreation(contact, creation);
    submitAddContact();
    gotoHomePage();
  }

  public void gotoHomePage() {
    click(By.linkText("home"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
  public void modify(ContactData contactData) {
    selectContact(0);
    updateContact();
    initContactCreation(contactData, false);
    submitModifyContact();
    gotoHomePage();
  }

  public List<ContactData> list() {
    List<ContactData> contact = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String firstName = element.findElement(By.xpath("td[3]")).getText();
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contact.add(new ContactData().withFirstName(firstName).withLastName(lastName).withId(id));
    }
    return contact;
  }
  public void delete(int index) {
    selectContact(index);
    deleteContact();
    closeAlertWindow();
    gotoHomePage();
  }
}
