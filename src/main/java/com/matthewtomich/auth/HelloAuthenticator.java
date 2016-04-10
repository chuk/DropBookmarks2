package com.matthewtomich.auth;

import com.google.common.base.Optional;
import com.matthewtomich.core.User;
import com.matthewtomich.db.UserDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

/**
 * Created by matthewtomich on 29/11/2015.
 */
public class HelloAuthenticator implements Authenticator<BasicCredentials,User> {

    private String password;

    private UserDAO dao;

    public HelloAuthenticator(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {

        Optional<User> user = dao.findByUsernamePassword(basicCredentials.getUsername(),basicCredentials.getPassword());

        if(user.get().getUsername().equals(basicCredentials.getUsername())
                && user.get().getPassword().equals(basicCredentials.getPassword())) {
            User authUser = new User(
                    basicCredentials.getUsername(),
                    basicCredentials.getPassword());

            authUser.setId(user.get().getId());

            return Optional.of(authUser);
        } else {
            return Optional.absent();
        }
    }

}
