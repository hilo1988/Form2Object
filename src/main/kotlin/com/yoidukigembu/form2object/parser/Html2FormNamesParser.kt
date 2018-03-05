package com.yoidukigembu.form2object.parser

import com.yoidukigembu.form2object.data.FormData
import com.yoidukigembu.form2object.parser.impl.Html2FormNamesParserImpl
import java.io.InputStream
import java.net.URL
import java.nio.charset.Charset

interface Html2FormNamesParser {


    fun parse(url: URL, charset: Charset = Charset.defaultCharset()): List<FormData>

    fun parse(html: String): List<FormData>

    fun parse(inputStream: InputStream, charset: Charset = Charset.defaultCharset()): List<FormData>

    companion object {
        fun newInstance() :Html2FormNamesParser {
            return Html2FormNamesParserImpl()
        }
    }
}