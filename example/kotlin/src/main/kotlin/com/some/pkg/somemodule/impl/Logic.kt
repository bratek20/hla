package com.some.pkg.somemodule.impl

import com.some.pkg.somemodule.api.*

class SomeInterfaceLogic: SomeInterface {
    override fun someCommand(id: SomeId, amount: Int): Unit {
        TODO("Not yet implemented")
    }

    override fun someQuery(id: SomeId): SomeClass {
        TODO("Not yet implemented")
    }

    override fun optMethod(optId: SomeId?): SomeClass? {
        TODO("Not yet implemented")
    }
}