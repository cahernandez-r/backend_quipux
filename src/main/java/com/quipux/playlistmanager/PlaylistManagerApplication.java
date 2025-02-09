package com.quipux.playlistmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class PlaylistManagerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PlaylistManagerApplication.class, args);
    }
}
