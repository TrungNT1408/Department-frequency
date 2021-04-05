package vn.osp.adfilex.Departmentfrequency.repository;


import vn.osp.adfilex.Departmentfrequency.entity.CellNoise;
import vn.osp.adfilex.Departmentfrequency.model.map.BtsfRes;
import vn.osp.adfilex.Departmentfrequency.model.map.FilterMap;

import java.util.Date;
import java.util.List;

public interface CellNoiseDAO {
    List<CellNoise> listCellNoise(FilterMap search);

    CellNoise getCellNoise(String cellId);
    CellNoise getCellNoise(String cellId, Date startDate);

    CellNoise insertDb(CellNoise cellNoise);

    CellNoise getCellNoiseFromId(long id);

    void update(CellNoise cellNoise);

    List<CellNoise> listCellNoiseReg(FilterMap filter);
}
