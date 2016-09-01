package Movie.controllers;

/**
 * Created by MSBH9799 on 04/04/2016.
 */

import Movie.Dao.CategoryDao;
import Movie.models.Category;
import Movie.models.Movie;
import Movie.Dao.MovieDao;
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
 * A class interacts with the MySQL database using the MovieDao and CategoryDao class.
 */
@RestController
@RequestMapping(value = "/movie")
@EnableSwagger2
public class MovieListController {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private CategoryDao categoryDao;

    /**
     * The welcoming page, which gives the description.
     *
     * @return a string description.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "This is movie list";
    }

    /**
     * List all the movies in the database.
     *
     * @return All the movies.
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Movie> all() {
        return movieDao.findAll();
    }


    /**
     * Create a new movie and save it in the database.
     *
     * @param title  new movie's title
     * @param categoryID The new movie's category id
     * @return A string describing if the movie is successfully created or not.
     */
    @RequestMapping(value = "/create/{title}/{categoryID}", method = RequestMethod.POST)
    @ResponseBody
    public String create(@PathVariable(value = "title") String title, @PathVariable(value = "categoryID") Long categoryID) {

        Movie movie = null;
        List<Category> categoryCollection = new ArrayList<Category>();

        try {
            Category category = categoryDao.findByCategoryID(categoryID);
            categoryCollection.add(category);
            movie = new Movie(title, categoryCollection);
            movieDao.save(movie);
        } catch (Exception ex) {
            return "error";
        }
        return "ok";
    }


    /**
     * Return the title for the movie having the passed id.
     *
     * @param videoID The movie id to search in the database.
     * @return The movie, or null if the movie is not found.
     */
    @RequestMapping(value = "/getByID/{videoID}", method = RequestMethod.GET)
    @ResponseBody
    public Movie getByID(@PathVariable(value = "videoID") Long videoID) {
        Movie movie = null;
        try {
            movie = movieDao.findByVideoID(videoID);
        } catch (Exception ex) {
            return movie;
        }
        return movie;
    }


    /**
     * Add category for movie.
     *
     * @param videoID The id of the movie to add the category.
     * @param categoryID  The id of the category to be added to the movie.
     * @return The the categories of the movie after adding new category, or a message error if the movie is not found.
     */
    @RequestMapping(value = "/addCategory/{videoID}/{categoryID}", method = RequestMethod.POST)
    public List<Category> addCategory(@PathVariable(value = "videoID") Long videoID, @PathVariable(value = "categoryID") Long categoryID) {
        Movie movie;
        Category category;
        List<Category> categoryList;

        try {
            movie = movieDao.findByVideoID(videoID);
            categoryList = movie.getCategories();
            category = categoryDao.findByCategoryID(categoryID);
            categoryList.add(category);
            movieDao.updateCategory(videoID, categoryList);
            return categoryList;
        } catch (Exception ex) {
            return null;
        }
    }



    /**
     * Return the id for the movie having the passed title.
     *
     * @param title The title to search in the database.
     * @return The movie, or null if the movie is not found.
     */
    @RequestMapping(value = "/getByTitle/{title}", method = RequestMethod.GET)
    @ResponseBody
    public Movie getByTitle(@PathVariable(value = "title") String title) {
        Movie movie = null;
        try {
            movie = movieDao.findByTitle(title);
        } catch (Exception ex) {
            return null;
        }
        return movie;
    }

    /**
     * Delete the movie having the passed id.
     *
     * @param videoID The id of the movie to be deleted
     * @return A string describing if the movie is successfully deleted or not.
     */
    @RequestMapping(value = "/deleteByID/{videoID}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteByID(@PathVariable(value = "videoID") Long videoID) {

        try {
            Movie movie = new Movie(videoID);
            movieDao.delete(movie);
        } catch (Exception ex) {
            return "Error deleting the movie:" + ex.toString();
        }
        return "Movie successfully deleted!";
    }

    /**
     * Delete the movie having the passed title.
     *
     * @param title The title of the movie to be deleted
     * @return A string describing if the movie is successfully deleted or not.
     */
    @RequestMapping(value = "/deleteByTitle/{title}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteByTitle(@PathVariable(value = "title") String title) {
        try {
            Movie movie = movieDao.findByTitle(title);
            movieDao.delete(movie);
        } catch (Exception ex) {
            return "Error deleting the movie:" + ex.toString();
        }
        return "Movie successfully deleted!";
    }



    /**
     * Show the movie's categories.
     *
     * @param videoID The id of the movie to show the category.
     * @return The the categories of the movie or a message error if the movie is not found.
     */
    @RequestMapping(value = "/showCategories/{videoID}", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> showCategories(@PathVariable(value = "videoID") Long videoID) {
        Movie movie;
        List<Category> categoryList = new ArrayList<Category> ();
        String CategoryNames = " is in category: ";
        try {
            movie = movieDao.findByVideoID(videoID);
            categoryList = movie.getCategories();
            int numOfMovies = categoryList.size();
            for (Category aCategoryList : categoryList) {
                aCategoryList.getName();
                CategoryNames = CategoryNames + aCategoryList.getName() + ", ";
            }
            return categoryList;
        } catch (Exception ex) {
            return categoryList;
        }
    }

    /**
     * Update the title for a movie in the database.
     *
     * @param videoID The id of the movie to be updated.
     * @param title   The new title for the movie.
     * @return A string describing if the movie is successfully updated or not.
     */
    @RequestMapping(value = "/updateMovieTitle/{videoID}/{title}", method = RequestMethod.POST)
    @ResponseBody
    public String updateMovieTitle(@PathVariable(value = "videoID") Long videoID, @PathVariable(value = "title") String title) {
        try {
            Movie movie = movieDao.findOne(videoID);
            movie.setTitle(title);
            movieDao.save(movie);
        } catch (Exception ex) {
            return "Error updating the movie: " + ex.toString();
        }
        return "Movie successfully updated!";
    }

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        ApiInfo apiInfo = new ApiInfo("Movie and Category", "This service manages all the movies and categories", null, null, null, null, null);
        return new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/.*")).build()
                .apiInfo(apiInfo).useDefaultResponseMessages(false);
    }

}












