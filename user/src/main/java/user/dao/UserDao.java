package user.dao;

import user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by AdminMiage on 07/04/2016.
 */
public interface UserDao extends JpaRepository<User, String> {

    public List<User> findAll();

    public User findByLastname(String lastname);

    public User findByUserID(Long userID);

}
