package com.erdogan.utku.can.trade.dto.converter;

import com.erdogan.utku.can.trade.dto.CategoryDto;
import com.erdogan.utku.can.trade.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoConverter {

    public CategoryDto convert(Category from){
        return new CategoryDto(from.getName());
    }
}
