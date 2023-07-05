package co.za.lotto.machine.lotto;

import com.google.common.collect.*;

import java.math.BigDecimal;
import java.util.List;

public class QuickFiveTicket implements LottoTicket {
    private Lotto lotto;
    private List<List<Integer>> selections;

    public QuickFiveTicket(Lotto lotto, List<List<Integer>> selections) {
        this.lotto = lotto;
        this.selections = selections;
    }

    @Override
    public BigDecimal calculateWinnings(List<Integer> winningNumbers) {
        ImmutableMultiset<Integer> numberMultiset = ImmutableMultiset.copyOf(winningNumbers);
        int totalMatchedNumbers = Multisets.copyHighestCountFirst(numberMultiset).entrySet().stream()
                .filter(entry -> entry.getCount() > 1)
                .mapToInt(Multiset.Entry::getCount)
                .sum();

        int totalWinnings = (int) Math.pow(10, totalMatchedNumbers);
        return BigDecimal.valueOf(totalWinnings);
    }

    @Override
    public String printTicket() {
        StringBuilder ticket = new StringBuilder("Quick Five Lotto Ticket\n");
        int rowNumber = 1;
        for (List<Integer> row : selections) {
            ticket.append("Row ").append(rowNumber++).append(": ").append(row).append("\n");
        }
        return ticket.toString();
    }
}
