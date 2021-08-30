package ru.my.pack.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().gotoHomePage();
    if (app.contact().list().size() ==0 ) {
      app.contact().gotoAddContactPage();
      app.contact().create(new ContactData("Anton",
                      "Tuzhilov",
                      "Москва улица Новая",
                      "87959999999",
                      "anton@mail.ru",
                      "new_test2"),
              true);
    }
  }

  @Test
  public void testContactModification() {

    List<ContactData> before = app.contact().list();
    ContactData contactData = new ContactData(before.get(0).getId(),
            "Антошка123", "Тужилов123");
    app.contact().modify(contactData);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());
    before.remove(0);
    before.add(contactData);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);


  }


}
