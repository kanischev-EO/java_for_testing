package ru.my.pack.addressbook.test;

import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("new_test1", "" +
            "new_test2", "new_test3"));
    app.getContactHelper().submitGroupModification();
    app.getGroupHelper().returntoGroupPage();
  }
}
