package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        if(! app.getContacrHelper().isThereAContact()){
            app.getContacrHelper().createContact();
            app.getNavigationHelper().returnToHomePage();
        }
        List<ContactData> before = app.getContacrHelper().getContactList();
        app.getContacrHelper().selectContacts(before.size() - 1);
        app.getContacrHelper().deletionSelectedContacts();
        app.getNavigationHelper().acceptAlert();
        List<ContactData> after = app.getContacrHelper().getContactList();
        app.getNavigationHelper().returnToHomePage();
        Assert.assertEquals(after.size(), before.size() - 1);
    }
}
