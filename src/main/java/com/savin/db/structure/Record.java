package com.savin.db.structure;

import javax.sql.DataSource;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class Record {
    private Connection conn;
    private Statement st1;

    public Record(DataSource ds) {
        try {
            this.conn = ds.getConnection();
            st1 = conn.createStatement();
            if (!new File("test.h2.db").exists()) {
                st1.execute("CREATE TABLE posts(id int NOT NULL AUTO_INCREMENT," +
                        "postDate long," +
                        "postMessage varchar(255)," +
                        "PRIMARY KEY (id))\n");
            }
            System.out.println("ready to start");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public void addRecord(long date, String postMessage) throws SQLException {
        st1.execute("INSERT INTO posts(postDate,postMessage) VALUES (" + date + ",'" + postMessage + "')");
    }

    public ArrayList<String> getRecords() throws SQLException {
        ResultSet result;
        ArrayList<String>list=new ArrayList<>();

        result = st1.executeQuery("SELECT * FROM posts");
        while (result.next()) {
            list.add(result.getString("PostMessage"));

        }
        return list;
    }

    public void delete() throws SQLException {
        st1.execute("DELETE FROM POSTS");
    }

    public void close() throws SQLException {
        conn.close();
    }


}
