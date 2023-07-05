package co.za.lotto.machine.lotto;

import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RandomLottoTicket implements LottoTicket {
    private Lotto lotto;
    private List<Integer> selections;

    public RandomLottoTicket(Lotto lotto) {
        this.lotto = lotto;
        this.selections = generateRandomSelections();
    }

    private List<Integer> generateRandomSelections() {
        List<Integer> randomSelections = new ArrayList<>();
        for (int i = 0; i < lotto.getBallsToDraw(); i++) {
            int randomNumber = (int) (Math.random() * lotto.getNumberOfBalls()) + 1;
            randomSelections.add(randomNumber);
        }
        return randomSelections;
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
        return String.format("Random Lotto Ticket%nSelected Numbers: %s%n", selections);
    }
}
