package pakiet.scheduledTransfer.interfaces;

/**
 * Created by Michał on 20.01.2018.
 */
public interface Transfer {

    public void  changeSender();
    public void  changeBeneficary();
    public void  changeDate();
    public void  changeAmount();
    public void  cancelTransfer();

}