package co.za.lotto.machine.lotto;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.util.List;

public class SingleLottoTicket implements LottoTicket {
    private Lotto lotto;
    private List<Integer> selections;

    public SingleLottoTicket(Lotto lotto, List<Integer> selections) {
        this.lotto = lotto;
        this.selections = selections;
    }

    @Override
    public BigDecimal calculateWinnings(List<Integer> winningNumbers) {
        int matchedNumbers = Sets.intersection(Sets.newHashSet(selections), Sets.newHashSet(winningNumbers)).size();
        if (matchedNumbers > 2) {
            int winnings = (int) Math.pow(10, matchedNumbers - 2);
            return BigDecimal.valueOf(winnings);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public String printTicket() {
        return String.format("Single Lotto Ticket%nSelected Numbers: %s%n", selections);
    }
}
