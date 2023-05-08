package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.ToString;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(this);
    }

    public Car unserialize(String JSON) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(JSON, Car.class);
    }
    // END
}
