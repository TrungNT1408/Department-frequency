package vn.osp.adfilex.Departmentfrequency.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.osp.adfilex.Departmentfrequency.entity.CellFreq;
import vn.osp.adfilex.Departmentfrequency.entity.HistoryCellNoise;
import vn.osp.adfilex.Departmentfrequency.repository.HistoryCellNoiseDAO;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class HistoryCellNoiseDAOImpl implements HistoryCellNoiseDAO {

    private Logger logger = LogManager.getLogger(LicenseBTSDAOImpl.class);
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<HistoryCellNoise> getHistoryCellNoises(String cellId) {

        List<HistoryCellNoise> historyCellNoises = entityManager.createQuery("select hcn from HistoryCellNoise hcn where hcn.cellId='" + cellId + "'" + " order by hcn.id desc", HistoryCellNoise.class).setMaxResults(50).getResultList();

        entityManager.close();

        return historyCellNoises;
    }
}
