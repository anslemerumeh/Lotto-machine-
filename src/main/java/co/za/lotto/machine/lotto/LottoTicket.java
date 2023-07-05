package co.za.lotto.machine.lotto;

import java.math.BigDecimal;
import java.util.List;

public interface LottoTicket {

    BigDecimal calculateWinnings(List<Integer> winningNumbers);
    String printTicket();

}
