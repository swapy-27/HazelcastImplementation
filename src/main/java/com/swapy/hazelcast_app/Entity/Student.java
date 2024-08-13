package com.swapy.hazelcast_app.Entity;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "student_table")
public class Student implements IdentifiedDataSerializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private int age;

    @Override
    public int getFactoryId() {
        return 1; // Ensure this matches with your factory configuration
    }

    @Override
    public int getClassId() {
        return 1; // Ensure this matches with your class configuration in the factory
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeInt(id != null ? id : -1); // Handle nulls appropriately if needed
        out.writeString(lastName);
        out.writeInt(age);
        out.writeString(firstName);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        id = in.readInt();
        if (id == -1) { // Assuming -1 is used as a marker for null
            id = null;
        }
        lastName = in.readString();
        age = in.readInt();
        firstName = in.readString();
    }
}
