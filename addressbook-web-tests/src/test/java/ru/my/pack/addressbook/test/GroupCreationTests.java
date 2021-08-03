package ru.my.pack.addressbook.test;


import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test", "test1", "test2"));
  }

}
