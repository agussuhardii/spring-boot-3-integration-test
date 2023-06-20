package com.agussuhardi.springboot3integrationtest.controller;

import com.agussuhardi.springboot3integrationtest.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * @author agussuhardi
 * @created 20/06/23/06/2023 :20.25
 * @project spring-boot-3-integration-test
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                statements = {
                        "delete from product where product.id IS NOT NULL;",
                        "insert into product(id, name, qty)values ('1ad1fccc-d279-46a0-8980-1d91afd6ba67', 'HDD 2GB', 3)"
                }),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                statements = {
                        "delete from product where product.id IS NOT NULL;"
                })
})
class ProductControllerTest {

    private final HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;
    private final String HOST = "http://localhost:";
    private final String productId = "1ad1fccc-d279-46a0-8980-1d91afd6ba67";

    @Test
    void saveSuccess() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        var form = new Product();
        form.setId(UUID.randomUUID().toString());
        form.setName("Laptop Acer");
        form.setQty(10);

        var request = new RequestEntity<>(form, headers, HttpMethod.POST, URI.create(HOST + port + "/products"));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void saveBadRequestInput() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        var form = new Product();
        form.setId(UUID.randomUUID().toString());
        form.setQty(10);

        var request = new RequestEntity<>(form, headers, HttpMethod.POST, URI.create(HOST + port + "/products"));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    public void deleteSuccess() {
        var request = new RequestEntity<>(headers, HttpMethod.DELETE, URI.create(HOST + port + "/products/" + productId));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateSuccess() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        var form = new Product();
        form.setId(productId);
        form.setName("Macbook");
        form.setQty(33);

        var request = new RequestEntity<>(form, headers, HttpMethod.PUT, URI.create(HOST + port + "/products"));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getByIdSuccess() {
        var request = new RequestEntity<>(headers, HttpMethod.GET, URI.create(HOST + port + "/products/" + productId));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void querySuccess() {
        var request = new RequestEntity<>(null, headers, HttpMethod.GET, URI.create(HOST + port + "/products"));
        var response = restTemplate.exchange(request, Object.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}