package vn.osp.adfilex.Departmentfrequency.repository;

import vn.osp.adfilex.Departmentfrequency.entity.Cell;
import vn.osp.adfilex.Departmentfrequency.model.map.FilterMap;

import java.util.List;

public interface CellDAO {

    Cell getCell(String cellId);

    List<Cell> getCells(String btsId);
    List<Cell> getCells(String btsId, FilterMap filterMap);

    List<Cell> getCells(String techType, String btsId);

    List<Cell> getCells(String techType, String freq, String btsId);

    List<Cell> getAllCell(FilterMap filter);
}
