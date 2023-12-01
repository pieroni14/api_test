package TestAPI_Challenge.tests.status_api.tests;

import TestAPI_Challenge.tests.status_api.requests.StatusRequest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class StatusTest extends StatusRequest {

	@Test
	@Tag("all")
	@Description("Validar o status da API /TEST")
	public void validateStatusAPI() throws Exception {
		getStatus();
	}
}
