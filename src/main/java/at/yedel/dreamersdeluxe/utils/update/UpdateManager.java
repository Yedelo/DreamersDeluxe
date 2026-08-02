package at.yedel.dreamersdeluxe.utils.update;



import at.yedel.dreamersdeluxe.DreamersDeluxe;
import at.yedel.dreamersdeluxe.launch.DreamersDeluxeConstants;
import at.yedel.dreamersdeluxe.utils.ClickNotifications;
import at.yedel.dreamersdeluxe.utils.Requests;
import cc.polyfrost.oneconfig.libs.universal.UChat;
import cc.polyfrost.oneconfig.libs.universal.UDesktop;
import cc.polyfrost.oneconfig.libs.universal.UScreen;
import cc.polyfrost.oneconfig.libs.universal.wrappers.message.UTextComponent;
import cc.polyfrost.oneconfig.utils.Notifications;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.event.ClickEvent;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;



public class UpdateManager {
	private static final UpdateManager INSTANCE = new UpdateManager();

	public static UpdateManager getInstance() {
		return INSTANCE;
	}

	private static final String CURRENT_VERSION = DreamersDeluxeConstants.MOD_VERSION;
	private static final URL MODRINTH_API_URL;
	private static final URL GITHUB_API_URL;

	static {
		try {
			MODRINTH_API_URL = new URL("https://api.modrinth.com/v2/project/dreamersdeluxe/version");
			GITHUB_API_URL = new URL("https://api.github.com/repos/yedelo/dreamersdeluxe/releases/latest");
		}
		catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	private UpdateManager() {}

	public void checkForUpdates(UpdateSource updateSource, FeedbackMethod feedbackMethod) {
		new Thread(() -> threadedCheckForUpdates(updateSource, feedbackMethod), "DreamersDeluxe Update Checker").start();
	}

	private void threadedCheckForUpdates(UpdateSource updateSource, FeedbackMethod feedbackMethod) {
		try {
			if (updateSource == UpdateSource.MODRINTH) {
				JsonArray modrinthApiInfo = getModrinthApiInfo();
				String modrinthVersion = getModrinthVersion(modrinthApiInfo);
				if (Objects.equals(modrinthVersion, CURRENT_VERSION)) {
					notifyUpToDate("Modrinth", feedbackMethod);
					return;
				}
				notifyNewVersion(modrinthVersion, updateSource, feedbackMethod);
			}
			else {
				JsonObject githubApiInfo = getGithubApiInfo();
				String githubVersion = getGithubVersion(githubApiInfo);
				if (Objects.equals(githubVersion, CURRENT_VERSION)) {
					notifyUpToDate("GitHub", feedbackMethod);
					return;
				}
				notifyNewVersion(githubVersion, updateSource, feedbackMethod);
			}
		}
		catch (IOException e) {
			handleError(updateSource, feedbackMethod);
			e.printStackTrace();
		}
	}

	public JsonArray getModrinthApiInfo() throws IOException {
		return Requests.GSON.fromJson(new InputStreamReader(Requests.openURLConnection(MODRINTH_API_URL).getInputStream(), StandardCharsets.UTF_8), JsonArray.class);
	}

	public String getModrinthVersion(JsonArray modrinthApiInfo) {
		return modrinthApiInfo.
			get(0).
			getAsJsonObject().
			get("version_number").
			getAsString().
			replace("\"", "");
	}

	public JsonObject getGithubApiInfo() throws IOException {
		return Requests.getJsonObject(GITHUB_API_URL);
	}

	public String getGithubVersion(JsonObject githubApiInfo) {
		return githubApiInfo.
			getAsJsonObject().
			get("tag_name").
			getAsString().
			replace("\"", "");
	}

	public void notifyUpToDate(String updateSource, FeedbackMethod feedbackMethod) {
		if (feedbackMethod == FeedbackMethod.CHAT) {
			UChat.chat(DreamersDeluxe.LOGGER + " §cYou are up to date with the mod version on " + updateSource + "!");
		}
		else {
			if (UScreen.getCurrentScreen() != null) { // if this isn't at launch, for auto check updates
				Notifications.INSTANCE.send("DreamersDeluxe", "You are up to date with the mod version on " + updateSource + "!");
			}
		}
	}

	private void notifyNewVersion(String newVersion, UpdateSource updateSource, FeedbackMethod feedbackMethod) {
		if (feedbackMethod == FeedbackMethod.CHAT) {
			UChat.chat(new UTextComponent(DreamersDeluxe.LOGGER + " §eVersion " + newVersion + " is avaliable on " + updateSource.coloredName + "§e!").setClick(ClickEvent.Action.OPEN_URL, updateSource.uri.toString()));
		}
		else {
			ClickNotifications.getInstance().send("DreamersDeluxe", "Version " + newVersion + " is avaliable on " + updateSource.name + "! Press %k to open.", () -> {
				if (!UDesktop.browse(updateSource.uri)) {
					Notifications.INSTANCE.send("DreamersDeluxe", "Couldn't open link for " + updateSource.name + "!");
				}
			});
		}
	}

	private void handleError(UpdateSource updateSource, FeedbackMethod feedbackMethod) {
		if (feedbackMethod == FeedbackMethod.CHAT) {
			UChat.chat(DreamersDeluxe.LOGGER + " §cCouldn't get update information from " + updateSource.name + "!");
		}
		else {
			Notifications.INSTANCE.send("DreamersDeluxe", "Couldn't get update information from " + updateSource.name + "!");
		}
	}

	public enum FeedbackMethod {
		CHAT,
		NOTIFICATIONS
	}
}
