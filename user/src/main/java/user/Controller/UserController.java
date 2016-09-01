package user.Controller;

import user.entities.User;
import user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;


/**
 * A class interacts with the MySQL database using the OrderDao class.
 */
@RestController
//@RequestMapping(value = "/user")
@EnableSwagger2
public class UserController {

    @Autowired
    private UserDao UserDao;

    /**
     * The welcoming page, which gives the description.
     *
     * @return a string description.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "This is user list";
    }

    /**
     * List all the users in the database.
     *
     * @return All the users.
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return UserDao.findAll();
    }

    /**
     * Create a new user and save it in the database.
     *
     * @param firstname The firstname of new user.
     * @param lastname The lastname of new user.
     * @return The new user, or null if it is not successfully created.
     */
    @RequestMapping(value = "/create/{firstname}/{lastname}", method = RequestMethod.POST)
    @ResponseBody
    public String createUser(@PathVariable(value = "firstname") String firstname, @PathVariable(value = "lastname") String lastname) {
        User user = null;
        try {
            user = new User(firstname, lastname);
            UserDao.save(user);
        } catch (Exception ex) {
            return "error";
        }
        return "ok";
    }

    /**
     * Get the user with the given last name.
     *
     * @param lastname  The last name of user which to be searched in the database.
     * @return The user with the given name or null if there is no ordered movie.
     */
    @RequestMapping(value = "/getByLastname/{lastname}", method = RequestMethod.GET)
    public User getByLastname(@PathVariable String lastname) {
        User user = null;
        try {
            user = UserDao.findByLastname(lastname);
        }catch (Exception ex) {
            return user;
        }
            return user;
    }

    /**
     * Get the user with the given id.
     *
     * @param userID  The id of user which to be searched in the database.
     * @return The the user with the given id or null if there is no ordered movie.
     */
    @RequestMapping(value = "/getByUserID/{userID}", method = RequestMethod.GET)
    public User getByUserID(@PathVariable Long userID) {
        User user = null;
        try {
            user = UserDao.findByUserID(userID);
        }catch (Exception ex) {
            return user;
        }
        return user;
    }

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        ApiInfo apiInfo = new ApiInfo("User", "This service manages all the users", null, null, null, null, null);
        return new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/.*")).build()
                .apiInfo(apiInfo).useDefaultResponseMessages(false);
    }

}
