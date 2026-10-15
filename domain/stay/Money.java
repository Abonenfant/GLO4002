package ca.ulaval.glo4002.inn.domain.stay;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money {
    public static final Money ZERO = ofPieces(0);
    private static final int CENTS_DECIMAL_PLACES = 2;

    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount.setScale(CENTS_DECIMAL_PLACES, RoundingMode.HALF_EVEN);
    }

    public static Money ofPieces(int pieces) {
        return new Money(BigDecimal.valueOf(pieces));
    }

    public Money add(Money other) {
        return new Money(amount.add(other.amount));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Money money)) {
            return false;
        }
        return amount.equals(money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return amount.toPlainString();
    }
}
