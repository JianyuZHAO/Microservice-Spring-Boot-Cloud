package creation.controller;

import creation.DAO.movieClient;
import creation.models.Category;
import creation.models.Movie;
import creation.service.movieService;
import creation.DAO.orderClient;
import creation.DAO.userClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;


/**
 * A class interacts with the other microservices: user, movie, order, to create data in their database.
 */
@Controller
@RequestMapping("/APIGateway")
@EnableSwagger2

public class APIGatewayController {

    @Autowired private movieClient movieService;
    @Autowired private movieService movieService2;

    @Autowired private userClient userService;
    @Autowired private orderClient orderService;



    /**
     * The welcoming page, which gives the description.
     *
     * @return a string description.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    String home() {
        return "This is API Gateway";
    }




    /**
     * Get all the categories which the movies ordered by the user belongs to. This function serves the getRecommendedMovieListByUserID function.
     *
     * @param userID  The user's id which will be searched in the database.
     * @return The category list of the the user.
     */
    @RequestMapping(value = "/CategoryList/{userID}", method = RequestMethod.GET)
    @ResponseBody
    private List<Category> getCategoryListByUserID(@PathVariable(value = "userID") Long userID) {

        List<Category> categoryList = new ArrayList<Category>();
        List<Long> videoIDs = orderService.getVideoIDByUserID(userID);
        for (Long videoID : videoIDs) {
            categoryList.addAll(movieService.showCategories(videoID));
        }
        return categoryList;
    }

    /**
     * Get the recommended movies for the user with the given id -- all movies in the categories found by function getCategoryListByUserID.
     *
     * @param userID  The user's id which will be searched in the database.
     * @return The recommended movies for the user.
     */
    @RequestMapping(value = "/MovieList/{userID}", method = RequestMethod.GET)
    @ResponseBody
    private List<Movie> getRecommendedMovieListByUserID(@PathVariable(value = "userID") Long userID) {

        List<Movie> movieList = new ArrayList<Movie>();
        List<Category> categoryList = getCategoryListByUserID(userID);
        for (Category Category : categoryList) {
            movieList.addAll(movieService.getMoviesByCategory(Category.getCategoryID()));
        }
        return movieList;
    }





    /**
     * Create a new movie and save it in the database.
     *
     * @param title  new movie's title
     * @param categoryID The new movie's category id
     * @return A string describing if the movie is successfully created or not.
     */
      @RequestMapping(value = "/movie/{title}/{categoryID}", method = RequestMethod.POST)
      @ResponseBody
      public String createMovie(@PathVariable(value = "title") String title, @PathVariable(value = "categoryID") Long categoryID){

        return movieService2.createMovie(title,categoryID);
    }


    /**
     * Create a new category and save it in the database.
     *
     * @param name New category's name
     * @param description The description of the new category.
     * @return A string describing if the category is successfully created or not.
     */
    @RequestMapping(value = "/category/{name}/{description}", method = RequestMethod.POST)
    @ResponseBody
    public String createCategory(@PathVariable(value = "name") String name, @PathVariable(value = "description") String description){

        if(movieService.createCategory(name,description).equals("ok"))
            return "The category is created successfully";
        else
            return "Error!!! The category is not created";
    }




    /**
     * Create a new user and save it in the database.
     *
     * @param firstname New new user's firstname.
     * @param lastname New new user's lastname.
     * @return A string describing if the category is successfully created or not.
     */
    @RequestMapping(value = "/user/{firstname}/{lastname}", method = RequestMethod.POST)
    @ResponseBody
    public String createUser(@PathVariable(value = "firstname") String firstname, @PathVariable(value = "lastname") String lastname){

        if(userService.createUser(firstname,lastname).equals("ok"))
            return "The category is user successfully";
        else
            return "Error!!! The user is not created";
    }


    /**
     * Create a new order and save it in the database.
     *
     * @param userID  The user's id of new order
     * @param videoID The video's id of new order
     * @return The new order, or null if it is not successfully created.
     */
    @RequestMapping(value = "/order/{userID}/{videoID}", method = RequestMethod.POST)
    @ResponseBody
    public String create(@PathVariable(value = "userID") Long userID, @PathVariable(value = "videoID") Long videoID) {

        if ( (movieService.getMovieByID(videoID) != null) && (userService.getByUserID(userID) != null)) {

            if (orderService.createOrder(userID, videoID).equals("ok"))
                return "The order is created successfully";
            else {
                return "Error!!! The order is not created";
            }
        }
        else{
            if(userService.getByUserID(userID) == null)
                return "Error!!! The user doesn't exist";
            else
                return "Error!!! The movie doesn't exist";
        }

    }

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        ApiInfo apiInfo = new ApiInfo("API Gateway", "This is the API Gateway.", null, null, null, null, null);
        return new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/.*")).build()
                .apiInfo(apiInfo).useDefaultResponseMessages(false);
    }


}
