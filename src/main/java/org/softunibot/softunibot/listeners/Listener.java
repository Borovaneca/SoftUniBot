package org.softunibot.softunibot.listeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.softunibot.softunibot.services.TaskService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class Listener extends ListenerAdapter {

    private final TaskService taskService;
    private List<ListenerAdapter> listeners;

    public Listener(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void onReady(ReadyEvent event) {
        Guild guild = event.getJDA().getGuildById("1094748029866758219");
        guild.upsertCommand("add", "Adding new task.")
                .addOptions(
                        new OptionData(OptionType.STRING, "name", "Name of the task.", true),
                        new OptionData(OptionType.STRING, "url", "URL of the task to the repo.", true)
                )
                .queue();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String message = event.getMessage().getContentRaw();
        if (!message.contains("!java")) return;
//        if (event.getMessage().isFromGuild()) return;
        String substring = message.substring(6);
        event.getChannel().sendMessage(this.taskService.getTask(substring)).queue();
    }
}
