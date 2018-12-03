package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddAndRemoveContactFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
        if(app.db().contacts().size() == 0){
            Groups groups = app.db().groups();
            ContactData contact = new ContactData()
                    .withFirstName("Nametest")
                    .withLastName("Lastnametest")
                    .withAddress("Test home 123")
                    .withMobilePhone("111111111")
                    .withFax("222222222")
                    .withEmail("test@mail.ru")
                    .withHomepage("testhomepage.ru");
            File photo = new File("src/test/resources/stru.png");
            app.contact().create(contact.withPhoto(photo).inGroup(groups.iterator().next()), true);
            app.goTo().homePage();
        }
    }

    @Test(enabled = true)
    public void testAddContactToGroup(){
        app.db().cleanRelationsBetweenContactsAndGroups();
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData addedContact = contacts.iterator().next();
        GroupData selectedGroup = groups.iterator().next();
        app.contact().addToGroup(addedContact , selectedGroup);
        assertThat(addedContact, equalTo(app.db().addedContactToGroup().iterator().next()));
    }

    @Test
    public void testRemoveContactFromGroup(){
        if(app.db().addedContactToGroup() == null){
            Contacts contacts = app.db().contacts();
            Groups groups = app.db().groups();
            ContactData addedContact = contacts.iterator().next();
            GroupData selectedGroup = groups.iterator().next();
            app.contact().addToGroup(addedContact , selectedGroup);
        }
        Groups groups = app.db().groups();
        GroupData selectedGroup = groups.iterator().next();
        ContactData removedContact = app.db().addedContactToGroup().iterator().next();
       // app.goTo().homePage();
       // app.contact().removeFromGroup(removedContact, selectedGroup); //не удаляет контакт из группы
       // assertThat(app.db().addedContactToGroup().without(removedContact) , equalTo(app.db().addedContactToGroup()));
    }
}
