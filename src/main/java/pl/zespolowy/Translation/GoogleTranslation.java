//package pl.zespolowy.Translation;
//
//import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonType;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class GoogleTranslation {
//
//    String text;
//    String source;
//
//    public GoogleTranslation(String text, String source) {
//        this.text = text;
//        this.source = source;
//    }
//
//
//
//    public List<String> toList() {
//        int[] invalidCodes = { 10 };
//        for (int code : invalidCodes) {
//            char c = (char)code;
//            this.text = this.text.replace(Character.toString(c), "");
//        }
//        String[] array = text.split(", ");
//        return Arrays.asList(array);
//    }
//
//    public String multiLine() {
//        int[] invalidCodes = { 10 };
//        for (int code : invalidCodes) {
//            char c = (char)code;
//            this.text = this.text.replace(Character.toString(c), "");
//        }
//
//        return text.replace("; ", "\n");
//    }
//
//    public void print() {
//        List<String> translationList = toList();
//        String[] sourceList = source.split(", ");
//
//        int size = translationList.size();
//        if (sourceList.length > size) { size = sourceList.length; }
//        for (int i = 0; i < size; i++) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(i);
//            sb.append(". [");
//            if (i < translationList.size()) {
//                String t = translationList.get(i);
//                sb.append(t);
//            }
//            sb.append("][");
//            if (i < sourceList.length) {
//                String s = sourceList[i];
//                sb.append(s);
//            }
//            sb.append("]");
//            System.out.println(sb);
//        }
//
//    }
//
//    public void alert() {
//        List<String> translationList = toList();
//        String[] sourceList = source.split(", ");
//
//        StringBuilder sb = new StringBuilder();
//        int size = translationList.size();
//        if (sourceList.length > size) { size = sourceList.length; }
//        for (int i = 0; i < size; i++) {
//
//            sb.append(i);
//            sb.append(". [");
//            if (i < translationList.size()) {
//                String t = translationList.get(i);
//                sb.append(t);
//            }
//            sb.append("][");
//            if (i < sourceList.length) {
//                String s = sourceList[i];
//                sb.append(s);
//            }
//            sb.append("]\n");
//        }
//
//        Alert alert = new Alert(Alert.AlertType.NONE);
//        alert.setTitle("");
//        alert.setContentText(sb.toString());
//        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
//        alert.show();
//    }
//
//
//
//
//
//}
