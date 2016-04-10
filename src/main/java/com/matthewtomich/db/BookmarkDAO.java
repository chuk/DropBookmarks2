package com.matthewtomich.db;

import com.google.common.base.Optional;

import com.matthewtomich.core.Bookmark;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 * Created by matthewtomich on 10/04/2016.
 */
public class BookmarkDAO extends AbstractDAO<Bookmark> {

    public BookmarkDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Find all bookmarks for the user with specified id.
     *
     * @param id user id
     * @return list of bookmarks for the user with specified id.
     */
    public List<Bookmark> findForUser(long id) {
        System.out.println("user:" + id);
        return list(
                namedQuery("com.matthewtomich.core.Bookmark.findForUser")
                        .setParameter("id", id)
        );
    }

    /**
     * Finds a bookmark by its id.
     *
     * @param id id of a bookmark
     * @return a bookmark with specified id
     */
    public Optional<Bookmark> findById(long id) {
        return Optional.fromNullable(get(id));
    }

    /**
     * Create or Update a bookmark.
     *
     * @param bookmark a bookmark to be saved
     * @return the saved bookmark with all auto-generated fields filled.
     */
    public Bookmark save(Bookmark bookmark) {
        return persist(bookmark);
    }

    /**
     * Removes a bookmark from the database.
     *
     * @param bookmark a bookmark to be removed.
     */
    public void delete(Bookmark bookmark) {
        namedQuery("com.matthewtomich.core.Bookmark.remove")
                .setParameter("id",bookmark.getId()).executeUpdate();
    }
}