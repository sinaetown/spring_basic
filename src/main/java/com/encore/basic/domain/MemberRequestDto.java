package com.encore.basic.domain;

import lombok.Data;

@Data
public class MemberRequestDto {
    private String name;
    private String email;
    private String password;
}
