package pl.zespolowy.Words;

public class Word {
    private String text;
    private boolean enabled;

    public Word() {}

    public Word(String text) {
        this.text = text;
        this.enabled = true;
    }

    public Word(String text, boolean enabled) {
        this.text = text;
        this.enabled = enabled;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
