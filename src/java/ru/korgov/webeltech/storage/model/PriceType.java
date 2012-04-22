package ru.korgov.webeltech.storage.model;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 17.03.12
 */
public class PriceType {
    private long id;
    private String name;

    public PriceType() {
    }

    public PriceType(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PriceType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
