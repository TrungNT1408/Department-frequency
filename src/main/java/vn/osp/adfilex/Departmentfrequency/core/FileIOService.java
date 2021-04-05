/**
 * Welcome developer friend. PC ospAdfilex-smartTeleSale-service FileIOService.java 5:06:27 PM
 */
package vn.osp.adfilex.Departmentfrequency.core;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import vn.osp.adfilex.Departmentfrequency.core.impl.FileIOServiceImpl;


/**
 *
 * @author Nguyen_Toan
 */
public interface FileIOService {

  List<String> getListFile(String path);

  /**
   * 
   * @param fileName
   * @return
   * @throws URISyntaxException
   * @throws IOException
   * @throws Exception 
   */
  String getContentAsStringFile(String fileName) throws URISyntaxException, IOException, Exception;

  FileIOServiceImpl getFileIOServiceImpl();
}
