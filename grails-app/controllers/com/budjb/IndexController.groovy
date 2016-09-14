package com.budjb

import grails.plugin.json.view.JsonViewConfiguration
import grails.plugin.json.view.JsonViewTemplate
import grails.plugin.json.view.JsonViewTemplateEngine
import groovy.text.Template

class IndexController {
    def fails() {
        Template template = getJsonView('/example')

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        OutputStreamWriter writer = new OutputStreamWriter(outputStream)
        Writable writable = template.make(myDomain: MyDomain.findByName('test'))
        writable.writeTo(writer)

        // Uncommenting the line below fixes the issue. Looks like the writer
        // needs to be explicitly flushed when there are no more writes expected.
        //writer.flush()

        println outputStream.size()

        render new String(outputStream.toByteArray())
    }

    def works() {
        Template template = getJsonView('/example')

        StringWriter writer = new StringWriter()
        Writable writable = template.make(myDomain: MyDomain.findByName('test'))
        writable.writeTo(writer)

        println writer.toString().size()

        render writer.toString()
    }

    Template getJsonView(String name) {
        return new JsonViewTemplateEngine(new JsonViewConfiguration()).resolveTemplate(name)
    }
}
