package gr.verbose.microservices.localization.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.UUID

const val TRANSLATION_COLLECTION_NAME = "translations"

@Document(collection = TRANSLATION_COLLECTION_NAME)
data class Translation(
    @Id
    val id: UUID = UUID.randomUUID(),
    val slug: String,
    val text: Map<String, String>,
    val parentId: UUID,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = createdAt
)
