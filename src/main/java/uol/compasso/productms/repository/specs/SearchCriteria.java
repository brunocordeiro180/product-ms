package uol.compasso.productms.repository.specs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;

}