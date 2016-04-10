package com.matthewtomich.resources;

import com.matthewtomich.core.Bookmark;
import com.matthewtomich.core.User;
import com.matthewtomich.db.BookmarkDAO;
import com.matthewtomich.db.UserDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by matthewtomich on 10/04/2016.
 */
@Path("/bookmarks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookmarksResourse {

    private BookmarkDAO dao;

    private UserDAO userDAO;

    public BookmarksResourse(BookmarkDAO dao, UserDAO userDAO) {
        this.dao = dao;
        this.userDAO = userDAO;
    }

    @GET
    @UnitOfWork
    public List<Bookmark> getBookmarks(@Auth User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(userDAO.findByUsernamePassword(user.getUsername(),user.getPassword()));
        return dao.findForUser(user.getId());
    }

}
