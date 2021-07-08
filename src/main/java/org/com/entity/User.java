package org.com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name="users"
        ,catalog="users_BD"
        , uniqueConstraints = @UniqueConstraint(columnNames={"Login", "Password"})
)
@AttributeOverride(name = "id", column = @Column(name = "id"))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name="Login", nullable=false, length=15)
    private String login;

    @Column(name="Password", nullable=false, length=64)
    private String password;

    @Column(name="Authority", length=10)
    private String authority;
}

