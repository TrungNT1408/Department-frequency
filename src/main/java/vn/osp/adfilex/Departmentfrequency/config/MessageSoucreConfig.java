/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.config;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.lang.Nullable;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Nguyen_Toan
 */
@Configuration
public class MessageSoucreConfig {

  /**
   * Bean read message properties
   *
   * @return
   */
  @Bean
  public MessageSourceEn messageSourceEn() {

    return new MessageSourceEn();
  }

  /**
   * Bean read message properties
   *
   * @return
   */
  @Bean
  public MessageSourceVi messageSourceVi() {

    return new MessageSourceVi();
  }

  /**
   * Bean to custom message read from message properties
   *
   * @return
   */
  @Bean
  public LocalValidatorFactoryBean getValidator() {
    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSourceEn().getMessageSource());
    return bean;
  }

  @Getter
  @Setter
  public class MessageSourceEn {

    private MessageSource messageSource;

    public MessageSourceEn() {
      ReloadableResourceBundleMessageSource bundleMessageSource =
          new ReloadableResourceBundleMessageSource();
      bundleMessageSource.setBasename("classpath:messages_en");
      bundleMessageSource.setDefaultEncoding("UTF-8");
      this.messageSource = bundleMessageSource;
    }

    public String getMessageEn(String codeMsg) {
      return messageSource.getMessage(codeMsg, null, Locale.ENGLISH);
    }

    public String getMessageEn(String codeMsg, @Nullable Object[] args) {
      return messageSource.getMessage(codeMsg, args, Locale.ENGLISH);
    }
  }

  @Getter
  @Setter
  public class MessageSourceVi {

    private MessageSource messageSource;

    public MessageSourceVi() {
      ReloadableResourceBundleMessageSource bundleMessageSource =
          new ReloadableResourceBundleMessageSource();
      bundleMessageSource.setBasename("classpath:messages_vi");
      bundleMessageSource.setDefaultEncoding("UTF-8");
      this.messageSource = bundleMessageSource;
    }

    public String getMessageVi(String codeMsg) {
      return messageSource.getMessage(codeMsg, null, new Locale("vi", "VN"));
    }

    public String getMessageVi(String codeMsg, @Nullable Object[] args) {
      return messageSource.getMessage(codeMsg, args, new Locale("vi", "VN"));
    }
  }
}
