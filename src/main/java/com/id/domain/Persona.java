package com.id.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.*;

@Data
@Entity
@Table(name = "persona2")
public class Persona implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id_persona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido;
    
    @NotEmpty//(message = "Error")
    @Email
    private String email;
    
    private String telefono;
    
    @NotNull
    private Double saldo;
    
}
