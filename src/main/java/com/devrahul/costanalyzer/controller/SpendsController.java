package com.devrahul.costanalyzer.controller;

import com.devrahul.costanalyzer.model.SpendAddDto;
import com.devrahul.costanalyzer.service.SpendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spends")
public class SpendsController {

    @Autowired
    SpendService spendService;
    @PostMapping(value = "/addSpend")
    private ResponseEntity<?> addSpend(@RequestHeader String token, @RequestBody SpendAddDto newSpend){
        List<Object> regResult = spendService.addSpending(token,newSpend);
        if(regResult.get(0).toString() .equals("1")){
            return new ResponseEntity<>(regResult.get(1), HttpStatus.OK);
        }
        else return new ResponseEntity<>(regResult.get(1),HttpStatus.BAD_REQUEST);
    }

}
