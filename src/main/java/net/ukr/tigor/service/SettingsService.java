package net.ukr.tigor.service;

import net.ukr.tigor.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SettingsService {

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    PositionRepo positionRepo;
    @Autowired
    TypeOfAgriculturalMachineryRepo typeOfAgriculturalMachinery;
    @Autowired
    ManufacturerRepo manufacturerRepo;
    @Autowired
    ContactPersonService contactPersonService;

    Map<String, String> russVoc = new HashMap<String, String>() {
        {
            put("Position", "Должность");
            put("Manufacturer", "Производитель");
            put("TypeOfAgriculturalMachinery", "Вид техники");
        }
    };

    Map<String, String> relatedClass = new HashMap<String, String>() {
        {
            put("Position", "ContactPersonService");
            put("Manufacturer", "UniParkService");
            put("TypeOfAgriculturalMachinery", "UniParkService");
        }
    };

    private Map<String, String> agreementClassParam;

    public Object getPositions() {
        return positionRepo.findAll();
    }

    public Object getTypesEquipment() {
        return typeOfAgriculturalMachinery.findAll();
    }

    public Object getManufacturers() {
        return manufacturerRepo.findAll();
    }

    public Object getThesaurusByName(String className, Long id) {
        Object objOfThesaurus = null;

        try {
            Object bean = appContext.getBean(Class.forName("net.ukr.tigor.repos." + className + "Repo"));
            Optional optThesaurus = ((CrudRepository) bean).findById(id);
            if (optThesaurus.isPresent()) {
                objOfThesaurus = optThesaurus.get();
            } else {
                // get dummy
                Class domainClass = Class.forName("net.ukr.tigor.domain." + className);
                Class[] params = {String.class};
                objOfThesaurus = domainClass.getConstructor(params).newInstance("");

            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return objOfThesaurus;
    }

    public void saveThesaurusByName(String className, Long id, String name) {

        Object objOfThesaurus = null;

        try {
            Object bean = appContext.getBean(Class.forName("net.ukr.tigor.repos." + className + "Repo"));

            Class domainClass = Class.forName("net.ukr.tigor.domain." + className);
            Class[] params = {String.class};
            objOfThesaurus = domainClass.getConstructor(params).newInstance(name);
            if (id != 0) {
                Method setId = domainClass.getMethod("setId", new Class[]{Long.class});
                Object[] args = new Object[]{id};
                setId.invoke(objOfThesaurus, args);
            }
            ((CrudRepository) bean).save(objOfThesaurus);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException |
                NoSuchMethodException e)

        {
            e.printStackTrace();
        }

    }

    public Map<String, Object> getParamForEditThesaurus(String className, Long id) {

        Object elementOfThesaurus = getThesaurusByName(className, id);

        Map<String, Object> param = new HashMap<>();
        param.put("name", russVoc.get(className));
        param.put("object", elementOfThesaurus);

        return param;
    }

    public void saveThesaurus(String thesaurus, Long id, String name) {
        saveThesaurusByName(thesaurus, id, name);
    }

    public void deleteThesaurus(String className, Long id) {
        Class classThesaurus = getClassByName("repos.",className,"Repo");
        if (checkBeforeDelete(className, id)) {
            Object bean = appContext.getBean(classThesaurus);
            ((CrudRepository) bean).deleteById(id);
        }
    }

    private Class getClassByName(String suffix, String className, String postfix) {
        Class cls = null;
        try {
            cls = Class.forName("net.ukr.tigor." + suffix+ className + postfix);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cls;
    }

    private boolean checkBeforeDelete(String parentClassName, Long id) {

        Class relatedClassService = getClassByName("service.",relatedClass.get(parentClassName),"");
        Object beanService = appContext.getBean(relatedClassService);
        boolean ableTodelete = ((Related) beanService).checkRelated(id, parentClassName);

        return ableTodelete;
    }
}
