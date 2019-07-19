package poc.spring.scheduler.cluster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data. mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider;

@Configuration
public class AppConfig {

	@Bean
	MongoClient mongo() {
		MongoClientOptions.Builder optionsBuilder = MongoClientOptions.builder();
		optionsBuilder.socketTimeout(10000);
		optionsBuilder.maxConnectionIdleTime(600000);
		optionsBuilder.heartbeatConnectTimeout(5000);
		MongoClientURI mongoClientURI = new MongoClientURI("mongodb://localhost:27017/", optionsBuilder);
		return new MongoClient(mongoClientURI);
	}

	@Bean
	public MongoDbFactory mongoDbFactory() {
		return new SimpleMongoDbFactory(mongo(), "sample-db");
	}

	@Bean
	public LockProvider lockProviderider(MongoClient mongo) {
		return new MongoLockProvider(mongo, "sample-db", "schedulerlock");
	}
	
//	@Bean
//	public LockProvider lockProvider(DataSource dataSource) {
//	    return new JdbcTemplateLockProvider(dataSource);
//	}
//	
////	@Bean
////	DataSource dataSource() {
////		MysqlDataSource mysqlDS ;
////	}

}
