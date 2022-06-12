package co.develhope.CRUD.controllers;

import co.develhope.CRUD.entities.Car;
import co.develhope.CRUD.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public Car createCar(@RequestBody Car car){
        Car carSaved = carRepository.save(car);
        return carSaved;
    }

    @GetMapping
    public List<Car> getCars(){
        List<Car> carSaved = carRepository.findAll();
        return carSaved;
    }

    @GetMapping("/{id}")
    public Optional<Car> getCar(@RequestBody(required = true) String id){
        boolean existsById = carRepository.existsById(id);
        if (existsById==true) {
            Optional<Car> carSaved = carRepository.findById(id);
            return carSaved;
        }else{
            return null;
        }
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable String type, @RequestBody Car car){
        car.setType(type);
        Car carUpdated = carRepository.saveAndFlush(car);
        return carUpdated;
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable String id){
        boolean existsById = carRepository.existsById(id);
        if(existsById==true)
            carRepository.deleteById(id);
        else{
            HttpStatus httpStatus = HttpStatus.CONFLICT;
            System.out.println(httpStatus);
        }
    }

    @DeleteMapping("")
    public void deleteAllCars(){
        carRepository.deleteAll();
    }
}