package com.procedimientos.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatChoiceWrapper {
    public List<Choice> choices;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Choice {
        public Message message;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Message {
        public String role;
        public String content;
    }
}
