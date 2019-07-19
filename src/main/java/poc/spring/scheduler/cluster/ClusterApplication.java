package poc.spring.scheduler.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

@SpringBootApplication
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT8M")
public class ClusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClusterApplication.class, args);
	}

}
