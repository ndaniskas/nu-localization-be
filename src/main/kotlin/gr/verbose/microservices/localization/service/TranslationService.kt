package gr.verbose.microservices.localization.service

import gr.verbose.microservices.localization.repository.TranslationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TranslationService(private val translationRepository: TranslationRepository) {

    @Transactional(readOnly = true)
    fun getTranslations() =
            translationRepository.findAll()
}