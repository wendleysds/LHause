package com.lhause.wend.LHouseWeb.data;

import com.lhause.wend.LHouseWeb.model.UserLoginData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Wendley S
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    @Query(value="select * from usuario where login = ?1 and senha = ?2", nativeQuery=true) 
    UserEntity findByLoginAndPassword(String login, String password);
}
