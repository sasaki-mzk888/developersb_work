package jp.co.developersb.entity.chapter7_entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "appuser7")
public class AppUser7 {

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
    private Role7 role;

    protected AppUser7() {}   // JPA用(必須)

    public AppUser7(String username, String password, Role7 role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

}