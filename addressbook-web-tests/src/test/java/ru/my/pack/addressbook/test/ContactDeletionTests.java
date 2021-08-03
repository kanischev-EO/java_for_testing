package ru.my.pack.addressbook.test;

import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().gotoAddContactPage();
      app.getContactHelper().createContact(new ContactData("Anton",
                      "Tuzhilov",
                      "Москва улица Новая",
                      "87959999999",
                      "anton@mail.ru",
                      "new_test2"),
              true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().closeAlertWindow();


  }
}
