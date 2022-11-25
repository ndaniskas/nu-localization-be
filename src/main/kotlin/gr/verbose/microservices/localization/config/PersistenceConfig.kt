package gr.verbose.microservices.localization.config

import com.mongodb.ReadConcern
import com.mongodb.ReadPreference
import com.mongodb.TransactionOptions
import com.mongodb.WriteConcern
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.ReactiveMongoTransactionManager
import java.util.concurrent.TimeUnit

@Configuration
class PersistenceConfig {
    @Bean
    fun reactiveTransactionManager(dbFactory: ReactiveMongoDatabaseFactory) =
        ReactiveMongoTransactionManager(
            dbFactory,
            TransactionOptions.builder()
                .readPreference(ReadPreference.primary())
                .readConcern(ReadConcern.MAJORITY)
                .writeConcern(WriteConcern.MAJORITY)
                .maxCommitTime(60, TimeUnit.SECONDS)
                .build()
        )
}
