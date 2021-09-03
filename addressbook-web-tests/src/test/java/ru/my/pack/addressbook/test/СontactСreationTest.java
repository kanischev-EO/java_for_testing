package ru.my.pack.addressbook.test;


import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;
import ru.my.pack.addressbook.model.Contacts;



import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class СontactСreationTest extends TestBase {


  @Test
  public void testContactCreation(){
    app.contact().contactPage();
    Contacts before = app.contact().all();
    app.contact().gotoAdd();
    ContactData contact = new ContactData()
            .withFirstName("Юлий3")
            .withLastName("Цезарь3")
            .withAddress("Мослица Новая1123")
            .withEmail("uliy@21.ru")
            .withGroup("new_test2");
    app.contact().create(contact,
            true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(c ->c.getId()).max().getAsInt()))));
    ;


  }


}
