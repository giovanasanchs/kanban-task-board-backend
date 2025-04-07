package com.nexum.kanban.service;

import com.nexum.kanban.dto.TaskDTO;
import com.nexum.kanban.mapper.TaskMapper;
import com.nexum.kanban.model.Subtask;
import com.nexum.kanban.model.Task;
import com.nexum.kanban.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));
        return new TaskDTO(task); // ou use um mapper se tiver
    }

    public TaskDTO createTask(TaskDTO dto) {
        Task task = TaskMapper.toEntity(dto);
        for (Subtask sub : task.getSubtasks()) {
            sub.setTask(task); // associa cada subtask à task
        }
        Task saved = taskRepository.save(task);
        return TaskMapper.toDTO(saved);
    }

    public TaskDTO updateTask(Long id, TaskDTO dto) {
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setDueDate(dto.getDueDate());
        existing.setStatus(dto.getStatus());

        // Limpa subtarefas antigas
        existing.getSubtasks().clear();

        // Cria novas subtarefas associadas à tarefa existente
        List<Subtask> novasSubtasks = dto.getSubtasks().stream().map(sub -> {
            Subtask subtask = new Subtask();
            subtask.setTitle(sub.getTitle());
            subtask.setCompleted(sub.isCompleted());
            subtask.setTask(existing);
            return subtask;
        }).collect(Collectors.toList());

        existing.getSubtasks().addAll(novasSubtasks);

        Task updated = taskRepository.save(existing);
        return TaskMapper.toDTO(updated);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
