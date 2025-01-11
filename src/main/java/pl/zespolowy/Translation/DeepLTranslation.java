//package pl.zespolowy.Translation;
//
//import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonType;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class DeepLTranslation extends Translation {
//
//    public DeepLTranslation() {}
//
//    public DeepLTranslation(String source, String target) {
//        Init(source, target);
//    }
//
//    public void Init(String source, String target) {
//        System.out.println("________ INIT ___________");
//        System.out.println(source.toString());
//        var tar = target.split(", ");
//        var src = source.split(", ");
//
//        this.translations = new HashMap<String, String>();
//        for (int i = 0; i < tar.length; i++) {
//            this.translations.put(src[i], tar[i]);
//        }
//    }
//
//
//
//    public List<String> toList() {
//        //int[] invalidCodes = { 10 };
//        //for (int code : invalidCodes) {
//        //    char c = (char)code;
//        //    this.text = this.text.replace(Character.toString(c), "");
//        //}
//        //String[] array = text.split(", ");
//        //return Arrays.asList(array);
//        String[] strings = { "" } ;
//        return Arrays.stream(strings).toList();
//    }
//
//    public String multiLine() {
//        //int[] invalidCodes = { 10 };
//        //for (int code : invalidCodes) {
//        //    char c = (char)code;
//        //    this.text = this.text.replace(Character.toString(c), "");
//        //}
////
//        //return text.replace("; ", "\n");
//        return "";
//    }
//
//    public void print() {
//        //List<String> translationList = toList();
//        //String[] sourceList = source.split(", ");
////
//        //int size = translationList.size();
//        //if (sourceList.length > size) { size = sourceList.length; }
//        //for (int i = 0; i < size; i++) {
//        //    StringBuilder sb = new StringBuilder();
//        //    sb.append(i);
//        //    sb.append(". [");
//        //    if (i < translationList.size()) {
//        //        String t = translationList.get(i);
//        //        sb.append(t);
//        //    }
//        //    sb.append("][");
//        //    if (i < sourceList.length) {
//        //        String s = sourceList[i];
//        //        sb.append(s);
//        //    }
//        //    sb.append("]");
//        //    System.out.println(sb);
//        //}
//
//    }
//
//    public void alert() {
//        //List<String> translationList = toList();
//        //String[] sourceList = source.split(", ");
////
//        //StringBuilder sb = new StringBuilder();
//        //int size = translationList.size();
//        //if (sourceList.length > size) { size = sourceList.length; }
//        //for (int i = 0; i < size; i++) {
////
//        //    sb.append(i);
//        //    sb.append(". [");
//        //    if (i < translationList.size()) {
//        //        String t = translationList.get(i);
//        //        sb.append(t);
//        //    }
//        //    sb.append("][");
//        //    if (i < sourceList.length) {
//        //        String s = sourceList[i];
//        //        sb.append(s);
//        //    }
//        //    sb.append("]\n");
//        //}
////
//        //Alert alert = new Alert(Alert.AlertType.NONE);
//        //alert.setTitle("");
//        //alert.setContentText(sb.toString());
//        //alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
//        //alert.show();
//    }
//}
