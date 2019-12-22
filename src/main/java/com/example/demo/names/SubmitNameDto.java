package com.example.demo.names;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class SubmitNameDto {
    private String name;

    public static SubmitNameDto valueOf(String name) {
        return new SubmitNameDto(name);
    }
}
