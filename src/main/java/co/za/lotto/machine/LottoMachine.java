package co.za.lotto.machine;

import co.za.lotto.machine.lotto.*;
import co.za.lotto.machine.wallet.Change;
import co.za.lotto.machine.wallet.InvalidAmountException;
import co.za.lotto.machine.wallet.Wallet;
import java.math.BigDecimal;
import java.util.List;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoMachine {
    private final Wallet wallet = new Wallet();
    private final List<LottoTicket> tickets = new ArrayList<>();

    public void addFunds(BigDecimal amount) throws InvalidAmountException {
        wallet.addFunds(amount);
    }

    public void placeSingleLottoBet(Lotto lotto, List<Integer> selections) {
        LottoTicket ticket = new SingleLottoTicket(lotto, selections);
        tickets.add(ticket);
    }

    public void placeRandomLottoBet(Lotto lotto) {
        LottoTicket ticket = new RandomLottoTicket(lotto);
        tickets.add(ticket);
    }

    public void placeQuickFiveBet(Lotto lotto, List<List<Integer>> selections) {
        for (List<Integer> selection : selections) {
            placeSingleLottoBet(lotto, selection);
        }
    }


    public void placeRandomFiveBet(Lotto lotto) {
        Random random = new Random();
        int minNumber = 1;
        int maxNumber = lotto.getNumberOfBalls();
        int numbersToDraw = lotto.getNumbersToDraw();

        for (int i = 0; i < 5; i++) {
            List<Integer> randomSelection = new ArrayList<>();
            while (randomSelection.size() < numbersToDraw) {
                int randomNumber = random.nextInt(maxNumber - minNumber + 1) + minNumber;
                if (!randomSelection.contains(randomNumber)) {
                    randomSelection.add(randomNumber);
                }
            }
            placeSingleLottoBet(lotto, randomSelection);
        }
    }


    public void cancelTicket() throws InvalidAmountException {
//        List<LottoTicket> tickets = getTickets();
//        if (!tickets.isEmpty()) {
//            LottoTicket canceledTicket = tickets.remove(tickets.size() - 1);
//            BigDecimal ticketPrice = canceledTicket.getPrice();
//            wallet.addFunds(ticketPrice.negate());
//        }
    }


    public String printTicket() {
        if (!tickets.isEmpty()) {
            LottoTicket lastTicket = tickets.get(tickets.size() - 1);
            return lastTicket.printTicket();
        }
        return "";
    }

    public void resultTicket(List<Integer> numbers) throws InvalidAmountException {
        if (!tickets.isEmpty()) {
            LottoTicket lastTicket = tickets.get(tickets.size() - 1);
            BigDecimal winnings = lastTicket.calculateWinnings(numbers);
            wallet.addFunds(winnings);
        }
    }

    public BigDecimal getBalance() {
        return wallet.getFunds();
    }

    public List<LottoTicket> getTickets() {
        return new ArrayList<>(tickets);
    }

    public List<Change> withdrawFunds() throws InvalidAmountException {
        BigDecimal balance = wallet.getFunds();
        List<Change> changes = calculateChange(balance);
        wallet.addFunds(balance.negate()); // Deduct the balance from the wallet
        return changes;
    }

    private List<Change> calculateChange(BigDecimal amount) {
        int r100Notes = amount.intValue() / 100;
        amount = amount.remainder(BigDecimal.valueOf(100));
        int r50Notes = amount.intValue() / 50;
        amount = amount.remainder(BigDecimal.valueOf(50));
        int r10Notes = amount.intValue() / 10;
        amount = amount.remainder(BigDecimal.valueOf(10));
        int r5Coins = amount.intValue() / 5;
        amount = amount.remainder(BigDecimal.valueOf(5));
        int r2Coins = amount.intValue() / 2;
        amount = amount.remainder(BigDecimal.valueOf(2));
        int r1Coins = amount.intValue();
        List<Change> changes = new ArrayList<>();
        changes.add(new Change(r1Coins, r2Coins, r5Coins, r10Notes, r50Notes, r100Notes));
        return changes;
    }
}
