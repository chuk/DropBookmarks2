package com.matthewtomich;

import com.matthewtomich.auth.HelloAuthenticator;
import com.matthewtomich.core.Bookmark;
import com.matthewtomich.core.User;
import com.matthewtomich.db.BookmarkDAO;
import com.matthewtomich.db.UserDAO;
import com.matthewtomich.resources.BookmarksResourse;
import com.matthewtomich.resources.HelloResourse;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropBookmarksApplication extends Application<DropBookmarksConfiguration> {

    private final HibernateBundle<DropBookmarksConfiguration> hibernateBundle
            = new HibernateBundle<DropBookmarksConfiguration>(User.class, Bookmark.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DropBookmarksConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new DropBookmarksApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropBookmarks";
    }

    @Override
    public void initialize(final Bootstrap<DropBookmarksConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final DropBookmarksConfiguration configuration,
                    final Environment environment) {

        final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());
        final BookmarkDAO bookmarkDAO = new BookmarkDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(
                new HelloResourse()
        );

        environment.jersey().register(
                new BookmarksResourse(bookmarkDAO, userDAO)
        );

        environment.jersey().register(
                AuthFactory.binder(
                        new BasicAuthFactory<>(
                                new HelloAuthenticator(userDAO),
                                "SECURITY REALM",
                                User.class
                        )
                )
        );
    }
}
