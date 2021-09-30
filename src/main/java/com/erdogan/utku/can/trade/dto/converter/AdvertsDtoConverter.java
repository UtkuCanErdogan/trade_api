package com.erdogan.utku.can.trade.dto.converter;

import com.erdogan.utku.can.trade.dto.AdvertDto;
import com.erdogan.utku.can.trade.model.Advert;
import org.springframework.stereotype.Component;

@Component
public class AdvertsDtoConverter {

    public AdvertDto convert(Advert from){
        return new AdvertDto(from.getProductName(),from.getCategory(), from.getMark(), from.getDescription(), from.getPrice());
    }
}
