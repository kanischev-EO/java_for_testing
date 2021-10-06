package ru.my.pack.mantis.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.my.pack.mantis.model.Issue;
import ru.my.pack.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {
    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project: projects){
            System.out.println(project);
        }


    }
    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue7")
                .withDescription("Test issue description7").withProject(projects.iterator().next());
       Issue created = app.soap().addIssue(issue);
       Assert.assertEquals(issue.getSummary(), created.getSummary());

    }

}
