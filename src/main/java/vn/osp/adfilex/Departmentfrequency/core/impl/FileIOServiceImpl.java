/**
 * Welcome developer friend. PC ospAdfilex-smartTeleSale-service FileIOServiceImpl.java 5:06:41 PM
 */
package vn.osp.adfilex.Departmentfrequency.core.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import vn.osp.adfilex.Departmentfrequency.config.MessageSoucreConfig.MessageSourceVi;
import vn.osp.adfilex.Departmentfrequency.core.FileIOService;
import vn.osp.adfilex.Departmentfrequency.exception.FileIOException;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;


/**
 *
 * @author Nguyen_Toan
 */
@Component
@Getter
@Slf4j
public class FileIOServiceImpl implements FileIOService {

	@Value("${pathFileLog}")
	private String pathFileLog;

	@Autowired
	private MessageSourceVi messageSourceVi;

	@Override
	public List<String> getListFile(final String path) {

		File file = new File(path);
		List<String> listFileNames = new ArrayList<>();
		for (String string : Arrays.asList(file.list())) {
			if (string.contains(StringUtils.APPLICATION)) {
				listFileNames.add(string);
			}
		}

		return listFileNames;
	}

	@Override
	public String getContentAsStringFile(final String fileName) throws Exception {

		String data = null;
		File file = new File(pathFileLog);
		String filePath = StringUtils.EMPTY;
		try {

			for (String string : Arrays.asList(file.list())) {
				if (string.equalsIgnoreCase(fileName)) {
					filePath = new StringBuilder(pathFileLog + StringUtils.SLASH_RIGHT + string).toString();
					break;
				}
			}

			if (filePath.contains(StringUtils.GZ)) {

				data = readLinesFromGZ(filePath);

			} else {

				Path path = Paths.get(filePath);
				Stream<String> lines = Files.lines(path);
				data = lines.collect(Collectors.joining(StringUtils.NEWLINE_CHARACTER));
				lines.close();
			}
			log.info("*// PATH FILE LOG : " + filePath);
			log.info("*// CONTENT LENGTH = " + data.length());
		} catch (Exception e) {
			throw new FileIOException(
					messageSourceVi.getMessageVi("ER013", new Object[] { fileName }) + e.getMessage());
		}

		return data;
	}

	@Override
	public FileIOServiceImpl getFileIOServiceImpl() {
		return this;
	}

	private String readLinesFromGZ(String filePath) throws IOException {
		StringBuilder content = new StringBuilder();

		GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(filePath));
		InputStreamReader iReader = new InputStreamReader(gzip);
		BufferedReader bufferedReader = new BufferedReader(iReader);
		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			content.append(StringUtils.NEWLINE_CHARACTER + line);
		}
		gzip.close();
		iReader.close();
		bufferedReader.close();
		return content.toString();
	}
}
