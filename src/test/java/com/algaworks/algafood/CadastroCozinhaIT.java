package com.algaworks.algafood;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());//200
	}
	
	@Test
	public void deveConter4Cozinhas_QuandoConsultarCozinhas() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(4))
			.body("nome", Matchers.hasItems("Indiana", "Tailandesa"));
	}
	
	@Test
	public void testRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
			.body("{ \"nome\": \"Chinesa\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
		
	}
	
//	@Test
//	public void deveConter6Restaurantes_QuandoConsultarRestaurantes() {
//		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//		
//		RestAssured.given()
//			.basePath("/restaurantes")
//			.accept(ContentType.JSON)
//		.when()
//			.get()
//		.then()
//			.body("", Matchers.hasSize(6))
//			.body("taxaFrete", Matchers.greaterThanOrEqualTo(0))
//			;
//	}
	
//	@Test
//	public void deveConter6Restaurantes_QuandoConsultarRestaurantes() {
//		Restaurante[] restaurantes = RestAssured.given()
//				.basePath("/restaurantes")
//				.get()
//			.then()
//				.statusCode(200)
//				.extract()
//				.as(Restaurante[].class);
//			
//		assertThat(restaurantes.length).isEqualTo(6);
//	}
	
}
