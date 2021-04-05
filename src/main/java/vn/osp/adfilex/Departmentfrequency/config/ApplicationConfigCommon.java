package vn.osp.adfilex.Departmentfrequency.config;

import java.util.concurrent.Executor;
import org.modelmapper.ModelMapper;
/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nguyen_Toan
 */
@Configuration
@EnableScheduling
public class ApplicationConfigCommon {

  private static final Resource[] DEV_PROPERTIES =
      new ClassPathResource[] {new ClassPathResource("dev-env.properties"),};

  private static final Resource[] TEST_PROPERTIES =
      new ClassPathResource[] {new ClassPathResource("test-env.properties"),};

//  private static final Resource[] PROD_PROPERTIES =
//      new ClassPathResource[] {new ClassPathResource("prod-env.properties"),};
  
  private static final Resource[] PROD_PROPERTIES = 
    new FileSystemResource[] {new FileSystemResource("./config/prod-env.properties"),};

  @Profile("dev")
  public static class DevConfig {

    private DevConfig() {

    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
      PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
      pspc.setLocations(DEV_PROPERTIES);
      return pspc;
    }
  }

  @Profile("test")
  public static class TestConfig {

    private TestConfig() {

    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
      PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
      pspc.setLocations(TEST_PROPERTIES);

      return pspc;
    }
  }
  /*
  @Profile("prod")
  public static class ProdConfig {

    private ProdConfig() {

    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
      PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
      pspc.setLocations(PROD_PROPERTIES);
      return pspc;
    }
  }
  */

  @Component
  @Profile("prod")
  public static class ProdConfig {

    private ProdConfig() {
      //System.out.println("============================> COME ON HERE");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
      //System.out.println("=======================> Print here");
      PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
      //System.out.println("================> " + PROD_PROPERTIES[0].getFilename());
      pspc.setLocations(PROD_PROPERTIES);
      
      return pspc;
    }
  }

  @Bean
  public ModelMapper getModelMap() {
    return new ModelMapper();
  }

  @Bean
  public ObjectMapper getObjectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public TaskScheduler taskScheduler() {
    return new ConcurrentTaskScheduler();
  }

  @Bean
  public Executor taskExecutor() {
    return new SimpleAsyncTaskExecutor();
  }

}
