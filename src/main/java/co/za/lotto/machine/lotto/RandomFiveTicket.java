package co.za.lotto.machine.lotto;

import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RandomFiveTicket implements LottoTicket {
    private Lotto lotto;
    private List<List<Integer>> selections;

    public RandomFiveTicket(Lotto lotto) {
        this.lotto = lotto;
        this.selections = generateRandomSelections();
    }

    private List<List<Integer>> generateRandomSelections() {
        List<List<Integer>> randomSelections = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < lotto.getBallsToDraw(); j++) {
                int randomNumber = (int) (Math.random() * lotto.getNumberOfBalls()) + 1;
                row.add(randomNumber);
            }
            randomSelections.add(row);
        }
        return randomSelections;
    }

    @Override
    public BigDecimal calculateWinnings(List<Integer> winningNumbers) {
        int totalMatchedNumbers = 0;
        for (List<Integer> row : selections) {
            int matchedNumbers = Sets.intersection(Sets.newHashSet(row), Sets.newHashSet(winningNumbers)).size();
            totalMatchedNumbers += matchedNumbers;
        }
        if (totalMatchedNumbers > 2) {
            int winnings = (int) Math.pow(10, totalMatchedNumbers - 2);
            return BigDecimal.valueOf(winnings);
        }
        return BigDecimal.ZERO;
    }


    @Override
    public String printTicket() {
        StringBuilder ticket = new StringBuilder("Random Five Lotto Ticket\n");
        int rowNumber = 1;
        for (List<Integer> row : selections) {
            ticket.append("Row ").append(rowNumber++).append(": ").append(row).append("\n");
        }
        return ticket.toString();
    }
}
