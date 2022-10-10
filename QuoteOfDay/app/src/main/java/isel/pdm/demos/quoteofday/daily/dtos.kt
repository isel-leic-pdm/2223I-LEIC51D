package isel.pdm.demos.quoteofday.daily

import isel.pdm.demos.quoteofday.utils.hypermedia.SirenEntity

data class QuoteDtoProperties(
    val id: Long,
    val author: String,
    val text: String
)

typealias QuoteDto = SirenEntity<QuoteDtoProperties>
val QuoteDtoType = SirenEntity.getType<QuoteDtoProperties>()

fun QuoteDto.toQuote(): Quote {
    val dto = this.properties
    require(dto != null)
    return Quote(author = dto.author, content = dto.text)
}

fun QuoteDto.toQuoteOrNull(): Quote? {
    val dto = this.properties
    return if (dto == null) null
    else Quote(author = dto.author, content = dto.text)
}