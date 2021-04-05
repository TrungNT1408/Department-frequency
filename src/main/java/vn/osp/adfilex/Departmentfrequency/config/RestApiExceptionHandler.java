package vn.osp.adfilex.Departmentfrequency.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vn.osp.adfilex.Departmentfrequency.config.MessageSoucreConfig.MessageSourceVi;
import vn.osp.adfilex.Departmentfrequency.core.LoggerService;
import vn.osp.adfilex.Departmentfrequency.entity.other.LoggerRegister;
import vn.osp.adfilex.Departmentfrequency.exception.ExpiredResourcesDateException;
import vn.osp.adfilex.Departmentfrequency.exception.FileIOException;
import vn.osp.adfilex.Departmentfrequency.exception.LoginFailedException;
import vn.osp.adfilex.Departmentfrequency.exception.NotAllowCallingException;
import vn.osp.adfilex.Departmentfrequency.exception.PermissionException;
import vn.osp.adfilex.Departmentfrequency.exception.ProxyAuthenticationRequired;
import vn.osp.adfilex.Departmentfrequency.exception.ResourceNotFoundException;
import vn.osp.adfilex.Departmentfrequency.model.ResponseBody;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;


/**
 * 
 * vn.osp.adfilex.config 
 * @author LuongTN : 9:25:29 AM
 * 
 * 
 * RestApiExceptionHandler.java
 */
@ControllerAdvice
@Slf4j
public class RestApiExceptionHandler {

  private final String REQUEST_FROM = "    Exception from request :::::::::: ";
  private final String USER_AGENT = "User-Agent";

  @Autowired
  private MessageSourceVi messageSourceVi;

  @Autowired
  @Qualifier("LoggerServiceImpl_Main")
  private LoggerService callLogService;

  @ExceptionHandler({ResourceNotFoundException.class, UsernameNotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)

  public ResponseEntity<?> resourceNotFoundException(Exception ex, WebRequest request) {
    loggingAdvice(ex, request);
            return new ResponseEntity<>(ResponseBody.builder().status(404).message(ex.getMessage())
        .time(new Date()).build(), HttpStatus.NOT_FOUND);
//    return new ResponseEntity<>(ErrorDetail.builder().errorCode("404").message(ex.getMessage())
//        .timestamp(new Date()).build(), HttpStatus.NOT_FOUND);
  }

  /**
   * @param ex
   * @param request
   */
  @Transactional
  private void loggingAdvice(Exception ex, WebRequest request) {

    List<LoggerRegister> callLoggers = new ArrayList<>();
    try {

      HttpServletRequest httpServletRequest = ((ServletWebRequest) request).getRequest();

      String information =
          new StringBuilder(REQUEST_FROM.toUpperCase() + request.getUserPrincipal().getName()
              + StringUtils.COLON + httpServletRequest.getRemoteAddr() + StringUtils.COLON
              + httpServletRequest.getLocalPort() + httpServletRequest.getRequestURI()).toString();

      log.info(information);

      log.info(httpServletRequest.getHeader(USER_AGENT));

      log.error(
          new StringBuilder(ex.getClass().getSimpleName() + StringUtils.COLON + ex.getMessage())
              .toString());

      callLoggers.add(LoggerRegister.builder().codeName("API").codeId(111L)
          .text(new StringBuilder(ex.getClass().getSimpleName() + StringUtils.COLON
              + ex.getMessage() + StringUtils.COMMA + information).toString())
          .build());

    } catch (Exception e) {

      log.error(
          new StringBuilder(ex.getClass().getSimpleName() + StringUtils.COLON + ex.getMessage())
              .toString());
    }

    callLogService.createAll(callLoggers);
  }

  @ExceptionHandler({Exception.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
    loggingAdvice(ex, request);
    
    return new ResponseEntity<>(ResponseBody.builder().status(500).message(ex.getMessage())
        .time(new Date()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
//    return new ResponseEntity<>(ErrorDetail.builder().errorCode("500")
//        .message(messageSourceVi.getMessageVi("ER500")).timestamp(new Date()).build(),
//        HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @ExceptionHandler({ProxyAuthenticationRequired.class})
  @ResponseStatus(value = HttpStatus.PROXY_AUTHENTICATION_REQUIRED)
  public ResponseEntity<?> tokenFailedException(ProxyAuthenticationRequired ex, WebRequest request) {

    loggingAdvice(ex, request);
    
    return new ResponseEntity<>(ResponseBody.builder().status(407).message(ex.getMessage())
        .time(new Date()).build(), HttpStatus.UNAUTHORIZED);
//    return new ResponseEntity<>(ErrorDetail.builder().errorCode("401").message(ex.getMessage())
//        .timestamp(new Date()).build(), HttpStatus.UNAUTHORIZED);
  }


  @ExceptionHandler({LoginFailedException.class})
  @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
  public ResponseEntity<?> loginFailedException(LoginFailedException ex, WebRequest request) {

    loggingAdvice(ex, request);
    
    return new ResponseEntity<>(ResponseBody.builder().status(401).message(ex.getMessage())
        .time(new Date()).build(), HttpStatus.UNAUTHORIZED);
//    return new ResponseEntity<>(ErrorDetail.builder().errorCode("401").message(ex.getMessage())
//        .timestamp(new Date()).build(), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler({ExpiredResourcesDateException.class, PermissionException.class,
      IllegalArgumentException.class, SignatureException.class, MalformedJwtException.class,
      UnsupportedJwtException.class, ExpiredJwtException.class})
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  public ResponseEntity<?> permissionException(Exception ex, WebRequest request) {

    loggingAdvice(ex, request);
    
    return new ResponseEntity<>(ResponseBody.builder().status(403).message(ex.getMessage())
        .time(new Date()).build(), HttpStatus.FORBIDDEN);
//    return new ResponseEntity<>(ErrorDetail.builder().errorCode("403").message(ex.getMessage())
//        .timestamp(new Date()).build(), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler({MissingRequestHeaderException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseEntity<?> headMissException(Exception ex, WebRequest request) {
    loggingAdvice(ex, request);
    
    return new ResponseEntity<>(ResponseBody.builder().status(400).message(ex.getMessage())
        .time(new Date()).build(), HttpStatus.BAD_REQUEST);
//    return new ResponseEntity<>(ErrorDetail.builder().errorCode("400").message(ex.getMessage())
//        .timestamp(new Date()).build(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({NotAllowCallingException.class,FileIOException.class})
  @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
  public ResponseEntity<?> notImplementedException(Exception ex, WebRequest request) {
    loggingAdvice(ex, request);
    return new ResponseEntity<>(ResponseBody.builder().status(501).message(ex.getMessage())
        .time(new Date()).build(), HttpStatus.NOT_IMPLEMENTED);
//    return new ResponseEntity<>(ErrorDetail.builder().errorCode("501").message(ex.getMessage())
//        .timestamp(new Date()).build(), HttpStatus.NOT_IMPLEMENTED);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex,
      WebRequest request) {
    loggingAdvice(ex, request);
    StringBuilder buffer = new StringBuilder();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      buffer.append(/* error.getField() + ": " + */ error.getDefaultMessage());
    }
    return new ResponseEntity<>(ResponseBody.builder().status(400).message(buffer.toString())
        .time(new Date()).build(), HttpStatus.BAD_REQUEST);
//    return new ResponseEntity<>(ErrorDetail.builder().errorCode("400").message(buffer.toString())
//        .timestamp(new Date()).build(), HttpStatus.BAD_REQUEST);
  }

}
