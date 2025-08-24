package com.geopokrovskiy.payments_service.it.payment_provider;

import com.geopokrovskiy.payments_service.config.TestDatabaseConfiguration;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Import(TestDatabaseConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
public class PaymentProviderControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private final String TEST_PROVIDER_1 = "fake_provider";
    private final String TEST_PROVIDER_2 = "fake_provider2";

    @Test
    public void testGetAllPaymentProviders() throws Exception {
        mockMvc.perform(get("/api/v1/payment_provider/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value(TEST_PROVIDER_1))
                .andExpect(jsonPath("$[0].description").value(TEST_PROVIDER_1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value(TEST_PROVIDER_2))
                .andExpect(jsonPath("$[1].description").value(TEST_PROVIDER_2));
    }
}
