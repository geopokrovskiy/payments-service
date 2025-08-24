package com.geopokrovskiy.payments_service;

import com.geopokrovskiy.payments_service.config.TestDatabaseConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@Import(TestDatabaseConfiguration.class)
class PaymentsServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
