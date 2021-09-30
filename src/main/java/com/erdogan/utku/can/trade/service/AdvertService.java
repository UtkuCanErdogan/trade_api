package com.erdogan.utku.can.trade.service;

import com.erdogan.utku.can.trade.dto.AdvertDto;
import com.erdogan.utku.can.trade.dto.CreateAdvertRequest;
import com.erdogan.utku.can.trade.dto.UpdateAdvertRequest;
import com.erdogan.utku.can.trade.dto.converter.AdvertsDtoConverter;
import com.erdogan.utku.can.trade.exception.AdvertNotFoundException;
import com.erdogan.utku.can.trade.model.Advert;
import com.erdogan.utku.can.trade.model.AdvertType;
import com.erdogan.utku.can.trade.model.User;
import com.erdogan.utku.can.trade.repository.AdvertRepository;
import org.springframework.stereotype.Service;

@Service
public class AdvertService {

    private final AdvertRepository repository;
    private final AdvertsDtoConverter converter;
    private final UserService service;

    public AdvertService(AdvertRepository repository, AdvertsDtoConverter converter, UserService service) {
        this.repository = repository;
        this.converter = converter;
        this.service = service;
    }

    public  AdvertDto getAdvertById(final Long advertId){
        Advert advert = findAdvertById(advertId);
        return converter.convert(advert);
    }

    public AdvertDto createAdvert(final CreateAdvertRequest request){
        User user = service.findUserById(request.getUserId());

        Advert createAdvert = new Advert(
                request.getProductName(),
                request.getCategory(),
                request.getMark(),
                request.getDescription(),
                request.getPrice(),
                AdvertType.INVALID,
                user);

        return converter.convert(repository.save(createAdvert));
    }

    public AdvertDto updateAdvert(final UpdateAdvertRequest request, final Long advertId){
        Advert advert =  findAdvertById(advertId);

        Advert updatedAdvert = new Advert(
                advert.getId(),
                request.getProductName(),
                advert.getCategory(),
                advert.getMark(),
                request.getDescription(),
                request.getPrice(),
                advert.getAdvertType(),
                advert.getUser(),
                advert.getComments());

        return converter.convert(repository.save(updatedAdvert));
    }

    public void deleteAdvert(final Long advertId){
        findAdvertById(advertId);
        repository.deleteById(advertId);
    }

    public void toActiveAdvert(final Long advertId){
        changeActivate(advertId,AdvertType.ACTIVE);
    }

    public void deActiveAdvert(final Long advertId){
        changeActivate(advertId,AdvertType.INVALID);
    }

    public void soldAdvert(final Long advertId){
        changeActivate(advertId, AdvertType.SOLD);
    }

    protected Advert findAdvertById(final Long advertId){
        return repository.findById(advertId).orElseThrow(() -> new AdvertNotFoundException("İlan Bulunamadı"));
    }

    private void changeActivate(final Long advertId, final AdvertType type){
        Advert advert = findAdvertById(advertId);
        Advert changeActivate = new Advert(
                advert.getId(),
                advert.getProductName(),
                advert.getCategory(),
                advert.getMark(),
                advert.getDescription(),
                advert.getPrice(),
                type,
                advert.getUser(),
                advert.getComments()
        );
        repository.save(changeActivate);
    }
}
