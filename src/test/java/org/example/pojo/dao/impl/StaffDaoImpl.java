package org.example.pojo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.example.pojo.dao.StaffDao;
import org.example.pojo.dao.entities.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaffDaoImpl implements StaffDao {

  private static final Logger LOG = LoggerFactory.getLogger(StaffDaoImpl.class);
  private final Connection connection;

  public StaffDaoImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Optional<Staff> findById(Integer staffid) {
    String query = "SELECT * FROM reg_office.staff WHERE staffid = ?";
    try (PreparedStatement pstm = connection.prepareStatement(query)) {
      pstm.setInt(1, staffid);
      ResultSet rs = pstm.executeQuery();
      if (rs.next()) {
        return Optional.of(
            new Staff(
                rs.getInt("staffid"),
                rs.getString("surname"),
                rs.getString("name"),
                rs.getString("middlename"),
                rs.getString("dateofbirth"),
                rs.getString("passportnumber"),
                rs.getString("phonenumber")));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return Optional.empty();
  }

  @Override
  public List<Staff> findAll() {
    return List.of();
  }

  @Override
  public Staff save(Staff staff) {
    return null;
  }

  @Override
  public Staff update(Staff staff, String[] params) {
    return null;
  }

  @Override
  public void deleteById(Integer staffid) {
    String query = "DELETE FROM reg_office.staff WHERE staffid = ?";
    try (PreparedStatement pstm = connection.prepareStatement(query)) {
      pstm.setInt(1, staffid);
      int deleted = pstm.executeUpdate();
      if (deleted == 0) {
        LOG.warn("Staff with id={} not found", staffid);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
