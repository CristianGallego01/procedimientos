package com.procedimientos.Controller;

import com.procedimientos.Model.ChatRequest;
import com.procedimientos.Model.ChatResponse;
import com.procedimientos.Service.ChatIAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatIAController {

    @Autowired
    private ChatIAService chatIAService;

    @PostMapping
    public ChatResponse getResponse(@RequestBody ChatRequest request) {
        return chatIAService.getIAResponse(request);
    }
}