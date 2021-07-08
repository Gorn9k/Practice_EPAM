package org.com.controller;

import org.com.Application;
import org.com.entity.Ingot;
import org.com.security.AuthenticationManagerImpl;
import org.junit.*;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.SimpleDateFormat;
import java.util.List;

public class IngotControllerTest {

    private static IngotController ingotController;
    private static ConfigurableApplicationContext context;
    private static Ingot actual;
    private static String date;

    @BeforeClass
    public static void setUp() throws Exception {
        context = SpringApplication.run(Application.class);
        ingotController = context.getBean(IngotController.class);

        AuthenticationManager authenticationManager = context.getBean(AuthenticationManagerImpl.class);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("admin", "admin"));
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);

        actual = Ingot.builder()
                .Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-07-01 03:00:00"))
                .MetalID(0L)
                .Nominal(1.0)
                .NoCertificateDollars(null)
                .NoCertificateRubles(76.20)
                .CertificateDollars(null)
                .CertificateRubles(84.22)
                .BanksDollars(null)
                .BanksRubles(90.24)
                .EntitiesDollars(null)
                .EntitiesRubles(104.28)
                .build();
        date = "2016-07-01";
    }

    @AfterClass
    public static void tearDown() throws Exception {
        context.close();
    }

    @Test
    public void getAllIngotsOnMetalsByDate() {
        List<Ingot> expected = ingotController.getAllIngotsOnMetalsByDate(date);

        int actualSize = 24;

        Assert.assertNotNull(expected);

        Assert.assertEquals(expected.size(), actualSize);

        Assert.assertEquals(expected.get(0), actual);
    }

    @Test
    public void getIngotsOnMetalsByIdAndDate() {
        List<Ingot> expected = ingotController.getIngotsOnMetalsByIdAndDate(0L, date);

        int actualSize = 9;

        Assert.assertNotNull(expected);

        Assert.assertEquals(expected.size(), actualSize);

        Assert.assertEquals(expected.get(0), actual);
    }
}