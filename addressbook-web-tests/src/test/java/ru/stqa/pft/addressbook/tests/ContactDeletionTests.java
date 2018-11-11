package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if(! app.getContacrHelper().isThereAContact()){
            app.getContacrHelper().createContact(new ContactData("Nametest", "Lastnametest", "Test home 123", "111111111", "test@mail.ru", "test1"), true);
            app.getNavigationHelper().returnToHomePage();
        }
    }

    @Test
    public void testContactDeletion(){
        List<ContactData> before = app.getContacrHelper().getContactList();
        int index = before.size() - 1;
        app.getContacrHelper().selectContacts(index);
        app.getContacrHelper().deletionSelectedContacts();
        app.getNavigationHelper().acceptAlert();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContacrHelper().getContactList();
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        Assert.assertEquals(before,after);
    }
}
