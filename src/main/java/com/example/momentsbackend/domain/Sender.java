package com.example.momentsbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Sender {
    private Long id;
    private String userName;
    private String nick;
    private String avatar;
}
