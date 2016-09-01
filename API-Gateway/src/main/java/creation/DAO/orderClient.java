package creation.DAO;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by MSBH9799 on 02/05/2016.
 */
@FeignClient("order")
public interface orderClient {

    @RequestMapping(method= RequestMethod.POST, value="/create/{userID}/{videoID}")
    String createOrder(@PathVariable(value = "userID") Long userID, @PathVariable(value = "videoID") Long videoID);

    @RequestMapping(method= RequestMethod.GET, value="/getVideoIDByUserID/{userID}")
    List<Long> getVideoIDByUserID(@PathVariable(value = "userID") java.lang.Long userID);

}
