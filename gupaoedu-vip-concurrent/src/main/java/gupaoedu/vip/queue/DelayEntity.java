package gupaoedu.vip.queue;

import com.sun.istack.internal.NotNull;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2022年12月12日 14:55
 */
public class DelayEntity implements Delayed {

    private static final Long currentTime = System.currentTimeMillis();
    private String str;
    private Long scheduleTime;

    public DelayEntity(String str, Long delayed) {
        this.str = str;
        scheduleTime = System.currentTimeMillis() + (1000) * delayed;
    }

    @Override
    public long getDelay(@NotNull TimeUnit unit) {
        return unit.convert(scheduleTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NotNull Delayed o) {
        return (int) (this.scheduleTime - ((DelayEntity) o).scheduleTime);
    }

    public String getStr() {
        return str;
    }

    public Long getScheduleTime() {
        return scheduleTime;
    }

    public String showScheduleTime() {
        return "计划执行时间:" + new Date(this.scheduleTime).toString();
    }
}
