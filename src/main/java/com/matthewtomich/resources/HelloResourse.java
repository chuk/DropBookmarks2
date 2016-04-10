package com.matthewtomich.resources;

import com.matthewtomich.core.User;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by matthewtomich on 28/11/2015.
 */
@Path("/hello")
public class HelloResourse {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting() {
        return "Hello World";
    }

    @GET
    @UnitOfWork
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/secured")
    public String getGreetingSecured(@Auth User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return "Hello Secured World";
    }
}
