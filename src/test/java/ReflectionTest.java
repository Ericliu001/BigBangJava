import com.ericliu.reflection.MySubclass;
import com.ericliu.reflection.MySuperclass;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ericliu on 18/04/2016.
 */
public class ReflectionTest {

    @Test
    public void testReflection() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("test begins");

//        method = object.getClass().getDeclaredMethod(methodName);
//        method.setAccessible(true);
//        Object r = method.invoke(object);

        MySuperclass superclass = new MySuperclass();
        Method doSomething1 = superclass.getClass().getDeclaredMethod("doSomething");
        doSomething1.setAccessible(true);

        doSomething1.invoke(superclass, null);

        MySuperclass subclass = new MySubclass();
        Method doSomething2 = subclass.getClass().getDeclaredMethod("doSomething");
        doSomething2.setAccessible(true);

        doSomething2.invoke(subclass, null);


    }
}
