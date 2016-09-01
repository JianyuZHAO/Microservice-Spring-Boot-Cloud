package creation.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import creation.DAO.movieClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by MSBH9799 on 03/05/2016.
 */
@Service
public class movieService {

    @Autowired
    private movieClient movieClient;

    @HystrixCommand(fallbackMethod="defaultCreateMovie",
            commandProperties = {
                    @HystrixProperty(
                            name = "circuitBreaker.errorThresholdPercentage", value = "20"),   // over 20% failure rate in 10 second period, open breaker
                    @HystrixProperty(
                            name = "circuitBreaker.SleepWindowInMilliseconds", value = "1000")}     // After 1 second, try closing breaker
    )
    public String createMovie(String title,Long categoryID){

        if(movieClient.createMovie(title,categoryID).equals("ok"))
            return "The movie is created successfully";
        else
            return "Error!!! The movie is not created";
    }

    public String defaultCreateMovie(String title,Long categoryID){
        return "The movie service is not available now.";
    }

}



