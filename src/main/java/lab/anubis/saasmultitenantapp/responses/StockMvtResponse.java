package lab.anubis.saasmultitenantapp.responses;

import lab.anubis.saasmultitenantapp.entities.TypeMvt;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockMvtResponse {
    private String id;
    private TypeMvt typeMvt;
    private Integer quantity;
    private LocalDate dateMvt;
    private String comment;
}
