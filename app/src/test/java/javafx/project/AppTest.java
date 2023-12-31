/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package javafx.project;

import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void hasMainMethod() throws NoSuchMethodException {
        Class<?> clazz = App.class;
        Method mainMethod = clazz.getMethod("main", String[].class);
        assertNotNull(mainMethod);
        assertTrue(Modifier.isStatic(mainMethod.getModifiers()));
        assertEquals(void.class, mainMethod.getReturnType());
    }

}
