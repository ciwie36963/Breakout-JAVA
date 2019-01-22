/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.groupOperationBehaviours;

import java.util.stream.IntStream;

/**
 *
 * @author Matthias
 */
public class SwapCharBehaviour implements AnwserBehaviour{

    @Override
    public String getAnwser(String exValue, String groupOpValue)
    {
        char[] anwserChars = exValue.toCharArray();
        String[] groupOpSplitted = groupOpValue.split("&");
     
        char char1 = groupOpSplitted[0].charAt(0);
        char char2 = groupOpSplitted[1].charAt(0);
        IntStream.range(0, anwserChars.length).forEach(e -> {
            if(anwserChars[e] == char1)
                anwserChars[e] = char2;
            else if(anwserChars[e] == char2)
                 anwserChars[e] = char1;
        });
        
        return new String(anwserChars);
    }
    
}
