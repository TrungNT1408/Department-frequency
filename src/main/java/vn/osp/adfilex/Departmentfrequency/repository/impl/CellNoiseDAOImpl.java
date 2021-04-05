package vn.osp.adfilex.Departmentfrequency.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.osp.adfilex.Departmentfrequency.constants.ArfmConstants;
import vn.osp.adfilex.Departmentfrequency.entity.CellNoise;
import vn.osp.adfilex.Departmentfrequency.model.map.BtsfRes;
import vn.osp.adfilex.Departmentfrequency.model.map.FilterMap;
import vn.osp.adfilex.Departmentfrequency.repository.CellNoiseDAO;
import vn.osp.adfilex.Departmentfrequency.utils.ArfmUtils;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class CellNoiseDAOImpl implements CellNoiseDAO {

    private Logger LOG = LogManager.getLogger(CellNoiseDAO.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<CellNoise> listCellNoise(FilterMap filter) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(calendar.getTime());

        StringBuilder search = new StringBuilder();

        if (filter.getTelco() !=null && !filter.getTelco().isEmpty()){
            search.append(" x.telco in (");
            for (Integer integer : filter.getTelco()) {
                String telco = ArfmUtils.getTelco(integer);
                search.append("'").append(telco).append("'");
            }

            search.append(" ) ");
        }

        if (filter.getTech() !=null && !filter.getTech().isEmpty()){
            search.append(" c.techType in (");
            for (Integer integer : filter.getTech()) {
                String tech = ArfmUtils.getTech(integer);
                search.append("'").append(tech).append("'");
            }
            search.append(" ) ");
        }

//        List<CellNoise> list = entityManager.createQuery("select x from CellNoise x where x.status = 'NOISE'  and x.cellNoiseInfoId IS NULL and x.updateTime > '" + today + "'", CellNoise.class).getResultList();
        List<CellNoise> list = entityManager.createQuery("select x from CellNoise x where x.status = 'NOISE'  and x.updateTime > '" + today + "'" + " order by x.id desc ", CellNoise.class).getResultList();
        //select x.cell_id, x.tech_type, x.bts_id,b.telco from (select c.cell_id, c.tech_type, c.bts_id  from arfm.cell_noise cn left join arfm.cell c on cn.cell_id=c.cell_id where c.cell_id is not null ) x left join arfm.bts b on b.bts_id=x.bts_id
//        List<Object[]> list = entityManager.createNativeQuery("select x.cell_id, x.tech_type, x.bts_id,b.telco from (select c.cell_id, c.tech_type, c.bts_id  from arfm.cell_noise cn left join arfm.cell c on cn.cell_id=c.cell_id where c.cell_id is not null ) x left join arfm.bts b on b.bts_id=x.bts_id"
//                ).getResultList();
//
//
//        for (Object[] objects : list) {
//
//        }
//
        entityManager.close();
//
        return list;
//        return null;
    }

//    @Override
    public List<CellNoise> listCellNoiseNew(FilterMap filter) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -10);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(calendar.getTime());

        StringBuilder search = new StringBuilder();

        if (filter.getTelco() !=null && !filter.getTelco().isEmpty()){
            search.append(" x.telco in (");
            for (Integer integer : filter.getTelco()) {
                String telco = ArfmUtils.getTelco(integer);
                search.append("'").append(telco).append("'");
            }

            search.append(" ) ");
        }

        if (filter.getTech() !=null && !filter.getTech().isEmpty()){
            search.append(" c.techType in (");
            for (Integer integer : filter.getTech()) {
                String tech = ArfmUtils.getTech(integer);
                search.append("'").append(tech).append("'");
            }
            search.append(" ) ");
        }

//        List<CellNoise> list = entityManager.createQuery("select x from CellNoise x where x.status = 'NOISE'  and x.cellNoiseInfoId IS NULL and x.updateTime > '" + today + "'", CellNoise.class).getResultList();
        //select x.cell_id, x.tech_type, x.bts_id,b.telco from (select c.cell_id, c.tech_type, c.bts_id  from arfm.cell_noise cn left join arfm.cell c on cn.cell_id=c.cell_id where c.cell_id is not null ) x left join arfm.bts b on b.bts_id=x.bts_id
        List<Object[]> list = entityManager.createNativeQuery("select x.cell_id, x.tech_type, x.bts_id,b.telco from (select c.cell_id, c.tech_type, c.bts_id  from arfm.cell_noise cn left join arfm.cell c on cn.cell_id=c.cell_id where c.cell_id is not null ) x left join arfm.bts b on b.bts_id=x.bts_id"
        ).getResultList();


        for (Object[] objects : list) {

        }

        entityManager.close();

//        return list;
        return null;
    }

    @Override
    public List<CellNoise> listCellNoiseReg(FilterMap filter) {

        String search = " ";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -10);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(calendar.getTime());
        StringBuilder appendWhere = new StringBuilder();

        List<CellNoise> list = entityManager.createQuery("select x from CellNoise x where status = 'NOISE' and x.cellNoiseInfoId is not NULL and x.updateTime > '" + today + "'" + appendWhere.toString(), CellNoise.class).getResultList();
        entityManager.close();

        return list;
    }

    @Override
    public CellNoise getCellNoise(String cellId) {
        try {
            Calendar calendar = Calendar.getInstance();
            //TODO: Neu can thi bat len
            calendar.add(Calendar.DAY_OF_MONTH, -20);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(calendar.getTime());


            CellNoise cellNoise = entityManager.createQuery("select c from CellNoise c where c.status='NOISE'  and c.updateTime > '" + today + "'" + " and c.cellId='" + cellId + "'" + " order by c.id desc", CellNoise.class)
                    .setMaxResults(1).getSingleResult();
            return cellNoise;
        } catch (Exception e) {
//            LOG.error(e.getMessage());
        }
        return null;
    }

    @Override
    public CellNoise getCellNoise(String cellId, Date startDate) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strStartDate = sdf.format(calendar.getTime());

            CellNoise cellNoise = entityManager.createQuery("select c from CellNoise c where c.status='NOISE'  and DATE_FORMAT(c.startDate,'%Y-%m-%d') ='" + strStartDate + "'" + " and c.cellId='" + cellId + "'" + " order by c.id desc", CellNoise.class)
                    .setMaxResults(1).getSingleResult();
            return cellNoise;
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return null;

    }

    @Override
    public CellNoise insertDb(CellNoise cellNoise) {
        entityManager.persist(cellNoise);
        entityManager.flush();
        entityManager.close();
        return cellNoise;
    }

    @Override
    public CellNoise getCellNoiseFromId(long id) {
        CellNoise cellNoise = entityManager.createQuery("select c from CellNoise c where c.id=" + id, CellNoise.class)
                .setMaxResults(1).getSingleResult();
        return cellNoise;
    }

    @Override
    public void update(CellNoise cellNoise) {
        entityManager.merge(cellNoise);
        entityManager.flush();
        entityManager.close();

    }


}
