package com.heren.his.register.domain1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Embeddable
public class PeriodOfValidity {
    public static enum Period {
        MORNING, AFTERNOON, EVENING
    }

    @NotNull
    @JsonProperty
    private Date validOn;

    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private Period period;

    private PeriodOfValidity() {
    }

    @JsonCreator
    public PeriodOfValidity(@JsonProperty("validOn") Date validOn,
                            @JsonProperty("period") Period period) {
        this.validOn = validOn;
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeriodOfValidity that = (PeriodOfValidity) o;

        if (period != that.period) return false;
        if (!validOn.equals(that.validOn)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = validOn.hashCode();
        result = 31 * result + period.hashCode();
        return result;
    }
}