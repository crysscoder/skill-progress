package dev.cryst.skillprogress.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import dev.cryst.skillprogress.configuration.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {
    private final DatabaseConfiguration config;
    @Getter
    private HikariDataSource ds;


    public Database(DatabaseConfiguration config) {
        this.config = config;
        create();
        hikariconnect();
        createTables();
    }


    private void create() {
        final String sql = "jdbc:mysql://" + config.host() + ":" + config.port() +
                "/?useSSL=false&serverTimezone=UTC";

        try (Connection conn = java.sql.DriverManager.getConnection(
                sql, config.user(), config.password());
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
                    "CREATE DATABASE IF NOT EXISTS `" + config.database() + "` " +
                            "CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
            );
        } catch (SQLException ex) {
            throw new RuntimeException("Failed to create database", ex);
        }
    }

    private void hikariconnect() {
        final String url = "jdbc:mysql://" + config.host() + ":" + config.port() +
                "/" + config.database() +
                "?useSSL=false" +
                "&allowPublicKeyRetrieval=true" +
                "&useUnicode=true" +
                "&characterEncoding=UTF-8" +
                "&serverTimezone=UTC";

        HikariConfig hikari = new HikariConfig();
        hikari.setJdbcUrl(url);
        hikari.setUsername(config.user());
        hikari.setPassword(config.password());

        hikari.setMaximumPoolSize(10);
        hikari.setMinimumIdle(2);
        hikari.setPoolName("SkillProgressPlugin");

        ds = new HikariDataSource(hikari);
    }

    private void createTables() {
        final String skill = """
                CREATE TABLE IF NOT EXISTS `skills` (
                    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                
                    name VARCHAR(36)  NOT NULL,
                    class VARCHAR(18) NOT NULL,
                    level INTEGER NOT NULL,
                    progress INTEGER)   
                """;
        final String tasks = """
                CREATE TABLE IF NOT EXISTS `tasks` (
                task_id VARCHAR(36) PRIMARY KEY,
                skill_id BIGINT NOT NULL,
                data TEXT NOT NULL,
                
                FOREIGN KEY (skill_id) REFERENCES skills(id) ON DELETE CASCADE);
                """;


        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(skill);
            stmt.executeUpdate(tasks);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to create tables", e);
        }
    }

    public void shutdown() {
        ds.close();
    }

}
