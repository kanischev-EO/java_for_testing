package ru.my.pack.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().gotoHomePage();
    if (app.contact().list().size() == 0) {
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
  public void testContactDeletion() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Assert.assertEquals(before, after);


  }

}
