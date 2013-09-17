package com.niccholaspage.nVerse;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public enum Phrase {
	YOU_DO_NOT_HAVE_PERMISSION_TO_USE_THIS_COMMAND("You do not have permission to use this command."),
	YOU_HAVE_CREATED_WORLD("You have created $1."),
	WORLD_ALREADY_EXISTS("That world already exists."),
	WORLD_DOES_NOT_EXIST("That world does not exist."),
	COMMAND_NOT_CONSOLE("The command '$1' cannot be used in the console."),
	COMMAND_CREATEWORLD("Creates a world", true),
	COMMAND_GOTO("Teleports you to a world", true),
	PRIMARY_COLOR(ChatColor.GOLD.toString()),
	SECONDARY_COLOR(ChatColor.GRAY.toString()),
	TERTIARY_COLOR(ChatColor.DARK_GRAY.toString()),
	ARGUMENT_COLOR(ChatColor.YELLOW.toString());

	private final String defaultMessage;

	private final boolean categorized;

	private String message;

	private Phrase(String defaultMessage) {
		this(defaultMessage, false);
	}

	private Phrase(String defaultMessage, boolean categorized) {
		this.defaultMessage = defaultMessage;

		this.categorized = categorized;

		message = defaultMessage + "";
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String getMessage() {
		return message;
	}

	public void reset() {
		message = defaultMessage + "";
	}

	public String getConfigName() {
		String name = name();

		if (categorized) {
			name = name.replaceFirst("_", ".");
		}

		return name.toLowerCase();
	}

	public String parse(Object... params) {
		String parsedMessage = getMessage();

		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				parsedMessage = parsedMessage.replace("$" + (i + 1), params[i].toString());
			}
		}

		return parsedMessage;
	}

	public String parseWithoutSpaces(Object... params) {
		return parse(params).replace(" ", "");
	}

	public String parseWithPrefix(Object... params) {
		String third = Phrase.TERTIARY_COLOR.parse();

		return third + "[" + Phrase.PRIMARY_COLOR.parse() + "nVerse" + third + "] " + Phrase.SECONDARY_COLOR.parse() + parse(params);
	}

	public void send(CommandSender sender, Object... params) {
		sender.sendMessage(parse(params));
	}

	public void sendWithPrefix(CommandSender sender, Object... params) {
		sender.sendMessage(parseWithPrefix(params));
	}
}