package exercise.dto.posts;

import java.util.List;
import java.util.Map;

import io.javalin.validation.ValidationError;
import lombok.*;

// BEGIN
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EditPostPage {
    private String name;
    private String body;
    private Long id;
    private Map<String, List<ValidationError<Object>>> errors;

    public EditPostPage(String name, String body, Long id) {
        this.name = name;
        this.body = body;
        this.id = id;
    }
}
// END
