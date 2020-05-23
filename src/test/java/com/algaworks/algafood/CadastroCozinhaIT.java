package com.algaworks.algafood;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * @author israel
 * Cadastro de Cozinha Integration Test
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

	public static int COZINHA_ID_INEXISTENTE = 2000;

	private Cozinha cozinhaAmericana;
	private int quantidadeCozinhasCadastradas;
	private String jsonCorretoCozinhaChinesa;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource(
				"/json/correto/cozinha-chinesa.json");
		
		databaseCleaner.clearTables();
		prepararDados();
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
	public void deveRetornarQuantidadeCorretaDeCozinhas_QuandoConsultarCozinhas() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(quantidadeCozinhasCadastradas));
	}
	
	@Test
	public void testRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
			.body(jsonCorretoCozinhaChinesa)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
		
	}
	
	@Test
	public void deveRetornarRespostaEStatosCorretos_QuandoConsultarCozinhaExistente() {
		RestAssured.given()
			.pathParam("cozinhaId", cozinhaAmericana.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", Matchers.equalTo(cozinhaAmericana.getNome()));
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
		RestAssured.given()
			.pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarSTatus409_QuandoCozinhaEmUso() {
		RestAssured.given()
			.pathParam("cozinhaId", 2)
			.accept(ContentType.JSON)
		.when()
			.delete("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.CONFLICT.value());
		
	}
	
	private void prepararDados() {
		cozinhaAmericana = criarCozinha("Americana");
		ArrayList<Cozinha> cozinhas = new ArrayList<>();
		cozinhas.add(criarCozinha("Tailandesa"));
		cozinhas.add(cozinhaAmericana);
		cozinhas.forEach(cozinha ->	cozinhaRepository.save(cozinha));
		
		Restaurante restaurante = criarRestaurante("bbb", "12.5", cozinhaAmericana);
		restauranteRepository.save(restaurante);
		
		quantidadeCozinhasCadastradas = cozinhas.size();
	}
	
	private Cozinha criarCozinha(String nome) {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome(nome);
		return cozinha;
	}
	
	private Restaurante criarRestaurante(String nome, String taxaFrete,
			Cozinha cozinha) {
		
		LocalDateTime agora = LocalDateTime.now();
		
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(nome);
		restaurante.setTaxaFrete(new BigDecimal(taxaFrete));
		restaurante.setCozinha(cozinha);
		restaurante.setDataCadastro(agora);
		restaurante.setDataAtualizacao(agora);
		
		
		return restaurante;
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
