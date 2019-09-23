package cn.sibetech.monitor.job;

/**
 * @author hexl
 * @date 2019/9/17
 */
public class ScheduledTaskJob implements Runnable{
    private final static String TASK1 = "1111";

    private final static String TASK2 = "2222";

    private final static int TYPE1 = 1;

    private final static int TYPE2 = 2;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public ScheduledTaskJob(Integer type) {
        this.type = type;
    }

    public ScheduledTaskJob() {
    }

    @Override
    public void run() {
        if (this.type == TYPE1) {
            System.out.println("任务1执行了！");
        }else if (this.type==TYPE2) {
            System.out.println("任务2执行了！");
        }else {
            System.out.println("任务类型为空！");
        }
    }
}
