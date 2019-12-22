package com.example.demo.names;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class NameControllerTest {

    private final String baseURL = "http://localhost:8080/api";
    private final String getCustomerEndpoint = baseURL + "/{id}";
    private final String postCustomerEndpoint = baseURL;

    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = new TestRestTemplate();
    }

    // One sample text that calls the POST endpoint and then the GET endpoint
    @Test
    void createNameWithPostThenGetNameWithId() {
        SubmitNameDto postBody = SubmitNameDto.valueOf("Hayden Meloche");

        ResponseEntity<Name> postResponse = restTemplate.postForEntity(postCustomerEndpoint, postBody, Name.class);
        final Name postResponseBody = postResponse.getBody();

        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(postResponseBody.getId()).isNotNull();
        assertThat(postResponseBody.getName()).isEqualTo(postBody.getName());
        assertThat(postResponse.getHeaders().getLocation().toString()).isEqualTo(baseURL + "/" + postResponseBody.getId());


        ResponseEntity<Name> getResponse = restTemplate.getForEntity(
                UriComponentsBuilder.fromUriString(getCustomerEndpoint).build(postResponseBody.getId()).toString(),
                Name.class
        );

        final Name getResponseBody = getResponse.getBody();

        assertThat(getResponseBody).isEqualToComparingFieldByField(postResponseBody);
    }
}