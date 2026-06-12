package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;
import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {

    private final String title;
    private final String text;
    private final UUID id;

    public Article(UUID id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;

    }

    @Override
    public UUID getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm(){
        return title + " " + text;
    }

    @JsonIgnore
    @Override
    public String getContentType(){
        return "ARTICLE";
    }

    @Override
    public String getName(){
        return title;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title + "\n" + text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(getName(), article.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
