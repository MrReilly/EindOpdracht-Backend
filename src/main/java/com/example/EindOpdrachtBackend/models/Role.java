package com.example.EindOpdrachtBackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name="roles")
public class Role {

    @Id
    @Column(name = "role_name")
    @Enumerated(value = EnumType.STRING)
    private RoleOption rolename;

    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    @JsonIgnore
    private Collection<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return rolename != null && Objects.equals(rolename, role.rolename);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}





