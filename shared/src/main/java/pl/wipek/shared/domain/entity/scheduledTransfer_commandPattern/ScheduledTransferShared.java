package pl.wipek.shared.domain.entity.scheduledTransfer_commandPattern;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Michał on 20.01.2018.
 */

@Entity
@Table(name = "scheduledTransfer")
@XmlRootElement
public class ScheduledTransferShared implements Serializable {


    @Id
    @Column(name = "transferID", nullable = false)
    private String id;

    @Column(name = "trans_state", nullable = false)
    private String state;

    @Column(name = "userID", nullable = false)
    private String userId;

   @OneToMany(fetch = FetchType.LAZY)
   @JoinColumn(name = "sh_transferID")
    private Set<Transfer> transfers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduledTransferShared that = (ScheduledTransferShared) o;

        if (!id.equals(that.id)) return false;
        if (!state.equals(that.state)) return false;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + userId.hashCode();
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(Set<Transfer> transfers) {
        this.transfers = transfers;
    }

}
