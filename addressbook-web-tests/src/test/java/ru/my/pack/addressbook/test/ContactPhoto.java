package ru.my.pack.addressbook.test;

import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;
import ru.my.pack.addressbook.model.Groups;

import java.io.File;



public class ContactPhoto extends TestBase{
  //Если нет ни одной группы, то создать группу


  @Test
  public void testContactCreation(){
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/cat.jpeg");
    ContactData newContact = new ContactData()
            .withFirstName("Юлий31")
            .withLastName("Цезарь31")
            .withPhoto(photo)
            .inGroup(groups.iterator().next());
    app.contact().contactPage();
    app.contact().gotoAdd();
    app.contact().create(newContact,
            true);


  }



}
