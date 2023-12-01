package TestAPI_Challenge.tests.status_api.requests;

import TestAPI_Challenge.setup.PageObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class StatusRequest extends PageObject {

	@Step("Testando Status da API")
	public Response getStatus() {
		String uri = getUri(BASIC_TEST);

		return when()
				.get(uri)
				.then()
				.log().all()
				.statusCode(HttpStatus.SC_OK)
				.body("status", is("ok"))
				.body("method", is("GET"))
				.extract().response();
	}
}
