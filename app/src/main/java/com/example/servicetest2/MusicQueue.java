package com.example.servicetest2;

import java.util.LinkedList;
import java.util.Queue;

public class MusicQueue {
    static class Music {
        public String musicname = null;
        public String singer = null;
        public String musicurl = null;

        public Music(String musicname1, String singer1, String musicurl1){
            this.musicname = musicname1;
            this.singer = singer1;
            this.musicurl = musicurl1;
        }
    }
    public static Queue<Music> queue = new LinkedList<Music>();
}
