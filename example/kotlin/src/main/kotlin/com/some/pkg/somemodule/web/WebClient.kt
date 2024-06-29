package com.some.pkg.somemodule.web

import com.github.bratek20.infrastructure.httpclient.api.HttpClientFactory

import com.some.pkg.somemodule.api.*

class SomeInterfaceWebClient(
    private val factory: HttpClientFactory,
    private val url: SomeModuleWebServerUrl,
): SomeInterface {
    override fun someCommand(id: SomeId, amount: Int) {
        factory.create(url.value).post("/someInterface/someCommand", SomeInterfaceSomeCommandRequest(id, amount))
    }

    override fun someQuery(id: SomeId): SomeClass {
        return factory.create(url.value).post("/someInterface/someQuery", SomeInterfaceSomeQueryRequest(id)).getBody(SomeInterfaceSomeQueryResponse::class.java).value
    }

    override fun optMethod(optId: SomeId?): SomeClass? {
        return factory.create(url.value).post("/someInterface/optMethod", SomeInterfaceOptMethodRequest(optId)).getBody(SomeInterfaceOptMethodResponse::class.java).value
    }
}