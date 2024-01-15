package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResponseDto {
    private int id;
    private String name;
    private String email;
    private String password;
}
