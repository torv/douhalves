package com.torv.db.doumovide.util;

/**
 * Created by admin on 16/6/25.
 */
public class UrlConstant {

    public static final String HOST = "http://api.douban.com/";
    public static final String VERSION = "v2/";
    public static final String CATEGORY_MOVIE = "movie/";

    public static final String IN_THEATERS = "in_theaters";

    public static String getMovieBaseUrl(){
        return HOST + VERSION + CATEGORY_MOVIE;
    }
}
