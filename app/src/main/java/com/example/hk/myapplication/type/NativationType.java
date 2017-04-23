package com.example.hk.myapplication.type;

import android.support.v4.app.Fragment;

import com.example.hk.myapplication.R;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum NativationType {

    CAMERA(R.id.nav_camera, null),
    GALLERY(R.id.nav_gallery, null),
    SLIDE_SHOW(R.id.nav_slideshow, null),
    MANAGE(R.id.nav_manage, null),
    SHARE(R.id.nav_share, null),
    SEND(R.id.nav_send, null),;

    @Getter
    private int resourceId;

    @Getter
    private Fragment fragment;

    public static NativationType of(int resourceId) {

        for (NativationType navigationType :
                values()) {

            if (resourceId == navigationType.getResourceId()) {

                return navigationType;
            }
        }

        throw new IllegalStateException();
    }
}
