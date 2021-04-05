package vn.osp.adfilex.Departmentfrequency.repository;


import vn.osp.adfilex.Departmentfrequency.entity.CellFreq;
import vn.osp.adfilex.Departmentfrequency.entity.CellNoiseInfo;
import vn.osp.adfilex.Departmentfrequency.model.InterferenceDTO;
import vn.osp.adfilex.Departmentfrequency.model.InterferenceFilterDTO;

import java.util.List;

public interface CellNoiseInfoDAO {

    CellNoiseInfo insertDb(CellNoiseInfo cellNoiseInfo);
    CellNoiseInfo from(InterferenceDTO b);

    List<CellNoiseInfo> getCellNoiseInfo(InterferenceFilterDTO ifd);

    void update(CellNoiseInfo cellNoiseInfo);
}
