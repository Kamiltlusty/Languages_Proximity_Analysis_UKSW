package pl.zespolowy.Translation;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Translation {
    protected String[] source;
    protected String[] target;
    protected Map<String, String> translations;

    public Translation() {}

    public Translation(String[] source, String[] target) {
        boolean _ = Init(source, target);
    }

    public Map<String, String> getTranslations() { return translations; }

    public boolean Init(String[] source, String[] target) {
        System.out.println("________ INIT _________");
        this.source = source;
        this.target = target;

        if (source.length != target.length) {
            System.out.println("Invalid target length.");
            return false;
        }

        this.translations = new HashMap<String, String>();
        for (int i = 0; i < source.length; i++) {
            this.translations.put(source[i], target[i]);
        }
        return true;
    }

    //public abstract List<String> toList();

    //public abstract String multiLine();

    //public abstract void print();

    //public abstract void alert();



    public boolean readJson(String folderName, String fileName) {
        File file = new File(folderName + fileName);
        if (!file.exists()) {
            System.out.println("File doesn't exists.");
            return false;
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            this.translations = objectMapper.readValue(file, Map.class);
            source = new String[this.translations.keySet().size()];
            target = new String[this.translations.keySet().size()];
            int i = 0;
            for (var key : translations.keySet()) {
                source[i] = key;
                target[i] = translations.get(key);
                i += 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean writeJson(String folderName, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();

        File folder = new File(folderName);
        if (!folder.exists()) {
            boolean result = folder.mkdirs();
            if (!result) {
                return false;
            }
        }

        try {
            File file = new File(folder, fileName);
            if (file.exists()) {
                boolean _ = file.delete();
            }
            objectMapper.writeValue(file, this.translations);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String[] getSource() {
        return source;
    }

    public String[] getTarget() {
        return target;
    }

    public String sourceText() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (var key : translations.keySet()) {
            String value = translations.get(key);
            sb.append(key);
            if (i < translations.size() - 1) {
                sb.append(", ");
            }
            i++;
        }
        return sb.toString();
    }

    public String sourceText(String separator) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (var key : translations.keySet()) {
            String value = translations.get(key);
            sb.append(key);
            if (i < translations.size() - 1) {
                sb.append(separator);
            }
            i++;
        }
        return sb.toString();
    }

    public String targetText() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (var key : translations.keySet()) {
            String value = translations.get(key);
            sb.append(value);
            if (i < translations.size() - 1) {
                sb.append(", ");
            }
            i++;
        }
        return sb.toString();
    }

    public String targetText(String separator) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (var key : translations.keySet()) {
            String value = translations.get(key);
            sb.append(value);
            if (i < translations.size() - 1) {
                sb.append(separator);
            }
            i++;
        }
        return sb.toString();
    }

    public String translationsText() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (var key : translations.keySet()) {
            String value = translations.get(key);
            sb.append("[\"");
            sb.append(key);
            sb.append("\"] = \"");
            sb.append(value);
            sb.append("\"");
            if (i < translations.size() - 1) {
                sb.append("\n");
            }
            i++;
        }
        return sb.toString();
    }

    public String translationsText(String separator) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (var key : translations.keySet()) {
            String value = translations.get(key);
            sb.append("[\"");
            sb.append(key);
            sb.append("\"] = \"");
            sb.append(value);
            sb.append("\"");
            if (i < translations.size() - 1) {
                sb.append(separator);
            }
            i++;
        }
        return sb.toString();
    }

}

