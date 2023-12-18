import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class DTOTOJSONConversion {
        public static void main(String[] args) throws Exception {
            Person person= new Person();
            person.setFirstName("Apurva");
            person.setLastname("Rawal");
            person.setAge(23);

            ObjectMapper mapper = new ObjectMapper();
            //mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String json = mapper.writeValueAsString(person);
            System.out.println(json);
        }
}
