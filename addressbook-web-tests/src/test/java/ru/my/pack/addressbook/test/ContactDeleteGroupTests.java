package ru.my.pack.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;
import ru.my.pack.addressbook.model.Contacts;
import ru.my.pack.addressbook.model.GroupData;
import ru.my.pack.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDeleteGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        ContactAddGroupTests  helper = new ContactAddGroupTests();
        helper.ensurePreconditions();
        Contacts allContacts = app.db().contacts();
        for (ContactData contact : allContacts){
            if(contact.getGroups().size() > 0){
                return;
            }
        }
        helper.ContactAddGroupTest();
    }
    @Test
    public void contactDeleteGroupTest(){
        app.contact().contactPage();
        ContactData before = Contacts.getContactWithGroup(app.db().contacts());
        Groups groupsBefore = before.getGroups();
        GroupData deletedGroup = before.getGroups().iterator().next();
        app.contact().deleteGroup(before, deletedGroup);
        ContactData after = app.db().contacts().getInfoOnContact(before);
        Groups groupsAfter = after.getGroups();
        assertThat(groupsAfter.size(), equalTo(groupsBefore.size()-1));
        assertThat(groupsAfter, equalTo(groupsBefore.without(deletedGroup)));

    }
}
