package ru.my.pack.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.my.pack.addressbook.model.ContactData;
import ru.my.pack.addressbook.model.Contacts;
import ru.my.pack.addressbook.model.GroupData;


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
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());


    if (creation) {
      if(contactData.getGroups().size() > 0){
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
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

  public void initContactModificationByID(int id) {
//    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
//    WebElement row = checkbox.findElement(By.xpath("./../.."));
//    List<WebElement> cells = row.findElements(By.tagName("td"));
//    cells.get(7).findElement(By.tagName("a")).click();

//    wd.findElement(By.xpath(String.format("//input[@value='$s']/../../td[8]/a", id))).click();
//    wd.findElement(By.xpath(String.format("//tr[.//input[@value='$s']]/td[8]/a", id))).click();
    wd.findElement(By.cssSelector(String.format("a[href = 'edit.php?id=%s'", id))).click();
//    click(By.xpath(String.format("//input[@id='%s']//ancestor::tr//a/img[@title='Edit']", id)));

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
  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
  public void modify(ContactData contactData) {
    selectContactById(contactData.getId());
    initContactModificationByID(contactData.getId());
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
  public ContactData infoFromEditForm(ContactData contact){
    initContactModificationByID(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId())
            .withFirstName(firstName)
            .withLastName(lastName)
            .withHomePhone(home)
            .withMobilePhone(mobile)
            .withWorkPhone(work)
            .withEmail(email)
            .withEmail2(email2)
            .withEmail3(email3)
            .withAddress(address);
  }
  private Contacts contactsCache = null;

  public Contacts all() {
    if(contactsCache != null){
      return new Contacts(contactsCache);
    }
    contactsCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
//      String firstName = element.findElement(By.xpath("td[3]")).getText();
//      String lastName = element.findElement(By.xpath("td[2]")).getText();
//      String allPhones = element.findElement(By.xpath("td[6]")).getText();
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactsCache.add(new ContactData()
              .withFirstName(firstName)
              .withLastName(lastName)
              .withId(id)
              .withAllPhones(allPhones)
              .withAllEmails(allEmails)
              .withAddress(address));

    }
    return new Contacts(contactsCache);
  }

  public void addGroup( ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    selectGroupByname(group.getName());
    click(By.name("add"));
  }

  public void selectGroupByname(String nameGroup) {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(nameGroup);
  }

  public void deleteGroup(ContactData contact, GroupData groupContact) {
    groupFiltration(groupContact.getName());
    selectContactById(contact.getId());
    removeGroup();

  }

  public void removeGroup() {
    click(By.name("remove"));
  }

  public void groupFiltration(String nameGroup) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(nameGroup);
  }
}
