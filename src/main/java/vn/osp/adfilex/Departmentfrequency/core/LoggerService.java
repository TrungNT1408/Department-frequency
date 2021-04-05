package vn.osp.adfilex.Departmentfrequency.core;
/**
 * Welcome developer friend. LuongTN ospAdfilex-smartTeleSale-service CallLogService.java 5:40:43 PM
 */

import java.util.List;
import org.springframework.data.domain.Page;
import vn.osp.adfilex.Departmentfrequency.entity.other.LoggerRegister;
import vn.osp.adfilex.Departmentfrequency.entity.other.LoggerSearch;

/**
 *
 * @author Nguyen_Toan
 */
public interface LoggerService {

  void intTable();

  void createAll(List<LoggerRegister> callLoggers);

  int deleteAll(List<Long> callLoggers);

  Page<LoggerSearch> searchCallLogger(int page, int size, String sortByProperties, String sortBy,
      LoggerSearch callLogger);
}
