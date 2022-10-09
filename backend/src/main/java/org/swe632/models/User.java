package org.swe632.models;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;
import javax.persistence.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@javax.persistence.Table(name = "users", indexes = {
        @Index(name = "idx_user_email", columnList = "email")
})
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;

}
