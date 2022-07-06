package authentication;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//Step 1: Get Code from logged user:
        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWgERR9yyLWLwewKj4DDgn2kC_Acl9vAEdiIME4bPyjOEdpBeRktOnxpDsen8g1mGQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=1&prompt=none";
        String partialCode = url.split("code=")[1];
        String code = partialCode.split("&scope")[0];
        System.out.println(code);


//Step 2: get token:
        String accessTokenResponse = given().urlEncodingEnabled(false)
                .queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type", "authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token")
                .asString();
        JsonPath jsonPath = new JsonPath(accessTokenResponse);
        String accessToken = jsonPath.getString("access_token");
        System.out.println();

//Step 3: login in https://rahulshettyacademy.com/getCourse.php with token:
        String response = given().queryParam("access_token", accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println(response);
    }
}
