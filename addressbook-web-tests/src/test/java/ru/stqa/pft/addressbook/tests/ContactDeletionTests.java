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

public class ContactDeletionTests extends TestBase {

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
                    .withEmail("test@mail.ru");
            File photo = new File("src/test/resources/stru.png");
            app.contact().create(contact.withPhoto(photo).inGroup(groups.iterator().next()), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDeletion(){
        Contacts before = app.db().contacts();
        ContactData deletionContact = before.iterator().next();
        app.contact().delete(deletionContact);
        app.goTo().acceptAlert();
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletionContact)));
        verifyContactListInUI();
    }
}
