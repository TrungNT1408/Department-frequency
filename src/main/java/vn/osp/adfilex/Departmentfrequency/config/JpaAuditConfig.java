package vn.osp.adfilex.Departmentfrequency.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vn.osp.adfilex.Departmentfrequency.config.impl.AuditorAwareImpl;

/**
 * 
 * vn.osp.adfilex.config 
 * @author LuongTN : 9:25:23 AM
 * 
 * 
 * JpaAuditConfig.java
 */
/* CONFIG AUDITING */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
public class JpaAuditConfig {

  @Bean
  public AuditorAware<String> auditorAware() {
    return new AuditorAwareImpl();
  }

}
