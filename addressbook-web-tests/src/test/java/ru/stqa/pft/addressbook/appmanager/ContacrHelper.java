package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContacrHelper extends BaseHelper {

    public ContacrHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
      click(By.xpath("//*[@id=\"content\"]/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstname());
      type(By.name("lastname"), contactData.getLastname());
      type(By.name("address"), contactData.getAddress());
      type(By.name("mobile"), contactData.getMobile());
      type(By.name("email"), contactData.getEmail());

      if(creation){
          String existingText = wd.findElement(By.name("new_group")).getAttribute("value");
          if(! existingText.equals("[none]")) {
              new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
          }
      } else {
          Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }

    public void initContact() {
      click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        click(By.name("selected[]"),index);
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void initContactModification(int id) {

        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModifivation() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void create(ContactData contactData, boolean creation) {
        initContact();
        fillContactForm(contactData, true);
        submitContactCreation();
        contactCach = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCach = null;

    public Contacts all() {
        if(contactCach != null){
            return new Contacts(contactCach);
        }
        contactCach = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements){
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            contactCach.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
        }
        return new Contacts(contactCach);
    }


    public void modify(ContactData contactData, boolean creation) {
        initContactModification(contactData.getId());
        fillContactForm(contactData, false);
        submitContactModifivation();
        contactCach = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        contactCach = null;
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }
}
