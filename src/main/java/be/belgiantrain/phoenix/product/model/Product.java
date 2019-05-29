package be.belgiantrain.phoenix.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * @author Michael Couck
 * @version 1.0
 * @since 28-05-2019
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String code;
    private String name;

    private Map<String, Object> conditions;

}