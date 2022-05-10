package com.final_project.chriscosmetic.dto.req;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UpdateCartReqDTO {

    @NotNull
    private List<Integer> quantity;

    public UpdateCartReqDTO() {
    }

    public UpdateCartReqDTO(List<Integer> quantity) {
        this.quantity = quantity;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }
}
