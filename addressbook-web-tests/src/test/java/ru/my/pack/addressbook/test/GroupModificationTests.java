package ru.my.pack.addressbook.test;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.GroupData;
import ru.my.pack.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().create( new GroupData().withName("new_testmodif1").withHeader("new_testmodif2").withFooter("new_testmodif3"));
    }
  }
  @Test
  public void testGroupModification() {
    Groups before = app.db().groups();
    GroupData modifyGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifyGroup.getId())
            .withName("new_test2")
            .withHeader("new_test3")
            .withFooter("new_test4");
    app.group().modify(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifyGroup).withAdded(group)));

  }


}
