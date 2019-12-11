package com.example.demo.names;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "Names")
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Name {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    private int id;

    private String name;

    public static Name valueOf(String name) {
        return new Name(0, name);
    }
}
