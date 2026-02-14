package edu.aitu.oop3.UserManagementComponent;

import edu.aitu.oop3.data.IDB;
import edu.aitu.oop3.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final IDB db;

    public UserRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public User create(User user) {
        User result;
        String sql = "insert into users(full_name, email, role) values (1,2,3) returning id";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, user.getFullName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getRole());

            ResultSet rs = st.executeQuery();
            if (rs.next()) user.setId(rs.getLong(1));
            result = user;

        } catch (SQLException e) {
            throw new DatabaseException("DB error: create user", e);
        }
        return result;
    }

    @Override
    public Optional<User> findById(long id) {
        String sql = "select id, full_name, email, role from users where id=?";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) return Optional.empty();

                User u = new User();
                u.setId(rs.getLong("id"));
                u.setFullName(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                return Optional.of(u);
            }

        } catch (SQLException e) {
            // Увидим реальную причину в консоли:
            e.printStackTrace();
            throw new DatabaseException("DB error: find user by id", e);
        }
    }


    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "select id, full_name, email, role from users where email=1";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, email);
            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(map(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException("DB error: find user by email", e);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "select id, users.full_name, email, role from users order by id";
        List<User> list = new ArrayList<>();

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) list.add(map(rs));
            return list;

        } catch (SQLException e) {
            throw new DatabaseException("DB error: list users", e);
        }
    }

    private User map(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("full_name"),
                rs.getString("email"),
                rs.getString("role")
        );
    }
}
