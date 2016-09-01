package order.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by MSBH9799 on 13/04/2016.
 */

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderID;

    @NotNull
    private Long userID;

    @NotNull
    private Long videoID;

    public Order() {
    }

    public Order(Long userID, Long videoID) {
        this.userID = userID;
        this.videoID = videoID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getVideoID() {
        return videoID;
    }

    public void setVideoID(Long videoID) {
        this.videoID = videoID;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }
}
