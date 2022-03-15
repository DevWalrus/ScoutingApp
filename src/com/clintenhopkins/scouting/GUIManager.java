package com.clintenhopkins.scouting;

import com.clintenhopkins.scouting.controller.MatchManager;
import com.clintenhopkins.scouting.model.Match;
import com.clintenhopkins.scouting.utils.WarningTypes;
import com.clintenhopkins.scouting.view.ConfirmMatch;
import com.clintenhopkins.scouting.view.MainScreen;
import com.clintenhopkins.scouting.view.NewMatch;
import com.clintenhopkins.scouting.view.RecordMatch;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;

import java.util.HashMap;

public class GUIManager {

    private final MatchManager matchManager;
    private final MatchManager practiceManager;
    public final HashMap<WarningTypes, URLImage> images;

    private GUIManager() {
        this.matchManager = new MatchManager();
        this.practiceManager = new MatchManager();
        images = new HashMap<>();
        //EncodedImage p1 = EncodedImage.createFromImage(Image.createImage(100, 100, 0xffff9900), false);
        //images.put(WarningTypes.WARNING, URLImage.createToStorage(p1, "warning_img.png", "http://walruswebsite.s3-website.us-east-2.amazonaws.com/images/warning-7-512.png", URLImage.RESIZE_SCALE));
        //EncodedImage p2 = EncodedImage.createFromImage(Image.createImage(100, 100, 0xffff0000), false);
        //images.put(WarningTypes.ERROR, URLImage.createToStorage(p2, "error_img.png", "http://walruswebsite.s3-website.us-east-2.amazonaws.com/images/error-7-512.png", URLImage.RESIZE_SCALE));
    }

    public static GUIManager getInstance() {
        return new GUIManager();
    }

    public void begin() {
        new MainScreen(this).newSession();
    }

    public void newRecord(Match toRecord, boolean isPractice) {
        (isPractice ? practiceManager : matchManager).addMatch(toRecord.getTeamNum(), toRecord);
        record(toRecord);
    }

    public void record(Match toRecord) {
        new RecordMatch(this).recordMatch(toRecord);
    }

    public void scout() {
        new NewMatch(this).createNewMatch();
    }

    public void confirm(Match toConfirm) {
        new ConfirmMatch(this).confirmMatch(toConfirm);
    }
}
