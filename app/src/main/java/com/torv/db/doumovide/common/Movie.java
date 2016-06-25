package com.torv.db.doumovide.common;

import java.util.List;

/**
 * Created by admin on 16/6/25.
 */
public class Movie {

    public Rating rating;
    public List<String> genres;
    public String title;
    public List<Cast> casts;
    public int collect_count;
    public String original_title;
    public String subtype;
    public List<Cast> directors;
    public String year;
    public Cast.Avatars images;
    public String alt;
    public String id;


    public static class Rating{
        public float max;
        public float average;
        public String stars;
        public float min;
    }

    public static class Cast{
        public String alt;
        public Avatars avatars;
        public String name;
        public String id;

        public static class Avatars{
            public String small;
            public String large;
            public String medium;
        }
    }
}
