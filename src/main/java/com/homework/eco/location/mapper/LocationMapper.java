package com.homework.eco.location.mapper;

import com.homework.eco.location.dto.LocationDto;
import com.homework.eco.location.entity.Country;
import com.homework.eco.location.entity.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    default LocationDto.Detail entityToDetail(Location location) {
        if ( location == null ) {
            return null;
        }

        Country country = location.getCountryId();

        LocationDto.Detail detail = new LocationDto.Detail();

        detail.setLocationId( location.getId() );
        detail.setCountryId( country.getId() );
        detail.setStreetAddress( location.getStreetAddress() );
        detail.setPostalCode( location.getPostalCode() );
        detail.setCity( location.getCity() );
        detail.setStateProvince( location.getStateProvince() );


        return detail;
    }
}
