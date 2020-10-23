package com.cms.db;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author DELL PC
 */
public class DBConnection {
  public static Connection connect() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
      return DriverManager.getConnection("jdbc:mysql://localhost:3306/cms", "root", "");
  }
}
