package vn.osp.adfilex.Departmentfrequency.repository.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.osp.adfilex.Departmentfrequency.entity.BtsCell;
import vn.osp.adfilex.Departmentfrequency.entity.Btsf;
import vn.osp.adfilex.Departmentfrequency.repository.BtsCellDAO;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class BtsCellDAOImpl implements BtsCellDAO {

    private Logger logger = LogManager.getLogger(BtsfDAOImpl.class);
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<BtsCell> getAll(Long id) {
        List<BtsCell> list = entityManager.createQuery("select b from BtsCell b join b.btsId bs where bs.id=" + id, BtsCell.class).getResultList();
        entityManager.close();

        return list;
    }

    @Override
    public List<BtsCell> getAll(Btsf btsf) {
        List<BtsCell> list = entityManager.createQuery("select b from BtsCell b where b.btsId=" + btsf, BtsCell.class).getResultList();
        entityManager.close();

        return list;
    }


}
