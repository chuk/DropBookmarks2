package com.matthewtomich.db;

import com.google.common.base.Optional;
import com.matthewtomich.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by matthewtomich on 09/04/2016.
 */
public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<User> findAll() {
        return list(
                namedQuery("com.matthewtomich.core.User.findAll")
        );
    }

    public Optional<User> findByUsernamePassword (String username, String password) {
        return Optional.fromNullable(
                uniqueResult(
                        namedQuery("com.matthewtomich.core.User.findByUsernamePassword")
                        .setParameter("username", username)
                        .setParameter("password",password)
                )
        );
    }
}
