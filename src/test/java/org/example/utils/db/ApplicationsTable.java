package org.example.utils.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.example.pojo.db.Application;
import org.example.utils.JdbcConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationsTable {
  private static final Logger LOG = LoggerFactory.getLogger(ApplicationsTable.class);

  public static Application getApplicationById(final Integer applicationId) {
    String query = "SELECT * FROM reg_office.applications WHERE applicationid = ?";
    Application application = null;

    try (Connection con = JdbcConnection.connectToDB();
        PreparedStatement pstmt = con.prepareStatement(query)) {

      pstmt.setInt(1, applicationId);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          application = new Application();
          application.setApplicantid(rs.getInt("applicantid"));
          application.setApplicationid(rs.getInt("applicationid"));
          application.setChannel(rs.getString("channel"));
          application.setCitizenid(rs.getInt("citizenid"));
          application.setDateofapplication(rs.getString("dateofapplication"));
          application.setStaffid(rs.getInt("staffid"));
          application.setKindofapplication(rs.getString("kindofapplication"));
          application.setStatusofapplication(rs.getString("statusofapplication"));
          application.setFromDraft(rs.getString("from_draft"));
        }
      }
    } catch (SQLException e) {
      LOG.error("DB query failed", e);
    }

    return application;
  }
}
