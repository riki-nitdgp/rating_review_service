package com.rating.ratingreviewservice.clients;

import com.rating.ratingreviewservice.models.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;


@FeignClient(name="inventory-service", url="${inventory.service.host}", decode404 = true)
public interface InventoryServiceClient {

    @GetMapping("/product/{id}")
     Response getProductById(
            @NotNull(message = "Product Id is Missing") @PathVariable("id") Integer id );
}
