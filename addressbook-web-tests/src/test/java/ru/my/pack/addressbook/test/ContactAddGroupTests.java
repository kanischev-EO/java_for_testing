package ru.my.pack.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;

import ru.my.pack.addressbook.model.Contacts;
import ru.my.pack.addressbook.model.GroupData;
import ru.my.pack.addressbook.model.Groups;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactAddGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        GroupData newGroupData = new GroupData().withName("new_test1").withHeader("new_test2").withFooter("new_test3");
        ContactData newContactData = new ContactData().withFirstName("Anton").withLastName("Tuzhilov").withAddress("Москва улица Новая")
                .withEmail("anton@mail.ru");
        app.group().createGroupIfNotExist(app.db().groups(), newGroupData);
        app.contact().createContactIfNotExist(app.db().contacts(), newContactData);
    }


    @Test
    void contactAddGroupTest() {
//        ContactData contact = app.db().contacts().iterator().next();
//        GroupData groupToAdd = prepareGroupsBeforeOperation(contact);
        Map<ContactData, GroupData> groupToAddAndContact = getContactWithoutGroupAndGroupForAdd(app.db().contacts(), app.db().groups());
        GroupData groupToAdd = null;
        ContactData contact = null;
        for (Map.Entry<ContactData, GroupData> map : groupToAddAndContact.entrySet()) {
            contact = map.getKey();
            groupToAdd = map.getValue();
        }
        app.contact().addGroup(contact, groupToAdd);
        Groups groupsAfterOperation = getGroupsAfterOperation(contact);
        assertThat(groupsAfterOperation.size(), equalTo(contact.getGroups().size() + 1));
        int maxIdFromGroupsAfterOperation = groupsAfterOperation.stream()
                .mapToInt(g -> g.getId())
                .max()
                .getAsInt();
        assertThat(groupsAfterOperation, equalTo(contact.getGroups()
                .withAdded(groupToAdd
                        .withId(maxIdFromGroupsAfterOperation))));
    }

    private Groups getGroupsAfterOperation(ContactData contactBeforeOperation) {
        ContactData contactAfterOperation = app.db().contacts().getInfoOnContact(contactBeforeOperation);
        return contactAfterOperation.getGroups();
    }


    public Map<ContactData, GroupData> getContactWithoutGroupAndGroupForAdd(Contacts contacts, Groups groups) {
        Map<ContactData, GroupData> contactDataAndGroupData = new HashMap<>();
        for (ContactData contact : contacts) {
            Groups groupContact = contact.getGroups();
            for (GroupData group : groups) {
                if (!groupContact.contains(group)) {
                    contactDataAndGroupData.put(contact, group);
                    return contactDataAndGroupData;
                }
            }
        }
        if (contactDataAndGroupData.isEmpty()) {
            app.group().groupPage();
            GroupData groupToAdd = new GroupData().withName("Auto").withHeader("Auto").withFooter("Auto");
            app.group().create(groupToAdd);
            ContactData contact = contacts.iterator().next();
            contactDataAndGroupData.put(contact, groupToAdd);
        }
        return contactDataAndGroupData;
    }
}
