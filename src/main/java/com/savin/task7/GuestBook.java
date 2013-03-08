package com.savin.task7;

import com.savin.db.controller.DBController;
import com.savin.db.controller.GuestBookController;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: BuOs
 * Date: 02.03.13
 * Time: 15:14
 * To change this template use File | Settings | File Templates.
 */
public class GuestBook {
    private GuestBookController db;
    public  GuestBook(DataSource sources){
        db = new DBController(sources);
        System.out.println("Guest BOOk init");
    };

    public synchronized void add(String message)throws IOException,SQLException {
        db.addRecord(message.trim());
        System.out.println("Add record:"+message);
    }
    public synchronized ArrayList<String> list() throws SQLException{
        ArrayList<String>ms;
        ms=db.getRecords();
        return ms;
    }
    public synchronized void close(){
        try {
            db.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


}
