package ru.cft.shift.intensive.template.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GptUtils {
    private List<Choice> choices;

    public String getAnswer() {
        if (choices != null && !choices.isEmpty()) {
            return choices.get(0).getText().replace("\n", "").replace("\r", "").trim();
        }
        return null;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    @JsonProperty("choices")
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Choice {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        // Добавлен пустой конструктор для десериализации
        public Choice() {
        }
    }
}
