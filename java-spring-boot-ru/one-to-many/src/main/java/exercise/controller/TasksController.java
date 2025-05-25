package exercise.controller;

import java.util.ArrayList;
import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.model.Task;
import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> index() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(task -> taskMapper.map(task)).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO show(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource with id: " + id + " not found")
        );
        return taskMapper.map(task);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@RequestBody @Valid TaskCreateDTO taskCreateDTO) {
        Task task = taskMapper.map(taskCreateDTO);
        taskRepository.save(task);

        Long assigneeId = task.getAssignee().getId();
        User user = userRepository.findById(assigneeId).get();
        user.addTask(task);

        return taskMapper.map(task);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO update(@RequestBody @Valid TaskUpdateDTO taskUpdateDTO, @PathVariable Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource with id: " + id + " not found")
        );
        taskMapper.update(taskUpdateDTO, task);

        //  этого не было в теории | start
        User oldUser = userRepository.findById(task.getAssignee().getId()).get();
        oldUser.removeTask(task);

        Long newAssigneeId = taskUpdateDTO.getAssigneeId();
        User newUser = userRepository.findById(newAssigneeId).orElseThrow(
                () -> new ResourceNotFoundException("Assignee with id: " + newAssigneeId + " not found")
        );
        task.setAssignee(newUser);
        newUser.addTask(task);
        // этого не было в теории | end

        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TaskDTO delete(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource with id: " + id + " not found")
        );
        taskRepository.deleteById(id);

        Long assigneeId = task.getAssignee().getId();
        User user = userRepository.findById(assigneeId).get();
        user.removeTask(task);

        return taskMapper.map(task);
    }
    // END
}
