package ru.baykov.springcourserest;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCourseRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCourseRestApplication.class, args);

//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, String> jsonToSend = new HashMap<>();
//        jsonToSend.put("name", "Test name");
//        jsonToSend.put("job", "Test job");
//        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend);
//        String url = "https://reqres.in/api/users";
//        String response = restTemplate.postForObject(url, jsonToSend, String.class);
//        System.out.println(response);

//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://reqres.in/api/users/2";
//        String response = restTemplate.getForObject(url, String.class);
//        System.out.println(response);
//        //Парсим с помощью Jackson
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonNode = mapper.readTree(response);
//        System.out.println(jsonNode.get("data").get("email"));

//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://reqres.in/api/users/2";
//        User response = restTemplate.getForObject(url, User.class);
//        System.out.printf("%s %s: %s", response.getData().getFirst_name(),
//                response.getData().getLast_name(), response.getData().getEmail());
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
