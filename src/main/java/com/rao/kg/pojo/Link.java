package com.rao.kg.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Link {
    private String id;
    private String source;
    private String type;
    private String target;

    @Override
    public String toString() {
        return "Link{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
