package com.yoidukigembu.form2object.parser.impl

import com.yoidukigembu.form2object.data.FormData
import com.yoidukigembu.form2object.data.InputData
import com.yoidukigembu.form2object.parser.Html2FormNamesParser
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.InputStream
import java.net.URL
import java.nio.charset.Charset

class Html2FormNamesParserImpl : Html2FormNamesParser {

    override fun parse(url: URL, charset: Charset): List<FormData> {
        return parse(url.openStream())
    }

    override fun parse(html: String): List<FormData> {

        return Jsoup.parse(html)
                .getElementsByTag("form")
                .map { form ->
                    val action = form.getElementsByAttribute("action")
                            .firstOrNull()
                            ?.`val`() ?: ""
                    val inputDataList = parseForm(form)
                    FormData(action, inputDataList)
                }


    }

    override fun parse(inputStream: InputStream, charset: Charset): List<FormData> {
        return parse(inputStream.bufferedReader(charset).readText())
    }

    /**
     *
     */
    private fun parseForm(form:Element) : List<InputData> {
        val inputSet = form.getElementsByTag("input")
                .map { elm -> InputData.newInstance(form) }
                .distinct()
                .toMutableSet()

        val selectSet = form.getElementsByTag("select")
                .map { elm -> InputData.newInstance(elm) }
                .distinct()
                .toSet()

        inputSet.addAll(selectSet)

        return inputSet
                .filter { data -> data != null }
                .map { data -> data!! }
                .toList()
    }
}