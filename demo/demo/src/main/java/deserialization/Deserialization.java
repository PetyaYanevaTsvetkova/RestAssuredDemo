package deserialization;

import deserialization.pojod.entities.EntriesRepository;
import deserialization.pojod.entities.Entry;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Deserialization {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://api.publicapis.org/entries";

        EntriesRepository entries = given()
                .expect().defaultParser(Parser.JSON)
                .when()
                .get(baseURI)
                .as(EntriesRepository.class);
        System.out.println("Entries count: " + entries.getEntries().size());

        for (Entry entry : entries.getEntries()) {
            System.out.println(entry);
        }
    }
}
