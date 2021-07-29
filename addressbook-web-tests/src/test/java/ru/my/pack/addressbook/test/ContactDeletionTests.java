package ru.my.pack.addressbook.test;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().closeAlertWindow();


  }
}
