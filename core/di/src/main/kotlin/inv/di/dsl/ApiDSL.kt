package inv.di.dsl

import androidx.annotation.RestrictTo
import org.koin.core.definition.Kind
import org.koin.core.qualifier.Qualifier
import kotlin.reflect.KClass

@RestrictTo(RestrictTo.Scope.TESTS)
data class ApiDefinition(
    val kind: Kind,
    val qualifier: Qualifier?,
    val type: KClass<*>
)

class ApiDSL {

    @RestrictTo(RestrictTo.Scope.TESTS)
    val definitions = mutableListOf<ApiDefinition>()

    @Suppress("RestrictedApi")
    inline fun <reified T> scoped(qualifier: Qualifier? = null) {
        definitions.add(ApiDefinition(Kind.Scoped, qualifier, T::class))
    }

    @Suppress("RestrictedApi")
    inline fun <reified T> factory(qualifier: Qualifier? = null) {
        definitions.add(ApiDefinition(Kind.Factory, qualifier, T::class))
    }
}
