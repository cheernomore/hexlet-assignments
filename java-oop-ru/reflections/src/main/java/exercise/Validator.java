package exercise;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class Validator {

    public static List<String> validate(Object object) {
        List<String> invalidFields = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field: fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation: annotations) {
                if (annotation instanceof NotNull) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(object);
                        if (value == null) {
                            invalidFields.add(field.getName());
                        }
                    } catch (IllegalAccessException e) {

                    }
                }
            }
        }
        return invalidFields;
    }
}
// END
