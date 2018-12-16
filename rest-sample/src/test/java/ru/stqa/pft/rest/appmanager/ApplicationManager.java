package ru.stqa.pft.rest.appmanager;

public class ApplicationManager {

    private RestAssuredHelper restAssuredHelper;
    private RestHelper restHelper;

    public RestAssuredHelper restAssured() {
        if(restAssuredHelper == null){
            restAssuredHelper = new RestAssuredHelper(this);
        }
        return restAssuredHelper;
    }

    public RestHelper rest() {
        if(restHelper == null){
            restHelper = new RestHelper(this);
        }
        return restHelper;
    }
}
