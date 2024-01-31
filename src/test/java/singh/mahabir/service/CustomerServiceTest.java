package singh.mahabir.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @Test
    void testCustomerId()
    {
        CustomerService customerService = new CustomerService(new RestTemplateBuilder().build());
        Assertions.assertEquals("Customer id 631", customerService.getCustomerId("631"));
    }
}
