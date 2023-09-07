package xyz.aqlabs.cookbook.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "cookbook")
@Entity
public class Cookbook {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private String name;
}
