package ru.itis.websockers.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@ToString(exclude = "tokens")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat_member")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String passwordHash;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Token> tokens;

}
