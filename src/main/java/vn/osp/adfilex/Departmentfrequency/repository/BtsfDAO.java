package vn.osp.adfilex.Departmentfrequency.repository;

import vn.osp.adfilex.Departmentfrequency.entity.Bts;
import vn.osp.adfilex.Departmentfrequency.entity.Btsf;
import vn.osp.adfilex.Departmentfrequency.model.Location;
import vn.osp.adfilex.Departmentfrequency.model.map.BtsfRes;
import vn.osp.adfilex.Departmentfrequency.model.map.FilterMap;

import java.util.List;

public interface BtsfDAO {

    List<BtsfRes> listBtsf(String search);
    List<Bts> listBts(FilterMap filter);

    Btsf getBTSf(Location location);
    Bts getBTS(Location location);

    long countTelco(String viettel);

    long countTech(String tech, String telco, String province);

    List<Bts> getAll();
}
