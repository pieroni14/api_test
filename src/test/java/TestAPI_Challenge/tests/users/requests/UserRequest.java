package TestAPI_Challenge.tests.users.requests;

import TestAPI_Challenge.setup.PageObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.when;

public class UserRequest {

	private static final int HTTP_STATUS_OK = HttpStatus.SC_OK;
	private final String usersUri = getUsersUri();

	/**
	 * Obtém a URI para a listagem de usuários.
	 */
	private String getUsersUri() {
		return PageObject.getUri(PageObject.PATH_USERS);
	}

	/**
	 * Testa a listagem de todos os usuários e valida se o username e password não estão em branco.
	 */
	@Step("Testando listagem de todos usuários e validando username e password se não estão em branco")
	public Response listUser() {
		return when()
				.get(usersUri)
				.then()
				.log().all()
				.statusCode(HTTP_STATUS_OK)
				.body("users.username", Matchers.notNullValue())
				.body("users.password", Matchers.notNullValue())
				.extract().response();
	}

	/**
	 * Testa o limite de 30 da lista de usuários e valida o primeiro e último nome na posição 01 e 30.
	 */
	@Step("Testando limite de 30 da lista de usuário e validando o primeiro e último nome da posição 01 e 30")
	public Response testLimit30User() {
		return when()
				.get(usersUri)
				.then()
				.log().all()
				.statusCode(HTTP_STATUS_OK)
				.body("users[0].firstName", Matchers.is("Terry"))
				.body("users[0].lastName", Matchers.is("Medhurst"))
				.body("users[29].firstName", Matchers.is("Maurine"))
				.body("users[29].lastName", Matchers.is("Stracke"))
				.extract().response();
	}
}
