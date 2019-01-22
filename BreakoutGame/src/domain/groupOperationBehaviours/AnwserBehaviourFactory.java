/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.groupOperationBehaviours;

import domain.OperationCategory;
import java.util.HashMap;
import java.util.function.Supplier;

/**
 *
 * @author Matthias
 */
public class AnwserBehaviourFactory {

    private HashMap<OperationCategory, Supplier<AnwserBehaviour>> map = new HashMap<>();

    public static AnwserBehaviour createAnwserBehaviour(OperationCategory operationCategory)
    {
        switch (operationCategory)
        {
            case MIN:
                return new MinBehaviour();
            case MULTIPLY:
                return new MultiplyBehaviour();
            case PLUS:
                return new PlusBehaviour();
            case SWAPCHAR:
                return new SwapCharBehaviour();
            default:
                return null;
        }
    }
}
