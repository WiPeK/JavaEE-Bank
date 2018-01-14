package pl.wipek.rest.api.timerstest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.accounts.bonuses.timers.MonthlyAccountBonusesTimer;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

@Path("/bonuses")
public class MonthlyAccountBonusesTest extends Application {
    private static final Logger logger = LoggerFactory.getLogger(MonthlyAccountBonusesTest.class);

    @EJB
    private MonthlyAccountBonusesTimer monthlyAccountBonusesTimer;

    @GET
    public Response testTimer() {
        monthlyAccountBonusesTimer.chargeMonthlyBonuses();
        return Response.ok(MonthlyAccountBonusesTimer.emailMessages.toString()).build();
    }
}
