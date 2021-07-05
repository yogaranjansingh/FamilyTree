package com.assignment.service;

import com.assignment.model.Gender;
import com.assignment.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RelationshipServiceImpl implements RelationshipService {

    @Override
    public List<Person> getSons(Person root) {
        if(root!=null)
        {
            List<Person> children = root.getChildren();
            if(children!=null)
            {
                return children.stream().filter(c -> c.getGender()== Gender.MALE).collect(Collectors.toList());
            }
        }
        return null;
    }

    @Override
    public List<Person> getDaughters(Person root) {
        if(root!=null)
        {
            List<Person> children = root.getChildren();
            if(children!=null)
            {
                return children.stream().filter(c -> c.getGender()== Gender.FEMALE).collect(Collectors.toList());
            }
        }
        return null;
    }

    @Override
    public List<Person> getPaternalUncles(Person root) {
        if(root!=null)
        {
            Person father = root.getFather();
            if(father!=null) {
                Person grandfather = father.getFather();
                if(grandfather!=null) {
                    List<Person> paternalUncles = grandfather.getSons();
                    paternalUncles.remove(father);
                    return paternalUncles;
                }
            }
        }
        return null;
    }

    @Override
    public List<Person> getMaternalUncles(Person root) {
        if(root!=null)
        {
            Person mother = root.getMother();
            if(mother!=null) {
                Person grandfather = mother.getFather();
                if(grandfather!=null) {
                    List<Person> maternalAunts = grandfather.getSons();
                    return maternalAunts;
                }
            }
        }
        return null;
    }

    @Override
    public List<Person> getPateralAunts(Person root) {
        if(root!=null)
        {
            Person father = root.getFather();
            if(father!=null) {
                Person grandfather = father.getFather();
                if(grandfather!=null) {
                    List<Person> paternalAunts = grandfather.getDaughters();
                    return paternalAunts;
                }
            }
        }
        return null;
    }

    @Override
    public List<Person> getMaternalAunts(Person root) {
        if(root!=null)
        {
            Person mother = root.getMother();
            if(mother!=null) {
                Person grandfather = mother.getFather();
                if(grandfather!=null) {
                    List<Person> maternalAunts = grandfather.getDaughters();
                    maternalAunts.remove(mother);
                    return maternalAunts;
                }
            }
        }
        return null;
    }

    @Override
    public List<Person> getSiblings(Person root) {
        if(root!=null)
        {
            Person father = root.getFather();
            if(father!=null) {
                if(father!=null) {
                    List<Person> children = father.getChildren();
                    children.remove(root);
                    return children;
                }
            }
        }
        return null;
    }

    @Override
    public List<Person> getSisterInLaws(Person root) {
        List<Person> sisterInLaws = new ArrayList<Person>();
        if(root!=null)
        {
            Person father = root.getFather();
            if(father!=null) {
                List<Person> siblings = father.getSons();
                for (Person sibling : siblings) {
                    if (sibling.isMarried()) {
                        sisterInLaws.add(sibling.getSpouse());
                    }
                }
            }
            Person spouse = root.getSpouse();
            if(spouse !=null)
            {
                father = spouse.getFather();
                if(father!=null)
                {
                    List<Person> spouseSisters = father.getDaughters();
                    if(root.getGender()==Gender.FEMALE)
                    spouseSisters.remove(spouse);
                    sisterInLaws.addAll(spouseSisters);
                }
            }
        }
        return sisterInLaws;
    }

    @Override
    public List<Person> getBrotherInLaws(Person root) {
        List<Person> brotherInLaws = new ArrayList<Person>();
        if (root != null) {
            Person father = root.getFather();
            if (father != null) {
                List<Person> siblings = father.getDaughters();
                for (Person sibling : siblings) {
                    if (sibling.isMarried()) {
                        brotherInLaws.add(sibling.getSpouse());
                    }
                }
            }
            Person spouse = root.getSpouse();
            if (spouse != null) {
                father = spouse.getFather();
                if (father != null) {
                    List<Person> spouseBrothers = father.getSons();
                    if (root.getGender() == Gender.MALE)
                        spouseBrothers.remove(spouse);
                    brotherInLaws.addAll(spouseBrothers);
                }
            }
        }
        return brotherInLaws;
    }

    @Override
    public Person getFather(Person root) {
        if(root!=null)
        {
            return root.getFather();
        }
        return null;
    }

    @Override
    public Person getMother(Person root) {
        if(root!=null)
        {
            return root.getMother();
        }
        return null;
    }
}
