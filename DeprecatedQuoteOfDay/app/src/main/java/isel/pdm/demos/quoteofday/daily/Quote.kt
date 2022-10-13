package isel.pdm.demos.quoteofday.daily

import com.google.gson.annotations.SerializedName

data class Quote(
    val content: String,
    val author: String
) {
    init {
        require(content.isNotBlank())
        require(author.isNotBlank())
    }
}
