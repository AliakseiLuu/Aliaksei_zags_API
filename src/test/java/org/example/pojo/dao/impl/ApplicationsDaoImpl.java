package org.example.pojo.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.pojo.dao.ApplicationDao;
import org.example.pojo.dao.entities.Applications;
import org.example.utils.db.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationsDaoImpl implements ApplicationDao {

  private static final Logger LOG = LoggerFactory.getLogger(ApplicationsDaoImpl.class);
  private final Connection connection;

  public ApplicationsDaoImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Optional<Applications> findById(final Integer applicationId) {
    String query = "SELECT * FROM reg_office.applications WHERE applicationid = ?";

    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, applicationId);
      ResultSet rs = stmt.executeQuery();
      return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
    } catch (SQLException e) {
      throw new DataAccessException("Failed to find application by id=" + applicationId, e);
    }
  }

  private Applications mapRow(ResultSet rs) throws SQLException {
    return new Applications(
        rs.getInt("applicationid"),
        rs.getInt("citizenid"),
        rs.getInt("applicantid"),
        rs.getInt("staffid"),
        rs.getString("dateofapplication"),
        rs.getString("kindofapplication"),
        rs.getString("statusofapplication"),
        rs.getString("channel"),
        rs.getString("from_draft"));
  }

  @Override
  public List<Applications> findAll() {
    String query = "SELECT * FROM reg_office.applications";

    try (Statement stmt = connection.createStatement()) {
      ResultSet rs = stmt.executeQuery(query);
      List<Applications> applications = new ArrayList<>();

      while (rs.next()) {
        applications.add(mapRow(rs));
      }
      return applications;
    } catch (SQLException e) {
      throw new DataAccessException("Failed to find application", e);
    }
  }

  @Override
  public Applications save(Applications application) {
    return null;
  }

  @Override
  public Applications update(Applications application, String[] params) {
    return null;
  }

  @Override
  public void deleteById(Integer integer) {

  }
}
