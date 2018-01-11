package pl.wipek.accounts.bonuses.families.voucher;

import pl.wipek.shared.domain.entity.Account;
import pl.wipek.shared.domain.entity.account.bonuses.ActualVoucher;
import pl.wipek.shared.domain.entity.account.bonuses.GrantedVoucher;

import java.util.Random;

public class BusinessVoucherBonus extends VoucherBonus {
    private int premiumVouchersForZusTransfers = 0;

    public BusinessVoucherBonus(Account account) {
        super(account);
    }

    @Override
    public void grandVouchers(Double saldo) {
        if (saldo > MIN_SALDO_FOR_BUSINESS_ACCOUNT) {
            Random random = new Random();
            for (int i = 0; i < MAX_VOUCHERS_FOR_BUSINESS_ACCOUNT; i++) {
                ActualVoucher actualVoucher = this.actualVouchers.stream().skip(random.nextInt() % this.actualVouchers.size()).findFirst().get();
                for (int j = 0; j < premiumVouchersForZusTransfers; j++) {
                    GrantedVoucher voucher = new GrantedVoucher();
                    voucher.setCode(generateVoucherCode(actualVoucher));
                    voucher.setAccount(account);
                    grantedVouchers.add(voucher);
                }
            }
        } else {
            isGranted = false;
        }
    }

    public void setPremiumVouchersForZusTransfers(int premiumVouchersForZusTransfers) {
        this.premiumVouchersForZusTransfers = premiumVouchersForZusTransfers;
    }
}
