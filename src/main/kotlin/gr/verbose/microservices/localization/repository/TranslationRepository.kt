package gr.verbose.microservices.localization.repository

import gr.verbose.microservices.localization.model.Translation
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.util.UUID

@Repository
interface TranslationRepository : ReactiveMongoRepository<Translation, UUID> {
    fun findByParentId(parentId: UUID): Flux<Translation>

    fun deleteByParentId(parentId: UUID)
}