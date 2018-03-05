package com.yoidukigembu.form2object.data

import com.yoidukigembu.form2object.enums.InputType

class ConvertData(val className:String, formData: FormData) {

    val singleList: List<InputData> = formData.inputDataList
            .filter { data -> data.inputType == InputType.Single }

    val multiList: List<InputData> = formData.inputDataList
    .filter { data -> data.inputType == InputType.Multi }

    val list: MutableList<String> = mutableListOf()


}