package vn.gmostore.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * A custom job that extends from the QuartzJobBean
 * <p>
 * The fields are automatically mapped by Spring's {@link JobDetailsBean} based
 * on the properties we declared in the XML configuration for the jobDataAsMap
 * property. Make sure to add a setter for the property!
 * <p>
 * By default, Quartz Jobs are stateless, resulting in the possibility of jobs
 * interfering with each other. If you specify two triggers for the same
 * JobDetail, it might be possible that before the first job has finished, the
 * second one will start. If JobDetail classes implement the Stateful interface,
 * this won't happen. The second job will not start before the first one has
 * finished.
 * 
 * @see http 
 *      ://static.springsource.org/spring/docs/3.0.x/spring-framework-reference
 *      /html/scheduling.html
 */
public class CustomJob extends QuartzJobBean implements StatefulJob {

    protected final Log logger = LogFactory.getLog(this.getClass());
    private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private IWorker worker;

    @Override
    protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {

        // The job data map is available through the JobExecutionContext 
        // (passed to you at execution time)
        @SuppressWarnings("unused")
        JobDataMap jobDataMap = ctx.getJobDetail().getJobDataMap();

        try {
            // Retrieve the last date when the job was run
            Date lastDateRun = ctx.getPreviousFireTime();

            // Job was run previously
            if (lastDateRun != null) {
                logger.debug("Last date run: " + sdf.format(lastDateRun));

                // Retrieve the number of times this job has been attempted
                int refireCount = ctx.getRefireCount();

                if (refireCount > 0) {
                    logger.debug("Total attempts: " + refireCount);
                }
            } else {
                // Job is run for the first time
                logger.debug("Job is run for the first time");
            }

            // Do the actual work
            logger.debug("Delegating work to worker");
            worker.work();

            // Retrieve the next date when the job will be run
            String nextDateRun = sdf.format(ctx.getNextFireTime());

            logger.debug("Next date run: " + nextDateRun);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Unexpected exception", e);
            throw new JobExecutionException("Unexpected exception", e, true);
        }
    }

    /**
     * The worker
     * <p>
     * This is required so that Spring's {@link JobDetailsBean} will
     * automatically inject the values
     */
    public void setWorker(IWorker worker) {
        this.worker = worker;
    }

}
