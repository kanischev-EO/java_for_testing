package ru.my.pack.addressbook.test;

import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;

import java.io.File;



public class ContactPhoto extends TestBase{
  @Test
  public void testContactCreation(){
    app.contact().contactPage();
    app.contact().gotoAdd();
    File photo = new File("src/test/resources/cat.jpeg");
    ContactData contact = new ContactData()
            .withFirstName("Юлий31")
            .withLastName("Цезарь31")
            .withPhoto(photo)
            .withGroup("new_test2");
    app.contact().create(contact,
            true);


  }



}
