package exercise;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
abstract class Tag {
    protected String tagName;
    protected Map<String, String> attributes;
    protected String body;
    protected List<Tag> children;

    public Tag(String tagName, Map<String, String> attributes, String body, List<Tag> children) {
        this.tagName = tagName;
        this.attributes = attributes;
        this.body = body;
        this.children = children;
    }

    protected String attributesToString(Map<String, String> attributes){
        StringBuilder stringAttributes = new StringBuilder();

        for (Map.Entry<String, String> attribute: attributes.entrySet()) {
            stringAttributes.append(attribute.getKey());
            stringAttributes.append("=");
            stringAttributes.append("\"" + attribute.getValue() + "\"" + " ");
        }

        return stringAttributes.toString().trim();
    }

    protected String childrenToString(List<Tag> children) {
        return children.stream()
                .map(Tag::toString)
                .collect(Collectors.joining());
    }
}
// END
