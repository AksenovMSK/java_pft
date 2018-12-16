package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager();

    public boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException {
//        boolean result;
//        if(app.soap().checkIssueStatus(issueId)){
//            result = false;
//        } else {
//            result = true;
//        }
        return true;
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public boolean checkIssueStatus(int issueId)throws MalformedURLException, RemoteException {
//        MantisConnectPortType mc = getMantisConnect();
//        IssueData issueData = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
//        ObjectRef status = issueData.getStatus();
//        return status.getName().equals("resolved");
        return true;
    }
}
