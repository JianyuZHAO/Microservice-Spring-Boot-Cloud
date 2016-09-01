package creation.DAO;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import creation.models.Category;
import creation.models.Movie;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by MSBH9799 on 29/04/2016.
 */

@FeignClient("movies-and-categories")
public interface movieClient {

    @RequestMapping(method= RequestMethod.POST, value="/movie/create/{title}/{categoryID}")
    String createMovie(@PathVariable(value = "title") String title, @PathVariable(value = "categoryID") Long categoryID);

    @RequestMapping(method= RequestMethod.POST, value="/category/create/{name}/{description}")
    String createCategory(@PathVariable(value = "name") String name, @PathVariable(value = "description") String description);

    @RequestMapping(method= RequestMethod.GET, value="/movie/getByID/{videoID}")
    Movie getMovieByID(@PathVariable(value = "videoID") Long videoID);


    @RequestMapping(method= RequestMethod.GET, value="/movie/showCategories/{videoID}")
    List<Category> showCategories(@PathVariable(value = "videoID") Long videoID);

    @RequestMapping(method= RequestMethod.GET, value="/category/getMoviesByCategory/{categoryID}")
    List<Movie> getMoviesByCategory(@PathVariable(value = "categoryID") Long categoryID);

}
