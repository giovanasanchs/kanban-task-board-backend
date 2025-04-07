package com.nexum.kanban.dto;

import com.nexum.kanban.model.Status;
import com.nexum.kanban.model.Task;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.stream.Collectors;

import java.time.LocalDate;
import java.util.List;

public class TaskDTO {

    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String title;

    private String description;

    @FutureOrPresent(message = "A data deve ser hoje ou futura")
    private LocalDate dueDate;

    @NotNull(message = "Status é obrigatório")
    private Status status;

    @Size(max = 10, message = "No máximo 10 subtarefas")
    private List<@Valid SubtaskDTO> subtasks;

    public TaskDTO() {
        // construtor vazio necessário para a desserialização
    }

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate();
        this.status = task.getStatus();
        this.subtasks = task.getSubtasks().stream().map(sub -> {
            SubtaskDTO dto = new SubtaskDTO();
            dto.setId(sub.getId());
            dto.setTitle(sub.getTitle());
            dto.setCompleted(sub.isCompleted());
            return dto;
        }).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<SubtaskDTO> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<SubtaskDTO> subtasks) {
        this.subtasks = subtasks;
    }
}
