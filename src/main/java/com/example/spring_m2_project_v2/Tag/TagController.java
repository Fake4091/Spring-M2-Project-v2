package com.example.spring_m2_project_v2.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tags")
public class TagController {
  private final TagService tagService;

  @Autowired
  public TagController(TagService tagService) { this.tagService = tagService; }

  @GetMapping
  public List<Tag> getAllTags() {
    return tagService.getAllTags();
  }

  @GetMapping(path = "{tagId}")
  public Tag getTagById(@PathVariable Long tagId) {
    return tagService.getTagById(tagId);
  }

  @PostMapping
  public Tag createTag(@RequestBody Tag tag) {
    return tagService.createTag(tag);
  }

  @DeleteMapping(path = "{tagId}")
  public void deleteTag(@PathVariable Long tagId) {
    tagService.deleteTag(tagId);
  }
}
