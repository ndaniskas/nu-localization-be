package gr.verbose.microservices.localization.api

import gr.verbose.microservices.localization.model.Translation
import gr.verbose.microservices.localization.service.TranslationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.util.*

@RestController
@RequestMapping("/api/v1/translations")
class TranslationController(private val translationService: TranslationService) {

    @GetMapping
    fun getTranslations(): Flux<TranslationApiOutput> {
        return translationService.getTranslations().map { it.toTranslationApiOutput() }
    }

    data class TranslationApiOutput(
        val id: UUID,
        val slug: String,
        val text: Map<String, String>,
        val parentId: UUID
    )

    private fun Translation.toTranslationApiOutput() =
        TranslationApiOutput(
            id = this.id,
            slug = this.slug,
            text = this.text,
            parentId = this.parentId
        )
}
