/**
 * Welcome developer friend. PC ospAdfilex-smartTeleSale-service ScheduleTaskServiceImpl.java
 * 4:17:28 PM
 */
package vn.osp.adfilex.Departmentfrequency.core.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import vn.osp.adfilex.Departmentfrequency.config.MessageSoucreConfig.MessageSourceVi;
import vn.osp.adfilex.Departmentfrequency.core.CellNoiseServices;
import vn.osp.adfilex.Departmentfrequency.core.ScheduleTaskService;
import vn.osp.adfilex.Departmentfrequency.repository.LoggerRepository;
import vn.osp.adfilex.Departmentfrequency.repository.UserRepository;

/**
 *
 * @author Nguyen_Toan
 */
@Component
@EnableAsync
@Slf4j
public class ScheduleTaskServiceImpl implements ScheduleTaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageSourceVi messageSourceVi;

    @Autowired
    private LoggerRepository callLogRepository;

    @Autowired
    private CellNoiseServices cellNoiseServices;

    @Scheduled(cron = "${auto_run_service}")
    @Transactional
    @Override
    public void autoRunAt1stMonth() {
        log.info(" RUN THE SERVICE ON THE FIRST DAY OF THE MONTH ");
        callLogRepository.initTable();
    }


    @Scheduled(cron = "${cron_auto_rollback_resource}")
    @Transactional
    @Override
    public void autoRunAt1stDay() {
//        cellNoiseServices.scanCellNoise();
    }

}
