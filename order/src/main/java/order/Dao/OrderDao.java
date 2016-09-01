package order.Dao;

import order.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by MSBH9799 on 13/04/2016.
 */


/**
 * An interface OrderDao has three functions (findByOrderID, findByUserID, findByVideoID).
 * The Repository annotation indicates that this class is a Data Access Object.
 * The Transactional annotation defines the scope of a single database transaction.
 */
@Repository
@Transactional
public interface OrderDao extends JpaRepository<Order, Long> {

    public Order findByOrderID(Long orderID);

    @Query(value = "select a from Order a where a.userID = ?1")
    List<Order> findByUserID(Long userID);

    @Query(value = "select a from Order a where a.videoID = ?1")
    List<Order> findByVideoID(Long videoID);

}
