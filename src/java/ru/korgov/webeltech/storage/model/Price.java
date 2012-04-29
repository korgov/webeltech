package ru.korgov.webeltech.storage.model;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 17.03.12
 */
public class Price {
    private long id;
    private double value;
    private PriceType priceType;

    public Price() {
    }

    public Price(final long id, final double value, final PriceType priceType) {
        this.id = id;
        this.value = value;
        this.priceType = priceType;
    }

    public Price(final long id, final double value) {
        this(id, value, new PriceType(1L, "руб"));
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(final double value) {
        this.value = value;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(final PriceType priceType) {
        this.priceType = priceType;
    }

    @SuppressWarnings({"ControlFlowStatementWithoutBraces", "NonFinalFieldReferenceInEquals", "RedundantIfStatement"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Price price = (Price) o;

        if (id != price.id) return false;
        if (Double.compare(price.value, value) != 0) return false;
        if (priceType != null ? !priceType.equals(price.priceType) : price.priceType != null) return false;

        return true;
    }

    @SuppressWarnings({"ConditionalExpressionWithNegatedCondition", "NonFinalFieldReferencedInHashCode", "NumericCastThatLosesPrecision", "TooBroadScope", "UnaryPlus"})
    @Override
    public int hashCode() {
        int result;
        final long temp;
        result = (int) (id ^ (id >>> 32));
        temp = value != +0.0d ? Double.doubleToLongBits(value) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (priceType != null ? priceType.hashCode() : 0);
        return result;
    }

    @SuppressWarnings({"RedundantIfStatement", "ControlFlowStatementWithoutBraces", "FloatingPointEquality", "NonFinalFieldReferenceInEquals"})


    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", value=" + value +
                ", priceType=" + priceType +
                '}';
    }
}
