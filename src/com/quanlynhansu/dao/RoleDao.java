package com.quanlynhansu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quanlynhansu.connection.JDBCConnection;
import com.quanlynhansu.model.Role;

public class RoleDAO {
	public List<Role> findAll() {
		String query = "SELECT * FROM roles";
		List<Role> roles = new ArrayList<Role>();
		// Buoc 1: Mo ket noi den database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Buoc 2: Truy van du lieu tu database

			// Tao doi tuong PreparedStatement de thuc hien truy van du lieu
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");

				Role role = new Role(id, name, description);
				roles.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return roles;
	}

	public Role findById(int id) {
		String query = "SELECT * FROM roles WHERE id = ?";

		Role role = null;
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao ca truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");

				role = new Role(id, name, description);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return role;
	}

	public boolean deleteById(int id) {

		String query = "DELETE FROM roles WHERE id = ?";

		// Tao ket noi database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao cau truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			// Chay cau truy van
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insert(Role role) {
		String query = "INSERT INTO roles(name, description) VALUES (?, ?)";
		// Tao ket noi database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao cau truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Role role) {
		String query = "UPDATE roles SET name = ?, description = ? WHERE id = ?";
		// Tao ket noi database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao cau truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			statement.setInt(3, role.getId());

			// Chay cau truy van
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
