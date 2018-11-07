package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        if(! app.getContacrHelper().isThereAContact()){
            app.getContacrHelper().createContact(new ContactData("Nametest", "Lastnametest", "Test home 123", "111111111", "test@mail.ru", "test1"));
            app.getNavigationHelper().returnToHomePage();
        }
        List<ContactData> before = app.getContacrHelper().getContactList();
        app.getContacrHelper().selectContacts(before.size() - 1);
        app.getContacrHelper().deletionSelectedContacts();
        app.getNavigationHelper().acceptAlert();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContacrHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before,after);
    }
}
