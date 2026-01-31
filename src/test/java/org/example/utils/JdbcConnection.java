package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcConnection {
  private static final String DBURL = Config.get("DBurl");
  private static final String USER = Config.get("dbLogin");
  private static final String PASSWORD = Config.get("dbPassword");
  private static final Logger LOG = LoggerFactory.getLogger(JdbcConnection.class);

  private static Connection con = null;

  public static Connection connectToDB() {
    LOG.info("Connect to DB " + DBURL + " by " + USER);

    try {
      Class.forName("org.postgresql.Driver");
      con = DriverManager.getConnection(DBURL, USER, PASSWORD);
      LOG.info("Connection to DB successful!");
    } catch (SQLException e) {
      LOG.error("Connection to DB failed!\n" + e.getMessage());
    } catch (ClassNotFoundException e) {
      LOG.error(e.getMessage());
    }

    return con;
  }
}
