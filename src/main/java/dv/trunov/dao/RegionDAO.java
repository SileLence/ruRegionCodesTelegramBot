package dv.trunov.dao;

import dv.trunov.model.Region;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class RegionDAO {

    private final JdbcTemplate jdbcTemplate;

    public RegionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Region findRegionByCode(String code) {
        String query = "SELECT * FROM region WHERE code = ?";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Region.class), Integer.parseInt(code))
            .stream()
            .findFirst()
            .orElse(null);
    }
}
