package com.yoidukigembu.form2object.convertor

import com.google.common.base.CaseFormat

object Utils {

    fun toCamelCase(str: String) =
            CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str)

    fun toUpperCamelCase(str: String) =
            CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, str)
}