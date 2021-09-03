package ru.my.pack.addressbook.test;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.GroupData;
import ru.my.pack.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class GroupDeletionTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create( new GroupData().withName("new_test1").withHeader("new_test2").withFooter("new_test3"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.group().count(),equalTo( before.size() - 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(deletedGroup)));

  }




}
