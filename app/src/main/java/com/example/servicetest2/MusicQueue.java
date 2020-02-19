package com.example.servicetest2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

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
    public static Vector<Music> queue = new Vector<Music>();
    public static int i = 0; //记录当前播放的下标

    public boolean isinclude(String name){ //如果没有才能加入，否则会造成重复
        for(Music q : queue){
            if (q.musicname == name){
                return true;
            }
        }
        return false;
    }

//    public String getmusicurl(String name){
//        for(Music q : queue){
//            if (q.musicname == name){
//                return q.musicurl;
//            }
//        }
//        return null;
//    }
//
//    public String getnextmusicurl(String name){
//        Music test = new Music(null,null,null);
//        for(int i = 0; i<queue.size(); i++){
//            test = queue.get(i);
//            if (test.musicname == name){
//                return queue.get(i+1).musicurl;
//            }
//        }
//        return null;
//    }

    public int getindex(String name){
        Music test = new Music(null,null,null);
        for(int i = 0; i<queue.size(); i++){
            test = queue.get(i);
            if (test.musicname == name){
                return i;
            }
        }
        return -1; //播放队列中无
    }
}
