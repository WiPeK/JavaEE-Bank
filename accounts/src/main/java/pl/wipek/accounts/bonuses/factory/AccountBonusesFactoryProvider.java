package pl.wipek.accounts.bonuses.factory;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.accounts.bonuses.exceptions.NoFactoryException;
import pl.wipek.shared.domain.entity.Account;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Stateless
public class AccountBonusesFactoryProvider {

    private static final Logger logger = LoggerFactory.getLogger(AccountBonusesFactoryProvider.class);

    @Inject
    private StandardBonusesFactory standardBonusesFactory;

    @Inject
    private GoldBonusesFactory goldBonusesFactory;

    @Inject
    private BusinessBonusesFactory businessBonusesFactory;

    public AccountBonusesFactory getFactory(@NotNull Account account) throws NoFactoryException {
        AccountBonusesFactory factory = null;
        switch(account.getType().toLowerCase()) {
            case Account.STANDARD_TYPE:
                factory = standardBonusesFactory;
                break;
            case Account.GOLD_TYPE:
                factory = goldBonusesFactory;
                break;
            case Account.BUSINESS_TYPE:
                factory = businessBonusesFactory;
                break;
            default:
                throw new NoFactoryException("Can not find factory for this account type");
        }
        return factory;
    }

    @Deprecated
    public static AccountBonusesFactory getFactoryByReflection(@NotNull Account account) throws NoFactoryException {
        final Set<Class<?>> classes = new HashSet<>();
        new FastClasspathScanner("pl.wipek.accounts.bonuses.factory")
                .matchAllClasses(classes::add).scan();

        AccountBonusesFactory factory = null;
        String accountType = account.getType().toLowerCase();
        logger.info(accountType);

        for (Class<?> clazz : classes) {
            logger.info(clazz.getSimpleName());
            if (clazz.getSimpleName().toLowerCase().contains(accountType)) {
                try {
                    factory = (AccountBonusesFactory)clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        if (factory == null) {
            throw new NoFactoryException("Can not find factory for this account type");
        }
        return factory;
    }
}
