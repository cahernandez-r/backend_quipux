package com.quipux.playlistmanager.domains.playlist;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Route {

    public static final String LISTS = "lists";

    public static final String LISTS_DETAIL = "{listName}";

    public static final String LISTS_DELETE = "{listName}";
}
