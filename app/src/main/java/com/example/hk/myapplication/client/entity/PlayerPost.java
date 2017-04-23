package com.example.hk.myapplication.client.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PlayerPost extends BaseEntity {

    public Long id;

    public String playerName;

    public String imageUrl;
}
