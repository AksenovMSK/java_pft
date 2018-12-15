package ru.stqa.pft.mantis.tests;

import com.google.common.collect.Iterables;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangeUserPasswordTests extends TestBase  {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test

    public void testChangeUserPassword() throws IOException, MessagingException, InterruptedException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now) ;
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().wairForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);

        app.loginUI().in("administrator", "root");
        app.goTo().users();
        app.goTo().user(user);
        app.user().resetPassword();
        app.loginUI().out();

        String newPassword = "newpassword";
        List<MailMessage> newMailMessages = app.mail().wairForMail(1, 10000);
        MailMessage lastElement = Iterables.getLast(newMailMessages);
        List<MailMessage> listWithLastElement = new ArrayList<>();
        listWithLastElement.add(lastElement);
        String newConfirmationLink = findConfirmationLink(listWithLastElement, email);
        app.registration().enterNewPassword(newConfirmationLink, newPassword);

        app.newSession().login(user, newPassword);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
