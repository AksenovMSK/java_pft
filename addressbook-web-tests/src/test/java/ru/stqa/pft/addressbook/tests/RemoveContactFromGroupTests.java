package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class RemoveContactFromGroupTests extends TestBase {

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
    }

    @Test
    public void testRemoveContactFromGroup(){

    }
}
