package com.lhause.wend.LHouseWeb.service;

import com.lhause.wend.LHouseWeb.data.UserEntity;
import com.lhause.wend.LHouseWeb.data.UserRepository;
import com.lhause.wend.LHouseWeb.Utils.Criptografar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Wendley S
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public UserEntity createUser(UserEntity user){
        user.setId(null);
        userRepository.save(user);
        return user;
    }
    
    public UserEntity findUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }
    
    public UserEntity findUser(String login, String password){
        return userRepository.findByLoginAndPassword(login, Criptografar.md5(password));
    }
    
    public UserEntity updateUser(Integer id, UserEntity userRequest){
        UserEntity user = findUserById(id);
        
        user.setCpf(userRequest.getCpf());
        user.setEmail(userRequest.getEmail());
        user.setLogin(userRequest.getLogin());
        user.setNome(userRequest.getNome());
        user.setSenha(userRequest.getSenha());
        user.setTelefone(userRequest.getTelefone());
        user.setTipo(userRequest.getTipo());
        user.setUltimoLogin(userRequest.getUltimoLogin());
        
        userRepository.save(user);
        
        return user;
    }
    
    public List<UserEntity> findAllUsers(){
        return userRepository.findAll();
    }
    
    public void deleteUser(Integer id){
        userRepository.deleteById(findUserById(id).getId());
    }
}
