package com.savin.db.controller;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GuestBookController extends AutoCloseable {
    void addRecord(String message) throws SQLException;

    ArrayList<String> getRecords() throws SQLException; //Record {id, postDate, message}

    void close() throws SQLException;

    void delete() throws SQLException;
}
