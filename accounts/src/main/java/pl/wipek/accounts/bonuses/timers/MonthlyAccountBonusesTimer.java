package pl.wipek.accounts.bonuses.timers;

import javax.ejb.Schedule;
import javax.ejb.Schedules;

public class MonthlyAccountBonusesTimer {

    @Schedules({
            @Schedule(dayOfMonth = "1")
    })
    public void chargeMonthlyBenefits() {

    }
}
