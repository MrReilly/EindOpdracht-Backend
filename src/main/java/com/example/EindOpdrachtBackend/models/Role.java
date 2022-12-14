package com.example.EindOpdrachtBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "role_name")
    @Enumerated(value = EnumType.STRING)
    private RoleOption rolename;

    @OneToMany(mappedBy = "role")
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
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





