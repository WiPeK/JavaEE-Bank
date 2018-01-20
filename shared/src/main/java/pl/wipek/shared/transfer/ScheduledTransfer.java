package pl.wipek.shared.transfer;

import pl.wipek.shared.domain.entity.DomesticTransfer;
import pl.wipek.shared.domain.entity.User;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Michał on 20.01.2018.
 */

@Entity
@Table(name = "scheduledTransfer")
@XmlRootElement
public class ScheduledTransfer implements Serializable {

    @Id
    @Column(name = "transferID", nullable = false)
    private String id;

    @Column(name = "trans_state", nullable = false)
    private String state;

    @Column(name = "userID", nullable = false)
    private String userId;

    @OneToMany(mappedBy = "transfer", targetEntity = Transfer.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transfer> transfers;


}
