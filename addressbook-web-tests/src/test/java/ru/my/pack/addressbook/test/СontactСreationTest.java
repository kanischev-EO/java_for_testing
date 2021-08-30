package ru.my.pack.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class СontactСreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.contact().gotoHomePage();
    List<ContactData> before = app.contact().list();
    app.contact().gotoAddContactPage();
    ContactData contactData = new ContactData()
            .withFirstName("Юлий")
            .withLastName("Цезарь")
            .withAddress("Мослица Новая1123")
            .withEmail("uliy@21.ru")
            .withGroup("test2");
    app.contact().create(contactData,
            true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contactData);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    after.sort(byId);
    before.get(before.size()-1).withId(after.get(after.size()-1).getId());
    before.sort(byId);
    Assert.assertEquals(before, after);
  }


}
