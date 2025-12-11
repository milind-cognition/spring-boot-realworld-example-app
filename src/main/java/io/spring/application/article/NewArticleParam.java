package io.spring.application.article;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonRootName("article")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Parameters for creating a new article")
public class NewArticleParam {
  @NotBlank(message = "can't be empty")
  @DuplicatedArticleConstraint
  @Schema(description = "Title of the article", example = "How to train your dragon")
  private String title;

  @NotBlank(message = "can't be empty")
  @Schema(description = "Short description of the article", example = "Ever wonder how?")
  private String description;

  @NotBlank(message = "can't be empty")
  @Schema(description = "Body content of the article", example = "You have to believe")
  private String body;

  @Schema(description = "List of tags for the article", example = "[\"dragons\", \"training\"]")
  private List<String> tagList;
}
