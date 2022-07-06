package serialization;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import serialization.pojos.location.Location;
import serialization.pojos.location.Place;


import java.util.List;

import static io.restassured.RestAssured.given;

public class Serialization {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //Adding Place in the API similar to google maps:
//{
//  "location": {
//    "lat": -38.383494,
//    "lng": 33.427362
//  },
//  "accuracy": 50,
//  "name": "Rahul Shetty, Academy",
//  "phone_number": "(+91) 983 893 3937",
//  "address": "29, side layout, cohen 09",
//  "types": [
//    "shoe park",
//    "shop"
//  ],
//  "website": "https://rahulshettyacademy.com",
//  "language": "French-IN"
//}


        Place place = new Place();
        Location location = new Location();

        location.setLat(-38.383494);
        location.setLng(33.427362);

        place.setAccuracy(50);
        place.setAddress("29, side layout, cohen 09");
        place.setLanguage("EN");
        place.setPhone_number("(+91) 983 893 3937");
        place.setWebsite("https://rahulshettyacademy.com");
        place.setName("House");
        place.setTypes(List.of("shoe park", "shop"));
        place.setLocation(location);

        //Add place
        Response response = given().queryParams("key", "qaclick123")
                .body(place)
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        String s = response.asString();
        System.out.println(s);
        JsonPath jsonPath = new JsonPath(s);
        String place_id = jsonPath.getString("place_id");

        //Get place
        String getPlaceResponse = given().log().all()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", place_id)
                .when().get("/maps/api/place/get/json")
                .then()
                .assertThat().log().all()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(getPlaceResponse);
    }
}
