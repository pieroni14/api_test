package TestAPI_Challenge.tests.products.tests;

import TestAPI_Challenge.tests.products.requests.ProductsRequest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ProductsTest extends ProductsRequest {

	@Test
	@Tag("all")
	@Description("Validar o limite da lista que é 30 id. Validando o primeiro e o ultimo produto")
	public void checkListProducts() throws Exception {
		listProducts();

	}
	@Test
	@Tag("all")
	@Description("Valida todos os produtos listado dentro de um for com 100 id e faz uma validação de algum produto com id invalido.")
	public void testLimit100Products()throws Exception{
		checkLimit100Products();
	}

	@Test
	@Tag("all")
	@Description("Validando criação de um produto")
	public void ValidationOfNewProduct () throws Exception{
		creatingProduct();
	}

}
