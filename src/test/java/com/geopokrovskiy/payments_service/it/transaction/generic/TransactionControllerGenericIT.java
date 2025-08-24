package com.geopokrovskiy.payments_service.it.transaction.generic;

import com.geopokrovskiy.payments_service.config.TestDatabaseConfiguration;
import com.geopokrovskiy.payments_service.utils.Constants;
import com.geopokrovskiy.payments_service.utils.FakeProviderTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Import(TestDatabaseConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
public class TransactionControllerGenericIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenPaymentProviderDoesNotExist_whenCreateTransaction_then404() throws Exception {
        // Test
        mockMvc.perform(post("/api/v1/transaction/create?paymentMethodId=999999999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(FakeProviderTestData.getCreateFakeProviderTopUpRequestBodyWithoutPassword()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$.http_status_code").value(Constants.NOT_FOUND_404));
    }
}
