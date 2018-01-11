package pl.wipek.accounts.bonuses.families.voucher;

import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.account.bonuses.ActualVoucher;
import pl.wipek.shared.domain.entity.account.bonuses.GrantedVoucher;

import java.math.BigDecimal;
import java.util.Random;

public class StandardVoucherBonus extends VoucherBonus {
    public StandardVoucherBonus(Account account) {
        super(account);
    }

    @Override
    public void grandVouchers(Double saldo) {
        if (saldo >= MIN_SALDO_FOR_STANDARD_ACCOUNT) {
            Random random = new Random();
            for (int i = 0; i < MAX_VOUCHERS_FOR_STANDARD_ACCOUNT; i++) {
                ActualVoucher actualVoucher = this.actualVouchers.stream().skip(random.nextInt() % this.actualVouchers.size()).findFirst().get();
                GrantedVoucher voucher = new GrantedVoucher();
                voucher.setCode(generateVoucherCode(actualVoucher));
                voucher.setAccount(account);
                grantedVouchers.add(voucher);
            }
        } else {
            isGranted = false;
        }
    }
}
