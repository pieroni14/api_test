package TestAPI_Challenge.tests.authentication.requests;

import TestAPI_Challenge.setup.PageObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class AuthenticationRequest extends PageObject {

	private String AUTH_TOKEN;

	// Função para ler as credenciais a partir de um arquivo JSON
	private JSONObject readCredentials() throws IOException {
		try (InputStream is = getClass().getResourceAsStream("/credentials.json");
			 InputStreamReader reader = new InputStreamReader(is)) {
			JSONParser parser = new JSONParser();
			return (JSONObject) parser.parse(reader);
		} catch (Exception e) {
			throw new IOException("Erro ao ler o arquivo de credenciais.", e);
		}
	}

	// Função para obter as credenciais
	private JSONObject getCredentials() throws IOException {
		return readCredentials();
	}

	// Função para obter o nome de usuário a partir das credenciais
	private String getUsername() throws IOException {
		JSONObject credentials = getCredentials();
		return (String) credentials.get("username");
	}

	// Função para obter a senha a partir das credenciais
	private String getPassword() throws IOException {
		JSONObject credentials = getCredentials();
		return (String) credentials.get("password");
	}

	// Função para autenticar e armazenar o token
	@Step("Realizar autenticação e armazenar o token e valida informações do retorno")
	public void authenticateAndStoreToken() throws IOException {
		String uri = getUri(LOGIN_URL);
		String username = getUsername();
		String password = getPassword();

		Response response = given()
				.log().all()
				.header("Content-Type", "application/json")
				.body("{\n" +
						"    \"username\": \"" + username + "\",\n" +
						"    \"password\": \"" + password + "\"\n" +
						"}")
				.when()
				.post(uri);

		response.then()
				.log().all()
				.statusCode(HttpStatus.SC_CREATED)
				.body("id", is(15))
				.body("token", is(notNullValue()))
				.body("username", is("kminchelle"))
				.body("email", is("kminchelle@qq.com"))
				.body("firstName", is("Jeanne"))
				.body("lastName", is("Halvorson"))
				.body("gender", is("female"))
				.body("image", is("https://robohash.org/autquiaut.png"));

		AUTH_TOKEN = response.then().extract().path("token");
	}

	// Função para obter o token armazenado
	public String getAuthToken() {
		return AUTH_TOKEN;
	}

	@Step("Executar uma ação usando o token armazenado")
	public void doSomethingWithToken() {
		System.out.println("Token: " + AUTH_TOKEN);
	}

	@Step("Pesquisar um produto autenticado")
	public Response productSearchAuth() throws IOException {
		// Certifique-se de que a autenticação seja realizada antes de usar o token
		if (AUTH_TOKEN == null) {
			authenticateAndStoreToken();
		}

		String uri = getUri(AUTH_PRODUCT);

		return given()
				.log().all()
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + AUTH_TOKEN) // Use "Bearer" para o token
				.when()
				.get(uri)
				.then()
				.log().all()
				.body("products[0].id", is(1)) // Corrigido o índice
				.body("products[0].title", is("iPhone 9,")) // Corrigido o índice
				.body("products[29].id", is(30))
				.body("products[29].title", is("Key Holder"))
				.extract().response();
	}

	@Step("Gerar um token inválido resultando em erro 401")
	public void generateInvalidToken() {
		String resourceUri = getUri(AUTH_PRODUCT);

		given()
				.log().all()
				.header("Authorization", "InvalidToken") // Token inválido
				.when()
				.get(resourceUri)
				.then()
				.log().all()
				.statusCode(HttpStatus.SC_UNAUTHORIZED);
	}

	@Step("Simular um erro 403")
	public void simulateUnauthorizedError() {
		String resourceUri = getUri(AUTH_PRODUCT);

		given()
				.log().all()
				.header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTU") // Token inválido
				.when()
				.get(resourceUri)
				.then()
				.log().all()
				.statusCode(HttpStatus.SC_FORBIDDEN);
	}

}
