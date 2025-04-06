package com.nexum.kanban.service;

import com.nexum.kanban.model.Task;
import com.nexum.kanban.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task save(Task task) {
        return repository.save(task);
    }

    public Task update(Long id, Task updatedTask) {
        Task task = repository.findById(id).orElseThrow();
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDueDate(updatedTask.getDueDate());
        task.setStatus(updatedTask.getStatus());
        return repository.save(task);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
