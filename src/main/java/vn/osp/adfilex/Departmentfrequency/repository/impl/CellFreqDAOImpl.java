package vn.osp.adfilex.Departmentfrequency.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.osp.adfilex.Departmentfrequency.constants.ArfmConstants;
import vn.osp.adfilex.Departmentfrequency.entity.CellFreq;
import vn.osp.adfilex.Departmentfrequency.repository.CellFreqDAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class CellFreqDAOImpl implements CellFreqDAO {

    private Logger logger = LogManager.getLogger(CellFreqDAOImpl.class);
    @Autowired
    private EntityManager entityManager;

    @Override
    public CellFreq getCellFreq(long cellFreqId) {
        CellFreq cellFreq = entityManager.createQuery("select cf from CellFreq cf where cf.id=" + cellFreqId, CellFreq.class).setMaxResults(1).getSingleResult();

        entityManager.close();

        return cellFreq;

    }

    @Override
    public List<CellFreq> getCellFreq(String cellId) {

        List<CellFreq> cellFreqs = entityManager.createQuery("select cf from CellFreq cf where cf.cellId='" + cellId + "'", CellFreq.class).setMaxResults(100).getResultList();

        entityManager.close();

        return cellFreqs;

    }

    @Override
    public CellFreq getCellFreqNow(String cellId, int hour, String date) {
        try {

            // TODO: Du lieu demo khong cap nhat, nen chi lay ban ghi cuoi cung
            CellFreq cellFreq = entityManager.createQuery(
                    "select cf from CellFreq cf where cf.cellId='" + cellId + "'" + " order by cf.id desc ",
                    CellFreq.class).setMaxResults(1).getSingleResult();

            entityManager.close();

            return cellFreq;
        } catch (EmptyResultDataAccessException | NoResultException erd) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CellFreq getCellFreqNoOrder(String cellId, int hour, String date) {
        try {

            // TODO: Du lieu demo khong cap nhat, nen chi lay ban ghi cuoi cung
            CellFreq cellFreq = entityManager.createQuery(
//                    "select cf from CellFreq cf where cf.cellId='" + cellId + "'",
                    "select cf from CellFreq cf where  cf.cellId='" + cellId + "'" + " and cf.hour =" + hour + " and cf.dateTime='" + date + "'",
                    CellFreq.class).setMaxResults(1).getSingleResult();

            entityManager.close();

            return cellFreq;
        } catch (EmptyResultDataAccessException | NoResultException erd) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CellFreq> getAllNoiseCell(int hour, String date, double rtwpNoise) {
        try {
            // TODO: Du lieu demo khong cap nhat, nen chi lay ban ghi cuoi cung
            List<CellFreq> cellFreqs = entityManager.createQuery(
                    "select cf from CellFreq cf where  cf.hour =" + hour + " and cf.dateTime='" + date + "'" + " and cf.rtwp >" + rtwpNoise,
                    CellFreq.class).getResultList();

            entityManager.close();

            return cellFreqs;
        } catch (EmptyResultDataAccessException erd) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CellFreq> getHourNoise(String cellId, Date startDate) {
        try {
            // TODO: Du lieu demo khong cap nhat, nen chi lay ban ghi cuoi cung
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_MONTH, -5);

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            String date = sdf.format(calendar.getTime());

            List<CellFreq> cellFreqs = entityManager.createQuery(
                    "select cf from CellFreq cf where  cf.cellId ='" + cellId + "'" + " and cf.dateTime>'" + date + "'" + " and cf.rtwp >" + ArfmConstants.RTWP_NOISE_STANDARD,
                    CellFreq.class).getResultList();

            entityManager.close();

            return cellFreqs;
        } catch (EmptyResultDataAccessException erd) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<CellFreq> getCellFreqs(Date from, Date to) {
        try {
            // TODO: Du lieu demo khong cap nhat, nen chi lay ban ghi cuoi cung
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Calendar calendarFrom = Calendar.getInstance();
            calendarFrom.set(Calendar.YEAR,2021);
            calendarFrom.set(Calendar.MONTH,2);
            calendarFrom.set(Calendar.DAY_OF_MONTH,15);
            calendarFrom.add(Calendar.DAY_OF_MONTH,-1);
//            calendarFrom.setTime(from);
            String dateFrom = sdf.format(calendarFrom.getTime());
            dateFrom="3/14/2021";

            Calendar calendarTo = Calendar.getInstance();
            calendarTo.set(calendarTo.YEAR,2021);
            calendarTo.set(calendarTo.MONTH,2);
            calendarTo.set(calendarTo.DAY_OF_MONTH,15);
//            calendarTo.setTime(to);
            String dateTo = sdf.format(calendarTo.getTime());
            dateTo="3/15/2021";

            List<CellFreq> cellFreqs = entityManager.createQuery(
                    "select cf from CellFreq cf where 1=1 " + " and cf.dateTime='" + dateFrom  +  "'" + " or cf.dateTime='" + dateTo + "'",
                    CellFreq.class).getResultList();

            entityManager.close();

            return cellFreqs;
        } catch (EmptyResultDataAccessException erd) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
