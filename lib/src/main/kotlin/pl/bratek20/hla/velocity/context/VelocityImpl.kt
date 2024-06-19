package pl.bratek20.hla.velocity.context

import com.github.bratek20.architecture.context.api.ContextBuilder
import com.github.bratek20.architecture.context.api.ContextModule
import pl.bratek20.hla.velocity.api.VelocityFacade
import pl.bratek20.hla.velocity.impl.VelocityFacadeImpl

class VelocityImpl: ContextModule {
    override fun apply(builder: ContextBuilder) {
        builder.setImpl(VelocityFacade::class.java, VelocityFacadeImpl::class.java)
    }
}