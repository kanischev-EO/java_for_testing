package ru.my.pack.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {
  private Set<ContactData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<ContactData>();
  }

  public Contacts(Collection <ContactData> contacts) {
    this.delegate = new HashSet<ContactData>(contacts);

  }

  public static ContactData getContactWithGroup(Contacts contacts) {
    ContactData contactWithGroup = null;
    for (ContactData con : contacts ){
      if(con.getGroups().size() > 0){
        contactWithGroup = con;
      }
    }
    return contactWithGroup;
  }

  @Override
  protected Set delegate() {
    return delegate;
  }

  public Contacts withAdded (ContactData contact){
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }
  public Contacts without (ContactData contact){
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }


  public ContactData getInfoOnContact(ContactData contact) {
    ContactData desiredСontact = null;
    for(ContactData contactData : delegate){
     if(contactData.getId() == contact.getId()){
     desiredСontact = contactData;
     break;
     }
    }
    return desiredСontact;
  }

  public ContactData nextElement(ContactData existingContact) {
    ContactData contact = null;
    for (ContactData cn : delegate){
      if(!cn.equals(existingContact)){
        contact = cn;
        break;
      }
    }
    return contact;

  }
}
