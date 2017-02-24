package io.digitalsynapse.classificationIndicator;

/**
 * Created by raparkhurst on 2/13/17.
 */
public class ClassificationParameters {
    private String classificationFileName;
    private String classificationLevel;
    private String classificationCaveats;
    private boolean ConsoleMode;
    private boolean GuiMode;
    private boolean GuiFullScreenMode;


    public ClassificationParameters() {
        this.classificationFileName = null;
        this.classificationLevel = "UNCLASSIFIED";
        this.classificationCaveats = "FOUO";
        this.ConsoleMode = false;
        this.GuiMode = true;
        this.GuiFullScreenMode = false;
    }

    public String getClassificationFileName() {
        return classificationFileName;
    }

    public void setClassificationFileName(String classificationFileName) {
        this.classificationFileName = classificationFileName;
    }

    public String getClassificationLevel() {
        return classificationLevel;
    }

    public void setClassificationLevel(String classificationLevel) {
        this.classificationLevel = classificationLevel;
    }

    public String getClassificationCaveats() {
        return classificationCaveats;
    }

    public void setClassificationCaveats(String classificationCaveats) {
        this.classificationCaveats = classificationCaveats;
    }

    public boolean isConsoleMode() {
        return ConsoleMode;
    }

    public void setConsoleMode(boolean consoleMode) {
        ConsoleMode = consoleMode;
    }

    public boolean isGuiMode() {
        return GuiMode;
    }

    public void setGuiMode(boolean guiMode) {
        GuiMode = guiMode;
    }

    public boolean isGuiFullScreenMode() {
        return GuiFullScreenMode;
    }

    public void setGuiFullScreenMode(boolean guiFullScreenMode) {
        GuiFullScreenMode = guiFullScreenMode;
    }
}
