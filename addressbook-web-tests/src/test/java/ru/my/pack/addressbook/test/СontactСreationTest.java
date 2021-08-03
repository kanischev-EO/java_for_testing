package ru.my.pack.addressbook.test;

import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;

public class СontactСreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().gotoAddContactPage();
    app.getContactHelper().createContact(new ContactData("Anton1",
                    "Tuzhilov",
                    "Москва улица Новая",
                    "87959999999",
                    "anton@mail.ru",
                    "new_test2"),
            true);

  }


}
