package pl.wipek.accounts.ejb.finder;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EjbFinder {
    public static Object getBean(String beanName) throws NamingException {
        return new InitialContext().lookup(beanName);
    }
}
