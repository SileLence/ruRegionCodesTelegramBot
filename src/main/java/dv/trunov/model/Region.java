package dv.trunov.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Region {

    private int id;
    private int code;
    private String name;
    private String capital;
    private String description;
    private String url;
}
