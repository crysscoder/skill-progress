package dev.cryst.skillprogress.repository.skill;

import lombok.AllArgsConstructor;
import dev.cryst.skillprogress.dto.Skill;
import dev.cryst.skillprogress.dto.User;
import dev.cryst.skillprogress.repository.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@AllArgsConstructor
public class DatabaseSkillRepository implements SkillRepository {
    private final Database dataSource;
    private final Executor executor = Executors.newFixedThreadPool(3);

    @Override
    public CompletableFuture<User> add(User entity) {
        return CompletableFuture.supplyAsync(() -> {
            final String sql = "INSERT INTO skills (name, class, level, progress) VALUES (?,?, ?, ?)";

            try (final Connection connection = dataSource.getDs().getConnection();
                 final PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, entity.name());
                ps.setString(2, entity.className().name());
                ps.setInt(3, entity.level());
                ps.setDouble(4, entity.progress());

                ps.executeUpdate();

                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<Void> delete(User entity) {
        return CompletableFuture.runAsync(() -> {
            final String sql = "DELETE FROM skills WHERE name = ?";
            try (final Connection connection = dataSource.getDs().getConnection();
                 final PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, entity.name());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<Void> update(User entity) {
        return CompletableFuture.runAsync(() -> {
            final String sql = """
                    UPDATE skills 
                    SET className = ?, level = ?, progress = ?
                    WHERE name = ?
                    """;

            try (final Connection connection = dataSource.getDs().getConnection();
                 final PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, entity.className().name());
                ps.setInt(2, entity.level());
                ps.setDouble(3, entity.progress());
                ps.setString(4, entity.name());

                ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }, executor);
    }

    @Override
    public CompletableFuture<User> getPlayer(String name) {
        return CompletableFuture.supplyAsync(() -> {
            final String sql = "SELECT * FROM skills WHERE name = ?";

            try (final Connection connection = dataSource.getDs().getConnection();
                 final PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                try (final ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new User(
                                name,
                                Skill.valueOf(rs.getString("class")),
                                rs.getInt("level"),
                                rs.getDouble("progress")
                        );
                    }
                }

                return null;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }
}
