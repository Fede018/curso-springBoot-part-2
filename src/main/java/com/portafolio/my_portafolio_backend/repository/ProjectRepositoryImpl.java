package com.portafolio.my_portafolio_backend.repository;

import com.portafolio.my_portafolio_backend.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements IProjectRepository{

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Project> projectRowMapper = (rs, rowNum) -> {
        Project project = new Project();
        project.setId(rs.getLong("id"));
        project.setTitle(rs.getString("title"));
        project.setDescription(rs.getString("description"));
        project.setImageUrl(rs.getString("image_url"));
        project.setProjectUrl(rs.getString("project_url"));
        project.setPersonalInfoId(rs.getLong("personal_info_id"));
        return project;
    };

    @Override
    public List<Project> findAll() {
        return jdbcTemplate.query("SELECT * FROM projects", projectRowMapper);
    }

    @Override
    public Optional<Project> findbyId(Long id) {
        String sql = "SELECT * FROM projects WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, projectRowMapper,id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public Project save(Project project) {
        if (project.getId() == null) {
            // INSERT
            String sql = "INSERT INTO projects (title, description, image_url, project_url, personal_info_id) " +
                     "VALUES (?, ?, ?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql,
                        new String[]{"id"});
                ps.setString(1, project.getTitle());
                ps.setString(2, project.getDescription());
                ps.setObject(3, project.getImageUrl());
                ps.setObject(4, project.getProjectUrl());
                ps.setLong(5, project.getPersonalInfoId());
                return ps;
            }, keyHolder);

            project.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            // UPDATE
            String sql = "UPDATE projects SET title = ?, description = ?, image_url = ?, project_url = ?, " +
                    "personal_info_id = ? WHERE id = ?";
            jdbcTemplate.update(sql,
                    project.getTitle(),
                    project.getDescription(),
                    project.getImageUrl(),
                    project.getProjectUrl(),
                    project.getPersonalInfoId(),
                    project.getId());
        }
        return project;
    }

    @Override
    public void deletebyId(Long id) {
        jdbcTemplate.update("DELETE FROM projects WHERE id = ?", id);

    }
}
