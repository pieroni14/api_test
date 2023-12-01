package TestAPI_Challenge.tests.products.requests;

import TestAPI_Challenge.setup.PageObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is; // Importe o método is(int) corretamente

public class ProductsRequest extends PageObject {

	@Step("Testando listagem de produtos e validando o produto do array 0 e 29")
	public Response listProducts() {
		String uri = getUri(PRODUCTS_URL);

		return when()
				.get(uri)
				.then()
				.log().ifStatusCodeIsEqualTo(HttpStatus.SC_OK)
				.body("products[00].id", is(1))
				.body("products[00].title", is("iPhone 9"))
				.body("products[29].id", is(30))
				.body("products[29].title", is("Key Holder"))
				.extract().response();
	}

	@Step("Valida todos os produtos listados dentro de um loop com 100 id e faz uma validação de algum produto com id inválido")
	public void checkLimit100Products() {
		String uri = getUri(PRODUCTS_URL);

		for (int id = 1; id <= 100; id++) {
			String productUri = uri + "/" + id;

			Response response = when()
					.get(productUri)
					.then()
					.log().all()
					.statusCode(HttpStatus.SC_OK)
					.extract().response();
		}

		String invalidProductUri = uri + "/101";
		Response invalidResponse = when()
				.get(invalidProductUri)
				.then()
				.log().all()
				.statusCode(HttpStatus.SC_NOT_FOUND)
				.body("message", is("Product with id '101' not found"))
				.extract().response();
	}

	@Step("Testando a criação de um produto")
	public Response creatingProduct() {
		String uri = getUri(CREATING_PRODUCT);

		return given()
				.log().all()
				.header("Content-Type", "application/json")
				.body("{\n" +
						"    \"title\": \"Perfume Oil\",\n" +
						"    \"description\": \"Mega Discount, Impression of A...\",\n" +
						"    \"price\": 13,\n" +
						"    \"discountPercentage\": 8.4,\n" +
						"    \"rating\": 4.26,\n" +
						"    \"stock\": 65,\n" +
						"    \"brand\": \"Impression of Acqua Di Gio\",\n" +
						"    \"category\": \"fragrances\",\n" +
						"    \"thumbnail\": \"https://i.dummyjson.com/data/products/11/thumnail.jpg\"\n" +
						"}")
				.when()
				.post(uri)
				.then()
				.log().all()
				.statusCode(HttpStatus.SC_CREATED)
				.extract().response();
	}
}
