package conta.micro.infra;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;

// Reposanvel por fazer integração do spring para os beans ejb/cdi
public class Spring implements ApplicationContextAware, Serializable {

    private static ApplicationContext context;

    public static <T> T inject(Class<T> classe) {
        if (context == null) {
            throw new IllegalStateException("Esta classe ão foi registrado como listener de contexto no spring.");
        } else {
            return context.getBean(classe);
        }
    }

    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        context = ac;
    }
}
