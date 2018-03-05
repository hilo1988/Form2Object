package com.yoidukigembu.form2object.data

import com.yoidukigembu.form2object.enums.InputType
import org.jsoup.nodes.Element

class InputData(val name:String, val inputType: InputType) {

    companion object {
        fun newInstance(element: Element) :InputData? {

            val name = element.getElementsByAttribute("name").first().`val`()
            if (element.tagName() == "input") {
                val typeName = element.getElementsByAttribute("type").first().`val`()

                return if (typeName.toLowerCase() == "checkbox")
                        InputData(name.replace(Regex("\\[\\w+\\]"), ""), InputType.Multi)
                else InputData(name, InputType.Single)
            }

            if (element.tagName() == "select") {
                val multiElms = element.getElementsByAttribute("multiple")
                if (multiElms.isEmpty()) {
                    return InputData(name, InputType.Single)
                }

                return InputData(name.replace(Regex("\\[\\w+\\]"), ""), InputType.Multi)
            }



            return null

        }
    }
}