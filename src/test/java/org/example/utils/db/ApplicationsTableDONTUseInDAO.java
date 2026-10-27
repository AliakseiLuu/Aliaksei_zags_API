package org.example.utils.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.example.pojo.dao.entities.Applications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationsTableDONTUseInDAO {
  private static final Logger LOG = LoggerFactory.getLogger(ApplicationsTableDONTUseInDAO.class);

  public static Applications getApplicationById(final Integer applicationId) {
    String query = "SELECT * FROM reg_office.applications WHERE applicationid = ?";
    Applications application = null;

    try (Connection con = JdbcConnectionDONTUseInDAO.connectToDB();
         PreparedStatement pstmt = con.prepareStatement(query)) {

      pstmt.setInt(1, applicationId);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          application = new Applications();
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
