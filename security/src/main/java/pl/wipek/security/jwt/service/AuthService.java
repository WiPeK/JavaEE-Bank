package pl.wipek.security.jwt.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wipek.security.jwt.dao.AuthDao;
import pl.wipek.security.jwt.util.KeyGenerator;
import pl.wipek.shared.domain.entity.Users;
import pl.wipek.shared.ejb.dao.exceptions.DaoException;
import pl.wipek.shared.util.converter.DateConverter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Krzysztof Adamczyk on 25.10.2017.
 */
@Stateless
public class AuthService implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @EJB
    private KeyGenerator keyGenerator;

    @Context
    private UriInfo uriInfo;

    @EJB(beanInterface = AuthDao.class, beanName = "AuthDaoImpl")
    private AuthDao authDao;

    public Users autenticate(Users user) {
        try {
            user = authDao.isUserExists(user);
        } catch (SecurityException e) {
            logger.info("Invalid user: " + user);
        }
        return user;
    }

    public String issueToken(Users user) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(user.getLogin())
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(DateConverter.fromLocalDateTimeToDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        user.setToken(jwtToken);
//        try {
//            authDao.merge(user);
//        } catch (DaoException e) {
//            logger.error(e.toString());
//        }

        logger.info("#### generating token for a key : " + jwtToken + " - " + key);
        return jwtToken;
    }
}
