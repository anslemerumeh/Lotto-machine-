package co.za.lotto.machine.wallet;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Wallet {
    private BigDecimal funds = BigDecimal.ZERO;
    private static final Set<BigDecimal> VALID_AMOUNTS;

    static {
        VALID_AMOUNTS = new HashSet<>();
        VALID_AMOUNTS.add(new BigDecimal("1"));
        VALID_AMOUNTS.add(new BigDecimal("2"));
        VALID_AMOUNTS.add(new BigDecimal("5"));
        VALID_AMOUNTS.add(new BigDecimal("10"));
        VALID_AMOUNTS.add(new BigDecimal("50"));
        VALID_AMOUNTS.add(new BigDecimal("100"));
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public void addFunds(BigDecimal amount) throws InvalidAmountException {
        if (!VALID_AMOUNTS.contains(amount)) {
            throw new InvalidAmountException("Invalid amount: " + amount);
        }
        funds = funds.add(amount);
    }
}
