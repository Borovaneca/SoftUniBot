package org.softunibot.softunibot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.softunibot.softunibot.listeners.AddSlashCommand;
import org.softunibot.softunibot.listeners.Listener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoftUniBotApplication {

    private static Listener listener;
    private static AddSlashCommand addSlashCommand;

    public SoftUniBotApplication(Listener listener, AddSlashCommand addSlashCommand) {
        SoftUniBotApplication.listener = listener;
        SoftUniBotApplication.addSlashCommand = addSlashCommand;
    }

    public static void main(String[] args) {
        SpringApplication.run(SoftUniBotApplication.class, args);
        try {
            JDA jda = JDABuilder.createDefault(System.getenv("BOT_TOKEN"))
                    .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                    .addEventListeners(listener, addSlashCommand)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
