package com.isa.wildcards.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.wildcards.entity.Movie;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonReader {

    public static List<Movie> readJsonFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        return objectMapper.readValue(file, new TypeReference<List<Movie>>() {
        });
    }


}
