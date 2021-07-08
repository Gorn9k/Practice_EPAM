package org.com.controller;

import org.com.Application;
import org.com.entity.Metal;
import org.com.security.AuthenticationManagerImpl;
import org.junit.*;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class MetalControllerTest {

    private static Metal metal0;
    private static Metal metal1;
    private static Metal metal2;
    private static Metal metal3;
    private static MetalController metalController;
    private static ConfigurableApplicationContext context;

    @BeforeClass
    public static void setUp() throws Exception {
        context = SpringApplication.run(Application.class);
        metalController = context.getBean(MetalController.class);

        AuthenticationManager authenticationManager = context.getBean(AuthenticationManagerImpl.class);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("user", "user"));
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);

        metal0 = Metal.builder()
                .Id(0L)
                .Name("Золото")
                .NameBel("Золата")
                .NameEng("Gold")
                .build();
        metal1 = Metal.builder()
                .Id(1L)
                .Name("Серебро")
                .NameBel("Серабро")
                .NameEng("Silver")
                .build();
        metal2 = Metal.builder()
                .Id(2L)
                .Name("Платина")
                .NameBel("Плаціна")
                .NameEng("Platinum")
                .build();
        metal3 = Metal.builder()
                .Id(3L)
                .Name("Палладий")
                .NameBel("Паладый")
                .NameEng("Palladium")
                .build();
    }

    @Test
    public void getAllMetals() {
        List<Metal> expected = metalController.getAllMetals();

        List<Metal> actual = new ArrayList<>();
        actual.add(metal0);
        actual.add(metal1);
        actual.add(metal2);
        actual.add(metal3);

        Assert.assertNotNull(expected);

        Assert.assertEquals(expected.size(), actual.size());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMetalById() {
        Metal expected = metalController.getMetalById(0L);

        Metal actual = metal0;

        Assert.assertEquals(expected, actual);
    }

    @AfterClass
    public static void tearDown() throws Exception{
        context.close();
    }
}