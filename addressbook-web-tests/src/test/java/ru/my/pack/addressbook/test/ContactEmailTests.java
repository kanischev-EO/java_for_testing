package ru.my.pack.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {
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
                      .withEmail2("anton2@mail.ru")
                      .withEmail3("anton3@mail.ru")
                      .withGroup("new_test2"),
              true);
    }
  }

  @Test
  public void testContactEmail() {
    app.contact().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmail(contactInfoFromEditForm)));


  }

  private String mergeEmail(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter(s -> !s.equals(""))
            .map(ContactEmailTests::cleaned)
            .collect(Collectors.joining("\n"));

  }
  public static String cleaned(String email) {
    return email.replaceAll("\\s", "");
  }
}
