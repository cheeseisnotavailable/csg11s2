package com.example.csg11s2;

import com.example.csg11s2.framework.Page;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class WeirdWikiServer {
    public static void main(String[] args) throws Exception {
        // set up the db connection
        // this comes from: https://ormlite.com/
        // this uses h2, but you can change it to match your database
        String databaseUrl = "jdbc:h2:file:./database/page.db";

        // create a connection source to our database
        ConnectionSource connectionSource =
                new JdbcConnectionSource(databaseUrl);

        // instantiate the DAO to handle User with integer id
        Dao<Page, String> pageDao = DaoManager.createDao(connectionSource, Page.class);

        // if you need to create the 'user' table make this call
        TableUtils.createTableIfNotExists(connectionSource, Page.class);

        // Search for the user in the db with id 1
        Page p1 = pageDao.queryForId("dbtest");
        if (p1 == null) {
            // create our one and only user
            p1 = new Page("dbtest", "yay this is a test");

            // persist the account object to the database
            pageDao.create(p1);
        }

        pageDao.update(p1);

    }
}