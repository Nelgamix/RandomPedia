package com.skysurf.nico.randompedia.model

import android.util.Log
import org.jsoup.Jsoup
import java.util.*

class RandomArticle {
    private val TAG = "RP_RA"
    private val RANDOM_ARTICLE_URL = "https://en.wikipedia.org/wiki/Special:Random"
    private val CONTENT_CLASS = "mw-parser-output"

    fun launch() {
        val dicoDelete = ArrayList<String>()
        dicoDelete.addAll(arrayOf(
                "is",
                "a",
                "his",
                "her",
                "then",
                "than",
                "he",
                "she",
                "it",
                "its",
                "for",
                "not",
                "now",
                "just",
                "way",
                "what",
                "well",
                "other",
                "an",
                "to",
                "and",
                "or",
                "on",
                "of",
                "was",
                "in",
                "would",
                "that",
                "who",
                "by",
                "from",
                "as",
                "the",
                "can",
                "be",
                "are",
                "does",
                "whom",
                "into",
                "but",
                "do",
                "how"
        ))

        val document = Jsoup.connect(RANDOM_ARTICLE_URL).get()
        val content = document.getElementsByClass(CONTENT_CLASS)
        val words = ArrayList<String>()
        val wordsValued = HashMap<String, Int>()

        log("Connected to ${document.baseUri()} (title: ${document.title()})")

        if (content.size > 0) {
            log("Content has ${content.size} results")

            for (item in content) {
                val contentParagraphs = item.getElementsByTag("p")

                log("\tThis content has ${contentParagraphs.size} paragraphs:")

                for (p in contentParagraphs) {
                    if (p.text().isEmpty()) {
                        log("\t\tEmpty paragraph.")
                        continue
                    }

                    val sups = p.getElementsByTag("sup")
                    for (s in sups) {
                        s.remove()
                    }

                    log("\t\t${p.text()}")

                    for (word in p.text().split(" ")) {
                        words.add(cleanWord(word))
                    }
                }
            }
        } else {
            log("Content has no output.")
        }

        if (!words.isEmpty()) {
            for (word in words) {
                if (wordsValued[word] != null) {
                    increment(wordsValued, word)
                } else {
                    if (!dicoDelete.contains(word) and !word.matches(Regex(".*\\d+.*"))) {
                        wordsValued[word] = 1
                    }
                }
            }
        }

        if (!wordsValued.isEmpty()) {
            for (wordValued in wordsValued.entries) {
                log("${wordValued.key}(${wordValued.value})")
            }
        }
    }

    private fun log(s: String) {
        Log.d(TAG, s)
    }

    private fun increment(map: HashMap<String, Int>, key: String) {
        if (!map.containsKey(key)) return

        val i = map[key]!!.plus(1)
        map.remove(key)
        map[key] = i
    }

    private fun cleanWord(word: String): String {
        return word
                .toLowerCase()
                .replace(",", "")
                .replace(";", "")
                .replace(".", "")
                .replace("\"", "")
                .replace(":", "")
                .replace("'", "")
                .replace("(", "")
                .replace(")", "")
                .replace("[", "")
                .replace("]", "")
    }
}