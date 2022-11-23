package com.project.smallbeginjava11;
;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SmallBeginJava11Application {


    public static void main(String[] args) {
        SpringApplication.run(SmallBeginJava11Application.class, args);
    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        System.out.println(dataSource);
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/mappers/*.xml"));
//
//        Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpat:mybatis/mybatis-config.xml");
//        sessionFactory.setConfigLocation(myBatisConfig);
//
//        return sessionFactory.getObject();
//    }

}
