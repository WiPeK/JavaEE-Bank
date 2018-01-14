package pl.wipek.accounts.bonuses.families.voucher;

import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.account.bonuses.ActualVoucher;
import pl.wipek.shared.domain.entity.account.bonuses.GrantedVoucher;

import java.math.BigDecimal;
import java.util.Random;

public class GoldVoucherBonus extends VoucherBonus {
    private int numbersOfPremiumVouchers = 0;

    public GoldVoucherBonus(Account account) {
        super(account);
    }

    @Override
    public void grandVouchers(Double saldo) {
        if (saldo > MIN_SALDO_FOR_GOLD_ACCOUNT) {
            Random random = new Random();
            for (int i = 0; i < MAX_VOUCHERS_FOR_GOLD_ACCOUNT + numbersOfPremiumVouchers; i++) {
                ActualVoucher actualVoucher = this.actualVouchers.stream().skip((random.nextInt() & Integer.MAX_VALUE) % this.actualVouchers.size()).findFirst().get();
                GrantedVoucher voucher = new GrantedVoucher();
                voucher.setActualVoucher(actualVoucher);
                voucher.setAccount(account);
                voucher.setCode(generateVoucherCode(actualVoucher));
                grantedVouchers.add(voucher);
            }
        } else {
            isGranted = false;
        }
    }

    public void createPremiumVouchers(Double saldo) {
        if (saldo > 5000) {
            numbersOfPremiumVouchers += 2;
        }
    }
}
