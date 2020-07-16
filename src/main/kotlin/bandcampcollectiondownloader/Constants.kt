package bandcampcollectiondownloader

object Constants {

    const val LINESIZE: Int = 130
    const val VERSION: String = "v2020-04-17"
    val UNICODE_CHARS_REPLACEMENTS = hashMapOf<Char, Char>(
            ':' to '꞉',
            '/' to '／',
            '\\' to '⧹',
            '"' to '＂',
            '*' to '⋆',
            '<' to '＜',
            '>' to '＞',
            '?' to '？',
            '|' to '∣'
    )
}