package ru.my.pack.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;
import ru.my.pack.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;


public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().contactPage();
    if (app.contact().all().size() == 0) {
      app.contact().gotoAdd();
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
  public void testContactDeletion() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));


  }

}
