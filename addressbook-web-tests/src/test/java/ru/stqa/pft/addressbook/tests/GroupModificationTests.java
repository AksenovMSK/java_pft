package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().submitGroupModifivation();
        app.getGroupHelper().returnToGroupPage();
    }
}
