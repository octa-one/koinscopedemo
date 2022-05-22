package inv.di.internal;

import org.koin.core.instance.InstanceFactory;
import org.koin.core.module.Module;

import java.util.HashMap;

/**
 * Воспомогательный класс, чтобы получить доступ к internal методу в kotlin классе.
 * Это возможно только из Java кода.
 */
public class ModuleInternal {

    @SuppressWarnings("KotlinInternalInJava")
    public static HashMap<String, InstanceFactory<?>> getMappings(Module module) {
        return module.getMappings();
    }
}
