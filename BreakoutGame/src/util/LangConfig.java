/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Matthias
 */
public class LangConfig {
    private static Locale currentLocale = Locale.getDefault();
    private static ResourceBundle rs = ResourceBundle.getBundle("resources/lang/Lang", new Locale("nl_BE"));
    public static void setLang() {
        currentLocale = new Locale("nl_BE");
        Locale.setDefault(currentLocale);
        rs = ResourceBundle.getBundle("resources/lang/Lang", Locale.getDefault());
    }
    public static String getString(String key) {
        return rs.getString(key);
    }
}
