package com.matthewtomich.core;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.Objects;

/**
 * Created by matthewtomich on 10/04/2016.
 */
@Entity
@Table(name = "bookmarks")
@NamedQueries({
        @NamedQuery(name = "com.matthewtomich.core.Bookmark.findForUser",
                query = "select b from Bookmark b where b.user.id = :id"),
        @NamedQuery(name = "com.matthewtomich.core.Bookmark.remove",
                query = "delete Bookmark b where b.id = :id")
})
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String url;
    private String description;


    public Bookmark() {
    }

    public Bookmark(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects  .hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.url);
        hash = 97 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bookmark other = (Bookmark) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!java.util.Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        /*if (!Objects.equals(this.user, other.user)) {
            return false;
        }     */
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}