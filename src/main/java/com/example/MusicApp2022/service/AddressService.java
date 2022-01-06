package com.example.MusicApp2022.service;

import java.util.List;

import com.example.MusicApp2022.shared.dto.AddressDto;

public interface AddressService {

	List<AddressDto> getAddresses(String id);

	AddressDto getAddressById(String addressId);

}
