package com.taskflow.taskflow.repository;

import com.taskflow.taskflow.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ITagRepo extends JpaRepository<Tag, UUID> {
    Optional<Tag> findByTagName(String tagName);
}
