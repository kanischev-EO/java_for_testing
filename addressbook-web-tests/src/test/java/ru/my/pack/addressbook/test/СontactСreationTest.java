package ru.my.pack.addressbook.test;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.my.pack.addressbook.model.ContactData;
import ru.my.pack.addressbook.model.Contacts;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class СontactСreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactFromJson() throws IOException{
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.json"));
    String json ="";
    String line = reader.readLine();
    while (line != null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json,new TypeToken<List<ContactData>>(){}.getType());//List<GroupData>.class
    return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validContactFromXml() throws IOException{
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.xml"));
    String xml ="";
    String line = reader.readLine();
    while (line != null){
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    xStream.addPermission(AnyTypePermission.ANY);
    List<ContactData> contacts =(List<ContactData>) xStream.fromXML(xml);
    return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
  }


  @Test(dataProvider = "validContactFromJson")
  public void testContactCreation(ContactData contact){
    app.contact().contactPage();
    Contacts before = app.contact().all();
    app.contact().gotoAdd();
    app.contact().create(contact,
            true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(c ->c.getId()).max().getAsInt()))));
    ;


  }


}
