package com.yoidukigembu.form2object.data


/**
 * フォームタグのデータ
 */
data class FormData (
        /** actionの値 */
        val action:String,
        /** input/selectのデータリスト */
        val inputDataList : List<InputData>)