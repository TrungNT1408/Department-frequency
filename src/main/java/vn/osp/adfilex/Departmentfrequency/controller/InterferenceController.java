package vn.osp.adfilex.Departmentfrequency.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import vn.osp.adfilex.Departmentfrequency.authencation.JwtTokenProvider;
import vn.osp.adfilex.Departmentfrequency.config.MessageSoucreConfig;
import vn.osp.adfilex.Departmentfrequency.core.AcountManagementService;
import vn.osp.adfilex.Departmentfrequency.core.InterferenceServices;
import vn.osp.adfilex.Departmentfrequency.core.UserSessionService;
import vn.osp.adfilex.Departmentfrequency.model.InterferenceDTO;
import vn.osp.adfilex.Departmentfrequency.model.InterferenceFilterDTO;
import vn.osp.adfilex.Departmentfrequency.model.ResponseBody;
import vn.osp.adfilex.Departmentfrequency.model.account.UserUpdateDto;
import vn.osp.adfilex.Departmentfrequency.model.map.FilterMap;
import vn.osp.adfilex.Departmentfrequency.repository.UserRoleRepository;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("${versionapi}/interference")
public class InterferenceController {

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
    private InterferenceServices interferenceServices;


    @PostMapping("/checkLicense")
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
    public ResponseEntity<?> isValidLicense(
            @ApiParam(value = "Thong tin kiem tra license") @Valid @RequestBody InterferenceDTO interferenceDTO
    ) {
        return ResponseEntity.ok(vn.osp.adfilex.Departmentfrequency.model.ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(interferenceServices.isValidLicense(interferenceDTO)).build());
    }



    @PostMapping("/register")
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
    public ResponseEntity<?> register(
            @ApiParam(value = "Thong tin khai nhieu") @Valid @RequestBody InterferenceDTO interferenceDTO
    ) {
        return ResponseEntity.ok(vn.osp.adfilex.Departmentfrequency.model.ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(interferenceServices.register(interferenceDTO)).build());
    }


    @PostMapping("/getInterferences")
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
    public ResponseEntity<?> getInterferences(
            @ApiParam(value = "Thong tin tim kiem") @Valid @RequestBody InterferenceFilterDTO interferenceFilterDTO
    ) {
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(interferenceServices.getInterferences(interferenceFilterDTO)).build());
    }

    @PostMapping("/getInterference")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API lay thong tin khai nhieu",
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
    public ResponseEntity<?> getInterference(
            @ApiParam(value = "cell noise id") @RequestParam long id
    ) {
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(interferenceServices.getInterference(id)).build());
    }

    @PostMapping("/update-interference")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API cap nhat thong tin nhieu",
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
    public ResponseEntity<?> updateInterference(
            @ApiParam(value = "Thong tin tim kiem") @Valid @RequestBody InterferenceDTO interferenceDTO
    ) {
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(interferenceServices.updateInterference(interferenceDTO)).build());
    }
}
