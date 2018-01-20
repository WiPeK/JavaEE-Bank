package pl.wipek.shared.transfer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Michał on 20.01.2018.
 */

@Entity
@Table(name = "Sch_Transfer")
@XmlRootElement
public class Transfer implements Serializable {


    private Transfer transfer;

    @Id
    @Column(name = "transferID", nullable = false)
    private String id;

    @Column(name = "IBAN", nullable = false)
    private String IBAN;

    @Column(name = "Money", nullable = false)
    private String balance;

    @Column(name = "BeneficaryIBAN", nullable = false)
    private String beneficiaryIBAN;

    @Column(name = "trans_date", nullable = false)
    private Date date;

}
