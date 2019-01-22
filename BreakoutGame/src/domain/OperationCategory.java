/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import util.LangConfig;

/**
 *
 * @author Matthias
 */
public enum OperationCategory {
    MULTIPLY("MULTIPLY"),
    MIN("MIN"),
    PLUS("PLUS"),
    SWAPCHAR("SWAPCHAR");
    
    private String key;

    OperationCategory(String key)
    {
        this.key = key;
    }
    public String getDescription() {
        return LangConfig.getString(key+"DESC");
    }
  /*  public String getSort() {
        return LangConfig.getString(key);
    }*/

    @Override
    public String toString()
    {
       return LangConfig.getString(key);
    }
    
}
