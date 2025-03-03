package com.example.spring_m2_project_v2.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
  private final TagRepository tagRepository;

  @Autowired
  public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  public List<Tag> getAllTags() {
    return tagRepository.findAll();
  }

  public Tag getTagById(Long tagId) {
    Optional<Tag> tagOptional = tagRepository.findById(tagId);
    if (tagOptional.isEmpty()) {
      throw new IllegalStateException("No tag found with id: " + tagId);
    }
    return tagOptional.get();
  }

  public Tag createTag(Tag tag) {
    return tagRepository.save(tag);
  }

  public void deleteTag(Long tagId) {
    Tag tag = getTagById(tagId);
    tagRepository.delete(tag);
  }
}
