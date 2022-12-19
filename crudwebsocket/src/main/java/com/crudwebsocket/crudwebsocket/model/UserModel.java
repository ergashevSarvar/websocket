package com.crudwebsocket.crudwebsocket.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserModel {
    private String name;
    private String login;
    private String email;
}
