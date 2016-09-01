package Movie.controllers;

import Movie.Dao.CategoryDao;
import Movie.models.Category;
import Movie.models.Movie;
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
 * Created by MSBH9799 on 04/04/2016.
 */

/**
 * A class interacts with the MySQL database using the CategoryDao class.
 */

@RestController
@RequestMapping(value = "/category")
@EnableSwagger2
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;


    /**
     * The welcoming page, which gives the description.
     *
     * @return A string of description.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "This is category list";
    }

    /**
     * List all the categories in the database.
     *
     * @return All the categories.
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Category> all() {
        return categoryDao.findAll();
    }


    /**
     * Create a new category and save it in the database.
     *
     * @param name New category's name
     * @param description The description of the new category.
     * @return A string describing if the category is successfully created or not.
     */
    @RequestMapping(value = "/create/{name}/{description}", method = RequestMethod.POST)
    @ResponseBody
    public String create(@PathVariable(value = "name") String name, @PathVariable(value = "description") String description) {

        Category category = null;
        try {
            category = new Category(name, description);
            categoryDao.save(category);
        } catch (Exception ex) {
            return "error";
        }
        return "ok";
    }


    /**
     * Get the category  having the passed name.
     *
     * @param name The name to be searched in the database.
     * @return The category, or null if the category is not found.
     */
    @RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Category getByName(@PathVariable(value = "name") String name) {
        Category category = null;
        try {
            category = categoryDao.findByName(name);
        } catch (Exception ex) {
            return category;
        }
        return category;
    }


    /**
     * Get the category having the passed id.
     *
     * @param categoryID The category id to be searched in the database.
     * @return The category, or null if the movie is not found.
     */
    @RequestMapping(value = "/getByID/{categoryID}", method = RequestMethod.GET)
    @ResponseBody
    public Category getByID(@PathVariable(value = "categoryID") Long categoryID) {
        String name;
        Category category = null;
        try {
            category = categoryDao.findByCategoryID(categoryID);
            name = category.getName();
        } catch (Exception ex) {
            return category;
        }
        return category;
    }

    /**
     * Get the movies in the category.
     *
     * @param categoryID The category id to be searched in the database.
     * @return The list of movie in the category, or null if the category is not found or there is no movie in it.
     */
    @RequestMapping(value = "/getMoviesByCategory/{categoryID}", method = RequestMethod.GET)
    @ResponseBody
    public List<Movie> getMoviesByCategory(@PathVariable(value = "categoryID") Long categoryID) {
        Category category;
        List<Movie> moviesList = new ArrayList<Movie>();
        try {
            category = categoryDao.findByCategoryID(categoryID);
            moviesList = category.getMovies();
            return moviesList;
        } catch (Exception ex) {
            return moviesList;
        }
    }

//    @Bean
//    public Docket swaggerSpringMvcPlugin() {
//        ApiInfo apiInfo = new ApiInfo("category", "scategory", null, null, null, null, null);
//        return new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/.*")).build()
//                .apiInfo(apiInfo).useDefaultResponseMessages(false);
//    }

}