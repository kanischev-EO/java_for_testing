package ru.my.pack.addressbook.generators;

import ru.my.pack.addressbook.model.GroupData;

import java.io.File;
import java.util.List;

public class GroupDataGenerators {
  public static void main(String[] args) {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);
    List<GroupData> groups = generateGroups(count);
    save(groups, file);
  }

  private static void save(List<GroupData> groups, File file) {

  }

  private static List<GroupData> generateGroups(int count) {
    return null;
  }
}
