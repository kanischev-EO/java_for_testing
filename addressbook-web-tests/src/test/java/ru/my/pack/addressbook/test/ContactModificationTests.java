package ru.my.pack.addressbook.test;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;
import ru.my.pack.addressbook.model.Contacts;



import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().contactPage();
    if (app.contact().all().size() ==0 ) {
      app.contact().gotoAdd();
      app.contact().create(new ContactData()
                      .withFirstName("Anton")
                      .withLastName("Tuzhilov")
                      .withAddress("Москва улица Новая")
                      .withEmail("anton@mail.ru")
                      .withGroup("new_test2"),
              true);
    }
  }

  @Test
  public void testContactModification() {

    Contacts before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifyContact.getId())
            .withFirstName("Новыое имя")
            .withLastName("Новая фамилия");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));


  }


}
