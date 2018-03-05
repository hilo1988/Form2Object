package com.yoidukigembu.form2object.enums

/**
 * InputTypeが複数系か
 */
enum class InputType {
    Single,
    Multi
    ;

    companion object {
        fun newInstance(inputName: String): InputType {
            return if (inputName.toLowerCase() == "checkbox")
                Multi
            else Single
        }
    }
}