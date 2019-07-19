package poc.spring.scheduler.cluster.job;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.javacrumbs.shedlock.core.SchedulerLock;

@Component
public class SimpleSchedulerJob {

	@Autowired
	public MongoTemplate mongoTemplate;

	@Scheduled(cron = "0 0/4 * * * *")
	@SchedulerLock(name = "mongoLock", lockAtLeastForString = "PT1M")
	public void scheduledTask() throws InterruptedException {
		System.out.println(" -------> +++++++++++++++++++++");
		IntStream.range(1, 10).forEach(i -> {
			System.out.println("Count : " + i);
			TestS ts = new TestS();
			ts.setName("Name" + i);
			ts.setDes("Testing the lock " + i);
			mongoTemplate.insert(ts, "schedulerlock");
		});
		
		Thread.sleep(180000);
	}

	public class TestS {
		private String name;
		private String des;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}

	}
}
