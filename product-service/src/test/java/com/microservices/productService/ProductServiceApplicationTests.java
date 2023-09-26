package com.microservices.productService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.productService.dto.ProductRequest;
import com.microservices.productService.repository.ProductRepository;
import com.mongodb.assertions.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0");
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;  //converts POJO objects to json
	@Autowired
	private ProductRepository productRepository;
	@DynamicPropertySource
	static void setProperties( DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
	}
	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest=getProductReqest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
	mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
			.contentType(MediaType.APPLICATION_JSON)
			.content(productRequestString))
			.andExpect(status().isCreated());
		Assertions.assertTrue(productRepository.findAll().size()==1);
	}

	private ProductRequest getProductReqest() {
		return ProductRequest.builder()
				.name("Iphone 13")
				.description("Iphone 13")
				.price(BigDecimal.valueOf(1200))
				.build();
	}
	//TODO : creating test for get product and for post

}
