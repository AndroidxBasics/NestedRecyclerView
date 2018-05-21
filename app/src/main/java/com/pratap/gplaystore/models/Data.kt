package com.pratap.gplaystore.models

/**
 * Created by pratap.kesaboyina on 30-11-2015.
 */
class Data {


    var title: String? = null
    var section: List<Section>? = null


    constructor() {}

    constructor(title: String, section: List<Section>) {
        this.title = title
        this.section = section
    }


}
