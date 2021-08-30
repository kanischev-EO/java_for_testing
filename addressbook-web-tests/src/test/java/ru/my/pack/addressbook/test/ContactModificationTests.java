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
      app.contact().create(new ContactData()
                      .withFirstName("Anton")
                      .withLastName("Tuzhilov")
                      .withAddress("Москва улица Новая")
                      .withEmail("anton@mail.ru")
                      .withGroup("new_test2")
                      .withPhoneNumber("87959999999"),
              true);
    }
  }

  @Test
  public void testContactModification() {

    List<ContactData> before = app.contact().list();
    ContactData contactData = new ContactData()
            .withId(before.get(0).getId())
            .withFirstName("Антошка123")
            .withLastName("Тужилов123");
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
