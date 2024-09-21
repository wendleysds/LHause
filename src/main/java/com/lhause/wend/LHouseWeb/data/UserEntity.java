package com.lhause.wend.LHouseWeb.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.sql.Date;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Wendley S
 */
@Data
@Entity
@Table(name = "usuario")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    
    @Size(min=2, message="Informe ao menos 2 caracteres para o campo nome") 
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "tipo",nullable = false)
    private String tipo;
    
    @NotBlank(message="CPF obrigatório") 
    @CPF(message="CPF inválido") 
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;
    @Column(name = "email", unique = true, nullable = false)
    @NotBlank(message="Email obrigatório") 
    private String email;
    @Column(name = "telefone", unique = true, nullable = false)
    private String telefone;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "senha", nullable = false)
    private String senha;
    @Column(name = "ultimo_login")
    private Date ultimoLogin;
}
