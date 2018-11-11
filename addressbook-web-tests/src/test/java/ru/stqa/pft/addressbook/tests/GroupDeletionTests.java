package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().gotoGroupPage();
    if(! app.group().isThereAGroup()){
      app.group().createGroup(new GroupData("test1", "test2", "test3"));
    }
  }

  @Test
  public void testGroupDeletion(){

    List<GroupData> before = app.group().getGroupList();
    app.group().selectGroup(before.size() - 1);
    app.group().deletionSelectedGroups();
    app.group().returnToGroupPage();
    List<GroupData> after = app.group().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before,after);
  }

}
