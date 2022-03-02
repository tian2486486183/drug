package com.harlon.drugmanagement.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInfoUser {

    private String password;
    private String nickName;
    private String email;
    private String sex;
    private String avatar;
    private String phonenumber;
}
