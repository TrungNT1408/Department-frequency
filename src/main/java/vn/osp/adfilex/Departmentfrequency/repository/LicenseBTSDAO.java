package vn.osp.adfilex.Departmentfrequency.repository;

import vn.osp.adfilex.Departmentfrequency.entity.LicenseBts;

public interface LicenseBTSDAO {
    boolean isExistedLicense(String profileCode);

    LicenseBts getLicenseBts(String profileCode);
}
