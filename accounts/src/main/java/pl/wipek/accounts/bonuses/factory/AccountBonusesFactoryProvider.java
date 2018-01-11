package pl.wipek.accounts.bonuses.factory;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.matchprocessor.ClassMatchProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.accounts.bonuses.exceptions.NoFactoryException;
import pl.wipek.shared.domain.entity.Account;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class AccountBonusesFactoryProvider {

    private static final Logger logger = LoggerFactory.getLogger(AccountBonusesFactoryProvider.class);


    public static AccountBonusesFactory getFactory(@NotNull Account account) throws NoFactoryException {
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
