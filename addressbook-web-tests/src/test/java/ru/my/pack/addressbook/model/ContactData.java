package ru.my.pack.addressbook.model;

import java.util.Objects;

public class ContactData {
  private String firstName;
  private String lastName;
  private String address;
  private String phoneNumber;
  private String email;
  private String group;
  private String workPhone;
  private String homePhone;
  private String mobilePhone;
  private String allPhones;

  public ContactData withWorkPhone(String phoneNumber) {
    this.workPhone = phoneNumber;
    return this;
  }
  public ContactData withHomePhone(String phoneNumber) {
    this.homePhone = phoneNumber;
    return this;
  }
  public ContactData withMobilePhone(String phoneNumber) {
    this.mobilePhone = phoneNumber;
    return this;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  private int id = Integer.MAX_VALUE;

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, id);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", id=" + id +
            '}';
  }

  public ContactData withId(int id){
    this.id = id;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }
}
