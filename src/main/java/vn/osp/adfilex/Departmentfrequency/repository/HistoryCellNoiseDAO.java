package vn.osp.adfilex.Departmentfrequency.repository;

import vn.osp.adfilex.Departmentfrequency.entity.Cell;
import vn.osp.adfilex.Departmentfrequency.entity.HistoryCellNoise;
import vn.osp.adfilex.Departmentfrequency.model.map.FilterMap;

import java.util.List;

public interface HistoryCellNoiseDAO {

    List<HistoryCellNoise> getHistoryCellNoises(String cellId);

}
