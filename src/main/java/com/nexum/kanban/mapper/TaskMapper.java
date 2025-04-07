package com.nexum.kanban.mapper;

import com.nexum.kanban.dto.SubtaskDTO;
import com.nexum.kanban.dto.TaskDTO;
import com.nexum.kanban.model.Subtask;
import com.nexum.kanban.model.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {

    public static TaskDTO toDTO(Task task) {
        TaskDTO dto = new TaskDTO(task);
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setDueDate(task.getDueDate());
        dto.setStatus(task.getStatus());

        List<SubtaskDTO> subtaskDTOs = task.getSubtasks().stream().map(sub -> {
            SubtaskDTO s = new SubtaskDTO();
            s.setId(sub.getId());
            s.setTitle(sub.getTitle());
            s.setCompleted(sub.isCompleted());
            return s;
        }).collect(Collectors.toList());

        dto.setSubtasks(subtaskDTOs);
        return dto;
    }

    public static Task toEntity(TaskDTO dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDueDate(dto.getDueDate());
        task.setStatus(dto.getStatus());

        List<Subtask> subtasks = dto.getSubtasks().stream().map(sdto -> {
            Subtask subtask = new Subtask();
            subtask.setId(sdto.getId());
            subtask.setTitle(sdto.getTitle());
            subtask.setCompleted(sdto.isCompleted());
            subtask.setTask(task);
            return subtask;
        }).collect(Collectors.toList());

        task.setSubtasks(subtasks);
        return task;
    }
}
