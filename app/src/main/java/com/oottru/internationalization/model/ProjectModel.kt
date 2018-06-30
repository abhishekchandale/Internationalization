package com.oottru.internationalization.model

data class ProjectModel(var id: Int, var name: String, var picture: String, var share_link: String,
                   var favourites: Boolean, var complexity: String, var duration: String, var desc_title: String,
                   var desc_value: String, var finished_size_title: String, var finished_size_value: String,
                   var materials_cut_title: String, var materials_cut_value: String, var everything_else_title: String,
                   var everything_else_value: String, var prepariton_title: String, var prepariton_value: String,
                   var cut_title: String, var cut_value: String, var assemble_title: String, var assemble_value: String, var price: Double,
                   var currency: String, var locale_code: String, var cost: String) {
}

