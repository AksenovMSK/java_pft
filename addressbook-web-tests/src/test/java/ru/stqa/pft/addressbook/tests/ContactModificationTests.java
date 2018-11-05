package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        if(! app.getContacrHelper().isThereAContact()){
            app.getContacrHelper().createContact();
            app.getNavigationHelper().returnToHomePage();
        }
        List<ContactData> before = app.getContacrHelper().getContactList();
        app.getContacrHelper().selectContacts(before.size() - 1);
        app.getContacrHelper().initContactModification();
        app.getContacrHelper().fillContactForm(new ContactData("Nametest", "Lastnametest", "Test home 123", "111111111", "test@mail.ru", null), false);
        app.getContacrHelper().submitContactModifivation();
        app.getNavigationHelper().returnToHomePage();
    }
}
