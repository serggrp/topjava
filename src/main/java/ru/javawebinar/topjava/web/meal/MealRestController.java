package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    public MealTo get(int id){
        return MealsUtil.createTo(service.get(id, SecurityUtil.authUserId()), service.get(id, SecurityUtil.authUserId()).getCalories() >= SecurityUtil.authUserCaloriesPerDay());
    }
    public List<MealTo> getAll(){
        return MealsUtil.getTos(service.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay());
    }

    public MealTo save(Meal meal){
        Meal savedMeal = service.save(meal, SecurityUtil.authUserId());
        return MealsUtil.createTo(savedMeal, savedMeal.getCalories() >= SecurityUtil.authUserCaloriesPerDay());
    }

    public boolean delete(int id){
        return service.delete(id, SecurityUtil.authUserId());
    }

}