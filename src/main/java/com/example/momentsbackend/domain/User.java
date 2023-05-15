package com.example.momentsbackend.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class User extends BaseUser {

    private String profileImage;

    public User(long id, String userName, String nick, String avatar, String profileImage) {
        super(id, userName, nick, avatar);
        this.profileImage = profileImage;
    }
}
