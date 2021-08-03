package ru.my.pack.addressbook.test;

import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.GroupData;


public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("new_test1",
              "new_test2", "new_test3"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectGroups();
    app.getGroupHelper().returnToGroupPage();
  }


}
