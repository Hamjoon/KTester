import com.google.gson.reflect.TypeToken;
import java.lang.reflect.ParameterizedType;
import java.util.List;
// Test class for GenericWithMultiBound
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GenericWithMultiBound {

    private Class<T> type;

    class GenericWithMultiBoundTest {

        @Test
        void testIsAssignableFrom() {
            GenericWithMultiBound<List<String>> listType = new GenericWithMultiBound<List<String>>((Class<List<String>>) new TypeToken<List<String>>() {
            }.getRawType());
            assertTrue(listType.isAssignableFrom(List.class));
        }
    }

    public boolean isAssignableFrom(Class<?> clazz) {
        return type.isAssignableFrom(clazz);
    }

    public static void main(String[] args) {
        GenericWithMultiBound<List<String>> listType = new GenericWithMultiBound<List<String>>((Class<List<String>>) new TypeToken<List<String>>() {
        }.getRawType());
        // Should print true
        System.out.println("Is List<String> assignable from List: " + listType.isAssignableFrom(List.class));
    }
}
