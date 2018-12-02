package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class AddAndRemoveContactFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if(app.db().contacts().size() == 0){
            ContactData contact = new ContactData()
                    .withFirstName("Nametest")
                    .withLastName("Lastnametest")
                    .withAddress("Test home 123")
                    .withMobilePhone("111111111")
                    .withFax("222222222")
                    .withEmail("test@mail.ru")
                    .withHomepage("testhomepage.ru");
            app.contact().create(contact, true);
            app.goTo().homePage();
        }
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test
    public void testAddContactToGroup(){
        app.db().cleanRelationsBetweenContactsAndGroups();
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData modifyContact = contacts.iterator().next();
        GroupData selectedGroup = groups.iterator().next();
        app.contact().addToGroup(modifyContact , selectedGroup);
        app.goTo().addedGroupPage();
    }

    @Test
    public void testRemoveContactFromGroup(){

    }
}
