package vn.osp.adfilex.Departmentfrequency.controller;


import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.osp.adfilex.Departmentfrequency.authencation.JwtTokenProvider;
import vn.osp.adfilex.Departmentfrequency.config.MessageSoucreConfig;
import vn.osp.adfilex.Departmentfrequency.core.AcountManagementService;
import vn.osp.adfilex.Departmentfrequency.core.InterferenceMapServices;
import vn.osp.adfilex.Departmentfrequency.core.ReportServices;
import vn.osp.adfilex.Departmentfrequency.core.UserSessionService;
import vn.osp.adfilex.Departmentfrequency.model.InterferenceFilterDTO;
import vn.osp.adfilex.Departmentfrequency.model.ReportFilterDTO;
import vn.osp.adfilex.Departmentfrequency.model.ResponseBody;
import vn.osp.adfilex.Departmentfrequency.model.account.UserUpdateDto;
import vn.osp.adfilex.Departmentfrequency.repository.UserRoleRepository;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("${versionapi}/report")
public class ReportController {

    @Autowired
    @Qualifier("AcountManagementService_Main")
    private AcountManagementService acountManagement;

    @Autowired
    private UserSessionService userSessionService;

    @Autowired
    private MessageSoucreConfig.MessageSourceVi messageSourceVi;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final String OK002 = "OK002";
    private final String OK005 = "OK005";
    private final String ER002 = "ER002";

    @Autowired
    private ReportServices reportServices;


    @PostMapping("/region-report")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp load thông tin 1 user để sửa tài khoản",
            value = "(Account Management Page) API Get Information A User ",
            authorizations = {
                    @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data Response Retrieved.",
                    response = UserUpdateDto.class)
            ,
            @ApiResponse(code = 500, message = "Internal Server Error.")
            ,
            @ApiResponse(code = 400, message = "Bad Request cause data input.")
            ,
            @ApiResponse(code = 404, message = "Not found.")
            ,
            @ApiResponse(code = 403, message = "Access Denied Or Any More.")
            ,
            @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> getRegionReport(
            @ApiParam(value = "Thong tin tim kiem") @Valid @RequestBody ReportFilterDTO reportFilterDTO
    ) {
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(reportServices.getRegionReport(reportFilterDTO)).build());
    }


    @PostMapping("/telco-report")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp load thông tin 1 user để sửa tài khoản",
            value = "(Account Management Page) API Get Information A User ",
            authorizations = {
                    @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data Response Retrieved.",
                    response = UserUpdateDto.class)
            ,
            @ApiResponse(code = 500, message = "Internal Server Error.")
            ,
            @ApiResponse(code = 400, message = "Bad Request cause data input.")
            ,
            @ApiResponse(code = 404, message = "Not found.")
            ,
            @ApiResponse(code = 403, message = "Access Denied Or Any More.")
            ,
            @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> getTelcoReport(
            @ApiParam(value = "Thong tin tim kiem") @Valid @RequestBody ReportFilterDTO reportFilterDTO
    ) {
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(reportServices.getRegionReport(reportFilterDTO)).build());
    }


    @PostMapping("/history")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp load thông tin 1 user để sửa tài khoản",
            value = "(Account Management Page) API Get Information A User ",
            authorizations = {
                    @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data Response Retrieved.",
                    response = UserUpdateDto.class)
            ,
            @ApiResponse(code = 500, message = "Internal Server Error.")
            ,
            @ApiResponse(code = 400, message = "Bad Request cause data input.")
            ,
            @ApiResponse(code = 404, message = "Not found.")
            ,
            @ApiResponse(code = 403, message = "Access Denied Or Any More.")
            ,
            @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> getHistory(
            @ApiParam(value = "Thong tin tim kiem") @Valid @RequestBody ReportFilterDTO reportFilterDTO
    ) {
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(reportServices.getRegionReport(reportFilterDTO)).build());
    }
}
