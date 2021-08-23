package ru.my.pack.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;
import ru.my.pack.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class СontactСreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoAddContactPage();
    ContactData contactData = new ContactData("on5",
            "T1234",
            "Мослица Новая11",
            "879599955591",
            "anton@2.ru",
            "new_test2");
    app.getContactHelper().createContact(contactData,
            true);
    app.getContactHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contactData);
    before.get(before.size()-1).setId(after.get(after.size()-1).getId());
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
