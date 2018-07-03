package com.oottru.internationalization.model

data class TranslationApiResponse(var Translation_Masters: List<TranslationsModel>,
                                  var Language_Master: List<LanguageModel>,
                                  var Values: List<ProjectModel>) {
}