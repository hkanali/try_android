package com.example.hk.myapplication.type;

import android.util.Log;

import com.example.hk.myapplication.R;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RecyclerViewHolderType {

    PLAYER_POST(0, R.layout.content_player_post);

    @Getter
    private int id;

    @Getter
    private int layoutResouceId;

    public static RecyclerViewHolderType of(int id) {

        for (RecyclerViewHolderType type :
                values()) {

            if (type.id == id) {

                return type;
            }
        }

        throw new IllegalStateException(String.format("id: %s", id));
    }
}
