package ru.my.pack.addressbook.test;

import org.testng.annotations.*;


public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectGroups();
    app.getNavigationHelper().gotoHomePage();
  }


}
