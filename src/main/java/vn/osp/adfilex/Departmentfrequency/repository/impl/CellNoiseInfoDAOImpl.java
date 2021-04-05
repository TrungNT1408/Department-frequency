package vn.osp.adfilex.Departmentfrequency.repository.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.osp.adfilex.Departmentfrequency.constants.ArfmConstants;
import vn.osp.adfilex.Departmentfrequency.entity.CellNoiseInfo;
import vn.osp.adfilex.Departmentfrequency.model.InterferenceDTO;
import vn.osp.adfilex.Departmentfrequency.model.InterferenceFilterDTO;
import vn.osp.adfilex.Departmentfrequency.repository.CellNoiseInfoDAO;
import vn.osp.adfilex.Departmentfrequency.utils.OspStringUtils;

import javax.persistence.EntityManager;
import java.util.*;

@Repository
@Transactional
public class CellNoiseInfoDAOImpl implements CellNoiseInfoDAO {

    private Logger LOG = LogManager.getLogger(CellNoiseInfoDAO.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public CellNoiseInfo insertDb(CellNoiseInfo cellNoiseInfo) {
        entityManager.persist(cellNoiseInfo);
        entityManager.flush();
        entityManager.close();

        return cellNoiseInfo;
    }


    @Override
    public CellNoiseInfo from(InterferenceDTO b) {
        CellNoiseInfo cellNoiseInfo = new CellNoiseInfo();

        cellNoiseInfo.setLicenseNumber(b.getLicense_number());
        cellNoiseInfo.setOrganisationName(b.getOrganisation_name());
        cellNoiseInfo.setOrganisation(b.getOrganisation());
        cellNoiseInfo.setPhoneNumber(b.getPhone_number());
        cellNoiseInfo.setMobilePhone(b.getMobile_phone());
        cellNoiseInfo.setEmail(b.getEmail());
        cellNoiseInfo.setRadioName(b.getRadio_name());
        cellNoiseInfo.setFreqInterf(b.getFreq_interf());
        cellNoiseInfo.setLocaltion(b.getLocaltion());
        cellNoiseInfo.setDirectionRanger(b.getDirection_ranger());
        cellNoiseInfo.setTimeInterf(b.getTime_interf());
        cellNoiseInfo.setHourInterf(b.getHour_interf());
        cellNoiseInfo.setLevelAffect(b.getLevel_affect());
        cellNoiseInfo.setPhenomenaInterf(b.getPhenomena_interf());
        cellNoiseInfo.setPhenomenaDesc(b.getPhenomena_desc());
        cellNoiseInfo.setAddInfo(b.getAddInfo());
        cellNoiseInfo.setResonFreqInterf(b.getReson_freq_interf());
        cellNoiseInfo.setIdentCauseRadio(b.getIdent_cause_radio());
        cellNoiseInfo.setLocationRadioCause(b.getLocation_radio_cause());
        cellNoiseInfo.setTimeStatistic(b.getTime_statistic());
        cellNoiseInfo.setProvince(b.getProvince());
        cellNoiseInfo.setCellId(b.getCell_id());
        cellNoiseInfo.setFreq(b.getFreq());
        cellNoiseInfo.setLon(b.getLon());
        cellNoiseInfo.setLat(b.getLat());
        cellNoiseInfo.setAzimuth(b.getAzimuth());
        cellNoiseInfo.setRtwp(b.getRtwp());
        cellNoiseInfo.setNumDayInterf(b.getNum_day_interf());
        cellNoiseInfo.setStatus(b.getStatus());
        cellNoiseInfo.setCauseInfo(b.getCause_info());
        cellNoiseInfo.setProcessInfo(b.getProcess_info());
        cellNoiseInfo.setNumRequest(b.getRequest_file());
        cellNoiseInfo.setRequestFile(b.getProcess_info());

        if (b.getFiles() != null && !b.getFiles().isEmpty()) {
            cellNoiseInfo.setFiles(String.join(ArfmConstants.FILE_SPLIT, b.getFiles()));
        }

        return cellNoiseInfo;
    }

    @Override
    public List<CellNoiseInfo> getCellNoiseInfo(InterferenceFilterDTO ifd) {

        try {
            StringBuilder where = new StringBuilder();
            where.append(" where 1=1 ");

            if (StringUtils.isNotEmpty(ifd.getLicense_number())) {
                where.append(" and x.licenseNumber>'");
                where.append(ifd.getLicense_number());
                where.append("'");
            }

            if (ifd.getFrom() != null) {
                where.append(" and x.createDate>'");
                where.append(OspStringUtils.buildFirstTimeOfDay(ifd.getFrom()));
                where.append("'");
            }

            if (ifd.getTo() != null) {
                where.append("and x.createDate<'");
                where.append(OspStringUtils.buildFirstTimeOfDay(ifd.getTo()));
                where.append("'");
            }

            if (StringUtils.isNotEmpty(ifd.getProvince())) {
                where.append(" and x.province>'");
                where.append(ifd.getProvince());
                where.append("'");
            }

            if (StringUtils.isNotEmpty(ifd.getStatus())) {
                //TODO: Need support status
                LOG.warn("We have not support filter with Status");
//            where.append(" and x.licenseNumber>'");
//            where.append(ifd.getStatus());
//            where.append("'");
            }

            if (StringUtils.isNotEmpty(ifd.getOrganisation())) {
                where.append(" and x.organisation>'");
                where.append(ifd.getOrganisation());
                where.append("'");
            }


            //TODO: Need support split page
            List<CellNoiseInfo> cellNoiseInfos = entityManager.createQuery("select x from CellNoiseInfo x " + where.toString(), CellNoiseInfo.class).setMaxResults(100).getResultList();

            entityManager.close();

            return cellNoiseInfos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void update(CellNoiseInfo cellNoiseInfo) {
        entityManager.merge(cellNoiseInfo);
        entityManager.flush();
        entityManager.close();
    }
}
