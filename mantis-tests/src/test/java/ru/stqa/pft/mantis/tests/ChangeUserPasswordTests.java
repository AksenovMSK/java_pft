package ru.stqa.pft.mantis.tests;

import com.google.common.collect.Iterables;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

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

    public void testChangeUserPassword() throws IOException, MessagingException {

        User user = app.db().users().iterator().next();

        app.loginUI().in("administrator", "root");
        app.goTo().users();
        app.goTo().user(user.getUsername());
        app.user().resetPassword();
        app.loginUI().out();

        String newPassword = "newpassword";
        List<MailMessage> MailMessages = app.mail().wairForMail(1, 10000);
        String ConfirmationLink = findConfirmationLink(MailMessages, user.getEmail());
        app.registration().enterNewPassword(ConfirmationLink, newPassword);

        assertTrue(app.newSession().login(user.getUsername(), newPassword));
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
