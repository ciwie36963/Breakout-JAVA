package domain.managers;

import domain.AccessCode;
import domain.BoBAction;
import domain.Category;
import domain.Exercise;
import domain.Goal;
import domain.GroupOperation;
import domain.PersistMode;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceController;

public class ExerciseManager extends Manager<Exercise> {

    private ObservableList<GroupOperation> groupOperationsTemp;
    private ObservableList<Goal> goalsTemp;
    private ObservableList<Category> categories;
    private ObservableList<Category> filterCat;
    private ObservableList<Goal> goals;
    private List<String> goalFilter;

    private Map<String, ObservableList> tempLists;

    protected ExerciseManager()
    {
        super(Exercise.class, new PersistenceController());
        //  setItems(FXCollections.observableList(persistence.getAllOfType(Exercise.class)));
        groupOperationsTemp = FXCollections.observableArrayList();
        goalsTemp = FXCollections.observableArrayList();

    }

    public ExerciseManager(PersistenceController persistence)
    {
        super(Exercise.class, persistence);
        setItems(FXCollections.observableList(persistence.getAllOfType(Exercise.class)));
        groupOperationsTemp = FXCollections.observableArrayList();
        categories = FXCollections.observableArrayList(persistence.getAllOfType(Category.class));
        goals = FXCollections.observableArrayList(persistence.getAllOfType(Goal.class).stream().sorted().collect(Collectors.toList()));
        goalsTemp = FXCollections.observableArrayList();
        filterCat = FXCollections.observableArrayList(categories);
        goalFilter = new ArrayList<>();

        tempLists = new HashMap<>();
        tempLists.put(GroupOperation.class.getSimpleName(), groupOperationsTemp);
        tempLists.put(Goal.class.getSimpleName(), goalsTemp);
    }

    public <T extends IManageable> void addObjectToTemp(List<T> object)
    {
        String key = object.get(0).getClass().getSimpleName();
        tempLists.get(key).addAll(object);
    }

    public <T extends IManageable> void removeObjectFromTemp(List<T> object)
    {
        String key = object.get(0).getClass().getSimpleName();
        tempLists.get(key).removeAll(object);

    }

    public ObservableList<GroupOperation> getGroupOperationsTemp()
    {
        return groupOperationsTemp;
    }

    public ObservableList<Goal> getGoalsTemp()
    {
        return goalsTemp;
    }

    public ObservableList<Category> getCategories()
    {
        return categories;
    }

    public Category getCategory(int id)
    {
        return categories.get(id);
    }

    public ObservableList<Goal> getGoals()
    {
        return goals;
    }

    @Override
    public void save(Exercise object)
    {
        getPersistenceController().setPersistMode(getManagerMode());
        if(getManagerMode() == PersistMode.UPDATE)
            if(getPersistenceController().exInBox(getSelected()))
                throw new IllegalArgumentException("De oefening bevindt zich in een box die zich in een actieve sessie bevind hierdoor kan deze niet worden aangepast");
        ((Exercise) getSelected()).copy(object);
        System.out.println(((Exercise) getSelected()).getId());
        super.save(object);
    }

    @Override
    public void setSelected(Exercise item)
    {
        groupOperationsTemp.setAll(item.getGroupOperations());
        goalsTemp.setAll(item.getGoals());
        super.setSelected(item);

    }

    public void changeFilter(List<Exercise> exercises)
    {
        getFilteredItems().setPredicate(e -> !exercises.contains(e) && filterCat.contains((Category) e.categoryProperty().get())
                && (!Collections.disjoint(e.getGoals().stream().map(e2 -> e2.getCode().toLowerCase()).collect(Collectors.toList()), goalFilter) || goalFilter.isEmpty()));
        System.out.println("");
    }

    public void addCategoryToFilter(Category cat)
    {
        if (filterCat.size() == categories.size())
        {
            filterCat.removeAll(categories);
        }
        if (cat != null && !filterCat.contains(cat))
        {

            filterCat.add(cat);
        }

    }

    public void removeCategoryToFilter(Category cat)
    {
        if (cat != null && filterCat.contains(cat))
        {
            filterCat.remove(cat);
            if (filterCat.isEmpty())
            {
                filterCat.addAll(categories);
            }
        }

    }

    public void changeGoalFilter(List<String> goals)
    {

        this.goalFilter = goals;
    }

    @Override
    public void delete() throws InvocationTargetException
    {
         if(getPersistenceController().exInBox(getSelected()))
                throw new IllegalArgumentException("De oefening bevindt zich in een box die zich in een actieve sessie bevind hierdoor kan deze niet worden aangepast");
        super.delete(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
