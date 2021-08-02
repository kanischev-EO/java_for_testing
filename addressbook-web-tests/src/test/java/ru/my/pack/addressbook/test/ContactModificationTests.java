package ru.my.pack.addressbook.test;

import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification(){
    app.getContactHelper().selectContact();
    app.getContactHelper().updateContact();
    app.getContactHelper().initContactCreation(
            new ContactData("Антон", "Тужилов",
                    "Другая Улица", "89859278614", "антон@mail.ru", null), false);
    app.getGroupHelper().submitContactModification();

  }
}
