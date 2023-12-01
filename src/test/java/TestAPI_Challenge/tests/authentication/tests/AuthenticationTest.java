package TestAPI_Challenge.tests.authentication.tests;

import TestAPI_Challenge.tests.authentication.requests.AuthenticationRequest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException; // Importe a exceção IOException

public class AuthenticationTest extends AuthenticationRequest {

	@Test
	@Tag("all")
	@Description("Validar uma autenticação e validar o retorno do json")
	public void validateAuthentication() throws IOException {
		authenticateAndStoreToken();

	}

	@Test
	@Tag("all")
	@Description("Pesquisar um produto já com token autenticado e faz uma previa validação de conteudo do produto")
	public void searchProductAuth() throws IOException {
		productSearchAuth();
	}

	@Test
	@Tag("all")
	@Description("Pesquisar um produto já com token inválido")
	public void searchProductAuthTokenInvalid() {

		generateInvalidToken();
	}

	@Test
	@Tag("all")
	@Description("Pesquisar um produto já com token não autorizado")
	public void searchProductAuthTokenNotValid() {

		simulateUnauthorizedError();
	}
}
