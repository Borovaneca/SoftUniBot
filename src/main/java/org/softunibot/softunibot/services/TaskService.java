package org.softunibot.softunibot.services;

import org.softunibot.softunibot.model.Task;
import org.softunibot.softunibot.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public boolean addTask(Task task) {
        try {
            this.taskRepository.save(task);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getTask(String name) {
        Optional<Task> task = this.taskRepository.findByName(name);
        if (task.isEmpty()) {
            return "Not found!";
        } else {
            return task.get().getUrl();
        }
    }
}
