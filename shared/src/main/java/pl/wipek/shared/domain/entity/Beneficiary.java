package pl.wipek.shared.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

public abstract class Beneficiary implements Serializable {
    public abstract DomesticTransfer getDomesticTransfer();
    public abstract String getAccountNumber();
    public abstract String getAddress();
    public abstract String getName();
    public abstract String getId();
}
