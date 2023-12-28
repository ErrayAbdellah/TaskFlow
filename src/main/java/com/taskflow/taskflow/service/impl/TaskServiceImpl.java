package com.taskflow.taskflow.service.impl;

import com.taskflow.taskflow.dto.TaskDTO;
import com.taskflow.taskflow.dto.UserDTO;
import com.taskflow.taskflow.entity.Tag;
import com.taskflow.taskflow.entity.Task;
import com.taskflow.taskflow.entity.User;
import com.taskflow.taskflow.repository.ITagRepo;
import com.taskflow.taskflow.repository.ITaskRepo;
import com.taskflow.taskflow.repository.IUserRepo;
import com.taskflow.taskflow.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
        if (taskDTO.getCreatedDate().before(new Date())) {
            throw new IllegalArgumentException("Task cannot be created in the past.");
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

}
