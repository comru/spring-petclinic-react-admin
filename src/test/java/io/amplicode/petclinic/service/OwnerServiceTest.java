package io.amplicode.petclinic.service;

import io.amplicode.petclinic.dto.OwnerWithPetsDto;
import io.amplicode.petclinic.service.filter.OwnerFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    @Test
    public void getList() {
        Page<OwnerWithPetsDto> page = ownerService.getList(new OwnerFilter(null), PageRequest.of(0, 5));

        assertEquals(5, page.getSize());

        List<OwnerWithPetsDto> owners = page.getContent();
        for (OwnerWithPetsDto owner : owners) {
            assertFalse(owner.getPets().isEmpty());
        }
    }

}