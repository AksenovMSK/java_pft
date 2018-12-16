package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager();

    public boolean isIssueOpen(int issueId) throws IOException {
        boolean result;
        if(app.rest().checkIssueStatus(issueId)){
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
