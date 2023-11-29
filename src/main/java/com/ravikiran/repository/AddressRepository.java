package com.ravikiran.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ravikiran.modal.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
