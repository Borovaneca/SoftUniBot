package org.softunibot.softunibot.listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.softunibot.softunibot.model.Task;
import org.softunibot.softunibot.services.TaskService;
import org.springframework.stereotype.Component;

@Component
public class AddSlashCommand extends ListenerAdapter {

    private final TaskService taskService;

    public AddSlashCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("add")) return;

        OptionMapping name = event.getOption("name");
        OptionMapping url = event.getOption("url");

        Task task = Task.builder()
                .name(name.getAsString())
                .url(url.getAsString())
                .build();

        try {
            this.taskService.addTask(task);
            event.reply("Successfully added " + task.getName()).queue();
        } catch (Exception e) {
            event.reply("Something went wrong!").queue();
        }
    }
}
