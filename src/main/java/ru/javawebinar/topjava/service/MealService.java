package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService {
    @Autowired
    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal save(Meal meal, int userId) {
        return checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    public boolean delete(int id, int userId){
        return repository.delete(id, userId);
    }


    public List<Meal> getAll(int authUserId) {
        return repository.getAll(authUserId).stream().collect(Collectors.toList());
    }

    public Meal get(int id, int authUserId) {
        return checkNotFoundWithId(repository.get(id, authUserId), id);
    }
}