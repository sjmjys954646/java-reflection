package next.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import next.optional.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.*;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    @DisplayName("테스트1: 리플렉션을 이용해서 클래스와 메소드의 정보를 정확하게 출력해야 한다.")
    public void showClass() {
        SoftAssertions s = new SoftAssertions();
        Class<Question> clazz = Question.class;
        logger.debug("Classs Name {}", clazz.getName());
        Arrays.stream(clazz.getFields())
                .forEach(field -> logger.debug("Field Name: {}", field.getName()));
        Arrays.stream(clazz.getConstructors())
                .forEach(field -> logger.debug("Constructor Name: {}", field.getName()));
        Arrays.stream(clazz.getMethods())
                .forEach(field -> logger.debug("Method Name: {}", field.getName()));
    }

    @Test
    public void constructor() throws Exception {
        Class<Question> clazz = Question.class;
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();
            logger.debug("paramer length : {}", parameterTypes.length);
            for (Class paramType : parameterTypes) {
                logger.debug("param type : {}", paramType);
            }
        }
    }


    public class Student {
        private String name;

        private int age;

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    @Test
    public void privateFieldAccess() throws NoSuchFieldException, IllegalAccessException {
        Class<Student> clazz = Student.class;
        Field f = clazz.getDeclaredField("name");
        logger.debug(clazz.getName());
        Student student = new Student();
        f.setAccessible(true);
        f.set(student, "재성");
        logger.debug(student.getName());
    }

    @Test
    public void makeUserClass() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<User> clazz = User.class;
        Constructor<User> constructor = clazz.getDeclaredConstructor(String.class, Integer.class);
        User userInstance = constructor.newInstance("Yunsu", 25);
        logger.debug(userInstance.toString());
    }
}
