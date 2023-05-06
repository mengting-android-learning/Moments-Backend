package com.example.momentsbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sender {
    private Long id;
    private String userName;
    private String nick;
    private String avatar;
}
