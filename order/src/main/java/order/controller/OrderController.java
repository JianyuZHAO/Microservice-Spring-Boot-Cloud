package order.controller;

import order.Dao.OrderDao;
import order.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by MSBH9799 on 13/04/2016.
 */


/**
 * A class interacts with the MySQL database using the OrderDao class.
 */
@RestController
//@RequestMapping("/order")
@EnableSwagger2
public class OrderController {

    @Autowired
    private OrderDao orderDao;


    /**
     * The welcoming page, which gives the description.
     *
     * @return a string description.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "This is order list";
    }


    /**
     * List all the orders in the database.
     *
     * @return All the orders.
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Order> all() {
        return orderDao.findAll();
    }


    /**
     * Create a new order and save it in the database.
     *
     * @param userID  The user's id of new order
     * @param videoID The video's id of new order
     * @return The new order, or null if it is not successfully created.
     */
    @RequestMapping(value = "/create/{userID}/{videoID}", method = RequestMethod.POST)
    @ResponseBody
    public String create(@PathVariable(value = "userID") Long userID, @PathVariable(value = "videoID") Long videoID) {
        Order order = null;
        try {
            order = new Order(userID, videoID);
            orderDao.save(order);
        } catch (Exception ex) {
            return "error";
        }
        return "ok";
    }


    /**
     * Get the videos ordered by the user with the given id.
     *
     * @param userID  The user's id to be searched in the database.
     * @return The list of movie id ordered by the user, or null if there is no ordered movie.
     */
    @RequestMapping(value = "/getVideoIDByUserID/{userID}", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> getVideoIDByUserID(@PathVariable(value = "userID") Long userID) {
        List<Long> videoIDList = new ArrayList<Long>();
        List<Order> orderList;
        try {
            orderList = orderDao.findByUserID(userID);
            for (Order anOrderList : orderList) {
                videoIDList.add(anOrderList.getVideoID());
            }
        } catch (Exception ex) {
            return videoIDList;
        }
        return videoIDList;
    }

    /**
     * Get the orders of the user with the given id.
     *
     * @param userID  The user's id to be searched in the database.
     * @return The list of orders made the user, or null if there is no order found.
     */
    @RequestMapping(value = "/getByUserID/{userID}", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getByUserID(@PathVariable(value = "userID") Long userID) {
        List<Order> orderList = new ArrayList<Order>();
        try {
            orderList = orderDao.findByUserID(userID);
        } catch (Exception ex) {
            return orderList;
        }
        return orderList;
    }


    @RequestMapping(value = "/getByVideoID/{videoID}", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getByVideoID(@PathVariable(value = "videoID") Long videoID) {
        List<Order> orderList = new ArrayList<Order>();
        try {
            orderList = orderDao.findByVideoID(videoID);
        } catch (Exception ex) {
            return orderList;
        }
        return orderList;
    }


    @RequestMapping(value ="/getByOrderID/{orderID}", method = RequestMethod.GET)
    @ResponseBody
    public Order getByOrderID(@PathVariable(value = "orderID") Long orderID) {
        Order order = null;
        try {
            order = orderDao.findByOrderID(orderID);
        } catch (Exception ex) {
            return null;
        }
        return order;
    }

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        ApiInfo apiInfo = new ApiInfo("Order", "This service manages all the orders", null, null, null, null, null);
        return new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/.*")).build()
                .apiInfo(apiInfo).useDefaultResponseMessages(false);
    }

}
