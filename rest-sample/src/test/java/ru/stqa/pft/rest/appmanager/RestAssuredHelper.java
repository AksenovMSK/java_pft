package ru.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

public class RestAssuredHelper {

    private ApplicationManager app;

    public RestAssuredHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Issue> getIssues() throws IOException {
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json").asString();

        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = RestAssured.given()
                .parameter("subject", newIssue.getSubject())
                .parameter("description" , newIssue.getDescription())
                .post("http://bugify.stqa.ru/api/issues.json").asString();

        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
