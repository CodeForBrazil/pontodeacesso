package codeforcuritiba.pontodeacesso.model;

import java.io.Serializable;

/**
 * Created by gleisser on 24/10/2015.
 */
public class Categoria implements Serializable {

    private Long id;

    private String categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
