package com.yoidukigembu.form2object.convertor.impl

import com.google.common.base.CaseFormat
import com.yoidukigembu.form2object.convertor.Form2FileConvertor
import com.yoidukigembu.form2object.convertor.Utils
import com.yoidukigembu.form2object.data.ConvertData
import com.yoidukigembu.form2object.data.FormData
import com.yoidukigembu.form2object.data.InputData
import com.yoidukigembu.form2object.enums.InputType
import javax.rmi.CORBA.Util

class PhpForm2FileConvertorImpl : Form2FileConvertor {


    override fun convert(className:String, formData: FormData) {
        val convertData = ConvertData(className, formData)


        addMembers(convertData)

        addGetters(convertData)

        addSetters(convertData)

        addFromRequest(convertData)

    }


    private fun addMembers(convertData:ConvertData) {
        val memberFormat = "/** @var %s $%s */\nprivate $%s;"

        convertData.singleList
                .map { data ->
                    memberFormat.format(
                            "mixed|null",
                            Utils.toCamelCase(data.name),
                            Utils.toCamelCase(data.name))
                }
                .forEach{data -> convertData.list.add(data)}

        convertData.multiList
                .map { data ->
                    memberFormat.format(
                            "array|null",
                            Utils.toCamelCase(data.name),
                            Utils.toCamelCase(data.name))
                }
                .forEach{data -> convertData.list.add(data)}
    }

    private fun addGetters(convertData:ConvertData) {

        val getterFormat = this.javaClass.classLoader.getResourceAsStream("template/php/getter")
                .bufferedReader()
                .readText()

        convertData.singleList
                .map { data ->
                    getterFormat.format("mixed|null", Utils.toUpperCamelCase(data.name), Utils.toCamelCase(data.name))
                }
                .forEach{getter -> convertData.list.add(getter)}

        convertData.multiList
                .map { data ->
                    getterFormat.format("array|null", Utils.toUpperCamelCase(data.name), Utils.toCamelCase(data.name))
                }
                .forEach{getter -> convertData.list.add(getter)}
    }

    private fun addSetters(convertData:ConvertData) {

        val setterFormat = this.javaClass.classLoader.getResourceAsStream("template/php/setter")
                .bufferedReader()
                .readText()

        convertData.singleList
                .map { data ->
                    setterFormat.format(
                            "mixed|null",
                            Utils.toCamelCase(data.name),
                            Utils.toUpperCamelCase(data.name),
                            Utils.toCamelCase(data.name),
                            Utils.toCamelCase(data.name),
                            Utils.toCamelCase(data.name)

                    )
                }
                .forEach{setter -> convertData.list.add(setter)}

        convertData.multiList
                .map { data ->
                    setterFormat.format(
                            "array|null",
                            Utils.toCamelCase(data.name),
                            Utils.toUpperCamelCase(data.name),
                            Utils.toCamelCase(data.name),
                            Utils.toCamelCase(data.name),
                            Utils.toCamelCase(data.name)

                    )
                }
                .forEach{setter -> convertData.list.add(setter)}
    }

    private fun addFromRequest(convertData: ConvertData) {

    }


}