package popr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.geo.Point;

import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@Data
public class Location implements Serializable {

	private String voivodeship;

	@DecimalMin("-90.0")
	@DecimalMax("90.0")
	@NotNull
	private Double latitude;

	@DecimalMin("-180.0")
	@DecimalMax("180.0")
	@NotNull
	private Double longitude;

	@JsonIgnore
	public Point asPoint() {
		if (longitude != null && latitude != null) {
			return new Point(latitude, longitude);
		} else {
			return null;
		}
	}

}
