package com.quanlynhansu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quanlynhansu.connection.JDBCConnection;
import com.quanlynhansu.dto.UserDTO;
import com.quanlynhansu.model.User;

public class UserDAO {
	public List<User> findAll() {

		String query = "SELECT * FROM users";
		List<User> users = new ArrayList<User>();
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setAvatar("avatar");
				user.setRoleId(rs.getInt("role_id"));

				// Thêm user vào danh sách để hiển thị lên jsp
				users.add(user);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return users;

	}

	public List<UserDTO> findAllUserWithRoleName() {
		String query = "SELECT u.id, u.email, u.fullname, "
				+ "r.description FROM users u JOIN roles r ON u.role_id = r.id";

		List<UserDTO> users = new ArrayList<UserDTO>();
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				UserDTO user = new UserDTO();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setRoleName(rs.getString("description"));

				users.add(user);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return users;
	}

	public User findById(int id) {
		String query = "SELECT * FROM users WHERE id = ?";
		User user = new User();
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				// set thuộc tính cho user model
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFullname(rs.getString("fullname"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(rs.getInt("role_id"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return user;
	}

	public boolean insert(User user) {
		String query = "INSERT INTO Users(email, password, fullname, avatar, role_id)" + " VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = JDBCConnection.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(query);

			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRoleId());

			statement.executeUpdate();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	public boolean update(User user) {
		String query = "UPDATE users SET email = ?, fullname = ?, " + "avatar = ?, role_id = ? WHERE id = ?";
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getFullname());
			statement.setString(3, user.getAvatar());
			statement.setInt(4, user.getRoleId());
			statement.setInt(5, user.getId());

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteById(int id) {

		String query = "DELETE FROM user WHERE id = ?";

		// Tao ket noi db
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
}
