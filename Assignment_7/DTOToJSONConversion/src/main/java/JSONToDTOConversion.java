import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONToDTOConversion {
    public static void main(String[] args) throws Exception {
        String json = "{\"firstName\":\"Apurva\",\"lastname\":\"Rawal\",\"age\":23}";

        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(json, Person.class);
        System.out.println(person.getFirstName()+"  "+person.getLastname());
        System.out.println("Age: "+person.getAge());
    }
}
