package org.learn.mediatek.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
    @Id
    private Integer user_id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private  String password;
    @ManyToMany
    @JoinTable(name="user_roles",
            joinColumns= @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private List<RoleEntity> roles;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserProfile profile;

}
