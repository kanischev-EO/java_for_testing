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
    if (app.db().contacts().size() ==0 ) {
      app.contact().gotoAdd();
      app.contact().create(new ContactData()
              .withFirstName("Anton")
              .withLastName("Tuzhilov")
              .withAddress("Москва, улица новая д 54 корпус б строение 1/3")
              .withHomePhone("111 111")
              .withMobilePhone("+7 (111)")
              .withWorkPhone("22-22-22")
              .withEmail("anton@mail.ru")
              .withEmail2("anton2@mail.ru")
              .withEmail3("anton3@mail.ru"),
              true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifyContact.getId())
            .withFirstName("Новыое имя1")
            .withLastName("Новая фамилия1")
            .withAddress(modifyContact.getAddress())
            .withHomePhone(modifyContact.getHomePhone())
            .withMobilePhone(modifyContact.getMobilePhone())
            .withWorkPhone(modifyContact.getWorkPhone())
            .withEmail(modifyContact.getEmail())
            .withEmail2(modifyContact.getEmail2())
            .withEmail3(modifyContact.getEmail3());
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));


  }


}
