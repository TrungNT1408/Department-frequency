/**
 * Welcome developer friend. LuongTN ospAdfilex-smartTeleSale-data CallLogFactory.java 2:01:46 PM
 */
package vn.osp.adfilex.Departmentfrequency.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import vn.osp.adfilex.Departmentfrequency.entity.other.LoggerRegister;
import vn.osp.adfilex.Departmentfrequency.entity.other.LoggerSearch;


/**
 *
 * @author Nguyen_Toan
 */
public interface LoggerRepository {

  void initTable();

  void createAll(List<LoggerRegister> callLoggers);

  int deleteAll(List<Long> callLoggers);

  Page<LoggerSearch> searchLogger(Long codeId, String codeName, String text,
      Date createdDateFrom, Date createdDateTo, int page, int size, String sortByProperties,
      String sortBy);

}
