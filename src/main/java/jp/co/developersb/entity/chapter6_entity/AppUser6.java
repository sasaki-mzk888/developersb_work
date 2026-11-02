package jp.co.developersb.entity.chapter6_entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "appuser6")
public class AppUser6 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private  Role role;

    protected AppUser6(){
    }

    public AppUser6(String username, String password,Role role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
