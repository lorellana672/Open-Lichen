package org.cloudhack.lichenapp;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class GreetingController {
    @RequestMapping(value="/report",method=RequestMethod.POST)
    public String reportResponse(@RequestBody lichenReport report){
        return "OK"
    }
}