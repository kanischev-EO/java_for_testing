package ru.my.pack.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity

@Table(name = "group_list")

public class GroupData {
  @XStreamOmitField
  @Id
  @Column(name = "group_id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "group_footer")
  @Type(type = "text")
  private String footer;

  @Expose
  @Column(name = "group_name")
  private String name;

  @ManyToMany(mappedBy = "groups")
  private Set<ContactData> contacts = new HashSet<ContactData>();

  @Expose
  @Column(name = "group_header")
  @Type(type = "text")
  private String header;

  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return id == groupData.id && Objects.equals(footer, groupData.footer) && Objects.equals(name, groupData.name) && Objects.equals(header, groupData.header);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, footer, name, header);
  }

  public GroupData withId(int id) {
    this.id =  id;
    return this;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", footer='" + footer + '\'' +
            ", name='" + name + '\'' +
            ", header='" + header + '\'' +
            '}';
  }

  public String getName() {
    return name;
  }

  public Contacts getContacts() {
    return new Contacts(contacts);
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  public int getId() {
    return id; }
}
