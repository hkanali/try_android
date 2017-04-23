package com.example.hk.myapplication.client.entity;

public class News extends BaseEntity {

    public Long id;

    public Meta meta;

    public static class Meta {

        public String imageUrl;
    }
}
