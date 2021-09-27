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
        GroupData newGroupData = new GroupData().withName("new_test1").withHeader("new_test2").withFooter("new_test3");
        ContactData newContactData = new ContactData().withFirstName("Anton").withLastName("Tuzhilov").withAddress("Москва улица Новая")
                .withEmail("anton@mail.ru");
        app.group().createGroupIfNotExist(app.db().groups(), newGroupData);
        app.contact().createContactIfNotExist(app.db().contacts(), newContactData);
    }


    @Test
     void contactAddGroupTest() {
        ContactData contact = app.db().contacts().iterator().next();
        GroupData groupToAdd = prepareGroupsBeforeOperation(contact);
        app.contact().addGroup(contact, groupToAdd);
        Groups groupsAfterOperation = getGroupsBeforeOperation(contact);
        assertThat(groupsAfterOperation.size(), equalTo(contact.getGroups().size() + 1));
        int maxIdFromGroupsAfterOperation = groupsAfterOperation.stream()
                .mapToInt(g -> g.getId())
                .max()
                .getAsInt();
        assertThat(groupsAfterOperation, equalTo(contact.getGroups()
                                .withAdded(groupToAdd
                                        .withId(maxIdFromGroupsAfterOperation))));


    }

    private Groups getGroupsBeforeOperation(ContactData contactBeforeOperation) {
        ContactData contactAfterOperation = app.db().contacts().getInfoOnContact(contactBeforeOperation);
        return contactAfterOperation.getGroups();
    }

    private GroupData prepareGroupsBeforeOperation(ContactData contact) {
        Groups contactGroups = contact.getGroups();
        Groups allGroups = app.db().groups();
        return getGroupDataToAdd(allGroups, contactGroups);
    }

    private GroupData getGroupDataToAdd(Groups allGroups, Groups contactGroups) {
        GroupData groupToAdd;
        for (GroupData groupContact : contactGroups) {
            if (allGroups.contains(groupContact)) {
                allGroups.remove(groupContact);
            }
        }
        if (allGroups.isEmpty()) {
            app.group().groupPage();
            groupToAdd = new GroupData().withName("Auto_test").withHeader("Auto_test").withFooter("Auto_test");
            app.group().create(groupToAdd);
        } else {
            groupToAdd = allGroups.iterator().next();
        }
        return groupToAdd;
    }
}

//1+)Cоздать приватный метод createGroupIfNotExist
//2+)Cоздать приватный метод createContactIfNotExist
//3)Вынести из 1 пунтка метод для подготовки данныъ prepareGroupsBeforeOperation return GroupsBeforeOperation
//4)3 пункт возможно вынести в метод getGroupAfterOperation

