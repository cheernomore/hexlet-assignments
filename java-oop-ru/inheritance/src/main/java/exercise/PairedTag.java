package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class PairedTag extends Tag{

    public PairedTag(String tagName, Map<String, String> attributes, String body, List<Tag> children) {
        super(tagName, attributes, body, children);
    }

    @Override
    public String toString() {
        if (attributes.isEmpty()) {
            return "<" + tagName + ">" + "</" + tagName + ">";
        }

        return "<" + tagName + " " + attributesToString(attributes) + ">" + body + childrenToString(children) + "</" + tagName + ">";
    }
}
// END
