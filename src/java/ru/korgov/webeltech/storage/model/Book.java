package ru.korgov.webeltech.storage.model;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

/**
 * @author Deepak Kumar
 *
 * Java Class to map to the datbase Contact Table
 */

/*
автор (выбор из списка);
название;
издательство;
год издания;
цена;
количество экземпляров;
время поступления;
ключевые слова.
 */

public class Book {
    private long id;
    private Author author;
    private Publishing publishing;
    private String name;
    private Date publishTime;
    private List<Price> price;
    private int count;
    private Date arrivalTime;
    private List<Keyword> keywords;

    public Book(final long id) {
        this.id = id;
    }

    public Book(final long id, final Author author, final Publishing publishing, final String name, final Date publishTime, final List<Price> price, final int count, final Date arrivalTime, final List<Keyword> keywords) {
        this.id = id;
        this.author = author;
        this.publishing = publishing;
        this.name = name;
        this.publishTime = publishTime;
        this.price = price;
        this.count = count;
        this.arrivalTime = arrivalTime;
        this.keywords = keywords;
    }

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(final Author author) {
        this.author = author;
    }

    public Publishing getPublishing() {
        return publishing;
    }

    public void setPublishing(final Publishing publishing) {
        this.publishing = publishing;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(final Date publishTime) {
        this.publishTime = publishTime;
    }

    public List<Price> getPrice() {
        return Collections.unmodifiableList(price);
    }

    public void setPrice(final List<Price> price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(final Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<Keyword> getKeywords() {
        return Collections.unmodifiableList(keywords);
    }

    public void setKeywords(final List<Keyword> keywords) {
        this.keywords = keywords;
    }

    @SuppressWarnings({"RedundantIfStatement", "NonFinalFieldReferenceInEquals", "ControlFlowStatementWithoutBraces", "OverlyComplexMethod"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Book book = (Book) o;

        if (count != book.count) return false;
        if (id != book.id) return false;
        if (arrivalTime != null ? !arrivalTime.equals(book.arrivalTime) : book.arrivalTime != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (keywords != null ? !keywords.equals(book.keywords) : book.keywords != null) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        if (publishTime != null ? !publishTime.equals(book.publishTime) : book.publishTime != null) return false;
        if (publishing != null ? !publishing.equals(book.publishing) : book.publishing != null) return false;

        return true;
    }

    @SuppressWarnings({"NonFinalFieldReferencedInHashCode", "NumericCastThatLosesPrecision"})
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publishing != null ? publishing.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + count;
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author=" + author +
                ", publishing=" + publishing +
                ", name='" + name + '\'' +
                ", publishTime=" + publishTime +
                ", price=" + price +
                ", count=" + count +
                ", arrivalTime=" + arrivalTime +
                ", keywords=" + keywords +
                '}';
    }
}