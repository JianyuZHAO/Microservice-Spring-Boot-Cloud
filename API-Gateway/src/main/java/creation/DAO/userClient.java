package creation.DAO;

import creation.models.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by MSBH9799 on 02/05/2016.
 */

@FeignClient("user")
public interface userClient {

    @RequestMapping(method= RequestMethod.POST, value="/user/create/{firstname}/{lastname}")
    String createUser(@PathVariable(value = "firstname") String firstname, @PathVariable(value = "lastname") String lastname);

    @RequestMapping(method= RequestMethod.GET, value="/user/getByUserID/{userID}")
    User getByUserID(@PathVariable (value = "userID") Long userID);

}
