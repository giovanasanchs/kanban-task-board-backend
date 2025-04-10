package com.nexum.kanban.controller;

import com.nexum.kanban.dto.SubtaskStatusDTO;
import com.nexum.kanban.dto.TaskDTO;
import com.nexum.kanban.model.Subtask;
import com.nexum.kanban.repository.SubtaskRepository;
import com.nexum.kanban.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")

@CrossOrigin
public class TaskController {

    private final TaskService taskService;
    @Autowired
    private SubtaskRepository subtaskRepository;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO dto) {
        return ResponseEntity.ok(taskService.createTask(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody @Validated TaskDTO dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}/subtasks/{subtaskId}")
    public ResponseEntity<?> updateSubtaskStatus(
            @PathVariable Long taskId,
            @PathVariable Long subtaskId,
            @RequestBody SubtaskStatusDTO statusDTO) {
        Optional<Subtask> optionalSubtask = subtaskRepository.findById(subtaskId);

        if (optionalSubtask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Subtask subtask = optionalSubtask.get();

        if (!subtask.getTask().getId().equals(taskId)) {
            return ResponseEntity.badRequest().body("Subtarefa não pertence à tarefa informada.");
        }

        subtask.setCompleted(statusDTO.isCompleted());
        subtaskRepository.save(subtask);

        return ResponseEntity.ok().build();
    }
}