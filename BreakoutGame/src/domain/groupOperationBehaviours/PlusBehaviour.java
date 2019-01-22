/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.groupOperationBehaviours;

/**
 *
 * @author Matthias
 */
public class PlusBehaviour implements AnwserBehaviour{

    @Override
    public String getAnwser(String exValue, String groupOpValue)
    {
        double anwser = Double.valueOf(exValue);
        double actionValue = Double.valueOf(groupOpValue);
        
        return String.valueOf(anwser + actionValue);
        
    }
    
}
