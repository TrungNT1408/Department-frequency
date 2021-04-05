package vn.osp.adfilex.Departmentfrequency.repository.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.osp.adfilex.Departmentfrequency.entity.BtsCell;
import vn.osp.adfilex.Departmentfrequency.entity.LicenseBts;
import vn.osp.adfilex.Departmentfrequency.repository.LicenseBTSDAO;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class LicenseBTSDAOImpl implements LicenseBTSDAO {

    private Logger logger = LogManager.getLogger(LicenseBTSDAOImpl.class);
    @Autowired
    private EntityManager entityManager;


    @Override
    public boolean isExistedLicense(String profileCode) {

        try {
            profileCode = profileCode.trim();
            List<LicenseBts> licenseBts = entityManager.createQuery("select lb from LicenseBts lb where upper(lb.licenseNum)='" + profileCode + "'", LicenseBts.class).getResultList();
            entityManager.close();
            if (licenseBts != null && !licenseBts.isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public LicenseBts getLicenseBts(String profileCode) {
        try {
            profileCode = profileCode.trim();
            List<LicenseBts> licenseBts = entityManager.createQuery("select lb from LicenseBts lb where upper(lb.licenseNum)='" + profileCode + "'", LicenseBts.class).setMaxResults(1).getResultList();
            entityManager.close();
            if (licenseBts != null && !licenseBts.isEmpty()) {
                return licenseBts.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
