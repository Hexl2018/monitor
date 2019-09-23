package cn.sibetech.monitor.util;

import cn.sibetech.monitor.exception.WebException;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.util.Date;

/**
 * spring 定时表达式工具类
 * @author hexl
 * @date 2019/9/23
 */
@SuppressWarnings("unused")
public class ScheduledCronUtil {

    private static final String CRON_PER_MINUTES = "0 */%d * * * ?";

    private static final String CRON_PER_SECONDS = "*/%d * * * * ?";

    private static final Integer SCHEDULED_PER_MINUTES_MIN = 1;

    private static final Integer SCHEDULED_PER_MINUTES_MAX = 60;

    public static void getNextTime(String cron) {
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);
        Date currentTime = new Date();
        System.out.println("近三次执行时间：");
        Date nextTimePoint = cronSequenceGenerator.next(currentTime);
        System.out.println("下一次执行时间: " + nextTimePoint);
        Date nextNextTimePoint = cronSequenceGenerator.next(nextTimePoint);
        System.out.println("下下次执行时间: " + nextNextTimePoint);
        Date nextNextNextTimePoint = cronSequenceGenerator.next(nextNextTimePoint);
        System.out.println("下下下次执行时间: " + nextNextNextTimePoint);
    }

    public static String makeCronPerMinutes(Integer interval) {
        if (interval<SCHEDULED_PER_MINUTES_MIN) {
            throw new WebException("此定时器间隔不能小于一分钟");
        }
        if (interval>SCHEDULED_PER_MINUTES_MAX) {
            throw new WebException("此定时器间隔不能大于60分钟");
        }
        return String.format(CRON_PER_MINUTES,interval);
    }

    public static String makeCronPerSeconds(Integer interval) {
        if (interval<SCHEDULED_PER_MINUTES_MIN) {
            throw new WebException("此定时器间隔不能小于一秒钟");
        }
        if (interval>SCHEDULED_PER_MINUTES_MAX) {
            throw new WebException("此定时器间隔不能大于60秒钟");
        }
        return String.format(CRON_PER_SECONDS,interval);
    }

    public static void validateCron(String cron) {
        try{
            new CronSequenceGenerator(cron);
        }catch (Exception e) {
            throw new WebException("定时任务表达式有误");
        }
    }
}
