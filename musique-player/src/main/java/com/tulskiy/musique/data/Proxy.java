package com.tulskiy.musique.data;

/**
 * @author JuanLv created at 2022/2/18
 * olbbme@gmail.com
 */
public enum Proxy {

    Ins;

    public static Proxy getIns() {
        return Ins;
    }

    Configuration mConfiguration;

    Proxy() {
       mConfiguration = new Configuration();
    }


    public Configuration getConfiguration() {
        return mConfiguration;
    }
}
