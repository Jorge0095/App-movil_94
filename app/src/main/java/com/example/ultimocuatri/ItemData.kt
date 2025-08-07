package com.example.ultimocuatri

data class ItemData(
    var txtCategoria: String = "",
    var txtDescripcion: String = "",
    var imageId: Int = 0

) {
    constructor(item: ItemData) : this(
        txtCategoria = item.txtCategoria,
        txtDescripcion = item.txtDescripcion,
        imageId = item.imageId
    )
}
