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
    public void contactAddGroupTest() {
        app.contact().contactPage();
        //1)Подготовка данных
        ContactData contactBeforeOperation = app.db().contacts().iterator().next();
        Groups groupsBeforeOperation = contactBeforeOperation.getGroups();
        Groups allGroups = app.db().groups();
        GroupData groupToAdd =  allGroups.iterator().next();
        if(contactBeforeOperation.getGroups().contains(groupToAdd)){
            groupToAdd = allGroups.nextElement(groupToAdd) ;
        }
        //2)Операция
        app.contact().addGroup(contactBeforeOperation, groupToAdd);
        //3)Данные после операции
        ContactData contactAfterOperation = app.db().contacts().getInfoOnContact(contactBeforeOperation);
        Groups groupsAfterOperation = contactAfterOperation.getGroups();
        //4)
        assertThat( groupsAfterOperation.size(), equalTo(groupsBeforeOperation.size()+ 1));
        assertThat( groupsAfterOperation, equalTo(groupsBeforeOperation.withAdded(groupToAdd)));


    }

    //1)Cоздать приватный метод createGroupIfNotExist
    //2Cоздать приватный метод createContactIfNotExist
    //3)Вынести из 1 пунтка метод для подготовки данныъ prepareGroupsBeforeOperation return GroupsBeforeOperation
    //4)3 пункт возможно вынести в метод getGroupAfterOperation
}
