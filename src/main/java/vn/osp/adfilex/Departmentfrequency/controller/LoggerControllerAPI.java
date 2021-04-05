/**
 * Welcome developer friend. LuongTN ospAdfilex-smartTeleSale-controller CallLogController.java
 * 5:44:49 PM
 */
package vn.osp.adfilex.Departmentfrequency.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import vn.osp.adfilex.Departmentfrequency.authencation.JwtAuthenticationResponse;
import vn.osp.adfilex.Departmentfrequency.config.MessageSoucreConfig.MessageSourceVi;
import vn.osp.adfilex.Departmentfrequency.constants.ApplicationConstant;
import vn.osp.adfilex.Departmentfrequency.core.LoggerService;
import vn.osp.adfilex.Departmentfrequency.entity.other.LoggerRegister;
import vn.osp.adfilex.Departmentfrequency.entity.other.LoggerSearch;
import vn.osp.adfilex.Departmentfrequency.exception.PermissionException;
import vn.osp.adfilex.Departmentfrequency.model.ResponseBody;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;


/**
 *
 * @author Nguyen_Toan
 */
@RestController
@RequestMapping("${versionapi}/call-log")
@Api
@Validated
public class LoggerControllerAPI {

  @Autowired
  @Qualifier("LoggerServiceImpl_Main")
  private LoggerService loggerService;

  @Autowired
  private MessageSourceVi messageSourceVi;

  @PostConstruct
  @Transactional
  public void init() {
    loggerService.intTable();
  }

  @PostMapping
  @ResponseStatus(value = HttpStatus.OK)
  @ApiOperation(notes = "API sẽ được gọi trong trường hợp log lỗi .",
      value = "(Account Management Page) API Create list Log save to DB",
      authorizations = {@Authorization(value = StringUtils.API_KEY)})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Data Response Retrieved.",
          response = JwtAuthenticationResponse.class),
      @ApiResponse(code = 500, message = "Internal Server Error."),
      @ApiResponse(code = 400, message = "Bad Request cause data input."),
      @ApiResponse(code = 404, message = "Not found."),
      @ApiResponse(code = 403, message = "Access Denied Or Any More."),
      @ApiResponse(code = 401, message = "Unauthorized.")})
  public ResponseEntity<?> saveLog(
      @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME,
          required = true) String token,
      @ApiParam(
          value = "Danh sách bản ghi cần lưu ") @RequestBody @Valid List<LoggerRegister> callLoggers)
      throws PermissionException {

    loggerService.createAll(callLoggers);

    return ResponseEntity
        .ok(ResponseBody.builder().message(messageSourceVi.getMessageVi("OK002")).build());
  }

  @DeleteMapping
  @ResponseStatus(value = HttpStatus.OK)
  @ApiOperation(notes = "API sẽ được gọi trong trường hợp xóa lịch sử cuộc gọi.",
      value = "(Account Management Page) API Delete Call Log ",
      authorizations = {@Authorization(value = StringUtils.API_KEY)})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Data Response Retrieved.",
          response = JwtAuthenticationResponse.class),
      @ApiResponse(code = 500, message = "Internal Server Error."),
      @ApiResponse(code = 400, message = "Bad Request cause data input."),
      @ApiResponse(code = 404, message = "Not found."),
      @ApiResponse(code = 403, message = "Access Denied Or Any More."),
      @ApiResponse(code = 401, message = "Unauthorized.")})
  public ResponseEntity<?> deleteLogger(
      @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME,
          required = true) String token,
      @ApiParam(
          value = "Danh sách id bản ghi cần xóa") @RequestBody @Valid List<Long> callLoggersId)
      throws PermissionException {

    return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi("OK002"))
        .body(loggerService.deleteAll(callLoggersId)).build());
  }


  @PostMapping("/search")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiOperation(notes = "API sẽ được gọi trong trường hợp lấy lịch sử cuộc gọi.",
      value = "(Account Management Page) API Search Call Log ",
      authorizations = {@Authorization(value = StringUtils.API_KEY)})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Data Response Retrieved.",
          response = JwtAuthenticationResponse.class),
      @ApiResponse(code = 500, message = "Internal Server Error."),
      @ApiResponse(code = 400, message = "Bad Request cause data input."),
      @ApiResponse(code = 404, message = "Not found."),
      @ApiResponse(code = 403, message = "Access Denied Or Any More."),
      @ApiResponse(code = 401, message = "Unauthorized.")})
  public ResponseEntity<?> searchLogger(
      @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME,
          required = true) String token,
      @ApiParam(value = "Số trang") @RequestParam(name = "page", defaultValue = "0") int page,
      @ApiParam(value = "Số bản ghi / trang") @RequestParam(name = "size",
          defaultValue = "10") int size,
      @ApiParam(value = "Sắp xếp theo các thuộc tính", defaultValue = "call_log_id") @RequestParam(
          name = "properties", defaultValue = "call_log_id",
          required = true) String sortByProperties,
      @ApiParam(value = "Loại sắp xếp ", defaultValue = "ASC") @RequestParam(name = "sortBy",
          defaultValue = "ASC") String sortBy,
      @ApiParam(
          value = "Tìm kiếm theo các tiêu chí ") @RequestBody @Valid LoggerSearch callLogger)
      throws PermissionException {


    return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi("OK002"))
        .body(loggerService.searchCallLogger(page, size, sortByProperties, sortBy, callLogger))
        .build());
  }
}
