package ru.korgov.webeltech.storage.model;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 17.03.12
 */
public class Price {
    private long id;
    private int value;
    private PriceType priceType;

    public Price() {
    }

    public Price(final long id, final int value, final PriceType priceType) {
        this.id = id;
        this.value = value;
        this.priceType = priceType;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(final PriceType priceType) {
        this.priceType = priceType;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Price price = (Price) o;

        if (id != price.id) return false;
        if (value != price.value) return false;
        if (priceType != null ? !priceType.equals(price.priceType) : price.priceType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + value;
        result = 31 * result + (priceType != null ? priceType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", value=" + value +
                ", priceType=" + priceType +
                '}';
    }
}
