package ru.korgov.webeltech.storage.model;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 17.03.12
 */
public class Publishing {
    private long id;
    private String name;

    public Publishing() {
    }

    public Publishing(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Publishing(final long id) {
        this.id = id;
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

    @SuppressWarnings({"RedundantIfStatement", "ControlFlowStatementWithoutBraces", "NonFinalFieldReferenceInEquals"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Publishing that = (Publishing) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @SuppressWarnings({"NonFinalFieldReferencedInHashCode", "NumericCastThatLosesPrecision"})
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Publishing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
