package com.example;

import java.io.IOException;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.DaoGenerator;

public class DemoDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "hugh.greendao");
        addNode(schema);
        new DaoGenerator().generateAll(schema,
                "/Users/hugh/AndroidStudioProjects/GreenDaoDemo/app/src/main/java-gen");
    }

    private static void addNode(Schema schema) {
        Entity entity = schema.addEntity("person");
        entity.addIdProperty();
        entity.addStringProperty("name").notNull();
        entity.addIntProperty("age");
        entity.addStringProperty("sex");
    }
}
