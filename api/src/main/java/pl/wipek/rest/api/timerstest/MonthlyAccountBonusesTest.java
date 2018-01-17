package pl.wipek.rest.api.timerstest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.accounts.bonuses.families.BonusContainer;
import pl.wipek.accounts.bonuses.timers.MonthlyAccountBonusesTimer;
import pl.wipek.shared.util.converter.JsonSerializer;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.util.Set;

@Path("/accounts")
public class MonthlyAccountBonusesTest extends Application {
    private static final Logger logger = LoggerFactory.getLogger(MonthlyAccountBonusesTest.class);

    @EJB
    private MonthlyAccountBonusesTimer monthlyAccountBonusesTimer;

    @GET
    @Path("/bonuses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response testTimer() throws JAXBException {
        Set<BonusContainer> bonusContainers = monthlyAccountBonusesTimer.chargeMonthlyBonuses();
        logger.info("Generated containers:" + bonusContainers.toString());
        String resultJson = JsonSerializer.convertSet(bonusContainers, BonusContainer.class);
        return Response.ok(resultJson).build();
    }
}
