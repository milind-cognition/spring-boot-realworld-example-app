package io.spring.application.article;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("article")
@Schema(description = "Parameters for updating an article")
public class UpdateArticleParam {
  @Schema(description = "Updated title of the article", example = "How to train your dragon")
  private String title = "";

  @Schema(description = "Updated body content of the article", example = "You have to believe")
  private String body = "";

  @Schema(description = "Updated description of the article", example = "Ever wonder how?")
  private String description = "";
}
