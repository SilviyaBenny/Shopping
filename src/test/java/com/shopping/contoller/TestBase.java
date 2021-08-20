package com.shopping.contoller;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.shopping.ShoppingApplication;
import com.shopping.config.DBConfiguration;

@SpringBootTest(classes = { ShoppingApplication.class, DBConfiguration.class })
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class TestBase {

}
