package ru.my.pack.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;
import ru.my.pack.addressbook.model.GroupData;
import ru.my.pack.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactAddGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("new_test1").withHeader("new_test2").withFooter("new_test3"));
        }
        if (app.db().contacts().size() == 0) {
            app.contact().contactPage();
            app.contact().gotoAdd();
            app.contact().create(new ContactData()
                            .withFirstName("Anton")
                            .withLastName("Tuzhilov")
                            .withAddress("Москва улица Новая")
                            .withEmail("anton@mail.ru"),
                    true);
        }
    }

    @Test
    public void ContactAddGroupTest() {
        app.contact().contactPage();
        ContactData before = app.db().contacts().iterator().next();
        Groups groupsBefore = before.getGroups();
        Groups allGroups = app.db().groups();
        GroupData groupToAdd =  allGroups.iterator().next();
        if(before.getGroups().contains(groupToAdd)){
            groupToAdd = allGroups.nextElement(groupToAdd) ;
        }
        app.contact().addGroup( before, groupToAdd);
        ContactData after = app.db().contacts().getInfoOnContact(before);
        Groups groupsAfter = after.getGroups();
        assertThat( after.getGroups().size(), equalTo(before.getGroups().size()+ 1));
        assertThat( groupsAfter, equalTo(groupsBefore.withAdded(groupToAdd)));


    }
}
