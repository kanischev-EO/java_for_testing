package ru.my.pack.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.my.pack.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerators {
  @Parameter(names = "-c", description = "Contact count")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;
  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerators generator = new ContactDataGenerators();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException exception) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContact(count);
    if (format.equals("csv")) {
      saveCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();

  }

  private void saveXml(List<ContactData> contacts, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    String xml = xStream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private List<ContactData> generateContact(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
              .withLastName(String.format("test_last_name %s", i))
              .withFirstName(String.format("test_first_name %s", i))
              .withAddress(String.format("test_address %s", i))
              .withEmail(String.format("test@email%s.ru", i))
              .withEmail2(String.format("test@2email%s.ru", i))
              .withEmail3(String.format("test@3email%s.ru", i))
              .withGroup("test1")
              .withWorkPhone(String.format("7(985)-123-%s", i))
              .withMobilePhone(String.format("7(985)-123-%s", i))
              .withHomePhone(String.format("7(985)-123-%s", i)));

    }
    return contacts;
  }

  private void saveCsv(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contactData : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
              contactData.getLastName(),
              contactData.getFirstName(),
              contactData.getAddress(),
              contactData.getEmail(),
              contactData.getEmail2(),
              contactData.getEmail3(),
              contactData.getGroup(),
              contactData.getWorkPhone(),
              contactData.getHomePhone(),
              contactData.getMobilePhone()));
    }
    writer.close();
  }
}
