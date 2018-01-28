package pl.wipek.rest.api.timerstest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.accounts.bonuses.families.BonusContainer;
import pl.wipek.accounts.bonuses.timers.MonthlyAccountBonusesTimer;

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
        //String resultJson = JsonSerializer.convertSet(bonusContainers, BonusContainer.class);
        String resultJson = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            resultJson = mapper.writeValueAsString(bonusContainers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Response.ok(resultJson).build();
    }
}
