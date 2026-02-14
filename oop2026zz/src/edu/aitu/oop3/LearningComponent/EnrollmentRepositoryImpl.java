package edu.aitu.oop3.LearningComponent;
import edu.aitu.oop3.data.IDB;
import edu.aitu.oop3.exceptions.DatabaseException;

import java.sql.*;
import java.util.Optional;

public class EnrollmentRepositoryImpl implements EnrollmentRepository {
    private final IDB db;

    public EnrollmentRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public Enrollment enroll(long userId, long courseId) {
        String sql = """
            insert into enrollments(user_id, course_id)
            values (?, ?) returning id
            """;
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, userId);
            st.setLong(2, courseId);

            ResultSet rs = st.executeQuery();
            rs.next();
            return new Enrollment(rs.getLong(1), userId, courseId);

        } catch (SQLException e) {
            throw new DatabaseException("Enroll failed", e);
        }
    }

    @Override
    public Optional<Enrollment> find(long userId, long courseId) {
        String sql = "select id from enrollments where user_id=? and course_id=?";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setLong(1, userId);
            st.setLong(2, courseId);

            ResultSet rs = st.executeQuery();
            if (!rs.next()) return Optional.empty();
            return Optional.of(new Enrollment(rs.getLong(1), userId, courseId));

        } catch (SQLException e) {
            throw new DatabaseException("Find enrollment failed", e);
        }
    }
}
