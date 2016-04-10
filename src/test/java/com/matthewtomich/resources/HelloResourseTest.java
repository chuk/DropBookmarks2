package com.matthewtomich.resources;

import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import io.dropwizard.testing.junit.ResourceTestRule;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.*;

/**
 * Created by matthewtomich on 29/11/2015.
 */
public class HelloResourseTest {

    @ClassRule
    public static final ResourceTestRule RULE =
            ResourceTestRule.builder().addResource(new HelloResourse()).build();

    @Test
    public void testGetGreeting() throws Exception {
        String expectedResult = "Hello World";
        String actual = RULE
                .getJerseyTest()
                .target("/hello")
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);
        assertEquals(expectedResult,actual);
    }
}