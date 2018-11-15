package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if(app.contact().all().size() == 0){
            ContactData contact = new ContactData()
                    .withFirstname("Nametest")
                    .withLastname("Lastnametest")
                    .withAddress("Test home 123")
                    .withMobile("111111111")
                    .withEmail("test@mail.ru").withGroup("test1");
            app.contact().create(contact, true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification(){

        Contacts before = app.contact().all();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifyContact.getId())
                .withFirstname("Nametest")
                .withLastname("Lastnametest")
                .withAddress("Test home 123")
                .withMobile("111111111")
                .withEmail("test@mail.ru");
        app.contact().modify(contact,false);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));
        
        assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
    }
}
