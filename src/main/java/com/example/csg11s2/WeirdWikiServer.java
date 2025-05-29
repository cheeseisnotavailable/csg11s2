package com.example.csg11s2;

import com.example.csg11s2.framework.Page;
import com.example.csg11s2.util.FileOperations;
import com.example.csg11s2.util.FormatUnwrapper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class WeirdWikiServer {
    public static void main(String[] args) throws Exception {
        System.out.println(FormatUnwrapper.wrapFormat(FileOperations.readFile("/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/dollshouse.html"), ""));

    }
}