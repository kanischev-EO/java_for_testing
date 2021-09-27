package ru.my.pack.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.my.pack.addressbook.model.GroupData;
import ru.my.pack.addressbook.model.Groups;

import java.security.acl.Group;
import java.util.List;


public class GroupHelper extends HelperBase {
    private Groups groupsCache = null;


    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }


    public void deleteSelectGroups() {
        click(By.name("delete"));
    }

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void modify(GroupData groupData) {
        selectGroupById(groupData.getId());
        initGroupModification();
        fillGroupForm(groupData);
        submitGroupModification();
        groupsCache = null;
        returnToGroupPage();
    }

    public void createGroupIfNotExist(Groups groups, GroupData newGroup) {
        if (groups.size() == 0) {
            groupPage();
            GroupData group = new GroupData().withName(newGroup.getName()).withFooter(newGroup.getFooter()).withHeader(newGroup.getHeader());
            create(group);
        }
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupsCache = null;
        returnToGroupPage();
    }

    public void returnToGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1")) &&
                wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectGroups();
        groupsCache = null;
        returnToGroupPage();
    }

    public Groups all() {
        if (groupsCache != null) {
            return new Groups(groupsCache);
        }
        groupsCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement wb : elements) {
            String name = wb.getText();
            int id = Integer.parseInt(wb.findElement(By.tagName("input")).getAttribute("value"));
            groupsCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupsCache);
    }


}

