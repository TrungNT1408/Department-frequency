package vn.osp.adfilex.Departmentfrequency.repository.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.osp.adfilex.Departmentfrequency.entity.Bts;
import vn.osp.adfilex.Departmentfrequency.entity.Btsf;
import vn.osp.adfilex.Departmentfrequency.model.Location;
import vn.osp.adfilex.Departmentfrequency.model.map.BtsfRes;
import vn.osp.adfilex.Departmentfrequency.model.map.FilterMap;
import vn.osp.adfilex.Departmentfrequency.repository.BtsfDAO;
import vn.osp.adfilex.Departmentfrequency.utils.ArfmUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class BtsfDAOImpl implements BtsfDAO {

    private Logger logger = LogManager.getLogger(BtsfDAOImpl.class);
    @Autowired
    private EntityManager entityManager;


    @Override
    public List<BtsfRes> listBtsf(String search) {
        List<Btsf> list = entityManager.createQuery("select b from Btsf b where 1=1 " + search, Btsf.class).getResultList();
        entityManager.close();

        return BtsfRes.fromBtsf(list);
    }

    @Override
    public List<Bts> listBts(FilterMap filter) {

        StringBuilder where = new StringBuilder();
        where.append(" where 1=1 ");


        int count = 0;
        if (filter.getTelco() != null && !filter.getTelco().isEmpty()) {
            for (Integer idx : filter.getTelco()) {
                String telco = ArfmUtils.getTelco(idx);
                if (StringUtils.isNotEmpty(telco)) {
                    if (count == 0) {
                        where.append(" and x.telco='" + telco + "'");
                    }
                    where.append(" or x.telco='" + telco + "'");
                }
                count++;
            }
        }


        count = 0;
//        if (filter.getTech() != null && !filter.getTech().isEmpty()) {
//            for (Integer idx : filter.getTech()) {
//                String tech = ArfmUtils.getTech(idx);
//                if (StringUtils.isNotEmpty(tech)){
//                    if (count == 0){
//                        where.append(" and x.telco='" + tech + "'");
//                    }
//                    where.append(" or x.telco='" + tech + "'");
//                }
//                count++;
//            }
//        }

//        if (filter.getTech() != null && !filter.getTech().isEmpty()) {
//            int idxTech = ArfmUtils.getIdxTech(cell.getTechType());
//            if (!filter.getTech().contains(idxTech)) {
//                continue;
//
//            }
//        }

        List<Bts> list = entityManager.createQuery("select x from Bts x " + where.toString(), Bts.class).getResultList();
        entityManager.close();

        return list;
//        return BtsfRes.fromBts(list);
    }

    @Override
    public Btsf getBTSf(Location location) {

        String search = String.format(" and lat=%f and lon=%f", location.getLat(), location.getLon());
        List<Btsf> list = entityManager.createQuery("select b from Btsf b where 1=1 " + search + " order by id desc", Btsf.class).getResultList();
        entityManager.close();
        return list.get(0);

    }

    @Override
    public Bts getBTS(Location location) {

        try {
            String search = String.format(" and lat=%f and lon=%f", location.getLat(), location.getLon());
            List<Bts> list = entityManager.createQuery("select b from Bts b where 1=1 " + search + " order by id desc", Bts.class).getResultList();
            entityManager.close();
            return list.get(0);

        } catch (Exception e){
            System.out.println("Not found with location: " + location.toString());
        }

        return null;
    }


    @Override
    public long countTelco(String telco) {
        Long count = entityManager.createQuery("select count(b) from Bts b where b.telco='" + telco + "'", Long.class).setMaxResults(1).getSingleResult();
        return count;
    }


    @Override
    public long countTech(String tech, String telco, String province) {

        StringBuilder where = new StringBuilder();
        where.append(" where 1=1");
        if (StringUtils.isNotEmpty(tech)) {
            where.append(" and c.techType='");
            where.append(tech);
            where.append("'");
        }

        if (StringUtils.isNotEmpty(telco)) {
            where.append(" and b.telco='");
            where.append(telco);
            where.append("'");
        }

        if (StringUtils.isNotEmpty(province)) {
            where.append(" and b.province='");
            where.append(province);
            where.append("'");
        }


        Long count = entityManager.createQuery("select count(c) from Cell c join c.btsId b " + where.toString(), Long.class).setMaxResults(1).getSingleResult();
        return count;
    }

    @Override
    public List<Bts> getAll() {
        List<Bts> list = entityManager.createQuery("select b from Bts b where 1=1 ", Bts.class).getResultList();
        entityManager.close();
        return list;
    }

}
