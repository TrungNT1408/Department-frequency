package vn.osp.adfilex.Departmentfrequency.repository;

import vn.osp.adfilex.Departmentfrequency.entity.BtsCell;
import vn.osp.adfilex.Departmentfrequency.entity.Btsf;

import java.util.List;

public interface BtsCellDAO {
    List<BtsCell> getAll(Long id);

    List<BtsCell> getAll(Btsf btsf);
}
