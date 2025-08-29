package com.geopokrovskiy.payments_service.it.transaction.fake_provider;

import com.geopokrovskiy.payments_service.config.TestDatabaseConfiguration;
import com.geopokrovskiy.payments_service.utils.Constants;
import com.geopokrovskiy.payments_service.utils.FakeProviderTestData;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.mockserver.model.JsonBody.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Import(TestDatabaseConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
public class TransactionControllerFakeProviderTopUpIT {

    @LocalServerPort
    private Integer port;

    static MockServerClient mockServerClient;

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        mockServerClient =
                new MockServerClient(
                        mockServerContainer.getHost(),
                        mockServerContainer.getServerPort()
                );
        registry.add("providers.fake-provider.methods.top-up.provider-url.host", mockServerContainer::getHost);
        registry.add("providers.fake-provider.methods.top-up.provider-url.port", mockServerContainer::getServerPort);
    }

    @Container
    static MockServerContainer mockServerContainer = new MockServerContainer(
            DockerImageName.parse("mockserver/mockserver:5.15.0")
    );

    @Autowired
    private MockMvc mockMvc;

    @Value("${providers.fake-provider.methods.top-up.provider-url.uri}")
    private String PROVIDER_URL_URI;


    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        mockServerClient.reset();
    }

    @Test
    public void givenAllOk_whenTopUpRequest_then201() throws Exception {
        // Set up expectations

        mockServerClient
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath(PROVIDER_URL_URI)
                                .withHeader("Content-Type", "application/json")
                                .withHeader("Authorization")
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody(json(FakeProviderTestData.getFakeProviderTopUpResponseBody200()))
                                .withHeader("Content-Type", "application/json")
                );


        // Test
        mockMvc.perform(post("/api/v1/transaction?paymentMethodId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(FakeProviderTestData.getCreateFakeProviderTopUpRequestBodyCorrect()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$.id").value(Constants.CREATED_TOP_UP_FAKE_PROVIDER_TRANSACTION_UUID))
                .andExpect(jsonPath("$.transaction_status").value(Constants.TRANSACTION_STATUS_IN_PROGRESS));
    }

    @Test
    public void givenIncorrectPasswordProvided_whenTopUpRequest_then401() throws Exception {
        // Set up expectations

        mockServerClient
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath(PROVIDER_URL_URI)
                                .withHeader("Content-Type", "application/json")
                                .withHeader("Authorization")
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(401)
                );

        // Test
        mockMvc.perform(post("/api/v1/transaction?paymentMethodId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(FakeProviderTestData.getCreateFakeProviderTopUpRequestBodyCorrect()))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$.http_status_code").value(Constants.UNAUTHORIZED_401));

    }

    @Test
    public void givenServiceUnavailable_whenTopUpRequest_then503() throws Exception {
        // Set up expectations

        mockServerClient
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath(PROVIDER_URL_URI)
                                .withHeader("Content-Type", "application/json")
                                .withHeader("Authorization")
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(503)
                );

        // Test
        mockMvc.perform(post("/api/v1/transaction?paymentMethodId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(FakeProviderTestData.getCreateFakeProviderTopUpRequestBodyCorrect()))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$.http_status_code").value(Constants.SERVICE_UNAVAILABLE_503));

    }

    @Test
    public void givenNoRequiredFieldProvided_whenTopUpRequest_then422() throws Exception {

        // Test
        mockMvc.perform(post("/api/v1/transaction?paymentMethodId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(FakeProviderTestData.getCreateFakeProviderTopUpRequestBodyWithoutPassword()))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$.http_status_code").value(Constants.UNPROCESSABLE_ENTITY_422));
    }

    @Test
    public void givenIncorrectRequiredFieldFilled_whenTopUpRequest_then422() throws Exception {

        // Test
        mockMvc.perform(post("/api/v1/transaction?paymentMethodId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(FakeProviderTestData.getCreateFakeProviderTopUpRequestBodyWithoutIncorrectField()))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$.http_status_code").value(Constants.UNPROCESSABLE_ENTITY_422));
    }
}
