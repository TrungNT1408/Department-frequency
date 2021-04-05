package vn.osp.adfilex.Departmentfrequency.controller;


import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.osp.adfilex.Departmentfrequency.authencation.JwtTokenProvider;
import vn.osp.adfilex.Departmentfrequency.config.MessageSoucreConfig;
import vn.osp.adfilex.Departmentfrequency.core.AcountManagementService;
import vn.osp.adfilex.Departmentfrequency.core.InterferenceMapServices;
import vn.osp.adfilex.Departmentfrequency.core.UserSessionService;
import vn.osp.adfilex.Departmentfrequency.model.Circle;
import vn.osp.adfilex.Departmentfrequency.model.Location;
import vn.osp.adfilex.Departmentfrequency.model.account.UserUpdateDto;
import vn.osp.adfilex.Departmentfrequency.model.map.FilterMap;
import vn.osp.adfilex.Departmentfrequency.repository.UserRoleRepository;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;
import vn.osp.adfilex.Departmentfrequency.model.ResponseBody;

import javax.validation.Valid;

@RestController
@RequestMapping("${versionapi}/map")
public class InterferenceMapController {

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
    private InterferenceMapServices interferenceMapServices;

    @PostMapping("/getBTSs")
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
    public ResponseEntity<?> getBTSs(
            @ApiParam(value = "JSon Object để tạo tài khoản") @Valid @RequestBody FilterMap filter
    ) {
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(interferenceMapServices.getBTSs(filter)).build());
    }



    @PostMapping("/getBTS")
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
    public ResponseEntity<?> getBTS(
            @ApiParam(value = "Location") @Valid @RequestBody Location location
    ) throws Exception {
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(interferenceMapServices.getBTS(location)).build());
    }


    @PostMapping("/get-cell-freq-info")
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
    public ResponseEntity<?> getCellFreqInfo(
            @ApiParam(value = "cellId") @RequestParam String cellId
    ) {
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(interferenceMapServices.getCellFreqInfo(cellId)).build());
    }

    @PostMapping("/overview")
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
    public ResponseEntity<?> overview() {
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(interferenceMapServices.overview()).build());
    }

    @PostMapping("/bts/get-noise-cell")
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
    public ResponseEntity<?> btsGetNoiseCells() {
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(interferenceMapServices.btsGetNoiseCells()).build());
    }

    @PostMapping("/bts/update-cell-noise")
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
    public ResponseEntity<?> btsUpdateNoiseCell() {
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(interferenceMapServices.btsUpdateNoiseCell()).build());
    }

    @PostMapping("/cell-history")
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
    public ResponseEntity<?> getCellHistory(
            @ApiParam(value = "cellId") @RequestParam String cellId
    ) {
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(interferenceMapServices.getCellHistory(cellId)).build());
    }


    @PostMapping("/get-bts-in-circle")
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
    public ResponseEntity<?> getBtsInCircle(
            @ApiParam(value = "Location") @Valid @RequestBody Circle circle
    ) {
        return ResponseEntity.ok(ResponseBody.builder().message(messageSourceVi.getMessageVi(OK002))
                .body(interferenceMapServices.getBtsInCircle(circle)).build());
    }

}
