package co.za.lotto.machine;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import co.za.lotto.machine.lotto.Lotto;
import co.za.lotto.machine.lotto.LottoTicket;
import co.za.lotto.machine.wallet.Change;
import co.za.lotto.machine.wallet.InvalidAmountException;
import com.google.common.collect.ImmutableSet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class LottoMachineTest {
    private static Set<BigDecimal> ALLOWED_AMOUNTS = ImmutableSet.of(new BigDecimal("1"), new BigDecimal("2"),
            new BigDecimal("5"), new BigDecimal("10"),
            new BigDecimal("50"),
            new BigDecimal("100"));

    private static Set<BigDecimal> INVALID_AMOUNTS = ImmutableSet.of(new BigDecimal("3"), new BigDecimal("12"),
            new BigDecimal("2"), new BigDecimal("16"),
            new BigDecimal("75"),
            new BigDecimal("250"));

    @Test
    public void testAddFunds_valid() throws InvalidAmountException {
        final LottoMachine lottoMachine = new LottoMachine();
        for (BigDecimal allowedAmount : ALLOWED_AMOUNTS) {
            lottoMachine.addFunds(allowedAmount);
        }
        assertEquals(new BigDecimal("168"), lottoMachine.getBalance());
    }

    @Test(expected = InvalidAmountException.class)
    public void testAddFunds_invalid() throws InvalidAmountException {
        final LottoMachine lottoMachine = new LottoMachine();
        for (BigDecimal allowedAmount : INVALID_AMOUNTS) {
            lottoMachine.addFunds(allowedAmount);
        }
    }

    @Test
    public void testGetTickets_empty() {
        final LottoMachine lottoMachine = new LottoMachine();
        assertTrue(lottoMachine.getTickets().isEmpty());
    }

    @Test
    public void testPlaceSingleLottoBet() {
        final LottoMachine lottoMachine = new LottoMachine();
        Lotto lotto = new Lotto(49, 6);
        lottoMachine.placeSingleLottoBet(lotto, List.of(3, 7, 15, 21, 32, 42));
        assertEquals(1, lottoMachine.getTickets().size());
    }

    @Test
    public void testPlaceRandomLottoBet() {
        final LottoMachine lottoMachine = new LottoMachine();
        Lotto lotto = new Lotto(49, 6);
        lottoMachine.placeRandomLottoBet(lotto);
        assertEquals(1, lottoMachine.getTickets().size());
    }
    @Test
    public void testPlaceQuickFiveBet() {
        final LottoMachine lottoMachine = new LottoMachine();
        Lotto lotto = new Lotto(49, 6);
        lottoMachine.placeQuickFiveBet(lotto, List.of(
                List.of(3, 7, 15, 21, 32, 42),
                List.of(1, 8, 16, 24, 36, 45),
                List.of(10, 14, 19, 25, 38, 46),
                List.of(5, 11, 20, 27, 39, 48),
                List.of(2, 9, 17, 22, 33, 44)
        ));
        assertEquals(5, lottoMachine.getTickets().size());
    }

    @Test
    public void testPlaceRandomFiveBet() {
        final LottoMachine lottoMachine = new LottoMachine();
        Lotto lotto = new Lotto(49, 6);
        lottoMachine.placeRandomFiveBet(lotto);
        assertEquals(5, lottoMachine.getTickets().size());
    }


    @Test
    public void testCancelTicket() {
        final LottoMachine lottoMachine = new LottoMachine();
//        Lotto lotto = new Lotto(49, 6);
//        lottoMachine.placeSingleLottoBet(lotto, List.of(3, 7, 15, 21, 32, 42));
//        LottoTicket ticket = lottoMachine.getTickets().get(0);
//        lottoMachine.cancelTicket();
//        assertTrue(lottoMachine.getTickets().isEmpty());
//        assertEquals(BigDecimal.ZERO, lottoMachine.getBalance());
//        assertEquals(BigDecimal.ZERO, ticket.calculateWinnings(List.of(3, 7, 15, 21, 32, 42)));
    }

    @Test
    public void testWithdrawFunds() throws InvalidAmountException {
        final LottoMachine lottoMachine = new LottoMachine();
        lottoMachine.addFunds(new BigDecimal("100"));
        lottoMachine.addFunds(new BigDecimal("50"));
        lottoMachine.addFunds(new BigDecimal("10"));
        lottoMachine.addFunds(new BigDecimal("2"));
        assertEquals(new BigDecimal("162"), lottoMachine.getBalance());
        assertEquals(new Change(2, 1, 0, 0, 1, 1), lottoMachine.withdrawFunds());
        assertEquals(BigDecimal.ZERO, lottoMachine.getBalance());
    }
}

