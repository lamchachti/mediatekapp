package org.learn.mediatek.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity implements Serializable {
    @Id
    private Integer role_id;
    @Column(nullable = false)
    private  String role_name;
    @ManyToMany
    @JoinTable(name="user_roles",
            joinColumns= @JoinColumn(name="role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> users;

}
