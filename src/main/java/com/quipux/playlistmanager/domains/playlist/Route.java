package com.quipux.playlistmanager.domains.playlist;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Route {

    public static final String PLAY_LIST = "playlist";

    public static final String LISTS = "lists";

    public static final String LISTS_DETAIL = "lists/{listName}";
}
