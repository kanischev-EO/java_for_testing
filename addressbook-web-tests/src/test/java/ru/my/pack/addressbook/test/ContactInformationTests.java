package ru.my.pack.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;
import ru.my.pack.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactInformationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().contactPage();
    if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      app.contact().gotoAdd();
      app.contact().create(new ContactData()
                      .withFirstName("Anton")
                      .withLastName("Tuzhilov")
                      .withAddress("Москва, улица новая д 54 корпус б строение 1/3")
                      .inGroup(groups.iterator().next())
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
  public void testContactPhones() {
    app.contact().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmail(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));


  }

  public String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter(s -> !s.equals(""))
            .map(ContactInformationTests::cleanedPhones)
            .collect(Collectors.joining("\n"));

  }
  private String mergeEmail(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter(s -> !s.equals(""))
            .map(ContactInformationTests::cleanedEmails)
            .collect(Collectors.joining("\n"));

  }

  public static String cleanedPhones(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
  public static String cleanedEmails(String email) {
    return email.replaceAll("\\s", "");
  }
}
