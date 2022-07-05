package com.steven.demo01.scheduleTask;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class MessageScheduleTask {

    /**
     * 支持简单的延时操作，例如 fixedDelay ，fixedRate 填写相应的毫秒数即可
     *
     * 每一个字段都有一套可以指定有效值，如
     * Seconds (秒) ：可以用数字0－59 表示；
     *
     * Minutes(分) ：可以用数字0－59 表示；
     *
     * Hours(时) ：可以用数字0-23表示；
     *
     * Day-of-Month(天) ：可以用数字1-31 中的任一一个值，但要注意一些特别的月份；
     *
     * Month(月) ：可以用0-11 或用字符串 “JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV and DEC” 表示；
     *
     * Day-of-Week(*每周*)*：*可以用数字1-7表示（1 ＝ 星期日）或用字符口串“SUN, MON, TUE, WED, THU, FRI and SAT”表示；
     *
     * “/”：为特别单位，表示为“每”如“0/15”表示每隔15分钟执行一次,“0”表示为从“0”分开始, “3/20”表示表示每隔20分钟执行一次，“3”表示从第3分钟开始执行；
     *
     * “?”：表示每月的某一天，或第周的某一天；
     *
     * “L”：用于每月，或每周，表示为每月的最后一天，或每个月的最后星期几如“6L”表示“每月的最后一个星期五”；
     *
     * “W”：表示为最近工作日，如“15W”放在每月（day-of-month）字段上表示为“到本月15日最近的工作日”；
     *
     * “#”：是用来指定“的”每月第n个工作日,例 在每周（day-of-week）这个字段中内容为”6#3” or “FRI#3” 则表示“每月第三个星期五”；
     *
     * “*” 代表整个时间段。
     *
     * 常见的 cron 表达
     * 每隔5秒执行一次：* / 5 * * * * ?
     * 每隔1分钟执行一次：0 * / 1 * * * ?
     * 每天23点执行一次：0 0 23 * * ?
     * 每天凌晨1点执行一次：0 0 1 * * ?
     * 每月1号凌晨1点执行一次：0 0 1 1 * ?
     * 每月最后一天23点执行一次：0 0 23 L * ?
     * 每周星期天凌晨1点实行一次：0 0 1 ? * L
     * 在26分、29 分、33 分执行一次：0 26,29,33 * * * ?
     * 每天的0点、13 点、18 点、21 点都执行一次：0 0 0,13,18,21 * * ?
     */
//    @Scheduled(cron = "20/5 * * * * ?")
    public void configureTasks() {
        System.out.println("this is a scheduleTask!");
    }
}
