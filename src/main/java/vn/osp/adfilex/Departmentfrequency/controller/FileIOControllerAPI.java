/**
 * Welcome developer friend. PC ospAdfilex-smartTeleSale-controller FileIOControllerAPI.java 5:08:36
 * PM
 */
package vn.osp.adfilex.Departmentfrequency.controller;

import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import org.springframework.web.multipart.MultipartFile;
import vn.osp.adfilex.Departmentfrequency.config.MessageSoucreConfig.MessageSourceVi;
import vn.osp.adfilex.Departmentfrequency.constants.ApplicationConstant;
import vn.osp.adfilex.Departmentfrequency.core.FileIOService;
import vn.osp.adfilex.Departmentfrequency.model.ResponseBody;
import vn.osp.adfilex.Departmentfrequency.utils.FileUtils;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.util.List;

/**
 * @author Nguyen_Toan
 */
@RestController
@RequestMapping("${versionapi}/files")
@Api
@Validated
public class FileIOControllerAPI {

    @Autowired
    private FileIOService fileIOService;

    @Autowired
    private MessageSourceVi messageSourceVi;

    private static Logger LOG = LoggerFactory.getLogger(FileIOControllerAPI.class);

//	@PostMapping(value = "/uploads")
//	@ResponseStatus(value = HttpStatus.OK)
//	@ApiOperation(notes = "API sẽ được gọi trong trường hợp upload file lên server ", value = "(File Management Page) API File Management Page ", authorizations = {
//			@Authorization(value = StringUtils.API_KEY) })
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Data Response Retrieved."),
//			@ApiResponse(code = 500, message = "Internal Server Error."),
//			@ApiResponse(code = 400, message = "Bad Request cause data input."),
//			@ApiResponse(code = 404, message = "Not found."),
//			@ApiResponse(code = 403, message = "Access Denied Or Any More."),
//			@ApiResponse(code = 401, message = "Unauthorized.") })
//	public ResponseEntity<?> exportAccount(
//			@RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME, required = true) String token) {
//
//		return ResponseEntity.ok("");
//
//	}

    @PostMapping(value = "/list")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp load danh sách file logs ", value = "(File Management Page) API File Management Page ", authorizations = {
            @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Data Response Retrieved."),
            @ApiResponse(code = 500, message = "Internal Server Error."),
            @ApiResponse(code = 400, message = "Bad Request cause data input."),
            @ApiResponse(code = 404, message = "Not found."),
            @ApiResponse(code = 403, message = "Access Denied Or Any More."),
            @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> getListFileLog(
            @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME, required = true) String token,
            @ApiParam(value = "Đường dẫn tới file  (EMPTY là sử dụng path: Default)") @RequestParam(value = "path", defaultValue = StringUtils.EMPTY) String path) {

        return ResponseEntity.ok(ResponseBody.builder()
                .body(fileIOService.getListFile(
                        StringUtils.EMPTY.equals(path) ? fileIOService.getFileIOServiceImpl().getPathFileLog() : path))
                .message(messageSourceVi.getMessageVi("OK002")).build());

    }

    @GetMapping(value = "/content")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp load nội dung file log theo tên file ", value = "(File Management Page) API File Management Page ", authorizations = {
            @Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Data Response Retrieved."),
            @ApiResponse(code = 500, message = "Internal Server Error."),
            @ApiResponse(code = 400, message = "Bad Request cause data input."),
            @ApiResponse(code = 404, message = "Not found."),
            @ApiResponse(code = 403, message = "Access Denied Or Any More."),
            @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> getContentFiles(
            @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME, required = true) String token,
            @ApiParam(value = "Tên file cần xem") @RequestParam(value = "file_name", defaultValue = StringUtils.APPLICATION) @Size(min = 3, message = "Tên file phải lớn hơn 3 ký tự.") String fileName)
            throws Exception {

        return ResponseEntity.ok(ResponseBody.builder().body(fileIOService.getContentAsStringFile(fileName))
                .message(messageSourceVi.getMessageVi("OK002")).build());

    }


    @PostMapping(value = "/uploads")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp upload file lên server ",
            value = "(File Management Page) API File Management Page ",
            authorizations = {@Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Data Response Retrieved."),
            @ApiResponse(code = 500, message = "Internal Server Error."),
            @ApiResponse(code = 400, message = "Bad Request cause data input."),
            @ApiResponse(code = 404, message = "Not found."),
            @ApiResponse(code = 403, message = "Access Denied Or Any More."),
            @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<?> multiUploadFileModel(
            @RequestHeader(value = ApplicationConstant.AUTHENTICATION_SCHEME_NAME, required = true) String token,
            @RequestParam(name = "files") MultipartFile[] fileUploads
//			, @RequestParam(name = "destination", defaultValue = "/FileUploadAdfilex") String destination
    )
            throws IOException {


        LOG.info("trung: {}" + fileUploads.length);
        LOG.info("trung: {}" + fileUploads);
        LOG.info("trung: {}" + fileUploads[0]);

        System.out.println(fileUploads.length);
        List<String> list = FileUtils.saveUploadedFiles(fileUploads);

        return ResponseEntity.ok(ResponseBody.builder()
                .body(list).message(!list.isEmpty() ? "UPLOAD FILES SUCCESS!" : "THẤT BẠI!").build());

//		return ResponseEntity.ok(CallApiResponse.builder().body(list)
//				.message(!list.isEmpty() ? "UPLOAD FILES SUCCESS!" : "THẤT BẠI!").build());
    }

    @RequestMapping(path = "/download", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(notes = "API sẽ được gọi trong trường hợp download file tu server ",
            value = "(File Management Page) API File Management Page ",
            authorizations = {@Authorization(value = StringUtils.API_KEY)})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Data Response Retrieved."),
            @ApiResponse(code = 500, message = "Internal Server Error."),
            @ApiResponse(code = 400, message = "Bad Request cause data input."),
            @ApiResponse(code = 404, message = "Not found."),
            @ApiResponse(code = 403, message = "Access Denied Or Any More."),
            @ApiResponse(code = 401, message = "Unauthorized.")})
    public ResponseEntity<Resource> download(String path_file) throws IOException {

        String fileName = path_file.substring(path_file.lastIndexOf("/") +1);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        File file = new File(path_file);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    public static void main(String[] args) {
        String pathStr = "upload/FileUploadAdfilex/Screenshot from 2021-03-02 08-30-32.png";

        String fileName = pathStr.substring(pathStr.lastIndexOf("/") +1);

        System.out.println(fileName);
    }
}
