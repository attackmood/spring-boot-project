package com.cantaur.api.config.mybatis;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@org.springframework.context.annotation.Configuration
@MapperScan(basePackages = "com.cantaur.api.**.mapper"
        , sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfig {

    protected DataSource dataSource;

    @Value("${mybatis.mapper-locations-path}")
    protected String mapperLocationsPath;


    private static final String TYPE_ALIASES_PACKAGE = "com.cantaur.api.**.model";

    public MybatisConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }



    protected SqlSessionFactoryBean configureSqlSessionFactory() throws IOException {
        Configuration configuration = new Configuration();
        configuration.setLazyLoadingEnabled(Boolean.TRUE);
        configuration.setCacheEnabled(Boolean.FALSE);
        configuration.setUseGeneratedKeys(Boolean.TRUE);
        configuration.setDefaultExecutorType(ExecutorType.REUSE);
        configuration.setMapUnderscoreToCamelCase(Boolean.TRUE);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setCallSettersOnNulls(Boolean.TRUE);

        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        sessionFactoryBean.setMapperLocations(pathResolver.getResources(this.mapperLocationsPath));
        sessionFactoryBean.setConfiguration(configuration);

        return sessionFactoryBean;
    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        return configureSqlSessionFactory().getObject();
    }


}
