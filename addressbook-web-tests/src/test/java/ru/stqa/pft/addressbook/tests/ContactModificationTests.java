package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getContacrHelper().selectContacts();
        app.getContacrHelper().initContactModification();
        app.getContacrHelper().fillContactForm(new ContactData("Nametest", "Lastnametest", "Test home 123", "111111111", "test@mail.ru"));
        app.getContacrHelper().submitContactModifivation();
        app.getNavigationHelper().returnToHomePage();
    }
}
