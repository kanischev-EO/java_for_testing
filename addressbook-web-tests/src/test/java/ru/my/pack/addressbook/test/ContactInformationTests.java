package ru.my.pack.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactInformationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().contactPage();
    if (app.contact().all().size() == 0) {
      app.contact().gotoAdd();
      app.contact().create(new ContactData()
                      .withFirstName("Anton")
                      .withLastName("Tuzhilov")
                      .withAddress("Москва, улица новая д 54 корпус б строение 1/3")
                      .withGroup("new_test2")
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
    assertThat(contact.getAllPhones(), equalTo(merge(contactInfoFromEditForm, "phone")));
    assertThat(contact.getAllEmails(), equalTo(merge(contactInfoFromEditForm, "email")));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));


  }
  
  private String merge(ContactData contact, String emailOrPhone) {
    List<String> list = null;
    if(emailOrPhone.equals("email")){
      list = Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3());
    }else if (emailOrPhone.equals("phone")){
      list = Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone());
    }
    return list.stream().filter(s -> !s.equals(""))
            .map(ContactInformationTests::cleaned)
            .collect(Collectors.joining("\n"));

  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
