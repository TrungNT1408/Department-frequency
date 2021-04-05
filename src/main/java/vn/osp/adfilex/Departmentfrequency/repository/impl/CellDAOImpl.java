package vn.osp.adfilex.Departmentfrequency.repository.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.osp.adfilex.Departmentfrequency.constants.ArfmConstants;
import vn.osp.adfilex.Departmentfrequency.entity.Cell;
import vn.osp.adfilex.Departmentfrequency.entity.CellNoise;
import vn.osp.adfilex.Departmentfrequency.model.map.FilterMap;
import vn.osp.adfilex.Departmentfrequency.repository.CellDAO;
import vn.osp.adfilex.Departmentfrequency.utils.ArfmUtils;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
@Transactional
public class CellDAOImpl implements CellDAO {

    private Logger logger = LogManager.getLogger(CellDAOImpl.class);
    @Autowired
    private EntityManager entityManager;

    @Override
    public Cell getCell(String cellId) {
        try {
            Cell cell = entityManager.createQuery("select c from Cell c where c.cellId='" + cellId + "'", Cell.class).setMaxResults(1).getSingleResult();
            entityManager.close();

            return cell;

        }catch (EmptyResultDataAccessException er){

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;


    }

    @Override
    public List<Cell> getCells(String btsId) {
        List<Cell> cell = entityManager.createQuery("select c from Cell c join c.btsId bs where bs.btsId='" + btsId + "'", Cell.class).getResultList();
        entityManager.close();

        return cell;
    }

    @Override
    public List<Cell> getCells(String btsId, FilterMap filter) {
        StringBuilder andWhere = new StringBuilder();


        int count = 0;
//        if (filter.getTelco() != null && !filter.getTelco().isEmpty()) {
//            for (Integer idx : filter.getTelco()) {
//                String telco = ArfmUtils.getTelco(idx);
//                if (StringUtils.isNotEmpty(telco)) {
//                    if (count == 0) {
//                        andWhere.append(" and ( xx.telco='" + telco + "'");
//                    }
//                    andWhere.append(" or xx.telco='" + telco + "'");
//                    count++;
//                }
//            }
//
//            if (count > 0) {
//                andWhere.append(")");
//            }
//        }

        if (filter.getTech() != null && !filter.getTech().isEmpty()) {
            for (Integer idx : filter.getTech()) {
                String telco = ArfmUtils.getTech(idx);
                if (StringUtils.isNotEmpty(telco)) {
                    if (count == 0) {
                        andWhere.append(" and ( x.techType='" + telco + "'");
                    } else{
                        andWhere.append(" or x.techType='" + telco + "'");
                    }

                    count++;
                }
            }

            if (count > 0) {
                andWhere.append(")");
            }
        }


        List<Cell> cell = entityManager.createQuery("select x from Cell x join x.btsId xx where xx.btsId='" + btsId + "'" + andWhere , Cell.class).getResultList();
        entityManager.close();

        return cell;
    }

    @Override
    public List<Cell> getCells(String techType, String btsId) {
        List<Cell> cells = entityManager.createQuery("select x from Cell x join x.btsId xx where xx.btsId='" + btsId + "'" + " and x.techType='" + techType + "'", Cell.class).getResultList();
        entityManager.close();

        return cells;
    }

    @Override
    public List<Cell> getCells(String techType, String freq, String btsId) {
        List<Cell> cells = entityManager.createQuery("select x from Cell x join x.btsId xx where xx.btsId='" + btsId + "'" + " and x.techType='" + techType + "'" + " and x.freq='" + freq + "'", Cell.class).getResultList();
        entityManager.close();

        return cells;
    }

    @Override
    public List<Cell> getAllCell(FilterMap filter) {

//        StringBuilder appendWhere = new StringBuilder();
//
//        if (filter.getTelco() != null) {
//            int counter = 0;
//            for (Integer status : filter.getStatus()) {
//
//                if (status == ArfmConstants.Map_Status.NORMAL) {
//                    continue;
//                }
//
//                if (counter == 0) {
//                    appendWhere.append(" and (");
//                } else {
//                    appendWhere.append(" or ");
//                }
//
//                if (status == ArfmConstants.Map_Status.INFERENCE) {
//                    appendWhere.append(" x.cellNoiseInfoId IS NULL");
//
//                } else if (status == ArfmConstants.Map_Status.INFERENCE_AUTO) {
//
//                }
//
//                counter++;
//            }
//
//            if (counter > 0) {
//                appendWhere.append(" ) ");
//            }
//        }
//
//        List<CellNoise> list = entityManager.createQuery("select x from CellNoise x where status = 'NOISE' and x.updateTime > '" + today + "'" + appendWhere.toString(), CellNoise.class).getResultList();
//        entityManager.close();
//
//        return list;

        return null;
    }
}
