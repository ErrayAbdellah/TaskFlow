package com.taskflow.taskflow.service.impl;

import com.taskflow.taskflow.dto.TaskDTO;
import com.taskflow.taskflow.dto.UserDTO;
import com.taskflow.taskflow.entity.Tag;
import com.taskflow.taskflow.entity.Task;
import com.taskflow.taskflow.entity.User;
import com.taskflow.taskflow.enums.Status;
import com.taskflow.taskflow.repository.ITagRepo;
import com.taskflow.taskflow.repository.ITaskRepo;
import com.taskflow.taskflow.repository.IUserRepo;
import com.taskflow.taskflow.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {
    private final ITaskRepo taskRepo;
    private final ModelMapper modelMapper;
    private final ITagRepo tagRepo ;
    private final IUserRepo userRepo;


    @Override
    public void saveTask(TaskDTO taskDTO) {

        Date currentDate = new Date();
        Date threeDaysFromNow = Date.from(Instant.now().plus(Duration.ofDays(3)));

        if (taskDTO.getCreatedDate().before(currentDate)) {
            throw new IllegalArgumentException("Task cannot be created in the past.");
        }

        if (taskDTO.getCreatedDate().after(threeDaysFromNow)) {
            throw new IllegalArgumentException("Task cannot be scheduled more than 3 days in advance.");
        }

        if (taskDTO.getTagNames() == null || taskDTO.getTagNames().size() < 2) {
            throw new IllegalArgumentException("Task must have multiple tags.");
        }
        User user= userRepo.findById(taskDTO.getUserID()).orElseThrow(()->new IllegalArgumentException("Sorry, this User does not exist!"));
        Task task = modelMapper.map(taskDTO,Task.class);
        task.setUser(user);
        task.setTags(checkTagsAndAdd(taskDTO.getTagNames()));
        taskRepo.save(task);
    }

    @Override
    public void updateTaskStatus(UUID taskId, Status  newStatus) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + taskId));


        if ("done".equals(newStatus)) {
            if (new Date().after(task.getDueDate())) {
                throw new IllegalArgumentException("Cannot mark task as done past its due date.");
            }
        }
        task.setStatus(newStatus);
        taskRepo.save(task);
        modifyTask(taskId);
    }


    public List<Tag> checkTagsAndAdd(List<String> tagsName){
        List<Tag> tags = new ArrayList<>();
        for (String tagName:tagsName ) {
            if (tagRepo.findByTagName(tagName).isEmpty()){
                Tag tag = new Tag();
                tag.setTagName(tagName);
                tagRepo.save(tag);
                tags.add(tagRepo.findByTagName(tagName).get());
            }
            else {
                tags.add(tagRepo.findByTagName(tagName).get());
            }
        }
        return tags ;
    }

    public void modifyTask(UUID taskId) {
        User user = userRepo.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getModificationTokens() <= 0) {
            throw new IllegalStateException("No modification tokens left");
        }
        user.setModificationTokens(user.getModificationTokens() - 1);

        userRepo.save(user);
    }

    public void deleteTask(UUID taskId, UUID userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if (!task.getUser().getId().equals(userId)) {
            if (user.getDeletionTokens() <= 0) {
                throw new IllegalStateException("No deletion tokens left");
            }
            user.setDeletionTokens(user.getDeletionTokens() - 1);
            userRepo.save(user);
        }
        taskRepo.deleteById(taskId);
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void resetDeletionTokens() {
        List<User> users = userRepo.findAll();
        for (User user : users) {
            user.setDeletionTokens(1);
            userRepo.save(user);
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void resetModificationTokens() {
        List<User> users = userRepo.findAll();
        for (User user : users) {
            user.setModificationTokens(2);
            userRepo.save(user);
        }
    }
}
