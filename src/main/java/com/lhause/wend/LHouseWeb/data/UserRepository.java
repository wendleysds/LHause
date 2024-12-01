package com.lhause.wend.LHouseWeb.data;

import jakarta.transaction.Transactional;
import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Wendley S
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    @Query(value="select * from usuario where login = ?1", nativeQuery=true) 
    UserEntity findByLogin(String login);
    
    @Query(value="select * from usuario where login = ?1 and senha = ?2", nativeQuery=true) 
    UserEntity findByLoginAndPassword(String login, String password);
    
    @Modifying
    @Transactional
    @Query(value="update usuario set ultimo_login = ?1 where id = ?2", nativeQuery=true)
    Integer updateLastLogin(Date data, Integer userId);
}
