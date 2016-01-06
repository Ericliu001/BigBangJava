import com.ericliu.MyOutterClass;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ericliu on 6/01/2016.
 */
public class InnerClassTest {



    @Test
    public void testInnerClass() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {



        Class<?> innerClass = Class.forName("com.ericliu.MyOutterClass$MyInnerClass");
        Constructor<?> constructor = innerClass.getDeclaredConstructor(MyOutterClass.class);
        constructor.setAccessible(true);

        Object myInnerClass = constructor.newInstance(new MyOutterClass());

        Method sayHello = myInnerClass.getClass().getDeclaredMethod("sayHello");
        sayHello.setAccessible(true);

        sayHello.invoke(myInnerClass);
    }


}
