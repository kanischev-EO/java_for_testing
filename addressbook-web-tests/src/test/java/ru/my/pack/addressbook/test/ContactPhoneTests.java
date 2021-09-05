package ru.my.pack.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase {
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
                      .withHomePhone("111 111")
                      .withMobilePhone("+7 (111)")
                      .withWorkPhone("22-22-22"),
              true);
    }
  }

  @Test
  public void testContactPhones() {
    app.contact().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));


  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter(s -> !s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));

  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
