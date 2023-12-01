package TestAPI_Challenge.tests.users.tests;

import TestAPI_Challenge.tests.users.requests.UserRequest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UserTest extends UserRequest {

	@Test
	@Tag("all")
	@Description("Validar que os campos 'username' e 'password' não estão em branco na lista de todos os usuários")
	public void validateUsernamesAndPasswords() throws Exception {
		listUser();
	}

	@Test
	@Tag("all")
	@Description("Validar o limite de 30 usuários na lista e verificar o primeiro e o último nome na posição 01 e 30")
	public void validateUserListLimitAndNames() throws Exception {
		testLimit30User();
	}
}
