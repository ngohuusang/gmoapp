package vn.gmostore.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.gmostore.server.service.UserService;

/**
 * A synchronous worker
 */
@Component("syncWorker")
public class SyncWorker implements IWorker {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    UserService userService;

    @Override
    public void work() {
        String threadName = Thread.currentThread().getName();
        logger.info("   " + threadName + " has began working.");
        try {
            logger.info("working...");
            userService.getAllUser();
            Thread.sleep(10000); // simulates work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logger.info("   " + threadName + " has completed work.");
    }

}
