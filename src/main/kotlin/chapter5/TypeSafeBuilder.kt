package chapter5

val joinWithPipe = with(listOf("하나", "둘", "셋")) {
    joinToString(separator = "|")
}

val html = buildString {
    append("<html>\n")
    append("\t<body>\n")
    append("\t\t<ul>\n")
    listOf(1, 2, 3).forEach { i ->
        append("\t\t\t<li>$i</li>\n")
    }
    append("\t\t</ul>\n")
    append("\t</body>\n")
    append("</html>")
}