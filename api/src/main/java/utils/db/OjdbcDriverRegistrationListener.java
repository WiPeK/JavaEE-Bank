package utils.db;

import oracle.jdbc.OracleDriver;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class OjdbcDriverRegistrationListener implements ServletContextListener {
//    private static final Logger LOG = LoggerFactory
//            .getLogger(OjdbcDriverRegistrationListener.class);

    private Driver driver = null;

    /**
     * Registers the Oracle JDBC driver
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.driver = new OracleDriver(); // load and instantiate the class
        boolean skipRegistration = false;
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver instanceof OracleDriver) {
                OracleDriver alreadyRegistered = (OracleDriver) driver;
                if (alreadyRegistered.getClass() == this.driver.getClass()) {
                    // same class in the VM already registered itself
                    skipRegistration = true;
                    this.driver = alreadyRegistered;
                    break;
                }
            }
        }

        try {
            if (!skipRegistration) {
                DriverManager.registerDriver(driver);
            }// else {
//                LOG.debug("driver was registered automatically");
            //}
            //LOG.info(String.format("registered jdbc driver: %s v%d.%d", driver,
            //        driver.getMajorVersion(), driver.getMinorVersion()));
        } catch (SQLException e) {
            // LOG.error(
//                    "Error registering oracle driver: " +
//                            "database connectivity might be unavailable!",
//                    e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Deregisters JDBC driver
     *
     * Prevents Tomcat 7 from complaining about memory leaks.
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (this.driver != null) {
            try {
                DriverManager.deregisterDriver(driver);
                //LOG.info(String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
//                LOG.warn(
//                        String.format("Error deregistering driver %s", driver),
//                        e);
            }
            this.driver = null;
        } //else {
//            LOG.warn("No driver to deregister");
        //}

    }

}