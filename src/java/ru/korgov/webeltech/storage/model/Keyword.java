package ru.korgov.webeltech.storage.model;

import ru.korgov.util.alias.Cf;

import java.util.List;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 18.03.12
 */
public class Keyword {
    private long id;
    private String value;

    public Keyword(final long id, final String value) {
        this.id = id;
        this.value = value;
    }

    public Keyword() {
    }

    public Keyword(final String value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }


    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @SuppressWarnings({"RedundantIfStatement", "ControlFlowStatementWithoutBraces", "NonFinalFieldReferenceInEquals"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Keyword keyword = (Keyword) o;

        if (id != keyword.id) return false;
        if (value != null ? !value.equals(keyword.value) : keyword.value != null) return false;

        return true;
    }

    @SuppressWarnings({"NonFinalFieldReferencedInHashCode", "NumericCastThatLosesPrecision"})
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Keyword{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }

    public static List<Keyword> createFromStrings(final List<String> keywords){
        final List<Keyword> out = Cf.newList();
        for (final String keyword : keywords) {
            out.add(new Keyword(keyword));
        }
        return out;
    }
}
