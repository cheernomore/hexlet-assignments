package exercise;

import java.util.Map;

// BEGIN
class SingleTag extends Tag {

    public SingleTag(String tagName, Map<String, String> attributes){
        super(tagName, attributes, null, null);
    }

    @Override
    public String toString() {
        if (attributes.size() == 0) {
            return "<" + tagName + ">";
        }
        return "<" + tagName + " " + attributesToString(attributes) + ">";
    }
}
// END
