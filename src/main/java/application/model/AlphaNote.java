package application.model;

import java.util.List;

public interface AlphaNote {

    int getId();
    String getName();
    List<String> getTags();
    void setId(int id);
    void setName(String name);
    void setTags(List<String> tags);
    String toString();

}
