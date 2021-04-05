package vn.osp.adfilex.Departmentfrequency.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.osp.adfilex.Departmentfrequency.model.*;
import vn.osp.adfilex.Departmentfrequency.model.map.BtsfRes;
import vn.osp.adfilex.Departmentfrequency.repository.BtsfDAO;
import vn.osp.adfilex.Departmentfrequency.repository.CellDAO;
import vn.osp.adfilex.Departmentfrequency.repository.CellNoiseDAO;
import vn.osp.adfilex.Departmentfrequency.utils.OspStringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ReportServices {

    private final static Logger LOG = LoggerFactory.getLogger(ReportServices.class);

    @Autowired
    private BtsfDAO btfDao;

    @Autowired
    private CellNoiseDAO cellNoiseDAO;

    @Autowired
    private CellDAO cellDAO;

    public List<BtsfRes> getReport(ReportQuery query) {
        String search = "";

        if (query.getTime_from() == null || !query.getTime_from().isValid()) {
            Calendar cal = Calendar.getInstance();
            String ttTo = (String.format("%04d-%02d-%02d",
                    cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
                    cal.get(Calendar.DATE)
            ));
            cal.add(Calendar.DATE, -14);
            String ttFrom = (String.format("%04d-%02d-%02d",
                    cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
                    cal.get(Calendar.DATE)
            ));
            DateQuery dtq = new DateQuery(ttFrom, ttTo);
            query.setTime_from(dtq);
        }

        final List<ReportQueryItem> list = new ArrayList<>();
        String q;
        try {
            //id, user_id, masked_isdn, call_status, rating, note, time_from, time_to, duration, isdn_id, hobby_id, call_id, category
            q = buildReportQuery(query);
        } catch (ParseException ex) {
            LOG.error("Error build query. Reason: {}", ex.getMessage(), ex);
            return null;
        }


        return btfDao.listBtsf(search);
    }

    private String buildReportQuery(ReportQuery query) throws ParseException{
        StringBuilder sb = new StringBuilder();

        String select = " id, user_id, masked_isdn, call_status, rating, note, time_from, time_to, duration, isdn_id, hobby_id, call_id, category,freq, ctx ";
        String where = "";

        boolean hasWhere = false;
        sb.append("(");
        sb.append("SELECT ");
        sb.append(select);
        sb.append(" FROM ");
        String tableName = "cell_noise";
        sb.append(tableName);

        if (!where.isEmpty()) {
            sb.append(" WHERE ");
        }

        if (!where.isEmpty()) {
            if (hasWhere) {
                sb.append(" AND ");
            }
            hasWhere = true;
            sb.append(where);
        }
        sb.append("  order by time_from desc ");
        sb.append(")");

        return null;
    }

    public ReportRegionRes getRegionReport(ReportFilterDTO reportFilterDTO) {

        ReportRegionRes reportRegionRes = new ReportRegionRes();

        long totalCell = btfDao.countTech(reportFilterDTO.getTech(), reportFilterDTO.getOrganisation(), reportFilterDTO.getProvince());

        //TODO: Fake
        long interf_weak = 2;
        long interf_normal = 3;
        long interf_strong = 4;
        long no_interference = totalCell- interf_weak - interf_normal - interf_strong;

        reportRegionRes.setInterf_weak(interf_weak);
        reportRegionRes.setInterf_normal(interf_normal);
        reportRegionRes.setInterf_strong(interf_strong);
        reportRegionRes.setNo_interference(no_interference);

        return reportRegionRes;
    }

//    private String buildWhere(ReportFilterDTO rfd, String tableName){
//
//        StringBuilder where = new StringBuilder();
//        where.append(" where 1=1 ");
//
//
//        if (rfd.getFrom() != null) {
//            where.append(" and x.createDate>'");
//            where.append(OspStringUtils.buildFirstTimeOfDay(rfd.getFrom()));
//            where.append("'");
//        }
//
//        if (rfd.getTo() != null) {
//            where.append("and x.createDate>'");
//            where.append(OspStringUtils.buildFirstTimeOfDay(rfd.getTo()));
//            where.append("'");
//        }
//
//        if (rfd.getOrganisation() != null) {
//            where.append("and x.createDate>'");
//            where.append(rfd.getOrganisation()));
//            where.append("'");
//        }
//
//        return where.toString();
//    }
}
