package dv.trunov.config;

import dv.trunov.bot.Bot;
import dv.trunov.dao.RegionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("dv.trunov")
@PropertySource("classpath:application.properties")
public class SpringConfig {

    @Bean
    public BotConfig botConfig() {
        return new BotConfig();
    }

    @Bean
    public Bot regionCodesBot() {
        return new Bot(botConfig(), regionDAO());
    }

    @Bean
    public BotInitializer botInitializer() {
        return new BotInitializer(regionCodesBot());
    }

    @Bean
    public RegionDAO regionDAO() {
        return new RegionDAO(jdbcTemplate());
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManager = new DriverManagerDataSource(
            "jdbc:postgresql://localhost:5432/region_code_bot",
            "postgres",
            "postgres");
        driverManager.setDriverClassName("org.postgresql.Driver");
        return driverManager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
