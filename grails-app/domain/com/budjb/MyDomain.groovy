package com.budjb

class MyDomain {
    String name

    static constraints = {
        name blank: false, nullable: false, unique: true
    }
}
