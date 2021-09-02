package ru.my.pack.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.my.pack.addressbook.model.ContactData;
import ru.my.pack.addressbook.model.Contacts;


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

  public void gotoAdd() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }
  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeAlertWindow() {
    wd.switchTo().alert().accept();
  }

  public void updateContact(int id) {
    click(By.xpath("//input[@id=" + id + "]//ancestor::tr//a/img[@title='Edit']"));
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation(contact, creation);
    submitAddContact();
    contactsCache = null;
    contactPage();
  }

  public void contactPage() {
    click(By.linkText("home"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
  public void modify(ContactData contactData) {
    selectContactById(contactData.getId());
    updateContact(contactData.getId());
    initContactCreation(contactData, false);
    submitModifyContact();
    contactsCache = null;
    contactPage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    closeAlertWindow();
    contactsCache = null;
    contactPage();
  }
  private Contacts contactsCache = null;

  public Contacts all() {
    if(contactsCache != null){
      return new Contacts(contactsCache);
    }
    contactsCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String firstName = element.findElement(By.xpath("td[3]")).getText();
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactsCache.add(new ContactData().withFirstName(firstName).withLastName(lastName).withId(id));
    }
    return new Contacts(contactsCache);
  }
}
